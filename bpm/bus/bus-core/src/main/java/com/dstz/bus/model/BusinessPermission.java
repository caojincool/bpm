/*     */ package com.dstz.bus.model;
/*     */ 
/*     */ import com.alibaba.fastjson.JSONObject;
/*     */ import com.dstz.base.core.model.BaseModel;
/*     */ import com.dstz.base.core.util.JsonUtil;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.bus.api.constant.RightsType;
/*     */ import com.dstz.bus.api.model.IBusinessPermission;
/*     */ import com.dstz.bus.api.model.permission.IBusColumnPermission;
/*     */ import com.dstz.bus.api.model.permission.IBusObjPermission;
/*     */ import com.dstz.bus.api.model.permission.IBusTablePermission;
/*     */ import com.dstz.bus.model.permission.BusObjPermission;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BusinessPermission
/*     */   extends BaseModel
/*     */   implements IBusinessPermission
/*     */ {
/*     */   private String G;
/*     */   private String H;
/*     */   private String I;
/*  58 */   private Map<String, BusObjPermission> J = new HashMap();
/*     */   private JSONObject K;
/*     */   
/*     */   public String getObjType() {
/*  62 */     return this.G;
/*     */   }
/*     */   
/*     */   public void setObjType(String objType) {
/*  66 */     this.G = objType;
/*     */   }
/*     */   
/*     */   public String getObjVal()
/*     */   {
/*  71 */     return this.H;
/*     */   }
/*     */   
/*     */   public void setObjVal(String objVal) {
/*  75 */     this.H = objVal;
/*     */   }
/*     */   
/*     */   public String getBusObjMapJson() {
/*  79 */     return this.I;
/*     */   }
/*     */   
/*     */   public void setBusObjMapJson(String busObjMapJson) {
/*  83 */     this.I = busObjMapJson;
/*  84 */     if (StringUtil.isEmpty(busObjMapJson)) {
/*  85 */       this.J = null;
/*  86 */       return;
/*     */     }
/*     */     
/*  89 */     this.J = new HashMap();
/*  90 */     Map<String, Object> map = (Map)JSONObject.parseObject(busObjMapJson, Map.class);
/*  91 */     for (Map.Entry<String, Object> entry : map.entrySet()) {
/*  92 */       this.J.put(entry.getKey(), JSONObject.parseObject(entry.getValue().toString(), BusObjPermission.class));
/*     */     }
/*     */   }
/*     */   
/*     */   public Map<String, BusObjPermission> getBusObjMap()
/*     */   {
/*  98 */     return this.J;
/*     */   }
/*     */   
/*     */   public void setBusObjMap(Map<String, BusObjPermission> busObjMap) {
/* 102 */     this.J = busObjMap;
/* 103 */     this.I = JsonUtil.toJSONString(busObjMap);
/*     */   }
/*     */   
/*     */   public BusObjPermission c(String boKey)
/*     */   {
/* 108 */     return (BusObjPermission)this.J.get(boKey);
/*     */   }
/*     */   
/*     */   public JSONObject getTablePermission(boolean isReadonly) {
/* 112 */     a(Boolean.valueOf(isReadonly));
/* 113 */     return this.K;
/*     */   }
/*     */   
/*     */   public JSONObject getPermission(boolean isReadonly) {
/* 117 */     a(Boolean.valueOf(isReadonly));
/* 118 */     return this.L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 125 */   private JSONObject L = null;
/*     */   
/* 127 */   private synchronized void a(Boolean isReadonly) { if (this.L != null) { return;
/*     */     }
/* 129 */     this.K = new JSONObject();
/* 130 */     this.L = new JSONObject();
/*     */     
/* 132 */     for (Map.Entry<String, ? extends IBusObjPermission> entry : getBusObjMap().entrySet()) {
/* 133 */       busObjPermission = (IBusObjPermission)entry.getValue();
/* 134 */       this.L.put(busObjPermission.getKey(), new JSONObject());
/* 135 */       this.K.put(busObjPermission.getKey(), new JSONObject());
/* 136 */       for (Map.Entry<String, ? extends IBusTablePermission> etry : busObjPermission.getTableMap().entrySet()) {
/* 137 */         busTablePermission = (IBusTablePermission)etry.getValue();
/* 138 */         this.L.getJSONObject(busObjPermission.getKey()).put(busTablePermission.getKey(), new JSONObject());
/* 139 */         this.K.getJSONObject(busObjPermission.getKey()).put(busTablePermission.getKey(), a(busTablePermission.getResult(), isReadonly));
/*     */         
/* 141 */         for (Map.Entry<String, ? extends IBusColumnPermission> ery : busTablePermission.getColumnMap().entrySet()) {
/* 142 */           IBusColumnPermission busColumnPermission = (IBusColumnPermission)ery.getValue();
/* 143 */           this.L.getJSONObject(busObjPermission.getKey()).getJSONObject(busTablePermission.getKey()).put(busColumnPermission.getKey(), a(busColumnPermission.getResult(), isReadonly));
/*     */         }
/*     */       }
/*     */     }
/*     */     IBusObjPermission busObjPermission;
/*     */     IBusTablePermission busTablePermission; }
/*     */   
/* 150 */   private String a(String result, Boolean isReadonly) { if (!isReadonly.booleanValue()) { return result;
/*     */     }
/* 152 */     if ((RightsType.REQUIRED.getKey().equals(result)) || (RightsType.WRITE.getKey().equals(result))) {
/* 153 */       return RightsType.READ.getKey();
/*     */     }
/*     */     
/* 156 */     return result;
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\model\BusinessPermission.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */