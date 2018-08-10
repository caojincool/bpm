package com.dstz.bpm.engine.plugin.runtime;

import com.dstz.bpm.api.engine.plugin.def.BpmExecutionPluginDef;
import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
import com.dstz.bpm.engine.plugin.session.BpmExecutionPluginSession;

public interface BpmExecutionPlugin<S extends BpmExecutionPluginSession, M extends BpmExecutionPluginDef>
		extends
			RunTimePlugin<S, M, Void> {
}