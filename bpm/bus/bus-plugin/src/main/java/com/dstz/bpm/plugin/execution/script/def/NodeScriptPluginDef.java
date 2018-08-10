/*    */ package com.dstz.bpm.plugin.execution.script.def;
/*    */ 
/*    */ import com.dstz.bpm.api.constant.EventType;
/*    */ import com.dstz.bpm.engine.plugin.plugindef.AbstractBpmExecutionPluginDef;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.hibernate.validator.constraints.NotEmpty;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NodeScriptPluginDef
/*    */   extends AbstractBpmExecutionPluginDef
/*    */ {
/*    */   @NotEmpty(message="事件脚本节点ID不能为空")
/* 16 */   private String a = "";
/*    */   
/* 18 */   private Map<EventType, String> k = new HashMap();
/*    */   
/*    */   public String a(EventType event) {
/* 21 */     return (String)this.k.get(event);
/*    */   }
/*    */   
/*    */   public void a(EventType event, String scritp) {
/* 25 */     this.k.put(event, scritp);
/*    */   }
/*    */   
/*    */   public Map<EventType, String> getScript() {
/* 29 */     return this.k;
/*    */   }
/*    */   
/*    */   public void setScript(Map<EventType, String> script) {
/* 33 */     this.k = script;
/*    */   }
/*    */   
/*    */   public String getNodeId() {
/* 37 */     return this.a;
/*    */   }
/*    */   
/*    */   public void setNodeId(String nodeId) {
/* 41 */     this.a = nodeId;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\execution\script\def\NodeScriptPluginDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */