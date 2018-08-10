/*    */ package com.dstz.bpm.core.model;
/*    */ 
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.bus.api.model.IBusinessPermission;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BpmOverallView
/*    */ {
/*    */   public static final String K = "override";
/*    */   public static final String L = "edit";
/*    */   public static final String M = "newVersion";
/*    */   private BpmDefinition N;
/* 25 */   private List<IBusinessPermission> O = new ArrayList();
/*    */   
/*    */ 
/*    */ 
/* 29 */   private String importType = "edit";
/*    */   
/*    */   private String defId;
/*    */   private Boolean P;
/*    */   private JSONObject Q;
/*    */   
/*    */   public List<IBusinessPermission> getFormRights()
/*    */   {
/* 37 */     return this.O;
/*    */   }
/*    */   
/*    */   public void setFormRights(List<IBusinessPermission> formRights) {
/* 41 */     this.O = formRights;
/*    */   }
/*    */   
/*    */   public String getImportType() {
/* 45 */     return this.importType;
/*    */   }
/*    */   
/*    */   public void setImportType(String importType) {
/* 49 */     this.importType = importType;
/*    */   }
/*    */   
/*    */   public Boolean getIsUpdateVersion() {
/* 53 */     return this.P;
/*    */   }
/*    */   
/*    */   public void setIsUpdateVersion(Boolean isUpdateVersion) {
/* 57 */     this.P = isUpdateVersion;
/*    */   }
/*    */   
/*    */   public JSONObject getDefSetting() {
/* 61 */     return this.Q;
/*    */   }
/*    */   
/*    */   public void setDefSetting(JSONObject defSetting) {
/* 65 */     this.Q = defSetting;
/*    */   }
/*    */   
/*    */   public BpmDefinition getBpmDefinition() {
/* 69 */     return this.N;
/*    */   }
/*    */   
/*    */   public void setBpmDefinition(BpmDefinition bpmDefinition) {
/* 73 */     this.N = bpmDefinition;
/*    */   }
/*    */   
/*    */   public String getDefId() {
/* 77 */     return this.defId;
/*    */   }
/*    */   
/*    */   public void setDefId(String defId) {
/* 81 */     this.defId = defId;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\model\BpmOverallView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */