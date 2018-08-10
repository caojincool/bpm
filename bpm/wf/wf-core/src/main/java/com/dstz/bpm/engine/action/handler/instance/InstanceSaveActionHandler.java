 package com.dstz.bpm.engine.action.handler.instance;
 
 import com.dstz.base.api.exception.BusinessException;
 import com.dstz.base.core.util.StringUtil;
 import com.dstz.bpm.api.constant.ActionType;
 import com.dstz.bpm.api.constant.InstanceStatus;
 import com.dstz.bpm.api.constant.NodeType;
 import com.dstz.bpm.api.model.def.IBpmDefinition;
 import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
 import com.dstz.bpm.api.service.BpmProcessDefService;
 import com.dstz.bpm.core.manager.BpmInstanceManager;
 import com.dstz.bpm.core.model.BpmInstance;
 import com.dstz.bpm.engine.action.cmd.DefaultInstanceActionCmd;
 import com.dstz.bpm.engine.action.handler.AbsActionHandler;
 import org.springframework.stereotype.Component;
 
 
 
 
 
 
 
 
 
 
 
 
 
 @Component
 public class InstanceSaveActionHandler
   extends AbsActionHandler<DefaultInstanceActionCmd>
 {
   protected void a(DefaultInstanceActionCmd model)
   {
     BpmInstance instance = (BpmInstance)model.getBpmInstance();
     instance.setStatus(InstanceStatus.STATUS_DRAFT.getKey());
   }
   
 
 
 
   protected void b(DefaultInstanceActionCmd actionModel) {}
   
 
 
   protected void c(DefaultInstanceActionCmd data)
   {
     data.setBpmDefinition(this.a.getDefinitionById(data.getDefId()));
     
     f(data);
     
     l(data);
     
     a(data, this.a.getStartEvent(data.getDefId()));
   }
   
 
 
   protected void d(DefaultInstanceActionCmd actionModel)
   {
     e(actionModel);
   }
   
   protected void e(DefaultInstanceActionCmd actionModel) {
     BpmInstance instance = (BpmInstance)actionModel.getBpmInstance();
     
     if (instance.hasCreate().booleanValue()) {
       this.f.update(instance);
     } else {
       this.f.create(instance);
     }
   }
   
   protected void f(DefaultInstanceActionCmd intanceCmdData) {
     String instId = intanceCmdData.getInstanceId();
     
     BpmInstance instance = null;
     if (StringUtil.isNotEmpty(instId)) {
       instance = (BpmInstance)this.f.get(instId);
       if (StringUtil.isNotEmpty(instance.getActInstId())) {
         throw new BusinessException("草稿已经启动，请勿多次启动该草稿！");
       }
     }
     
     if (instance == null) {
       IBpmDefinition bpmDefinition = intanceCmdData.getBpmDefinition();
       instance = this.f.genInstanceByDefinition(bpmDefinition);
     }
     
     intanceCmdData.setBpmInstance(instance);
   }
   
 
 
 
   public ActionType getActionType()
   {
     return ActionType.DRAFT;
   }
   
 
   public int getSn()
   {
     return 2;
   }
   
 
   public Boolean isSupport(BpmNodeDef nodeDef)
   {
     if (nodeDef.getType() == NodeType.START) {
       return Boolean.valueOf(true);
     }
     return Boolean.valueOf(false);
   }
   
 
   public String getConfigPage()
   {
     return null;
   }
   
 
 
   public String getDefaultGroovyScript()
   {
     return "";
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\action\handler\instance\InstanceSaveActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */