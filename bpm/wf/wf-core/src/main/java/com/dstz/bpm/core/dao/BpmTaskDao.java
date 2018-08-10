package com.dstz.bpm.core.dao;

import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.dao.BaseDao;
import com.dstz.bpm.core.model.BpmTask;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface BpmTaskDao extends BaseDao<String, BpmTask> {
	public List<BpmTask> getByInstIdNodeId(@Param(value = "instId") String var1, @Param(value = "nodeId") String var2);

	public List<BpmTask> getTodoList(QueryFilter var1);
}