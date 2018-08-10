/*     */ package com.dstz.bpm.core.model;
/*     */ 
/*     */ import com.dstz.base.api.model.IBaseModel;
/*     */ import com.dstz.bpm.api.model.def.IBpmDefinition;
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
/*     */ public class BpmDefinition
/*     */   implements IBaseModel, IBpmDefinition
/*     */ {
/*     */   protected String id;
/*     */   protected String name;
/*     */   protected String key;
/*     */   protected String desc;
/*     */   protected String r;
/*  44 */   protected String status = "draft";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String s;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String t;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String u;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Integer version;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String v;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String w;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String createBy;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Date createTime;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String x;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String updateBy;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Date updateTime;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 104 */   protected Integer supportMobile = Integer.valueOf(0);
/*     */   
/*     */ 
/*     */ 
/*     */   protected String y;
/*     */   
/*     */ 
/*     */ 
/*     */   protected Integer z;
/*     */   
/*     */ 
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/* 118 */     this.id = id;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getId()
/*     */   {
/* 127 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 131 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/* 139 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setKey(String key) {
/* 143 */     this.key = key;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getKey()
/*     */   {
/* 151 */     return this.key;
/*     */   }
/*     */   
/*     */   public void setDesc(String desc) {
/* 155 */     this.desc = desc;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDesc()
/*     */   {
/* 163 */     return this.desc;
/*     */   }
/*     */   
/*     */   public void setTypeId(String typeId) {
/* 167 */     this.r = typeId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getTypeId()
/*     */   {
/* 175 */     return this.r;
/*     */   }
/*     */   
/*     */   public void setStatus(String status) {
/* 179 */     this.status = status;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getStatus()
/*     */   {
/* 187 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setActDefId(String actDefId) {
/* 191 */     this.s = actDefId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getActDefId()
/*     */   {
/* 199 */     return this.s;
/*     */   }
/*     */   
/*     */   public void setActModelId(String actModelId) {
/* 203 */     this.t = actModelId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getActModelId()
/*     */   {
/* 211 */     return this.t;
/*     */   }
/*     */   
/*     */   public void setActDeployId(String actDeployId) {
/* 215 */     this.u = actDeployId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getActDeployId()
/*     */   {
/* 223 */     return this.u;
/*     */   }
/*     */   
/*     */   public void setVersion(Integer version) {
/* 227 */     this.version = version;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Integer getVersion()
/*     */   {
/* 235 */     return this.version;
/*     */   }
/*     */   
/*     */   public void setMainDefId(String mainDefId) {
/* 239 */     this.v = mainDefId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getMainDefId()
/*     */   {
/* 247 */     return this.v;
/*     */   }
/*     */   
/*     */   public void setIsMain(String isMain) {
/* 251 */     this.w = isMain;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getIsMain()
/*     */   {
/* 259 */     return this.w;
/*     */   }
/*     */   
/*     */   public void setCreateBy(String createBy) {
/* 263 */     this.createBy = createBy;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getCreateBy()
/*     */   {
/* 271 */     return this.createBy;
/*     */   }
/*     */   
/*     */   public void setCreateTime(Date createTime) {
/* 275 */     this.createTime = createTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getCreateTime()
/*     */   {
/* 283 */     return this.createTime;
/*     */   }
/*     */   
/*     */   public void setCreateOrgId(String createOrgId) {
/* 287 */     this.x = createOrgId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getCreateOrgId()
/*     */   {
/* 295 */     return this.x;
/*     */   }
/*     */   
/*     */   public void setUpdateBy(String updateBy) {
/* 299 */     this.updateBy = updateBy;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getUpdateBy()
/*     */   {
/* 307 */     return this.updateBy;
/*     */   }
/*     */   
/*     */   public void setUpdateTime(Date updateTime) {
/* 311 */     this.updateTime = updateTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getUpdateTime()
/*     */   {
/* 319 */     return this.updateTime;
/*     */   }
/*     */   
/*     */   public void setSupportMobile(Integer supportMobile) {
/* 323 */     this.supportMobile = supportMobile;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Integer getSupportMobile()
/*     */   {
/* 331 */     return this.supportMobile;
/*     */   }
/*     */   
/*     */   public void setDefSetting(String defSetting) {
/* 335 */     this.y = defSetting;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDefSetting()
/*     */   {
/* 343 */     return this.y;
/*     */   }
/*     */   
/*     */   public void setRev(Integer rev) {
/* 347 */     this.z = rev;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Integer getRev()
/*     */   {
/* 355 */     return this.z;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 361 */     return 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 382 */       new ToStringBuilder(this).append("id", this.id).append("name", this.name).append("key", this.key).append("desc", this.desc).append("typeId", this.r).append("status", this.status).append("actDefId", this.s).append("actModelId", this.t).append("actDeployId", this.u).append("version", this.version).append("mainDefId", this.v).append("isMain", this.w).append("createBy", this.createBy).append("createTime", this.createTime).append("createOrgId", this.x).append("updateBy", this.updateBy).append("updateTime", this.updateTime).append("supportMobile", this.supportMobile).append("defSetting", this.y).append("rev", this.z).toString();
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\model\BpmDefinition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */