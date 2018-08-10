/*     */ package com.dstz.bpm.plugin.usercalc.user.def;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExecutorVar
/*     */ {
/*     */   public static final String ad = "BO";
/*     */   
/*     */   public static final String ae = "flowVar";
/*     */   
/*     */   public static final String af = "user";
/*     */   
/*     */   public static final String ag = "group";
/*     */   
/*  15 */   private String source = "";
/*     */   
/*     */ 
/*  18 */   private String name = "";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  23 */   private String ah = "";
/*     */   
/*     */ 
/*  26 */   private String ai = "";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  31 */   private String aj = "";
/*  32 */   private String dimension = "";
/*     */   
/*     */ 
/*     */   private String value;
/*     */   
/*     */ 
/*     */ 
/*     */   public ExecutorVar() {}
/*     */   
/*     */ 
/*     */   public ExecutorVar(String source, String name, String executorType, String userValType, String groupValType, String dimension)
/*     */   {
/*  44 */     this.source = source;
/*  45 */     this.name = name;
/*  46 */     this.ah = executorType;
/*  47 */     this.ai = userValType;
/*  48 */     this.aj = groupValType;
/*  49 */     this.dimension = dimension;
/*     */   }
/*     */   
/*     */   public String getSource() {
/*  53 */     return this.source;
/*     */   }
/*     */   
/*     */   public void setSource(String source) {
/*  57 */     this.source = source;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  61 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  65 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getExecutorType() {
/*  69 */     return this.ah;
/*     */   }
/*     */   
/*     */   public void setExecutorType(String executorType) {
/*  73 */     this.ah = executorType;
/*     */   }
/*     */   
/*     */   public String getUserValType() {
/*  77 */     return this.ai;
/*     */   }
/*     */   
/*     */   public void setUserValType(String userValType) {
/*  81 */     this.ai = userValType;
/*     */   }
/*     */   
/*     */   public String getGroupValType() {
/*  85 */     return this.aj;
/*     */   }
/*     */   
/*     */   public void setGroupValType(String groupValType) {
/*  89 */     this.aj = groupValType;
/*     */   }
/*     */   
/*     */   public String getDimension() {
/*  93 */     return this.dimension;
/*     */   }
/*     */   
/*     */   public void setDimension(String dimension) {
/*  97 */     this.dimension = dimension;
/*     */   }
/*     */   
/*     */   public String getValue() {
/* 101 */     return this.value;
/*     */   }
/*     */   
/*     */   public void setValue(String value) {
/* 105 */     this.value = value;
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\usercalc\user\def\ExecutorVar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */