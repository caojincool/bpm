package com.dstz.bus.manager.impl;

import com.dstz.base.api.exception.BusinessException;
import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.api.query.QueryOP;
import com.dstz.base.core.util.BeanUtils;
import com.dstz.base.core.util.StringUtil;
import com.dstz.base.db.datasource.DbContextHolder;
import com.dstz.base.db.id.UniqueIdUtil;
import com.dstz.base.db.model.query.DefaultQueryFilter;
import com.dstz.base.db.model.table.Table;
import com.dstz.base.db.tableoper.TableOperator;
import com.dstz.base.db.tableoper.TableOperatorFactory;
import com.dstz.base.manager.impl.BaseManager;
import com.dstz.bus.dao.BusinessTableDao;
import com.dstz.bus.manager.BusColumnCtrlManager;
import com.dstz.bus.manager.BusinessColumnManager;
import com.dstz.bus.manager.BusinessTableManager;
import com.dstz.bus.model.BusColumnCtrl;
import com.dstz.bus.model.BusinessColumn;
import com.dstz.bus.model.BusinessTable;
import com.dstz.bus.util.BusinessTableCacheUtil;
import com.dstz.sys.api2.service.ISysDataSourceService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class BusinessTableManagerImpl extends BaseManager<String, BusinessTable> implements BusinessTableManager {
	@Autowired
	BusinessTableDao l;
	@Autowired
	BusinessColumnManager m;
	@Autowired
	BusColumnCtrlManager n;
	@Autowired
	ISysDataSourceService o;
	@Resource
	JdbcTemplate jdbcTemplate;

	public void save(BusinessTable businessTable) {
		if (StringUtil.isEmpty((String) businessTable.getId())) {
			businessTable.setId(UniqueIdUtil.getSuid());
			this.create((Serializable) businessTable);
		} else {
			this.update((Serializable) businessTable);
			this.n.removeByTableId(businessTable.getId());
			this.m.removeByTableId(businessTable.getId());
		}
		for (BusinessColumn businessColumn : businessTable.getColumns()) {
			if (StringUtil.isEmpty((String) businessColumn.getId())) {
				businessColumn.setId(UniqueIdUtil.getSuid());
			}
			businessColumn.setTable(businessTable);
			businessColumn.setTableId(businessTable.getId());
			this.m.create((Object) businessColumn);
			BusColumnCtrl ctrl = businessColumn.getCtrl();
			if (businessColumn.isPrimary())
				continue;
			if (StringUtil.isEmpty((String) ctrl.getId())) {
				ctrl.setId(UniqueIdUtil.getSuid());
			}
			ctrl.setColumnId(businessColumn.getId());
			this.n.create((Object) businessColumn.getCtrl());
		}
		this.newTableOperator(businessTable).syncColumn();
		BusinessTableCacheUtil.put((BusinessTable) businessTable);
	}

	public BusinessTable getByKey(String key) {
		DefaultQueryFilter filter = new DefaultQueryFilter();
		filter.addFilter("key_", (Object) key, QueryOP.EQUAL);
		return (BusinessTable) this.queryOne((QueryFilter) filter);
	}

	private void a(BusinessTable businessTable) {
		if (businessTable == null) {
			return;
		}
		List columns = this.m.getByTableId(businessTable.getId());
		for (BusinessColumn column : columns) {
			column.setCtrl(this.n.getByColumnId(column.getId()));
			column.setTable(businessTable);
		}
		businessTable.setColumns(columns);
		TableOperator tableOperator = this.newTableOperator(businessTable);
		businessTable.setCreatedTable(tableOperator.isTableCreated());
	}

	public TableOperator newTableOperator(BusinessTable businessTable) {
		JdbcTemplate dataSourceJdbcTemplate = this.o.getJdbcTemplateByKey(businessTable.getDsKey());
		return TableOperatorFactory.newOperator(
				(String) DbContextHolder.getDataSourceDbType((String) businessTable.getDsKey()), (Table) businessTable,
				(JdbcTemplate) dataSourceJdbcTemplate);
	}

	public BusinessTable getFilledByKey(String key) {
		BusinessTable businessTable = BusinessTableCacheUtil.get(key);
		if (businessTable != null) {
			return businessTable;
		}
		businessTable = this.getByKey(key);
		this.a(businessTable);
		BusinessTableCacheUtil.put((BusinessTable) businessTable);
		return businessTable;
	}

	public void remove(String entityId) {
		BusinessTable table = (BusinessTable) this.get((Serializable) ((Object) entityId));
		if (table == null) {
			return;
		}
		List boNames = this.jdbcTemplate.queryForList(
				"select name_ from bus_object where relation_json_ like  '%\"tableKey\":\"" + table.getKey() + "\"%'",
				String.class);
		if (BeanUtils.isNotEmpty((Object) boNames)) {
			throw new BusinessException("业务对象:" + boNames.toString() + "还在使用实体， 删除实体失败！");
		}
		super.remove((Serializable) ((Object) entityId));
	}
}