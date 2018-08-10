/*    */ package com.dstz.bpm.act.id;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ActivitiIdGenerator
/*    */   implements org.activiti.engine.impl.cfg.IdGenerator
/*    */ {
/* 10 */   private com.dstz.base.db.api.IdGenerator f = null;
/*    */   
/*    */   public void setIdGenerator(com.dstz.base.db.api.IdGenerator idGenerator) {
/* 13 */     this.f = idGenerator;
/*    */   }
/*    */   
/*    */   public String getNextId()
/*    */   {
/* 18 */     return this.f.getSuid();
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-act\1.1.5\wf-act-1.1.5-pg.jar!\com\dstz\bpm\act\id\ActivitiIdGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */