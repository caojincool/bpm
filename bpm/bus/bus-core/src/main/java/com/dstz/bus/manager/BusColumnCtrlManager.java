package com.dstz.bus.manager;

import com.dstz.base.manager.Manager;
import com.dstz.bus.model.BusColumnCtrl;

public abstract interface BusColumnCtrlManager
  extends Manager<String, BusColumnCtrl>
{
  public abstract void removeByTableId(String paramString);
  
  public abstract BusColumnCtrl getByColumnId(String paramString);
}


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\manager\BusColumnCtrlManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */