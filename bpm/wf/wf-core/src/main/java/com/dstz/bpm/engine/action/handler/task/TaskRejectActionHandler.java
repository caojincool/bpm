/*     */ package com.dstz.bpm.engine.action.handler.task;
/*     */ 
/*     */ import com.dstz.base.api.exception.BusinessException;
/*     */ import com.dstz.base.core.util.BeanUtils;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.bpm.api.constant.ActionType;
/*     */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*     */ import com.dstz.bpm.api.exception.WorkFlowException;
/*     */ import com.dstz.bpm.api.model.def.NodeProperties;
/*     */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*     */ import com.dstz.bpm.api.model.task.IBpmTask;
/*     */ import com.dstz.bpm.api.service.BpmProcessDefService;
/*     */ import com.dstz.bpm.core.manager.BpmTaskManager;
/*     */ import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
/*     */ import com.dstz.bpm.core.manager.BpmTaskStackManager;
/*     */ import com.dstz.bpm.core.model.BpmTask;
/*     */ import com.dstz.bpm.core.model.BpmTaskOpinion;
/*     */ import com.dstz.bpm.core.model.BpmTaskStack;
/*     */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*     */ import com.dstz.bpm.engine.model.BpmIdentity;
/*     */ import com.dstz.sys.api.model.SysIdentity;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ public class TaskRejectActionHandler
/*     */   extends AbstractTaskActionHandler<DefualtTaskActionCmd>
/*     */ {
/*  35 */   private static Logger log = LoggerFactory.getLogger(TaskRejectActionHandler.class);
/*     */   
/*     */   @Resource
/*     */   BpmTaskStackManager aA;
/*     */   
/*     */   @Resource
/*     */   BpmTaskOpinionManager aB;
/*     */   
/*     */   public void e(DefualtTaskActionCmd actionModel)
/*     */   {
/*  45 */     NodeProperties nodeProperties = this.a.getBpmNodeDef(actionModel.getDefId(), actionModel.getNodeId()).getNodeProperties();
/*     */     
/*  47 */     String destinationNode = a(actionModel, nodeProperties);
/*     */     
/*     */ 
/*  50 */     if ("history".equals(nodeProperties.getBackUserMode())) {
/*  51 */       a(actionModel, destinationNode);
/*     */     }
/*     */     
/*  54 */     IBpmTask task = actionModel.getBpmTask();
/*     */     
/*  56 */     if (task.getNodeId().equals(destinationNode)) {
/*  57 */       throw new BusinessException("目标节点不能为当前任务节点", BpmStatusCode.CANNOT_BACK_NODE);
/*     */     }
/*     */     
/*  60 */     actionModel.setDestination(destinationNode);
/*  61 */     log.info("任务【{}-{}】将驳回至节点{}", new Object[] { task.getName(), task.getId(), destinationNode });
/*     */   }
/*     */   
/*     */   private void a(DefualtTaskActionCmd actionModel, String destinationNode)
/*     */   {
/*  66 */     SysIdentity identitys = null;
/*     */     
/*  68 */     List<BpmTaskOpinion> taskOpinions = this.aB.getByInstAndNode(actionModel.getInstanceId(), actionModel.getBpmTask().getNodeId());
/*  69 */     for (BpmTaskOpinion opinion : taskOpinions) {
/*  70 */       if (StringUtil.isNotEmpty(opinion.getApprover())) {
/*  71 */         identitys = new BpmIdentity(opinion.getApprover(), opinion.getApproverName(), "user");
/*     */       }
/*     */     }
/*     */     
/*  75 */     if (identitys != null) {
/*  76 */       Object list = new ArrayList();
/*  77 */       ((List)list).add(identitys);
/*  78 */       actionModel.setBpmIdentity(destinationNode, (List)list);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String a(DefualtTaskActionCmd actionModel, NodeProperties nodeProperties)
/*     */   {
/*  94 */     if (StringUtil.isNotEmpty(actionModel.getDestination())) {
/*  95 */       return actionModel.getDestination();
/*     */     }
/*     */     
/*  98 */     if ((nodeProperties != null) && (StringUtil.isNotEmpty(nodeProperties.getBackNode()))) {
/*  99 */       return nodeProperties.getBackNode();
/*     */     }
/*     */     
/* 102 */     BpmTaskStack stack = this.aA.getByTaskId(actionModel.getTaskId());
/* 103 */     if (StringUtil.isZeroEmpty(stack.getParentId())) {
/* 104 */       throw new WorkFlowException(BpmStatusCode.NO_BACK_TARGET);
/*     */     }
/*     */     
/* 107 */     BpmTaskStack preStack = (BpmTaskStack)this.aA.get(stack.getParentId());
/* 108 */     if (preStack == null) {
/* 109 */       throw new WorkFlowException("上一步任务执行堆栈信息查找失败！", BpmStatusCode.NO_BACK_TARGET);
/*     */     }
/*     */     
/* 112 */     return preStack.getNodeId();
/*     */   }
/*     */   
/*     */ 
/*     */   public void d(DefualtTaskActionCmd actionModel)
/*     */   {
/* 118 */     NodeProperties nodeProperties = this.a.getBpmNodeDef(actionModel.getDefId(), actionModel.getNodeId()).getNodeProperties();
/*     */     
/* 120 */     if ("back".equals(nodeProperties.getBackMode())) {
/* 121 */       List<BpmTask> tasks = this.ay.getByInstIdNodeId(actionModel.getInstanceId(), actionModel.getNodeId());
/* 122 */       if (BeanUtils.isEmpty(tasks)) {
/* 123 */         throw new WorkFlowException("任务返回节点标记失败，待标记任务查找不到", BpmStatusCode.NO_BACK_TARGET);
/*     */       }
/* 125 */       boolean hasUpdated = false;
/* 126 */       for (BpmTask task : tasks) {
/* 127 */         if ((StringUtil.isNotEmpty(task.getActInstId())) && (StringUtil.isNotEmpty(task.getTaskId()))) {
/* 128 */           if (hasUpdated) {
/* 129 */             throw new WorkFlowException("任务返回节点标记失败，期望查找一条，但是出现多条", BpmStatusCode.NO_BACK_TARGET);
/*     */           }
/*     */           
/* 132 */           task.setBackNode(actionModel.getNodeId());
/* 133 */           this.ay.update(task);
/* 134 */           hasUpdated = true;
/*     */         }
/*     */       }
/* 137 */       if (!hasUpdated) {
/* 138 */         throw new WorkFlowException("任务返回节点标记失败，待标记任务查找不到", BpmStatusCode.NO_BACK_TARGET);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public ActionType getActionType()
/*     */   {
/* 146 */     return ActionType.REJECT;
/*     */   }
/*     */   
/*     */   public int getSn()
/*     */   {
/* 151 */     return 3;
/*     */   }
/*     */   
/*     */ 
/*     */   public String getConfigPage()
/*     */   {
/* 157 */     return "/bpm/task/taskOpinionDialog.html";
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\action\handler\task\TaskRejectActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */