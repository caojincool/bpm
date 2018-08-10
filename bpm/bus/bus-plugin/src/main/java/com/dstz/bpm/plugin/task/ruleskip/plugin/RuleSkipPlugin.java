/*    */ package com.dstz.bpm.plugin.task.ruleskip.plugin;
/*    */ 
/*    */ import com.dstz.base.core.util.BeanUtils;
/*    */ import com.dstz.base.core.util.StringUtil;
/*    */ import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
/*    */ import com.dstz.bpm.api.engine.context.BpmContext;
/*    */ import com.dstz.bpm.api.model.task.IBpmTask;
/*    */ import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractBpmTaskPlugin;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmTaskPluginSession;
/*    */ import com.dstz.bpm.plugin.task.ruleskip.def.JumpRule;
/*    */ import com.dstz.bpm.plugin.task.ruleskip.def.RuleSkipPluginDef;
/*    */ import com.dstz.sys.api.groovy.IGroovyScriptEngine;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.slf4j.Logger;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class RuleSkipPlugin
/*    */   extends AbstractBpmTaskPlugin<BpmTaskPluginSession, RuleSkipPluginDef>
/*    */ {
/*    */   @Resource
/*    */   IGroovyScriptEngine g;
/*    */   
/*    */   public Void a(BpmTaskPluginSession pluginSession, RuleSkipPluginDef pluginDef)
/*    */   {
/* 30 */     if (BeanUtils.isEmpty(pluginDef.getJumpRules())) return null;
/* 31 */     TaskActionCmd taskAction = (TaskActionCmd)BpmContext.getActionModel();
/* 32 */     if (StringUtil.isNotEmpty(taskAction.getDestination())) {
/* 33 */       LOG.info("任务【{}】已经指定了跳转节点【{}】，规则跳转将忽略", pluginSession.getBpmTask().getName(), taskAction.getDestination());
/* 34 */       return null;
/*    */     }
/*    */     
/* 37 */     for (JumpRule jumpRule : pluginDef.getJumpRules()) {
/* 38 */       if ((!StringUtil.isEmpty(jumpRule.getScript())) && (!StringUtil.isEmpty(jumpRule.getScript())))
/*    */       {
/*    */ 
/*    */ 
/* 42 */         boolean isJump = this.g.executeBoolean(jumpRule.getScript(), pluginSession);
/*    */         
/* 44 */         if (isJump) {
/* 45 */           taskAction.setDestination(jumpRule.getTargetNode());
/*    */           
/* 47 */           LOG.info("节点【{}】规则跳转【{}】条件满足，即将跳转至【{}】", new Object[] { pluginSession.getBpmTask().getName(), jumpRule.getName(), jumpRule.getTargetNode() });
/* 48 */           LOG.debug(jumpRule.getScript());
/* 49 */           return null;
/*    */         }
/*    */       }
/*    */     }
/* 53 */     LOG.info("节点{}规则跳转，共{}条，均不符合条件，将正常跳转", pluginSession.getBpmTask().getName(), Integer.valueOf(pluginDef.getJumpRules().size()));
/* 54 */     return null;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\task\ruleskip\plugin\RuleSkipPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */