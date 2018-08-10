/*    */ package com.dstz.bus.model;
/*    */ 
/*    */ import com.dstz.bus.api.model.IBusTableRelFk;
/*    */ import java.io.Serializable;
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
/*    */ public class BusTableRelFk
/*    */   implements IBusTableRelFk, Serializable
/*    */ {
/*    */   private String from;
/*    */   private String type;
/*    */   private String value;
/*    */   
/*    */   public String getFrom()
/*    */   {
/* 31 */     return this.from;
/*    */   }
/*    */   
/*    */   public void setFrom(String from) {
/* 35 */     this.from = from;
/*    */   }
/*    */   
/*    */   public String getType()
/*    */   {
/* 40 */     return this.type;
/*    */   }
/*    */   
/*    */   public void setType(String type) {
/* 44 */     this.type = type;
/*    */   }
/*    */   
/*    */   public String getValue()
/*    */   {
/* 49 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(String value) {
/* 53 */     this.value = value;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\model\BusTableRelFk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */