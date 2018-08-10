 package com.dstz.bpm.engine.action.handler;
 
 import com.alibaba.fastjson.JSON;
 import com.alibaba.fastjson.JSONObject;
 import com.dstz.base.api.exception.BusinessException;
 import com.dstz.base.api.exception.SystemException;
 import com.dstz.base.core.util.BeanUtils;
 import com.dstz.base.core.util.StringUtil;
 import com.dstz.bpm.api.constant.ActionType;
 import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
 import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
 import com.dstz.bpm.api.engine.action.handler.ActionHandler;
 import com.dstz.bpm.api.engine.context.BpmContext;
 import com.dstz.bpm.api.exception.BpmStatusCode;
 import com.dstz.bpm.api.exception.WorkFlowException;
 import com.dstz.bpm.api.model.def.BpmDataModel;
 import com.dstz.bpm.api.model.def.BpmDefProperties;
 import com.dstz.bpm.api.model.def.NodeInit;
 import com.dstz.bpm.api.model.form.BpmForm;
 import com.dstz.bpm.api.model.inst.IBpmInstance;
 import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
 import com.dstz.bpm.api.model.task.IBpmTask;
 import com.dstz.bpm.api.service.BpmProcessDefService;
 import com.dstz.bpm.core.manager.BpmInstanceManager;
 import com.dstz.bpm.core.manager.TaskIdentityLinkManager;
 import com.dstz.bpm.core.model.BpmInstance;
 import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
 import com.dstz.bpm.engine.constant.TaskSkipType;
 import com.dstz.bpm.engine.data.handle.BpmBusDataHandle;
 import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
 import com.dstz.bpm.engine.util.HandlerUtil;
 import com.dstz.bus.api.model.IBusinessData;
 import com.dstz.bus.api.service.IBusinessDataService;
 import com.dstz.org.api.model.IUser;
 import com.dstz.sys.api.groovy.IGroovyScriptEngine;
 import com.dstz.sys.util.ContextUtil;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.annotation.Resource;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.transaction.annotation.Transactional;
 
 
 
 
 
 
 
 
 public abstract class AbsActionHandler<T extends BaseActionCmd>
   implements ActionHandler<T>
 {
   protected Logger LOG = LoggerFactory.getLogger(getClass());
   
   @Resource
   protected BpmProcessDefService a;
   @Resource
   protected BpmInstanceManager f;
   @Resource
   protected BpmBusDataHandle at;
   @Resource
   protected TaskIdentityLinkManager i;
   @Resource
   protected IBusinessDataService au;
   @Resource
   protected IGroovyScriptEngine av;
   
   @Transactional
   public void a(T model)
   {
     c(model);
     
 
     k(model);
     
 
     BpmContext.setActionModel(model);
     
 
     d(model);
     
 
     b(model);
     
     f(model);
     
 
     BpmContext.removeActionModel();
     
 
     e(model);
   }
   
 
 
 
 
   protected abstract void b(T paramT);
   
 
 
 
 
   protected abstract void c(T paramT);
   
 
 
 
 
   protected void d(T data)
   {
     j(data);
     
 
     this.LOG.debug("流程启动处理业务数据...");
     this.at.n(data);
     
     h(data);
   }
   
   protected void e(T model) {
     i(model);
     
     if (getActionType() == ActionType.DRAFT) {
       return;
     }
     
     if (model.isSource()) {
       BpmContext.cleanTread();
     }
   }
   
   private void f(T model)
   {
     if ((getActionType() != ActionType.AGREE) && (getActionType() != ActionType.OPPOSE) && (getActionType() != ActionType.START)) {
       return;
     }
     
     DefualtTaskActionCmd taskModel = (DefualtTaskActionCmd)BpmContext.getActionModel();
     
     if (taskModel.getActionName().equals(ActionType.CREATE)) { return;
     }
     if (taskModel.b() == TaskSkipType.NO_SKIP) { return;
     }
     DefualtTaskActionCmd complateModel = new DefualtTaskActionCmd();
     complateModel.setBpmInstance(taskModel.getBpmInstance());
     complateModel.setBpmDefinition(taskModel.getBpmDefinition());
     complateModel.setBizDataMap(taskModel.getBizDataMap());
     complateModel.setBpmIdentities(taskModel.getBpmIdentities());
     complateModel.setBusinessKey(taskModel.getBusinessKey());
     complateModel.setActionName(ActionType.AGREE.getKey());
     complateModel.setBpmTask(taskModel.getBpmTask());
     complateModel.setOpinion(taskModel.b().getValue());
     complateModel.a();
   }
   
   public void g(T model) {
     BpmContext.setActionModel(model);
     h(model);
     b(model);
     f(model);
     BpmContext.removeActionModel();
     e(model);
   }
   
 
 
 
 
 
   protected abstract void h(T paramT);
   
 
 
 
 
 
   protected abstract void i(T paramT);
   
 
 
 
 
   protected void j(T actionModel)
   {
     BpmInstance instance = (BpmInstance)actionModel.getBpmInstance();
     
     if ((StringUtil.isEmpty(actionModel.getBusinessKey())) && (StringUtil.isNotEmpty(instance.getBizKey()))) {
       actionModel.setBusinessKey(instance.getBizKey());
     }
     
     String handler = m(actionModel);
     if (StringUtil.isEmpty(handler)) return;
     try
     {
       HandlerUtil.a(actionModel, handler);
       this.LOG.debug("执行URL表单处理器：{}", handler);
     } catch (Exception ex) {
       throw new WorkFlowException(BpmStatusCode.HANDLER_ERROR, ex);
     }
     
 
     if ((StringUtil.isNotEmpty(actionModel.getBusinessKey())) && (StringUtil.isEmpty(instance.getBizKey()))) {
       instance.setBizKey(actionModel.getBusinessKey());
     }
   }
   
   protected void k(BaseActionCmd actionModel)
   {
     IBpmInstance instance = actionModel.getBpmInstance();
     
     if (instance.getIsForbidden().shortValue() == 1) {
       throw new WorkFlowException("流程实例已经被禁止，请联系管理员", BpmStatusCode.DEF_FORBIDDEN);
     }
     
     DefaultBpmProcessDef def = (DefaultBpmProcessDef)this.a.getBpmProcessDef(instance.getDefId());
     if ("forbidden".equals(def.getExtProperties().getStatus())) {
       throw new WorkFlowException("流程定义已经被禁用，请联系管理员", BpmStatusCode.DEF_FORBIDDEN);
     }
     
     IUser user = ContextUtil.getCurrentUser();
     if (ContextUtil.isAdmin(user)) { return;
     }
     String taskId = null;
     String instId = actionModel.getInstanceId();
     if ((actionModel instanceof DefualtTaskActionCmd)) {
       IBpmTask task = ((DefualtTaskActionCmd)actionModel).getBpmTask();
       if (user.getUserId().equals(task.getAssigneeId())) {
         return;
       }
       
       taskId = task.getId();
       instId = null;
     } else { if (StringUtil.isNotEmpty(def.getProcessDefinitionId()))
       {
         return;
       }
       return;
     }
     
     Boolean hasPermission = this.i.checkUserOperatorPermission(user.getUserId(), instId, taskId);
     if (!hasPermission.booleanValue()) {
       throw new BusinessException("没有该任务的操作权限", BpmStatusCode.NO_PERMISSION);
     }
   }
   
 
 
 
   protected void l(T actionModel)
   {
     IBpmInstance instance = actionModel.getBpmInstance();
     if (StringUtil.isEmpty(actionModel.getBusData())) {
       return;
     }
     
     JSONObject data = JSON.parseObject(actionModel.getBusData());
     
     DefaultBpmProcessDef bpmProcessDef = (DefaultBpmProcessDef)this.a.getBpmProcessDef(instance.getDefId());
     
 
     for (BpmDataModel dataModel : bpmProcessDef.getDataModelList()) {
       String modelCode = dataModel.getCode();
       
       if (data.containsKey(modelCode)) {
         IBusinessData businessData = this.au.parseBusinessData(data.getJSONObject(modelCode), modelCode);
         actionModel.getBizDataMap().put(modelCode, businessData);
       }
     }
   }
   
 
 
 
 
 
 
   protected void a(BaseActionCmd cmd, BpmNodeDef nodeDef)
   {
     String nodeId = nodeDef.getNodeId();
     
     DefaultBpmProcessDef def = (DefaultBpmProcessDef)this.a.getBpmProcessDef(cmd.getBpmInstance().getDefId());
     List<NodeInit> nodeInitList = def.e(nodeId);
     
     Map<String, IBusinessData> bos = cmd.getBizDataMap();
     if ((BeanUtils.isEmpty(bos)) || (BeanUtils.isEmpty(nodeInitList))) { return;
     }
     
     Map<String, Object> param = new HashMap();
     param.putAll(bos);
     param.put("bpmInstance", cmd.getBpmInstance());
     
 
     if ((cmd instanceof DefualtTaskActionCmd)) {
       param.put("bpmTask", ((DefualtTaskActionCmd)cmd).getBpmTask());
     }
     
 
     for (NodeInit init : nodeInitList) {
       if (StringUtil.isNotEmpty(init.getWhenSave())) {
         try {
           this.av.execute(init.getWhenSave(), param);
         } catch (Exception e) {
           throw new SystemException(e.getMessage(), BpmStatusCode.FLOW_DATA_EXECUTE_SHOWSCRIPT_ERROR, e);
         }
         this.LOG.debug("执行节点数据初始化脚本{}", init.getBeforeShow());
       }
     }
   }
   
   private String m(T actionModel) {
     String defId = actionModel.getDefId();
     BpmForm form;
     BpmForm form;
     if ((actionModel instanceof TaskActionCmd)) {
       TaskActionCmd cmd = (TaskActionCmd)actionModel;
       String nodeId = cmd.getNodeId();
       BpmNodeDef nodeDef = this.a.getBpmNodeDef(defId, nodeId);
       form = nodeDef.getForm();
     } else {
       BpmNodeDef nodeDef = this.a.getStartEvent(defId);
       form = nodeDef.getForm();
     }
     if ((form == null) || (form.isFormEmpty())) {
       DefaultBpmProcessDef def = (DefaultBpmProcessDef)this.a.getBpmProcessDef(defId);
       form = def.getGlobalForm();
     }
     if (form != null) {
       return form.getFormHandler();
     }
     
     return null;
   }
   
 
   public Boolean isDefault()
   {
     return Boolean.valueOf(true);
   }
   
   public String getDefaultBeforeScript()
   {
     return "";
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\action\handler\AbsActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */