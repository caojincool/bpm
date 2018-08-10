/*     */ package com.dstz.bpm.core.model;
/*     */ 
/*     */ import com.dstz.base.api.model.IBaseModel;
/*     */ import com.dstz.bpm.api.model.task.IBpmTaskOpinion;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BpmTaskOpinion
/*     */   implements IBaseModel, IBpmTaskOpinion
/*     */ {
/*     */   protected String id;
/*     */   protected String o;
/*     */   protected String ac;
/*     */   protected String taskId;
/*     */   protected String ad;
/*     */   protected String taskName;
/*     */   protected String ae;
/*     */   private String af;
/*     */   protected String ag;
/*     */   protected String ah;
/*     */   protected String ai;
/*     */   protected String status;
/*     */   protected String formId;
/*     */   protected String createBy;
/*     */   protected Date createTime;
/*     */   protected Date Y;
/*     */   protected Long Z;
/*     */   
/*     */   public void setId(String id)
/*     */   {
/* 106 */     this.id = id;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getId()
/*     */   {
/* 114 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setInstId(String instId) {
/* 118 */     this.o = instId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getInstId()
/*     */   {
/* 126 */     return this.o;
/*     */   }
/*     */   
/*     */   public void setSupInstId(String supInstId) {
/* 130 */     this.ac = supInstId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getSupInstId()
/*     */   {
/* 138 */     return this.ac;
/*     */   }
/*     */   
/*     */   public void setTaskId(String taskId) {
/* 142 */     this.taskId = taskId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getTaskId()
/*     */   {
/* 150 */     return this.taskId;
/*     */   }
/*     */   
/*     */   public void setTaskKey(String taskKey) {
/* 154 */     this.ad = taskKey;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getTaskKey()
/*     */   {
/* 162 */     return this.ad;
/*     */   }
/*     */   
/*     */   public void setTaskName(String taskName) {
/* 166 */     this.taskName = taskName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getTaskName()
/*     */   {
/* 174 */     return this.taskName;
/*     */   }
/*     */   
/*     */   public void setToken(String token) {
/* 178 */     this.ae = token;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getToken()
/*     */   {
/* 186 */     return this.ae;
/*     */   }
/*     */   
/*     */   public void setApprover(String approver) {
/* 190 */     this.ag = approver;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getApprover()
/*     */   {
/* 198 */     return this.ag;
/*     */   }
/*     */   
/*     */   public void setApproverName(String approverName)
/*     */   {
/* 203 */     this.ah = approverName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getApproverName()
/*     */   {
/* 211 */     return this.ah;
/*     */   }
/*     */   
/*     */   public void setOpinion(String opinion) {
/* 215 */     this.ai = opinion;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getOpinion()
/*     */   {
/* 223 */     return this.ai;
/*     */   }
/*     */   
/*     */   public Date getApproveTime() {
/* 227 */     return this.Y;
/*     */   }
/*     */   
/*     */   public void setApproveTime(Date approveTime) {
/* 231 */     this.Y = approveTime;
/*     */   }
/*     */   
/*     */   public void setStatus(String status) {
/* 235 */     this.status = status;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getStatus()
/*     */   {
/* 243 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setFormId(String formId) {
/* 247 */     this.formId = formId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getFormId()
/*     */   {
/* 255 */     return this.formId;
/*     */   }
/*     */   
/*     */   public void setCreateBy(String createBy) {
/* 259 */     this.createBy = createBy;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getCreateBy()
/*     */   {
/* 267 */     return this.createBy;
/*     */   }
/*     */   
/*     */   public void setCreateTime(Date createTime) {
/* 271 */     this.createTime = createTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getCreateTime()
/*     */   {
/* 279 */     return this.createTime;
/*     */   }
/*     */   
/*     */   public void setDurMs(Long durMs) {
/* 283 */     this.Z = durMs;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Long getDurMs()
/*     */   {
/* 291 */     return this.Z;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 297 */     return 
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
/* 314 */       new ToStringBuilder(this).append("id", this.id).append("instId", this.o).append("supInstId", this.ac).append("taskId", this.taskId).append("taskKey", this.ad).append("taskName", this.taskName).append("token", this.ae).append("approver", this.ag).append("approverName", this.ah).append("opinion", this.ai).append("status", this.status).append("formId", this.formId).append("createBy", this.createBy).append("createTime", this.createTime).append("approveTime", this.Y).append("durMs", this.Z).toString();
/*     */   }
/*     */   
/*     */ 
/*     */   public Date getUpdateTime()
/*     */   {
/* 320 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setUpdateTime(Date updatetime) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public String getUpdateBy()
/*     */   {
/* 332 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setUpdateBy(String updateBy) {}
/*     */   
/*     */ 
/*     */   public String getAssignInfo()
/*     */   {
/* 342 */     return this.af;
/*     */   }
/*     */   
/*     */   public void setAssignInfo(String assignInfo) {
/* 346 */     this.af = assignInfo;
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\model\BpmTaskOpinion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */