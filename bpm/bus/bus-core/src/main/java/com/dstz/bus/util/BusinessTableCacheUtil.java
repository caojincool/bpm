/*    */ package com.dstz.bus.util;
/*    */ 
/*    */ import com.dstz.base.core.cache.ICache;
/*    */ import com.dstz.base.core.util.AppUtil;
/*    */ import com.dstz.bus.model.BusinessTable;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BusinessTableCacheUtil
/*    */ {
/* 19 */   private static String U = "businessTableMap";
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void put(BusinessTable businessTable)
/*    */   {
/* 26 */     Map<String, BusinessTable> map = (Map)AppUtil.getCache().getByKey(U);
/* 27 */     if (map == null) {
/* 28 */       map = new HashMap();
/*    */     }
/* 30 */     map.put(businessTable.getKey(), businessTable);
/* 31 */     AppUtil.getCache().add(U, map);
/*    */   }
/*    */   
/*    */   public static BusinessTable get(String key) {
/* 35 */     Map<String, BusinessTable> map = (Map)AppUtil.getCache().getByKey(U);
/* 36 */     if (map == null) {
/* 37 */       return null;
/*    */     }
/* 39 */     return (BusinessTable)map.get(key);
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\util\BusinessTableCacheUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */