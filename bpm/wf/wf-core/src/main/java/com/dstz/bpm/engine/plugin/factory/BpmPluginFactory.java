package com.dstz.bpm.engine.plugin.factory;

import com.dstz.base.core.util.AppUtil;
import com.dstz.bpm.api.constant.EventType;
import com.dstz.bpm.api.engine.plugin.context.BpmPluginContext;
import com.dstz.bpm.engine.plugin.runtime.BpmExecutionPlugin;
import com.dstz.bpm.engine.plugin.runtime.BpmTaskPlugin;
import java.util.List;

public class BpmPluginFactory {
	public static BpmExecutionPlugin buildExecutionPlugin(BpmPluginContext bpmPluginContext, EventType eventType) {
		if (bpmPluginContext.getEventTypes().contains((Object) eventType)) {
			try {
				BpmExecutionPlugin bpmExecutionPlugin = (BpmExecutionPlugin) AppUtil
						.getBean((Class) bpmPluginContext.getPluginClass());
				return bpmExecutionPlugin;
			} catch (Exception bpmExecutionPlugin) {
				// empty catch block
			}
		}
		return null;
	}

	public static BpmTaskPlugin buildTaskPlugin(BpmPluginContext bpmPluginContext, EventType eventType) {
		if (bpmPluginContext.getEventTypes().contains((Object) eventType)) {
			return (BpmTaskPlugin) AppUtil.getBean((Class) bpmPluginContext.getPluginClass());
		}
		return null;
	}
}