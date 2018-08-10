/*     */ package com.dstz.bus.model;
/*     */ 
/*     */ import com.dstz.base.core.util.BeanUtils;
/*     */ import com.dstz.bus.api.model.IBusTableRel;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
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
/*     */ public class BusTableRel
/*     */   implements IBusTableRel, Serializable
/*     */ {
/*     */   private List<BusTableRel> children;
/*     */   private String s;
/*     */   private String t;
/*     */   private String type;
/*     */   private List<BusTableRelFk> u;
/*     */   private BusinessTable v;
/*     */   private BusTableRel w;
/*     */   private BusinessObject x;
/*     */   
/*     */   public List<BusTableRel> getChildren()
/*     */   {
/*  57 */     if (this.children == null) {
/*  58 */       return Collections.emptyList();
/*     */     }
/*  60 */     return this.children;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<IBusTableRel> getChildren(String type)
/*     */   {
/*  67 */     List<IBusTableRel> list = new ArrayList();
/*  68 */     if (BeanUtils.isNotEmpty(this.children)) {
/*  69 */       for (BusTableRel rel : this.children) {
/*  70 */         if (type.equals(rel.getType()))
/*  71 */           list.add(rel);
/*     */       }
/*     */     }
/*  74 */     return list;
/*     */   }
/*     */   
/*     */   public void setChildren(List<BusTableRel> children) {
/*  78 */     this.children = children;
/*     */   }
/*     */   
/*     */   public String getTableKey()
/*     */   {
/*  83 */     return this.s;
/*     */   }
/*     */   
/*     */   public void setTableKey(String tableKey) {
/*  87 */     this.s = tableKey;
/*     */   }
/*     */   
/*     */   public String getTableComment()
/*     */   {
/*  92 */     return this.t;
/*     */   }
/*     */   
/*     */   public void setTableComment(String tableComment) {
/*  96 */     this.t = tableComment;
/*     */   }
/*     */   
/*     */   public String getType()
/*     */   {
/* 101 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(String type) {
/* 105 */     this.type = type;
/*     */   }
/*     */   
/*     */   public List<BusTableRelFk> getFks()
/*     */   {
/* 110 */     return this.u;
/*     */   }
/*     */   
/*     */   public void setFks(List<BusTableRelFk> fks) {
/* 114 */     this.u = fks;
/*     */   }
/*     */   
/*     */   public BusTableRel a(String tableKey)
/*     */   {
/* 119 */     if (this.s.equals(tableKey)) {
/* 120 */       return this;
/*     */     }
/* 122 */     if (this.children != null) {
/* 123 */       for (BusTableRel rel : this.children) {
/* 124 */         BusTableRel r = rel.a(tableKey);
/* 125 */         if (r != null) {
/* 126 */           return r;
/*     */         }
/*     */       }
/*     */     }
/* 130 */     return null;
/*     */   }
/*     */   
/*     */   public List<BusTableRel> list()
/*     */   {
/* 135 */     List<BusTableRel> rels = new ArrayList();
/* 136 */     rels.add(this);
/* 137 */     if (this.children != null) {
/* 138 */       for (BusTableRel rel : this.children) {
/* 139 */         rels.addAll(rel.list());
/*     */       }
/*     */     }
/* 142 */     return rels;
/*     */   }
/*     */   
/*     */   public BusinessTable getTable()
/*     */   {
/* 147 */     return this.v;
/*     */   }
/*     */   
/*     */   public void setTable(BusinessTable table) {
/* 151 */     this.v = table;
/*     */   }
/*     */   
/*     */   public BusTableRel getParent()
/*     */   {
/* 156 */     return this.w;
/*     */   }
/*     */   
/*     */   public void setParent(BusTableRel parent) {
/* 160 */     this.w = parent;
/*     */   }
/*     */   
/*     */   public BusinessObject getBusObj()
/*     */   {
/* 165 */     return this.x;
/*     */   }
/*     */   
/*     */   public void setBusObj(BusinessObject busObj) {
/* 169 */     this.x = busObj;
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\model\BusTableRel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */