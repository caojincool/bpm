 package com.dstz.bpm.engine.data;
 
 import com.alibaba.fastjson.JSONObject;
 import com.dstz.base.api.exception.BusinessException;
 import com.dstz.base.api.exception.SystemException;
 import com.dstz.base.core.util.BeanUtils;
 import com.dstz.base.core.util.StringUtil;
 import com.dstz.bpm.api.engine.action.button.ButtonFactory;
 import com.dstz.bpm.api.engine.data.BpmFlowDataAccessor;
 import com.dstz.bpm.api.engine.data.result.BpmFlowData;
 import com.dstz.bpm.api.engine.data.result.BpmFlowInstanceData;
 import com.dstz.bpm.api.engine.data.result.BpmFlowTaskData;
 import com.dstz.bpm.api.exception.BpmStatusCode;
 import com.dstz.bpm.api.model.def.BpmProcessDef;
 import com.dstz.bpm.api.model.def.NodeInit;
 import com.dstz.bpm.api.model.form.BpmForm;
 import com.dstz.bpm.api.model.inst.IBpmInstance;
 import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
 import com.dstz.bpm.api.model.nodedef.Button;
 import com.dstz.bpm.api.model.task.IBpmTask;
 import com.dstz.bpm.api.service.BpmProcessDefService;
 import com.dstz.bpm.api.service.BpmRightsFormService;
 import com.dstz.bpm.core.manager.BpmBusLinkManager;
 import com.dstz.bpm.core.manager.BpmDefinitionManager;
 import com.dstz.bpm.core.manager.BpmInstanceManager;
 import com.dstz.bpm.core.manager.BpmTaskManager;
 import com.dstz.bpm.core.model.BpmInstance;
 import com.dstz.bpm.engine.data.handle.BpmBusDataHandle;
 import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
 import com.dstz.bus.api.model.IBusinessData;
 import com.dstz.bus.api.model.IBusinessPermission;
 import com.dstz.bus.api.service.IBusinessDataService;
 import com.dstz.form.api.model.FormCategory;
 import com.dstz.form.api.model.FormType;
 import com.dstz.form.api.model.IFormDef;
 import com.dstz.form.api.service.FormService;
 import com.dstz.sys.api.groovy.IGroovyScriptEngine;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.annotation.Resource;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.stereotype.Component;
 
 
 
 
 
 
 @Component
 public class DefaultBpmFlowDataAccessor
   implements BpmFlowDataAccessor
 {
   protected Logger LOG = LoggerFactory.getLogger(getClass());
   
   @Resource
   BpmInstanceManager aD;
   @Resource
   BpmRightsFormService aE;
   @Resource
   BpmDefinitionManager c;
   @Resource
   BpmProcessDefService a;
   @Resource
   BpmTaskManager ay;
   @Resource
   FormService aF;
   @Resource
   BpmBusLinkManager aG;
   @Resource
   BpmBusDataHandle aH;
   @Resource
   IGroovyScriptEngine av;
   @Resource
   IBusinessDataService businessDataService;
   
   public BpmFlowInstanceData getInstanceData(String instanceId, FormType formType, String nodeId)
   {
     BpmFlowInstanceData data = new BpmFlowInstanceData();
     
     BpmInstance instance = (BpmInstance)this.aD.get(instanceId);
     data.setInstance(instance);
     
     a(data, instanceId, nodeId, formType, true);
     a(data, nodeId, true);
     return data;
   }
   
 
   public BpmFlowData getStartFlowData(String defId, String instanceId, FormType formType, Boolean readonly)
   {
     if ((StringUtil.isEmpty(instanceId)) && (StringUtil.isEmpty(defId))) {
       throw new BusinessException("获取发起流程数据失败!流程定义id或者实例id缺失", BpmStatusCode.PARAM_ILLEGAL);
     }
     
     BpmFlowInstanceData data = new BpmFlowInstanceData();
     
     if (StringUtil.isEmpty(instanceId)) {
       data.setDefId(defId);
       a(data, formType);
     } else {
       BpmInstance instance = (BpmInstance)this.aD.get(instanceId);
       data.setInstance(instance);
       BpmNodeDef startNode = this.a.getStartEvent(instance.getDefId());
       a(data, instanceId, readonly.booleanValue() ? "" : startNode.getNodeId(), formType, readonly.booleanValue());
     }
     
     BpmNodeDef startNode = this.a.getStartEvent(data.getDefId());
     a(data, startNode.getNodeId(), readonly.booleanValue());
     
     return data;
   }
   
 
   public BpmFlowData getFlowTaskData(String taskId, FormType formType)
   {
     BpmFlowTaskData taskData = new BpmFlowTaskData();
     
     IBpmTask task = (IBpmTask)this.ay.get(taskId);
     if (task == null) {
       throw new BusinessException("任务可能已经办理完成", BpmStatusCode.TASK_NOT_FOUND);
     }
     
     taskData.setTask(task);
     
     a(taskData, task.getInstId(), task.getNodeId(), formType, false);
     
     a(taskData, task.getNodeId(), false);
     
     return taskData;
   }
   
 
 
 
 
 
   private void a(BpmFlowInstanceData flowData, FormType formType)
   {
     String defId = flowData.getDefId();
     BpmNodeDef startNode = this.a.getStartEvent(defId);
     flowData.setDefName(this.a.getBpmProcessDef(defId).getName());
     
 
     IBusinessPermission permission = this.aE.getInstanceFormPermission(flowData, startNode.getNodeId(), formType, false);
     BpmForm form = flowData.getForm();
     
     if (FormCategory.INNER.equals(form.getType())) {
       Map<String, IBusinessData> dataMap = this.aH.a(permission, defId);
       
       IFormDef formDef = this.aF.getByFormKey(form.getFormValue());
       if (formDef == null) {
         throw new BusinessException(form.getFormValue(), BpmStatusCode.FLOW_FORM_LOSE);
       }
       
       form.setFormHtml(formDef.getHtml());
       flowData.setDataMap(dataMap);
       
       a(flowData, startNode.getNodeId());
     } else {
       String url = form.getFormValue().replace("{bizId}", "");
       form.setFormValue(url);
     }
   }
   
 
 
 
 
 
 
 
 
 
   private void a(BpmFlowData flowData, String instaneId, String nodeId, FormType formType, boolean isReadOnly)
   {
     BpmInstance instance = (BpmInstance)this.aD.get(instaneId);
     String defKey = instance.getDefKey();
     
 
     IBusinessPermission businessPermision = this.aE.getInstanceFormPermission(flowData, nodeId, formType, isReadOnly);
     
     BpmForm form = flowData.getForm();
     if (FormCategory.INNER.equals(form.getType())) {
       Map<String, IBusinessData> dataModel = this.aH.a(businessPermision, instance);
       flowData.setDataMap(dataModel);
       
       IFormDef formDef = this.aF.getByFormKey(form.getFormValue());
       if (formDef == null) {
         throw new BusinessException(form.getFormValue(), BpmStatusCode.FLOW_FORM_LOSE);
       }
       
 
       form.setFormHtml(formDef.getHtml());
       
 
       a(flowData, nodeId);
     }
     a(form, instance);
   }
   
   private void a(BpmFlowData flowData, String nodeId)
   {
     Map<String, IBusinessData> bos = flowData.getDataMap();
     if (BeanUtils.isEmpty(bos)) { return;
     }
     DefaultBpmProcessDef def = (DefaultBpmProcessDef)this.a.getBpmProcessDef(flowData.getDefId());
     
 
     Map<String, Object> param = new HashMap();
     param.putAll(bos);
     if ((flowData instanceof BpmFlowTaskData)) {
       param.put("task", ((BpmFlowTaskData)flowData).getTask());
     } else if ((flowData instanceof BpmFlowInstanceData)) {
       param.put("instance", ((BpmFlowInstanceData)flowData).getInstance());
     }
     
 
     for (NodeInit init : def.e(nodeId)) {
       if (StringUtil.isNotEmpty(init.getBeforeShow())) {
         try {
           this.av.execute(init.getBeforeShow(), param);
         } catch (Exception e) {
           throw new SystemException("执行脚本初始化失败", BpmStatusCode.FLOW_DATA_EXECUTE_SHOWSCRIPT_ERROR, e);
         }
         this.LOG.debug("执行节点数据初始化脚本{}", init.getBeforeShow());
       }
     }
     
 
     JSONObject json = new JSONObject();
     JSONObject initJson = new JSONObject();
     for (String key : bos.keySet()) {
       IBusinessData bd = (IBusinessData)bos.get(key);
       JSONObject boJson = this.businessDataService.assemblyFormDefData(bd);
       json.put(key, boJson);
       
 
       bd.fullBusDataInitData(initJson);
     }
     
     flowData.setData(json);
     flowData.setInitData(initJson);
   }
   
 
 
 
 
 
 
 
 
   private void a(BpmFlowData flowData, String nodeId, boolean isReadOnly)
   {
     BpmNodeDef nodeDef = this.a.getBpmNodeDef(flowData.getDefId(), nodeId);
     List<Button> buttons = nodeDef.getButtons();
     if (isReadOnly) {
       buttons = ButtonFactory.getInstanceButtons();
     }
     
     Map<String, Object> param = new HashMap();
     if (BeanUtils.isNotEmpty(flowData.getDataMap())) {
       param.putAll(flowData.getDataMap());
     }
     if ((flowData instanceof BpmFlowTaskData)) {
       param.put("task", ((BpmFlowTaskData)flowData).getTask());
     } else if ((flowData instanceof BpmFlowInstanceData)) {
       param.put("instance", ((BpmFlowInstanceData)flowData).getInstance());
     }
     
     List<Button> btns = new ArrayList();
     for (Button btn : buttons) {
       if (StringUtil.isNotEmpty(btn.getGroovyScript())) {
         try {
           boolean isSkip = this.av.executeBoolean(btn.getGroovyScript(), param);
           this.LOG.debug("执行节点按钮脚本{},执行跳过结果{}", btn.getGroovyScript(), Boolean.valueOf(isSkip));
           
           if (isSkip)
             continue;
         } catch (Exception e) { throw new SystemException("按钮脚本执行失败", BpmStatusCode.FLOW_DATA_GET_BUTTONS_ERROR, e);
         }
       } else {
         btns.add(btn);
       }
     }
     flowData.setButtonList(btns);
   }
   
 
 
 
 
 
 
   private void a(BpmForm form, IBpmInstance instance)
   {
     if ((form == null) || (form.isFormEmpty()) || (FormCategory.INNER == form.getType())) { return;
     }
     
     String url = form.getFormValue().replace("{bizId}", instance.getBizKey());
     
     form.setFormValue(url);
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\data\DefaultBpmFlowDataAccessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */