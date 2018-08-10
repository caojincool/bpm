/*     */ package com.dstz.bus.manager.impl;
/*     */ 
/*     */ import com.dstz.base.api.exception.BusinessException;
/*     */ import com.dstz.base.api.query.QueryFilter;
/*     */ import com.dstz.base.api.query.QueryOP;
/*     */ import com.dstz.base.core.util.BeanUtils;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.base.db.datasource.DbContextHolder;
/*     */ import com.dstz.base.db.id.UniqueIdUtil;
/*     */ import com.dstz.base.db.model.query.DefaultQueryFilter;
/*     */ import com.dstz.base.db.tableoper.TableOperator;
/*     */ import com.dstz.base.db.tableoper.TableOperatorFactory;
/*     */ import com.dstz.base.manager.impl.BaseManager;
/*     */ import com.dstz.bus.dao.BusinessTableDao;
/*     */ import com.dstz.bus.manager.BusColumnCtrlManager;
/*     */ import com.dstz.bus.manager.BusinessColumnManager;
/*     */ import com.dstz.bus.manager.BusinessTableManager;
/*     */ import com.dstz.bus.model.BusColumnCtrl;
/*     */ import com.dstz.bus.model.BusinessColumn;
/*     */ import com.dstz.bus.model.BusinessTable;
/*     */ import com.dstz.bus.util.BusinessTableCacheUtil;
/*     */ import com.dstz.sys.api2.service.ISysDataSourceService;
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
/*     */ @Service
/*     */ public class BusinessTableManagerImpl
/*     */   extends BaseManager<String, BusinessTable>
/*     */   implements BusinessTableManager
/*     */ {
/*     */   @Autowired
/*     */   BusinessTableDao l;
/*     */   @Autowired
/*     */   BusinessColumnManager m;
/*     */   @Autowired
/*     */   BusColumnCtrlManager n;
/*     */   @Autowired
/*     */   ISysDataSourceService o;
/*     */   @Resource
/*     */   JdbcTemplate jdbcTemplate;
/*     */   
/*     */   public void save(BusinessTable businessTable)
/*     */   {
/*  53 */     if (StringUtil.isEmpty(businessTable.getId())) {
/*  54 */       businessTable.setId(UniqueIdUtil.getSuid());
/*  55 */       create(businessTable);
/*     */     } else {
/*  57 */       update(businessTable);
/*  58 */       this.n.removeByTableId(businessTable.getId());
/*  59 */       this.m.removeByTableId(businessTable.getId());
/*     */     }
/*  61 */     for (BusinessColumn businessColumn : businessTable.getColumns()) {
/*  62 */       if (StringUtil.isEmpty(businessColumn.getId())) {
/*  63 */         businessColumn.setId(UniqueIdUtil.getSuid());
/*     */       }
/*  65 */       businessColumn.setTable(businessTable);
/*  66 */       businessColumn.setTableId(businessTable.getId());
/*  67 */       this.m.create(businessColumn);
/*  68 */       BusColumnCtrl ctrl = businessColumn.getCtrl();
/*  69 */       if (!businessColumn.isPrimary())
/*     */       {
/*     */ 
/*  72 */         if (StringUtil.isEmpty(ctrl.getId())) {
/*  73 */           ctrl.setId(UniqueIdUtil.getSuid());
/*     */         }
/*  75 */         ctrl.setColumnId(businessColumn.getId());
/*  76 */         this.n.create(businessColumn.getCtrl());
/*     */       }
/*     */     }
/*  79 */     newTableOperator(businessTable).syncColumn();
/*     */     
/*  81 */     BusinessTableCacheUtil.put(businessTable);
/*     */   }
/*     */   
/*     */   public BusinessTable getByKey(String key)
/*     */   {
/*  86 */     QueryFilter filter = new DefaultQueryFilter();
/*  87 */     filter.addFilter("key_", key, QueryOP.EQUAL);
/*  88 */     return (BusinessTable)queryOne(filter);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void a(BusinessTable businessTable)
/*     */   {
/* 100 */     if (businessTable == null) {
/* 101 */       return;
/*     */     }
/*     */     
/* 104 */     List<BusinessColumn> columns = this.m.getByTableId(businessTable.getId());
/* 105 */     for (BusinessColumn column : columns) {
/* 106 */       column.setCtrl(this.n.getByColumnId(column.getId()));
/* 107 */       column.setTable(businessTable);
/*     */     }
/* 109 */     businessTable.setColumns(columns);
/*     */     
/* 111 */     TableOperator tableOperator = newTableOperator(businessTable);
/* 112 */     businessTable.setCreatedTable(tableOperator.isTableCreated());
/*     */   }
/*     */   
/*     */   public TableOperator newTableOperator(BusinessTable businessTable)
/*     */   {
/* 117 */     JdbcTemplate dataSourceJdbcTemplate = this.o.getJdbcTemplateByKey(businessTable.getDsKey());
/* 118 */     return TableOperatorFactory.newOperator(DbContextHolder.getDataSourceDbType(businessTable.getDsKey()), businessTable, dataSourceJdbcTemplate);
/*     */   }
/*     */   
/*     */   public BusinessTable getFilledByKey(String key)
/*     */   {
/* 123 */     BusinessTable businessTable = BusinessTableCacheUtil.get(key);
/* 124 */     if (businessTable != null) {
/* 125 */       return businessTable;
/*     */     }
/* 127 */     businessTable = getByKey(key);
/* 128 */     a(businessTable);
/* 129 */     BusinessTableCacheUtil.put(businessTable);
/* 130 */     return businessTable;
/*     */   }
/*     */   
/*     */   public void remove(String entityId)
/*     */   {
/* 135 */     BusinessTable table = (BusinessTable)get(entityId);
/* 136 */     if (table == null) { return;
/*     */     }
/* 138 */     List<String> boNames = this.jdbcTemplate.queryForList("select name_ from bus_object where relation_json_ like  '%\"tableKey\":\"" + table.getKey() + "\"%'", String.class);
/* 139 */     if (BeanUtils.isNotEmpty(boNames)) {
/* 140 */       throw new BusinessException("业务对象:" + boNames.toString() + "还在使用实体， 删除实体失败！");
/*     */     }
/*     */     
/* 143 */     super.remove(entityId);
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\manager\impl\BusinessTableManagerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */