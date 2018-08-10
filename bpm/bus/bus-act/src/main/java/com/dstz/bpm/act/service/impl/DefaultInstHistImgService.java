/*     */ package com.dstz.bpm.act.service.impl;
/*     */ 
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.activiti.bpmn.model.BpmnModel;
/*     */ import org.activiti.engine.HistoryService;
/*     */ import org.activiti.engine.ProcessEngineConfiguration;
/*     */ import org.activiti.engine.RepositoryService;
/*     */ import org.activiti.engine.RuntimeService;
/*     */ import org.activiti.engine.history.HistoricActivityInstance;
/*     */ import org.activiti.engine.history.HistoricActivityInstanceQuery;
/*     */ import org.activiti.engine.history.HistoricProcessInstanceQuery;
/*     */ import org.activiti.engine.impl.RepositoryServiceImpl;
/*     */ import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
/*     */ import org.activiti.engine.impl.pvm.PvmTransition;
/*     */ import org.activiti.engine.impl.pvm.process.ActivityImpl;
/*     */ import org.activiti.image.ProcessDiagramGenerator;
/*     */ import org.activiti.image.impl.DefaultProcessDiagramGenerator;
/*     */ import org.activiti.spring.ProcessEngineFactoryBean;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ 
/*     */ @org.springframework.stereotype.Service
/*     */ public class DefaultInstHistImgService implements com.dstz.bpm.act.service.BpmImageService
/*     */ {
/*     */   @Autowired
/*     */   private RepositoryService repositoryService;
/*     */   @Autowired
/*     */   private org.activiti.engine.ManagementService managementService;
/*     */   @Autowired
/*     */   private RuntimeService runtimeService;
/*     */   @Autowired
/*     */   private ProcessEngineConfiguration processEngineConfiguration;
/*     */   @Autowired
/*     */   private ProcessEngineFactoryBean i;
/*     */   @Autowired
/*     */   private HistoryService historyService;
/*     */   
/*     */   public InputStream draw(String actDefId, String actInstId) throws Exception
/*     */   {
/*  42 */     if (StringUtil.isEmpty(actDefId)) {
/*  43 */       throw new com.dstz.base.api.exception.BusinessException("流程定义actDefId不能缺失", com.dstz.bpm.api.exception.BpmStatusCode.PARAM_ILLEGAL);
/*     */     }
/*  45 */     if (StringUtil.isEmpty(actInstId)) {
/*  46 */       return b(actDefId);
/*     */     }
/*     */     
/*     */ 
/*  50 */     List<String> activeActivityIds = new ArrayList();
/*  51 */     List<String> highLightedFlows = new ArrayList();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  56 */     if (a(actInstId)) {
/*  57 */       activeActivityIds.add(((HistoricActivityInstance)this.historyService.createHistoricActivityInstanceQuery().executionId(actInstId).activityType("endEvent").singleResult()).getActivityId());
/*     */     }
/*     */     else {
/*  60 */       activeActivityIds = this.runtimeService.getActiveActivityIds(actInstId);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  70 */     List<HistoricActivityInstance> historicActivityInstances = ((HistoricActivityInstanceQuery)this.historyService.createHistoricActivityInstanceQuery().executionId(actInstId).orderByHistoricActivityInstanceStartTime().asc()).list();
/*     */     
/*  72 */     highLightedFlows = a((ProcessDefinitionEntity)((RepositoryServiceImpl)this.repositoryService).getDeployedProcessDefinition(actDefId), historicActivityInstances);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  80 */     InputStream imageStream = null;
/*  81 */     if (null != activeActivityIds) {
/*     */       try
/*     */       {
/*  84 */         ProcessEngineConfiguration processEngineConfiguration = this.i.getProcessEngineConfiguration();
/*     */         
/*  86 */         BpmnModel bpmnModel = this.repositoryService.getBpmnModel(actDefId);
/*     */         
/*  88 */         DefaultProcessDiagramGenerator diagramGenerator = new DefaultProcessDiagramGenerator();
/*  89 */         imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", activeActivityIds, highLightedFlows, processEngineConfiguration.getActivityFontName(), processEngineConfiguration.getLabelFontName(), processEngineConfiguration.getAnnotationFontName(), processEngineConfiguration.getClassLoader(), 1.0D);
/*     */       } finally {
/*  91 */         org.apache.commons.io.IOUtils.closeQuietly(imageStream);
/*     */       }
/*     */     }
/*  94 */     return imageStream;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private List<String> a(ProcessDefinitionEntity processDefinitionEntity, List<HistoricActivityInstance> historicActivityInstances)
/*     */   {
/* 106 */     List<String> highFlows = new ArrayList();
/* 107 */     List<ActivityImpl> sameStartTimeNodes; HistoricActivityInstance activityImpl1; for (int i = 0; i < historicActivityInstances.size() - 1; i++) {
/* 108 */       ActivityImpl activityImpl = processDefinitionEntity.findActivity(((HistoricActivityInstance)historicActivityInstances.get(i)).getActivityId());
/* 109 */       sameStartTimeNodes = new ArrayList();
/* 110 */       ActivityImpl sameActivityImpl1 = processDefinitionEntity.findActivity(((HistoricActivityInstance)historicActivityInstances.get(i + 1)).getActivityId());
/*     */       
/* 112 */       sameStartTimeNodes.add(sameActivityImpl1);
/* 113 */       for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
/* 114 */         activityImpl1 = (HistoricActivityInstance)historicActivityInstances.get(j);
/* 115 */         HistoricActivityInstance activityImpl2 = (HistoricActivityInstance)historicActivityInstances.get(j + 1);
/* 116 */         if (!activityImpl1.getStartTime().equals(activityImpl2.getStartTime()))
/*     */           break;
/* 118 */         ActivityImpl sameActivityImpl2 = processDefinitionEntity.findActivity(activityImpl2.getActivityId());
/* 119 */         sameStartTimeNodes.add(sameActivityImpl2);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 125 */       List<PvmTransition> pvmTransitions = activityImpl.getOutgoingTransitions();
/* 126 */       for (PvmTransition pvmTransition : pvmTransitions)
/*     */       {
/* 128 */         ActivityImpl pvmActivityImpl = (ActivityImpl)pvmTransition.getDestination();
/*     */         
/* 130 */         if (sameStartTimeNodes.contains(pvmActivityImpl)) {
/* 131 */           highFlows.add(pvmTransition.getId());
/*     */         }
/*     */       }
/*     */     }
/* 135 */     return highFlows;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean a(String processInstanceId)
/*     */   {
/* 145 */     return this.historyService.createHistoricProcessInstanceQuery().finished().processInstanceId(processInstanceId).count() > 0L;
/*     */   }
/*     */   
/*     */ 
/*     */   private InputStream b(String actDefId)
/*     */   {
/* 151 */     BpmnModel bpmnModel = this.repositoryService.getBpmnModel(actDefId);
/*     */     
/* 153 */     return this.processEngineConfiguration.getProcessDiagramGenerator()
/* 154 */       .generateDiagram(bpmnModel, "png", this.processEngineConfiguration
/* 155 */       .getActivityFontName(), this.processEngineConfiguration
/* 156 */       .getLabelFontName(), this.processEngineConfiguration
/* 157 */       .getAnnotationFontName(), this.processEngineConfiguration
/* 158 */       .getClassLoader());
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-act\1.1.5\wf-act-1.1.5-pg.jar!\com\dstz\bpm\act\service\impl\DefaultInstHistImgService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */