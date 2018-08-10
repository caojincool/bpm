/*    */ package com.dstz.bpm.core.manager.impl;
/*    */ 
/*    */ import com.dstz.base.db.id.UniqueIdUtil;
/*    */ import com.dstz.base.manager.impl.BaseManager;
/*    */ import com.dstz.bpm.api.model.task.IBpmTask;
/*    */ import com.dstz.bpm.core.dao.BpmTaskStackDao;
/*    */ import com.dstz.bpm.core.manager.BpmTaskStackManager;
/*    */ import com.dstz.bpm.core.model.BpmTaskStack;
/*    */ import java.util.Date;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service("bpmExecutionStackManager")
/*    */ public class BpmTaskStackManagerImpl
/*    */   extends BaseManager<String, BpmTaskStack>
/*    */   implements BpmTaskStackManager
/*    */ {
/*    */   @Resource
/*    */   BpmTaskStackDao l;
/*    */   
/*    */   public BpmTaskStack getByTaskId(String taskId)
/*    */   {
/* 28 */     return this.l.getByTaskId(taskId);
/*    */   }
/*    */   
/*    */   public BpmTaskStack createStackByTask(IBpmTask task, BpmTaskStack parentStack)
/*    */   {
/* 33 */     BpmTaskStack stack = new BpmTaskStack();
/* 34 */     String id = UniqueIdUtil.getSuid();
/* 35 */     stack.setId(id);
/* 36 */     stack.setNodeId(task.getNodeId());
/* 37 */     stack.setNodeName(task.getName());
/* 38 */     stack.setTaskId(task.getId());
/*    */     
/* 40 */     stack.setStartTime(new Date());
/* 41 */     stack.setInstId(task.getInstId());
/*    */     
/* 43 */     if (parentStack == null) {
/* 44 */       stack.setPath(id + ".");
/* 45 */       stack.setParentId("0");
/*    */     } else {
/* 47 */       stack.setPath(parentStack.getPath() + id + ".");
/* 48 */       stack.setParentId(parentStack.getId());
/*    */     }
/* 50 */     create(stack);
/*    */     
/* 52 */     return stack;
/*    */   }
/*    */   
/*    */   public void removeByInstanceId(String instId)
/*    */   {
/* 57 */     this.l.removeByInstanceId(instId);
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\manager\impl\BpmTaskStackManagerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */