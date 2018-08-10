 package com.dstz.bpm.core.model;
 
 import com.dstz.base.api.model.IBaseModel;
 import com.dstz.bpm.api.model.task.IBpmTaskOpinion;
 import java.util.Date;
 import org.apache.commons.lang3.builder.ToStringBuilder;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class BpmTaskOpinion
   implements IBaseModel, IBpmTaskOpinion
 {
   protected String id;
   protected String o;
   protected String ac;
   protected String taskId;
   protected String ad;
   protected String taskName;
   protected String ae;
   private String af;
   protected String ag;
   protected String ah;
   protected String ai;
   protected String status;
   protected String formId;
   protected String createBy;
   protected Date createTime;
   protected Date Y;
   protected Long Z;
   
   public void setId(String id)
   {
     this.id = id;
   }
   
 
 
 
   public String getId()
   {
     return this.id;
   }
   
   public void setInstId(String instId) {
     this.o = instId;
   }
   
 
 
 
   public String getInstId()
   {
     return this.o;
   }
   
   public void setSupInstId(String supInstId) {
     this.ac = supInstId;
   }
   
 
 
 
   public String getSupInstId()
   {
     return this.ac;
   }
   
   public void setTaskId(String taskId) {
     this.taskId = taskId;
   }
   
 
 
 
   public String getTaskId()
   {
     return this.taskId;
   }
   
   public void setTaskKey(String taskKey) {
     this.ad = taskKey;
   }
   
 
 
 
   public String getTaskKey()
   {
     return this.ad;
   }
   
   public void setTaskName(String taskName) {
     this.taskName = taskName;
   }
   
 
 
 
   public String getTaskName()
   {
     return this.taskName;
   }
   
   public void setToken(String token) {
     this.ae = token;
   }
   
 
 
 
   public String getToken()
   {
     return this.ae;
   }
   
   public void setApprover(String approver) {
     this.ag = approver;
   }
   
 
 
 
   public String getApprover()
   {
     return this.ag;
   }
   
   public void setApproverName(String approverName)
   {
     this.ah = approverName;
   }
   
 
 
 
   public String getApproverName()
   {
     return this.ah;
   }
   
   public void setOpinion(String opinion) {
     this.ai = opinion;
   }
   
 
 
 
   public String getOpinion()
   {
     return this.ai;
   }
   
   public Date getApproveTime() {
     return this.Y;
   }
   
   public void setApproveTime(Date approveTime) {
     this.Y = approveTime;
   }
   
   public void setStatus(String status) {
     this.status = status;
   }
   
 
 
 
   public String getStatus()
   {
     return this.status;
   }
   
   public void setFormId(String formId) {
     this.formId = formId;
   }
   
 
 
 
   public String getFormId()
   {
     return this.formId;
   }
   
   public void setCreateBy(String createBy) {
     this.createBy = createBy;
   }
   
 
 
 
   public String getCreateBy()
   {
     return this.createBy;
   }
   
   public void setCreateTime(Date createTime) {
     this.createTime = createTime;
   }
   
 
 
 
   public Date getCreateTime()
   {
     return this.createTime;
   }
   
   public void setDurMs(Long durMs) {
     this.Z = durMs;
   }
   
 
 
 
   public Long getDurMs()
   {
     return this.Z;
   }
   
 
   public String toString()
   {
     return 
     
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
       new ToStringBuilder(this).append("id", this.id).append("instId", this.o).append("supInstId", this.ac).append("taskId", this.taskId).append("taskKey", this.ad).append("taskName", this.taskName).append("token", this.ae).append("approver", this.ag).append("approverName", this.ah).append("opinion", this.ai).append("status", this.status).append("formId", this.formId).append("createBy", this.createBy).append("createTime", this.createTime).append("approveTime", this.Y).append("durMs", this.Z).toString();
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
   
 
   public String getAssignInfo()
   {
     return this.af;
   }
   
   public void setAssignInfo(String assignInfo) {
     this.af = assignInfo;
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\model\BpmTaskOpinion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */