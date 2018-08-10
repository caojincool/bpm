 package com.dstz.bpm.engine.parser.flow;
 
 import com.alibaba.fastjson.JSON;
 import com.alibaba.fastjson.JSONObject;
 import com.dstz.base.core.util.AppUtil;
 import com.dstz.base.core.validate.ValidateUtil;
 import com.dstz.bpm.api.engine.plugin.context.BpmPluginContext;
 import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;
 import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
 import java.util.ArrayList;
 import org.slf4j.Logger;
 import org.springframework.stereotype.Component;
 
 
 
 @Component
 public class PluginsParser
   extends AbsFlowParse<BpmPluginContext>
 {
   public Object a(DefaultBpmProcessDef bpmProcessDef, String json)
   {
     JSONObject plugins = JSON.parseObject(json);
     
     ArrayList<BpmPluginContext> pluginContextList = new ArrayList();
     for (String pluginName : plugins.keySet()) {
       BpmPluginContext pluginContext = (BpmPluginContext)AppUtil.getBean(pluginName + "PluginContext");
       
       if ((pluginContext instanceof BpmPluginContext)) {
         try {
           pluginContext.parse((JSON)plugins.get(pluginName));
           BpmPluginDef def = pluginContext.getBpmPluginDef();
           
           ValidateUtil.validate(def);
         } catch (Exception e) {
           this.LOG.error("插件{}解析失败:{}！", new Object[] { pluginContext.getTitle(), e.getMessage(), e });
         }
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
   
   public void a(DefaultBpmProcessDef bpmProcessDef, Object object)
   {
     ArrayList<BpmPluginContext> pluginContextList = (ArrayList)object;
     
     bpmProcessDef.setPluginContextList(pluginContextList);
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\parser\flow\PluginsParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */