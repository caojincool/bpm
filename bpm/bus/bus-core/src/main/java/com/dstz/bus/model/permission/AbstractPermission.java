/*    */ package com.dstz.bus.model.permission;
/*    */ 
/*    */ import com.alibaba.fastjson.JSONArray;
/*    */ import com.dstz.bus.api.model.permission.IAbstractPermission;
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
/*    */ public abstract class AbstractPermission
/*    */   implements IAbstractPermission
/*    */ {
/* 22 */   protected Map<String, JSONArray> P = new HashMap();
/*    */   
/*    */ 
/*    */ 
/*    */   protected String Q;
/*    */   
/*    */ 
/*    */ 
/*    */   public String getResult()
/*    */   {
/* 32 */     return this.Q;
/*    */   }
/*    */   
/*    */   public void setResult(String result) {
/* 36 */     this.Q = result;
/*    */   }
/*    */   
/*    */   public Map<String, JSONArray> getRights() {
/* 40 */     return this.P;
/*    */   }
/*    */   
/*    */   public void setRights(Map<String, JSONArray> rights) {
/* 44 */     this.P = rights;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\model\permission\AbstractPermission.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */