/*    */ package com.dstz.bpm.plugin.usercalc.user.runtime;
/*    */ 
/*    */ import com.dstz.bpm.api.model.task.IBpmTask;
/*    */ import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
/*    */ import com.dstz.bpm.core.model.BpmTaskOpinion;
/*    */ import com.dstz.bpm.engine.model.BpmIdentity;
/*    */ import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractUserCalcPlugin;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
/*    */ import com.dstz.bpm.plugin.usercalc.user.def.UserPluginDef;
/*    */ import com.dstz.org.api.model.IUser;
/*    */ import com.dstz.org.api.service.UserService;
/*    */ import com.dstz.sys.api.model.SysIdentity;
/*    */ import com.dstz.sys.util.ContextUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class UserPlugin
/*    */   extends AbstractUserCalcPlugin<UserPluginDef>
/*    */ {
/*    */   @Resource
/*    */   BpmTaskOpinionManager aa;
/*    */   @Resource
/*    */   UserService ak;
/*    */   
/*    */   public List<SysIdentity> a(BpmUserCalcPluginSession pluginSession, UserPluginDef def)
/*    */   {
/* 32 */     List<SysIdentity> list = new ArrayList();
/*    */     
/* 34 */     String source = def.getSource();
/* 35 */     SysIdentity bpmIdentity; if ("start".equals(source))
/*    */     {
/* 37 */       List<BpmTaskOpinion> opinions = this.aa.getByInstAndNode(pluginSession.getBpmTask().getInstId(), "start");
/* 38 */       BpmTaskOpinion firstNode = (BpmTaskOpinion)opinions.get(0);
/*    */       
/* 40 */       bpmIdentity = new BpmIdentity(firstNode.getApprover(), firstNode.getApproverName(), "user");
/* 41 */       list.add(bpmIdentity);
/*    */     }
/* 43 */     if ("currentUser".equals(source)) {
/* 44 */       SysIdentity bpmIdentity = new BpmIdentity(ContextUtil.getCurrentUser());
/* 45 */       list.add(bpmIdentity);
/* 46 */     } else if ("spec".equals(source)) {
/* 47 */       String userKeys = def.getAccount();
/* 48 */       String[] aryAccount = userKeys.split(",");
/* 49 */       for (String account : aryAccount) {
/* 50 */         IUser user = this.ak.getUserByAccount(account);
/* 51 */         SysIdentity bpmIdentity = new BpmIdentity(user);
/* 52 */         list.add(bpmIdentity);
/*    */       }
/*    */     }
/* 55 */     return list;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\usercalc\user\runtime\UserPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */