/*    */ package com.dstz.bpm.engine.action.handler.task;
/*    */ 
/*    */ import com.dstz.base.api.exception.BusinessException;
/*    */ import com.dstz.base.core.util.StringUtil;
/*    */ import com.dstz.bpm.act.service.ActTaskService;
/*    */ import com.dstz.bpm.api.constant.EventType;
/*    */ import com.dstz.bpm.api.constant.NodeType;
/*    */ import com.dstz.bpm.api.engine.plugin.cmd.TaskCommand;
/*    */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*    */ import com.dstz.bpm.api.model.inst.IBpmInstance;
/*    */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*    */ import com.dstz.bpm.api.service.BpmProcessDefService;
/*    */ import com.dstz.bpm.core.manager.BpmInstanceManager;
/*    */ import com.dstz.bpm.core.manager.BpmTaskManager;
/*    */ import com.dstz.bpm.core.model.BpmTask;
/*    */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*    */ import com.dstz.bpm.engine.action.handler.AbsActionHandler;
/*    */ import javax.annotation.Resource;
/*    */ 
/*    */ public abstract class AbstractTaskActionHandler<T extends DefualtTaskActionCmd> extends AbsActionHandler<T>
/*    */ {
/*    */   @Resource
/*    */   protected ActTaskService ax;
/*    */   @Resource
/*    */   protected BpmTaskManager ay;
/*    */   @Resource
/*    */   protected TaskCommand az;
/*    */   
/*    */   public void a(T actionModel)
/*    */   {
/* 31 */     BpmTask bpmTask = (BpmTask)actionModel.getBpmTask();
/*    */     
/* 33 */     String taskId = bpmTask.getTaskId();
/*    */     
/* 35 */     String destinationNode = bpmTask.getBackNode();
/* 36 */     if (StringUtil.isEmpty(destinationNode)) {
/* 37 */       destinationNode = actionModel.getDestination();
/*    */     }
/*    */     
/* 40 */     if (StringUtil.isEmpty(destinationNode)) {
/* 41 */       this.ax.completeTask(taskId);
/*    */     }
/*    */     else
/*    */     {
/* 45 */       this.ax.completeTask(taskId, new String[] { destinationNode });
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   protected void b(T data)
/*    */   {
/* 52 */     if (data.getBpmTask() != null) { return;
/*    */     }
/* 54 */     BpmTask task = (BpmTask)this.ay.get(data.getTaskId());
/* 55 */     if (task == null) {
/* 56 */       throw new BusinessException(BpmStatusCode.TASK_NOT_FOUND);
/*    */     }
/*    */     
/* 59 */     data.setBpmTask(task);
/* 60 */     data.setDefId(task.getDefId());
/* 61 */     data.setBpmInstance((IBpmInstance)this.f.get(task.getInstId()));
/*    */     
/* 63 */     l(data);
/*    */     
/* 65 */     a(data, this.a.getBpmNodeDef(task.getDefId(), task.getNodeId()));
/*    */   }
/*    */   
/*    */   public Boolean isSupport(BpmNodeDef nodeDef)
/*    */   {
/* 70 */     NodeType nodeType = nodeDef.getType();
/*    */     
/* 72 */     if ((nodeType == NodeType.USERTASK) || (nodeType == NodeType.SIGNTASK)) {
/* 73 */       return Boolean.valueOf(true);
/*    */     }
/*    */     
/* 76 */     return Boolean.valueOf(false);
/*    */   }
/*    */   
/*    */   protected void c(DefualtTaskActionCmd actionModel) {
/* 80 */     this.az.execute(EventType.TASK_PRE_COMPLETE_EVENT, actionModel);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getDefaultGroovyScript()
/*    */   {
/* 89 */     return "";
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\action\handler\task\AbstractTaskActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */