package com.dstz.bpm.plugin.task.reminders.def;

import com.dstz.base.core.util.BeanUtils;
import com.dstz.bpm.engine.plugin.plugindef.AbstractBpmTaskPluginDef;
import com.dstz.bpm.plugin.task.reminders.def.Reminder;
import com.dstz.bpm.plugin.task.reminders.def.WarningSet;
import com.dstz.bpm.plugin.task.reminders.entity.Reminders;
import java.util.ArrayList;
import java.util.List;

public class RemindersPluginDef extends AbstractBpmTaskPluginDef {
	private List<Reminder> K;

	public List<Reminder> getReminderList() {
		return this.K;
	}

	public void setReminderList(List<Reminder> reminderList) {
		this.K = reminderList;
	}

	public static RemindersPluginDef a(Reminders remindersExt) {
		List<com.dstz.bpm.plugin.task.reminders.entity.Reminder> reminderExtList = remindersExt.getReminder();
		if (BeanUtils.isEmpty((Object) reminderExtList)) {
			return null;
		}
		ArrayList<Reminder> reminderList = new ArrayList<Reminder>();
		for (com.dstz.bpm.plugin.task.reminders.entity.Reminder r : reminderExtList) {
			Reminder reminder = RemindersPluginDef.a(r);
			reminderList.add(reminder);
		}
		RemindersPluginDef def = new RemindersPluginDef();
		def.setReminderList(reminderList);
		return def;
	}

	public static Reminders a(RemindersPluginDef reminders) {
		List<Reminder> reminderList = reminders.getReminderList();
		if (BeanUtils.isEmpty(reminderList)) {
			return null;
		}
		ArrayList<com.dstz.bpm.plugin.task.reminders.entity.Reminder> reminderExtList = new ArrayList<com.dstz.bpm.plugin.task.reminders.entity.Reminder>();
		for (Reminder r : reminderList) {
			com.dstz.bpm.plugin.task.reminders.entity.Reminder reminder = RemindersPluginDef.a(r);
			reminderExtList.add(reminder);
		}
		Reminders remindersExt = new Reminders();
		remindersExt.setReminder(reminderExtList);
		return remindersExt;
	}

	public static Reminder a(com.dstz.bpm.plugin.task.reminders.entity.Reminder reminderExt) {
		Reminder reminder = new Reminder();
		reminder.setCondition(reminderExt.getCondition());
		reminder.setDateType(reminderExt.getDateType());
		reminder.setDueAction(reminderExt.getDueAction());
		reminder.setDueScript(reminderExt.getDueScript());
		reminder.setDueTime(reminderExt.getDueTime());
		reminder.setHtmlMsg(reminderExt.getHtmlMsg());
		reminder.setIsSendMsg(reminderExt.d());
		reminder.setMsgCount(reminderExt.getMsgCount());
		reminder.setMsgType(reminderExt.getMsgType());
		reminder.setMsgInterval(reminderExt.getMsgInterval());
		reminder.setMsgSendTime(reminderExt.getMsgSendTime());
		reminder.setName(reminderExt.getName());
		reminder.setRelNodeId(reminderExt.getRelNodeId());
		reminder.setRelNodeEvent(reminderExt.getRelNodeEvent());
		reminder.setPlainMsg(reminderExt.getPlainMsg());
		if (BeanUtils.isEmpty((Object) reminderExt.getWarningSet())) {
			return reminder;
		}
		ArrayList<WarningSet> warningSetList = new ArrayList<WarningSet>();
		for (com.dstz.bpm.plugin.task.reminders.entity.WarningSet warningSetExt : reminderExt.getWarningSet()) {
			WarningSet warningSet = new WarningSet();
			warningSet.setLevel(warningSetExt.getLevel());
			warningSet.setWarnName(warningSetExt.getWarnName());
			warningSet.setWarnTime(warningSetExt.getWarnTime());
			warningSetList.add(warningSet);
		}
		reminder.setWarningSetList(warningSetList);
		return reminder;
	}

	public static com.dstz.bpm.plugin.task.reminders.entity.Reminder a(Reminder reminder) {
		com.dstz.bpm.plugin.task.reminders.entity.Reminder reminderExt = new com.dstz.bpm.plugin.task.reminders.entity.Reminder();
		reminderExt.setCondition(reminder.getCondition());
		reminderExt.setDateType(reminder.getDateType());
		reminderExt.setDueAction(reminder.getDueAction());
		reminderExt.setDueScript(reminder.getDueScript());
		reminderExt.setDueTime(reminder.getDueTime());
		reminderExt.setHtmlMsg(reminder.getHtmlMsg());
		reminderExt.setSendMsg(reminder.getIsSendMsg());
		reminderExt.setMsgCount(reminder.getMsgCount());
		reminderExt.setMsgType(reminder.getMsgType());
		reminderExt.setMsgInterval(reminder.getMsgInterval());
		reminderExt.setMsgSendTime(reminder.getMsgSendTime());
		reminderExt.setName(reminder.getName());
		reminderExt.setRelNodeId(reminder.getRelNodeId());
		reminderExt.setRelNodeEvent(reminder.getRelNodeEvent());
		reminderExt.setPlainMsg(reminder.getPlainMsg());
		if (BeanUtils.isEmpty((Object) reminder.getWarningSetList())) {
			return reminderExt;
		}
		ArrayList<com.dstz.bpm.plugin.task.reminders.entity.WarningSet> warningSetExtList = new ArrayList<com.dstz.bpm.plugin.task.reminders.entity.WarningSet>();
		for (WarningSet warningSet : reminder.getWarningSetList()) {
			com.dstz.bpm.plugin.task.reminders.entity.WarningSet warningSetExt = new com.dstz.bpm.plugin.task.reminders.entity.WarningSet();
			warningSetExt.setLevel(warningSet.getLevel());
			warningSetExt.setWarnName(warningSet.getWarnName());
			warningSetExt.setWarnTime(warningSet.getWarnTime());
			warningSetExtList.add(warningSetExt);
		}
		reminderExt.setWarningSet(warningSetExtList);
		return reminderExt;
	}
}