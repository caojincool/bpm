/*    */ package com.dstz.bpm.engine.parser.node;
/*    */ 
/*    */ import com.dstz.bpm.api.model.def.NodeProperties;
/*    */ import com.dstz.bpm.api.model.nodedef.impl.BaseBpmNodeDef;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class NodePropertiesParse
/*    */   extends AbsNodeParse<NodeProperties>
/*    */ {
/*    */   public String getKey()
/*    */   {
/* 14 */     return "propertie";
/*    */   }
/*    */   
/*    */   public void a(BaseBpmNodeDef userNodeDef, Object object)
/*    */   {
/* 19 */     NodeProperties prop = (NodeProperties)object;
/*    */     
/* 21 */     userNodeDef.setNodeProperties(prop);
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\parser\node\NodePropertiesParse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */