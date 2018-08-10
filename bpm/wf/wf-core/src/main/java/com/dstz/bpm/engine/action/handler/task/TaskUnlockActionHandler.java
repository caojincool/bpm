 package com.dstz.bpm.engine.action.handler.task;
 
 import com.dstz.base.api.exception.BusinessException;
 import com.dstz.bpm.api.constant.ActionType;
 import com.dstz.bpm.api.constant.TaskStatus;
 import com.dstz.bpm.core.manager.BpmTaskManager;
 import com.dstz.bpm.core.model.BpmTask;
 import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
 import javax.annotation.Resource;
 
 @org.springframework.stereotype.Component
 public class TaskUnlockActionHandler extends AbstractTaskActionHandler<DefualtTaskActionCmd>
 {
   @Resource
   BpmTaskManager aC;
   
   public ActionType getActionType()
   {
     return ActionType.UNLOCK;
   }
   
   public void f(DefualtTaskActionCmd model)
   {
     b(model);
     k(model);
     
     BpmTask task = (BpmTask)model.getBpmTask();
     if (!task.getStatus().equals(TaskStatus.LOCK.getKey())) {
       throw new BusinessException("该任务并非锁定状态,或已经被解锁锁定，解锁失败");
     }
     
     this.aC.unLockTask(task.getId());
   }
   
 
 
   protected void d(DefualtTaskActionCmd actionModel) {}
   
 
 
   protected void e(DefualtTaskActionCmd actionModel) {}
   
 
 
   public int getSn()
   {
     return 6;
   }
   
   public Boolean isDefault()
   {
     return Boolean.valueOf(false);
   }
   
   public String getDefaultGroovyScript()
   {
     return "return task.getStatus().equals(TaskStatus.LOCK.getKey();";
   }
   
   public String getConfigPage()
   {
     return "";
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\action\handler\task\TaskUnlockActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */