package com.dstz.bpm.core.manager;

import com.dstz.base.manager.Manager;
import com.dstz.bpm.api.model.task.IBpmTask;
import com.dstz.bpm.core.model.BpmTaskStack;

public interface BpmTaskStackManager extends Manager<String, BpmTaskStack> {
	public BpmTaskStack getByTaskId(String var1);

	public BpmTaskStack createStackByTask(IBpmTask var1, BpmTaskStack var2);

	public void removeByInstanceId(String var1);
}