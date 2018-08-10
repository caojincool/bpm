package com.dstz.bus.manager;

import com.dstz.base.manager.Manager;
import com.dstz.bus.model.BusinessPermission;

public interface BusinessPermissionManager extends Manager<String, BusinessPermission> {
	public BusinessPermission getByObjTypeAndObjVal(String var1, String var2);

	public BusinessPermission getByObjTypeAndObjVal(String var1, String var2, String var3);
}