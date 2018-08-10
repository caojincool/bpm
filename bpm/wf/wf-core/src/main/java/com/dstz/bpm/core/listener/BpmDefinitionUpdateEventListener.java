package com.dstz.bpm.core.listener;

import com.dstz.bpm.act.cache.ActivitiDefCache;
import com.dstz.bpm.api.engine.event.BpmDefinitionUpdateEvent;
import com.dstz.bpm.api.service.BpmProcessDefService;
import com.dstz.bpm.core.model.BpmDefinition;
import javax.annotation.Resource;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class BpmDefinitionUpdateEventListener implements ApplicationListener<BpmDefinitionUpdateEvent> {
	@Resource
	private BpmProcessDefService bpmProcessDefService;
	
	@Override
	public void onApplicationEvent(BpmDefinitionUpdateEvent event) {
		BpmDefinition bpmDef = (BpmDefinition) event.getSource();
		this.bpmProcessDefService.clean(bpmDef.getId());
		ActivitiDefCache.clearByDefId((String) bpmDef.getActDefId());
	}
}