package com.dstz.bpm.engine.plugin.session;

import com.dstz.bpm.api.constant.EventType;
import com.dstz.bpm.api.model.inst.IBpmInstance;
import com.dstz.bus.api.model.IBusinessData;
import java.util.Map;
import org.activiti.engine.delegate.VariableScope;

public interface BpmPluginSession extends Map<String, Object> {
	public IBpmInstance getBpmInstance();

	public Map<String, IBusinessData> getBoDatas();

	public VariableScope getVariableScope();

	public EventType getEventType();
}