/*     */ package com.dstz.bus.model;
/*     */ 
/*     */ import com.alibaba.fastjson.JSON;
/*     */ import com.alibaba.fastjson.JSONObject;
/*     */ import com.dstz.base.api.constant.ColumnType;
/*     */ import com.dstz.base.api.model.IBaseModel;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.base.core.util.time.DateFormatUtil;
/*     */ import com.dstz.base.db.model.table.Column;
/*     */ import com.dstz.bus.api.model.IBusinessColumn;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.RoundingMode;
/*     */ import java.util.Date;
/*     */ import javax.validation.Valid;
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
/*     */ public class BusinessColumn
/*     */   extends Column
/*     */   implements IBaseModel, IBusinessColumn
/*     */ {
/*     */   @NotEmpty
/*     */   private String id;
/*     */   @NotEmpty
/*     */   private String key;
/*     */   @NotEmpty
/*     */   private String y;
/*     */   @Valid
/*     */   private BusColumnCtrl z;
/*     */   private BusinessTable v;
/*     */   
/*     */   public String getId()
/*     */   {
/*  51 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/*  55 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getKey() {
/*  59 */     return this.key;
/*     */   }
/*     */   
/*     */   public void setKey(String key) {
/*  63 */     this.key = key;
/*     */   }
/*     */   
/*     */   public BusColumnCtrl getCtrl() {
/*  67 */     return this.z;
/*     */   }
/*     */   
/*     */   public void setCtrl(BusColumnCtrl ctrl) {
/*  71 */     this.z = ctrl;
/*     */   }
/*     */   
/*     */   public String getTableId() {
/*  75 */     return this.y;
/*     */   }
/*     */   
/*     */   public void setTableId(String tableId) {
/*  79 */     this.y = tableId;
/*     */   }
/*     */   
/*     */   public BusinessTable getTable()
/*     */   {
/*  84 */     return this.v;
/*     */   }
/*     */   
/*     */   public void setTable(BusinessTable table) {
/*  88 */     this.v = table;
/*     */   }
/*     */   
/*     */   public Object initValue()
/*     */   {
/*  93 */     return value(this.defaultValue);
/*     */   }
/*     */   
/*     */   public Object value(String str)
/*     */   {
/*  98 */     if (StringUtil.isEmpty(str)) {
/*  99 */       return null;
/*     */     }
/* 101 */     Object value = null;
/* 102 */     if ((ColumnType.VARCHAR.equalsWithKey(this.type)) || (ColumnType.CLOB.equalsWithKey(this.type))) {
/* 103 */       value = str;
/* 104 */     } else if (ColumnType.NUMBER.equalsWithKey(this.type)) {
/* 105 */       BigDecimal bigDecimal = new BigDecimal(str);
/*     */       
/* 107 */       value = bigDecimal.setScale(getDecimal(), RoundingMode.HALF_UP);
/* 108 */     } else if (ColumnType.DATE.equalsWithKey(this.type)) {
/* 109 */       JSONObject config = JSON.parseObject(getCtrl().getConfig());
/* 110 */       value = DateFormatUtil.parse(str, config.getString("format"));
/*     */     }
/*     */     
/* 113 */     return value;
/*     */   }
/*     */   
/*     */   public Date getCreateTime()
/*     */   {
/* 118 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setCreateTime(Date createTime) {}
/*     */   
/*     */ 
/*     */   public String getCreateBy()
/*     */   {
/* 128 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setCreateBy(String createBy) {}
/*     */   
/*     */ 
/*     */   public Date getUpdateTime()
/*     */   {
/* 138 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setUpdateTime(Date updateTime) {}
/*     */   
/*     */ 
/*     */   public String getUpdateBy()
/*     */   {
/* 148 */     return null;
/*     */   }
/*     */   
/*     */   public void setUpdateBy(String updateBy) {}
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\model\BusinessColumn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */