/*    */ package com.dstz.bus.model.permission;
/*    */ 
/*    */ import com.dstz.bus.api.model.permission.IBusColumnPermission;
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
/*    */ 
/*    */ public class BusColumnPermission
/*    */   extends AbstractPermission
/*    */   implements IBusColumnPermission
/*    */ {
/*    */   private String key;
/*    */   private String comment;
/*    */   
/*    */   public String getKey()
/*    */   {
/* 26 */     return this.key;
/*    */   }
/*    */   
/*    */   public void setKey(String key) {
/* 30 */     this.key = key;
/*    */   }
/*    */   
/*    */   public String getComment() {
/* 34 */     return this.comment;
/*    */   }
/*    */   
/*    */   public void setComment(String comment) {
/* 38 */     this.comment = comment;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\model\permission\BusColumnPermission.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */