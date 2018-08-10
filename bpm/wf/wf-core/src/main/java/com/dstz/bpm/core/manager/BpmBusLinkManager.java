package com.dstz.bpm.core.manager;

import com.dstz.base.manager.Manager;
import com.dstz.bpm.core.model.BpmBusLink;
import java.util.List;

public interface BpmBusLinkManager extends Manager<String, BpmBusLink> {
	public List<BpmBusLink> getByInstanceId(String instanceId);
}