/*     */ package com.dstz.bus.manager.impl;
/*     */ 
/*     */ import com.alibaba.fastjson.JSONObject;
/*     */ import com.dstz.base.api.exception.BusinessException;
/*     */ import com.dstz.base.api.query.QueryFilter;
/*     */ import com.dstz.base.api.query.QueryOP;
/*     */ import com.dstz.base.core.util.BeanUtils;
/*     */ import com.dstz.base.db.model.query.DefaultQueryFilter;
/*     */ import com.dstz.base.manager.impl.BaseManager;
/*     */ import com.dstz.bus.api.constant.BusTableRelType;
/*     */ import com.dstz.bus.dao.BusinessObjectDao;
/*     */ import com.dstz.bus.manager.BusinessObjectManager;
/*     */ import com.dstz.bus.manager.BusinessTableManager;
/*     */ import com.dstz.bus.model.BusTableRel;
/*     */ import com.dstz.bus.model.BusinessColumn;
/*     */ import com.dstz.bus.model.BusinessObject;
/*     */ import com.dstz.bus.model.BusinessTable;
/*     */ import com.dstz.bus.service.BusinessPermissionService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Service
/*     */ public class BusinessObjectManagerImpl
/*     */   extends BaseManager<String, BusinessObject>
/*     */   implements BusinessObjectManager
/*     */ {
/*     */   @Resource
/*     */   BusinessObjectDao g;
/*     */   @Autowired
/*     */   BusinessTableManager h;
/*     */   @Autowired
/*     */   BusinessPermissionService i;
/*     */   @Resource
/*     */   JdbcTemplate jdbcTemplate;
/*     */   
/*     */   public BusinessObject getByKey(String key)
/*     */   {
/*  49 */     QueryFilter filter = new DefaultQueryFilter();
/*  50 */     filter.addFilter("key_", key, QueryOP.EQUAL);
/*  51 */     return (BusinessObject)queryOne(filter);
/*     */   }
/*     */   
/*     */   public BusinessObject getFilledByKey(String key)
/*     */   {
/*  56 */     BusinessObject businessObject = getByKey(key);
/*  57 */     a(businessObject);
/*  58 */     return businessObject;
/*     */   }
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
/*     */   private void a(BusinessObject businessObject)
/*     */   {
/*  73 */     if (businessObject == null) {
/*  74 */       return;
/*     */     }
/*     */     
/*  77 */     for (BusTableRel rel : businessObject.getRelation().list()) {
/*  78 */       rel.setTable(this.h.getFilledByKey(rel.getTableKey()));
/*  79 */       rel.setBusObj(businessObject);
/*     */     }
/*     */     
/*  82 */     a(businessObject.getRelation());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void a(BusTableRel rel)
/*     */   {
/*  93 */     for (BusTableRel r : rel.getChildren()) {
/*  94 */       r.setParent(rel);
/*  95 */       a(r);
/*     */     }
/*     */   }
/*     */   
/*     */   public List<JSONObject> boTreeData(String key)
/*     */   {
/* 101 */     BusinessObject businessObject = getByKey(key);
/* 102 */     BusTableRel busTableRel = businessObject.getRelation();
/* 103 */     List<JSONObject> list = new ArrayList();
/* 104 */     a(busTableRel, "0", list);
/*     */     
/* 106 */     if (BeanUtils.isNotEmpty(list)) {
/* 107 */       ((JSONObject)list.get(0)).put("alias", key);
/*     */     }
/* 109 */     return list;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void a(BusTableRel busTableRel, String parentId, List<JSONObject> list)
/*     */   {
/* 122 */     BusinessTable businessTable = this.h.getFilledByKey(busTableRel.getTableKey());
/* 123 */     JSONObject root = new JSONObject();
/* 124 */     root.put("id", businessTable.getId());
/* 125 */     root.put("key", businessTable.getKey());
/* 126 */     root.put("name", businessTable.getName() + "(" + BusTableRelType.getByKey(busTableRel.getType()).getDesc() + ")");
/* 127 */     root.put("parentId", parentId);
/* 128 */     root.put("nodeType", "table");
/* 129 */     list.add(root);
/*     */     
/* 131 */     for (BusinessColumn businessColumn : businessTable.getColumns()) {
/* 132 */       JSONObject columnJson = new JSONObject();
/* 133 */       columnJson.put("id", businessColumn.getId());
/* 134 */       columnJson.put("key", businessColumn.getName());
/* 135 */       columnJson.put("name", businessColumn.getComment());
/* 136 */       columnJson.put("tableKey", businessTable.getKey());
/* 137 */       columnJson.put("parentId", businessTable.getId());
/* 138 */       columnJson.put("nodeType", "column");
/* 139 */       list.add(columnJson);
/*     */     }
/* 141 */     for (BusTableRel rel : busTableRel.getChildren()) {
/* 142 */       a(rel, businessTable.getId(), list);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void remove(String entityId)
/*     */   {
/* 153 */     BusinessObject businessObject = (BusinessObject)get(entityId);
/* 154 */     if (businessObject == null) { return;
/*     */     }
/* 156 */     List<String> names = this.jdbcTemplate.queryForList(" select name_ from form_def where bo_key_ = '" + businessObject.getKey() + "'", String.class);
/* 157 */     if (BeanUtils.isNotEmpty(names)) {
/* 158 */       throw new BusinessException("表单:" + names.toString() + "还在使用业务对象， 删除业务对象失败！");
/*     */     }
/*     */     
/* 161 */     super.remove(entityId);
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\manager\impl\BusinessObjectManagerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */