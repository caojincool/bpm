package com.dstz.bpm.core.dao;

import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.dao.BaseDao;
import com.dstz.bpm.core.model.BpmInstance;
import com.dstz.bpm.core.model.BpmTaskApprove;
import java.util.List;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public abstract interface BpmInstanceDao
  extends BaseDao<String, BpmInstance>
{
  public abstract List<BpmInstance> getApplyList(QueryFilter paramQueryFilter);
  
  public abstract List<BpmTaskApprove> getApproveHistoryList(QueryFilter paramQueryFilter);
}


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\dao\BpmInstanceDao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */