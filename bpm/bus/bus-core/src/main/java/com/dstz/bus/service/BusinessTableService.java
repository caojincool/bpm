/*    */ package com.dstz.bus.service;
/*    */ 
/*    */ import com.dstz.bus.api.model.IBusinessTable;
/*    */ import com.dstz.bus.api.service.IBusinessTableService;
/*    */ import com.dstz.bus.manager.BusinessTableManager;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
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
/*    */ @Service
/*    */ public class BusinessTableService
/*    */   implements IBusinessTableService
/*    */ {
/*    */   @Autowired
/*    */   BusinessTableManager h;
/*    */   
/*    */   public IBusinessTable getByKey(String key)
/*    */   {
/* 26 */     return this.h.getByKey(key);
/*    */   }
/*    */   
/*    */   public IBusinessTable getFilledByKey(String key)
/*    */   {
/* 31 */     return this.h.getFilledByKey(key);
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\service\BusinessTableService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */