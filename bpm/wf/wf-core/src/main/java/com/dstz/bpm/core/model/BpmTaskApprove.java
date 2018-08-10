/*     */ package com.dstz.bpm.core.model;
/*     */ 
/*     */ import java.util.Date;
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
/*     */ public class BpmTaskApprove
/*     */ {
/*     */   protected String id;
/*     */   protected String nodeName;
/*     */   protected String R;
/*     */   protected Date Y;
/*     */   protected Long Z;
/*     */   protected String aa;
/*     */   protected String subject;
/*     */   protected String defName;
/*     */   protected String status;
/*     */   protected Date endTime;
/*     */   protected Long C;
/*     */   protected String r;
/*     */   protected String D;
/*     */   protected String createBy;
/*     */   protected String creator;
/*     */   protected Date createTime;
/*     */   
/*     */   public String getId()
/*     */   {
/*  73 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/*  77 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getNodeName() {
/*  81 */     return this.nodeName;
/*     */   }
/*     */   
/*     */   public void setNodeName(String nodeName) {
/*  85 */     this.nodeName = nodeName;
/*     */   }
/*     */   
/*     */   public Date getApproveTime() {
/*  89 */     return this.Y;
/*     */   }
/*     */   
/*     */   public void setApproveTime(Date approveTime) {
/*  93 */     this.Y = approveTime;
/*     */   }
/*     */   
/*     */   public Long getDurMs() {
/*  97 */     return this.Z;
/*     */   }
/*     */   
/*     */   public void setDurMs(Long durMs) {
/* 101 */     this.Z = durMs;
/*     */   }
/*     */   
/*     */   public String getApproveStatus() {
/* 105 */     return this.aa;
/*     */   }
/*     */   
/*     */   public void setApproveStatus(String approveStatus) {
/* 109 */     this.aa = approveStatus;
/*     */   }
/*     */   
/*     */   public String getSubject() {
/* 113 */     return this.subject;
/*     */   }
/*     */   
/*     */   public void setSubject(String subject) {
/* 117 */     this.subject = subject;
/*     */   }
/*     */   
/*     */   public String getStatus() {
/* 121 */     return this.status;
/*     */   }
/*     */   
/*     */   public String getNodeId() {
/* 125 */     return this.R;
/*     */   }
/*     */   
/*     */   public void setNodeId(String nodeId) {
/* 129 */     this.R = nodeId;
/*     */   }
/*     */   
/*     */   public void setStatus(String status) {
/* 133 */     this.status = status;
/*     */   }
/*     */   
/*     */   public Date getEndTime() {
/* 137 */     return this.endTime;
/*     */   }
/*     */   
/*     */   public void setEndTime(Date endTime) {
/* 141 */     this.endTime = endTime;
/*     */   }
/*     */   
/*     */   public Long getDuration() {
/* 145 */     return this.C;
/*     */   }
/*     */   
/*     */   public void setDuration(Long duration) {
/* 149 */     this.C = duration;
/*     */   }
/*     */   
/*     */   public String getTypeId() {
/* 153 */     return this.r;
/*     */   }
/*     */   
/*     */   public void setTypeId(String typeId) {
/* 157 */     this.r = typeId;
/*     */   }
/*     */   
/*     */   public String getActInstId() {
/* 161 */     return this.D;
/*     */   }
/*     */   
/*     */   public String getDefName() {
/* 165 */     return this.defName;
/*     */   }
/*     */   
/*     */   public void setDefName(String defName) {
/* 169 */     this.defName = defName;
/*     */   }
/*     */   
/*     */   public void setActInstId(String actInstId) {
/* 173 */     this.D = actInstId;
/*     */   }
/*     */   
/*     */   public String getCreateBy() {
/* 177 */     return this.createBy;
/*     */   }
/*     */   
/*     */   public void setCreateBy(String createBy) {
/* 181 */     this.createBy = createBy;
/*     */   }
/*     */   
/*     */   public String getCreator() {
/* 185 */     return this.creator;
/*     */   }
/*     */   
/*     */   public void setCreator(String creator) {
/* 189 */     this.creator = creator;
/*     */   }
/*     */   
/*     */   public Date getCreateTime() {
/* 193 */     return this.createTime;
/*     */   }
/*     */   
/*     */   public void setCreateTime(Date createTime) {
/* 197 */     this.createTime = createTime;
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\model\BpmTaskApprove.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */