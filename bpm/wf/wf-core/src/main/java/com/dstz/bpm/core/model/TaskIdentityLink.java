/*     */ package com.dstz.bpm.core.model;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
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
/*     */ public class TaskIdentityLink
/*     */   implements Serializable
/*     */ {
/*     */   public static final String ak = "user";
/*     */   protected String id;
/*     */   protected String taskId;
/*     */   protected String o;
/*     */   protected String type;
/*     */   protected String al;
/*     */   protected String identity;
/*     */   protected String am;
/*     */   
/*     */   public void setId(String id)
/*     */   {
/*  54 */     this.id = id;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getId()
/*     */   {
/*  62 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setTaskId(String taskId) {
/*  66 */     this.taskId = taskId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getTaskId()
/*     */   {
/*  74 */     return this.taskId;
/*     */   }
/*     */   
/*     */   public void setInstId(String instId) {
/*  78 */     this.o = instId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getInstId()
/*     */   {
/*  86 */     return this.o;
/*     */   }
/*     */   
/*     */   public void setType(String type) {
/*  90 */     this.type = type;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getType()
/*     */   {
/*  98 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setIdentityName(String identityName) {
/* 102 */     this.al = identityName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getIdentityName()
/*     */   {
/* 110 */     return this.al;
/*     */   }
/*     */   
/*     */   public void setIdentity(String identity) {
/* 114 */     this.identity = identity;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getIdentity()
/*     */   {
/* 122 */     return this.identity;
/*     */   }
/*     */   
/*     */   public void setPermissionCode(String permissionCode) {
/* 126 */     this.am = permissionCode;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getPermissionCode()
/*     */   {
/* 134 */     return this.am;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 140 */     return 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 148 */       new ToStringBuilder(this).append("id", this.id).append("taskId", this.taskId).append("instId", this.o).append("type", this.type).append("identityName", this.al).append("identity", this.identity).append("permissionCode", this.am).toString();
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\model\TaskIdentityLink.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */