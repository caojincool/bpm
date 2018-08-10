/*    */ package com.dstz.bpm.core.manager.impl;
/*    */ 
/*    */ import com.dstz.base.api.query.QueryFilter;
/*    */ import com.dstz.base.core.util.StringUtil;
/*    */ import com.dstz.base.db.id.UniqueIdUtil;
/*    */ import com.dstz.base.manager.impl.BaseManager;
/*    */ import com.dstz.bpm.api.model.def.IBpmDefinition;
/*    */ import com.dstz.bpm.core.dao.BpmInstanceDao;
/*    */ import com.dstz.bpm.core.manager.BpmInstanceManager;
/*    */ import com.dstz.bpm.core.model.BpmInstance;
/*    */ import com.dstz.bpm.core.model.BpmTaskApprove;
/*    */ import com.dstz.org.api.model.IUser;
/*    */ import com.dstz.sys.util.ContextUtil;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service("bpmInstanceManager")
/*    */ public class BpmInstanceManagerImpl
/*    */   extends BaseManager<String, BpmInstance>
/*    */   implements BpmInstanceManager
/*    */ {
/*    */   @Resource
/*    */   BpmInstanceDao g;
/*    */   
/*    */   public boolean isSuspendByInstId(String instId)
/*    */   {
/* 35 */     return false;
/*    */   }
/*    */   
/*    */   public List<BpmInstance> getApplyList(String userId, QueryFilter queryFilter)
/*    */   {
/* 40 */     queryFilter.addParamsFilter("userId", userId);
/* 41 */     return this.g.getApplyList(queryFilter);
/*    */   }
/*    */   
/*    */ 
/*    */   public List<BpmTaskApprove> getApproveHistoryList(String userId, QueryFilter queryFilter)
/*    */   {
/* 47 */     queryFilter.addParamsFilter("approver", userId);
/* 48 */     return this.g.getApproveHistoryList(queryFilter);
/*    */   }
/*    */   
/*    */ 
/*    */   public BpmInstance getTopInstance(BpmInstance instance)
/*    */   {
/* 54 */     if ((instance == null) || (StringUtil.isZeroEmpty(instance.getParentInstId()))) {
/* 55 */       return null;
/*    */     }
/* 57 */     BpmInstance parentInstance = (BpmInstance)get(instance.getParentInstId());
/*    */     
/* 59 */     BpmInstance topInstance = getTopInstance(parentInstance);
/* 60 */     if (topInstance != null) {
/* 61 */       return topInstance;
/*    */     }
/*    */     
/* 64 */     return parentInstance;
/*    */   }
/*    */   
/*    */   public BpmInstance genInstanceByDefinition(IBpmDefinition bpmDefinition)
/*    */   {
/* 69 */     BpmInstance instance = new BpmInstance();
/* 70 */     instance.setId(UniqueIdUtil.getSuid());
/* 71 */     instance.setSubject(bpmDefinition.getName());
/*    */     
/* 73 */     instance.setDefId(bpmDefinition.getId());
/* 74 */     instance.setTypeId(bpmDefinition.getTypeId());
/* 75 */     instance.setDefKey(bpmDefinition.getKey());
/* 76 */     instance.setActDefId(bpmDefinition.getActDefId());
/* 77 */     instance.setDefName(bpmDefinition.getName());
/*    */     
/* 79 */     IUser user = ContextUtil.getCurrentUser();
/* 80 */     instance.setCreateBy(user.getUserId());
/* 81 */     instance.setCreator(user.getFullname());
/* 82 */     instance.setCreateTime(new Date());
/*    */     
/* 84 */     instance.setSupportMobile(bpmDefinition.getSupportMobile());
/*    */     
/* 86 */     instance.setParentInstId("0");
/*    */     
/* 88 */     if (bpmDefinition.getStatus().equals("deploy")) {
/* 89 */       instance.setIsFormmal("Y");
/*    */     }
/* 91 */     else if (bpmDefinition.getStatus().equals("draft")) {
/* 92 */       instance.setIsFormmal("N");
/*    */     }
/*    */     
/* 95 */     instance.setHasCreate(Boolean.valueOf(false));
/* 96 */     return instance;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\manager\impl\BpmInstanceManagerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */