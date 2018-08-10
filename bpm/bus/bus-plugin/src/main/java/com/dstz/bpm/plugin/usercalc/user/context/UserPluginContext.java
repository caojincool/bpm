/*    */ package com.dstz.bpm.plugin.usercalc.user.context;
/*    */ 
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
/*    */ import com.dstz.bpm.engine.plugin.context.AbstractUserCalcPluginContext;
/*    */ import com.dstz.bpm.plugin.usercalc.user.def.UserPluginDef;
/*    */ import com.dstz.bpm.plugin.usercalc.user.runtime.UserPlugin;
/*    */ import org.springframework.context.annotation.Scope;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ @Scope("prototype")
/*    */ public class UserPluginContext
/*    */   extends AbstractUserCalcPluginContext<UserPluginDef>
/*    */ {
/*    */   private static final long serialVersionUID = 8757352972830358986L;
/*    */   
/*    */   public String getDescription()
/*    */   {
/* 22 */     String str = "";
/* 23 */     UserPluginDef def = (UserPluginDef)getBpmPluginDef();
/* 24 */     if (def == null) return "";
/* 25 */     String source = def.getSource();
/* 26 */     if ("currentUser".equals(source)) {
/* 27 */       str = "当前登录人";
/*    */     }
/* 29 */     if ("start".equals(source)) {
/* 30 */       str = "发起人";
/* 31 */     } else if ("prev".equals(source)) {
/* 32 */       str = "上一步执行人";
/* 33 */     } else if ("spec".equals(source)) {
/* 34 */       str = def.getUserName();
/*    */     }
/*    */     
/* 37 */     return str;
/*    */   }
/*    */   
/*    */   public String getTitle()
/*    */   {
/* 42 */     return "用户";
/*    */   }
/*    */   
/*    */   public Class<? extends RunTimePlugin> getPluginClass()
/*    */   {
/* 47 */     return UserPlugin.class;
/*    */   }
/*    */   
/*    */ 
/*    */   protected UserPluginDef d(JSONObject pluginJson)
/*    */   {
/* 53 */     String source = pluginJson.getString("source");
/*    */     
/* 55 */     UserPluginDef def = new UserPluginDef();
/* 56 */     def.setSource(source);
/*    */     
/* 58 */     if ("spec".equals(source)) {
/* 59 */       String accounts = pluginJson.getString("account");
/* 60 */       String userNames = pluginJson.getString("userName");
/* 61 */       def.setAccount(accounts);
/* 62 */       def.setUserName(userNames);
/*    */     }
/*    */     
/* 65 */     return def;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\usercalc\user\context\UserPluginContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */