package com.dstz.bpm.core.dao;

import com.dstz.base.dao.BaseDao;
import com.dstz.bpm.core.model.TaskIdentityLink;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface TaskIdentityLinkDao extends BaseDao<String, TaskIdentityLink> {
	public void removeByInstanceId(String var1);

	public void removeByTaskId(String var1);

	public void bulkCreate(List<TaskIdentityLink> var1);

	public int checkUserOperatorPermission(@Param(value = "rights") Set<String> var1,
			@Param(value = "taskId") String var2, @Param(value = "instanceId") String var3);

	public List<TaskIdentityLink> getByTaskId(String var1);
}