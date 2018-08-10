/*     */ package com.dstz.bpm.core.model;
/*     */ 
/*     */ import com.dstz.base.api.model.IBaseModel;
/*     */ import com.dstz.bpm.api.model.inst.IBpmInstance;
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
/*     */ public class BpmInstance
/*     */   implements IBaseModel, IBpmInstance
/*     */ {
/*     */   protected String id;
/*     */   protected String subject;
/*     */   protected String defId;
/*     */   protected String s;
/*     */   protected String A;
/*     */   protected String defName;
/*     */   protected String B;
/*     */   protected String status;
/*     */   protected Date endTime;
/*     */   protected Long C;
/*     */   protected String r;
/*     */   protected String D;
/*     */   protected String createBy;
/*     */   protected String creator;
/*     */   protected Date createTime;
/*     */   protected String x;
/*     */   protected String updateBy;
/*     */   protected Date updateTime;
/*     */   protected String E;
/*     */   protected String F;
/* 119 */   protected Short G = Short.valueOf((short)0);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String H;
/*     */   
/*     */ 
/*     */ 
/*     */   protected Integer supportMobile;
/*     */   
/*     */ 
/*     */ 
/*     */   protected String I;
/*     */   
/*     */ 
/*     */ 
/* 136 */   protected transient boolean J = true;
/*     */   
/*     */   public void setId(String id)
/*     */   {
/* 140 */     this.id = id;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getId()
/*     */   {
/* 148 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setSubject(String subject) {
/* 152 */     this.subject = subject;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getSubject()
/*     */   {
/* 160 */     return this.subject;
/*     */   }
/*     */   
/*     */   public void setDefId(String defId) {
/* 164 */     this.defId = defId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDefId()
/*     */   {
/* 172 */     return this.defId;
/*     */   }
/*     */   
/*     */   public void setActDefId(String actDefId) {
/* 176 */     this.s = actDefId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getActDefId()
/*     */   {
/* 184 */     return this.s;
/*     */   }
/*     */   
/*     */   public void setDefKey(String defKey) {
/* 188 */     this.A = defKey;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDefKey()
/*     */   {
/* 196 */     return this.A;
/*     */   }
/*     */   
/*     */   public void setDefName(String defName) {
/* 200 */     this.defName = defName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDefName()
/*     */   {
/* 208 */     return this.defName;
/*     */   }
/*     */   
/*     */   public void setBizKey(String bizKey) {
/* 212 */     this.B = bizKey;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getBizKey()
/*     */   {
/* 220 */     if (this.B == null) {
/* 221 */       return "";
/*     */     }
/* 223 */     return this.B;
/*     */   }
/*     */   
/*     */   public void setStatus(String status) {
/* 227 */     this.status = status;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getStatus()
/*     */   {
/* 235 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setEndTime(Date endTime) {
/* 239 */     this.endTime = endTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getEndTime()
/*     */   {
/* 247 */     return this.endTime;
/*     */   }
/*     */   
/*     */   public void setDuration(Long duration) {
/* 251 */     this.C = duration;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Long getDuration()
/*     */   {
/* 259 */     return this.C;
/*     */   }
/*     */   
/*     */   public void setTypeId(String typeId) {
/* 263 */     this.r = typeId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getTypeId()
/*     */   {
/* 271 */     return this.r;
/*     */   }
/*     */   
/*     */   public void setActInstId(String actInstId) {
/* 275 */     this.D = actInstId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getActInstId()
/*     */   {
/* 283 */     return this.D;
/*     */   }
/*     */   
/*     */   public void setCreateBy(String createBy) {
/* 287 */     this.createBy = createBy;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getCreateBy()
/*     */   {
/* 295 */     return this.createBy;
/*     */   }
/*     */   
/*     */   public void setCreator(String creator) {
/* 299 */     this.creator = creator;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getCreator()
/*     */   {
/* 307 */     return this.creator;
/*     */   }
/*     */   
/*     */   public void setCreateTime(Date createTime) {
/* 311 */     this.createTime = createTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getCreateTime()
/*     */   {
/* 319 */     return this.createTime;
/*     */   }
/*     */   
/*     */   public void setCreateOrgId(String createOrgId) {
/* 323 */     this.x = createOrgId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getCreateOrgId()
/*     */   {
/* 331 */     return this.x;
/*     */   }
/*     */   
/*     */   public void setUpdateBy(String updateBy) {
/* 335 */     this.updateBy = updateBy;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getUpdateBy()
/*     */   {
/* 343 */     return this.updateBy;
/*     */   }
/*     */   
/*     */   public void setUpdateTime(Date updateTime) {
/* 347 */     this.updateTime = updateTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getUpdateTime()
/*     */   {
/* 355 */     return this.updateTime;
/*     */   }
/*     */   
/*     */   public void setIsFormmal(String isFormmal) {
/* 359 */     this.E = isFormmal;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getIsFormmal()
/*     */   {
/* 367 */     return this.E;
/*     */   }
/*     */   
/*     */   public void setParentInstId(String parentInstId) {
/* 371 */     this.F = parentInstId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getParentInstId()
/*     */   {
/* 379 */     return this.F;
/*     */   }
/*     */   
/*     */   public void setIsForbidden(Short isForbidden) {
/* 383 */     this.G = isForbidden;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Short getIsForbidden()
/*     */   {
/* 391 */     return this.G;
/*     */   }
/*     */   
/*     */   public void setDataMode(String dataMode) {
/* 395 */     this.H = dataMode;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDataMode()
/*     */   {
/* 403 */     return this.H;
/*     */   }
/*     */   
/*     */   public void setSupportMobile(Integer supportMobile) {
/* 407 */     this.supportMobile = supportMobile;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Integer getSupportMobile()
/*     */   {
/* 415 */     return this.supportMobile;
/*     */   }
/*     */   
/*     */   public void setSuperNodeId(String superNodeId) {
/* 419 */     this.I = superNodeId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getSuperNodeId()
/*     */   {
/* 427 */     return this.I;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 433 */     return 
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
/* 458 */       new ToStringBuilder(this).append("id", this.id).append("subject", this.subject).append("defId", this.defId).append("actDefId", this.s).append("defKey", this.A).append("defName", this.defName).append("bizKey", this.B).append("status", this.status).append("endTime", this.endTime).append("duration", this.C).append("typeId", this.r).append("actInstId", this.D).append("createBy", this.createBy).append("creator", this.creator).append("createTime", this.createTime).append("createOrgId", this.x).append("updateBy", this.updateBy).append("updateTime", this.updateTime).append("isFormmal", this.E).append("parentInstId", this.F).append("isForbidden", this.G).append("dataMode", this.H).append("supportMobile", this.supportMobile).append("superNodeId", this.I).toString();
/*     */   }
/*     */   
/*     */   public Boolean hasCreate()
/*     */   {
/* 463 */     return Boolean.valueOf(this.J);
/*     */   }
/*     */   
/*     */   public void setHasCreate(Boolean hasCreate)
/*     */   {
/* 468 */     this.J = hasCreate.booleanValue();
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\model\BpmInstance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */