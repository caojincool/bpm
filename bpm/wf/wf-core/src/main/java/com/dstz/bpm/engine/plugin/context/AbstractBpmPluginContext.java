/*     */ package com.dstz.bpm.engine.plugin.context;
/*     */ 
/*     */ import com.alibaba.fastjson.JSON;
/*     */ import com.dstz.base.core.util.BeanUtils;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.bpm.api.engine.plugin.context.BpmPluginContext;
/*     */ import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractBpmPluginContext<T extends BpmPluginDef>
/*     */   implements BpmPluginContext<T>
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  23 */   protected Logger LOG = LoggerFactory.getLogger(getClass());
/*     */   
/*     */   private T bG;
/*     */   
/*     */   public T getBpmPluginDef()
/*     */   {
/*  29 */     return this.bG;
/*     */   }
/*     */   
/*     */   public void setBpmPluginDef(T bpmPluginDef) {
/*  33 */     this.bG = bpmPluginDef;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract T parseFromJson(JSON paramJSON);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public JSON getJson()
/*     */   {
/*  46 */     return (JSON)JSON.toJSON(this.bG);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public T parse(JSON json)
/*     */   {
/*  54 */     T def = parseFromJson(json);
/*  55 */     setBpmPluginDef(def);
/*  56 */     return this.bG;
/*     */   }
/*     */   
/*     */   public T parse(String json) {
/*  60 */     if (StringUtil.isEmpty(json)) { return null;
/*     */     }
/*  62 */     JSON j = (JSON)JSON.parse(json);
/*  63 */     return parse(j);
/*     */   }
/*     */   
/*     */   public String getType()
/*     */   {
/*  68 */     return StringUtil.lowerFirst(getClass().getSimpleName().replaceAll("PluginContext", ""));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String persistnce(String defId)
/*     */   {
/*  78 */     String msg = null;
/*  79 */     if (BeanUtils.isEmpty(getJson())) { msg = "清空改插件";
/*     */     }
/*     */     
/*  82 */     return msg;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  91 */   protected int bH = 100;
/*     */   
/*     */   public Integer getSn()
/*     */   {
/*  95 */     return Integer.valueOf(this.bH);
/*     */   }
/*     */   
/*     */   public int compareTo(BpmPluginContext pluginContext)
/*     */   {
/* 100 */     if (this.bH == pluginContext.getSn().intValue()) { return 0;
/*     */     }
/* 102 */     if (this.bH > pluginContext.getSn().intValue()) { return 1;
/*     */     }
/* 104 */     return 0;
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\plugin\context\AbstractBpmPluginContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */