/*    */ package com.dstz.bpm.engine.plugin.cmd;
/*    */ 
/*    */ import com.dstz.base.api.exception.BusinessException;
/*    */ import com.dstz.base.core.util.BeanUtils;
/*    */ import com.dstz.bpm.api.constant.EventType;
/*    */ import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
/*    */ import com.dstz.bpm.api.engine.plugin.cmd.TaskCommand;
/*    */ import com.dstz.bpm.api.engine.plugin.context.BpmPluginContext;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmExecutionPluginDef;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmTaskPluginDef;
/*    */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*    */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*    */ import com.dstz.bpm.api.model.task.IBpmTask;
/*    */ import com.dstz.bpm.api.service.BpmProcessDefService;
/*    */ import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
/*    */ import com.dstz.bpm.engine.plugin.factory.BpmPluginFactory;
/*    */ import com.dstz.bpm.engine.plugin.factory.BpmPluginSessionFactory;
/*    */ import com.dstz.bpm.engine.plugin.runtime.BpmExecutionPlugin;
/*    */ import com.dstz.bpm.engine.plugin.runtime.BpmTaskPlugin;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmExecutionPluginSession;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmTaskPluginSession;
/*    */ import java.util.Iterator;
/*    */ import javax.annotation.Resource;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service
/*    */ public class TaskPluginCommand implements TaskCommand
/*    */ {
/* 32 */   protected Logger LOG = LoggerFactory.getLogger(getClass());
/*    */   @Resource
/*    */   BpmProcessDefService a;
/*    */   
/*    */   public void execute(EventType eventType, TaskActionCmd actionModel)
/*    */   {
/* 38 */     String defId = actionModel.getBpmTask().getDefId();
/*    */     
/* 40 */     BpmNodeDef bpmNodeDef = this.a.getBpmNodeDef(defId, actionModel.getNodeId());
/*    */     
/* 42 */     BpmTaskPluginSession bpmTaskSession = BpmPluginSessionFactory.buildTaskPluginSession(actionModel, eventType);
/* 43 */     BpmExecutionPluginSession executionSession = BpmPluginSessionFactory.buildExecutionPluginSession(actionModel, eventType);
/*    */     
/* 45 */     for (Iterator localIterator = bpmNodeDef.getBpmPluginContexts().iterator(); localIterator.hasNext();) { bpmPluginContext = (BpmPluginContext)localIterator.next();
/*    */       
/* 47 */       if (!BeanUtils.isEmpty(bpmPluginContext.getEventTypes()))
/*    */       {
/*    */ 
/* 50 */         BpmPluginDef bpmPluginDef = bpmPluginContext.getBpmPluginDef();
/* 51 */         if ((bpmPluginDef instanceof BpmTaskPluginDef)) {
/* 52 */           BpmTaskPluginDef bpmTaskPluginDef = (BpmTaskPluginDef)bpmPluginDef;
/*    */           
/* 54 */           BpmTaskPlugin bpmTaskPlugin = BpmPluginFactory.buildTaskPlugin(bpmPluginContext, eventType);
/* 55 */           if (bpmTaskPlugin == null)
/*    */             continue;
/* 57 */           try { this.LOG.debug("==>执行任务插件【{}】", bpmPluginContext.getTitle());
/* 58 */             bpmTaskPlugin.execute(bpmTaskSession, bpmTaskPluginDef);
/*    */           } catch (Exception e) {
/* 60 */             this.LOG.error("执行任务插件【{}】出现异常:{}", new Object[] { bpmPluginContext.getTitle(), e.getMessage(), e });
/* 61 */             throw new BusinessException(bpmPluginContext.getTitle(), BpmStatusCode.PLUGIN_ERROR, e);
/*    */           }
/*    */         }
/*    */         
/*    */ 
/* 66 */         if ((bpmPluginDef instanceof BpmExecutionPluginDef)) {
/* 67 */           BpmExecutionPlugin bpmExecutionPlugin = BpmPluginFactory.buildExecutionPlugin(bpmPluginContext, eventType);
/* 68 */           if (bpmExecutionPlugin != null) {
/*    */             try {
/* 70 */               this.LOG.debug("==>执行节点实例插件【{}】", bpmPluginContext.getTitle());
/* 71 */               bpmExecutionPlugin.execute(executionSession, bpmPluginContext.getBpmPluginDef());
/*    */             } catch (Exception e) {
/* 73 */               this.LOG.error("节点实例插件【{}】出现异常:{}", new Object[] { bpmPluginContext.getTitle(), e.getMessage(), e });
/* 74 */               throw new BusinessException(bpmPluginContext.getTitle(), BpmStatusCode.PLUGIN_ERROR, e);
/*    */             }
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */     
/*    */     BpmPluginContext bpmPluginContext;
/* 82 */     DefaultBpmProcessDef bpmProcessDef = (DefaultBpmProcessDef)this.a.getBpmProcessDef(defId);
/* 83 */     for (BpmPluginContext globalCtx : bpmProcessDef.getBpmPluginContexts()) {
/* 84 */       BpmExecutionPlugin bpmExecutionPlugin = BpmPluginFactory.buildExecutionPlugin(globalCtx, eventType);
/* 85 */       if (bpmExecutionPlugin != null) {
/*    */         try {
/* 87 */           this.LOG.debug("==>【{}】节点执行全局插件【{}】", bpmNodeDef.getName(), globalCtx.getTitle());
/* 88 */           bpmExecutionPlugin.execute(executionSession, globalCtx.getBpmPluginDef());
/*    */         } catch (Exception e) {
/* 90 */           this.LOG.error("【{}】节点执行全局插件【{}】出现异常:{}", new Object[] { bpmNodeDef.getName(), globalCtx.getTitle(), e.getMessage(), e });
/* 91 */           throw new BusinessException(globalCtx.getTitle(), BpmStatusCode.PLUGIN_ERROR, e);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\plugin\cmd\TaskPluginCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */