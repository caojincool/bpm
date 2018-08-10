package com.dstz.bpm.core.manager;

import com.dstz.base.manager.Manager;
import com.dstz.bpm.api.model.task.IBpmTask;
import com.dstz.bpm.core.model.TaskIdentityLink;
import com.dstz.sys.api.model.SysIdentity;
import java.util.List;
import java.util.Set;

public interface TaskIdentityLinkManager extends Manager<String, TaskIdentityLink> {
	public void removeByTaskId(String var1);

	public void bulkCreate(List<TaskIdentityLink> var1);

	public void removeByInstanceId(String var1);

	public Boolean checkUserOperatorPermission(String var1, String var2, String var3);

	public void createIdentityLink(IBpmTask var1, List<SysIdentity> var2);

	public Set<String> getUserRights(String var1);

	public List<TaskIdentityLink> getByTaskId(String var1);
}