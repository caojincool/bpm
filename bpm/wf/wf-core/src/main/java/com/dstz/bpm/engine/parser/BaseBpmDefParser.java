/*    */ package com.dstz.bpm.engine.parser;
/*    */ 
/*    */ import com.alibaba.fastjson.JSON;
/*    */ import com.alibaba.fastjson.JSONArray;
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.base.core.validate.ValidateUtil;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmDef;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ public abstract class BaseBpmDefParser<T, D extends BpmDef>
/*    */   implements BpmDefParser<D>
/*    */ {
/* 15 */   protected Logger LOG = LoggerFactory.getLogger(getClass());
/*    */   
/*    */   public void parse(D def, JSONObject jsonConf)
/*    */   {
/* 19 */     Object args = null;
/* 20 */     if (jsonConf.containsKey(getKey())) {
/* 21 */       String jsonStr = jsonConf.getString(getKey());
/*    */       
/*    */ 
/* 24 */       args = parseDef(def, jsonStr);
/*    */       
/* 26 */       if (args == null) {
/* 27 */         if (isArray()) {
/* 28 */           args = JSONArray.parseArray(jsonStr, getClazz());
/*    */         } else {
/* 30 */           args = JSON.parseObject(jsonStr, getClazz());
/*    */         }
/*    */       }
/*    */       
/*    */ 
/* 35 */       setDefParam(def, args);
/*    */     }
/*    */     
/*    */ 
/* 39 */     validate(args);
/*    */     
/* 41 */     JSONAmend(def, args, jsonConf);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Object parseDef(D def, String conf)
/*    */   {
/* 50 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public Class<T> getClazz()
/*    */   {
/* 57 */     return 
/* 58 */       (Class)((java.lang.reflect.ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean isArray()
/*    */   {
/* 66 */     return false;
/*    */   }
/*    */   
/*    */   public String validate(Object o)
/*    */   {
/* 71 */     if (o != null) {
/* 72 */       ValidateUtil.validate(o);
/*    */     }
/* 74 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void JSONAmend(D bpmdef, Object args, JSON configJson) {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getN()
/*    */   {
/* 87 */     return "asdf2dds";
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\parser\BaseBpmDefParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */