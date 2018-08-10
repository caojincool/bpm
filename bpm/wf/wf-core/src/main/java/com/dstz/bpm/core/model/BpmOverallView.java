 package com.dstz.bpm.core.model;
 
 import com.alibaba.fastjson.JSONObject;
 import com.dstz.bus.api.model.IBusinessPermission;
 import java.util.ArrayList;
 import java.util.List;
 
 
 
 
 
 
 
 
 
 
 
 
 public class BpmOverallView
 {
   public static final String K = "override";
   public static final String L = "edit";
   public static final String M = "newVersion";
   private BpmDefinition N;
   private List<IBusinessPermission> O = new ArrayList();
   
 
 
   private String importType = "edit";
   
   private String defId;
   private Boolean P;
   private JSONObject Q;
   
   public List<IBusinessPermission> getFormRights()
   {
     return this.O;
   }
   
   public void setFormRights(List<IBusinessPermission> formRights) {
     this.O = formRights;
   }
   
   public String getImportType() {
     return this.importType;
   }
   
   public void setImportType(String importType) {
     this.importType = importType;
   }
   
   public Boolean getIsUpdateVersion() {
     return this.P;
   }
   
   public void setIsUpdateVersion(Boolean isUpdateVersion) {
     this.P = isUpdateVersion;
   }
   
   public JSONObject getDefSetting() {
     return this.Q;
   }
   
   public void setDefSetting(JSONObject defSetting) {
     this.Q = defSetting;
   }
   
   public BpmDefinition getBpmDefinition() {
     return this.N;
   }
   
   public void setBpmDefinition(BpmDefinition bpmDefinition) {
     this.N = bpmDefinition;
   }
   
   public String getDefId() {
     return this.defId;
   }
   
   public void setDefId(String defId) {
     this.defId = defId;
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\model\BpmOverallView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */