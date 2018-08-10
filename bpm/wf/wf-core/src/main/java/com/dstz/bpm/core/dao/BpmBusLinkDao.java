package com.dstz.bpm.core.dao;

import com.dstz.base.dao.BaseDao;
import com.dstz.bpm.core.model.BpmBusLink;
import java.util.List;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface BpmBusLinkDao extends BaseDao<String, BpmBusLink> {
	public List<BpmBusLink> getByInstanceId(String var1);
}