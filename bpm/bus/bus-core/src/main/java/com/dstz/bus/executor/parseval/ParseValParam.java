/*    */ package com.dstz.bus.executor.parseval;
/*    */ 
/*    */ import com.dstz.bus.model.BusTableRel;
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
/*    */ public class ParseValParam
/*    */ {
/*    */   private String key;
/*    */   private Object value;
/*    */   private Map<String, Object> c;
/*    */   private BusTableRel d;
/*    */   
/*    */   public ParseValParam(String key, Object value, Map<String, Object> data, BusTableRel busTableRel)
/*    */   {
/* 24 */     this.key = key;
/* 25 */     this.value = value;
/* 26 */     this.c = data;
/* 27 */     this.d = busTableRel;
/*    */   }
/*    */   
/*    */   public String getKey() {
/* 31 */     return this.key;
/*    */   }
/*    */   
/*    */   public Object getValue() {
/* 35 */     return this.value;
/*    */   }
/*    */   
/*    */   public Map<String, Object> getData() {
/* 39 */     return this.c;
/*    */   }
/*    */   
/*    */   public BusTableRel getBusTableRel() {
/* 43 */     return this.d;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\executor\parseval\ParseValParam.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */