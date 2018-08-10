/*     */ package com.dstz.bpm.core.manager.impl;
/*     */ 
/*     */ import com.dstz.base.api.exception.BusinessException;
/*     */ import com.dstz.base.core.util.BeanUtils;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.base.db.id.UniqueIdUtil;
/*     */ import com.dstz.base.manager.impl.BaseManager;
/*     */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*     */ import com.dstz.bpm.api.model.task.IBpmTask;
/*     */ import com.dstz.bpm.core.dao.TaskIdentityLinkDao;
/*     */ import com.dstz.bpm.core.manager.TaskIdentityLinkManager;
/*     */ import com.dstz.bpm.core.model.TaskIdentityLink;
/*     */ import com.dstz.org.api.model.IGroup;
/*     */ import com.dstz.org.api.service.GroupService;
/*     */ import com.dstz.sys.api.model.SysIdentity;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Resource;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Service("taskIdentityLinkManager")
/*     */ public class TaskIdentityLinkManagerImpl
/*     */   extends BaseManager<String, TaskIdentityLink>
/*     */   implements TaskIdentityLinkManager
/*     */ {
/*     */   @Resource
/*     */   TaskIdentityLinkDao m;
/*     */   @Resource
/*     */   GroupService n;
/*     */   
/*     */   public void removeByTaskId(String taskId)
/*     */   {
/*  40 */     this.m.removeByTaskId(taskId);
/*     */   }
/*     */   
/*     */   public void removeByInstanceId(String instId)
/*     */   {
/*  45 */     this.m.removeByInstanceId(instId);
/*     */   }
/*     */   
/*     */ 
/*     */   public void bulkCreate(List<TaskIdentityLink> list)
/*     */   {
/*  51 */     this.m.bulkCreate(list);
/*     */   }
/*     */   
/*     */   public Boolean checkUserOperatorPermission(String userId, String instanceId, String taskId)
/*     */   {
/*  56 */     if ((StringUtil.isEmpty(instanceId)) && (StringUtil.isEmpty(taskId))) {
/*  57 */       throw new BusinessException("检查权限必须提供流程或者任务id", BpmStatusCode.PARAM_ILLEGAL);
/*     */     }
/*     */     
/*  60 */     Set<String> rights = getUserRights(userId);
/*     */     
/*  62 */     return Boolean.valueOf(this.m.checkUserOperatorPermission(rights, taskId, instanceId) > 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set<String> getUserRights(String userId)
/*     */   {
/*  72 */     List<IGroup> list = this.n.getGroupsByUserId(userId);
/*     */     
/*  74 */     Set<String> rights = new HashSet();
/*  75 */     rights.add(String.format("%s-%s", new Object[] { userId, "user" }));
/*     */     
/*  77 */     if (BeanUtils.isEmpty(list)) { return rights;
/*     */     }
/*  79 */     for (IGroup group : list) {
/*  80 */       rights.add(String.format("%s-%s", new Object[] { group.getGroupId(), group.getGroupType() }));
/*     */     }
/*     */     
/*  83 */     return rights;
/*     */   }
/*     */   
/*     */ 
/*     */   public void createIdentityLink(IBpmTask bpmTask, List<SysIdentity> identitys)
/*     */   {
/*  89 */     List<TaskIdentityLink> identityLinks = new ArrayList();
/*     */     
/*  91 */     for (SysIdentity identity : identitys) {
/*  92 */       TaskIdentityLink link = new TaskIdentityLink();
/*  93 */       link.setId(UniqueIdUtil.getSuid());
/*  94 */       link.setIdentity(identity.getId());
/*  95 */       link.setIdentityName(identity.getName());
/*  96 */       link.setType(identity.getType());
/*  97 */       link.setPermissionCode(identity.getId() + "-" + identity.getType());
/*  98 */       link.setTaskId(bpmTask.getId());
/*  99 */       link.setInstId(bpmTask.getInstId());
/* 100 */       identityLinks.add(link);
/*     */     }
/*     */     
/* 103 */     bulkCreate(identityLinks);
/*     */   }
/*     */   
/*     */   public List<TaskIdentityLink> getByTaskId(String taskId)
/*     */   {
/* 108 */     return this.m.getByTaskId(taskId);
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\manager\impl\TaskIdentityLinkManagerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */