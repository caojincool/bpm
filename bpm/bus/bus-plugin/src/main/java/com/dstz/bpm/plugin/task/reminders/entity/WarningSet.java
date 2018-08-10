/*     */ package com.dstz.bpm.plugin.task.reminders.entity;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlRootElement;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="")
/*     */ @XmlRootElement(name="warningSet")
/*     */ public class WarningSet
/*     */ {
/*     */   @XmlAttribute(name="warnName")
/*     */   protected String L;
/*     */   @XmlAttribute(name="warnTime")
/*     */   protected Integer M;
/*     */   @XmlAttribute(name="level")
/*     */   protected Integer N;
/*     */   
/*     */   public String getWarnName()
/*     */   {
/*  50 */     return this.L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setWarnName(String value)
/*     */   {
/*  60 */     this.L = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Integer getWarnTime()
/*     */   {
/*  70 */     return this.M;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setWarnTime(Integer value)
/*     */   {
/*  80 */     this.M = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Integer getLevel()
/*     */   {
/*  90 */     return this.N;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLevel(Integer value)
/*     */   {
/* 100 */     this.N = value;
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\task\reminders\entity\WarningSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */