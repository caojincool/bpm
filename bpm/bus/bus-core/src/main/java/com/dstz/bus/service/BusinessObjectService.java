/*    */ package com.dstz.bus.service;
/*    */ 
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.bus.api.model.IBusinessObject;
/*    */ import com.dstz.bus.api.service.IBusinessObjectService;
/*    */ import com.dstz.bus.manager.BusinessObjectManager;
/*    */ import java.util.List;
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
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class BusinessObjectService
/*    */   implements IBusinessObjectService
/*    */ {
/*    */   @Autowired
/*    */   BusinessObjectManager k;
/*    */   
/*    */   public IBusinessObject getByKey(String key)
/*    */   {
/* 30 */     return this.k.getByKey(key);
/*    */   }
/*    */   
/*    */   public IBusinessObject getFilledByKey(String key)
/*    */   {
/* 35 */     return this.k.getFilledByKey(key);
/*    */   }
/*    */   
/*    */   public List<JSONObject> boTreeData(String key)
/*    */   {
/* 40 */     return this.k.boTreeData(key);
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\service\BusinessObjectService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */