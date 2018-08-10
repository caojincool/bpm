/*     */ package com.dstz.bpm.engine.model;
/*     */ 
/*     */ import com.dstz.org.api.model.IUser;
/*     */ import com.dstz.sys.api.model.SysIdentity;
/*     */ import java.io.PrintStream;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BpmIdentity
/*     */   implements SysIdentity
/*     */ {
/*     */   private static final long serialVersionUID = 4416404339210896051L;
/*     */   private String id;
/*     */   private String name;
/*     */   private String type;
/*     */   
/*     */   public BpmIdentity() {}
/*     */   
/*     */   public BpmIdentity(String id, String name, String type)
/*     */   {
/*  37 */     this.id = id;
/*  38 */     this.name = name;
/*  39 */     this.type = type;
/*     */   }
/*     */   
/*     */   public BpmIdentity(IUser user) {
/*  43 */     this.id = user.getUserId();
/*  44 */     this.name = user.getFullname();
/*  45 */     this.type = "user";
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/*  49 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getId()
/*     */   {
/*  54 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  58 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getName()
/*     */   {
/*  63 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setType(String type) {
/*  67 */     this.type = type;
/*     */   }
/*     */   
/*     */   public String getType()
/*     */   {
/*  72 */     return this.type;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/*  77 */     return this.id.hashCode() + this.type.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj)
/*     */   {
/*  82 */     if (!(obj instanceof BpmIdentity)) {
/*  83 */       return false;
/*     */     }
/*  85 */     BpmIdentity identity = (BpmIdentity)obj;
/*     */     
/*  87 */     if ((identity.type.equals(this.type)) && (identity.id.equals(this.id))) {
/*  88 */       return true;
/*     */     }
/*     */     
/*  91 */     return false;
/*     */   }
/*     */   
/*     */   public static void main(String[] args)
/*     */   {
/*  96 */     BpmIdentity id1 = new BpmIdentity();
/*  97 */     id1.setId("1");
/*  98 */     id1.setType("user");
/*     */     
/* 100 */     BpmIdentity id2 = new BpmIdentity();
/* 101 */     id2.setId("1");
/* 102 */     id2.setType("user");
/*     */     
/* 104 */     Set<BpmIdentity> list = new LinkedHashSet();
/* 105 */     list.add(id1);
/* 106 */     list.add(id2);
/*     */     
/* 108 */     list.remove(id2);
/*     */     
/* 110 */     System.out.println(list.size());
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\model\BpmIdentity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */