 package com.dstz.bpm.engine.plugin.context;
 
 import com.alibaba.fastjson.JSON;
 import com.dstz.base.core.util.BeanUtils;
 import com.dstz.base.core.util.StringUtil;
 import com.dstz.bpm.api.engine.plugin.context.BpmPluginContext;
 import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 
 
 
 
 
 
 
 
 
 public abstract class AbstractBpmPluginContext<T extends BpmPluginDef>
   implements BpmPluginContext<T>
 {
   private static final long serialVersionUID = 1L;
   protected Logger LOG = LoggerFactory.getLogger(getClass());
   
   private T bG;
   
   public T getBpmPluginDef()
   {
     return this.bG;
   }
   
   public void setBpmPluginDef(T bpmPluginDef) {
     this.bG = bpmPluginDef;
   }
   
 
 
 
   protected abstract T parseFromJson(JSON paramJSON);
   
 
 
 
   public JSON getJson()
   {
     return (JSON)JSON.toJSON(this.bG);
   }
   
 
 
 
   public T parse(JSON json)
   {
     T def = parseFromJson(json);
     setBpmPluginDef(def);
     return this.bG;
   }
   
   public T parse(String json) {
     if (StringUtil.isEmpty(json)) { return null;
     }
     JSON j = (JSON)JSON.parse(json);
     return parse(j);
   }
   
   public String getType()
   {
     return StringUtil.lowerFirst(getClass().getSimpleName().replaceAll("PluginContext", ""));
   }
   
 
 
 
 
 
   public String persistnce(String defId)
   {
     String msg = null;
     if (BeanUtils.isEmpty(getJson())) { msg = "清空改插件";
     }
     
     return msg;
   }
   
 
 
 
 
 
 
   protected int bH = 100;
   
   public Integer getSn()
   {
     return Integer.valueOf(this.bH);
   }
   
   public int compareTo(BpmPluginContext pluginContext)
   {
     if (this.bH == pluginContext.getSn().intValue()) { return 0;
     }
     if (this.bH > pluginContext.getSn().intValue()) { return 1;
     }
     return 0;
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\plugin\context\AbstractBpmPluginContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */