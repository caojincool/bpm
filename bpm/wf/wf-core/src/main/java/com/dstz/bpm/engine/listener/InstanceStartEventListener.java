/*     */ package com.dstz.bpm.engine.listener;
/*     */ 
/*     */ import com.dstz.base.api.exception.BusinessException;
/*     */ import com.dstz.base.core.util.BeanUtils;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.base.core.util.time.DateUtil;
/*     */ import com.dstz.bpm.api.constant.EventType;
/*     */ import com.dstz.bpm.api.constant.ScriptType;
/*     */ import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
/*     */ import com.dstz.bpm.api.engine.action.cmd.InstanceActionCmd;
/*     */ import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
/*     */ import com.dstz.bpm.api.engine.context.BpmContext;
/*     */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*     */ import com.dstz.bpm.api.model.def.BpmDefProperties;
/*     */ import com.dstz.bpm.api.model.inst.IBpmInstance;
/*     */ import com.dstz.bpm.api.service.BpmProcessDefService;
/*     */ import com.dstz.bpm.core.manager.BpmDefinitionManager;
/*     */ import com.dstz.bpm.core.manager.BpmInstanceManager;
/*     */ import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
/*     */ import com.dstz.bpm.core.model.BpmDefinition;
/*     */ import com.dstz.bpm.core.model.BpmInstance;
/*     */ import com.dstz.bpm.engine.action.cmd.DefaultInstanceActionCmd;
/*     */ import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
/*     */ import com.dstz.bus.api.model.IBusinessData;
/*     */ import com.dstz.org.api.model.IUser;
/*     */ import com.dstz.sys.util.ContextUtil;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.annotation.Resource;
/*     */ import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ @Component
/*     */ public class InstanceStartEventListener
/*     */   extends AbstractInstanceListener
/*     */ {
/*     */   @Resource
/*     */   BpmTaskOpinionManager aO;
/*     */   @Resource
/*     */   BpmProcessDefService a;
/*     */   @Resource
/*     */   BpmInstanceManager f;
/*     */   @Resource
/*     */   BpmDefinitionManager aP;
/*     */   
/*     */   public EventType getBeforeTriggerEventType()
/*     */   {
/*  55 */     return EventType.START_EVENT;
/*     */   }
/*     */   
/*     */   public EventType getAfterTriggerEventType()
/*     */   {
/*  60 */     return EventType.START_POST_EVENT;
/*     */   }
/*     */   
/*     */   public void a(InstanceActionCmd instanceActionModel)
/*     */   {
/*  65 */     this.LOG.debug("流程实例【{}】执行启动过程 instanceID:[{}]", instanceActionModel.getBpmInstance().getSubject(), instanceActionModel.getBpmInstance().getId());
/*     */     
/*  67 */     Map<String, Object> actionVariables = instanceActionModel.getActionVariables();
/*  68 */     if (BeanUtils.isEmpty(actionVariables)) {
/*  69 */       return;
/*     */     }
/*     */     
/*  72 */     for (String key : actionVariables.keySet()) {
/*  73 */       instanceActionModel.addVariable(key, actionVariables.get(key));
/*     */     }
/*  75 */     this.LOG.debug("设置流程变量【{}】", actionVariables.keySet().toString());
/*     */   }
/*     */   
/*     */ 
/*     */   public void b(InstanceActionCmd instanceActionModel)
/*     */   {
/*  81 */     this.aO.createOpinionByInstance(instanceActionModel.getBpmInstance(), instanceActionModel.getFormId(), true);
/*     */     
/*  83 */     g((DefaultInstanceActionCmd)instanceActionModel);
/*     */   }
/*     */   
/*     */   public void c(InstanceActionCmd instanceActionModel)
/*     */   {
/*  88 */     this.LOG.debug("流程实例【{}】完成创建过程   instanceID：{}", instanceActionModel.getBpmInstance().getSubject(), instanceActionModel.getBpmInstance().getId());
/*     */   }
/*     */   
/*     */   protected ScriptType getScriptType()
/*     */   {
/*  93 */     return ScriptType.START;
/*     */   }
/*     */   
/*     */ 
/*     */   protected InstanceActionCmd a(ExecutionEntity excutionEntity)
/*     */   {
/*  99 */     ActionCmd actionCmd = BpmContext.getActionModel();
/* 100 */     a(excutionEntity, actionCmd);
/*     */     
/* 102 */     DefaultInstanceActionCmd actionModel = (DefaultInstanceActionCmd)BpmContext.getActionModel();
/* 103 */     actionModel.setExecutionEntity(excutionEntity);
/*     */     
/* 105 */     BpmInstance instance = (BpmInstance)actionModel.getBpmInstance();
/* 106 */     if (BeanUtils.isEmpty(instance.getActInstId())) {
/* 107 */       instance.setActDefId(excutionEntity.getProcessDefinitionId());
/* 108 */       instance.setActInstId(excutionEntity.getProcessInstanceId());
/*     */     }
/* 110 */     return actionModel;
/*     */   }
/*     */   
/*     */   private void g(DefaultInstanceActionCmd data)
/*     */   {
/* 115 */     BpmInstance instance = (BpmInstance)data.getBpmInstance();
/*     */     
/* 117 */     DefaultBpmProcessDef processDef = (DefaultBpmProcessDef)this.a.getBpmProcessDef(instance.getDefId());
/* 118 */     String subjectRule = processDef.getExtProperties().getSubjectRule();
/*     */     
/* 120 */     if (StringUtil.isEmpty(subjectRule)) { return;
/*     */     }
/* 122 */     Map<String, Object> ruleVariables = new HashMap();
/* 123 */     ruleVariables.put("title", processDef.getName());
/* 124 */     ruleVariables.put("startorName", ContextUtil.getCurrentUser().getFullname());
/* 125 */     ruleVariables.put("startDate", DateUtil.getCurrentTime("yyyy-MM-dd"));
/* 126 */     ruleVariables.put("startTime", DateUtil.getCurrentTime());
/* 127 */     ruleVariables.putAll(data.getVariables());
/*     */     
/*     */ 
/* 130 */     Map<String, IBusinessData> boMap = data.getBizDataMap();
/* 131 */     Iterator localIterator1; if (BeanUtils.isNotEmpty(boMap)) {
/* 132 */       Set<String> bocodes = boMap.keySet();
/* 133 */       for (localIterator1 = bocodes.iterator(); localIterator1.hasNext();) { bocode = (String)localIterator1.next();
/* 134 */         IBusinessData bizData = (IBusinessData)boMap.get(bocode);
/*     */         
/* 136 */         Map<String, Object> dataMap = bizData.getData();
/* 137 */         for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
/* 138 */           ruleVariables.put(bocode + "." + (String)entry.getKey(), entry.getValue());
/*     */         }
/*     */       }
/*     */     }
/*     */     String bocode;
/* 143 */     String subject = a(subjectRule, ruleVariables);
/*     */     
/* 145 */     instance.setSubject(subject);
/* 146 */     this.LOG.debug("更新流程标题:{}", subject);
/*     */   }
/*     */   
/*     */   private String a(String subject, Map<String, Object> variables) {
/* 150 */     if (StringUtils.isEmpty(subject))
/* 151 */       return "";
/* 152 */     Pattern regex = Pattern.compile("\\{(.*?)\\}", 98);
/* 153 */     Matcher matcher = regex.matcher(subject);
/* 154 */     while (matcher.find()) {
/* 155 */       String tag = matcher.group(0);
/* 156 */       String rule = matcher.group(1);
/* 157 */       String[] aryRule = rule.split(":");
/* 158 */       String name = "";
/* 159 */       if (aryRule.length == 1) {
/* 160 */         name = rule;
/*     */       } else {
/* 162 */         name = aryRule[1];
/*     */       }
/* 164 */       if (variables.containsKey(name)) {
/* 165 */         Object obj = variables.get(name);
/* 166 */         if (obj != null) {
/*     */           try {
/* 168 */             subject = subject.replace(tag, obj.toString());
/*     */           } catch (Exception e) {
/* 170 */             subject = subject.replace(tag, "");
/*     */           }
/*     */         } else {
/* 173 */           subject = subject.replace(tag, "");
/*     */         }
/*     */       } else {
/* 176 */         subject = subject.replace(tag, "");
/*     */       }
/*     */     }
/* 179 */     return subject;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void a(ExecutionEntity excutionEntity, ActionCmd preAction)
/*     */   {
/* 188 */     String preActionDefKey = preAction.getBpmInstance().getDefKey();
/*     */     
/* 190 */     if ((preAction instanceof InstanceActionCmd)) {
/* 191 */       if (!excutionEntity.getProcessDefinitionKey().equals(preActionDefKey)) {
/* 192 */         throw new BusinessException("流程启动失败，错误的线程数据！", BpmStatusCode.ACTIONCMD_ERROR);
/*     */       }
/* 194 */       return;
/*     */     }
/*     */     
/* 197 */     ExecutionEntity callActivityNode = excutionEntity.getSuperExecution();
/*     */     
/*     */ 
/*     */ 
/* 201 */     if (((preAction instanceof TaskActionCmd)) && (
/* 202 */       (callActivityNode == null) || (!preAction.getBpmInstance().getActInstId().equals(callActivityNode.getProcessInstanceId())))) {
/* 203 */       throw new BusinessException(BpmStatusCode.ACTIONCMD_ERROR);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 211 */     BpmDefinition subDefinition = this.aP.getByKey(excutionEntity.getProcessDefinitionKey());
/* 212 */     BpmInstance subInstance = this.f.genInstanceByDefinition(subDefinition);
/*     */     
/* 214 */     subInstance.setActInstId(excutionEntity.getProcessInstanceId());
/* 215 */     subInstance.setParentInstId(preAction.getBpmInstance().getId());
/* 216 */     this.f.create(subInstance);
/*     */     
/* 218 */     DefaultInstanceActionCmd startAction = new DefaultInstanceActionCmd();
/* 219 */     startAction.setBpmDefinition(subDefinition);
/* 220 */     startAction.setBpmInstance(subInstance);
/* 221 */     startAction.setBizDataMap(preAction.getBizDataMap());
/*     */     
/* 223 */     BpmContext.setActionModel(startAction);
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\listener\InstanceStartEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */