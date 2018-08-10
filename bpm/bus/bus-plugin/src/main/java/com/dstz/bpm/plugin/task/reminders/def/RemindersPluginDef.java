/*     */ package com.dstz.bpm.plugin.task.reminders.def;
/*     */ 
/*     */ import com.dstz.base.core.util.BeanUtils;
/*     */ import com.dstz.bpm.engine.plugin.plugindef.AbstractBpmTaskPluginDef;
/*     */ import com.dstz.bpm.plugin.task.reminders.entity.Reminders;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class RemindersPluginDef
/*     */   extends AbstractBpmTaskPluginDef
/*     */ {
/*     */   private List<Reminder> K;
/*     */   
/*     */   public List<Reminder> getReminderList()
/*     */   {
/*  16 */     return this.K;
/*     */   }
/*     */   
/*     */   public void setReminderList(List<Reminder> reminderList) {
/*  20 */     this.K = reminderList;
/*     */   }
/*     */   
/*     */   public static RemindersPluginDef a(Reminders remindersExt)
/*     */   {
/*  25 */     List<com.dstz.bpm.plugin.task.reminders.entity.Reminder> reminderExtList = remindersExt.getReminder();
/*  26 */     if (BeanUtils.isEmpty(reminderExtList)) { return null;
/*     */     }
/*  28 */     List<Reminder> reminderList = new ArrayList();
/*  29 */     for (com.dstz.bpm.plugin.task.reminders.entity.Reminder r : reminderExtList) {
/*  30 */       Reminder reminder = a(r);
/*  31 */       reminderList.add(reminder);
/*     */     }
/*  33 */     RemindersPluginDef def = new RemindersPluginDef();
/*  34 */     def.setReminderList(reminderList);
/*  35 */     return def;
/*     */   }
/*     */   
/*     */   public static Reminders a(RemindersPluginDef reminders) {
/*  39 */     List<Reminder> reminderList = reminders.getReminderList();
/*  40 */     if (BeanUtils.isEmpty(reminderList)) { return null;
/*     */     }
/*  42 */     List<com.dstz.bpm.plugin.task.reminders.entity.Reminder> reminderExtList = new ArrayList();
/*  43 */     for (Reminder r : reminderList) {
/*  44 */       com.dstz.bpm.plugin.task.reminders.entity.Reminder reminder = a(r);
/*  45 */       reminderExtList.add(reminder);
/*     */     }
/*  47 */     Reminders remindersExt = new Reminders();
/*  48 */     remindersExt.setReminder(reminderExtList);
/*  49 */     return remindersExt;
/*     */   }
/*     */   
/*     */   public static Reminder a(com.dstz.bpm.plugin.task.reminders.entity.Reminder reminderExt)
/*     */   {
/*  54 */     Reminder reminder = new Reminder();
/*  55 */     reminder.setCondition(reminderExt.getCondition());
/*  56 */     reminder.setDateType(reminderExt.getDateType());
/*  57 */     reminder.setDueAction(reminderExt.getDueAction());
/*  58 */     reminder.setDueScript(reminderExt.getDueScript());
/*  59 */     reminder.setDueTime(reminderExt.getDueTime());
/*  60 */     reminder.setHtmlMsg(reminderExt.getHtmlMsg());
/*  61 */     reminder.setIsSendMsg(reminderExt.d());
/*  62 */     reminder.setMsgCount(reminderExt.getMsgCount());
/*  63 */     reminder.setMsgType(reminderExt.getMsgType());
/*  64 */     reminder.setMsgInterval(reminderExt.getMsgInterval());
/*  65 */     reminder.setMsgSendTime(reminderExt.getMsgSendTime());
/*  66 */     reminder.setName(reminderExt.getName());
/*  67 */     reminder.setRelNodeId(reminderExt.getRelNodeId());
/*  68 */     reminder.setRelNodeEvent(reminderExt.getRelNodeEvent());
/*  69 */     reminder.setPlainMsg(reminderExt.getPlainMsg());
/*     */     
/*  71 */     if (BeanUtils.isEmpty(reminderExt.getWarningSet())) { return reminder;
/*     */     }
/*  73 */     List<WarningSet> warningSetList = new ArrayList();
/*  74 */     for (com.dstz.bpm.plugin.task.reminders.entity.WarningSet warningSetExt : reminderExt.getWarningSet())
/*     */     {
/*  76 */       WarningSet warningSet = new WarningSet();
/*  77 */       warningSet.setLevel(warningSetExt.getLevel());
/*  78 */       warningSet.setWarnName(warningSetExt.getWarnName());
/*  79 */       warningSet.setWarnTime(warningSetExt.getWarnTime());
/*  80 */       warningSetList.add(warningSet);
/*     */     }
/*  82 */     reminder.setWarningSetList(warningSetList);
/*  83 */     return reminder;
/*     */   }
/*     */   
/*     */   public static com.dstz.bpm.plugin.task.reminders.entity.Reminder a(Reminder reminder)
/*     */   {
/*  88 */     com.dstz.bpm.plugin.task.reminders.entity.Reminder reminderExt = new com.dstz.bpm.plugin.task.reminders.entity.Reminder();
/*  89 */     reminderExt.setCondition(reminder.getCondition());
/*  90 */     reminderExt.setDateType(reminder.getDateType());
/*  91 */     reminderExt.setDueAction(reminder.getDueAction());
/*  92 */     reminderExt.setDueScript(reminder.getDueScript());
/*  93 */     reminderExt.setDueTime(reminder.getDueTime());
/*  94 */     reminderExt.setHtmlMsg(reminder.getHtmlMsg());
/*  95 */     reminderExt.setSendMsg(reminder.getIsSendMsg());
/*  96 */     reminderExt.setMsgCount(reminder.getMsgCount());
/*  97 */     reminderExt.setMsgType(reminder.getMsgType());
/*  98 */     reminderExt.setMsgInterval(reminder.getMsgInterval());
/*  99 */     reminderExt.setMsgSendTime(reminder.getMsgSendTime());
/* 100 */     reminderExt.setName(reminder.getName());
/* 101 */     reminderExt.setRelNodeId(reminder.getRelNodeId());
/* 102 */     reminderExt.setRelNodeEvent(reminder.getRelNodeEvent());
/* 103 */     reminderExt.setPlainMsg(reminder.getPlainMsg());
/*     */     
/* 105 */     if (BeanUtils.isEmpty(reminder.getWarningSetList())) { return reminderExt;
/*     */     }
/* 107 */     List<com.dstz.bpm.plugin.task.reminders.entity.WarningSet> warningSetExtList = new ArrayList();
/* 108 */     for (WarningSet warningSet : reminder.getWarningSetList()) {
/* 109 */       com.dstz.bpm.plugin.task.reminders.entity.WarningSet warningSetExt = new com.dstz.bpm.plugin.task.reminders.entity.WarningSet();
/* 110 */       warningSetExt.setLevel(warningSet.getLevel());
/* 111 */       warningSetExt.setWarnName(warningSet.getWarnName());
/* 112 */       warningSetExt.setWarnTime(warningSet.getWarnTime());
/*     */       
/* 114 */       warningSetExtList.add(warningSetExt);
/*     */     }
/*     */     
/* 117 */     reminderExt.setWarningSet(warningSetExtList);
/* 118 */     return reminderExt;
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\task\reminders\def\RemindersPluginDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */