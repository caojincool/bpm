/*     */ package com.dstz.bpm.act.util;
/*     */ 
/*     */ import com.dstz.base.core.util.AppUtil;
/*     */ import com.dstz.base.core.util.FileUtil;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.activiti.engine.ProcessEngine;
/*     */ import org.activiti.engine.RepositoryService;
/*     */ import org.activiti.engine.impl.ProcessEngineImpl;
/*     */ import org.activiti.engine.impl.RepositoryServiceImpl;
/*     */ import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
/*     */ import org.activiti.engine.impl.interceptor.CommandExecutor;
/*     */ import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
/*     */ import org.activiti.engine.impl.pvm.PvmActivity;
/*     */ import org.activiti.engine.impl.pvm.PvmTransition;
/*     */ import org.activiti.engine.impl.pvm.process.ActivityImpl;
/*     */ import org.activiti.engine.impl.pvm.process.TransitionImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ActivitiUtil
/*     */ {
/*     */   public static CommandExecutor getCommandExecutor()
/*     */   {
/*  29 */     ProcessEngineImpl engine = (ProcessEngineImpl)AppUtil.getBean(ProcessEngine.class);
/*  30 */     CommandExecutor cmdExecutor = engine.getProcessEngineConfiguration().getCommandExecutor();
/*  31 */     return cmdExecutor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Map<String, Object> a(String actDefId, String nodeId, String[] aryDestination)
/*     */   {
/*  43 */     Map<String, Object> map = new HashMap();
/*     */     
/*  45 */     RepositoryService repositoryService = (RepositoryService)AppUtil.getBean(RepositoryService.class);
/*     */     
/*     */ 
/*  48 */     ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity)((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(actDefId);
/*     */     
/*  50 */     ActivityImpl curAct = processDefinition.findActivity(nodeId);
/*  51 */     List<PvmTransition> outTrans = curAct.getOutgoingTransitions();
/*     */     try {
/*  53 */       List<PvmTransition> cloneOutTrans = (List)FileUtil.cloneObject(outTrans);
/*  54 */       map.put("outTrans", cloneOutTrans);
/*     */     } catch (Exception ex) {
/*  56 */       ex.printStackTrace();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  63 */     for (Iterator<PvmTransition> it = outTrans.iterator(); it.hasNext();) {
/*  64 */       transition = (PvmTransition)it.next();
/*  65 */       activity = transition.getDestination();
/*  66 */       List<PvmTransition> inTrans = activity.getIncomingTransitions();
/*  67 */       for (itIn = inTrans.iterator(); itIn.hasNext();) {
/*  68 */         PvmTransition inTransition = (PvmTransition)itIn.next();
/*  69 */         if (inTransition.getSource().getId().equals(curAct.getId()))
/*  70 */           itIn.remove();
/*     */       }
/*     */     }
/*     */     PvmTransition transition;
/*     */     PvmActivity activity;
/*     */     Iterator<PvmTransition> itIn;
/*  76 */     curAct.getOutgoingTransitions().clear();
/*     */     
/*  78 */     if ((aryDestination != null) && (aryDestination.length > 0)) {
/*  79 */       it = aryDestination;transition = it.length; for (activity = 0; activity < transition; activity++) { String dest = it[activity];
/*     */         
/*  81 */         ActivityImpl destAct = processDefinition.findActivity(dest);
/*  82 */         TransitionImpl transitionImpl = curAct.createOutgoingTransition();
/*  83 */         transitionImpl.setDestination(destAct);
/*     */       }
/*     */     }
/*     */     
/*  87 */     map.put("activity", curAct);
/*     */     
/*     */ 
/*  90 */     return map;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void a(Map<String, Object> map)
/*     */   {
/* 100 */     ActivityImpl curAct = (ActivityImpl)map.get("activity");
/* 101 */     List<PvmTransition> outTrans = (List)map.get("outTrans");
/* 102 */     curAct.getOutgoingTransitions().clear();
/* 103 */     curAct.getOutgoingTransitions().addAll(outTrans);
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-act\1.1.5\wf-act-1.1.5-pg.jar!\com\dstz\bpm\act\util\ActivitiUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */