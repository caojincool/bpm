package com.dstz.bpm.core.manager.impl;

import com.dstz.base.manager.impl.BaseManager;
import com.dstz.bpm.core.dao.BpmBusLinkDao;
import com.dstz.bpm.core.manager.BpmBusLinkManager;
import com.dstz.bpm.core.model.BpmBusLink;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service(value = "bpmBusLinkManager")
public class BpmBusLinkManagerImpl extends BaseManager<String, BpmBusLink> implements BpmBusLinkManager {
	@Resource
	private BpmBusLinkDao bpmBusLinkDao;

	public List<BpmBusLink> getByInstanceId(String instanceId) {
		return this.bpmBusLinkDao.getByInstanceId(instanceId);
	}
}