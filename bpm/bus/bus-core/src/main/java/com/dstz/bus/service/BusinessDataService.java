/*     */ package com.dstz.bus.service;
/*     */ 
/*     */ import com.alibaba.fastjson.JSONArray;
/*     */ import com.alibaba.fastjson.JSONObject;
/*     */ import com.dstz.base.core.executor.ExecutorFactory;
/*     */ import com.dstz.base.core.util.BeanUtils;
/*     */ import com.dstz.bus.api.constant.BusTableRelType;
/*     */ import com.dstz.bus.api.model.IBusinessData;
/*     */ import com.dstz.bus.api.model.IBusinessObject;
/*     */ import com.dstz.bus.api.model.IBusinessPermission;
/*     */ import com.dstz.bus.api.service.IBusinessDataService;
/*     */ import com.dstz.bus.executor.assemblyval.AssemblyValExecuteChain;
/*     */ import com.dstz.bus.executor.assemblyval.AssemblyValParam;
/*     */ import com.dstz.bus.executor.parseval.ParseValExecuteChain;
/*     */ import com.dstz.bus.executor.parseval.ParseValParam;
/*     */ import com.dstz.bus.manager.BusinessObjectManager;
/*     */ import com.dstz.bus.model.BusTableRel;
/*     */ import com.dstz.bus.model.BusinessData;
/*     */ import com.dstz.bus.model.BusinessObject;
/*     */ import com.dstz.bus.model.BusinessTable;
/*     */ import com.dstz.bus.model.permission.BusObjPermission;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service
/*     */ public class BusinessDataService implements IBusinessDataService
/*     */ {
/*     */   @Autowired
/*     */   BusinessObjectManager k;
/*     */   
/*     */   public void saveFormDefData(JSONObject data, IBusinessPermission businessPermission)
/*     */   {
/*  37 */     for (Map.Entry<String, Object> entry : data.entrySet()) {
/*  38 */       String boKey = (String)entry.getKey();
/*  39 */       JSONObject boData = (JSONObject)entry.getValue();
/*  40 */       BusinessData businessData = (BusinessData)parseBusinessData(boData, boKey);
/*  41 */       businessData.getBusTableRel().getBusObj().setPermission((BusObjPermission)businessPermission.getBusObj(boKey));
/*  42 */       BusinessDataPersistenceServiceFactory.saveData(businessData);
/*     */     }
/*     */   }
/*     */   
/*     */   public JSONObject getFormDefData(IBusinessObject businessObject, Object id)
/*     */   {
/*  48 */     BusinessData businessData = BusinessDataPersistenceServiceFactory.loadData((BusinessObject)businessObject, id);
/*  49 */     if (BeanUtils.isEmpty(id)) {
/*  50 */       b(businessData);
/*     */     }
/*     */     
/*  53 */     JSONObject data = new JSONObject();
/*     */     
/*  55 */     a(data, businessData);
/*  56 */     return data;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void b(BusinessData businessData)
/*     */   {
/*  68 */     BusTableRel busTableRel = businessData.getBusTableRel();
/*  69 */     businessData.setDbData(busTableRel.getTable().initDbData());
/*     */     
/*     */ 
/*  72 */     for (BusTableRel rel : busTableRel.getChildren()) {
/*  73 */       if (BusTableRelType.ONE_TO_ONE.equalsWithKey(rel.getType())) {
/*  74 */         BusinessData childData = new BusinessData();
/*  75 */         childData.setBusTableRel(rel);
/*  76 */         b(childData);
/*  77 */         businessData.a(childData);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public JSONObject assemblyFormDefData(IBusinessData businessData)
/*     */   {
/*  85 */     JSONObject data = new JSONObject();
/*  86 */     a(data, businessData);
/*  87 */     return data;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void a(JSONObject data, IBusinessData ibusinessData)
/*     */   {
/*  99 */     BusinessData businessData = (BusinessData)ibusinessData;
/*     */     
/* 101 */     AssemblyValParam param = new AssemblyValParam(data, businessData);
/* 102 */     ExecutorFactory.execute(AssemblyValExecuteChain.class, param);
/*     */     
/*     */ 
/* 105 */     for (Map.Entry<String, List<BusinessData>> entry : businessData.getChildren().entrySet()) {
/* 106 */       String tableKey = (String)entry.getKey();
/* 107 */       List<BusinessData> children = (List)entry.getValue();
/* 108 */       if (BusTableRelType.ONE_TO_ONE.equalsWithKey(((BusinessData)children.get(0)).getBusTableRel().getType())) {
/* 109 */         JSONObject cData = new JSONObject();
/* 110 */         a(cData, (IBusinessData)children.get(0));
/* 111 */         data.put(tableKey, cData);
/*     */       } else {
/* 113 */         JSONArray dataList = new JSONArray();
/* 114 */         for (BusinessData bd : children) {
/* 115 */           JSONObject cData = new JSONObject();
/* 116 */           a(cData, bd);
/* 117 */           dataList.add(cData);
/*     */         }
/* 119 */         data.put(tableKey + "List", dataList);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private BusinessData a(Object object, BusTableRel relation)
/*     */   {
/* 135 */     BusinessData businessData = new BusinessData();
/* 136 */     businessData.setBusTableRel(relation);
/*     */     
/* 138 */     if ((object instanceof JSONObject)) {
/* 139 */       JSONObject jsonObject = (JSONObject)object;
/* 140 */       Map<String, Object> bdData = new HashMap();
/* 141 */       for (Map.Entry<String, Object> enty : jsonObject.entrySet())
/*     */       {
/* 143 */         if ((!(enty.getValue() instanceof JSONArray)) && (!(enty.getValue() instanceof JSONObject))) {
/* 144 */           ParseValParam param = new ParseValParam((String)enty.getKey(), enty.getValue(), bdData, relation);
/* 145 */           ExecutorFactory.execute(ParseValExecuteChain.class, param);
/*     */         }
/*     */         
/*     */         BusTableRel rel;
/* 149 */         if ((enty.getValue() instanceof JSONArray)) {
/* 150 */           String tableKey = ((String)enty.getKey()).substring(0, ((String)enty.getKey()).length() - 4);
/* 151 */           rel = relation.a(tableKey);
/* 152 */           for (Object obj : (JSONArray)enty.getValue()) {
/* 153 */             businessData.a(a(obj, rel));
/*     */           }
/*     */         }
/*     */         
/* 157 */         if ((enty.getValue() instanceof JSONObject)) {
/* 158 */           BusTableRel rel = relation.a((String)enty.getKey());
/* 159 */           businessData.a(a(enty.getValue(), rel));
/*     */         }
/*     */       }
/* 162 */       businessData.setData(bdData);
/*     */     }
/* 164 */     return businessData;
/*     */   }
/*     */   
/*     */   public void removeData(IBusinessObject businessObject, Object id)
/*     */   {
/* 169 */     BusinessDataPersistenceServiceFactory.removeData((BusinessObject)businessObject, id);
/*     */   }
/*     */   
/*     */   public void saveData(IBusinessData businessData)
/*     */   {
/* 174 */     BusinessDataPersistenceServiceFactory.saveData((BusinessData)businessData);
/*     */   }
/*     */   
/*     */   public IBusinessData loadData(IBusinessObject businessObject, Object id)
/*     */   {
/* 179 */     return BusinessDataPersistenceServiceFactory.loadData((BusinessObject)businessObject, id);
/*     */   }
/*     */   
/*     */   public IBusinessData parseBusinessData(JSONObject jsonObject, String boKey)
/*     */   {
/* 184 */     BusinessObject businessObject = this.k.getFilledByKey(boKey);
/* 185 */     return a(jsonObject, businessObject.getRelation());
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\service\BusinessDataService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */