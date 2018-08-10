 package com.dstz.bpm.core.model;
 
 import com.dstz.base.api.model.IDModel;
 import org.apache.commons.lang3.builder.ToStringBuilder;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class BpmBusLink
   implements IDModel
 {
   protected String id;
   protected String defId;
   protected String o;
   protected String p;
   protected String q;
   
   public void setId(String id)
   {
     this.id = id;
   }
   
 
 
 
   public String getId()
   {
     return this.id;
   }
   
   public void setDefId(String defId) {
     this.defId = defId;
   }
   
 
 
 
   public String getDefId()
   {
     return this.defId;
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
     
 
 
 
 
       new ToStringBuilder(this).append("id", this.id).append("defId", this.defId).append("instId", this.o).append("bizKey", this.p).append("bizIdentify", this.q).toString();
   }
   
   public String getBizId() {
     return this.p;
   }
   
   public void setBizId(String bizId) {
     this.p = bizId;
   }
   
   public String getBizCode() {
     return this.q;
   }
   
   public void setBizCode(String bizCode) {
     this.q = bizCode;
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\model\BpmBusLink.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */