 package com.dstz.bpm.engine.action.handler.instance;
 
 import com.dstz.bpm.api.constant.ActionType;
 import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
 import com.dstz.bpm.api.engine.action.handler.ActionHandler;
 import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
 import org.springframework.stereotype.Component;
 
 
 
 @Component
 public class InstancePrintActionHandler
   implements ActionHandler
 {
   public void execute(ActionCmd model) {}
   
   public ActionType getActionType()
   {
     return ActionType.PRINT;
   }
   
   public int getSn()
   {
     return 7;
   }
   
   public Boolean isSupport(BpmNodeDef nodeDef)
   {
     return Boolean.valueOf(true);
   }
   
   public Boolean isDefault()
   {
     return Boolean.valueOf(true);
   }
   
   public String getConfigPage()
   {
     return "";
   }
   
   public String getDefaultGroovyScript()
   {
     return "";
   }
   
   public String getDefaultBeforeScript()
   {
     return "print(); return false;";
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\action\handler\instance\InstancePrintActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */