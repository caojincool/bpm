package com.dstz.bus.dao;

import com.dstz.base.dao.BaseDao;
import com.dstz.bus.model.BusinessTable;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public abstract interface BusinessTableDao
  extends BaseDao<String, BusinessTable>
{}


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\dao\BusinessTableDao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */