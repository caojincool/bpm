/*    */ package com.dstz.bus.model;
/*    */ 
/*    */ import com.dstz.base.core.model.BaseModel;
/*    */ import com.dstz.bus.api.model.IBusColumnCtrl;
/*    */ import org.hibernate.validator.constraints.NotEmpty;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BusColumnCtrl
/*    */   extends BaseModel
/*    */   implements IBusColumnCtrl
/*    */ {
/*    */   @NotEmpty
/*    */   private String p;
/*    */   @NotEmpty
/*    */   private String type;
/*    */   private String q;
/*    */   private String r;
/*    */   
/*    */   public String getId()
/*    */   {
/* 24 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(String id) {
/* 28 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getColumnId() {
/* 32 */     return this.p;
/*    */   }
/*    */   
/*    */   public void setColumnId(String columnId) {
/* 36 */     this.p = columnId;
/*    */   }
/*    */   
/*    */   public String getType() {
/* 40 */     return this.type;
/*    */   }
/*    */   
/*    */   public void setType(String type) {
/* 44 */     this.type = type;
/*    */   }
/*    */   
/*    */   public String getConfig() {
/* 48 */     return this.q;
/*    */   }
/*    */   
/*    */   public void setConfig(String config) {
/* 52 */     this.q = config;
/*    */   }
/*    */   
/*    */   public String getValidRule() {
/* 56 */     return this.r;
/*    */   }
/*    */   
/*    */   public void setValidRule(String validRule) {
/* 60 */     this.r = validRule;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\model\BusColumnCtrl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */