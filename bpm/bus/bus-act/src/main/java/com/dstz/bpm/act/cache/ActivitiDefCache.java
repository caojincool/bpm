/*     */ package com.dstz.bpm.act.cache;
/*     */ 
/*     */ import com.dstz.base.core.cache.ICache;
/*     */ import com.dstz.base.core.util.AppUtil;
/*     */ import com.dstz.base.core.util.FileUtil;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Resource;
/*     */ import org.activiti.engine.impl.persistence.deploy.DeploymentCache;
/*     */ import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ public class ActivitiDefCache
/*     */   implements DeploymentCache<ProcessDefinitionEntity>
/*     */ {
/*     */   public static void clearLocal()
/*     */   {
/*  25 */     ActivitiDefCache cache = (ActivitiDefCache)AppUtil.getBean(ActivitiDefCache.class);
/*  26 */     cache.clearProcessCache();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  34 */   public static ActivitiDefCache a = null;
/*     */   
/*     */   public static void clearByDefId(String actDefId) {
/*  37 */     if (a == null) {
/*  38 */       a = (ActivitiDefCache)AppUtil.getBean(ActivitiDefCache.class);
/*     */     }
/*  40 */     a.clearProcessDefinitionEntity(actDefId);
/*  41 */     a.clearProcessCache();
/*     */   }
/*     */   
/*  44 */   private ThreadLocal<Map<String, ProcessDefinitionEntity>> b = new ThreadLocal();
/*     */   
/*     */   private void clearProcessDefinitionEntity(String defId) {
/*  47 */     remove(defId);
/*  48 */     this.b.remove();
/*     */   }
/*     */   
/*     */   private void clearProcessCache() {
/*  52 */     this.b.remove();
/*     */   }
/*     */   
/*     */   private void setThreadLocalDef(ProcessDefinitionEntity processEnt) {
/*  56 */     if (this.b.get() == null) {
/*  57 */       Map<String, ProcessDefinitionEntity> map = new HashMap();
/*  58 */       map.put(processEnt.getId(), processEnt);
/*  59 */       this.b.set(map);
/*     */     } else {
/*  61 */       Map<String, ProcessDefinitionEntity> map = (Map)this.b.get();
/*  62 */       map.put(processEnt.getId(), processEnt);
/*     */     }
/*     */   }
/*     */   
/*     */   private ProcessDefinitionEntity getThreadLocalDef(String processDefinitionId)
/*     */   {
/*  68 */     if (this.b.get() == null) {
/*  69 */       return null;
/*     */     }
/*  71 */     Map<String, ProcessDefinitionEntity> map = (Map)this.b.get();
/*  72 */     if (!map.containsKey(processDefinitionId)) {
/*  73 */       return null;
/*     */     }
/*     */     
/*  76 */     return (ProcessDefinitionEntity)map.get(processDefinitionId);
/*     */   }
/*     */   
/*     */ 
/*     */   @Resource
/*     */   ICache c;
/*     */   
/*     */   public ProcessDefinitionEntity get(String id)
/*     */   {
/*  85 */     ProcessDefinitionEntity ent = (ProcessDefinitionEntity)this.c.getByKey(id);
/*  86 */     if (ent == null) return null;
/*  87 */     ProcessDefinitionEntity cloneEnt = null;
/*     */     try
/*     */     {
/*  90 */       cloneEnt = (ProcessDefinitionEntity)FileUtil.cloneObject(ent);
/*     */     }
/*     */     catch (Exception e) {
/*  93 */       e.printStackTrace();
/*     */     }
/*  95 */     ProcessDefinitionEntity p = getThreadLocalDef(id);
/*  96 */     if (p == null) {
/*  97 */       setThreadLocalDef(cloneEnt);
/*     */     }
/*  99 */     p = getThreadLocalDef(id);
/*     */     
/* 101 */     return p;
/*     */   }
/*     */   
/*     */ 
/*     */   public void add(String id, ProcessDefinitionEntity object)
/*     */   {
/* 107 */     this.c.add(id, object);
/*     */   }
/*     */   
/*     */ 
/*     */   public void remove(String id)
/*     */   {
/* 113 */     this.c.delByKey(id);
/*     */   }
/*     */   
/*     */ 
/*     */   public void clear()
/*     */   {
/* 119 */     this.c.clearAll();
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-act\1.1.5\wf-act-1.1.5-pg.jar!\com\dstz\bpm\act\cache\ActivitiDefCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */