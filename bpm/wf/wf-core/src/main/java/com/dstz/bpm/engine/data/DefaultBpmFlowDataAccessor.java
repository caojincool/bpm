/*     */ package com.dstz.bpm.engine.data;
/*     */ 
/*     */ import com.alibaba.fastjson.JSONObject;
/*     */ import com.dstz.base.api.exception.BusinessException;
/*     */ import com.dstz.base.api.exception.SystemException;
/*     */ import com.dstz.base.core.util.BeanUtils;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.bpm.api.engine.action.button.ButtonFactory;
/*     */ import com.dstz.bpm.api.engine.data.BpmFlowDataAccessor;
/*     */ import com.dstz.bpm.api.engine.data.result.BpmFlowData;
/*     */ import com.dstz.bpm.api.engine.data.result.BpmFlowInstanceData;
/*     */ import com.dstz.bpm.api.engine.data.result.BpmFlowTaskData;
/*     */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*     */ import com.dstz.bpm.api.model.def.BpmProcessDef;
/*     */ import com.dstz.bpm.api.model.def.NodeInit;
/*     */ import com.dstz.bpm.api.model.form.BpmForm;
/*     */ import com.dstz.bpm.api.model.inst.IBpmInstance;
/*     */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*     */ import com.dstz.bpm.api.model.nodedef.Button;
/*     */ import com.dstz.bpm.api.model.task.IBpmTask;
/*     */ import com.dstz.bpm.api.service.BpmProcessDefService;
/*     */ import com.dstz.bpm.api.service.BpmRightsFormService;
/*     */ import com.dstz.bpm.core.manager.BpmBusLinkManager;
/*     */ import com.dstz.bpm.core.manager.BpmDefinitionManager;
/*     */ import com.dstz.bpm.core.manager.BpmInstanceManager;
/*     */ import com.dstz.bpm.core.manager.BpmTaskManager;
/*     */ import com.dstz.bpm.core.model.BpmInstance;
/*     */ import com.dstz.bpm.engine.data.handle.BpmBusDataHandle;
/*     */ import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
/*     */ import com.dstz.bus.api.model.IBusinessData;
/*     */ import com.dstz.bus.api.model.IBusinessPermission;
/*     */ import com.dstz.bus.api.service.IBusinessDataService;
/*     */ import com.dstz.form.api.model.FormCategory;
/*     */ import com.dstz.form.api.model.FormType;
/*     */ import com.dstz.form.api.model.IFormDef;
/*     */ import com.dstz.form.api.service.FormService;
/*     */ import com.dstz.sys.api.groovy.IGroovyScriptEngine;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Resource;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ public class DefaultBpmFlowDataAccessor
/*     */   implements BpmFlowDataAccessor
/*     */ {
/*  56 */   protected Logger LOG = LoggerFactory.getLogger(getClass());
/*     */   
/*     */   @Resource
/*     */   BpmInstanceManager aD;
/*     */   @Resource
/*     */   BpmRightsFormService aE;
/*     */   @Resource
/*     */   BpmDefinitionManager c;
/*     */   @Resource
/*     */   BpmProcessDefService a;
/*     */   @Resource
/*     */   BpmTaskManager ay;
/*     */   @Resource
/*     */   FormService aF;
/*     */   @Resource
/*     */   BpmBusLinkManager aG;
/*     */   @Resource
/*     */   BpmBusDataHandle aH;
/*     */   @Resource
/*     */   IGroovyScriptEngine av;
/*     */   @Resource
/*     */   IBusinessDataService businessDataService;
/*     */   
/*     */   public BpmFlowInstanceData getInstanceData(String instanceId, FormType formType, String nodeId)
/*     */   {
/*  81 */     BpmFlowInstanceData data = new BpmFlowInstanceData();
/*     */     
/*  83 */     BpmInstance instance = (BpmInstance)this.aD.get(instanceId);
/*  84 */     data.setInstance(instance);
/*     */     
/*  86 */     a(data, instanceId, nodeId, formType, true);
/*  87 */     a(data, nodeId, true);
/*  88 */     return data;
/*     */   }
/*     */   
/*     */ 
/*     */   public BpmFlowData getStartFlowData(String defId, String instanceId, FormType formType, Boolean readonly)
/*     */   {
/*  94 */     if ((StringUtil.isEmpty(instanceId)) && (StringUtil.isEmpty(defId))) {
/*  95 */       throw new BusinessException("获取发起流程数据失败!流程定义id或者实例id缺失", BpmStatusCode.PARAM_ILLEGAL);
/*     */     }
/*     */     
/*  98 */     BpmFlowInstanceData data = new BpmFlowInstanceData();
/*     */     
/* 100 */     if (StringUtil.isEmpty(instanceId)) {
/* 101 */       data.setDefId(defId);
/* 102 */       a(data, formType);
/*     */     } else {
/* 104 */       BpmInstance instance = (BpmInstance)this.aD.get(instanceId);
/* 105 */       data.setInstance(instance);
/* 106 */       BpmNodeDef startNode = this.a.getStartEvent(instance.getDefId());
/* 107 */       a(data, instanceId, readonly.booleanValue() ? "" : startNode.getNodeId(), formType, readonly.booleanValue());
/*     */     }
/*     */     
/* 110 */     BpmNodeDef startNode = this.a.getStartEvent(data.getDefId());
/* 111 */     a(data, startNode.getNodeId(), readonly.booleanValue());
/*     */     
/* 113 */     return data;
/*     */   }
/*     */   
/*     */ 
/*     */   public BpmFlowData getFlowTaskData(String taskId, FormType formType)
/*     */   {
/* 119 */     BpmFlowTaskData taskData = new BpmFlowTaskData();
/*     */     
/* 121 */     IBpmTask task = (IBpmTask)this.ay.get(taskId);
/* 122 */     if (task == null) {
/* 123 */       throw new BusinessException("任务可能已经办理完成", BpmStatusCode.TASK_NOT_FOUND);
/*     */     }
/*     */     
/* 126 */     taskData.setTask(task);
/*     */     
/* 128 */     a(taskData, task.getInstId(), task.getNodeId(), formType, false);
/*     */     
/* 130 */     a(taskData, task.getNodeId(), false);
/*     */     
/* 132 */     return taskData;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void a(BpmFlowInstanceData flowData, FormType formType)
/*     */   {
/* 142 */     String defId = flowData.getDefId();
/* 143 */     BpmNodeDef startNode = this.a.getStartEvent(defId);
/* 144 */     flowData.setDefName(this.a.getBpmProcessDef(defId).getName());
/*     */     
/*     */ 
/* 147 */     IBusinessPermission permission = this.aE.getInstanceFormPermission(flowData, startNode.getNodeId(), formType, false);
/* 148 */     BpmForm form = flowData.getForm();
/*     */     
/* 150 */     if (FormCategory.INNER.equals(form.getType())) {
/* 151 */       Map<String, IBusinessData> dataMap = this.aH.a(permission, defId);
/*     */       
/* 153 */       IFormDef formDef = this.aF.getByFormKey(form.getFormValue());
/* 154 */       if (formDef == null) {
/* 155 */         throw new BusinessException(form.getFormValue(), BpmStatusCode.FLOW_FORM_LOSE);
/*     */       }
/*     */       
/* 158 */       form.setFormHtml(formDef.getHtml());
/* 159 */       flowData.setDataMap(dataMap);
/*     */       
/* 161 */       a(flowData, startNode.getNodeId());
/*     */     } else {
/* 163 */       String url = form.getFormValue().replace("{bizId}", "");
/* 164 */       form.setFormValue(url);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void a(BpmFlowData flowData, String instaneId, String nodeId, FormType formType, boolean isReadOnly)
/*     */   {
/* 179 */     BpmInstance instance = (BpmInstance)this.aD.get(instaneId);
/* 180 */     String defKey = instance.getDefKey();
/*     */     
/*     */ 
/* 183 */     IBusinessPermission businessPermision = this.aE.getInstanceFormPermission(flowData, nodeId, formType, isReadOnly);
/*     */     
/* 185 */     BpmForm form = flowData.getForm();
/* 186 */     if (FormCategory.INNER.equals(form.getType())) {
/* 187 */       Map<String, IBusinessData> dataModel = this.aH.a(businessPermision, instance);
/* 188 */       flowData.setDataMap(dataModel);
/*     */       
/* 190 */       IFormDef formDef = this.aF.getByFormKey(form.getFormValue());
/* 191 */       if (formDef == null) {
/* 192 */         throw new BusinessException(form.getFormValue(), BpmStatusCode.FLOW_FORM_LOSE);
/*     */       }
/*     */       
/*     */ 
/* 196 */       form.setFormHtml(formDef.getHtml());
/*     */       
/*     */ 
/* 199 */       a(flowData, nodeId);
/*     */     }
/* 201 */     a(form, instance);
/*     */   }
/*     */   
/*     */   private void a(BpmFlowData flowData, String nodeId)
/*     */   {
/* 206 */     Map<String, IBusinessData> bos = flowData.getDataMap();
/* 207 */     if (BeanUtils.isEmpty(bos)) { return;
/*     */     }
/* 209 */     DefaultBpmProcessDef def = (DefaultBpmProcessDef)this.a.getBpmProcessDef(flowData.getDefId());
/*     */     
/*     */ 
/* 212 */     Map<String, Object> param = new HashMap();
/* 213 */     param.putAll(bos);
/* 214 */     if ((flowData instanceof BpmFlowTaskData)) {
/* 215 */       param.put("task", ((BpmFlowTaskData)flowData).getTask());
/* 216 */     } else if ((flowData instanceof BpmFlowInstanceData)) {
/* 217 */       param.put("instance", ((BpmFlowInstanceData)flowData).getInstance());
/*     */     }
/*     */     
/*     */ 
/* 221 */     for (NodeInit init : def.e(nodeId)) {
/* 222 */       if (StringUtil.isNotEmpty(init.getBeforeShow())) {
/*     */         try {
/* 224 */           this.av.execute(init.getBeforeShow(), param);
/*     */         } catch (Exception e) {
/* 226 */           throw new SystemException("执行脚本初始化失败", BpmStatusCode.FLOW_DATA_EXECUTE_SHOWSCRIPT_ERROR, e);
/*     */         }
/* 228 */         this.LOG.debug("执行节点数据初始化脚本{}", init.getBeforeShow());
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 233 */     JSONObject json = new JSONObject();
/* 234 */     JSONObject initJson = new JSONObject();
/* 235 */     for (String key : bos.keySet()) {
/* 236 */       IBusinessData bd = (IBusinessData)bos.get(key);
/* 237 */       JSONObject boJson = this.businessDataService.assemblyFormDefData(bd);
/* 238 */       json.put(key, boJson);
/*     */       
/*     */ 
/* 241 */       bd.fullBusDataInitData(initJson);
/*     */     }
/*     */     
/* 244 */     flowData.setData(json);
/* 245 */     flowData.setInitData(initJson);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void a(BpmFlowData flowData, String nodeId, boolean isReadOnly)
/*     */   {
/* 258 */     BpmNodeDef nodeDef = this.a.getBpmNodeDef(flowData.getDefId(), nodeId);
/* 259 */     List<Button> buttons = nodeDef.getButtons();
/* 260 */     if (isReadOnly) {
/* 261 */       buttons = ButtonFactory.getInstanceButtons();
/*     */     }
/*     */     
/* 264 */     Map<String, Object> param = new HashMap();
/* 265 */     if (BeanUtils.isNotEmpty(flowData.getDataMap())) {
/* 266 */       param.putAll(flowData.getDataMap());
/*     */     }
/* 268 */     if ((flowData instanceof BpmFlowTaskData)) {
/* 269 */       param.put("task", ((BpmFlowTaskData)flowData).getTask());
/* 270 */     } else if ((flowData instanceof BpmFlowInstanceData)) {
/* 271 */       param.put("instance", ((BpmFlowInstanceData)flowData).getInstance());
/*     */     }
/*     */     
/* 274 */     List<Button> btns = new ArrayList();
/* 275 */     for (Button btn : buttons) {
/* 276 */       if (StringUtil.isNotEmpty(btn.getGroovyScript())) {
/*     */         try {
/* 278 */           boolean isSkip = this.av.executeBoolean(btn.getGroovyScript(), param);
/* 279 */           this.LOG.debug("执行节点按钮脚本{},执行跳过结果{}", btn.getGroovyScript(), Boolean.valueOf(isSkip));
/*     */           
/* 281 */           if (isSkip)
/*     */             continue;
/* 283 */         } catch (Exception e) { throw new SystemException("按钮脚本执行失败", BpmStatusCode.FLOW_DATA_GET_BUTTONS_ERROR, e);
/*     */         }
/*     */       } else {
/* 286 */         btns.add(btn);
/*     */       }
/*     */     }
/* 289 */     flowData.setButtonList(btns);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void a(BpmForm form, IBpmInstance instance)
/*     */   {
/* 300 */     if ((form == null) || (form.isFormEmpty()) || (FormCategory.INNER == form.getType())) { return;
/*     */     }
/*     */     
/* 303 */     String url = form.getFormValue().replace("{bizId}", instance.getBizKey());
/*     */     
/* 305 */     form.setFormValue(url);
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\data\DefaultBpmFlowDataAccessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */