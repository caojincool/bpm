 package com.dstz.bpm.core.model;
 
 import com.dstz.base.api.model.IBaseModel;
 import java.util.Date;
 import org.apache.commons.lang3.builder.ToStringBuilder;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class BpmTaskStack
   implements IBaseModel
 {
   protected String id;
   protected String taskId;
   protected String o;
   protected String parentId;
   protected String R;
   protected String nodeName;
   protected Date startTime;
   protected Date endTime;
   protected Short aj;
   protected String path;
   
   public void setId(String id)
   {
     this.id = id;
   }
   
 
 
 
   public String getId()
   {
     return this.id;
   }
   
   public void setTaskId(String taskId) {
     this.taskId = taskId;
   }
   
 
 
 
   public String getTaskId()
   {
     return this.taskId;
   }
   
   public void setInstId(String instId) {
     this.o = instId;
   }
   
 
 
 
   public String getInstId()
   {
     return this.o;
   }
   
   public void setParentId(String parentId) {
     this.parentId = parentId;
   }
   
 
 
 
   public String getParentId()
   {
     return this.parentId;
   }
   
   public void setNodeId(String nodeId) {
     this.R = nodeId;
   }
   
 
 
 
   public String getNodeId()
   {
     return this.R;
   }
   
   public void setNodeName(String nodeName) {
     this.nodeName = nodeName;
   }
   
 
 
 
   public String getNodeName()
   {
     return this.nodeName;
   }
   
   public void setStartTime(Date startTime) {
     this.startTime = startTime;
   }
   
 
 
 
   public Date getStartTime()
   {
     return this.startTime;
   }
   
   public void setEndTime(Date endTime) {
     this.endTime = endTime;
   }
   
 
 
 
   public Date getEndTime()
   {
     return this.endTime;
   }
   
   public void setIsMulitiTask(Short isMulitiTask) {
     this.aj = isMulitiTask;
   }
   
 
 
 
 
   public Short getIsMulitiTask()
   {
     return this.aj;
   }
   
   public void setPath(String path) {
     this.path = path;
   }
   
 
 
 
   public String getPath()
   {
     return this.path;
   }
   
 
   public String toString()
   {
     return 
     
 
 
 
 
 
 
 
 
 
       new ToStringBuilder(this).append("id", this.id).append("taskId", this.taskId).append("instId", this.o).append("parentId", this.parentId).append("nodeId", this.R).append("nodeName", this.nodeName).append("startTime", this.startTime).append("endTime", this.endTime).append("isMulitiTask", this.aj).append("path", this.path).toString();
   }
   
 
   public String getCreateBy()
   {
     return null;
   }
   
 
 
 
   public void setCreateBy(String createBy) {}
   
 
 
   public Date getCreateTime()
   {
     return null;
   }
   
 
 
 
   public void setCreateTime(Date createtime) {}
   
 
 
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


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\model\BpmTaskStack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */