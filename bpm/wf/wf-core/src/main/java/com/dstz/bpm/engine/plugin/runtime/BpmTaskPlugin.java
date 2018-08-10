package com.dstz.bpm.engine.plugin.runtime;

import com.dstz.bpm.api.engine.plugin.def.BpmTaskPluginDef;
import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
import com.dstz.bpm.engine.plugin.session.BpmTaskPluginSession;

public interface BpmTaskPlugin<S extends BpmTaskPluginSession, M extends BpmTaskPluginDef>
		extends
			RunTimePlugin<S, M, Void> {
}