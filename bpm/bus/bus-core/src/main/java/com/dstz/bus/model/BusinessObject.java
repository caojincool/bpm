/*     */ package com.dstz.bus.model;
/*     */ 
/*     */ import com.dstz.base.core.model.BaseModel;
/*     */ import com.dstz.base.core.util.JsonUtil;
/*     */ import com.dstz.bus.api.constant.RightsType;
/*     */ import com.dstz.bus.api.model.IBusinessObject;
/*     */ import com.dstz.bus.api.model.permission.IBusObjPermission;
/*     */ import com.dstz.bus.model.permission.BusColumnPermission;
/*     */ import com.dstz.bus.model.permission.BusObjPermission;
/*     */ import com.dstz.bus.model.permission.BusTablePermission;
/*     */ import java.util.Map;
/*     */ import org.hibernate.validator.constraints.NotEmpty;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BusinessObject
/*     */   extends BaseModel
/*     */   implements IBusinessObject
/*     */ {
/*     */   @NotEmpty
/*     */   private String key;
/*     */   @NotEmpty
/*     */   private String name;
/*     */   private String desc;
/*     */   @NotEmpty
/*     */   private String C;
/*     */   private String groupId;
/*     */   private String groupName;
/*     */   @NotEmpty
/*     */   private String D;
/*     */   private BusTableRel E;
/*     */   private BusObjPermission F;
/*     */   
/*     */   public String getKey()
/*     */   {
/*  69 */     return this.key;
/*     */   }
/*     */   
/*     */   public void setKey(String key) {
/*  73 */     this.key = key;
/*     */   }
/*     */   
/*     */   public String getName()
/*     */   {
/*  78 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  82 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getDesc()
/*     */   {
/*  87 */     return this.desc;
/*     */   }
/*     */   
/*     */   public void setDesc(String desc) {
/*  91 */     this.desc = desc;
/*     */   }
/*     */   
/*     */   public String getRelationJson() {
/*  95 */     return this.C;
/*     */   }
/*     */   
/*     */   public void setRelationJson(String relationJson) {
/*  99 */     this.C = relationJson;
/* 100 */     this.E = ((BusTableRel)JsonUtil.parseObject(relationJson, BusTableRel.class));
/*     */   }
/*     */   
/*     */   public String getGroupId() {
/* 104 */     return this.groupId;
/*     */   }
/*     */   
/*     */   public void setGroupId(String groupId) {
/* 108 */     this.groupId = groupId;
/*     */   }
/*     */   
/*     */   public String getGroupName() {
/* 112 */     return this.groupName;
/*     */   }
/*     */   
/*     */   public void setGroupName(String groupName) {
/* 116 */     this.groupName = groupName;
/*     */   }
/*     */   
/*     */   public String getPersistenceType() {
/* 120 */     return this.D;
/*     */   }
/*     */   
/*     */   public void setPersistenceType(String persistenceType) {
/* 124 */     this.D = persistenceType;
/*     */   }
/*     */   
/*     */   public BusTableRel getRelation()
/*     */   {
/* 129 */     return this.E;
/*     */   }
/*     */   
/*     */   public void setRelation(BusTableRel relation) {
/* 133 */     this.E = relation;
/* 134 */     this.C = JsonUtil.toJSONString(relation);
/*     */   }
/*     */   
/*     */   public BusObjPermission getPermission()
/*     */   {
/* 139 */     return this.F;
/*     */   }
/*     */   
/*     */   public void setPermission(IBusObjPermission permission)
/*     */   {
/* 144 */     this.F = ((BusObjPermission)permission);
/*     */   }
/*     */   
/*     */   public boolean haveTableDbEditRights(String tableKey)
/*     */   {
/* 149 */     return a(true, tableKey);
/*     */   }
/*     */   
/*     */   public boolean haveTableDbReadRights(String tableKey)
/*     */   {
/* 154 */     return a(false, tableKey);
/*     */   }
/*     */   
/*     */   public boolean haveColumnDbEditRights(String tableKey, String columnKey)
/*     */   {
/* 159 */     return a(true, tableKey, columnKey);
/*     */   }
/*     */   
/*     */   public boolean haveColumnDbReadRights(String tableKey, String columnKey)
/*     */   {
/* 164 */     return a(false, tableKey, columnKey);
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
/*     */   private boolean a(boolean isEdit, String tableKey, String columnKey)
/*     */   {
/* 181 */     RightsType rightsType = null;
/* 182 */     if (this.F != null) {
/* 183 */       BusTablePermission tablePermission = (BusTablePermission)this.F.getTableMap().get(tableKey);
/* 184 */       if (tablePermission != null) {
/* 185 */         BusColumnPermission columnPermission = (BusColumnPermission)tablePermission.getColumnMap().get(columnKey);
/* 186 */         if (columnPermission != null) {
/* 187 */           rightsType = RightsType.getByKey(columnPermission.getResult());
/*     */         }
/*     */       }
/*     */     }
/* 191 */     if (rightsType == null) {
/* 192 */       rightsType = RightsType.getDefalut();
/*     */     }
/* 194 */     if (isEdit) {
/* 195 */       return rightsType.isDbEditable();
/*     */     }
/* 197 */     return rightsType.isDbReadable();
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
/*     */   private boolean a(boolean isEdit, String tableKey)
/*     */   {
/* 213 */     if (this.F != null) {
/* 214 */       BusTablePermission tablePermission = (BusTablePermission)this.F.getTableMap().get(tableKey);
/* 215 */       if (tablePermission != null) {
/* 216 */         for (BusinessColumn column : this.E.a(tableKey).getTable().getColumnsWithoutPk()) {
/* 217 */           if ((isEdit) && (haveColumnDbEditRights(tableKey, column.getKey()))) {
/* 218 */             return true;
/*     */           }
/* 220 */           if ((!isEdit) && (haveColumnDbReadRights(tableKey, column.getKey()))) {
/* 221 */             return true;
/*     */           }
/*     */         }
/* 224 */         return false;
/*     */       }
/*     */     }
/* 227 */     if (isEdit) {
/* 228 */       return RightsType.getDefalut().isDbEditable();
/*     */     }
/* 230 */     return RightsType.getDefalut().isDbReadable();
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\model\BusinessObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */