package com.dstz.bpm.core.dao;

import com.dstz.base.dao.BaseDao;
import com.dstz.bpm.core.model.BpmTaskOpinion;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public abstract interface BpmTaskOpinionDao
  extends BaseDao<String, BpmTaskOpinion>
{
  public abstract BpmTaskOpinion getByTaskId(String paramString);
  
  public abstract List<BpmTaskOpinion> getByInstAndNode(@Param("instId") String paramString1, @Param("nodeId") String paramString2);
}


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\dao\BpmTaskOpinionDao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */