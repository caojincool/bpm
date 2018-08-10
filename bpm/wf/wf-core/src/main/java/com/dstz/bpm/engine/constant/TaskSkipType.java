/*    */ package com.dstz.bpm.engine.constant;
/*    */ 
/*    */ public enum TaskSkipType {
/*  4 */   NO_SKIP("noSkip", "不跳过"), 
/*  5 */   ALL_SKIP("allSkip", "所有节点跳过"), 
/*  6 */   FIRSTNODE_SKIP("firstNodeSkip", "开始节点跳过"), 
/*  7 */   SAME_USER_SKIP("sameUserSkip", "前一节点相同用户则跳过"), 
/*  8 */   USER_EMPTY_SKIP("userEmptySkip", "执行人为空则跳过"), 
/*  9 */   SCRIPT_SKIP("scriptSkip", "脚本跳过");
/*    */   
/*    */ 
/* 12 */   private String key = "";
/*    */   
/* 14 */   private String value = "";
/*    */   
/*    */   private TaskSkipType(String key, String value)
/*    */   {
/* 18 */     this.key = key;
/* 19 */     this.value = value;
/*    */   }
/*    */   
/*    */   public String getKey()
/*    */   {
/* 24 */     return this.key;
/*    */   }
/*    */   
/*    */   public void setKey(String key) {
/* 28 */     this.key = key;
/*    */   }
/*    */   
/*    */   public String getValue() {
/* 32 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(String value) {
/* 36 */     this.value = value;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 41 */     return this.key;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static TaskSkipType fromKey(String key)
/*    */   {
/* 51 */     for (TaskSkipType c : ) {
/* 52 */       if (c.getKey().equalsIgnoreCase(key))
/* 53 */         return c;
/*    */     }
/* 55 */     throw new IllegalArgumentException(key);
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\constant\TaskSkipType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */