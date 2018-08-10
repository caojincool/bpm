package com.dstz.bus.dao;

import com.dstz.base.dao.BaseDao;
import com.dstz.bus.model.BusinessObject;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public abstract interface BusinessObjectDao
  extends BaseDao<String, BusinessObject>
{}


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\dao\BusinessObjectDao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */