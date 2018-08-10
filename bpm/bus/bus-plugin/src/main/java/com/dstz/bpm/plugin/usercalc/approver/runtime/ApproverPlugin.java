/*    */ package com.dstz.bpm.plugin.usercalc.approver.runtime;
/*    */ 
/*    */ import com.dstz.base.core.util.StringUtil;
/*    */ import com.dstz.bpm.api.model.task.IBpmTask;
/*    */ import com.dstz.bpm.api.model.task.IBpmTaskOpinion;
/*    */ import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
/*    */ import com.dstz.bpm.core.model.BpmTaskOpinion;
/*    */ import com.dstz.bpm.engine.model.BpmIdentity;
/*    */ import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractUserCalcPlugin;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
/*    */ import com.dstz.bpm.plugin.usercalc.approver.def.ApproverPluginDef;
/*    */ import com.dstz.sys.api.model.SysIdentity;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class ApproverPlugin
/*    */   extends AbstractUserCalcPlugin<ApproverPluginDef>
/*    */ {
/*    */   @Resource
/*    */   BpmTaskOpinionManager aa;
/*    */   
/*    */   public List<SysIdentity> a(BpmUserCalcPluginSession pluginSession, ApproverPluginDef pluginDef)
/*    */   {
/* 28 */     List<SysIdentity> bpmIdentities = new ArrayList();
/*    */     
/* 30 */     List<BpmTaskOpinion> taskOpinionList = this.aa.getByInstId(pluginSession.getBpmTask().getInstId());
/*    */     
/* 32 */     for (IBpmTaskOpinion taskOpinion : taskOpinionList) {
/* 33 */       if (!StringUtil.isEmpty(taskOpinion.getApprover()))
/*    */       {
/* 35 */         SysIdentity bpmIdentity = new BpmIdentity(taskOpinion.getApprover(), taskOpinion.getApproverName(), "user");
/* 36 */         bpmIdentities.add(bpmIdentity);
/*    */       }
/*    */     }
/* 39 */     return bpmIdentities;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\usercalc\approver\runtime\ApproverPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */