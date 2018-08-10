/*     */ package com.dstz.bpm.core.model;
/*     */ 
/*     */ import com.dstz.base.api.model.IBaseModel;
/*     */ import java.util.Date;
/*     */ import org.apache.commons.lang3.builder.ToStringBuilder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BpmTaskStack
/*     */   implements IBaseModel
/*     */ {
/*     */   protected String id;
/*     */   protected String taskId;
/*     */   protected String o;
/*     */   protected String parentId;
/*     */   protected String R;
/*     */   protected String nodeName;
/*     */   protected Date startTime;
/*     */   protected Date endTime;
/*     */   protected Short aj;
/*     */   protected String path;
/*     */   
/*     */   public void setId(String id)
/*     */   {
/*  70 */     this.id = id;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getId()
/*     */   {
/*  78 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setTaskId(String taskId) {
/*  82 */     this.taskId = taskId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getTaskId()
/*     */   {
/*  90 */     return this.taskId;
/*     */   }
/*     */   
/*     */   public void setInstId(String instId) {
/*  94 */     this.o = instId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getInstId()
/*     */   {
/* 102 */     return this.o;
/*     */   }
/*     */   
/*     */   public void setParentId(String parentId) {
/* 106 */     this.parentId = parentId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getParentId()
/*     */   {
/* 114 */     return this.parentId;
/*     */   }
/*     */   
/*     */   public void setNodeId(String nodeId) {
/* 118 */     this.R = nodeId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getNodeId()
/*     */   {
/* 126 */     return this.R;
/*     */   }
/*     */   
/*     */   public void setNodeName(String nodeName) {
/* 130 */     this.nodeName = nodeName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getNodeName()
/*     */   {
/* 138 */     return this.nodeName;
/*     */   }
/*     */   
/*     */   public void setStartTime(Date startTime) {
/* 142 */     this.startTime = startTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getStartTime()
/*     */   {
/* 150 */     return this.startTime;
/*     */   }
/*     */   
/*     */   public void setEndTime(Date endTime) {
/* 154 */     this.endTime = endTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getEndTime()
/*     */   {
/* 162 */     return this.endTime;
/*     */   }
/*     */   
/*     */   public void setIsMulitiTask(Short isMulitiTask) {
/* 166 */     this.aj = isMulitiTask;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Short getIsMulitiTask()
/*     */   {
/* 175 */     return this.aj;
/*     */   }
/*     */   
/*     */   public void setPath(String path) {
/* 179 */     this.path = path;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getPath()
/*     */   {
/* 187 */     return this.path;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 193 */     return 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 204 */       new ToStringBuilder(this).append("id", this.id).append("taskId", this.taskId).append("instId", this.o).append("parentId", this.parentId).append("nodeId", this.R).append("nodeName", this.nodeName).append("startTime", this.startTime).append("endTime", this.endTime).append("isMulitiTask", this.aj).append("path", this.path).toString();
/*     */   }
/*     */   
/*     */ 
/*     */   public String getCreateBy()
/*     */   {
/* 210 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCreateBy(String createBy) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public Date getCreateTime()
/*     */   {
/* 222 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCreateTime(Date createtime) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public Date getUpdateTime()
/*     */   {
/* 234 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setUpdateTime(Date updatetime) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public String getUpdateBy()
/*     */   {
/* 246 */     return null;
/*     */   }
/*     */   
/*     */   public void setUpdateBy(String updateBy) {}
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\model\BpmTaskStack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */