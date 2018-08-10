 package com.dstz.bpm.core.manager.impl;
 
 import com.dstz.base.api.exception.BusinessException;
 import com.dstz.base.core.util.BeanUtils;
 import com.dstz.base.core.util.StringUtil;
 import com.dstz.base.db.id.UniqueIdUtil;
 import com.dstz.base.manager.impl.BaseManager;
 import com.dstz.bpm.api.exception.BpmStatusCode;
 import com.dstz.bpm.api.model.task.IBpmTask;
 import com.dstz.bpm.core.dao.TaskIdentityLinkDao;
 import com.dstz.bpm.core.manager.TaskIdentityLinkManager;
 import com.dstz.bpm.core.model.TaskIdentityLink;
 import com.dstz.org.api.model.IGroup;
 import com.dstz.org.api.service.GroupService;
 import com.dstz.sys.api.model.SysIdentity;
 import java.util.ArrayList;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Set;
 import javax.annotation.Resource;
 import org.springframework.stereotype.Service;
 
 
 
 
 
 
 @Service("taskIdentityLinkManager")
 public class TaskIdentityLinkManagerImpl
   extends BaseManager<String, TaskIdentityLink>
   implements TaskIdentityLinkManager
 {
   @Resource
   TaskIdentityLinkDao m;
   @Resource
   GroupService n;
   
   public void removeByTaskId(String taskId)
   {
     this.m.removeByTaskId(taskId);
   }
   
   public void removeByInstanceId(String instId)
   {
     this.m.removeByInstanceId(instId);
   }
   
 
   public void bulkCreate(List<TaskIdentityLink> list)
   {
     this.m.bulkCreate(list);
   }
   
   public Boolean checkUserOperatorPermission(String userId, String instanceId, String taskId)
   {
     if ((StringUtil.isEmpty(instanceId)) && (StringUtil.isEmpty(taskId))) {
       throw new BusinessException("检查权限必须提供流程或者任务id", BpmStatusCode.PARAM_ILLEGAL);
     }
     
     Set<String> rights = getUserRights(userId);
     
     return Boolean.valueOf(this.m.checkUserOperatorPermission(rights, taskId, instanceId) > 0);
   }
   
 
 
 
 
 
   public Set<String> getUserRights(String userId)
   {
     List<IGroup> list = this.n.getGroupsByUserId(userId);
     
     Set<String> rights = new HashSet();
     rights.add(String.format("%s-%s", new Object[] { userId, "user" }));
     
     if (BeanUtils.isEmpty(list)) { return rights;
     }
     for (IGroup group : list) {
       rights.add(String.format("%s-%s", new Object[] { group.getGroupId(), group.getGroupType() }));
     }
     
     return rights;
   }
   
 
   public void createIdentityLink(IBpmTask bpmTask, List<SysIdentity> identitys)
   {
     List<TaskIdentityLink> identityLinks = new ArrayList();
     
     for (SysIdentity identity : identitys) {
       TaskIdentityLink link = new TaskIdentityLink();
       link.setId(UniqueIdUtil.getSuid());
       link.setIdentity(identity.getId());
       link.setIdentityName(identity.getName());
       link.setType(identity.getType());
       link.setPermissionCode(identity.getId() + "-" + identity.getType());
       link.setTaskId(bpmTask.getId());
       link.setInstId(bpmTask.getInstId());
       identityLinks.add(link);
     }
     
     bulkCreate(identityLinks);
   }
   
   public List<TaskIdentityLink> getByTaskId(String taskId)
   {
     return this.m.getByTaskId(taskId);
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\manager\impl\TaskIdentityLinkManagerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */