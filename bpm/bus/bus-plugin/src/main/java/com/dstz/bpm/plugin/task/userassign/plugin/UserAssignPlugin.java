/*    */ package com.dstz.bpm.plugin.task.userassign.plugin;
/*    */ 
/*    */ import com.alibaba.fastjson.JSON;
/*    */ import com.dstz.base.core.util.BeanUtils;
/*    */ import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
/*    */ import com.dstz.bpm.api.engine.context.BpmContext;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmTaskPluginDef;
/*    */ import com.dstz.bpm.api.engine.plugin.def.UserAssignRule;
/*    */ import com.dstz.bpm.engine.plugin.factory.BpmPluginSessionFactory;
/*    */ import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractBpmTaskPlugin;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmTaskPluginSession;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
/*    */ import com.dstz.bpm.plugin.task.userassign.def.UserAssignPluginDef;
/*    */ import com.dstz.sys.api.groovy.IGroovyScriptEngine;
/*    */ import com.dstz.sys.api.model.SysIdentity;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.slf4j.Logger;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ public class UserAssignPlugin
/*    */   extends AbstractBpmTaskPlugin<BpmTaskPluginSession, BpmTaskPluginDef>
/*    */ {
/*    */   @Resource
/*    */   IGroovyScriptEngine groovyScriptEngine;
/*    */   
/*    */   public Void a(BpmTaskPluginSession pluginSession, BpmTaskPluginDef pluginDef)
/*    */   {
/* 31 */     UserAssignPluginDef assignPluginDef = (UserAssignPluginDef)pluginDef;
/*    */     
/* 33 */     TaskActionCmd model = (TaskActionCmd)BpmContext.getActionModel();
/*    */     
/* 35 */     List<SysIdentity> identityList = model.getBpmIdentity(model.getNodeId());
/*    */     
/*    */ 
/* 38 */     if (BeanUtils.isNotEmpty(identityList)) {
/* 39 */       return null;
/*    */     }
/*    */     
/* 42 */     List<UserAssignRule> ruleList = assignPluginDef.getRuleList();
/* 43 */     if (BeanUtils.isEmpty(ruleList)) { return null;
/*    */     }
/* 45 */     BpmUserCalcPluginSession bpmUserCalcPluginSession = BpmPluginSessionFactory.buildBpmUserCalcPluginSession(pluginSession);
/* 46 */     List<SysIdentity> bpmIdentities = UserAssignRuleCalc.a(bpmUserCalcPluginSession, ruleList, Boolean.valueOf(false));
/*    */     
/*    */ 
/* 49 */     List<SysIdentity> identitieList = new ArrayList();
/*    */     
/* 51 */     for (SysIdentity identity : bpmIdentities) {
/* 52 */       if (identity != null) {
/* 53 */         identitieList.add(identity);
/*    */       }
/*    */     }
/* 56 */     LOG.debug("用户计算插件执行完毕，解析到【{}】条有效人员信息。节点:{}", Integer.valueOf(identitieList.size()), model.getNodeId());
/* 57 */     LOG.trace(JSON.toJSONString(identitieList));
/*    */     
/* 59 */     model.setBpmIdentity(model.getNodeId(), identitieList);
/* 60 */     return null;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\task\userassign\plugin\UserAssignPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */