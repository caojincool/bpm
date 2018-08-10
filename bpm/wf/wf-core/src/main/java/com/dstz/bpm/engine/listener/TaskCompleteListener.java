 package com.dstz.bpm.engine.listener;
 
 import com.dstz.base.core.util.BeanUtils;
 import com.dstz.bpm.api.constant.ActionType;
 import com.dstz.bpm.api.constant.EventType;
 import com.dstz.bpm.api.constant.InstanceStatus;
 import com.dstz.bpm.api.constant.OpinionStatus;
 import com.dstz.bpm.api.constant.ScriptType;
 import com.dstz.bpm.api.engine.context.BpmContext;
 import com.dstz.bpm.api.model.task.IBpmTask;
 import com.dstz.bpm.core.manager.BpmInstanceManager;
 import com.dstz.bpm.core.manager.BpmTaskManager;
 import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
 import com.dstz.bpm.core.manager.BpmTaskStackManager;
 import com.dstz.bpm.core.manager.TaskIdentityLinkManager;
 import com.dstz.bpm.core.model.BpmInstance;
 import com.dstz.bpm.core.model.BpmTaskOpinion;
 import com.dstz.bpm.core.model.BpmTaskStack;
 import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
 import com.dstz.org.api.model.IUser;
 import com.dstz.sys.util.ContextUtil;
 import java.util.Date;
 import java.util.Map;
 import javax.annotation.Resource;
 import org.activiti.engine.impl.persistence.entity.TaskEntity;
 import org.slf4j.Logger;
 import org.springframework.stereotype.Component;
 
 
 
 
 
 
 
 @Component
 public class TaskCompleteListener
   extends AbstractTaskListener<DefualtTaskActionCmd>
 {
   private static final long serialVersionUID = 6844821899585103714L;
   @Resource
   BpmInstanceManager f;
   @Resource
   BpmTaskManager aQ;
   @Resource
   TaskIdentityLinkManager i;
   @Resource
   BpmTaskOpinionManager aO;
   @Resource
   BpmTaskStackManager aA;
   
   public EventType getBeforeTriggerEventType()
   {
     return EventType.TASK_COMPLETE_EVENT;
   }
   
   public EventType getAfterTriggerEventType()
   {
     return EventType.TASK_POST_COMPLETE_EVENT;
   }
   
   public void g(DefualtTaskActionCmd taskActionModel)
   {
     this.LOG.debug("任务【{}】执行完成事件 - TaskID: {}", taskActionModel.getBpmTask().getName(), taskActionModel.getBpmTask().getId());
     
     Map<String, Object> actionVariables = taskActionModel.getActionVariables();
     if (BeanUtils.isEmpty(actionVariables)) {
       return;
     }
     
     for (String key : actionVariables.keySet()) {
       taskActionModel.addVariable(key, actionVariables.get(key));
     }
     this.LOG.debug("设置流程变量【{}】", actionVariables.keySet().toString());
   }
   
 
   public void h(DefualtTaskActionCmd taskActionModel)
   {
     DefualtTaskActionCmd complateModel = taskActionModel;
     
     this.LOG.trace("执行任务完成动作=====》更新任务意见状态");
     j(complateModel);
     
     this.LOG.trace("执行任务完成动作=====》更新任务堆栈记录");
     k(complateModel);
     
     this.LOG.trace("执行任务完成动作=====》删除任务相关信息 - 任务、任务后续人");
     l(complateModel);
   }
   
 
 
   public void i(DefualtTaskActionCmd taskActionModel) {}
   
 
 
   protected ScriptType getScriptType()
   {
     return ScriptType.COMPLETE;
   }
   
   public DefualtTaskActionCmd b(TaskEntity taskEntity)
   {
     DefualtTaskActionCmd model = (DefualtTaskActionCmd)BpmContext.getActionModel();
     model.setDelagateTask(taskEntity);
     return model;
   }
   
   private void j(DefualtTaskActionCmd taskActionModel)
   {
     InstanceStatus flowStatus = InstanceStatus.getByActionName(taskActionModel.getActionName());
     
 
     BpmInstance instance = (BpmInstance)taskActionModel.getBpmInstance();
     if (!flowStatus.getKey().equals(instance.getStatus())) {
       instance.setStatus(flowStatus.getKey());
       this.f.update(instance);
     }
     
 
     BpmTaskOpinion bpmTaskOpinion = this.aO.getByTaskId(taskActionModel.getTaskId());
     if (bpmTaskOpinion == null) { return;
     }
     OpinionStatus opnionStatus = d(taskActionModel.getActionName());
     bpmTaskOpinion.setStatus(opnionStatus.getKey());
     bpmTaskOpinion.setApproveTime(new Date());
     
     bpmTaskOpinion.setDurMs(Long.valueOf(bpmTaskOpinion.getApproveTime().getTime() - bpmTaskOpinion.getCreateTime().getTime()));
     bpmTaskOpinion.setOpinion(taskActionModel.getOpinion());
     
     IUser user = ContextUtil.getCurrentUser();
     if (user != null) {
       bpmTaskOpinion.setApprover(user.getUserId());
       bpmTaskOpinion.setApproverName(user.getFullname());
     }
     
     this.aO.update(bpmTaskOpinion);
   }
   
   private OpinionStatus d(String actionName)
   {
     ActionType action = ActionType.fromKey(actionName);
     
     switch (action) {
     case AGREE: 
       return OpinionStatus.AGREE;
     case OPPOSE:  return OpinionStatus.OPPOSE;
     case REJECT:  return OpinionStatus.REJECT;
     case REJECT2START:  return OpinionStatus.REJECT_TO_START;
     case RECOVER:  return OpinionStatus.REVOKER;
     case START:  return OpinionStatus.START;
     case MANUALEND:  return OpinionStatus.MANUAL_END;
     }
     return OpinionStatus.AWAITING_CHECK;
   }
   
 
   private void k(DefualtTaskActionCmd taskActionModel)
   {
     BpmTaskStack bpmTaskStack = this.aA.getByTaskId(taskActionModel.getTaskId());
     
     bpmTaskStack.setEndTime(new Date());
     this.aA.update(bpmTaskStack);
     taskActionModel.setTaskStack(bpmTaskStack);
   }
   
   private void l(DefualtTaskActionCmd taskActionModel) {
     this.i.removeByTaskId(taskActionModel.getTaskId());
     this.aQ.remove(taskActionModel.getTaskId());
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\listener\TaskCompleteListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */