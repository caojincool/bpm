/*     */ package com.dstz.bpm.plugin.execution.taskskip.plugin;
/*     */ 
/*     */ import com.dstz.base.core.util.BeanUtils;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
/*     */ import com.dstz.bpm.api.engine.context.BpmContext;
/*     */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*     */ import com.dstz.bpm.api.model.task.IBpmTask;
/*     */ import com.dstz.bpm.api.service.BpmProcessDefService;
/*     */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*     */ import com.dstz.bpm.engine.constant.TaskSkipType;
/*     */ import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractBpmExecutionPlugin;
/*     */ import com.dstz.bpm.engine.plugin.session.BpmExecutionPluginSession;
/*     */ import com.dstz.bpm.plugin.execution.taskskip.def.TaskSkipPluginDef;
/*     */ import com.dstz.sys.api.groovy.IGroovyScriptEngine;
/*     */ import com.dstz.sys.api.model.SysIdentity;
/*     */ import com.dstz.sys.util.ContextUtil;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import org.slf4j.Logger;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ public class TaskSkipPlugin
/*     */   extends AbstractBpmExecutionPlugin<BpmExecutionPluginSession, TaskSkipPluginDef>
/*     */ {
/*     */   @Resource
/*     */   IGroovyScriptEngine g;
/*     */   @Resource
/*     */   BpmProcessDefService h;
/*     */   
/*     */   public Void a(BpmExecutionPluginSession pluginSession, TaskSkipPluginDef pluginDef)
/*     */   {
/*  39 */     DefualtTaskActionCmd model = (DefualtTaskActionCmd)BpmContext.getActionModel();
/*     */     
/*     */ 
/*  42 */     TaskSkipType isSkip = b(pluginSession, pluginDef);
/*     */     
/*  44 */     model.setHasSkipThisTask(isSkip);
/*     */     
/*  46 */     return null;
/*     */   }
/*     */   
/*     */   private TaskSkipType b(BpmExecutionPluginSession pluginSession, TaskSkipPluginDef pluginDef) {
/*  50 */     TaskActionCmd model = (TaskActionCmd)BpmContext.getActionModel();
/*     */     
/*  52 */     TaskSkipType skipResult = TaskSkipType.NO_SKIP;
/*     */     
/*  54 */     if (StringUtil.isEmpty(pluginDef.getSkipTypeArr())) {
/*  55 */       return skipResult;
/*     */     }
/*     */     
/*  58 */     String[] skip = pluginDef.getSkipTypeArr().split(",");
/*     */     
/*  60 */     for (String typeStr : skip) {
/*  61 */       TaskSkipType type = TaskSkipType.fromKey(typeStr);
/*  62 */       switch (1.m[type.ordinal()]) {
/*     */       case 1: 
/*  64 */         skipResult = TaskSkipType.ALL_SKIP;
/*  65 */         break;
/*     */       case 2: 
/*  67 */         boolean isSkip = this.g.executeBoolean(pluginDef.getScript(), pluginSession);
/*  68 */         if (isSkip) {
/*  69 */           skipResult = TaskSkipType.SCRIPT_SKIP;
/*     */         }
/*     */         
/*     */         break;
/*     */       case 3: 
/*  74 */         List<SysIdentity> identityList = model.getBpmIdentity(model.getBpmTask().getNodeId());
/*  75 */         if ((BeanUtils.isEmpty(identityList)) || (identityList.size() > 1)) {
/*  76 */           return skipResult;
/*     */         }
/*  78 */         String userId = ContextUtil.getCurrentUserId();
/*  79 */         SysIdentity identity = (SysIdentity)identityList.get(0);
/*     */         
/*  81 */         if (identity.getId().equals(userId)) {
/*  82 */           skipResult = TaskSkipType.SAME_USER_SKIP;
/*     */         }
/*     */         
/*     */         break;
/*     */       case 4: 
/*  87 */         List<SysIdentity> identityList1 = model.getBpmIdentity(model.getBpmTask().getNodeId());
/*  88 */         if (BeanUtils.isEmpty(identityList1)) {
/*  89 */           skipResult = TaskSkipType.USER_EMPTY_SKIP;
/*     */         }
/*     */         
/*     */         break;
/*     */       case 5: 
/*  94 */         List<BpmNodeDef> list = this.h.getStartNodes(model.getBpmTask().getDefId());
/*  95 */         for (BpmNodeDef def : list) {
/*  96 */           if (def.getNodeId().equals(model.getBpmTask().getNodeId())) {
/*  97 */             skipResult = TaskSkipType.FIRSTNODE_SKIP;
/*  98 */             break;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */       
/*     */ 
/* 105 */       if (skipResult != TaskSkipType.NO_SKIP) {
/* 106 */         this.LOG.info("{}节点【{}】设置了【{}】，将跳过当前任务", new Object[] { model.getBpmTask().getId(), model.getBpmTask().getName(), skipResult.getValue() });
/* 107 */         return skipResult;
/*     */       }
/*     */     }
/*     */     
/* 111 */     return skipResult;
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\execution\taskskip\plugin\TaskSkipPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */