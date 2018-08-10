 package com.dstz.bpm.core.model;
 
 import com.dstz.base.api.model.IBaseModel;
 import com.dstz.bpm.api.model.task.IBpmTask;
 import java.util.Date;
 import org.apache.commons.lang3.builder.ToStringBuilder;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class BpmTask
   implements IBaseModel, IBpmTask
 {
   protected String id;
   protected String name;
   protected String subject;
   protected String o;
   protected String taskId;
   protected String R;
   protected String defId;
   protected String S;
   protected String T;
   protected String status;
   protected Integer priority;
   protected Date U;
   protected String V;
   protected String parentId;
   protected String D;
   protected String W;
   protected String r;
   protected Date createTime;
   protected String createBy;
   protected Integer supportMobile;
   protected String X;
   
   public String getTaskType()
   {
     return this.V;
   }
   
   public void setTaskType(String taskType) {
     this.V = taskType;
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
   public void setId(String id)
   {
     this.id = id;
   }
   
 
 
 
   public String getId()
   {
     return this.id;
   }
   
   public void setName(String name) {
     this.name = name;
   }
   
 
 
 
   public String getName()
   {
     return this.name;
   }
   
   public void setSubject(String subject) {
     this.subject = subject;
   }
   
 
 
 
   public String getSubject()
   {
     return this.subject;
   }
   
   public String getAssigneeNames() {
     return this.T;
   }
   
   public void setAssigneeNames(String assigneeNames) {
     this.T = assigneeNames;
   }
   
   public void setInstId(String instId) {
     this.o = instId;
   }
   
 
 
 
   public String getInstId()
   {
     return this.o;
   }
   
   public void setTaskId(String taskId) {
     this.taskId = taskId;
   }
   
 
 
 
   public String getTaskId()
   {
     return this.taskId;
   }
   
   public void setNodeId(String nodeId) {
     this.R = nodeId;
   }
   
 
 
 
   public String getNodeId()
   {
     return this.R;
   }
   
   public void setDefId(String defId) {
     this.defId = defId;
   }
   
 
 
 
   public String getDefId()
   {
     return this.defId;
   }
   
   public void setAssigneeId(String assigneeId) {
     this.S = assigneeId;
   }
   
 
 
 
   public String getAssigneeId()
   {
     return this.S;
   }
   
   public void setStatus(String status) {
     this.status = status;
   }
   
 
 
 
   public String getStatus()
   {
     return this.status;
   }
   
   public String getBackNode() {
     return this.X;
   }
   
   public void setBackNode(String backNode) {
     this.X = backNode;
   }
   
   public void setPriority(Integer priority) {
     this.priority = priority;
   }
   
 
 
 
   public Integer getPriority()
   {
     return this.priority;
   }
   
   public void setDueTime(Date dueTime) {
     this.U = dueTime;
   }
   
 
 
 
   public Date getDueTime()
   {
     return this.U;
   }
   
   public void setParentId(String parentId) {
     this.parentId = parentId;
   }
   
 
 
 
   public String getParentId()
   {
     return this.parentId;
   }
   
   public void setActInstId(String actInstId) {
     this.D = actInstId;
   }
   
 
 
 
   public String getActInstId()
   {
     return this.D;
   }
   
   public void setActExecutionId(String actExecutionId) {
     this.W = actExecutionId;
   }
   
 
 
 
   public String getActExecutionId()
   {
     return this.W;
   }
   
   public void setTypeId(String typeId) {
     this.r = typeId;
   }
   
 
 
 
   public String getTypeId()
   {
     return this.r;
   }
   
   public void setCreateTime(Date createTime) {
     this.createTime = createTime;
   }
   
 
 
 
   public Date getCreateTime()
   {
     return this.createTime;
   }
   
   public void setCreateBy(String createBy) {
     this.createBy = createBy;
   }
   
 
 
 
   public String getCreateBy()
   {
     return this.createBy;
   }
   
   public void setSupportMobile(Integer supportMobile) {
     this.supportMobile = supportMobile;
   }
   
 
 
 
   public Integer getSupportMobile()
   {
     return this.supportMobile;
   }
   
 
   public String toString()
   {
     return 
     
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
       new ToStringBuilder(this).append("id", this.id).append("name", this.name).append("subject", this.subject).append("instId", this.o).append("taskId", this.taskId).append("nodeId", this.R).append("defId", this.defId).append("assigneeId", this.S).append("status", this.status).append("priority", this.priority).append("dueTime", this.U).append("taskType", this.V).append("parentId", this.parentId).append("actInstId", this.D).append("actExecutionId", this.W).append("typeId", this.r).append("createTime", this.createTime).append("createBy", this.createBy).append("supportMobile", this.supportMobile).toString();
   }
   
   public String getActExecutionIdId()
   {
     return this.W;
   }
   
   public Date getUpdateTime()
   {
     return null;
   }
   
 
 
   public void setUpdateTime(Date updatetime) {}
   
 
   public String getUpdateBy()
   {
     return null;
   }
   
   public void setUpdateBy(String updateBy) {}
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\model\BpmTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */