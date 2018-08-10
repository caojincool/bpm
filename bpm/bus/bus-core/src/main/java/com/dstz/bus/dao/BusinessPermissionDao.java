package com.dstz.bus.dao;

import com.dstz.base.dao.BaseDao;
import com.dstz.bus.model.BusinessPermission;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public abstract interface BusinessPermissionDao
  extends BaseDao<String, BusinessPermission>
{
  public abstract BusinessPermission getByObjTypeAndObjVal(@Param("objType") String paramString1, @Param("objVal") String paramString2);
}


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\dao\BusinessPermissionDao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */