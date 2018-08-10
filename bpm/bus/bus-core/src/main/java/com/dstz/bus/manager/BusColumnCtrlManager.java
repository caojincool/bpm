package com.dstz.bus.manager;

import com.dstz.base.manager.Manager;
import com.dstz.bus.model.BusColumnCtrl;

public interface BusColumnCtrlManager extends Manager<String, BusColumnCtrl> {
	public void removeByTableId(String var1);

	public BusColumnCtrl getByColumnId(String var1);
}