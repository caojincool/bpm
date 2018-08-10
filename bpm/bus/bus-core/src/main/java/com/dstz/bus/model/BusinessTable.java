/*     */ package com.dstz.bus.model;
/*     */ 
/*     */ import com.dstz.base.api.model.IBaseModel;
/*     */ import com.dstz.base.db.model.table.Table;
/*     */ import com.dstz.bus.api.constant.BusColumnCtrlType;
/*     */ import com.dstz.bus.api.model.IBusinessColumn;
/*     */ import com.dstz.bus.api.model.IBusinessTable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
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
/*     */ public class BusinessTable
/*     */   extends Table<BusinessColumn>
/*     */   implements IBaseModel, IBusinessTable
/*     */ {
/*     */   @NotEmpty
/*     */   private String id;
/*     */   @NotEmpty
/*     */   private String key;
/*     */   @NotEmpty
/*     */   private String M;
/*     */   @NotEmpty
/*     */   private String dsName;
/*     */   private String groupId;
/*     */   private String groupName;
/*     */   private boolean N;
/*     */   private boolean O;
/*     */   
/*     */   public String getId()
/*     */   {
/*  68 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/*  72 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getKey()
/*     */   {
/*  77 */     return this.key;
/*     */   }
/*     */   
/*     */   public void setKey(String key) {
/*  81 */     this.key = key;
/*     */   }
/*     */   
/*     */   public String getDsKey()
/*     */   {
/*  86 */     return this.M;
/*     */   }
/*     */   
/*     */   public void setDsKey(String dsKey) {
/*  90 */     this.M = dsKey;
/*     */   }
/*     */   
/*     */   public String getDsName()
/*     */   {
/*  95 */     return this.dsName;
/*     */   }
/*     */   
/*     */   public void setDsName(String dsName) {
/*  99 */     this.dsName = dsName;
/*     */   }
/*     */   
/*     */   public String getGroupId() {
/* 103 */     return this.groupId;
/*     */   }
/*     */   
/*     */   public void setGroupId(String groupId) {
/* 107 */     this.groupId = groupId;
/*     */   }
/*     */   
/*     */   public String getGroupName() {
/* 111 */     return this.groupName;
/*     */   }
/*     */   
/*     */   public void setGroupName(String groupName) {
/* 115 */     this.groupName = groupName;
/*     */   }
/*     */   
/*     */   public boolean isExternal() {
/* 119 */     return this.N;
/*     */   }
/*     */   
/*     */   public void setExternal(boolean external) {
/* 123 */     this.N = external;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getPkName()
/*     */   {
/* 134 */     if (getPkColumn() == null) {
/* 135 */       return "";
/*     */     }
/* 137 */     return ((BusinessColumn)getPkColumn()).getName();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getPkKey()
/*     */   {
/* 148 */     if (getPkColumn() == null) {
/* 149 */       return "";
/*     */     }
/* 151 */     return ((BusinessColumn)getPkColumn()).getKey();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setColumns(List<BusinessColumn> columns)
/*     */   {
/* 162 */     super.setColumns(columns);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<BusinessColumn> getColumns()
/*     */   {
/* 173 */     return super.getColumns();
/*     */   }
/*     */   
/*     */   public boolean a() {
/* 177 */     return this.O;
/*     */   }
/*     */   
/*     */   public void setCreatedTable(boolean createdTable) {
/* 181 */     this.O = createdTable;
/*     */   }
/*     */   
/*     */   public List<BusinessColumn> getColumnsWithoutPk()
/*     */   {
/* 186 */     if (this.columns == null) {
/* 187 */       return null;
/*     */     }
/* 189 */     List<BusinessColumn> columnList = new ArrayList();
/* 190 */     for (BusinessColumn column : this.columns) {
/* 191 */       if (!column.isPrimary()) {
/* 192 */         columnList.add(column);
/*     */       }
/*     */     }
/* 195 */     return columnList;
/*     */   }
/*     */   
/*     */   public List<BusinessColumn> getColumnsWithOutHidden() {
/* 199 */     if (this.columns == null) {
/* 200 */       return null;
/*     */     }
/* 202 */     List<BusinessColumn> columnList = new ArrayList();
/* 203 */     for (BusinessColumn column : this.columns) {
/* 204 */       if ((!column.isPrimary()) && 
/*     */       
/*     */ 
/* 207 */         (column.getCtrl() != null) && (!BusColumnCtrlType.HIDDEN.getKey().equals(column.getCtrl().getType())))
/*     */       {
/*     */ 
/*     */ 
/* 211 */         columnList.add(column);
/*     */       }
/*     */     }
/* 214 */     return columnList;
/*     */   }
/*     */   
/*     */   public Map<String, Object> initDbData()
/*     */   {
/* 219 */     Map<String, Object> map = new HashMap();
/* 220 */     for (IBusinessColumn column : getColumnsWithoutPk()) {
/* 221 */       map.put(column.getName(), column.initValue());
/*     */     }
/* 223 */     return map;
/*     */   }
/*     */   
/*     */   public Map<String, Object> initData()
/*     */   {
/* 228 */     Map<String, Object> map = new HashMap();
/* 229 */     for (IBusinessColumn column : getColumnsWithoutPk()) {
/* 230 */       map.put(column.getKey(), column.initValue());
/*     */     }
/* 232 */     return map;
/*     */   }
/*     */   
/*     */   public BusinessColumn d(String key)
/*     */   {
/* 237 */     for (BusinessColumn column : this.columns) {
/* 238 */       if (key.equals(column.getKey())) {
/* 239 */         return column;
/*     */       }
/*     */     }
/* 242 */     return null;
/*     */   }
/*     */   
/*     */   public Date getCreateTime()
/*     */   {
/* 247 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setCreateTime(Date createTime) {}
/*     */   
/*     */ 
/*     */   public String getCreateBy()
/*     */   {
/* 257 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setCreateBy(String createBy) {}
/*     */   
/*     */ 
/*     */   public Date getUpdateTime()
/*     */   {
/* 267 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setUpdateTime(Date updateTime) {}
/*     */   
/*     */ 
/*     */   public String getUpdateBy()
/*     */   {
/* 277 */     return null;
/*     */   }
/*     */   
/*     */   public void setUpdateBy(String updateBy) {}
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\model\BusinessTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */