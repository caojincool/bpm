/*    */ package com.dstz.bpm.engine.parser.flow;
/*    */ 
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.bpm.api.model.def.BpmDefProperties;
/*    */ import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class FlowPropertiesParse
/*    */   extends AbsFlowParse<BpmDefProperties>
/*    */ {
/*    */   public void b(DefaultBpmProcessDef def, JSONObject flowConf)
/*    */   {
/* 18 */     JSONObject properties = (JSONObject)JSONObject.toJSON(def.getExtProperties());
/* 19 */     if (flowConf.containsKey(getKey())) {
/* 20 */       properties = flowConf.getJSONObject(getKey());
/*    */     }
/* 22 */     BpmDefProperties bpmDefproperties = (BpmDefProperties)JSONObject.toJavaObject(properties, BpmDefProperties.class);
/* 23 */     def.setExtProperties(bpmDefproperties);
/* 24 */     flowConf.put(getKey(), properties);
/*    */   }
/*    */   
/*    */   public String getKey()
/*    */   {
/* 29 */     return "properties";
/*    */   }
/*    */   
/*    */ 
/*    */   public void a(DefaultBpmProcessDef bpmProcessDef, Object object)
/*    */   {
/* 35 */     BpmDefProperties properties = (BpmDefProperties)object;
/* 36 */     bpmProcessDef.setExtProperties(properties);
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\parser\flow\FlowPropertiesParse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */