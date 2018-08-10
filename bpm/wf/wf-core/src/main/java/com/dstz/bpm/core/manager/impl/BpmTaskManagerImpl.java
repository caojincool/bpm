/*     */ package com.dstz.bpm.core.manager.impl;
/*     */ 
/*     */ import com.dstz.base.api.exception.BusinessException;
/*     */ import com.dstz.base.api.query.QueryFilter;
/*     */ import com.dstz.base.api.query.QueryOP;
/*     */ import com.dstz.base.core.util.BeanUtils;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.base.manager.impl.BaseManager;
/*     */ import com.dstz.bpm.api.constant.TaskStatus;
/*     */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*     */ import com.dstz.bpm.core.dao.BpmTaskDao;
/*     */ import com.dstz.bpm.core.manager.BpmInstanceManager;
/*     */ import com.dstz.bpm.core.manager.BpmTaskManager;
/*     */ import com.dstz.bpm.core.manager.TaskIdentityLinkManager;
/*     */ import com.dstz.bpm.core.model.BpmTask;
/*     */ import com.dstz.bpm.core.model.TaskIdentityLink;
/*     */ import com.dstz.sys.util.ContextUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Resource;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Service("bpmTaskManager")
/*     */ public class BpmTaskManagerImpl
/*     */   extends BaseManager<String, BpmTask>
/*     */   implements BpmTaskManager
/*     */ {
/*     */   @Resource
/*     */   BpmTaskDao h;
/*     */   @Resource
/*     */   TaskIdentityLinkManager i;
/*     */   @Resource
/*     */   BpmInstanceManager j;
/*     */   
/*     */   public List<BpmTask> getByInstIdNodeId(String instId, String nodeId)
/*     */   {
/*  44 */     return this.h.getByInstIdNodeId(instId, nodeId);
/*     */   }
/*     */   
/*     */   public List<BpmTask> getByInstId(String instId)
/*     */   {
/*  49 */     return this.h.getByInstIdNodeId(instId, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<BpmTask> getTodoList(String userId, QueryFilter queryFilter)
/*     */   {
/*  56 */     Set<String> userRights = this.i.getUserRights(userId);
/*  57 */     queryFilter.addParamsFilter("userRights", userRights);
/*  58 */     queryFilter.addParamsFilter("userId", ContextUtil.getCurrentUserId());
/*     */     
/*  60 */     List<BpmTask> taskList = this.h.getTodoList(queryFilter);
/*  61 */     return taskList;
/*     */   }
/*     */   
/*     */   public List getTodoList(QueryFilter queryFilter) {
/*  65 */     String userId = ContextUtil.getCurrentUserId();
/*  66 */     String type = (String)queryFilter.getParams().get("type");
/*  67 */     String title = (String)queryFilter.getParams().get("subject");
/*     */     
/*  69 */     if (StringUtil.isNotEmpty(title)) {
/*  70 */       queryFilter.addFilter("subject_", title, QueryOP.LIKE);
/*     */     }
/*     */     
/*  73 */     if ("done".equals(type)) {
/*  74 */       return this.j.getApproveHistoryList(userId, queryFilter);
/*     */     }
/*     */     
/*  77 */     Set<String> userRights = this.i.getUserRights(userId);
/*  78 */     queryFilter.addParamsFilter("userRights", userRights);
/*     */     
/*  80 */     return this.h.getTodoList(queryFilter);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void assigneeTask(String taskId, String userId, String userName)
/*     */   {
/*  87 */     BpmTask task = (BpmTask)get(taskId);
/*  88 */     if (task == null) {
/*  89 */       throw new BusinessException("任务可能已经被处理，请刷新。", BpmStatusCode.TASK_NOT_FOUND);
/*     */     }
/*     */     
/*  92 */     task.setAssigneeId(userId);
/*  93 */     task.setAssigneeNames(userName);
/*  94 */     task.setStatus(TaskStatus.DESIGNATE.getKey());
/*  95 */     update(task);
/*     */   }
/*     */   
/*     */   public void unLockTask(String taskId)
/*     */   {
/* 100 */     BpmTask task = (BpmTask)get(taskId);
/*     */     
/* 102 */     List<TaskIdentityLink> identitys = this.i.getByTaskId(task.getId());
/* 103 */     if (BeanUtils.isEmpty(identitys)) {
/* 104 */       throw new BusinessException("该任务并非多候选人状态，无效的操作！");
/*     */     }
/*     */     
/* 107 */     List<String> names = new ArrayList();
/* 108 */     for (TaskIdentityLink identity : identitys) {
/* 109 */       names.add(identity.getIdentityName());
/*     */     }
/*     */     
/* 112 */     task.setAssigneeId("0");
/* 113 */     task.setAssigneeNames(StringUtil.convertCollectionAsString(names));
/* 114 */     task.setStatus(TaskStatus.NORMAL.getKey());
/* 115 */     update(task);
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\manager\impl\BpmTaskManagerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */