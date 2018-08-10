package com.dstz.bus.manager.impl;

import com.alibaba.fastjson.JSONObject;
import com.dstz.base.api.exception.BusinessException;
import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.api.query.QueryOP;
import com.dstz.base.core.util.BeanUtils;
import com.dstz.base.db.model.query.DefaultQueryFilter;
import com.dstz.base.manager.impl.BaseManager;
import com.dstz.bus.api.constant.BusTableRelType;
import com.dstz.bus.dao.BusinessObjectDao;
import com.dstz.bus.manager.BusinessObjectManager;
import com.dstz.bus.manager.BusinessTableManager;
import com.dstz.bus.model.BusTableRel;
import com.dstz.bus.model.BusinessColumn;
import com.dstz.bus.model.BusinessObject;
import com.dstz.bus.model.BusinessTable;
import com.dstz.bus.service.BusinessPermissionService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class BusinessObjectManagerImpl extends BaseManager<String, BusinessObject> implements BusinessObjectManager {
	@Resource
	BusinessObjectDao g;
	@Autowired
	BusinessTableManager h;
	@Autowired
	BusinessPermissionService i;
	@Resource
	JdbcTemplate jdbcTemplate;

	public BusinessObject getByKey(String key) {
		DefaultQueryFilter filter = new DefaultQueryFilter();
		filter.addFilter("key_", (Object) key, QueryOP.EQUAL);
		return (BusinessObject) this.queryOne((QueryFilter) filter);
	}

	public BusinessObject getFilledByKey(String key) {
		BusinessObject businessObject = this.getByKey(key);
		this.a(businessObject);
		return businessObject;
	}

	private void a(BusinessObject businessObject) {
		if (businessObject == null) {
			return;
		}
		for (BusTableRel rel : businessObject.getRelation().list()) {
			rel.setTable(this.h.getFilledByKey(rel.getTableKey()));
			rel.setBusObj(businessObject);
		}
		this.a(businessObject.getRelation());
	}

	private void a(BusTableRel rel) {
		for (BusTableRel r : rel.getChildren()) {
			r.setParent(rel);
			this.a(r);
		}
	}

	public List<JSONObject> boTreeData(String key) {
		BusinessObject businessObject = this.getByKey(key);
		BusTableRel busTableRel = businessObject.getRelation();
		ArrayList<JSONObject> list = new ArrayList<JSONObject>();
		this.a(busTableRel, "0", list);
		if (BeanUtils.isNotEmpty(list)) {
			list.get(0).put("alias", (Object) key);
		}
		return list;
	}

	private void a(BusTableRel busTableRel, String parentId, List<JSONObject> list) {
		BusinessTable businessTable = this.h.getFilledByKey(busTableRel.getTableKey());
		JSONObject root = new JSONObject();
		root.put("id", (Object) businessTable.getId());
		root.put("key", (Object) businessTable.getKey());
		root.put("name", (Object) (businessTable.getName() + "("
				+ BusTableRelType.getByKey((String) busTableRel.getType()).getDesc() + ")"));
		root.put("parentId", (Object) parentId);
		root.put("nodeType", (Object) "table");
		list.add(root);
		for (BusinessColumn businessColumn : businessTable.getColumns()) {
			JSONObject columnJson = new JSONObject();
			columnJson.put("id", (Object) businessColumn.getId());
			columnJson.put("key", (Object) businessColumn.getName());
			columnJson.put("name", (Object) businessColumn.getComment());
			columnJson.put("tableKey", (Object) businessTable.getKey());
			columnJson.put("parentId", (Object) businessTable.getId());
			columnJson.put("nodeType", (Object) "column");
			list.add(columnJson);
		}
		for (BusTableRel rel : busTableRel.getChildren()) {
			this.a(rel, businessTable.getId(), list);
		}
	}

	public void remove(String entityId) {
		BusinessObject businessObject = (BusinessObject) this.get((Serializable) ((Object) entityId));
		if (businessObject == null) {
			return;
		}
		List names = this.jdbcTemplate.queryForList(
				" select name_ from form_def where bo_key_ = '" + businessObject.getKey() + "'", String.class);
		if (BeanUtils.isNotEmpty((Object) names)) {
			throw new BusinessException("表单:" + names.toString() + "还在使用业务对象， 删除业务对象失败！");
		}
		super.remove((Serializable) ((Object) entityId));
	}
}