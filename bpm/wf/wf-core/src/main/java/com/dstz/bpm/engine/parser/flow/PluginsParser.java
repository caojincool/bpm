/*    */ package com.dstz.bpm.engine.parser.flow;
/*    */ 
/*    */ import com.alibaba.fastjson.JSON;
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.base.core.util.AppUtil;
/*    */ import com.dstz.base.core.validate.ValidateUtil;
/*    */ import com.dstz.bpm.api.engine.plugin.context.BpmPluginContext;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;
/*    */ import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
/*    */ import java.util.ArrayList;
/*    */ import org.slf4j.Logger;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class PluginsParser
/*    */   extends AbsFlowParse<BpmPluginContext>
/*    */ {
/*    */   public Object a(DefaultBpmProcessDef bpmProcessDef, String json)
/*    */   {
/* 22 */     JSONObject plugins = JSON.parseObject(json);
/*    */     
/* 24 */     ArrayList<BpmPluginContext> pluginContextList = new ArrayList();
/* 25 */     for (String pluginName : plugins.keySet()) {
/* 26 */       BpmPluginContext pluginContext = (BpmPluginContext)AppUtil.getBean(pluginName + "PluginContext");
/*    */       
/* 28 */       if ((pluginContext instanceof BpmPluginContext)) {
/*    */         try {
/* 30 */           pluginContext.parse((JSON)plugins.get(pluginName));
/* 31 */           BpmPluginDef def = pluginContext.getBpmPluginDef();
/*    */           
/* 33 */           ValidateUtil.validate(def);
/*    */         } catch (Exception e) {
/* 35 */           this.LOG.error("插件{}解析失败:{}！", new Object[] { pluginContext.getTitle(), e.getMessage(), e });
/*    */         }
/*    */       }
/* 38 */       pluginContextList.add(pluginContext);
/*    */     }
/*    */     
/* 41 */     return pluginContextList;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getKey()
/*    */   {
/* 47 */     return "plugins";
/*    */   }
/*    */   
/*    */   public String validate(Object o)
/*    */   {
/* 52 */     return null;
/*    */   }
/*    */   
/*    */   public void a(DefaultBpmProcessDef bpmProcessDef, Object object)
/*    */   {
/* 57 */     ArrayList<BpmPluginContext> pluginContextList = (ArrayList)object;
/*    */     
/* 59 */     bpmProcessDef.setPluginContextList(pluginContextList);
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\parser\flow\PluginsParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */