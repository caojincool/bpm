 package com.dstz.bpm.core.manager.impl;
 
 import com.dstz.base.api.exception.BusinessException;
 import com.dstz.base.api.query.QueryFilter;
 import com.dstz.base.api.query.QueryOP;
 import com.dstz.base.core.util.BeanUtils;
 import com.dstz.base.core.util.StringUtil;
 import com.dstz.base.manager.impl.BaseManager;
 import com.dstz.bpm.api.constant.TaskStatus;
 import com.dstz.bpm.api.exception.BpmStatusCode;
 import com.dstz.bpm.core.dao.BpmTaskDao;
 import com.dstz.bpm.core.manager.BpmInstanceManager;
 import com.dstz.bpm.core.manager.BpmTaskManager;
 import com.dstz.bpm.core.manager.TaskIdentityLinkManager;
 import com.dstz.bpm.core.model.BpmTask;
 import com.dstz.bpm.core.model.TaskIdentityLink;
 import com.dstz.sys.util.ContextUtil;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 import java.util.Set;
 import javax.annotation.Resource;
 import org.springframework.stereotype.Service;
 
 
 
 
 
 
 @Service("bpmTaskManager")
 public class BpmTaskManagerImpl
   extends BaseManager<String, BpmTask>
   implements BpmTaskManager
 {
   @Resource
   BpmTaskDao h;
   @Resource
   TaskIdentityLinkManager i;
   @Resource
   BpmInstanceManager j;
   
   public List<BpmTask> getByInstIdNodeId(String instId, String nodeId)
   {
     return this.h.getByInstIdNodeId(instId, nodeId);
   }
   
   public List<BpmTask> getByInstId(String instId)
   {
     return this.h.getByInstIdNodeId(instId, null);
   }
   
 
 
   public List<BpmTask> getTodoList(String userId, QueryFilter queryFilter)
   {
     Set<String> userRights = this.i.getUserRights(userId);
     queryFilter.addParamsFilter("userRights", userRights);
     queryFilter.addParamsFilter("userId", ContextUtil.getCurrentUserId());
     
     List<BpmTask> taskList = this.h.getTodoList(queryFilter);
     return taskList;
   }
   
   public List getTodoList(QueryFilter queryFilter) {
     String userId = ContextUtil.getCurrentUserId();
     String type = (String)queryFilter.getParams().get("type");
     String title = (String)queryFilter.getParams().get("subject");
     
     if (StringUtil.isNotEmpty(title)) {
       queryFilter.addFilter("subject_", title, QueryOP.LIKE);
     }
     
     if ("done".equals(type)) {
       return this.j.getApproveHistoryList(userId, queryFilter);
     }
     
     Set<String> userRights = this.i.getUserRights(userId);
     queryFilter.addParamsFilter("userRights", userRights);
     
     return this.h.getTodoList(queryFilter);
   }
   
 
 
   public void assigneeTask(String taskId, String userId, String userName)
   {
     BpmTask task = (BpmTask)get(taskId);
     if (task == null) {
       throw new BusinessException("任务可能已经被处理，请刷新。", BpmStatusCode.TASK_NOT_FOUND);
     }
     
     task.setAssigneeId(userId);
     task.setAssigneeNames(userName);
     task.setStatus(TaskStatus.DESIGNATE.getKey());
     update(task);
   }
   
   public void unLockTask(String taskId)
   {
     BpmTask task = (BpmTask)get(taskId);
     
     List<TaskIdentityLink> identitys = this.i.getByTaskId(task.getId());
     if (BeanUtils.isEmpty(identitys)) {
       throw new BusinessException("该任务并非多候选人状态，无效的操作！");
     }
     
     List<String> names = new ArrayList();
     for (TaskIdentityLink identity : identitys) {
       names.add(identity.getIdentityName());
     }
     
     task.setAssigneeId("0");
     task.setAssigneeNames(StringUtil.convertCollectionAsString(names));
     task.setStatus(TaskStatus.NORMAL.getKey());
     update(task);
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\manager\impl\BpmTaskManagerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */