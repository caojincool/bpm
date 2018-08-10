 package com.dstz.bpm.engine.parser.node;
 
 import com.alibaba.fastjson.JSON;
 import com.alibaba.fastjson.JSONObject;
 import com.dstz.base.core.util.AppUtil;
 import com.dstz.bpm.api.engine.plugin.context.BpmPluginContext;
 import com.dstz.bpm.api.model.nodedef.impl.BaseBpmNodeDef;
 import java.util.ArrayList;
 import org.springframework.stereotype.Component;
 
 
 
 
 @Component
 public class NodePluginsParser
   extends AbsNodeParse<BpmPluginContext>
 {
   public Object a(BaseBpmNodeDef userNodeDef, String json)
   {
     JSONObject plugins = JSON.parseObject(json);
     
     ArrayList<BpmPluginContext> pluginContextList = new ArrayList();
     for (String pluginName : plugins.keySet()) {
       BpmPluginContext pluginContext = (BpmPluginContext)AppUtil.getBean(pluginName + "PluginContext");
       
       if ((pluginContext instanceof BpmPluginContext)) {
         Object object = plugins.get(pluginName);
         pluginContext.parse((JSON)object);
       }
       pluginContextList.add(pluginContext);
     }
     
     return pluginContextList;
   }
   
 
 
 
   public String getKey()
   {
     return "plugins";
   }
   
 
 
   public String validate(Object o)
   {
     return null;
   }
   
   public void a(BaseBpmNodeDef userNodeDef, Object object)
   {
     ArrayList<BpmPluginContext> pluginContextList = (ArrayList)object;
     
     userNodeDef.setBpmPluginContexts(pluginContextList);
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\parser\node\NodePluginsParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */