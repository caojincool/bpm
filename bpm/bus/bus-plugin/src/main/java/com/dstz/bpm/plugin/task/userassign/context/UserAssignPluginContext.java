/*     */ package com.dstz.bpm.plugin.task.userassign.context;
/*     */ 
/*     */ import com.alibaba.fastjson.JSON;
/*     */ import com.alibaba.fastjson.JSONArray;
/*     */ import com.alibaba.fastjson.JSONObject;
/*     */ import com.dstz.base.core.util.AppUtil;
/*     */ import com.dstz.base.core.util.BeanUtils;
/*     */ import com.dstz.base.core.util.JsonUtil;
/*     */ import com.dstz.base.core.util.ThreadMsgUtil;
/*     */ import com.dstz.bpm.api.constant.EventType;
/*     */ import com.dstz.bpm.api.engine.plugin.context.UserCalcPluginContext;
/*     */ import com.dstz.bpm.api.engine.plugin.context.UserQueryPluginContext;
/*     */ import com.dstz.bpm.api.engine.plugin.def.UserAssignRule;
/*     */ import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
/*     */ import com.dstz.bpm.engine.plugin.context.AbstractBpmTaskPluginContext;
/*     */ import com.dstz.bpm.engine.plugin.context.AbstractUserCalcPluginContext;
/*     */ import com.dstz.bpm.plugin.task.userassign.def.UserAssignPluginDef;
/*     */ import com.dstz.bpm.plugin.task.userassign.plugin.UserAssignPlugin;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.slf4j.Logger;
/*     */ import org.springframework.context.annotation.Scope;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ @Scope("prototype")
/*     */ public class UserAssignPluginContext
/*     */   extends AbstractBpmTaskPluginContext<UserAssignPluginDef>
/*     */   implements UserQueryPluginContext
/*     */ {
/*     */   public Class getPluginClass()
/*     */   {
/*  36 */     return UserAssignPlugin.class;
/*     */   }
/*     */   
/*     */   public Class<? extends RunTimePlugin> getUserQueryPluginClass() {
/*  40 */     return UserAssignPlugin.class;
/*     */   }
/*     */   
/*     */   public List<EventType> getEventTypes()
/*     */   {
/*  45 */     List<EventType> eventTypes = new ArrayList();
/*  46 */     eventTypes.add(EventType.TASK_CREATE_EVENT);
/*  47 */     return eventTypes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public JSON getJson()
/*     */   {
/*  62 */     List<UserAssignRule> ruleList = ((UserAssignPluginDef)getBpmPluginDef()).getRuleList();
/*  63 */     if (BeanUtils.isEmpty(ruleList)) { return (JSON)JSON.parse("[]");
/*     */     }
/*  65 */     return (JSON)JSON.toJSON(ruleList);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected UserAssignPluginDef g(JSON pluginJson)
/*     */   {
/*  74 */     UserAssignPluginDef def = new UserAssignPluginDef();
/*     */     
/*  76 */     JSONArray userRuleList = null;
/*  77 */     if ((pluginJson instanceof JSONObject)) {
/*  78 */       JSONObject json = (JSONObject)pluginJson;
/*  79 */       if (!json.containsKey("ruleList")) {
/*  80 */         ThreadMsgUtil.addMsg("人员条件不完整！");
/*  81 */         return def;
/*     */       }
/*     */       
/*  84 */       userRuleList = json.getJSONArray("ruleList");
/*     */     } else {
/*  86 */       userRuleList = (JSONArray)pluginJson;
/*     */     }
/*     */     
/*  89 */     List<UserAssignRule> ruleList = new ArrayList();
/*  90 */     for (int i = 0; i < userRuleList.size(); i++) {
/*  91 */       JSONObject ruleJson = userRuleList.getJSONObject(i);
/*  92 */       UserAssignRule rule = (UserAssignRule)JSON.toJavaObject(ruleJson, UserAssignRule.class);
/*  93 */       ruleList.add(rule);
/*     */       
/*  95 */       if (!ruleJson.containsKey("calcs")) {
/*  96 */         ThreadMsgUtil.addMsg("人员条件不完整！");
/*     */       }
/*     */       else
/*     */       {
/* 100 */         JSONArray calcAry = ruleJson.getJSONArray("calcs");
/* 101 */         List<UserCalcPluginContext> calcPluginContextList = new ArrayList();
/* 102 */         for (Object obj : calcAry) {
/* 103 */           JSONObject calcObj = (JSONObject)obj;
/* 104 */           String pluginContext = JsonUtil.getString(calcObj, "pluginType") + "PluginContext";
/*     */           
/* 106 */           AbstractUserCalcPluginContext ctx = (AbstractUserCalcPluginContext)AppUtil.getBean(pluginContext);
/* 107 */           if (ctx == null) {
/* 108 */             this.LOG.warn("插件{}查找失败！", pluginContext);
/*     */           }
/*     */           else
/*     */           {
/* 112 */             ctx.parse(calcObj);
/*     */             
/* 114 */             calcPluginContextList.add(ctx);
/*     */           }
/*     */         }
/* 117 */         rule.setCalcPluginContextList(calcPluginContextList);
/*     */       }
/*     */     }
/* 120 */     def.setRuleList(ruleList);
/* 121 */     return def;
/*     */   }
/*     */   
/*     */   public String getTitle()
/*     */   {
/* 126 */     return "用户分配插件";
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\task\userassign\context\UserAssignPluginContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */