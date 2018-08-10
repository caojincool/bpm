 package com.dstz.bpm.engine.plugin.cmd;
 
 import com.dstz.base.api.exception.BusinessException;
 import com.dstz.base.core.util.BeanUtils;
 import com.dstz.bpm.api.constant.EventType;
 import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
 import com.dstz.bpm.api.engine.plugin.cmd.TaskCommand;
 import com.dstz.bpm.api.engine.plugin.context.BpmPluginContext;
 import com.dstz.bpm.api.engine.plugin.def.BpmExecutionPluginDef;
 import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;
 import com.dstz.bpm.api.engine.plugin.def.BpmTaskPluginDef;
 import com.dstz.bpm.api.exception.BpmStatusCode;
 import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
 import com.dstz.bpm.api.model.task.IBpmTask;
 import com.dstz.bpm.api.service.BpmProcessDefService;
 import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
 import com.dstz.bpm.engine.plugin.factory.BpmPluginFactory;
 import com.dstz.bpm.engine.plugin.factory.BpmPluginSessionFactory;
 import com.dstz.bpm.engine.plugin.runtime.BpmExecutionPlugin;
 import com.dstz.bpm.engine.plugin.runtime.BpmTaskPlugin;
 import com.dstz.bpm.engine.plugin.session.BpmExecutionPluginSession;
 import com.dstz.bpm.engine.plugin.session.BpmTaskPluginSession;
 import java.util.Iterator;
 import javax.annotation.Resource;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.stereotype.Service;
 
 @Service
 public class TaskPluginCommand implements TaskCommand
 {
   protected Logger LOG = LoggerFactory.getLogger(getClass());
   @Resource
   BpmProcessDefService a;
   
   public void execute(EventType eventType, TaskActionCmd actionModel)
   {
     String defId = actionModel.getBpmTask().getDefId();
     
     BpmNodeDef bpmNodeDef = this.a.getBpmNodeDef(defId, actionModel.getNodeId());
     
     BpmTaskPluginSession bpmTaskSession = BpmPluginSessionFactory.buildTaskPluginSession(actionModel, eventType);
     BpmExecutionPluginSession executionSession = BpmPluginSessionFactory.buildExecutionPluginSession(actionModel, eventType);
     
     for (Iterator localIterator = bpmNodeDef.getBpmPluginContexts().iterator(); localIterator.hasNext();) { bpmPluginContext = (BpmPluginContext)localIterator.next();
       
       if (!BeanUtils.isEmpty(bpmPluginContext.getEventTypes()))
       {
 
         BpmPluginDef bpmPluginDef = bpmPluginContext.getBpmPluginDef();
         if ((bpmPluginDef instanceof BpmTaskPluginDef)) {
           BpmTaskPluginDef bpmTaskPluginDef = (BpmTaskPluginDef)bpmPluginDef;
           
           BpmTaskPlugin bpmTaskPlugin = BpmPluginFactory.buildTaskPlugin(bpmPluginContext, eventType);
           if (bpmTaskPlugin == null)
             continue;
           try { this.LOG.debug("==>执行任务插件【{}】", bpmPluginContext.getTitle());
             bpmTaskPlugin.execute(bpmTaskSession, bpmTaskPluginDef);
           } catch (Exception e) {
             this.LOG.error("执行任务插件【{}】出现异常:{}", new Object[] { bpmPluginContext.getTitle(), e.getMessage(), e });
             throw new BusinessException(bpmPluginContext.getTitle(), BpmStatusCode.PLUGIN_ERROR, e);
           }
         }
         
 
         if ((bpmPluginDef instanceof BpmExecutionPluginDef)) {
           BpmExecutionPlugin bpmExecutionPlugin = BpmPluginFactory.buildExecutionPlugin(bpmPluginContext, eventType);
           if (bpmExecutionPlugin != null) {
             try {
               this.LOG.debug("==>执行节点实例插件【{}】", bpmPluginContext.getTitle());
               bpmExecutionPlugin.execute(executionSession, bpmPluginContext.getBpmPluginDef());
             } catch (Exception e) {
               this.LOG.error("节点实例插件【{}】出现异常:{}", new Object[] { bpmPluginContext.getTitle(), e.getMessage(), e });
               throw new BusinessException(bpmPluginContext.getTitle(), BpmStatusCode.PLUGIN_ERROR, e);
             }
           }
         }
       }
     }
     
     BpmPluginContext bpmPluginContext;
     DefaultBpmProcessDef bpmProcessDef = (DefaultBpmProcessDef)this.a.getBpmProcessDef(defId);
     for (BpmPluginContext globalCtx : bpmProcessDef.getBpmPluginContexts()) {
       BpmExecutionPlugin bpmExecutionPlugin = BpmPluginFactory.buildExecutionPlugin(globalCtx, eventType);
       if (bpmExecutionPlugin != null) {
         try {
           this.LOG.debug("==>【{}】节点执行全局插件【{}】", bpmNodeDef.getName(), globalCtx.getTitle());
           bpmExecutionPlugin.execute(executionSession, globalCtx.getBpmPluginDef());
         } catch (Exception e) {
           this.LOG.error("【{}】节点执行全局插件【{}】出现异常:{}", new Object[] { bpmNodeDef.getName(), globalCtx.getTitle(), e.getMessage(), e });
           throw new BusinessException(globalCtx.getTitle(), BpmStatusCode.PLUGIN_ERROR, e);
         }
       }
     }
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\plugin\cmd\TaskPluginCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */