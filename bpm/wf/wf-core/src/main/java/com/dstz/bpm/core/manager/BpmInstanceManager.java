package com.dstz.bpm.core.manager;

import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.manager.Manager;
import com.dstz.bpm.api.model.def.IBpmDefinition;
import com.dstz.bpm.core.model.BpmInstance;
import com.dstz.bpm.core.model.BpmTaskApprove;
import java.util.List;

public interface BpmInstanceManager extends Manager<String, BpmInstance> {
	public boolean isSuspendByInstId(String isntanceId);

	public List<BpmInstance> getApplyList(String var1, QueryFilter queryFilter);

	public List<BpmTaskApprove> getApproveHistoryList(String var1, QueryFilter queryFilter);

	public BpmInstance getTopInstance(BpmInstance bpmInstance);

	public BpmInstance genInstanceByDefinition(IBpmDefinition bpmDefinition);
}