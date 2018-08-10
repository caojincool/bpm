/*     */ package com.dstz.bpm.engine.model;
/*     */ 
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.base.core.util.time.DateFormatUtil;
/*     */ import com.dstz.bpm.api.model.def.BpmVariableDef;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultBpmVariableDef
/*     */   implements BpmVariableDef
/*     */ {
/*  16 */   private String R = "";
/*     */   
/*  18 */   private String name = "";
/*     */   
/*  20 */   private String key = "";
/*     */   
/*  22 */   private String bf = "string";
/*     */   
/*  24 */   private Object bg = "";
/*     */   
/*  26 */   private boolean isRequired = false;
/*     */   
/*  28 */   private String description = "";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultBpmVariableDef() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Object getValue(String dataType, String value)
/*     */   {
/*  43 */     if ("double".equals(dataType))
/*  44 */       return new Double(value);
/*  45 */     if ("float".equals(dataType))
/*  46 */       return new Float(value);
/*  47 */     if ("int".equals(dataType)) {
/*  48 */       if ((value == null) || (StringUtil.isEmpty(value))) {
/*  49 */         return Integer.valueOf(0);
/*     */       }
/*  51 */       return new Integer(value); }
/*  52 */     if ("date".equals(dataType)) {
/*  53 */       return DateFormatUtil.parse(value, new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" });
/*     */     }
/*     */     
/*  56 */     return value;
/*     */   }
/*     */   
/*     */   public DefaultBpmVariableDef(String name, String varKey, String dataType, String defaultVal, boolean isRequired, String description)
/*     */   {
/*  61 */     this.name = name;
/*  62 */     this.key = varKey;
/*  63 */     this.bf = dataType;
/*  64 */     this.bg = getValue(dataType, defaultVal);
/*  65 */     this.isRequired = isRequired;
/*  66 */     this.description = description;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  70 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  74 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getKey() {
/*  78 */     return this.key;
/*     */   }
/*     */   
/*     */   public void setKey(String varKey) {
/*  82 */     this.key = varKey;
/*     */   }
/*     */   
/*     */   public String getDataType() {
/*  86 */     return this.bf;
/*     */   }
/*     */   
/*     */   public void setDataType(String dataType) {
/*  90 */     this.bf = dataType;
/*     */   }
/*     */   
/*     */   public Object getDefaultVal() {
/*  94 */     return this.bg;
/*     */   }
/*     */   
/*     */   public void setDefaultVal(Object defaultVal) {
/*  98 */     this.bg = defaultVal;
/*     */   }
/*     */   
/*     */   public void setDefaultVal(String defaulVal2) {
/* 102 */     this.bg = getValue(this.bf, defaulVal2);
/*     */   }
/*     */   
/*     */   public boolean getIsRequired() {
/* 106 */     return this.isRequired;
/*     */   }
/*     */   
/*     */   public void setIsRequired(boolean isRequired) {
/* 110 */     this.isRequired = isRequired;
/*     */   }
/*     */   
/* 113 */   public boolean isRequired() { return this.isRequired; }
/*     */   
/*     */   public void setRequired(boolean isRequired)
/*     */   {
/* 117 */     this.isRequired = isRequired;
/*     */   }
/*     */   
/* 120 */   public String getDescription() { return this.description == null ? "" : this.description; }
/*     */   
/*     */   public void setDescription(String description)
/*     */   {
/* 124 */     this.description = description;
/*     */   }
/*     */   
/*     */   public String getNodeId()
/*     */   {
/* 129 */     return this.R;
/*     */   }
/*     */   
/*     */   public void setNodeId(String nodeId)
/*     */   {
/* 134 */     this.R = nodeId;
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\model\DefaultBpmVariableDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */