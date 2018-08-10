/*     */ package com.dstz.bpm.engine.plugin.runtime.abstact;
/*     */ 
/*     */ import com.dstz.base.core.util.BeanUtils;
/*     */ import com.dstz.bpm.api.constant.ExtractType;
/*     */ import com.dstz.bpm.api.engine.plugin.def.BpmTaskPluginDef;
/*     */ import com.dstz.bpm.engine.model.BpmIdentity;
/*     */ import com.dstz.bpm.engine.plugin.plugindef.AbstractUserCalcPluginDef;
/*     */ import com.dstz.bpm.engine.plugin.runtime.BpmUserCalcPlugin;
/*     */ import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
/*     */ import com.dstz.org.api.model.IUser;
/*     */ import com.dstz.org.api.service.UserService;
/*     */ import com.dstz.sys.api.model.SysIdentity;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Resource;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractUserCalcPlugin<M extends BpmTaskPluginDef>
/*     */   implements BpmUserCalcPlugin<M>
/*     */ {
/*     */   @Resource
/*     */   protected UserService bL;
/*     */   
/*     */   protected abstract List<SysIdentity> queryByPluginDef(BpmUserCalcPluginSession paramBpmUserCalcPluginSession, M paramM);
/*     */   
/*     */   public List<SysIdentity> execute(BpmUserCalcPluginSession pluginSession, M pluginDef)
/*     */   {
/*  45 */     if ((pluginSession.isPreVrewModel().booleanValue()) && 
/*  46 */       (!supportPreView())) { return Collections.emptyList();
/*     */     }
/*     */     
/*  49 */     List<SysIdentity> list = queryByPluginDef(pluginSession, pluginDef);
/*  50 */     if (BeanUtils.isEmpty(list)) { return list;
/*     */     }
/*  52 */     ExtractType extractType = ((AbstractUserCalcPluginDef)pluginDef).getExtract();
/*     */     
/*  54 */     Set<SysIdentity> set = new LinkedHashSet();
/*  55 */     List<SysIdentity> rtnList = new ArrayList();
/*     */     
/*     */ 
/*  58 */     list = extract(list, extractType);
/*     */     
/*  60 */     set.addAll(list);
/*     */     
/*  62 */     rtnList.addAll(set);
/*     */     
/*  64 */     return rtnList;
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
/*     */   protected List<SysIdentity> extract(List<SysIdentity> bpmIdentities, ExtractType extractType)
/*     */   {
/*  77 */     if (BeanUtils.isEmpty(bpmIdentities)) { return Collections.EMPTY_LIST;
/*     */     }
/*  79 */     if (extractType == ExtractType.EXACT_NOEXACT) {
/*  80 */       return bpmIdentities;
/*     */     }
/*     */     
/*  83 */     return extractBpmIdentity(bpmIdentities);
/*     */   }
/*     */   
/*     */   protected List<SysIdentity> extractBpmIdentity(List<SysIdentity> bpmIdentities) {
/*  87 */     List<SysIdentity> results = new ArrayList();
/*  88 */     for (SysIdentity bpmIdentity : bpmIdentities)
/*     */     {
/*  90 */       if ("user".equals(bpmIdentity.getType())) {
/*  91 */         results.add(bpmIdentity);
/*     */       }
/*     */       else
/*     */       {
/*  95 */         List<IUser> users = this.bL.getUserListByGroup(bpmIdentity.getType(), bpmIdentity.getId());
/*  96 */         for (IUser user : users) {
/*  97 */           results.add(new BpmIdentity(user));
/*     */         }
/*     */       }
/*     */     }
/* 101 */     return results;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean supportPreView()
/*     */   {
/* 109 */     return true;
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\plugin\runtime\abstact\AbstractUserCalcPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */