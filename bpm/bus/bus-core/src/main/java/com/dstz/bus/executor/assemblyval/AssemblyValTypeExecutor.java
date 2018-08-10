/*    */ package com.dstz.bus.executor.assemblyval;
/*    */ 
/*    */ import com.alibaba.fastjson.JSON;
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.base.api.constant.ColumnType;
/*    */ import com.dstz.base.core.util.time.DateFormatUtil;
/*    */ import com.dstz.bus.model.BusColumnCtrl;
/*    */ import com.dstz.bus.model.BusTableRel;
/*    */ import com.dstz.bus.model.BusinessColumn;
/*    */ import com.dstz.bus.model.BusinessData;
/*    */ import com.dstz.bus.model.BusinessTable;
/*    */ import java.util.Date;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class AssemblyValTypeExecutor
/*    */   extends AssemblyValExecuteChain
/*    */ {
/*    */   public int getSn()
/*    */   {
/* 29 */     return 0;
/*    */   }
/*    */   
/*    */   protected void a(AssemblyValParam param)
/*    */   {
/* 34 */     BusinessData businessData = param.getBusinessData();
/* 35 */     JSONObject data = param.getData();
/*    */     
/* 37 */     for (Map.Entry<String, Object> entry : businessData.getData().entrySet()) {
/* 38 */       BusinessColumn column = businessData.getBusTableRel().getTable().d((String)entry.getKey());
/*    */       
/* 40 */       if ((ColumnType.DATE.equalsWithKey(column.getType())) && (entry.getValue() != null)) {
/* 41 */         JSONObject config = JSON.parseObject(column.getCtrl().getConfig());
/* 42 */         data.put(column.getKey(), DateFormatUtil.format((Date)entry.getValue(), config.getString("format")));
/*    */       } else {
/* 44 */         data.put((String)entry.getKey(), entry.getValue());
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\executor\assemblyval\AssemblyValTypeExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */