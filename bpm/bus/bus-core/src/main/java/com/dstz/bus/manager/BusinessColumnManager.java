package com.dstz.bus.manager;

import com.dstz.base.manager.Manager;
import com.dstz.bus.model.BusinessColumn;
import java.util.List;

public abstract interface BusinessColumnManager
  extends Manager<String, BusinessColumn>
{
  public abstract void removeByTableId(String paramString);
  
  public abstract List<BusinessColumn> getByTableId(String paramString);
}


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\manager\BusinessColumnManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */