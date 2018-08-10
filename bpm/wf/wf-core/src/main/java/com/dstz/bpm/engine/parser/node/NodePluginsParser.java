/*    */ package com.dstz.bpm.engine.parser.node;
/*    */ 
/*    */ import com.alibaba.fastjson.JSON;
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.base.core.util.AppUtil;
/*    */ import com.dstz.bpm.api.engine.plugin.context.BpmPluginContext;
/*    */ import com.dstz.bpm.api.model.nodedef.impl.BaseBpmNodeDef;
/*    */ import java.util.ArrayList;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class NodePluginsParser
/*    */   extends AbsNodeParse<BpmPluginContext>
/*    */ {
/*    */   public Object a(BaseBpmNodeDef userNodeDef, String json)
/*    */   {
/* 20 */     JSONObject plugins = JSON.parseObject(json);
/*    */     
/* 22 */     ArrayList<BpmPluginContext> pluginContextList = new ArrayList();
/* 23 */     for (String pluginName : plugins.keySet()) {
/* 24 */       BpmPluginContext pluginContext = (BpmPluginContext)AppUtil.getBean(pluginName + "PluginContext");
/*    */       
/* 26 */       if ((pluginContext instanceof BpmPluginContext)) {
/* 27 */         Object object = plugins.get(pluginName);
/* 28 */         pluginContext.parse((JSON)object);
/*    */       }
/* 30 */       pluginContextList.add(pluginContext);
/*    */     }
/*    */     
/* 33 */     return pluginContextList;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getKey()
/*    */   {
/* 41 */     return "plugins";
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String validate(Object o)
/*    */   {
/* 48 */     return null;
/*    */   }
/*    */   
/*    */   public void a(BaseBpmNodeDef userNodeDef, Object object)
/*    */   {
/* 53 */     ArrayList<BpmPluginContext> pluginContextList = (ArrayList)object;
/*    */     
/* 55 */     userNodeDef.setBpmPluginContexts(pluginContextList);
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\parser\node\NodePluginsParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */