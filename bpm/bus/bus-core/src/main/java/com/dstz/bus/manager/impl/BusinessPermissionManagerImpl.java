/*     */ package com.dstz.bus.manager.impl;
/*     */ 
/*     */ import com.alibaba.fastjson.JSONArray;
/*     */ import com.alibaba.fastjson.JSONObject;
/*     */ import com.dstz.base.api.exception.BusinessException;
/*     */ import com.dstz.base.manager.impl.BaseManager;
/*     */ import com.dstz.bus.api.constant.RightsType;
/*     */ import com.dstz.bus.dao.BusinessPermissionDao;
/*     */ import com.dstz.bus.manager.BusinessObjectManager;
/*     */ import com.dstz.bus.manager.BusinessPermissionManager;
/*     */ import com.dstz.bus.model.BusTableRel;
/*     */ import com.dstz.bus.model.BusinessColumn;
/*     */ import com.dstz.bus.model.BusinessObject;
/*     */ import com.dstz.bus.model.BusinessPermission;
/*     */ import com.dstz.bus.model.BusinessTable;
/*     */ import com.dstz.bus.model.permission.AbstractPermission;
/*     */ import com.dstz.bus.model.permission.BusColumnPermission;
/*     */ import com.dstz.bus.model.permission.BusObjPermission;
/*     */ import com.dstz.bus.model.permission.BusTablePermission;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Resource;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Service("busObjPermissionManager")
/*     */ public class BusinessPermissionManagerImpl
/*     */   extends BaseManager<String, BusinessPermission>
/*     */   implements BusinessPermissionManager
/*     */ {
/*     */   @Resource
/*     */   BusinessPermissionDao j;
/*     */   @Autowired
/*     */   BusinessObjectManager k;
/*     */   
/*     */   public BusinessPermission getByObjTypeAndObjVal(String objType, String objVal)
/*     */   {
/*  44 */     return this.j.getByObjTypeAndObjVal(objType, objVal);
/*     */   }
/*     */   
/*     */   public BusinessPermission getByObjTypeAndObjVal(String objType, String objVal, String defaultBoKeys)
/*     */   {
/*  49 */     BusinessPermission oldPermission = getByObjTypeAndObjVal(objType, objVal);
/*  50 */     if (oldPermission == null) {
/*  51 */       oldPermission = new BusinessPermission();
/*     */     }
/*     */     
/*  54 */     BusinessPermission businessPermission = new BusinessPermission();
/*  55 */     businessPermission.setObjType(objType);
/*  56 */     businessPermission.setObjVal(objVal);
/*  57 */     for (String boKey : defaultBoKeys.split(","))
/*     */     {
/*  59 */       BusinessObject bo = this.k.getFilledByKey(boKey);
/*  60 */       if (bo == null) {
/*  61 */         throw new BusinessException(boKey + " 业务对象丢失！");
/*     */       }
/*     */       
/*  64 */       BusObjPermission busObjPermission = oldPermission.c(boKey);
/*  65 */       if (busObjPermission == null) {
/*  66 */         busObjPermission = new BusObjPermission();
/*  67 */         busObjPermission.setKey(boKey);
/*  68 */         busObjPermission.setName(bo.getName());
/*  69 */         a(busObjPermission);
/*     */       }
/*  71 */       businessPermission.getBusObjMap().put(boKey, busObjPermission);
/*     */       
/*     */ 
/*  74 */       for (BusTableRel rel : bo.getRelation().list()) {
/*  75 */         BusTablePermission busTablePermission = (BusTablePermission)busObjPermission.getTableMap().get(rel.getTableKey());
/*  76 */         if (busTablePermission == null) {
/*  77 */           busTablePermission = new BusTablePermission();
/*  78 */           busTablePermission.setKey(rel.getTableKey());
/*  79 */           busTablePermission.setComment(rel.getTableComment());
/*     */         }
/*  81 */         busObjPermission.getTableMap().put(rel.getTableKey(), busTablePermission);
/*     */         
/*     */ 
/*  84 */         for (BusinessColumn column : rel.getTable().getColumnsWithoutPk()) {
/*  85 */           BusColumnPermission busColumnPermission = (BusColumnPermission)busTablePermission.getColumnMap().get(column.getKey());
/*  86 */           if (busColumnPermission == null) {
/*  87 */             busColumnPermission = new BusColumnPermission();
/*  88 */             busColumnPermission.setKey(column.getKey());
/*  89 */             busColumnPermission.setComment(column.getComment());
/*     */           }
/*  91 */           busTablePermission.getColumnMap().put(column.getKey(), busColumnPermission);
/*     */         }
/*     */         
/*     */ 
/*  95 */         Object it = busTablePermission.getColumnMap().entrySet().iterator();
/*  96 */         while (((Iterator)it).hasNext()) {
/*  97 */           Map.Entry<String, BusColumnPermission> entry = (Map.Entry)((Iterator)it).next();
/*  98 */           if (rel.getTable().d((String)entry.getKey()) == null) {
/*  99 */             ((Iterator)it).remove();
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 105 */       Object it = busObjPermission.getTableMap().entrySet().iterator();
/* 106 */       while (((Iterator)it).hasNext()) {
/* 107 */         Map.Entry<String, BusTablePermission> entry = (Map.Entry)((Iterator)it).next();
/* 108 */         if (bo.getRelation().a((String)entry.getKey()) == null) {
/* 109 */           ((Iterator)it).remove();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 114 */     return businessPermission;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void a(AbstractPermission permission)
/*     */   {
/* 126 */     JSONArray jsonArray = new JSONArray();
/* 127 */     JSONObject json = new JSONObject();
/* 128 */     json.put("type", "everyone");
/* 129 */     json.put("desc", "所有人");
/* 130 */     jsonArray.add(json);
/* 131 */     permission.getRights().put(RightsType.getDefalut().getKey(), jsonArray);
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\manager\impl\BusinessPermissionManagerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */