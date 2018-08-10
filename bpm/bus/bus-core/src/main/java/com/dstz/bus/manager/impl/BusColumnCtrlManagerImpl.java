package com.dstz.bus.manager.impl;

import com.dstz.base.manager.impl.BaseManager;
import com.dstz.bus.dao.BusColumnCtrlDao;
import com.dstz.bus.manager.BusColumnCtrlManager;
import com.dstz.bus.model.BusColumnCtrl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class BusColumnCtrlManagerImpl extends BaseManager<String, BusColumnCtrl> implements BusColumnCtrlManager {
	@Resource
	private BusColumnCtrlDao e;

	public void removeByTableId(String tableId) {
		this.e.removeByTableId(tableId);
	}

	public BusColumnCtrl getByColumnId(String columnId) {
		return this.e.getByColumnId(columnId);
	}
}