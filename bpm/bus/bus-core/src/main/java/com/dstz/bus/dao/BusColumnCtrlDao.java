package com.dstz.bus.dao;

import com.dstz.base.dao.BaseDao;
import com.dstz.bus.model.BusColumnCtrl;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface BusColumnCtrlDao extends BaseDao<String, BusColumnCtrl> {
	public void removeByTableId(String var1);

	public BusColumnCtrl getByColumnId(String var1);
}