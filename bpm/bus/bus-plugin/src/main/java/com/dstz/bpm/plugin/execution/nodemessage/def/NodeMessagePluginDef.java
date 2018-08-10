/*    */ package com.dstz.bpm.plugin.execution.nodemessage.def;
/*    */ 
/*    */ import com.dstz.bpm.engine.plugin.plugindef.AbstractBpmExecutionPluginDef;
/*    */ import java.util.List;
/*    */ import javax.validation.Valid;
/*    */ import org.hibernate.validator.constraints.NotEmpty;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NodeMessagePluginDef
/*    */   extends AbstractBpmExecutionPluginDef
/*    */ {
/*    */   @Valid
/*    */   @NotEmpty
/*    */   private List<NodeMessage> f;
/*    */   
/*    */   public NodeMessagePluginDef(List<NodeMessage> nodeMessageList)
/*    */   {
/* 20 */     this.f = nodeMessageList;
/*    */   }
/*    */   
/*    */   public List<NodeMessage> getNodeMessageList() {
/* 24 */     return this.f;
/*    */   }
/*    */   
/*    */   public void setNodeMessageList(List<NodeMessage> nodeMessageList) {
/* 28 */     this.f = nodeMessageList;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\execution\nodemessage\def\NodeMessagePluginDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */