package com.dstz.bpm.engine.plugin.session.impl;

import com.dstz.bpm.api.model.task.IBpmTask;
import com.dstz.bpm.engine.plugin.session.BpmTaskPluginSession;
import com.dstz.bpm.engine.plugin.session.impl.DefaultBpmExecutionPluginSession;

public class DefaultBpmTaskPluginSession extends DefaultBpmExecutionPluginSession implements BpmTaskPluginSession {
	private static final long serialVersionUID = 1L;
	private IBpmTask ao;

	public IBpmTask getBpmTask() {
		return this.ao;
	}

	public void setBpmTask(IBpmTask bpmTask) {
		this.put("bpmTask", bpmTask);
		this.ao = bpmTask;
	}
}