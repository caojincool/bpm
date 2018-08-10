 package com.dstz.bpm.engine.parser.flow;
 
 import com.alibaba.fastjson.JSONObject;
 import com.dstz.bpm.api.model.def.BpmDefProperties;
 import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
 import org.springframework.stereotype.Component;
 
 
 
 
 
 @Component
 public class FlowPropertiesParse
   extends AbsFlowParse<BpmDefProperties>
 {
   public void b(DefaultBpmProcessDef def, JSONObject flowConf)
   {
     JSONObject properties = (JSONObject)JSONObject.toJSON(def.getExtProperties());
     if (flowConf.containsKey(getKey())) {
       properties = flowConf.getJSONObject(getKey());
     }
     BpmDefProperties bpmDefproperties = (BpmDefProperties)JSONObject.toJavaObject(properties, BpmDefProperties.class);
     def.setExtProperties(bpmDefproperties);
     flowConf.put(getKey(), properties);
   }
   
   public String getKey()
   {
     return "properties";
   }
   
 
   public void a(DefaultBpmProcessDef bpmProcessDef, Object object)
   {
     BpmDefProperties properties = (BpmDefProperties)object;
     bpmProcessDef.setExtProperties(properties);
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\parser\flow\FlowPropertiesParse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */