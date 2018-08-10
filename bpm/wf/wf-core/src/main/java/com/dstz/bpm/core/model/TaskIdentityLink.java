 package com.dstz.bpm.core.model;
 
 import java.io.Serializable;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class TaskIdentityLink
   implements Serializable
 {
   public static final String ak = "user";
   protected String id;
   protected String taskId;
   protected String o;
   protected String type;
   protected String al;
   protected String identity;
   protected String am;
   
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
   
   public void setType(String type) {
     this.type = type;
   }
   
 
 
 
   public String getType()
   {
     return this.type;
   }
   
   public void setIdentityName(String identityName) {
     this.al = identityName;
   }
   
 
 
 
   public String getIdentityName()
   {
     return this.al;
   }
   
   public void setIdentity(String identity) {
     this.identity = identity;
   }
   
 
 
 
   public String getIdentity()
   {
     return this.identity;
   }
   
   public void setPermissionCode(String permissionCode) {
     this.am = permissionCode;
   }
   
 
 
 
   public String getPermissionCode()
   {
     return this.am;
   }
   
 
   public String toString()
   {
     return 
     
 
 
 
 
 
 
       new ToStringBuilder(this).append("id", this.id).append("taskId", this.taskId).append("instId", this.o).append("type", this.type).append("identityName", this.al).append("identity", this.identity).append("permissionCode", this.am).toString();
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\model\TaskIdentityLink.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */