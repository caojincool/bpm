/*     */ package com.dstz.bpm.core.model;
/*     */ 
/*     */ import com.dstz.base.api.model.IBaseModel;
/*     */ import com.dstz.bpm.api.model.task.IBpmTask;
/*     */ import java.util.Date;
/*     */ import org.apache.commons.lang3.builder.ToStringBuilder;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BpmTask
/*     */   implements IBaseModel, IBpmTask
/*     */ {
/*     */   protected String id;
/*     */   protected String name;
/*     */   protected String subject;
/*     */   protected String o;
/*     */   protected String taskId;
/*     */   protected String R;
/*     */   protected String defId;
/*     */   protected String S;
/*     */   protected String T;
/*     */   protected String status;
/*     */   protected Integer priority;
/*     */   protected Date U;
/*     */   protected String V;
/*     */   protected String parentId;
/*     */   protected String D;
/*     */   protected String W;
/*     */   protected String r;
/*     */   protected Date createTime;
/*     */   protected String createBy;
/*     */   protected Integer supportMobile;
/*     */   protected String X;
/*     */   
/*     */   public String getTaskType()
/*     */   {
/* 104 */     return this.V;
/*     */   }
/*     */   
/*     */   public void setTaskType(String taskType) {
/* 108 */     this.V = taskType;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/* 130 */     this.id = id;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getId()
/*     */   {
/* 138 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 142 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/* 150 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setSubject(String subject) {
/* 154 */     this.subject = subject;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getSubject()
/*     */   {
/* 162 */     return this.subject;
/*     */   }
/*     */   
/*     */   public String getAssigneeNames() {
/* 166 */     return this.T;
/*     */   }
/*     */   
/*     */   public void setAssigneeNames(String assigneeNames) {
/* 170 */     this.T = assigneeNames;
/*     */   }
/*     */   
/*     */   public void setInstId(String instId) {
/* 174 */     this.o = instId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getInstId()
/*     */   {
/* 182 */     return this.o;
/*     */   }
/*     */   
/*     */   public void setTaskId(String taskId) {
/* 186 */     this.taskId = taskId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getTaskId()
/*     */   {
/* 194 */     return this.taskId;
/*     */   }
/*     */   
/*     */   public void setNodeId(String nodeId) {
/* 198 */     this.R = nodeId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getNodeId()
/*     */   {
/* 206 */     return this.R;
/*     */   }
/*     */   
/*     */   public void setDefId(String defId) {
/* 210 */     this.defId = defId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDefId()
/*     */   {
/* 218 */     return this.defId;
/*     */   }
/*     */   
/*     */   public void setAssigneeId(String assigneeId) {
/* 222 */     this.S = assigneeId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getAssigneeId()
/*     */   {
/* 230 */     return this.S;
/*     */   }
/*     */   
/*     */   public void setStatus(String status) {
/* 234 */     this.status = status;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getStatus()
/*     */   {
/* 242 */     return this.status;
/*     */   }
/*     */   
/*     */   public String getBackNode() {
/* 246 */     return this.X;
/*     */   }
/*     */   
/*     */   public void setBackNode(String backNode) {
/* 250 */     this.X = backNode;
/*     */   }
/*     */   
/*     */   public void setPriority(Integer priority) {
/* 254 */     this.priority = priority;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Integer getPriority()
/*     */   {
/* 262 */     return this.priority;
/*     */   }
/*     */   
/*     */   public void setDueTime(Date dueTime) {
/* 266 */     this.U = dueTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getDueTime()
/*     */   {
/* 274 */     return this.U;
/*     */   }
/*     */   
/*     */   public void setParentId(String parentId) {
/* 278 */     this.parentId = parentId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getParentId()
/*     */   {
/* 286 */     return this.parentId;
/*     */   }
/*     */   
/*     */   public void setActInstId(String actInstId) {
/* 290 */     this.D = actInstId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getActInstId()
/*     */   {
/* 298 */     return this.D;
/*     */   }
/*     */   
/*     */   public void setActExecutionId(String actExecutionId) {
/* 302 */     this.W = actExecutionId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getActExecutionId()
/*     */   {
/* 310 */     return this.W;
/*     */   }
/*     */   
/*     */   public void setTypeId(String typeId) {
/* 314 */     this.r = typeId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getTypeId()
/*     */   {
/* 322 */     return this.r;
/*     */   }
/*     */   
/*     */   public void setCreateTime(Date createTime) {
/* 326 */     this.createTime = createTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getCreateTime()
/*     */   {
/* 334 */     return this.createTime;
/*     */   }
/*     */   
/*     */   public void setCreateBy(String createBy) {
/* 338 */     this.createBy = createBy;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getCreateBy()
/*     */   {
/* 346 */     return this.createBy;
/*     */   }
/*     */   
/*     */   public void setSupportMobile(Integer supportMobile) {
/* 350 */     this.supportMobile = supportMobile;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Integer getSupportMobile()
/*     */   {
/* 358 */     return this.supportMobile;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 364 */     return 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 384 */       new ToStringBuilder(this).append("id", this.id).append("name", this.name).append("subject", this.subject).append("instId", this.o).append("taskId", this.taskId).append("nodeId", this.R).append("defId", this.defId).append("assigneeId", this.S).append("status", this.status).append("priority", this.priority).append("dueTime", this.U).append("taskType", this.V).append("parentId", this.parentId).append("actInstId", this.D).append("actExecutionId", this.W).append("typeId", this.r).append("createTime", this.createTime).append("createBy", this.createBy).append("supportMobile", this.supportMobile).toString();
/*     */   }
/*     */   
/*     */   public String getActExecutionIdId()
/*     */   {
/* 389 */     return this.W;
/*     */   }
/*     */   
/*     */   public Date getUpdateTime()
/*     */   {
/* 394 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setUpdateTime(Date updatetime) {}
/*     */   
/*     */ 
/*     */   public String getUpdateBy()
/*     */   {
/* 404 */     return null;
/*     */   }
/*     */   
/*     */   public void setUpdateBy(String updateBy) {}
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\model\BpmTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */