package com.dstz.bus.manager;

import com.alibaba.fastjson.JSONObject;
import com.dstz.base.manager.Manager;
import com.dstz.bus.model.BusinessObject;
import java.util.List;

public abstract interface BusinessObjectManager
  extends Manager<String, BusinessObject>
{
  public abstract BusinessObject getByKey(String paramString);
  
  public abstract List<JSONObject> boTreeData(String paramString);
  
  public abstract BusinessObject getFilledByKey(String paramString);
}


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\manager\BusinessObjectManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */