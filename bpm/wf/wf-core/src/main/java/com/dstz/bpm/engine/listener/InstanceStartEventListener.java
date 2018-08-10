 package com.dstz.bpm.engine.listener;
 
 import com.dstz.base.api.exception.BusinessException;
 import com.dstz.base.core.util.BeanUtils;
 import com.dstz.base.core.util.StringUtil;
 import com.dstz.base.core.util.time.DateUtil;
 import com.dstz.bpm.api.constant.EventType;
 import com.dstz.bpm.api.constant.ScriptType;
 import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
 import com.dstz.bpm.api.engine.action.cmd.InstanceActionCmd;
 import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
 import com.dstz.bpm.api.engine.context.BpmContext;
 import com.dstz.bpm.api.exception.BpmStatusCode;
 import com.dstz.bpm.api.model.def.BpmDefProperties;
 import com.dstz.bpm.api.model.inst.IBpmInstance;
 import com.dstz.bpm.api.service.BpmProcessDefService;
 import com.dstz.bpm.core.manager.BpmDefinitionManager;
 import com.dstz.bpm.core.manager.BpmInstanceManager;
 import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
 import com.dstz.bpm.core.model.BpmDefinition;
 import com.dstz.bpm.core.model.BpmInstance;
 import com.dstz.bpm.engine.action.cmd.DefaultInstanceActionCmd;
 import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
 import com.dstz.bus.api.model.IBusinessData;
 import com.dstz.org.api.model.IUser;
 import com.dstz.sys.util.ContextUtil;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.Map;
 import java.util.Map.Entry;
 import java.util.Set;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 import javax.annotation.Resource;
 import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
 import org.apache.commons.lang3.StringUtils;
 import org.slf4j.Logger;
 import org.springframework.stereotype.Component;
 
 @Component
 public class InstanceStartEventListener
   extends AbstractInstanceListener
 {
   @Resource
   BpmTaskOpinionManager aO;
   @Resource
   BpmProcessDefService a;
   @Resource
   BpmInstanceManager f;
   @Resource
   BpmDefinitionManager aP;
   
   public EventType getBeforeTriggerEventType()
   {
     return EventType.START_EVENT;
   }
   
   public EventType getAfterTriggerEventType()
   {
     return EventType.START_POST_EVENT;
   }
   
   public void a(InstanceActionCmd instanceActionModel)
   {
     this.LOG.debug("流程实例【{}】执行启动过程 instanceID:[{}]", instanceActionModel.getBpmInstance().getSubject(), instanceActionModel.getBpmInstance().getId());
     
     Map<String, Object> actionVariables = instanceActionModel.getActionVariables();
     if (BeanUtils.isEmpty(actionVariables)) {
       return;
     }
     
     for (String key : actionVariables.keySet()) {
       instanceActionModel.addVariable(key, actionVariables.get(key));
     }
     this.LOG.debug("设置流程变量【{}】", actionVariables.keySet().toString());
   }
   
 
   public void b(InstanceActionCmd instanceActionModel)
   {
     this.aO.createOpinionByInstance(instanceActionModel.getBpmInstance(), instanceActionModel.getFormId(), true);
     
     g((DefaultInstanceActionCmd)instanceActionModel);
   }
   
   public void c(InstanceActionCmd instanceActionModel)
   {
     this.LOG.debug("流程实例【{}】完成创建过程   instanceID：{}", instanceActionModel.getBpmInstance().getSubject(), instanceActionModel.getBpmInstance().getId());
   }
   
   protected ScriptType getScriptType()
   {
     return ScriptType.START;
   }
   
 
   protected InstanceActionCmd a(ExecutionEntity excutionEntity)
   {
     ActionCmd actionCmd = BpmContext.getActionModel();
     a(excutionEntity, actionCmd);
     
     DefaultInstanceActionCmd actionModel = (DefaultInstanceActionCmd)BpmContext.getActionModel();
     actionModel.setExecutionEntity(excutionEntity);
     
     BpmInstance instance = (BpmInstance)actionModel.getBpmInstance();
     if (BeanUtils.isEmpty(instance.getActInstId())) {
       instance.setActDefId(excutionEntity.getProcessDefinitionId());
       instance.setActInstId(excutionEntity.getProcessInstanceId());
     }
     return actionModel;
   }
   
   private void g(DefaultInstanceActionCmd data)
   {
     BpmInstance instance = (BpmInstance)data.getBpmInstance();
     
     DefaultBpmProcessDef processDef = (DefaultBpmProcessDef)this.a.getBpmProcessDef(instance.getDefId());
     String subjectRule = processDef.getExtProperties().getSubjectRule();
     
     if (StringUtil.isEmpty(subjectRule)) { return;
     }
     Map<String, Object> ruleVariables = new HashMap();
     ruleVariables.put("title", processDef.getName());
     ruleVariables.put("startorName", ContextUtil.getCurrentUser().getFullname());
     ruleVariables.put("startDate", DateUtil.getCurrentTime("yyyy-MM-dd"));
     ruleVariables.put("startTime", DateUtil.getCurrentTime());
     ruleVariables.putAll(data.getVariables());
     
 
     Map<String, IBusinessData> boMap = data.getBizDataMap();
     Iterator localIterator1; if (BeanUtils.isNotEmpty(boMap)) {
       Set<String> bocodes = boMap.keySet();
       for (localIterator1 = bocodes.iterator(); localIterator1.hasNext();) { bocode = (String)localIterator1.next();
         IBusinessData bizData = (IBusinessData)boMap.get(bocode);
         
         Map<String, Object> dataMap = bizData.getData();
         for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
           ruleVariables.put(bocode + "." + (String)entry.getKey(), entry.getValue());
         }
       }
     }
     String bocode;
     String subject = a(subjectRule, ruleVariables);
     
     instance.setSubject(subject);
     this.LOG.debug("更新流程标题:{}", subject);
   }
   
   private String a(String subject, Map<String, Object> variables) {
     if (StringUtils.isEmpty(subject))
       return "";
     Pattern regex = Pattern.compile("\\{(.*?)\\}", 98);
     Matcher matcher = regex.matcher(subject);
     while (matcher.find()) {
       String tag = matcher.group(0);
       String rule = matcher.group(1);
       String[] aryRule = rule.split(":");
       String name = "";
       if (aryRule.length == 1) {
         name = rule;
       } else {
         name = aryRule[1];
       }
       if (variables.containsKey(name)) {
         Object obj = variables.get(name);
         if (obj != null) {
           try {
             subject = subject.replace(tag, obj.toString());
           } catch (Exception e) {
             subject = subject.replace(tag, "");
           }
         } else {
           subject = subject.replace(tag, "");
         }
       } else {
         subject = subject.replace(tag, "");
       }
     }
     return subject;
   }
   
 
 
 
 
   private void a(ExecutionEntity excutionEntity, ActionCmd preAction)
   {
     String preActionDefKey = preAction.getBpmInstance().getDefKey();
     
     if ((preAction instanceof InstanceActionCmd)) {
       if (!excutionEntity.getProcessDefinitionKey().equals(preActionDefKey)) {
         throw new BusinessException("流程启动失败，错误的线程数据！", BpmStatusCode.ACTIONCMD_ERROR);
       }
       return;
     }
     
     ExecutionEntity callActivityNode = excutionEntity.getSuperExecution();
     
 
 
     if (((preAction instanceof TaskActionCmd)) && (
       (callActivityNode == null) || (!preAction.getBpmInstance().getActInstId().equals(callActivityNode.getProcessInstanceId())))) {
       throw new BusinessException(BpmStatusCode.ACTIONCMD_ERROR);
     }
     
 
 
 
 
 
     BpmDefinition subDefinition = this.aP.getByKey(excutionEntity.getProcessDefinitionKey());
     BpmInstance subInstance = this.f.genInstanceByDefinition(subDefinition);
     
     subInstance.setActInstId(excutionEntity.getProcessInstanceId());
     subInstance.setParentInstId(preAction.getBpmInstance().getId());
     this.f.create(subInstance);
     
     DefaultInstanceActionCmd startAction = new DefaultInstanceActionCmd();
     startAction.setBpmDefinition(subDefinition);
     startAction.setBpmInstance(subInstance);
     startAction.setBizDataMap(preAction.getBizDataMap());
     
     BpmContext.setActionModel(startAction);
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\listener\InstanceStartEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */