/*    */ package com.dstz.bpm.engine.plugin.plugindef;
/*    */ 
/*    */ import com.dstz.bpm.api.constant.ExtractType;
/*    */ import com.dstz.bpm.api.engine.constant.LogicType;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmUserCalcPluginDef;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractUserCalcPluginDef
/*    */   implements BpmUserCalcPluginDef
/*    */ {
/* 14 */   private ExtractType bJ = ExtractType.EXACT_NOEXACT;
/* 15 */   private LogicType bK = LogicType.OR;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public ExtractType getExtract()
/*    */   {
/* 24 */     return this.bJ;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setExtract(ExtractType type)
/*    */   {
/* 35 */     this.bJ = type;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public LogicType getLogicCal()
/*    */   {
/* 45 */     return this.bK;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setLogicCal(LogicType logicType)
/*    */   {
/* 55 */     this.bK = logicType;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\plugin\plugindef\AbstractUserCalcPluginDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */