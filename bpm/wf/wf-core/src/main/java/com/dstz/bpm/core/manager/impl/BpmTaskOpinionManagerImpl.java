/*     */ package com.dstz.bpm.core.manager.impl;
/*     */ 
/*     */ import com.dstz.base.core.util.BeanUtils;
/*     */ import com.dstz.base.manager.impl.BaseManager;
/*     */ import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
/*     */ import com.dstz.bpm.api.model.inst.IBpmInstance;
/*     */ import com.dstz.bpm.api.model.task.IBpmTask;
/*     */ import com.dstz.bpm.core.dao.BpmTaskOpinionDao;
/*     */ import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
/*     */ import com.dstz.bpm.core.model.BpmTaskOpinion;
/*     */ import com.dstz.org.api.model.IUser;
/*     */ import com.dstz.sys.api.model.SysIdentity;
/*     */ import com.dstz.sys.util.ContextUtil;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Service("bpmTaskOpinionManager")
/*     */ public class BpmTaskOpinionManagerImpl
/*     */   extends BaseManager<String, BpmTaskOpinion>
/*     */   implements BpmTaskOpinionManager
/*     */ {
/*     */   @Resource
/*     */   BpmTaskOpinionDao k;
/*     */   
/*     */   public BpmTaskOpinion getByTaskId(String taskId)
/*     */   {
/*  34 */     return this.k.getByTaskId(taskId);
/*     */   }
/*     */   
/*     */ 
/*     */   public void createOpinionByInstance(IBpmInstance bpmInstance, String formIdentity, boolean isCreateEvent)
/*     */   {
/*  40 */     BpmTaskOpinion bpmTaskOpinion = new BpmTaskOpinion();
/*     */     
/*  42 */     bpmTaskOpinion.setApproveTime(new Date());
/*  43 */     bpmTaskOpinion.setUpdateTime(new Date());
/*  44 */     bpmTaskOpinion.setDurMs(Long.valueOf(0L));
/*     */     
/*  46 */     bpmTaskOpinion.setInstId(bpmInstance.getId());
/*  47 */     bpmTaskOpinion.setSupInstId(bpmInstance.getParentInstId());
/*     */     
/*  49 */     bpmTaskOpinion.setOpinion(isCreateEvent ? "发起流程" : "流程结束");
/*  50 */     bpmTaskOpinion.setStatus(isCreateEvent ? "start" : "end");
/*  51 */     bpmTaskOpinion.setTaskId("0");
/*  52 */     bpmTaskOpinion.setTaskKey(isCreateEvent ? "start" : "end");
/*  53 */     bpmTaskOpinion.setTaskName(isCreateEvent ? "发起节点" : "终止节点");
/*  54 */     bpmTaskOpinion.setFormId(formIdentity);
/*     */     
/*  56 */     IUser user = ContextUtil.getCurrentUser();
/*  57 */     if (user != null) {
/*  58 */       bpmTaskOpinion.setApprover(user.getUserId());
/*  59 */       bpmTaskOpinion.setApproverName(user.getFullname());
/*  60 */       bpmTaskOpinion.setCreateBy(user.getUserId());
/*  61 */       bpmTaskOpinion.setUpdateBy(user.getUserId());
/*     */     }
/*     */     
/*  64 */     create(bpmTaskOpinion);
/*     */   }
/*     */   
/*     */ 
/*     */   public void createOpinionByTask(TaskActionCmd taskActionModel)
/*     */   {
/*  70 */     IBpmTask task = taskActionModel.getBpmTask();
/*  71 */     IBpmInstance bpmInstance = taskActionModel.getBpmInstance();
/*  72 */     List<SysIdentity> taskIdentitys = taskActionModel.getBpmIdentity(taskActionModel.getNodeId());
/*     */     
/*  74 */     BpmTaskOpinion bpmTaskOpinion = new BpmTaskOpinion();
/*     */     
/*  76 */     bpmTaskOpinion.setUpdateTime(new Date());
/*  77 */     bpmTaskOpinion.setDurMs(Long.valueOf(0L));
/*     */     
/*  79 */     bpmTaskOpinion.setInstId(bpmInstance.getId());
/*  80 */     bpmTaskOpinion.setSupInstId(bpmInstance.getParentInstId());
/*     */     
/*  82 */     bpmTaskOpinion.setOpinion(taskActionModel.getOpinion());
/*  83 */     bpmTaskOpinion.setStatus(taskActionModel.getActionName());
/*  84 */     bpmTaskOpinion.setTaskId(task.getId());
/*  85 */     bpmTaskOpinion.setTaskKey(task.getNodeId());
/*  86 */     bpmTaskOpinion.setTaskName(task.getName());
/*  87 */     bpmTaskOpinion.setFormId(taskActionModel.getFormId());
/*     */     
/*  89 */     IUser user = ContextUtil.getCurrentUser();
/*  90 */     if (user != null) {
/*  91 */       bpmTaskOpinion.setCreateBy(user.getUserId());
/*  92 */       bpmTaskOpinion.setUpdateBy(user.getUserId());
/*     */     }
/*     */     
/*  95 */     StringBuffer assignInfo = new StringBuffer();
/*  96 */     if (BeanUtils.isNotEmpty(taskIdentitys)) {
/*  97 */       for (SysIdentity identity : taskIdentitys)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 102 */         assignInfo.append(identity.getType()).append("-").append(identity.getName()).append("-").append(identity.getId()).append(",");
/*     */       }
/*     */     }
/* 105 */     bpmTaskOpinion.setAssignInfo(assignInfo.toString());
/*     */     
/* 107 */     create(bpmTaskOpinion);
/*     */   }
/*     */   
/*     */   public List<BpmTaskOpinion> getByInstAndNode(String instId, String nodeId) {
/* 111 */     return this.k.getByInstAndNode(instId, nodeId);
/*     */   }
/*     */   
/*     */   public List<BpmTaskOpinion> getByInstId(String instId)
/*     */   {
/* 116 */     return this.k.getByInstAndNode(instId, null);
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\manager\impl\BpmTaskOpinionManagerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */