/*    */ package com.dstz.bpm.plugin.usercalc.user.def;
/*    */ 
/*    */ import com.dstz.bpm.engine.plugin.plugindef.AbstractUserCalcPluginDef;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UserPluginDef
/*    */   extends AbstractUserCalcPluginDef
/*    */ {
/* 10 */   private String source = "";
/*    */   
/*    */ 
/*    */ 
/*    */ 
/* 15 */   private String account = "";
/*    */   
/*    */ 
/*    */ 
/*    */ 
/* 20 */   private String userName = "";
/*    */   
/*    */   public String getAccount()
/*    */   {
/* 24 */     return this.account;
/*    */   }
/*    */   
/*    */   public void setAccount(String account) {
/* 28 */     this.account = account;
/*    */   }
/*    */   
/*    */   public String getUserName() {
/* 32 */     return this.userName;
/*    */   }
/*    */   
/*    */   public void setUserName(String userName) {
/* 36 */     this.userName = userName;
/*    */   }
/*    */   
/*    */   public String getSource() {
/* 40 */     return this.source;
/*    */   }
/*    */   
/*    */   public void setSource(String source) {
/* 44 */     this.source = source;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\usercalc\user\def\UserPluginDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */