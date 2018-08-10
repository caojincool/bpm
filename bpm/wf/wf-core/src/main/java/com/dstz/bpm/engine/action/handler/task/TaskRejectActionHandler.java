 package com.dstz.bpm.engine.action.handler.task;
 
 import com.dstz.base.api.exception.BusinessException;
 import com.dstz.base.core.util.BeanUtils;
 import com.dstz.base.core.util.StringUtil;
 import com.dstz.bpm.api.constant.ActionType;
 import com.dstz.bpm.api.exception.BpmStatusCode;
 import com.dstz.bpm.api.exception.WorkFlowException;
 import com.dstz.bpm.api.model.def.NodeProperties;
 import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
 import com.dstz.bpm.api.model.task.IBpmTask;
 import com.dstz.bpm.api.service.BpmProcessDefService;
 import com.dstz.bpm.core.manager.BpmTaskManager;
 import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
 import com.dstz.bpm.core.manager.BpmTaskStackManager;
 import com.dstz.bpm.core.model.BpmTask;
 import com.dstz.bpm.core.model.BpmTaskOpinion;
 import com.dstz.bpm.core.model.BpmTaskStack;
 import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
 import com.dstz.bpm.engine.model.BpmIdentity;
 import com.dstz.sys.api.model.SysIdentity;
 import java.util.ArrayList;
 import java.util.List;
 import javax.annotation.Resource;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.stereotype.Component;
 
 
 
 @Component
 public class TaskRejectActionHandler
   extends AbstractTaskActionHandler<DefualtTaskActionCmd>
 {
   private static Logger log = LoggerFactory.getLogger(TaskRejectActionHandler.class);
   
   @Resource
   BpmTaskStackManager aA;
   
   @Resource
   BpmTaskOpinionManager aB;
   
   public void e(DefualtTaskActionCmd actionModel)
   {
     NodeProperties nodeProperties = this.a.getBpmNodeDef(actionModel.getDefId(), actionModel.getNodeId()).getNodeProperties();
     
     String destinationNode = a(actionModel, nodeProperties);
     
 
     if ("history".equals(nodeProperties.getBackUserMode())) {
       a(actionModel, destinationNode);
     }
     
     IBpmTask task = actionModel.getBpmTask();
     
     if (task.getNodeId().equals(destinationNode)) {
       throw new BusinessException("目标节点不能为当前任务节点", BpmStatusCode.CANNOT_BACK_NODE);
     }
     
     actionModel.setDestination(destinationNode);
     log.info("任务【{}-{}】将驳回至节点{}", new Object[] { task.getName(), task.getId(), destinationNode });
   }
   
   private void a(DefualtTaskActionCmd actionModel, String destinationNode)
   {
     SysIdentity identitys = null;
     
     List<BpmTaskOpinion> taskOpinions = this.aB.getByInstAndNode(actionModel.getInstanceId(), actionModel.getBpmTask().getNodeId());
     for (BpmTaskOpinion opinion : taskOpinions) {
       if (StringUtil.isNotEmpty(opinion.getApprover())) {
         identitys = new BpmIdentity(opinion.getApprover(), opinion.getApproverName(), "user");
       }
     }
     
     if (identitys != null) {
       Object list = new ArrayList();
       ((List)list).add(identitys);
       actionModel.setBpmIdentity(destinationNode, (List)list);
     }
   }
   
 
 
 
 
 
 
 
 
 
 
   protected String a(DefualtTaskActionCmd actionModel, NodeProperties nodeProperties)
   {
     if (StringUtil.isNotEmpty(actionModel.getDestination())) {
       return actionModel.getDestination();
     }
     
     if ((nodeProperties != null) && (StringUtil.isNotEmpty(nodeProperties.getBackNode()))) {
       return nodeProperties.getBackNode();
     }
     
     BpmTaskStack stack = this.aA.getByTaskId(actionModel.getTaskId());
     if (StringUtil.isZeroEmpty(stack.getParentId())) {
       throw new WorkFlowException(BpmStatusCode.NO_BACK_TARGET);
     }
     
     BpmTaskStack preStack = (BpmTaskStack)this.aA.get(stack.getParentId());
     if (preStack == null) {
       throw new WorkFlowException("上一步任务执行堆栈信息查找失败！", BpmStatusCode.NO_BACK_TARGET);
     }
     
     return preStack.getNodeId();
   }
   
 
   public void d(DefualtTaskActionCmd actionModel)
   {
     NodeProperties nodeProperties = this.a.getBpmNodeDef(actionModel.getDefId(), actionModel.getNodeId()).getNodeProperties();
     
     if ("back".equals(nodeProperties.getBackMode())) {
       List<BpmTask> tasks = this.ay.getByInstIdNodeId(actionModel.getInstanceId(), actionModel.getNodeId());
       if (BeanUtils.isEmpty(tasks)) {
         throw new WorkFlowException("任务返回节点标记失败，待标记任务查找不到", BpmStatusCode.NO_BACK_TARGET);
       }
       boolean hasUpdated = false;
       for (BpmTask task : tasks) {
         if ((StringUtil.isNotEmpty(task.getActInstId())) && (StringUtil.isNotEmpty(task.getTaskId()))) {
           if (hasUpdated) {
             throw new WorkFlowException("任务返回节点标记失败，期望查找一条，但是出现多条", BpmStatusCode.NO_BACK_TARGET);
           }
           
           task.setBackNode(actionModel.getNodeId());
           this.ay.update(task);
           hasUpdated = true;
         }
       }
       if (!hasUpdated) {
         throw new WorkFlowException("任务返回节点标记失败，待标记任务查找不到", BpmStatusCode.NO_BACK_TARGET);
       }
     }
   }
   
 
   public ActionType getActionType()
   {
     return ActionType.REJECT;
   }
   
   public int getSn()
   {
     return 3;
   }
   
 
   public String getConfigPage()
   {
     return "/bpm/task/taskOpinionDialog.html";
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\action\handler\task\TaskRejectActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */