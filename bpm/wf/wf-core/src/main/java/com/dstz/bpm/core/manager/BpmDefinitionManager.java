package com.dstz.bpm.core.manager;

import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.manager.Manager;
import com.dstz.bpm.core.model.BpmDefinition;
import java.util.List;
import org.activiti.engine.repository.Model;
import org.springframework.util.MultiValueMap;

public interface BpmDefinitionManager extends Manager<String, BpmDefinition> {
	public void updateBpmnModel(Model model, MultiValueMap<String, String> var2) throws Exception;

	public BpmDefinition getMainDefByActModelId(String actModelId);

	public BpmDefinition getDefinitionByActDefId(String actDefId);

	public BpmDefinition getByKey(String key);

	public List<BpmDefinition> getMyDefinitionList(String var1, QueryFilter queryFilter);
}