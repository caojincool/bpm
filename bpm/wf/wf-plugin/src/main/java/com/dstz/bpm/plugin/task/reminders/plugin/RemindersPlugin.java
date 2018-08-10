package com.dstz.bpm.plugin.task.reminders.plugin;

import com.dstz.base.core.util.StringUtil;
import com.dstz.bpm.api.constant.EventType;
import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractBpmTaskPlugin;
import com.dstz.bpm.engine.plugin.session.BpmTaskPluginSession;
import com.dstz.bpm.plugin.task.reminders.def.Reminder;
import com.dstz.bpm.plugin.task.reminders.def.RemindersPluginDef;
import com.dstz.sys.api.groovy.IGroovyScriptEngine;
import java.util.List;
import javax.annotation.Resource;

public class RemindersPlugin extends AbstractBpmTaskPlugin<BpmTaskPluginSession, RemindersPluginDef> {
	@Resource
	IGroovyScriptEngine groovyScriptEngine;

	public Void a(BpmTaskPluginSession pluginSession, RemindersPluginDef pluginDef) {
		if (pluginSession.getEventType() == EventType.TASK_COMPLETE_EVENT) {
			return null;
		}
		RemindersPluginDef reminderDef = pluginDef;
		List reminderList = reminderDef.getReminderList();
		for (Reminder reminder : reminderList) {
			this.a(reminder, pluginSession);
		}
		return null;
	}

	private void a(Reminder reminder, BpmTaskPluginSession pluginSession) {
		Object object;
		String condition = reminder.getCondition();
		if (StringUtil.isNotEmpty((String) condition) && (object = null) instanceof Boolean
				&& !((Boolean) object).booleanValue()) {
			return;
		}
	}

	public Object execute(Object object, Object object2) {
		return this.a((BpmTaskPluginSession) object, (RemindersPluginDef) object2);
	}
}