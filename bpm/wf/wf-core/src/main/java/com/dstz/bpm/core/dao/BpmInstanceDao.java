package com.dstz.bpm.core.dao;

import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.dao.BaseDao;
import com.dstz.bpm.core.model.BpmInstance;
import com.dstz.bpm.core.model.BpmTaskApprove;
import java.util.List;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface BpmInstanceDao extends BaseDao<String, BpmInstance> {
	public List<BpmInstance> getApplyList(QueryFilter var1);

	public List<BpmTaskApprove> getApproveHistoryList(QueryFilter var1);
}