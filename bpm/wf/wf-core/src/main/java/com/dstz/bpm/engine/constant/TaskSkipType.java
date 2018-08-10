 package com.dstz.bpm.engine.constant;
 
 public enum TaskSkipType {
   NO_SKIP("noSkip", "不跳过"), 
   ALL_SKIP("allSkip", "所有节点跳过"), 
   FIRSTNODE_SKIP("firstNodeSkip", "开始节点跳过"), 
   SAME_USER_SKIP("sameUserSkip", "前一节点相同用户则跳过"), 
   USER_EMPTY_SKIP("userEmptySkip", "执行人为空则跳过"), 
   SCRIPT_SKIP("scriptSkip", "脚本跳过");
   
 
   private String key = "";
   
   private String value = "";
   
   private TaskSkipType(String key, String value)
   {
     this.key = key;
     this.value = value;
   }
   
   public String getKey()
   {
     return this.key;
   }
   
   public void setKey(String key) {
     this.key = key;
   }
   
   public String getValue() {
     return this.value;
   }
   
   public void setValue(String value) {
     this.value = value;
   }
   
   public String toString()
   {
     return this.key;
   }
   
 
 
 
 
 
   public static TaskSkipType fromKey(String key)
   {
     for (TaskSkipType c : ) {
       if (c.getKey().equalsIgnoreCase(key))
         return c;
     }
     throw new IllegalArgumentException(key);
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\constant\TaskSkipType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */