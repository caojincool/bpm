package com.dstz.bpm.core.manager;

import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.manager.Manager;
import com.dstz.bpm.core.model.BpmDefinition;
import java.util.List;
import org.activiti.engine.repository.Model;
import org.springframework.util.MultiValueMap;

public abstract interface BpmDefinitionManager
  extends Manager<String, BpmDefinition>
{
  public abstract void updateBpmnModel(Model paramModel, MultiValueMap<String, String> paramMultiValueMap)
    throws Exception;
  
  public abstract BpmDefinition getMainDefByActModelId(String paramString);
  
  public abstract BpmDefinition getDefinitionByActDefId(String paramString);
  
  public abstract BpmDefinition getByKey(String paramString);
  
  public abstract List<BpmDefinition> getMyDefinitionList(String paramString, QueryFilter paramQueryFilter);
}


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\manager\BpmDefinitionManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */