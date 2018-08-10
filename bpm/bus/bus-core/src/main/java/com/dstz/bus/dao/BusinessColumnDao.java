package com.dstz.bus.dao;

import com.dstz.base.dao.BaseDao;
import com.dstz.bus.model.BusinessColumn;
import java.util.List;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public abstract interface BusinessColumnDao
  extends BaseDao<String, BusinessColumn>
{
  public abstract void removeByTableId(String paramString);
  
  public abstract List<BusinessColumn> getByTableId(String paramString);
}


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\dao\BusinessColumnDao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */