/*    */ package com.dstz.bus.executor.parseval;
/*    */ 
/*    */ import com.dstz.base.core.util.StringUtil;
/*    */ import com.dstz.bus.model.BusTableRel;
/*    */ import com.dstz.bus.model.BusinessColumn;
/*    */ import com.dstz.bus.model.BusinessTable;
/*    */ import java.util.Map;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class ParseValTypeExecutor
/*    */   extends ParseValExecuteChain
/*    */ {
/*    */   public int getSn()
/*    */   {
/* 22 */     return 0;
/*    */   }
/*    */   
/*    */   protected void a(ParseValParam param)
/*    */   {
/* 27 */     String key = param.getKey();
/* 28 */     Object value = param.getValue();
/*    */     
/* 30 */     if ((value == null) || (StringUtil.isEmpty(value.toString()))) {
/* 31 */       return;
/*    */     }
/*    */     
/* 34 */     BusinessColumn column = param.getBusTableRel().getTable().d(key);
/* 35 */     if (column == null) {
/* 36 */       param.getData().put(key, value);
/* 37 */       return;
/*    */     }
/*    */     
/* 40 */     param.getData().put(column.getKey(), column.value(value.toString()));
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\executor\parseval\ParseValTypeExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */