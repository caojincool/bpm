/*     */ package com.dstz.bpm.plugin.task.reminders.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */ @XmlType(name="", propOrder={"condition", "htmlMsg", "plainMsg", "dueScript", "warningSet"})
/*     */ @XmlRootElement(name="reminder")
/*     */ public class Reminder
/*     */ {
/*     */   protected String condition;
/*     */   protected String E;
/*     */   protected String F;
/*     */   protected String G;
/*     */   protected List<WarningSet> O;
/*     */   @XmlAttribute(name="name")
/*     */   protected String name;
/*     */   @XmlAttribute(name="relNodeId")
/*     */   protected String v;
/*     */   @XmlAttribute(name="relNodeEvent")
/*     */   protected String w;
/*     */   @XmlAttribute(name="dateType")
/*     */   protected String x;
/*     */   @XmlAttribute(name="dueTime")
/*     */   protected Integer y;
/*     */   @XmlAttribute(name="dueAction")
/*     */   protected String z;
/*     */   @XmlAttribute(name="sendMsg")
/*     */   protected Boolean P;
/*     */   @XmlAttribute(name="msgSendTime")
/*     */   protected Integer B;
/*     */   @XmlAttribute(name="msgCount")
/*     */   protected Integer C;
/*     */   @XmlAttribute(name="msgType")
/*     */   protected String c;
/*     */   @XmlAttribute(name="msgInterval")
/*     */   protected Integer D;
/*     */   
/*     */   public String getCondition()
/*     */   {
/*  94 */     return this.condition;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCondition(String value)
/*     */   {
/* 104 */     this.condition = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getHtmlMsg()
/*     */   {
/* 114 */     return this.E;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setHtmlMsg(String value)
/*     */   {
/* 124 */     this.E = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getPlainMsg()
/*     */   {
/* 134 */     return this.F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPlainMsg(String value)
/*     */   {
/* 144 */     this.F = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDueScript()
/*     */   {
/* 154 */     return this.G;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDueScript(String value)
/*     */   {
/* 164 */     this.G = value;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<WarningSet> getWarningSet()
/*     */   {
/* 188 */     if (this.O == null) {
/* 189 */       this.O = new ArrayList();
/*     */     }
/* 191 */     return this.O;
/*     */   }
/*     */   
/*     */   public void setWarningSet(List<WarningSet> warningSetExtList) {
/* 195 */     this.O = warningSetExtList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/* 205 */     return this.name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setName(String value)
/*     */   {
/* 215 */     this.name = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getRelNodeId()
/*     */   {
/* 225 */     return this.v;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRelNodeId(String value)
/*     */   {
/* 235 */     this.v = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getRelNodeEvent()
/*     */   {
/* 245 */     return this.w;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRelNodeEvent(String value)
/*     */   {
/* 255 */     this.w = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDateType()
/*     */   {
/* 265 */     return this.x;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDateType(String value)
/*     */   {
/* 275 */     this.x = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Integer getDueTime()
/*     */   {
/* 285 */     return this.y;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDueTime(Integer value)
/*     */   {
/* 295 */     this.y = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDueAction()
/*     */   {
/* 305 */     return this.z;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDueAction(String value)
/*     */   {
/* 315 */     this.z = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Boolean d()
/*     */   {
/* 325 */     return this.P;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSendMsg(Boolean value)
/*     */   {
/* 335 */     this.P = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Integer getMsgSendTime()
/*     */   {
/* 345 */     return this.B;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMsgSendTime(Integer value)
/*     */   {
/* 355 */     this.B = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Integer getMsgCount()
/*     */   {
/* 365 */     return this.C;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMsgCount(Integer value)
/*     */   {
/* 375 */     this.C = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getMsgType()
/*     */   {
/* 385 */     return this.c;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMsgType(String value)
/*     */   {
/* 395 */     this.c = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Integer getMsgInterval()
/*     */   {
/* 405 */     return this.D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMsgInterval(Integer value)
/*     */   {
/* 415 */     this.D = value;
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\task\reminders\entity\Reminder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */