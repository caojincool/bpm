/*    */ package com.dstz.bus.model.permission;
/*    */ 
/*    */ import com.dstz.bus.api.model.permission.IBusTablePermission;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BusTablePermission
/*    */   extends AbstractPermission
/*    */   implements IBusTablePermission
/*    */ {
/*    */   private String key;
/*    */   private String comment;
/* 20 */   private Map<String, BusColumnPermission> S = new HashMap();
/*    */   
/*    */   public String getKey() {
/* 23 */     return this.key;
/*    */   }
/*    */   
/*    */   public void setKey(String key) {
/* 27 */     this.key = key;
/*    */   }
/*    */   
/*    */   public String getComment() {
/* 31 */     return this.comment;
/*    */   }
/*    */   
/*    */   public void setComment(String comment) {
/* 35 */     this.comment = comment;
/*    */   }
/*    */   
/*    */   public Map<String, BusColumnPermission> getColumnMap() {
/* 39 */     return this.S;
/*    */   }
/*    */   
/*    */   public void setColumnMap(Map<String, BusColumnPermission> columnMap) {
/* 43 */     this.S = columnMap;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\model\permission\BusTablePermission.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */