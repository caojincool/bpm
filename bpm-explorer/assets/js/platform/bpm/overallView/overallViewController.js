var overallViewApp = angular.module('overallViewApp', [ 'formDirective', 'arrayToolService', 'base' ]);

overallViewApp.controller("overallViewController", [ '$scope', 'baseService', 'ArrayToolService','$http', function($scope, baseService, ArrayToolService, $http) {
		$scope.ArrayTool = ArrayToolService;
		$scope.defId = defId;
		
		$scope.getOverallViewByDefId = function(){
			var param = {defId:defId,topDefKey:topDefKey};
			var defer = baseService.postForm(__ctx+"/flow/overallView/getOverallView",param);
			defer.then(function(data){
				$scope.overallView = data;
			},function(code){
				$.topCall.error("获取异常"+code);
			});
		}
		if(defId){
			$scope.getOverallViewByDefId();
		}
		
		// 一览页保存
		$scope.saveOverallView = function(){
			var defer = baseService.post(__ctx+"/flow/overallView/overallViewSave",$scope.overallView);
			defer.then(function(data){
				if(data.result==1){
					$.topCall.success("保存成功！");
					$scope.getOverallViewByDefId();
				}else{
					$.topCall.error("保存异常"+data.message);
				}
				
			},function(code){
				$.topCall.error("获取流程定义异常"+code);
			});
		}
		
		// 上传保存
		$scope.saveImportPreview = function(){
			var overallViewList = [];
			if($scope.importOverallViewMap)
			for(var key in $scope.importOverallViewMap){
				var obj = $scope.importOverallViewMap[key][0];
				if(obj.defId){
					$.topCall.error("数据异常");
					console.error($scope.importOverallViewMap);
					return;
				}
				
				overallViewList.push(obj);
			}
			if(overallViewList.length==0){
				$.topCall.error("要上传的数据不存在！");
				return;
			}
			
			var defer = baseService.post(__ctx+"/flow/overallView/importSave",overallViewList);
			defer.then(function(data){
				if(data.result==1){
					$.topCall.success(data.message,function(){
						window.location = "overallViewUpload";
					});
				}else{
					$.topCall.error("保存异常"+data.message);
				}
				
			},function(code){
				$.topCall.error("获取流程定义异常"+code);
			});
		}
		
		$scope.importPreview = function(){
			var filed = document.querySelector('input[name=xmlFile]').files[0];
			if(!filed){
				$.topCall.error("请先选择文件");
				return;
			}
			
			var fd = new FormData();
			fd.append('xmlFile', filed); 
			$http({
	              method:'POST',
	              url: __ctx + "/flow/overallView/importPreview",
	              data: fd,
	              headers: {'Content-Type':undefined},transformRequest: angular.identity})   
	              .success(function(data) {
	            	  if(data.message){
	            		  $.topCall.error(data.message);
	            		  return
	            	  }
	            	  $scope.importOverallViewMap = data;
	            	  window.setTimeout(function(){
	            		  $('#tt').tabs({width:'100%',height:'100%'});
	            	  },100)
	               }); 

	     }
		
		
		$scope.changeShowBtn = function(buttons){
			if(this.btn.isShow){
				this.btn.isShow = false;
				return;
			}
			
			for(var i=0,btn;btn=buttons[i++];){
				btn.isShow = false;
			}
			this.btn.isShow = true;
		}
		//让每个格子高度适配
		$scope.fixConfsHeight = function(){
			var overallConfs = $(".overallConf");
			if(overallConfs.length==2){
				$("fieldset",overallConfs[0]).each(function(){
					var name = $(this).attr("name");
					var height = this.style.height;
					var oldFieldset = $("[name='"+name+"']",overallConfs[1]);
					if(oldFieldset[0]){
						if(oldFieldset[0].style.height <height){
							oldFieldset[0].style.height = height;
						}else{
							this.style.height = oldFieldset[0].style.height;
						}
					}
					
				});
				
				
			}
			
			
		}
		//判断节点属性是否一致
		$scope.getNodeIsSame = function(nodeConf,compareOverallView,path){
			// 找到conf
			var compareNode = null;
			var nodeId = null;
			for(var i=0,node;node = compareOverallView.nodeConfs[i++];){
				if(node.nodeId===nodeConf.nodeId){
					compareNode = node;
					nodeId = node.nodeId;
					break;
				}
			}
			if(compareNode == null) return false;
			
			try {
				var newVal = eval("nodeConf."+path);
				var oldVal = eval("compareNode."+path);
				
				if(typeof newVal == "object" ){ newVal = JSON.stringify(newVal)};
				if(typeof oldVal == "object" ){ oldVal = JSON.stringify(oldVal)};
				
				if(typeof  newVal == "string"){ newVal = newVal.trim(); };
				if(typeof  oldVal == "string"){ oldVal = oldVal.trim(); };
				if(newVal == undefined){ newVal = ""; };
				if(oldVal == undefined){ oldVal = ""; };
				
				return newVal === oldVal;
			} catch (e) {
				return false;
			}
		}
		
		$scope.getIsSame = function(newObj,oldObj,path){
			try {
				var newVal = eval("newObj."+path);
				var oldVal = eval("oldObj."+path);
				
				if(typeof  newVal == "string"){
					newVal = newVal.trim();
				}
				if(typeof  oldVal == "string"){
					oldVal = oldVal.trim();
				}
				
				if(newVal == undefined){ newVal = ""; }
				if(oldVal == undefined){ oldVal = ""; }
				
				return newVal === oldVal;
			} catch (e) {
				return false;
			}
		}
		
		$scope.autoTextAreaHeight = function(suffix){
			if(!$scope[suffix+"HeightMap"]){
				$scope[suffix+"HeightMap"] = {};
			}
			var trs = $("[id$='"+suffix+"']");
			var maxHeight = 0;
			trs.each(function(i){
				$("textarea",$(this)).each(function(){
					if(!maxHeight || maxHeight < this.scrollHeight){
						maxHeight = this.scrollHeight;
					}
				})
				// 赋高度
				if(maxHeight)
				$("textarea",$(this)).each(function(){
					this.style.height  = maxHeight+ 2 +"px";
				})
				maxHeight = 0;
			})
			trs.each(function(i){
				$scope[suffix+"HeightMap"][$(this).attr("id")] = this.style.height;
			})
		}
		
		$scope.justShowOne = function(showOne){
			this.showForm=false;this.showNodeSkip=false;
			this.showScript=false;this.showBtn=false;
			this.isShowGateway=false;this.showGlobalConf=false;
			this.showUserConf=false;
			this[showOne] = true;
		}
		
		window.setTimeout(function(){
			$(".easyui-panel").panel();
		},100)
	}
]);




overallViewApp.service('overallViewService', ['$rootScope','baseService','$compile', function($rootScope,baseService,$compile) {
	return {
		
		
		
	};
	}
]);

overallViewApp.directive('dynamicDirective', function($compile) {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			var directives = attrs.dynamicDirective;
			if(!directives)return;
			var directiveArr = directives.split(",");
			
			var str = "<div ";
			for(var i=0,d;d=directiveArr[i++];){
				str=str+d.split(":")[0]+"="+d.split(":")[1]+" ";
			}
			str +="></div>";
			
			element.removeAttr("dynamic-directive")
			element.removeClass("ng-binding");
			element.html(str);
            $compile(element)(scope);
		}
	};
})
overallViewApp.directive('abTrim', function() {
	return {
		require : "ngModel",
		restrict : 'A',
		link : function(scope, element, attrs,ctrl) {
			
			ctrl.$formatters.push(function(val){
				if(typeof  val == "string"){
					return val.trim();
				}
			});
		}
	};
}).directive('abShowJson', function() {
	return {
		require : "ngModel",
		restrict : 'A',
		link : function(scope, element, attrs,ctrl) {
			var format = true;
			if(attrs.abShowJson =="false"){
				format = false;
			}
			
			ctrl.$formatters.push(function(val){
				if(!val)return val;
				var str = JSON.stringify(val)
				if(format){
				    str = formatJson(val).trim();
				}
				return str;
			});
			
			ctrl.$parsers.push(function(val){
				if(!val)return null;
				
				return eval("("+val+")");
			})
		}
	};
})
.directive('bpmForm', function() {
	return {
    	restrict : 'AE',
    	scope:{
    		bpmForm:"=",
    		mobileForm:"="
    	},
    	link: function(scope, element, attrs,ctrl){
    		scope.title = attrs.title;
    		var type = scope.type = attrs.type;
    		//选择表单
    		scope.selectForm = function(isPc){
    			var boCodes = scope.getBoCodes();
    			if(!boCodes){
    				jQuery.Dialog.warning("配置自定义表单，必须首先选择业务数据模型！")
    				return;
    			}
    			
    			CustUtil.openCustDialog("formSelector",{bo_key_:boCodes,type_:isPc?"pc":"mobile"},function(arrData,innerWindow){
    				var formStr = isPc?"bpmForm":"mobileForm";
    				scope.$apply(function(){
    					if(!scope[formStr])scope[formStr]={};
    					scope[formStr].type = 'INNER';
    					scope[formStr].name = arrData[0].name;
        				scope[formStr].formValue = arrData[0].key;	
    				})
		 			
		 		    jQuery.Dialog.close(innerWindow);
		 		})
    		}
    		//授权
    		scope.authorize = function(){
    			var boCodes = scope.getBoCodes();
    			if(!boCodes){
    				jQuery.Dialog.warning("配置业务数据权限，必须首先选择业务数据模型！")
    				return;
    			}
    			var objVal = type;
    			var objName = type == "global"?"全局":"实例";
    			if(type==="node"){
    				objVal = scope.$parent.selectedItem.nodeId;
    				objName = scope.$parent.selectedItem.title;
    			}
    			objVal = scope.$parent.bpmDefSetting.bpmDefinition.key + "-" + objVal;
    			
    			var url = '/bus/businessPermission/businessPermissionEdit.html?objType=flow&objVal='+objVal+'&boKeys=' + boCodes;
    			var def = { title : objName+"授权", width : 800, height : 600, modal : true, resizable : true};
    			def.url = url;
    			jQuery.Dialog.open(def);
    		}
    		
    		scope.getBoCodes = function(){
    			var boCodes = "";
    			for(var i=0,dm;dm= scope.$parent.bpmDefSetting.flow.dataModelList[i++];){
    				if(boCodes){boCodes = boCodes + ","};
    				boCodes = boCodes + dm.code;
    			}
    			return boCodes;
    			
    		}
    		
    		//清楚表单
    		scope.clearForm = function(form){
				form.name = "";
				form.formValue ="";
    		}
    		scope.changeFormType = function(){
    			scope.bpmForm.name = "";
    			scope.bpmForm.formValue ="";
    			if(scope.mobileForm){
    				scope.mobileForm.name = "";
    				scope.mobileForm.formValue ="";
    				scope.mobileForm.type = scope.bpmForm.type;
    			}
    		}
    	},                                                                                                                                                 
    	template:'<div class="panel-body" style="min-height: 100px">                                                                                       \
    		<table class="table table-hover table-bordered">                                                                                 \
		    	<tr>                                                                                                                                               \
		    		<th>{{title}}</th>                                                                                                                             \
		    		<td>                                                                                                                                           \
		    			<select class="form-control" ng-model="bpmForm.type" ng-change="changeFormType()">                                                       								 \
    						<option value="">请选择</option>\
		    				<option value="INNER">在线表单</option>                                                                                                \
		    				<option value="FRAME">URL表单</option>                                                                                                 \
		    			</select>                                                                                                                                  \
		    		</td>                                                                                                                                          \
		    		<td ng-if="bpmForm.type==\'INNER\'" ><a href="javascript:void(0);" class="btn btn-info btn-sm" ng-click="authorize()">授权</a></td>            \
		    	</tr>                                                                                                                                              \
		    	<tr ng-if="bpmForm.type==\'INNER\'" >                                                                                                              \
		    		<td>PC端</td>                                                                                                                                  \
		    		<td>  {{bpmForm.name}}  </td>                                                                                                                  \
		    		<td>                                                                                                                                           \
		    			<a href="javascript:void(0);" class="btn btn-info btn-sm glyphicon glyphicon-search" ng-click="selectForm(true)"></a>                      \
		    			<a href="javascript:void(0);" class="btn btn-info btn-sm glyphicon glyphicon-repeat" ng-click="clearForm(bpmForm)"></a>                    \
		    		</td>                                                                                                                                          \
		    	</tr>                                                                                                                                              \
		    	<tr ng-if="bpmForm.type==\'FRAME\'">                                                                                                               \
		    		<td>PC端URL:</td>                                                                                                                              \
		    		<td> <input ng-model="bpmForm.formValue" class="form-control"></td>                                                                            \
		    	</tr>                                                                                                                                              \
		    	                                                                                                                                                   \
		    	<tr  ng-if="bpmForm.type==\'INNER\'" >                                                                                                             \
		    		<td>移动端</td>                                                                                                                                \
		    		<td>  {{mobileForm.name}} </td>                                                                                                                \
		    		<td>                                                                                                                                           \
		    			<a href="javascript:void(0);" class="btn btn-info btn-sm  glyphicon glyphicon-search" ng-click="selectForm(false)"></a>                    \
		    			<a href="javascript:void(0);" class="btn btn-info btn-sm glyphicon glyphicon-repeat" ng-click="clearForm(mobileForm)"></a>                 \
		    		</td>									                                                                                                       \
		    	</tr>                                                                                                                                              \
		    	                                                                                                                                                   \
		    	<tr ng-if="bpmForm.type==\'FRAME\'">                                                                                                               \
		    		<td>移动端URL:</td>                                                                                                                            \
		    		<td> <input ng-model="mobileForm.formValue" class="form-control"></td>                                                                         \
		    	</tr>                                                                                                                                              \
	    		<tr ng-if="bpmForm.type==\'FRAME\' && type != \'instance\'">                                                                                                               \
	    			<td>URL表单处理器</td>                                                                                                                              \
	    			<td> <input ng-model="bpmForm.formHandler" class="form-control"></td>                                                                            \
	    		</tr>																																			   \
		    </table>                                                                                                                                               \
		    </div>' 
	}})
