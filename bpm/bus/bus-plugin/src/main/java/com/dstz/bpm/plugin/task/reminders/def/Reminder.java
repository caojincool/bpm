/*     */ package com.dstz.bpm.plugin.task.reminders.def;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Reminder
/*     */ {
/*     */   public static final String n = "create";
/*     */   public static final String o = "complete";
/*     */   public static final String p = "worktime";
/*     */   public static final String q = "caltime";
/*     */   public static final String r = "no-action";
/*     */   public static final String s = "auto-next";
/*     */   public static final String t = "end-process";
/*     */   public static final String u = "call-method";
/*     */   protected String name;
/*     */   protected String v;
/*     */   protected String w;
/*     */   protected String x;
/*     */   protected Integer y;
/*     */   protected String z;
/*     */   protected Boolean A;
/*     */   protected Integer B;
/*     */   protected Integer C;
/*     */   protected String c;
/*     */   protected Integer D;
/*     */   protected String condition;
/*     */   protected String E;
/*     */   protected String F;
/*     */   protected String G;
/*     */   protected List<WarningSet> H;
/*     */   
/*     */   public String getName()
/*     */   {
/*  60 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  64 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getRelNodeId() {
/*  68 */     return this.v;
/*     */   }
/*     */   
/*     */   public void setRelNodeId(String relNodeId) {
/*  72 */     this.v = relNodeId;
/*     */   }
/*     */   
/*     */   public String getMsgType() {
/*  76 */     return this.c;
/*     */   }
/*     */   
/*     */   public void setMsgType(String msgType) {
/*  80 */     this.c = msgType;
/*     */   }
/*     */   
/*     */   public String getRelNodeEvent() {
/*  84 */     return this.w;
/*     */   }
/*     */   
/*     */   public void setRelNodeEvent(String relNodeEvent) {
/*  88 */     this.w = relNodeEvent;
/*     */   }
/*     */   
/*     */   public String getDateType() {
/*  92 */     return this.x;
/*     */   }
/*     */   
/*     */   public void setDateType(String dateType) {
/*  96 */     this.x = dateType;
/*     */   }
/*     */   
/*     */   public Integer getDueTime() {
/* 100 */     return this.y;
/*     */   }
/*     */   
/*     */   public void setDueTime(Integer dueTime) {
/* 104 */     this.y = dueTime;
/*     */   }
/*     */   
/*     */   public String getDueAction() {
/* 108 */     return this.z;
/*     */   }
/*     */   
/*     */   public void setDueAction(String dueAction) {
/* 112 */     this.z = dueAction;
/*     */   }
/*     */   
/*     */   public Boolean getIsSendMsg() {
/* 116 */     return this.A;
/*     */   }
/*     */   
/*     */   public void setIsSendMsg(Boolean isSendMsg) {
/* 120 */     this.A = isSendMsg;
/*     */   }
/*     */   
/*     */   public Integer getMsgSendTime() {
/* 124 */     return this.B;
/*     */   }
/*     */   
/*     */   public void setMsgSendTime(Integer msgSendTime) {
/* 128 */     this.B = msgSendTime;
/*     */   }
/*     */   
/*     */   public Integer getMsgCount() {
/* 132 */     return this.C;
/*     */   }
/*     */   
/*     */   public void setMsgCount(Integer msgCount) {
/* 136 */     this.C = msgCount;
/*     */   }
/*     */   
/*     */   public Integer getMsgInterval() {
/* 140 */     return this.D;
/*     */   }
/*     */   
/*     */   public void setMsgInterval(Integer msgInterval) {
/* 144 */     this.D = msgInterval;
/*     */   }
/*     */   
/*     */   public String getCondition() {
/* 148 */     return this.condition;
/*     */   }
/*     */   
/*     */   public void setCondition(String condition) {
/* 152 */     this.condition = condition;
/*     */   }
/*     */   
/*     */   public String getHtmlMsg() {
/* 156 */     return this.E;
/*     */   }
/*     */   
/*     */   public void setHtmlMsg(String htmlMsg) {
/* 160 */     this.E = htmlMsg;
/*     */   }
/*     */   
/*     */   public String getPlainMsg() {
/* 164 */     return this.F;
/*     */   }
/*     */   
/*     */   public void setPlainMsg(String plainMsg) {
/* 168 */     this.F = plainMsg;
/*     */   }
/*     */   
/*     */   public String getDueScript() {
/* 172 */     return this.G;
/*     */   }
/*     */   
/*     */   public void setDueScript(String dueScript) {
/* 176 */     this.G = dueScript;
/*     */   }
/*     */   
/*     */   public List<WarningSet> getWarningSetList() {
/* 180 */     return this.H;
/*     */   }
/*     */   
/*     */   public void setWarningSetList(List<WarningSet> warningSetList) {
/* 184 */     this.H = warningSetList;
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\task\reminders\def\Reminder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */