/*    */ package com.dstz.bpm.plugin.task.reminders.entity;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlElement;
/*    */ import javax.xml.bind.annotation.XmlRootElement;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="", propOrder={"reminder"})
/*    */ @XmlRootElement(name="reminders")
/*    */ public class Reminders
/*    */ {
/*    */   @XmlElement(required=true)
/*    */   protected List<Reminder> Q;
/*    */   
/*    */   public List<Reminder> getReminder()
/*    */   {
/* 64 */     if (this.Q == null) {
/* 65 */       this.Q = new ArrayList();
/*    */     }
/* 67 */     return this.Q;
/*    */   }
/*    */   
/*    */   public void setReminder(List<Reminder> reminderExtList) {
/* 71 */     this.Q = reminderExtList;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\task\reminders\entity\Reminders.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */