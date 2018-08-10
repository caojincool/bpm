/*    */ package com.dstz.bus.service.impl;
/*    */ 
/*    */ import com.dstz.bus.api.constant.BusinessObjectPersistenceType;
/*    */ import com.dstz.bus.model.BusinessData;
/*    */ import com.dstz.bus.model.BusinessObject;
/*    */ import com.dstz.bus.service.BusinessDataPersistenceService;
/*    */ import org.springframework.stereotype.Service;
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
/*    */ @Service
/*    */ public class BusinessDataPersistenceInstService
/*    */   implements BusinessDataPersistenceService
/*    */ {
/*    */   public String type()
/*    */   {
/* 24 */     return BusinessObjectPersistenceType.INST.getKey();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void saveData(BusinessData businessData) {}
/*    */   
/*    */ 
/*    */ 
/*    */   public BusinessData loadData(BusinessObject businessObject, Object id)
/*    */   {
/* 36 */     return null;
/*    */   }
/*    */   
/*    */   public void removeData(BusinessObject businessObject, Object id) {}
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\service\impl\BusinessDataPersistenceInstService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */