package com.dstz.bpm.plugin.task.reminders.entity;

import com.dstz.bpm.plugin.task.reminders.entity.Reminder;
import com.dstz.bpm.plugin.task.reminders.entity.Reminders;
import com.dstz.bpm.plugin.task.reminders.entity.WarningSet;
import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
	public Reminder a() {
		return new Reminder();
	}

	public WarningSet b() {
		return new WarningSet();
	}

	public Reminders c() {
		return new Reminders();
	}
}