/*     */ package com.dstz.bpm.plugin.task.userassign.plugin;
/*     */ 
/*     */ import com.dstz.base.api.exception.BusinessException;
/*     */ import com.dstz.base.core.util.AppUtil;
/*     */ import com.dstz.base.core.util.BeanUtils;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.bpm.api.constant.ExtractType;
/*     */ import com.dstz.bpm.api.engine.constant.LogicType;
/*     */ import com.dstz.bpm.api.engine.plugin.context.UserCalcPluginContext;
/*     */ import com.dstz.bpm.api.engine.plugin.def.BpmUserCalcPluginDef;
/*     */ import com.dstz.bpm.api.engine.plugin.def.UserAssignRule;
/*     */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*     */ import com.dstz.bpm.api.exception.WorkFlowException;
/*     */ import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractBpmTaskPlugin;
/*     */ import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractUserCalcPlugin;
/*     */ import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
/*     */ import com.dstz.sys.api.groovy.IGroovyScriptEngine;
/*     */ import com.dstz.sys.api.model.SysIdentity;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class UserAssignRuleCalc
/*     */ {
/*  31 */   protected static final Logger LOG = LoggerFactory.getLogger(AbstractBpmTaskPlugin.class);
/*     */   
/*     */   public static List<SysIdentity> a(BpmUserCalcPluginSession bpmUserCalcPluginSession, List<UserAssignRule> ruleList, Boolean forceExtract)
/*     */   {
/*  35 */     List<SysIdentity> bpmIdentities = new ArrayList();
/*     */     
/*  37 */     java.util.Collections.sort(ruleList);
/*  38 */     for (UserAssignRule userRule : ruleList)
/*     */     {
/*     */ 
/*  41 */       if (bpmIdentities.size() > 0) {
/*     */         break;
/*     */       }
/*  44 */       boolean isValid = a(userRule.getCondition(), bpmUserCalcPluginSession);
/*  45 */       if (isValid)
/*     */       {
/*  47 */         List<UserCalcPluginContext> calcList = userRule.getCalcPluginContextList();
/*     */         
/*  49 */         for (UserCalcPluginContext context : calcList)
/*     */         {
/*  51 */           AbstractUserCalcPlugin plugin = (AbstractUserCalcPlugin)AppUtil.getBean(context.getPluginClass());
/*  52 */           if (plugin == null) {
/*  53 */             throw new WorkFlowException("请检查该插件是否注入成功：" + context.getPluginClass(), BpmStatusCode.PLUGIN_ERROR);
/*     */           }
/*     */           
/*  56 */           BpmUserCalcPluginDef pluginDef = (BpmUserCalcPluginDef)context.getBpmPluginDef();
/*  57 */           if (forceExtract.booleanValue()) {
/*  58 */             pluginDef.setExtract(ExtractType.EXACT_EXACT_USER);
/*     */           }
/*     */           
/*     */ 
/*  62 */           List<SysIdentity> biList = plugin.execute(bpmUserCalcPluginSession, pluginDef);
/*  63 */           LOG.debug("执行用户计算插件【{}】，解析到【{}】条人员信息，插件计算逻辑：{}", new Object[] { context.getTitle(), Integer.valueOf(biList.size()), pluginDef.getLogicCal() });
/*     */           
/*  65 */           if (!BeanUtils.isEmpty(biList))
/*     */           {
/*  67 */             if (BeanUtils.isEmpty(bpmIdentities)) {
/*  68 */               bpmIdentities.addAll(biList);
/*     */             }
/*     */             else
/*  71 */               a(bpmIdentities, biList, pluginDef.getLogicCal());
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*  76 */     return bpmIdentities;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static void a(List<SysIdentity> existBpmIdentities, List<SysIdentity> newBpmIdentities, LogicType logic)
/*     */   {
/*     */     Iterator localIterator1;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  91 */     switch (1.Z[logic.ordinal()])
/*     */     {
/*     */     case 1: 
/*  94 */       Set<SysIdentity> set = new LinkedHashSet();
/*  95 */       set.addAll(existBpmIdentities);
/*  96 */       set.addAll(newBpmIdentities);
/*  97 */       existBpmIdentities.clear();
/*  98 */       existBpmIdentities.addAll(set);
/*  99 */       break;
/*     */     case 2: 
/* 101 */       List<SysIdentity> rtnList = new ArrayList();
/* 102 */       for (localIterator1 = existBpmIdentities.iterator(); localIterator1.hasNext();) { identity = (SysIdentity)localIterator1.next();
/* 103 */         for (SysIdentity tmp : newBpmIdentities) {
/* 104 */           if (identity.equals(tmp))
/* 105 */             rtnList.add(identity);
/*     */         }
/*     */       }
/*     */       SysIdentity identity;
/* 109 */       existBpmIdentities.clear();
/* 110 */       existBpmIdentities.addAll(rtnList);
/* 111 */       break;
/*     */     
/*     */     default: 
/* 114 */       for (SysIdentity tmp : newBpmIdentities) {
/* 115 */         existBpmIdentities.remove(tmp);
/*     */       }
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */   private static boolean a(String script, BpmUserCalcPluginSession bpmUserCalcPluginSession)
/*     */   {
/* 123 */     if (StringUtil.isEmpty(script)) {
/* 124 */       return true;
/*     */     }
/*     */     
/* 127 */     Map<String, Object> map = new HashMap();
/*     */     
/* 129 */     map.putAll(bpmUserCalcPluginSession.getBoDatas());
/* 130 */     map.put("bpmTask", bpmUserCalcPluginSession.getBpmTask());
/* 131 */     map.put("bpmInstance", bpmUserCalcPluginSession.getBpmInstance());
/* 132 */     map.put("variableScope", bpmUserCalcPluginSession.getVariableScope());
/*     */     try
/*     */     {
/* 135 */       return ((IGroovyScriptEngine)AppUtil.getBean(IGroovyScriptEngine.class)).executeBoolean(script, map);
/*     */     } catch (Exception e) {
/* 137 */       LOG.error("人员前置脚本解析失败,脚本：{},可能原因为：{}", new Object[] { script, e.getMessage(), e });
/* 138 */       throw new BusinessException(BpmStatusCode.PLUGIN_USERCALC_RULE_CONDITION_ERROR);
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\task\userassign\plugin\UserAssignRuleCalc.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */