 package com.dstz.bpm.engine.action.handler.task;
 
 import com.dstz.base.api.exception.BusinessException;
 import com.dstz.bpm.api.constant.ActionType;
 import com.dstz.bpm.api.constant.TaskStatus;
 import com.dstz.bpm.core.model.BpmTask;
 import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
 import com.dstz.org.api.model.IUser;
 import com.dstz.sys.util.ContextUtil;
 
 @org.springframework.stereotype.Component
 public class TaskLockActionHandler extends AbstractTaskActionHandler<DefualtTaskActionCmd>
 {
   public ActionType getActionType()
   {
     return ActionType.LOCK;
   }
   
   public void f(DefualtTaskActionCmd model)
   {
     b(model);
     k(model);
     
     BpmTask task = (BpmTask)model.getBpmTask();
     if (!task.getAssigneeId().equals("0")) {
       throw new BusinessException("该任务不支持锁定,或已经被锁定");
     }
     
     task.setAssigneeId(ContextUtil.getCurrentUserId());
     task.setAssigneeNames(ContextUtil.getCurrentUser().getFullname());
     task.setStatus(TaskStatus.LOCK.getKey());
     this.ay.update(task);
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
     return "if(task.getAssignee()==\"0\") return true; return false;";
   }
   
   public String getConfigPage()
   {
     return "";
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\action\handler\task\TaskLockActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */