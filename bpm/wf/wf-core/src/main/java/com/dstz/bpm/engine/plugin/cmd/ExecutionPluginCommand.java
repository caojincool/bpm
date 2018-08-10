/*    */ package com.dstz.bpm.engine.plugin.cmd;
/*    */ 
/*    */ import com.dstz.base.api.exception.BusinessException;
/*    */ import com.dstz.bpm.api.constant.EventType;
/*    */ import com.dstz.bpm.api.engine.action.cmd.InstanceActionCmd;
/*    */ import com.dstz.bpm.api.engine.plugin.context.BpmPluginContext;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmExecutionPluginDef;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;
/*    */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*    */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*    */ import com.dstz.bpm.api.service.BpmProcessDefService;
/*    */ import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
/*    */ import com.dstz.bpm.engine.plugin.factory.BpmPluginFactory;
/*    */ import com.dstz.bpm.engine.plugin.factory.BpmPluginSessionFactory;
/*    */ import com.dstz.bpm.engine.plugin.runtime.BpmExecutionPlugin;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmExecutionPluginSession;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ public class ExecutionPluginCommand implements com.dstz.bpm.api.engine.plugin.cmd.ExecutionCommand
/*    */ {
/* 27 */   protected Logger LOG = LoggerFactory.getLogger(getClass());
/*    */   
/*    */   @Resource
/*    */   BpmProcessDefService a;
/*    */   
/*    */   public void execute(EventType eventType, InstanceActionCmd actionModel)
/*    */   {
/* 34 */     String defId = actionModel.getDefId();
/* 35 */     DefaultBpmProcessDef bpmProcessDef = (DefaultBpmProcessDef)this.a.getBpmProcessDef(defId);
/*    */     
/* 37 */     for (Iterator localIterator = bpmProcessDef.getBpmPluginContexts().iterator(); localIterator.hasNext();) { bpmPluginContext = (BpmPluginContext)localIterator.next();
/* 38 */       a(bpmPluginContext, actionModel, eventType);
/*    */     }
/*    */     
/*    */     BpmPluginContext bpmPluginContext;
/* 42 */     BpmNodeDef nodeDef = null;
/* 43 */     if ((eventType == EventType.START_POST_EVENT) || (eventType == EventType.START_EVENT)) {
/* 44 */       nodeDef = this.a.getStartEvent(defId);
/* 45 */     } else if ((eventType == EventType.END_EVENT) || (eventType == EventType.END_POST_EVENT))
/*    */     {
/* 47 */       nodeDef = (BpmNodeDef)this.a.getEndEvents(defId).get(0);
/*    */     }
/*    */     
/*    */ 
/* 51 */     if ((nodeDef != null) && (nodeDef.getBpmPluginContexts() != null)) {
/* 52 */       for (BpmPluginContext bpmPluginContext : nodeDef.getBpmPluginContexts())
/* 53 */         a(bpmPluginContext, actionModel, eventType);
/*    */     }
/*    */   }
/*    */   
/*    */   private void a(BpmPluginContext bpmPluginContext, InstanceActionCmd actionModel, EventType eventType) {
/* 58 */     BpmPluginDef bpmPluginDef = bpmPluginContext.getBpmPluginDef();
/*    */     
/* 60 */     if ((bpmPluginDef instanceof BpmExecutionPluginDef)) {
/* 61 */       BpmExecutionPluginDef bpmExecutionPluginDef = (BpmExecutionPluginDef)bpmPluginDef;
/* 62 */       BpmExecutionPlugin<BpmExecutionPluginSession, BpmExecutionPluginDef> bpmExecutionPlugin = BpmPluginFactory.buildExecutionPlugin(bpmPluginContext, eventType);
/* 63 */       BpmExecutionPluginSession session = BpmPluginSessionFactory.buildExecutionPluginSession(actionModel, eventType);
/*    */       
/* 65 */       if (bpmExecutionPlugin != null) {
/*    */         try {
/* 67 */           bpmExecutionPlugin.execute(session, bpmExecutionPluginDef);
/* 68 */           this.LOG.debug("==>执行插件【{}】", bpmPluginContext.getTitle());
/*    */         } catch (Exception e) {
/* 70 */           this.LOG.error("执行插件【{}】出现异常:{}", new Object[] { bpmPluginContext.getTitle(), e.getMessage(), e });
/* 71 */           throw new BusinessException(bpmPluginContext.getTitle(), BpmStatusCode.PLUGIN_ERROR, e);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\plugin\cmd\ExecutionPluginCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */