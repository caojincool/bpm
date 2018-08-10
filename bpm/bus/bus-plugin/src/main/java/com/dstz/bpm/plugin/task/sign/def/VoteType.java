/*    */ package com.dstz.bpm.plugin.task.sign.def;
/*    */ 
/*    */ public enum VoteType {
/*  4 */   AMOUNT("amount", "投票数"), 
/*  5 */   PERCENT("percent", "百分比");
/*    */   
/*  7 */   private String key = "";
/*  8 */   private String value = "";
/*    */   
/*    */   private VoteType(String key, String value) {
/* 11 */     this.key = key;
/* 12 */     this.value = value;
/*    */   }
/*    */   
/*    */   public String getKey()
/*    */   {
/* 17 */     return this.key;
/*    */   }
/*    */   
/*    */   public void setKey(String key) {
/* 21 */     this.key = key;
/*    */   }
/*    */   
/*    */   public String getValue() {
/* 25 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(String value) {
/* 29 */     this.value = value;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 34 */     return this.key;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static VoteType fromKey(String key)
/*    */   {
/* 43 */     for (VoteType c : ) {
/* 44 */       if (c.getKey().equalsIgnoreCase(key))
/* 45 */         return c;
/*    */     }
/* 47 */     throw new IllegalArgumentException(key);
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\task\sign\def\VoteType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */