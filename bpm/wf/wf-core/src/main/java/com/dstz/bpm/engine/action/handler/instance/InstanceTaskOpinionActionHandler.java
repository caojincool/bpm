 package com.dstz.bpm.engine.action.handler.instance;
 
 import com.dstz.bpm.api.constant.ActionType;
 import com.dstz.bpm.api.constant.NodeType;
 import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
 import com.dstz.bpm.api.engine.action.handler.ActionHandler;
 import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
 import org.springframework.stereotype.Component;
 
 
 
 @Component
 public class InstanceTaskOpinionActionHandler
   implements ActionHandler
 {
   public void execute(ActionCmd model) {}
   
   public ActionType getActionType()
   {
     return ActionType.TASKOPINION;
   }
   
   public int getSn()
   {
     return 5;
   }
   
   public Boolean isSupport(BpmNodeDef nodeDef)
   {
     if (nodeDef.getType() == NodeType.START) { return Boolean.valueOf(false);
     }
     return Boolean.valueOf(true);
   }
   
   public Boolean isDefault()
   {
     return Boolean.valueOf(true);
   }
   
 
 
   public String getConfigPage()
   {
     return "/bpm/instance/taskOpinionHistoryDialog.html";
   }
   
   public String getDefaultGroovyScript()
   {
     return "";
   }
   
   public String getDefaultBeforeScript()
   {
     return "";
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\action\handler\instance\InstanceTaskOpinionActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */