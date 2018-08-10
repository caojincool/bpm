package com.dstz.bus.service;

import com.dstz.bus.model.BusinessData;
import com.dstz.bus.model.BusinessObject;

public abstract interface BusinessDataPersistenceService
{
  public abstract String type();
  
  public abstract void saveData(BusinessData paramBusinessData);
  
  public abstract BusinessData loadData(BusinessObject paramBusinessObject, Object paramObject);
  
  public abstract void removeData(BusinessObject paramBusinessObject, Object paramObject);
}


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\service\BusinessDataPersistenceService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */