/*    */ package com.dstz.bus.service;
/*    */ 
/*    */ import com.dstz.base.api.exception.BusinessException;
/*    */ import com.dstz.base.core.util.AppUtil;
/*    */ import com.dstz.bus.model.BusTableRel;
/*    */ import com.dstz.bus.model.BusinessData;
/*    */ import com.dstz.bus.model.BusinessObject;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
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
/*    */ public class BusinessDataPersistenceServiceFactory
/*    */ {
/*    */   private static BusinessDataPersistenceService e(String type)
/*    */   {
/* 34 */     Map<String, BusinessDataPersistenceService> map = AppUtil.getImplInstance(BusinessDataPersistenceService.class);
/* 35 */     for (Map.Entry<String, BusinessDataPersistenceService> entry : map.entrySet()) {
/* 36 */       if (((BusinessDataPersistenceService)entry.getValue()).type().equals(type)) {
/* 37 */         return (BusinessDataPersistenceService)entry.getValue();
/*    */       }
/*    */     }
/* 40 */     throw new BusinessException(String.format("找不到类型[%s]的业务数据持久化的实现类", new Object[] { type }));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void saveData(BusinessData businessData)
/*    */   {
/* 51 */     BusinessObject businessObject = businessData.getBusTableRel().getBusObj();
/* 52 */     BusinessDataPersistenceService businessDataPersistenceService = e(businessObject.getPersistenceType());
/* 53 */     businessDataPersistenceService.saveData(businessData);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static BusinessData loadData(BusinessObject businessObject, Object id)
/*    */   {
/* 66 */     BusinessDataPersistenceService businessDataPersistenceService = e(businessObject.getPersistenceType());
/* 67 */     return businessDataPersistenceService.loadData(businessObject, id);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void removeData(BusinessObject businessObject, Object id)
/*    */   {
/* 80 */     BusinessDataPersistenceService businessDataPersistenceService = e(businessObject.getPersistenceType());
/* 81 */     businessDataPersistenceService.removeData(businessObject, id);
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\service\BusinessDataPersistenceServiceFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */