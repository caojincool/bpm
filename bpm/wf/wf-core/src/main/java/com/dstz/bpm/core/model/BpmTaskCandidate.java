 package com.dstz.bpm.core.model;
 
 import com.dstz.base.api.model.IBaseModel;
 import java.util.Date;
 import org.apache.commons.lang3.builder.ToStringBuilder;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class BpmTaskCandidate
   implements IBaseModel
 {
   protected String id;
   protected String taskId;
   protected String type;
   protected String ab;
   protected String o;
   
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
   
   public void setType(String type) {
     this.type = type;
   }
   
 
 
 
   public String getType()
   {
     return this.type;
   }
   
   public void setExecutor(String executor) {
     this.ab = executor;
   }
   
 
 
 
   public String getExecutor()
   {
     return this.ab;
   }
   
   public void setInstId(String instId) {
     this.o = instId;
   }
   
 
 
 
   public String getInstId()
   {
     return this.o;
   }
   
 
   public String toString()
   {
     return 
     
 
 
 
 
       new ToStringBuilder(this).append("id", this.id).append("taskId", this.taskId).append("type", this.type).append("executor", this.ab).append("instId", this.o).toString();
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


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\model\BpmTaskCandidate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */