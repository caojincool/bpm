package com.dstz.bpm.engine.plugin.context;

import com.dstz.bpm.api.engine.plugin.def.BpmExecutionPluginDef;
import com.dstz.bpm.engine.plugin.context.AbstractBpmPluginContext;

public abstract class AbstractBpmExecutionPluginContext<T extends BpmExecutionPluginDef>
		extends
			AbstractBpmPluginContext<T> {
}