 package com.dstz.bpm.engine.action.handler.instance;
 
 import com.dstz.base.core.util.StringUtil;
 import com.dstz.bpm.act.service.ActInstanceService;
 import com.dstz.bpm.api.constant.ActionType;
 import com.dstz.bpm.api.constant.NodeType;
 import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
 import com.dstz.bpm.api.service.BpmProcessDefService;
 import com.dstz.bpm.core.manager.BpmInstanceManager;
 import com.dstz.bpm.core.model.BpmInstance;
 import com.dstz.bpm.engine.action.cmd.DefaultInstanceActionCmd;
 import javax.annotation.Resource;
 import org.springframework.stereotype.Component;
 
 
 
 @Component
 public class InstanceStartActionHandler
   extends InstanceSaveActionHandler
 {
   @Resource
   BpmInstanceManager f;
   @Resource
   ActInstanceService aw;
   @Resource
   BpmProcessDefService a;
   
   public void a(DefaultInstanceActionCmd startActionModel)
   {
     String destination = startActionModel.getDestination();
     
     BpmInstance instance = (BpmInstance)startActionModel.getBpmInstance();
     
     String actInstId = null;
     if (StringUtil.isEmpty(destination))
     {
       actInstId = this.aw.startProcessInstance(instance.getActDefId(), instance.getBizKey(), startActionModel.getActionVariables());
     }
     else {
       actInstId = this.aw.startProcessInstance(instance.getActDefId(), instance.getBizKey(), startActionModel.getActionVariables(), new String[] { destination });
     }
     
     instance.setActInstId(actInstId);
     e(startActionModel);
   }
   
 
 
   protected void d(DefaultInstanceActionCmd actionModel) {}
   
 
 
   public int getSn()
   {
     return 1;
   }
   
   public ActionType getActionType()
   {
     return ActionType.START;
   }
   
   public Boolean isSupport(BpmNodeDef nodeDef)
   {
     return Boolean.valueOf(nodeDef.getType() == NodeType.START);
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\action\handler\instance\InstanceStartActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */