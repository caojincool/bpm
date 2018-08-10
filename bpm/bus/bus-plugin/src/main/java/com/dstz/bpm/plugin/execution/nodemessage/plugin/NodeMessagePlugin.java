/*     */ package com.dstz.bpm.plugin.execution.nodemessage.plugin;
/*     */ 
/*     */ import com.dstz.base.core.util.BeanUtils;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.bpm.api.constant.EventType;
/*     */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*     */ import com.dstz.bpm.api.engine.context.BpmContext;
/*     */ import com.dstz.bpm.api.engine.plugin.def.UserAssignRule;
/*     */ import com.dstz.bpm.api.service.BpmProcessDefService;
/*     */ import com.dstz.bpm.engine.plugin.factory.BpmPluginSessionFactory;
/*     */ import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractBpmExecutionPlugin;
/*     */ import com.dstz.bpm.engine.plugin.session.BpmExecutionPluginSession;
/*     */ import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
/*     */ import com.dstz.bpm.plugin.execution.nodemessage.def.NodeMessage;
/*     */ import com.dstz.bpm.plugin.execution.nodemessage.def.NodeMessagePluginDef;
/*     */ import com.dstz.bpm.plugin.task.userassign.plugin.UserAssignRuleCalc;
/*     */ import com.dstz.sys.api.freemark.IFreemarkEngine;
/*     */ import com.dstz.sys.api.groovy.IGroovyScriptEngine;
/*     */ import com.dstz.sys.api.jms.model.DefaultJmsDTO;
/*     */ import com.dstz.sys.api.jms.model.JmsDTO;
/*     */ import com.dstz.sys.api.jms.model.msg.NotifyMessage;
/*     */ import com.dstz.sys.api.jms.producer.JmsProducer;
/*     */ import com.dstz.sys.api.model.SysIdentity;
/*     */ import com.dstz.sys.util.ContextUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import org.slf4j.Logger;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ public class NodeMessagePlugin
/*     */   extends AbstractBpmExecutionPlugin<BpmExecutionPluginSession, NodeMessagePluginDef>
/*     */ {
/*     */   @Resource
/*     */   IGroovyScriptEngine g;
/*     */   @Resource
/*     */   BpmProcessDefService h;
/*     */   @Resource
/*     */   JmsProducer i;
/*     */   @Autowired
/*     */   IFreemarkEngine j;
/*     */   
/*     */   public Void a(BpmExecutionPluginSession pluginSession, NodeMessagePluginDef pluginDef)
/*     */   {
/*  52 */     List<NodeMessage> messages = pluginDef.getNodeMessageList();
/*  53 */     for (NodeMessage nodeMessage : messages)
/*     */     {
/*  55 */       if (a(pluginSession, nodeMessage))
/*     */       {
/*     */ 
/*     */ 
/*  59 */         List<JmsDTO> JmsDto = a(nodeMessage, pluginSession);
/*  60 */         this.i.sendToQueue(JmsDto);
/*  61 */         this.LOG.debug("【节点消息插件】发送消息成功！时机：{}，消息标题：{}", pluginSession.getEventType().getValue(), nodeMessage.getDesc());
/*     */       }
/*     */     }
/*  64 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private List<JmsDTO> a(NodeMessage nodeMessage, BpmExecutionPluginSession session)
/*     */   {
/*  74 */     String[] msgType = nodeMessage.getMsgType().split(",");
/*     */     
/*  76 */     String htmlTemplate = nodeMessage.getHtmlTemplate();
/*  77 */     String textTemplate = nodeMessage.getTextTemplate();
/*     */     try
/*     */     {
/*  80 */       if (StringUtil.isNotEmpty(htmlTemplate)) {
/*  81 */         htmlTemplate = htmlTemplate.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
/*     */         
/*  83 */         htmlTemplate = this.j.parseByString(htmlTemplate, session);
/*     */       }
/*  85 */       if (StringUtil.isNotEmpty(textTemplate)) {
/*  86 */         textTemplate = this.j.parseByString(textTemplate, session);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  90 */       this.LOG.error("htmlTemplate:{};textTempalte:{}", htmlTemplate, textTemplate);
/*  91 */       this.LOG.error("消息发送插件解析消息模板失败，可能原因为:{}", e.getMessage(), e);
/*     */       
/*  93 */       e.printStackTrace();
/*     */     }
/*     */     
/*  96 */     List<SysIdentity> userList = new ArrayList();
/*  97 */     if (BeanUtils.isEmpty(nodeMessage.getUserRules())) {
/*  98 */       BaseActionCmd cmd = (BaseActionCmd)BpmContext.getActionModel();
/*  99 */       userList = cmd.getBpmIdentity(cmd.getNodeId());
/*     */     } else {
/* 101 */       userList = a(session, nodeMessage.getUserRules());
/*     */     }
/*     */     
/* 104 */     if (BeanUtils.isEmpty(userList)) {
/* 105 */       this.LOG.info("【节点消息插件】发送消息失败！原因：接收消息人员为空。 节点：{}，时机：{}，消息标题：{}", new Object[] { getActivitiId(session), session.getEventType().getValue(), nodeMessage.getDesc() });
/* 106 */       return Collections.emptyList();
/*     */     }
/*     */     
/*     */ 
/* 110 */     List<JmsDTO> jmsDto = new ArrayList();
/*     */     
/* 112 */     for (String type : msgType) {
/* 113 */       NotifyMessage message = new NotifyMessage(nodeMessage.getDesc(), htmlTemplate, ContextUtil.getCurrentUser(), userList);
/* 114 */       jmsDto.add(new DefaultJmsDTO(type, message));
/*     */     }
/*     */     
/* 117 */     return jmsDto;
/*     */   }
/*     */   
/*     */   private List<SysIdentity> a(BpmExecutionPluginSession pluginSession, List<UserAssignRule> ruleList)
/*     */   {
/* 122 */     BpmUserCalcPluginSession calcSession = BpmPluginSessionFactory.buildBpmUserCalcPluginSession(pluginSession);
/* 123 */     return UserAssignRuleCalc.a(calcSession, ruleList, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean a(BpmExecutionPluginSession session, NodeMessage nodeMessage)
/*     */   {
/* 135 */     if ((StringUtil.isNotEmpty(nodeMessage.getEvent())) && 
/* 136 */       (!nodeMessage.getEvent().equals(session.getEventType().getKey()))) {
/* 137 */       return false;
/*     */     }
/*     */     
/* 140 */     if ((StringUtil.isNotEmpty(nodeMessage.getNodeId())) && 
/* 141 */       (!nodeMessage.getNodeId().equals(getActivitiId(session)))) {
/* 142 */       return false;
/*     */     }
/*     */     
/* 145 */     if (StringUtil.isNotEmpty(nodeMessage.getCondition())) {
/* 146 */       Boolean support = Boolean.valueOf(this.g.executeBoolean(nodeMessage.getCondition(), session));
/* 147 */       if (!support.booleanValue()) { return false;
/*     */       }
/*     */     }
/* 150 */     return true;
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\execution\nodemessage\plugin\NodeMessagePlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */