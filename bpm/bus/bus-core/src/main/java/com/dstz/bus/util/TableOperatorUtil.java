/*    */ package com.dstz.bus.util;
/*    */ 
/*    */ import com.dstz.base.db.api.table.DbType;
/*    */ import com.dstz.base.db.datasource.DataSourceUtil;
/*    */ import com.dstz.base.db.tableoper.MysqlTableOperator;
/*    */ import com.dstz.base.db.tableoper.TableOperator;
/*    */ import com.dstz.bus.model.BusinessTable;
/*    */ import org.springframework.jdbc.core.JdbcTemplate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TableOperatorUtil
/*    */ {
/*    */   public static TableOperator newOperator(BusinessTable table)
/*    */   {
/* 21 */     JdbcTemplate jdbcTemplate = DataSourceUtil.getJdbcTempByDsAlias(table.getDsKey());
/* 22 */     if (jdbcTemplate == null) {
/* 23 */       throw new RuntimeException("当前系统不存在的数据源:" + table.getDsKey());
/*    */     }
/*    */     
/* 26 */     String type = DbType.MYSQL.getKey();
/*    */     
/* 28 */     if (DbType.MYSQL.equalsWithKey(type)) {
/* 29 */       return new MysqlTableOperator(table, jdbcTemplate);
/*    */     }
/*    */     
/* 32 */     throw new RuntimeException("找不到类型[" + type + "]的数据库处理者(TableOperator)");
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\util\TableOperatorUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */