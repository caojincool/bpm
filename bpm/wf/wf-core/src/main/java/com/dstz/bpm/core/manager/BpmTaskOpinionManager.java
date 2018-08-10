package com.dstz.bpm.core.manager;

import com.dstz.base.manager.Manager;
import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
import com.dstz.bpm.api.model.inst.IBpmInstance;
import com.dstz.bpm.core.model.BpmTaskOpinion;
import java.util.List;

public interface BpmTaskOpinionManager extends Manager<String, BpmTaskOpinion> {
	public BpmTaskOpinion getByTaskId(String var1);

	public void createOpinionByInstance(IBpmInstance var1, String var2, boolean var3);

	public void createOpinionByTask(TaskActionCmd var1);

	public List<BpmTaskOpinion> getByInstAndNode(String var1, String var2);

	public List<BpmTaskOpinion> getByInstId(String var1);
}