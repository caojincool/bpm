/*    */ package com.dstz.bpm.plugin.usercalc.samenode.runtime;
/*    */ 
/*    */ import com.dstz.base.core.util.BeanUtils;
/*    */ import com.dstz.bpm.api.model.task.IBpmTask;
/*    */ import com.dstz.bpm.api.model.task.IBpmTaskOpinion;
/*    */ import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
/*    */ import com.dstz.bpm.core.model.BpmTaskOpinion;
/*    */ import com.dstz.bpm.engine.model.BpmIdentity;
/*    */ import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractUserCalcPlugin;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
/*    */ import com.dstz.bpm.plugin.usercalc.samenode.def.SameNodePluginDef;
/*    */ import com.dstz.sys.api.model.SysIdentity;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ public class SameNodePlugin extends AbstractUserCalcPlugin<SameNodePluginDef>
/*    */ {
/*    */   @Resource
/*    */   BpmTaskOpinionManager aa;
/*    */   
/*    */   public List<SysIdentity> a(BpmUserCalcPluginSession pluginSession, SameNodePluginDef sameNodeDef)
/*    */   {
/* 26 */     List<SysIdentity> bpmIdentities = new ArrayList();
/*    */     
/* 28 */     List<BpmTaskOpinion> taskOpinionList = this.aa.getByInstAndNode(pluginSession.getBpmTask().getInstId(), sameNodeDef.getNodeId());
/* 29 */     if (BeanUtils.isEmpty(taskOpinionList)) { return bpmIdentities;
/*    */     }
/* 31 */     IBpmTaskOpinion taskOpinion = (IBpmTaskOpinion)taskOpinionList.get(taskOpinionList.size() - 1);
/*    */     
/* 33 */     SysIdentity bpmIdentity = new BpmIdentity(taskOpinion.getApprover(), taskOpinion.getApproverName(), "user");
/* 34 */     bpmIdentities.add(bpmIdentity);
/*    */     
/* 36 */     return bpmIdentities;
/*    */   }
/*    */   
/*    */   public boolean supportPreView()
/*    */   {
/* 41 */     return false;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\usercalc\samenode\runtime\SameNodePlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */