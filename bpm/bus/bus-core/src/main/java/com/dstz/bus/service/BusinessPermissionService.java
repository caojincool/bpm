/*    */ package com.dstz.bus.service;
/*    */ 
/*    */ import com.alibaba.fastjson.JSONArray;
/*    */ import com.dstz.base.core.util.BeanUtils;
/*    */ import com.dstz.base.core.util.StringUtil;
/*    */ import com.dstz.bus.api.constant.RightsType;
/*    */ import com.dstz.bus.api.service.IBusinessPermissionService;
/*    */ import com.dstz.bus.manager.BusinessObjectManager;
/*    */ import com.dstz.bus.manager.BusinessPermissionManager;
/*    */ import com.dstz.bus.model.BusinessPermission;
/*    */ import com.dstz.bus.model.permission.AbstractPermission;
/*    */ import com.dstz.bus.model.permission.BusColumnPermission;
/*    */ import com.dstz.bus.model.permission.BusObjPermission;
/*    */ import com.dstz.bus.model.permission.BusTablePermission;
/*    */ import com.dstz.sys.api2.permission.PermissionCalculatorFactory;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service
/*    */ public class BusinessPermissionService implements IBusinessPermissionService
/*    */ {
/*    */   @Autowired
/*    */   BusinessPermissionManager T;
/*    */   @Autowired
/*    */   BusinessObjectManager k;
/*    */   
/*    */   public BusinessPermission a(String objType, String objVal, String defalutBoKeys, boolean calculate)
/*    */   {
/* 31 */     BusinessPermission businessPermission = null;
/* 32 */     if (StringUtil.isNotEmpty(defalutBoKeys)) {
/* 33 */       businessPermission = this.T.getByObjTypeAndObjVal(objType, objVal, defalutBoKeys);
/*    */     } else {
/* 35 */       businessPermission = this.T.getByObjTypeAndObjVal(objType, objVal);
/*    */     }
/* 37 */     if (businessPermission == null) {
/* 38 */       return new BusinessPermission();
/*    */     }
/*    */     
/* 41 */     if (calculate) {
/* 42 */       a(businessPermission);
/*    */     }
/* 44 */     return businessPermission;
/*    */   }
/*    */   
/*    */   private void a(BusinessPermission businessPermission)
/*    */   {
/* 49 */     for (Map.Entry<String, BusObjPermission> entry : businessPermission.getBusObjMap().entrySet()) {
/* 50 */       busObjPermission = (BusObjPermission)entry.getValue();
/* 51 */       b(busObjPermission);
/* 52 */       for (Map.Entry<String, BusTablePermission> etry : busObjPermission.getTableMap().entrySet()) {
/* 53 */         busTablePermission = (BusTablePermission)etry.getValue();
/*    */         
/* 55 */         if (BeanUtils.isEmpty(busTablePermission.getRights())) {
/* 56 */           busTablePermission.setResult(busObjPermission.getResult());
/*    */         } else {
/* 58 */           b(busTablePermission);
/*    */         }
/*    */         
/* 61 */         for (Map.Entry<String, BusColumnPermission> ery : busTablePermission.getColumnMap().entrySet()) {
/* 62 */           BusColumnPermission busColumnPermission = (BusColumnPermission)ery.getValue();
/*    */           
/* 64 */           if (BeanUtils.isEmpty(busColumnPermission.getRights())) {
/* 65 */             busColumnPermission.setResult(busTablePermission.getResult());
/*    */           } else {
/* 67 */             b(busColumnPermission);
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */     
/*    */ 
/*    */     BusObjPermission busObjPermission;
/*    */     
/*    */ 
/*    */     BusTablePermission busTablePermission;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   private void b(AbstractPermission permission)
/*    */   {
/* 84 */     for (RightsType rightsType : ) {
/* 85 */       JSONArray jsonArray = (JSONArray)permission.getRights().get(rightsType.getKey());
/* 86 */       boolean b = PermissionCalculatorFactory.haveRights(jsonArray);
/* 87 */       if (b) {
/* 88 */         permission.setResult(rightsType.getKey());
/* 89 */         break;
/*    */       }
/*    */     }
/*    */     
/* 93 */     if (StringUtil.isEmpty(permission.getResult())) {
/* 94 */       permission.setResult(RightsType.values()[(RightsType.values().length - 1)].getKey());
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\service\BusinessPermissionService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */