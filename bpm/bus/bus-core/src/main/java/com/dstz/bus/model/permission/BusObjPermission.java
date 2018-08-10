/*    */ package com.dstz.bus.model.permission;
/*    */ 
/*    */ import com.dstz.bus.api.model.permission.IBusObjPermission;
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
/*    */ 
/*    */ public class BusObjPermission
/*    */   extends AbstractPermission
/*    */   implements IBusObjPermission
/*    */ {
/*    */   private String key;
/*    */   private String name;
/* 34 */   private Map<String, BusTablePermission> R = new HashMap();
/*    */   
/*    */   public String getKey() {
/* 37 */     return this.key;
/*    */   }
/*    */   
/*    */   public void setKey(String key) {
/* 41 */     this.key = key;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 45 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 49 */     this.name = name;
/*    */   }
/*    */   
/*    */   public Map<String, BusTablePermission> getTableMap() {
/* 53 */     return this.R;
/*    */   }
/*    */   
/*    */   public void setTableMap(Map<String, BusTablePermission> tableMap) {
/* 57 */     this.R = tableMap;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\model\permission\BusObjPermission.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */