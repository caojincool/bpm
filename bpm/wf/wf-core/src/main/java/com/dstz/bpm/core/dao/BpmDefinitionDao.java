package com.dstz.bpm.core.dao;

import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.dao.BaseDao;
import com.dstz.bpm.core.model.BpmDefinition;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public abstract interface BpmDefinitionDao
  extends BaseDao<String, BpmDefinition>
{
  public abstract BpmDefinition getMainByDefKey(String paramString);
  
  public abstract void updateActResourceEntity(@Param("deploymentId") String paramString1, @Param("resName") String paramString2, @Param("bpmnBytes") byte[] paramArrayOfByte);
  
  public abstract List<BpmDefinition> getByKey(String paramString);
  
  public abstract BpmDefinition getMainDefByActModelId(String paramString);
  
  public abstract BpmDefinition getByActDefId(String paramString);
  
  public abstract void updateToMain(String paramString);
  
  public abstract List<BpmDefinition> getMyDefinitionList(QueryFilter paramQueryFilter);
}


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\dao\BpmDefinitionDao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */