package com.dstz.bus.manager.impl;

import com.alibaba.fastjson.JSONObject;
import com.dstz.base.api.exception.BusinessException;
import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.api.query.QueryOP;
import com.dstz.base.core.util.BeanUtils;
import com.dstz.base.db.model.query.DefaultQueryFilter;
import com.dstz.base.manager.impl.BaseManager;
import com.dstz.bus.api.constant.BusTableRelType;
import com.dstz.bus.api.model.IBusTableRel;
import com.dstz.bus.dao.BusinessObjectDao;
import com.dstz.bus.manager.BusinessObjectManager;
import com.dstz.bus.manager.BusinessTableManager;
import com.dstz.bus.model.BusTableRel;
import com.dstz.bus.model.BusinessColumn;
import com.dstz.bus.model.BusinessObject;
import com.dstz.bus.model.BusinessTable;
import com.dstz.bus.service.BusinessPermissionService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * 
*    
* 项目名称：bus-core   
* 类名称：BusinessObjectManagerImpl   
* 类描述：   TODO:BusinessObjectManagerImpl内部方法需要去猜测一下
* 创建人：Xianchang.min   
* 创建时间：2018年8月11日 下午5:11:05   
* 修改人：Xianchang.min   
* 修改时间：2018年8月11日 下午5:11:05   
* 修改备注：   
* @version  1.0  
*
 */
@Service
public class BusinessObjectManagerImpl extends BaseManager<String, BusinessObject> implements BusinessObjectManager {

	@Resource
	private BusinessObjectDao businessObjectDao;
	@Resource
	private BusinessTableManager businessTableManager;
	@Resource
	private BusinessPermissionService businessPermissionService;
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public BusinessObject getByKey(String key) {
		DefaultQueryFilter filter = new DefaultQueryFilter();
		filter.addFilter("key_", key, QueryOP.EQUAL);
		return (BusinessObject) this.queryOne(filter);
	}

	@Override
	public BusinessObject getFilledByKey(String key) {
		BusinessObject businessObject = this.getByKey(key);
		this.a(businessObject);
		return businessObject;
	}

	/**
	 * TODO: 函数的意思需要去猜想出来
	 * 
	 */
	private void a(BusinessObject businessObject) {
		if (businessObject == null) {
			return;
		}
		for (IBusTableRel rel : businessObject.getRelation().list()) {
			BusTableRel busTableRel = (BusTableRel) rel;
			busTableRel.setTable(this.businessTableManager.getFilledByKey(rel.getTableKey()));
			busTableRel.setBusObj(businessObject);
		}
		this.a(businessObject.getRelation());
	}

	private void a(IBusTableRel rel) {
		for (IBusTableRel r : rel.getChildren()) {
			BusTableRel busTableRel = (BusTableRel) r;
			busTableRel.setParent(rel);
			this.a(r);
		}
	}

	/*
	 * (非 Javadoc)
	 * 
	 * <p>Title: boTreeData</p>
	 * 
	 * <p>Description: TODO:业务对象的树形展示?</p>
	 * 
	 * @param key
	 * 
	 * @return
	 * 
	 * @see com.dstz.bus.manager.BusinessObjectManager#boTreeData(java.lang.String)
	 */
	@Override
	public List<JSONObject> boTreeData(String key) {
		BusinessObject businessObject = this.getByKey(key);
		BusTableRel busTableRel = businessObject.getRelation();
		ArrayList<JSONObject> list = new ArrayList<JSONObject>();
		this.a(busTableRel, "0", list);
		if (BeanUtils.isNotEmpty(list)) {
			list.get(0).put("alias", key);
		}
		return list;
	}

	private void a(IBusTableRel busTableRel, String parentId, List<JSONObject> list) {

		BusinessTable businessTable = this.businessTableManager.getFilledByKey(busTableRel.getTableKey());
		JSONObject root = new JSONObject();
		root.put("id", businessTable.getId());
		root.put("key", businessTable.getKey());
		root.put("name",
				businessTable.getName() + "(" + BusTableRelType.getByKey(busTableRel.getType()).getDesc() + ")");
		root.put("parentId", parentId);
		root.put("nodeType", "table");
		list.add(root);
		for (BusinessColumn businessColumn : businessTable.getColumns()) {
			JSONObject columnJson = new JSONObject();
			columnJson.put("id", businessColumn.getId());
			columnJson.put("key", businessColumn.getName());
			columnJson.put("name", businessColumn.getComment());
			columnJson.put("tableKey", businessTable.getKey());
			columnJson.put("parentId", businessTable.getId());
			columnJson.put("nodeType", "column");
			list.add(columnJson);
		}
		for (IBusTableRel rel : busTableRel.getChildren()) {
			this.a(rel, businessTable.getId(), list);
		}
	}

	public void remove(String entityId) {
		BusinessObject businessObject = (BusinessObject) this.get(entityId);
		if (businessObject == null) {
			return;
		}
		List<String> names = this.jdbcTemplate.queryForList(
				" select name_ from form_def where bo_key_ = '" + businessObject.getKey() + "'", String.class);
		if (BeanUtils.isNotEmpty(names)) {
			throw new BusinessException("表单:" + names.toString() + "还在使用业务对象， 删除业务对象失败！");
		}
		super.remove(entityId);
	}
}