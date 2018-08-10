/*    */ package com.dstz.bpm.engine.listener;
/*    */ 
/*    */ import com.dstz.bpm.act.listener.ActEventListener;
/*    */ import com.dstz.bpm.api.constant.EventType;
/*    */ import com.dstz.bpm.api.constant.ScriptType;
/*    */ import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
/*    */ import com.dstz.bpm.api.engine.plugin.cmd.TaskCommand;
/*    */ import javax.annotation.Resource;
/*    */ import org.activiti.engine.delegate.event.ActivitiEntityEvent;
/*    */ import org.activiti.engine.delegate.event.ActivitiEvent;
/*    */ import org.activiti.engine.impl.persistence.entity.TaskEntity;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractTaskListener<T extends TaskActionCmd>
/*    */   implements ActEventListener
/*    */ {
/* 21 */   protected Logger LOG = LoggerFactory.getLogger(getClass());
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   @Resource
/*    */   protected TaskCommand az;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public abstract EventType getBeforeTriggerEventType();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public abstract EventType getAfterTriggerEventType();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public abstract void a(T paramT);
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public abstract void b(T paramT);
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public abstract void c(T paramT);
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void notify(ActivitiEvent event)
/*    */   {
/* 64 */     ActivitiEntityEvent entityEvent = (ActivitiEntityEvent)event;
/* 65 */     TaskEntity taskEntity = (TaskEntity)entityEvent.getEntity();
/*    */     
/* 67 */     T model = a(taskEntity);
/*    */     
/*    */ 
/* 70 */     a(model);
/*    */     
/*    */ 
/*    */ 
/* 74 */     if (getBeforeTriggerEventType() != null) {
/* 75 */       this.LOG.debug("============【{}】插件执行开始=============", getBeforeTriggerEventType().getValue());
/* 76 */       this.az.execute(getBeforeTriggerEventType(), model);
/* 77 */       this.LOG.debug("============【{}】插件执行完毕=============", getBeforeTriggerEventType().getValue());
/*    */     }
/*    */     
/*    */ 
/* 81 */     b(model);
/*    */     
/*    */ 
/*    */ 
/* 85 */     if (getAfterTriggerEventType() != null) {
/* 86 */       this.LOG.debug("============【{}】插件执行开始=============", getAfterTriggerEventType().getValue());
/* 87 */       this.az.execute(getAfterTriggerEventType(), model);
/* 88 */       this.LOG.debug("============【{}】插件执行完毕=============", getAfterTriggerEventType().getValue());
/*    */     }
/*    */     
/*    */ 
/* 92 */     c(model);
/*    */   }
/*    */   
/*    */   protected abstract ScriptType getScriptType();
/*    */   
/*    */   public abstract T a(TaskEntity paramTaskEntity);
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\listener\AbstractTaskListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */