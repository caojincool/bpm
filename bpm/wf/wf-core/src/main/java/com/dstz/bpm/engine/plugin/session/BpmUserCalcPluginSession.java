package com.dstz.bpm.engine.plugin.session;

import com.dstz.bpm.api.model.task.IBpmTask;
import com.dstz.bpm.engine.plugin.session.BpmPluginSession;

public interface BpmUserCalcPluginSession extends BpmPluginSession {
	public Boolean isPreVrewModel();

	public IBpmTask getBpmTask();
}