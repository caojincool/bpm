package com.dstz.bpm.core.manager;

import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.manager.Manager;
import com.dstz.bpm.core.model.BpmTask;
import java.util.List;

public interface BpmTaskManager extends Manager<String, BpmTask> {
	public List<BpmTask> getByInstIdNodeId(String var1, String var2);

	public List<BpmTask> getByInstId(String instanceId);

	public List<BpmTask> getTodoList(String var1, QueryFilter queryFilter);

	public void assigneeTask(String var1, String var2, String var3);

	public void unLockTask(String var1);
}