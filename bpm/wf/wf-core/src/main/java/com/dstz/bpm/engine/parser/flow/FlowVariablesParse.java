/*    */ package com.dstz.bpm.engine.parser.flow;
/*    */ 
/*    */ import com.dstz.bpm.api.model.def.BpmVariableDef;
/*    */ import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
/*    */ import com.dstz.bpm.engine.model.DefaultBpmVariableDef;
/*    */ import java.util.HashSet;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class FlowVariablesParse
/*    */   extends AbsFlowParse<DefaultBpmVariableDef>
/*    */ {
/*    */   public String getKey()
/*    */   {
/* 19 */     return "variableList";
/*    */   }
/*    */   
/*    */   public String validate(Object object)
/*    */   {
/* 24 */     List<BpmVariableDef> varList = (List)object;
/*    */     
/* 26 */     Set<String> keys = new HashSet();
/* 27 */     for (BpmVariableDef def : varList) {
/* 28 */       String key = def.getKey();
/*    */       
/* 30 */       if (keys.contains(key)) {
/* 31 */         throw new RuntimeException("解析流程变量出错：" + key + "在流程中重复！");
/*    */       }
/* 33 */       keys.add(def.getKey());
/*    */     }
/* 35 */     return "";
/*    */   }
/*    */   
/*    */   public void a(DefaultBpmProcessDef bpmProcessDef, Object object)
/*    */   {
/* 40 */     List<BpmVariableDef> varList = (List)object;
/*    */     
/* 42 */     bpmProcessDef.setVarList(varList);
/*    */   }
/*    */   
/*    */   public boolean isArray()
/*    */   {
/* 47 */     return true;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\parser\flow\FlowVariablesParse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */