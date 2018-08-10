/*     */ package com.dstz.bpm.core.model;
/*     */ 
/*     */ import com.dstz.base.api.model.IBaseModel;
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
/*     */ public class BpmTaskCandidate
/*     */   implements IBaseModel
/*     */ {
/*     */   protected String id;
/*     */   protected String taskId;
/*     */   protected String type;
/*     */   protected String ab;
/*     */   protected String o;
/*     */   
/*     */   public void setId(String id)
/*     */   {
/*  44 */     this.id = id;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getId()
/*     */   {
/*  52 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setTaskId(String taskId) {
/*  56 */     this.taskId = taskId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getTaskId()
/*     */   {
/*  64 */     return this.taskId;
/*     */   }
/*     */   
/*     */   public void setType(String type) {
/*  68 */     this.type = type;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getType()
/*     */   {
/*  76 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setExecutor(String executor) {
/*  80 */     this.ab = executor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getExecutor()
/*     */   {
/*  88 */     return this.ab;
/*     */   }
/*     */   
/*     */   public void setInstId(String instId) {
/*  92 */     this.o = instId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getInstId()
/*     */   {
/* 100 */     return this.o;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 106 */     return 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 112 */       new ToStringBuilder(this).append("id", this.id).append("taskId", this.taskId).append("type", this.type).append("executor", this.ab).append("instId", this.o).toString();
/*     */   }
/*     */   
/*     */ 
/*     */   public String getCreateBy()
/*     */   {
/* 118 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCreateBy(String createBy) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public Date getCreateTime()
/*     */   {
/* 130 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCreateTime(Date createtime) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public Date getUpdateTime()
/*     */   {
/* 142 */     return null;
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
/* 154 */     return null;
/*     */   }
/*     */   
/*     */   public void setUpdateBy(String updateBy) {}
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\model\BpmTaskCandidate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */