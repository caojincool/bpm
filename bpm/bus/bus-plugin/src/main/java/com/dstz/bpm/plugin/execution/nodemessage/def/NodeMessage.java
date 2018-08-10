/*    */ package com.dstz.bpm.plugin.execution.nodemessage.def;
/*    */ 
/*    */ import com.dstz.bpm.api.engine.plugin.def.UserAssignRule;
/*    */ import com.dstz.bpm.engine.plugin.plugindef.AbstractBpmExecutionPluginDef;
/*    */ import java.util.List;
/*    */ import org.hibernate.validator.constraints.NotBlank;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NodeMessage
/*    */   extends AbstractBpmExecutionPluginDef
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @NotBlank(message="节点消息描述不能为空")
/*    */   private String desc;
/*    */   private String a;
/*    */   @NotBlank
/*    */   private String event;
/*    */   private String condition;
/*    */   private List<UserAssignRule> b;
/*    */   @NotBlank
/*    */   private String c;
/*    */   private String d;
/*    */   private String e;
/*    */   
/*    */   public String getDesc()
/*    */   {
/* 29 */     return this.desc;
/*    */   }
/*    */   
/*    */   public void setDesc(String desc) {
/* 33 */     this.desc = desc;
/*    */   }
/*    */   
/*    */   public String getNodeId() {
/* 37 */     return this.a;
/*    */   }
/*    */   
/*    */   public void setNodeId(String nodeId) {
/* 41 */     this.a = nodeId;
/*    */   }
/*    */   
/*    */   public String getEvent() {
/* 45 */     return this.event;
/*    */   }
/*    */   
/*    */   public void setEvent(String event) {
/* 49 */     this.event = event;
/*    */   }
/*    */   
/*    */   public String getCondition() {
/* 53 */     return this.condition;
/*    */   }
/*    */   
/*    */   public void setCondition(String condition) {
/* 57 */     this.condition = condition;
/*    */   }
/*    */   
/*    */   public List<UserAssignRule> getUserRules() {
/* 61 */     return this.b;
/*    */   }
/*    */   
/*    */   public void setUserRules(List<UserAssignRule> userRules) {
/* 65 */     this.b = userRules;
/*    */   }
/*    */   
/*    */   public String getMsgType() {
/* 69 */     return this.c;
/*    */   }
/*    */   
/*    */   public void setMsgType(String msgType) {
/* 73 */     this.c = msgType;
/*    */   }
/*    */   
/*    */   public String getHtmlTemplate() {
/* 77 */     return this.d;
/*    */   }
/*    */   
/*    */   public void setHtmlTemplate(String htmlTemplate) {
/* 81 */     this.d = htmlTemplate;
/*    */   }
/*    */   
/*    */   public String getTextTemplate() {
/* 85 */     return this.e;
/*    */   }
/*    */   
/*    */   public void setTextTemplate(String textTemplate) {
/* 89 */     this.e = textTemplate;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\execution\nodemessage\def\NodeMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */