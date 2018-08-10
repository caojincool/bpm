/*     */ package com.dstz.bpm.engine.action.handler;
/*     */ 
/*     */ import com.alibaba.fastjson.JSON;
/*     */ import com.alibaba.fastjson.JSONObject;
/*     */ import com.dstz.base.api.exception.BusinessException;
/*     */ import com.dstz.base.api.exception.SystemException;
/*     */ import com.dstz.base.core.util.BeanUtils;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.bpm.api.constant.ActionType;
/*     */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*     */ import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
/*     */ import com.dstz.bpm.api.engine.action.handler.ActionHandler;
/*     */ import com.dstz.bpm.api.engine.context.BpmContext;
/*     */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*     */ import com.dstz.bpm.api.exception.WorkFlowException;
/*     */ import com.dstz.bpm.api.model.def.BpmDataModel;
/*     */ import com.dstz.bpm.api.model.def.BpmDefProperties;
/*     */ import com.dstz.bpm.api.model.def.NodeInit;
/*     */ import com.dstz.bpm.api.model.form.BpmForm;
/*     */ import com.dstz.bpm.api.model.inst.IBpmInstance;
/*     */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*     */ import com.dstz.bpm.api.model.task.IBpmTask;
/*     */ import com.dstz.bpm.api.service.BpmProcessDefService;
/*     */ import com.dstz.bpm.core.manager.BpmInstanceManager;
/*     */ import com.dstz.bpm.core.manager.TaskIdentityLinkManager;
/*     */ import com.dstz.bpm.core.model.BpmInstance;
/*     */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*     */ import com.dstz.bpm.engine.constant.TaskSkipType;
/*     */ import com.dstz.bpm.engine.data.handle.BpmBusDataHandle;
/*     */ import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
/*     */ import com.dstz.bpm.engine.util.HandlerUtil;
/*     */ import com.dstz.bus.api.model.IBusinessData;
/*     */ import com.dstz.bus.api.service.IBusinessDataService;
/*     */ import com.dstz.org.api.model.IUser;
/*     */ import com.dstz.sys.api.groovy.IGroovyScriptEngine;
/*     */ import com.dstz.sys.util.ContextUtil;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Resource;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbsActionHandler<T extends BaseActionCmd>
/*     */   implements ActionHandler<T>
/*     */ {
/*  55 */   protected Logger LOG = LoggerFactory.getLogger(getClass());
/*     */   
/*     */   @Resource
/*     */   protected BpmProcessDefService a;
/*     */   @Resource
/*     */   protected BpmInstanceManager f;
/*     */   @Resource
/*     */   protected BpmBusDataHandle at;
/*     */   @Resource
/*     */   protected TaskIdentityLinkManager i;
/*     */   @Resource
/*     */   protected IBusinessDataService au;
/*     */   @Resource
/*     */   protected IGroovyScriptEngine av;
/*     */   
/*     */   @Transactional
/*     */   public void a(T model)
/*     */   {
/*  73 */     c(model);
/*     */     
/*     */ 
/*  76 */     k(model);
/*     */     
/*     */ 
/*  79 */     BpmContext.setActionModel(model);
/*     */     
/*     */ 
/*  82 */     d(model);
/*     */     
/*     */ 
/*  85 */     b(model);
/*     */     
/*  87 */     f(model);
/*     */     
/*     */ 
/*  90 */     BpmContext.removeActionModel();
/*     */     
/*     */ 
/*  93 */     e(model);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract void b(T paramT);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract void c(T paramT);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void d(T data)
/*     */   {
/* 114 */     j(data);
/*     */     
/*     */ 
/* 117 */     this.LOG.debug("流程启动处理业务数据...");
/* 118 */     this.at.n(data);
/*     */     
/* 120 */     h(data);
/*     */   }
/*     */   
/*     */   protected void e(T model) {
/* 124 */     i(model);
/*     */     
/* 126 */     if (getActionType() == ActionType.DRAFT) {
/* 127 */       return;
/*     */     }
/*     */     
/* 130 */     if (model.isSource()) {
/* 131 */       BpmContext.cleanTread();
/*     */     }
/*     */   }
/*     */   
/*     */   private void f(T model)
/*     */   {
/* 137 */     if ((getActionType() != ActionType.AGREE) && (getActionType() != ActionType.OPPOSE) && (getActionType() != ActionType.START)) {
/* 138 */       return;
/*     */     }
/*     */     
/* 141 */     DefualtTaskActionCmd taskModel = (DefualtTaskActionCmd)BpmContext.getActionModel();
/*     */     
/* 143 */     if (taskModel.getActionName().equals(ActionType.CREATE)) { return;
/*     */     }
/* 145 */     if (taskModel.b() == TaskSkipType.NO_SKIP) { return;
/*     */     }
/* 147 */     DefualtTaskActionCmd complateModel = new DefualtTaskActionCmd();
/* 148 */     complateModel.setBpmInstance(taskModel.getBpmInstance());
/* 149 */     complateModel.setBpmDefinition(taskModel.getBpmDefinition());
/* 150 */     complateModel.setBizDataMap(taskModel.getBizDataMap());
/* 151 */     complateModel.setBpmIdentities(taskModel.getBpmIdentities());
/* 152 */     complateModel.setBusinessKey(taskModel.getBusinessKey());
/* 153 */     complateModel.setActionName(ActionType.AGREE.getKey());
/* 154 */     complateModel.setBpmTask(taskModel.getBpmTask());
/* 155 */     complateModel.setOpinion(taskModel.b().getValue());
/* 156 */     complateModel.a();
/*     */   }
/*     */   
/*     */   public void g(T model) {
/* 160 */     BpmContext.setActionModel(model);
/* 161 */     h(model);
/* 162 */     b(model);
/* 163 */     f(model);
/* 164 */     BpmContext.removeActionModel();
/* 165 */     e(model);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract void h(T paramT);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract void i(T paramT);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void j(T actionModel)
/*     */   {
/* 188 */     BpmInstance instance = (BpmInstance)actionModel.getBpmInstance();
/*     */     
/* 190 */     if ((StringUtil.isEmpty(actionModel.getBusinessKey())) && (StringUtil.isNotEmpty(instance.getBizKey()))) {
/* 191 */       actionModel.setBusinessKey(instance.getBizKey());
/*     */     }
/*     */     
/* 194 */     String handler = m(actionModel);
/* 195 */     if (StringUtil.isEmpty(handler)) return;
/*     */     try
/*     */     {
/* 198 */       HandlerUtil.a(actionModel, handler);
/* 199 */       this.LOG.debug("执行URL表单处理器：{}", handler);
/*     */     } catch (Exception ex) {
/* 201 */       throw new WorkFlowException(BpmStatusCode.HANDLER_ERROR, ex);
/*     */     }
/*     */     
/*     */ 
/* 205 */     if ((StringUtil.isNotEmpty(actionModel.getBusinessKey())) && (StringUtil.isEmpty(instance.getBizKey()))) {
/* 206 */       instance.setBizKey(actionModel.getBusinessKey());
/*     */     }
/*     */   }
/*     */   
/*     */   protected void k(BaseActionCmd actionModel)
/*     */   {
/* 212 */     IBpmInstance instance = actionModel.getBpmInstance();
/*     */     
/* 214 */     if (instance.getIsForbidden().shortValue() == 1) {
/* 215 */       throw new WorkFlowException("流程实例已经被禁止，请联系管理员", BpmStatusCode.DEF_FORBIDDEN);
/*     */     }
/*     */     
/* 218 */     DefaultBpmProcessDef def = (DefaultBpmProcessDef)this.a.getBpmProcessDef(instance.getDefId());
/* 219 */     if ("forbidden".equals(def.getExtProperties().getStatus())) {
/* 220 */       throw new WorkFlowException("流程定义已经被禁用，请联系管理员", BpmStatusCode.DEF_FORBIDDEN);
/*     */     }
/*     */     
/* 223 */     IUser user = ContextUtil.getCurrentUser();
/* 224 */     if (ContextUtil.isAdmin(user)) { return;
/*     */     }
/* 226 */     String taskId = null;
/* 227 */     String instId = actionModel.getInstanceId();
/* 228 */     if ((actionModel instanceof DefualtTaskActionCmd)) {
/* 229 */       IBpmTask task = ((DefualtTaskActionCmd)actionModel).getBpmTask();
/* 230 */       if (user.getUserId().equals(task.getAssigneeId())) {
/* 231 */         return;
/*     */       }
/*     */       
/* 234 */       taskId = task.getId();
/* 235 */       instId = null;
/* 236 */     } else { if (StringUtil.isNotEmpty(def.getProcessDefinitionId()))
/*     */       {
/* 238 */         return;
/*     */       }
/* 240 */       return;
/*     */     }
/*     */     
/* 243 */     Boolean hasPermission = this.i.checkUserOperatorPermission(user.getUserId(), instId, taskId);
/* 244 */     if (!hasPermission.booleanValue()) {
/* 245 */       throw new BusinessException("没有该任务的操作权限", BpmStatusCode.NO_PERMISSION);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void l(T actionModel)
/*     */   {
/* 254 */     IBpmInstance instance = actionModel.getBpmInstance();
/* 255 */     if (StringUtil.isEmpty(actionModel.getBusData())) {
/* 256 */       return;
/*     */     }
/*     */     
/* 259 */     JSONObject data = JSON.parseObject(actionModel.getBusData());
/*     */     
/* 261 */     DefaultBpmProcessDef bpmProcessDef = (DefaultBpmProcessDef)this.a.getBpmProcessDef(instance.getDefId());
/*     */     
/*     */ 
/* 264 */     for (BpmDataModel dataModel : bpmProcessDef.getDataModelList()) {
/* 265 */       String modelCode = dataModel.getCode();
/*     */       
/* 267 */       if (data.containsKey(modelCode)) {
/* 268 */         IBusinessData businessData = this.au.parseBusinessData(data.getJSONObject(modelCode), modelCode);
/* 269 */         actionModel.getBizDataMap().put(modelCode, businessData);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void a(BaseActionCmd cmd, BpmNodeDef nodeDef)
/*     */   {
/* 282 */     String nodeId = nodeDef.getNodeId();
/*     */     
/* 284 */     DefaultBpmProcessDef def = (DefaultBpmProcessDef)this.a.getBpmProcessDef(cmd.getBpmInstance().getDefId());
/* 285 */     List<NodeInit> nodeInitList = def.e(nodeId);
/*     */     
/* 287 */     Map<String, IBusinessData> bos = cmd.getBizDataMap();
/* 288 */     if ((BeanUtils.isEmpty(bos)) || (BeanUtils.isEmpty(nodeInitList))) { return;
/*     */     }
/*     */     
/* 291 */     Map<String, Object> param = new HashMap();
/* 292 */     param.putAll(bos);
/* 293 */     param.put("bpmInstance", cmd.getBpmInstance());
/*     */     
/*     */ 
/* 296 */     if ((cmd instanceof DefualtTaskActionCmd)) {
/* 297 */       param.put("bpmTask", ((DefualtTaskActionCmd)cmd).getBpmTask());
/*     */     }
/*     */     
/*     */ 
/* 301 */     for (NodeInit init : nodeInitList) {
/* 302 */       if (StringUtil.isNotEmpty(init.getWhenSave())) {
/*     */         try {
/* 304 */           this.av.execute(init.getWhenSave(), param);
/*     */         } catch (Exception e) {
/* 306 */           throw new SystemException(e.getMessage(), BpmStatusCode.FLOW_DATA_EXECUTE_SHOWSCRIPT_ERROR, e);
/*     */         }
/* 308 */         this.LOG.debug("执行节点数据初始化脚本{}", init.getBeforeShow());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private String m(T actionModel) {
/* 314 */     String defId = actionModel.getDefId();
/*     */     BpmForm form;
/*     */     BpmForm form;
/* 317 */     if ((actionModel instanceof TaskActionCmd)) {
/* 318 */       TaskActionCmd cmd = (TaskActionCmd)actionModel;
/* 319 */       String nodeId = cmd.getNodeId();
/* 320 */       BpmNodeDef nodeDef = this.a.getBpmNodeDef(defId, nodeId);
/* 321 */       form = nodeDef.getForm();
/*     */     } else {
/* 323 */       BpmNodeDef nodeDef = this.a.getStartEvent(defId);
/* 324 */       form = nodeDef.getForm();
/*     */     }
/* 326 */     if ((form == null) || (form.isFormEmpty())) {
/* 327 */       DefaultBpmProcessDef def = (DefaultBpmProcessDef)this.a.getBpmProcessDef(defId);
/* 328 */       form = def.getGlobalForm();
/*     */     }
/* 330 */     if (form != null) {
/* 331 */       return form.getFormHandler();
/*     */     }
/*     */     
/* 334 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public Boolean isDefault()
/*     */   {
/* 340 */     return Boolean.valueOf(true);
/*     */   }
/*     */   
/*     */   public String getDefaultBeforeScript()
/*     */   {
/* 345 */     return "";
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\action\handler\AbsActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */