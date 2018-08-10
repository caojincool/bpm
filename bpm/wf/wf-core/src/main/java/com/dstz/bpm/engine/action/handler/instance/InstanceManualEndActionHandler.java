 package com.dstz.bpm.engine.action.handler.instance;
 
 import com.dstz.bpm.api.constant.ActionType;
 import com.dstz.bpm.api.constant.NodeType;
 import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
 import com.dstz.bpm.api.engine.action.handler.ActionHandler;
 import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
 import org.springframework.stereotype.Component;
 
 
 
 
 @Component
 public class InstanceManualEndActionHandler
   implements ActionHandler
 {
   public void execute(ActionCmd model) {}
   
   public ActionType getActionType()
   {
     return ActionType.MANUALEND;
   }
   
   public int getSn()
   {
     return 6;
   }
   
   public Boolean isSupport(BpmNodeDef nodeDef)
   {
     NodeType nodeType = nodeDef.getType();
     
     if ((nodeType == NodeType.USERTASK) || (nodeType == NodeType.SIGNTASK)) {
       return Boolean.valueOf(true);
     }
     
     return Boolean.valueOf(false);
   }
   
   public Boolean isDefault()
   {
     return Boolean.valueOf(false);
   }
   
 
 
 
   public String getConfigPage()
   {
     return "/bpm/task/taskOpinionDialog.html";
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


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\action\handler\instance\InstanceManualEndActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */