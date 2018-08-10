/*    */ package com.dstz.bpm.engine.action.handler.instance;
/*    */ 
/*    */ import com.dstz.bpm.api.constant.ActionType;
/*    */ import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
/*    */ import com.dstz.bpm.api.engine.action.handler.ActionHandler;
/*    */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class InstanceImageActionHandler
/*    */   implements ActionHandler
/*    */ {
/*    */   public void execute(ActionCmd model) {}
/*    */   
/*    */   public ActionType getActionType()
/*    */   {
/* 20 */     return ActionType.FLOWIMAGE;
/*    */   }
/*    */   
/*    */   public int getSn()
/*    */   {
/* 25 */     return 6;
/*    */   }
/*    */   
/*    */   public Boolean isSupport(BpmNodeDef nodeDef)
/*    */   {
/* 30 */     return Boolean.valueOf(true);
/*    */   }
/*    */   
/*    */   public Boolean isDefault()
/*    */   {
/* 35 */     return Boolean.valueOf(true);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String getConfigPage()
/*    */   {
/* 42 */     return "/bpm/instance/instanceImageDialog.html";
/*    */   }
/*    */   
/*    */   public String getDefaultGroovyScript()
/*    */   {
/* 47 */     return "";
/*    */   }
/*    */   
/*    */   public String getDefaultBeforeScript()
/*    */   {
/* 52 */     return "";
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\action\handler\instance\InstanceImageActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */