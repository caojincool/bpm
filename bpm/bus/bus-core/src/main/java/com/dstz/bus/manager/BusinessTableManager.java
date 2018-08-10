package com.dstz.bus.manager;

import com.dstz.base.db.tableoper.TableOperator;
import com.dstz.base.manager.Manager;
import com.dstz.bus.model.BusinessTable;

public abstract interface BusinessTableManager
  extends Manager<String, BusinessTable>
{
  public abstract void save(BusinessTable paramBusinessTable);
  
  public abstract BusinessTable getByKey(String paramString);
  
  public abstract BusinessTable getFilledByKey(String paramString);
  
  public abstract TableOperator newTableOperator(BusinessTable paramBusinessTable);
}


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\manager\BusinessTableManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */