 package com.dstz.bpm.engine.parser;
 
 import com.alibaba.fastjson.JSON;
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import com.dstz.base.core.validate.ValidateUtil;
 import com.dstz.bpm.api.engine.plugin.def.BpmDef;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 
 
 public abstract class BaseBpmDefParser<T, D extends BpmDef>
   implements BpmDefParser<D>
 {
   protected Logger LOG = LoggerFactory.getLogger(getClass());
   
   public void parse(D def, JSONObject jsonConf)
   {
     Object args = null;
     if (jsonConf.containsKey(getKey())) {
       String jsonStr = jsonConf.getString(getKey());
       
 
       args = parseDef(def, jsonStr);
       
       if (args == null) {
         if (isArray()) {
           args = JSONArray.parseArray(jsonStr, getClazz());
         } else {
           args = JSON.parseObject(jsonStr, getClazz());
         }
       }
       
 
       setDefParam(def, args);
     }
     
 
     validate(args);
     
     JSONAmend(def, args, jsonConf);
   }
   
 
 
 
 
   public Object parseDef(D def, String conf)
   {
     return null;
   }
   
 
 
   public Class<T> getClazz()
   {
     return 
       (Class)((java.lang.reflect.ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
   }
   
 
 
 
   public boolean isArray()
   {
     return false;
   }
   
   public String validate(Object o)
   {
     if (o != null) {
       ValidateUtil.validate(o);
     }
     return null;
   }
   
 
 
 
   public void JSONAmend(D bpmdef, Object args, JSON configJson) {}
   
 
 
 
   public String getN()
   {
     return "asdf2dds";
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\parser\BaseBpmDefParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */