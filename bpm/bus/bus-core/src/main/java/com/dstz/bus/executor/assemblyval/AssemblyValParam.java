/*    */ package com.dstz.bus.executor.assemblyval;
/*    */ 
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.bus.model.BusinessData;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AssemblyValParam
/*    */ {
/*    */   private JSONObject a;
/*    */   private BusinessData b;
/*    */   
/*    */   public AssemblyValParam(JSONObject data, BusinessData businessData)
/*    */   {
/* 21 */     this.a = data;
/* 22 */     this.b = businessData;
/*    */   }
/*    */   
/*    */   public JSONObject getData() {
/* 26 */     return this.a;
/*    */   }
/*    */   
/*    */   public BusinessData getBusinessData() {
/* 30 */     return this.b;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\executor\assemblyval\AssemblyValParam.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */