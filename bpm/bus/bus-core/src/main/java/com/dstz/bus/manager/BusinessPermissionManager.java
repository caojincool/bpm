package com.dstz.bus.manager;

import com.dstz.base.manager.Manager;
import com.dstz.bus.model.BusinessPermission;

public abstract interface BusinessPermissionManager
  extends Manager<String, BusinessPermission>
{
  public abstract BusinessPermission getByObjTypeAndObjVal(String paramString1, String paramString2);
  
  public abstract BusinessPermission getByObjTypeAndObjVal(String paramString1, String paramString2, String paramString3);
}


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\manager\BusinessPermissionManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */