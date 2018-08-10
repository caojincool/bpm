package com.dstz.bus.model;

import com.alibaba.fastjson.JSONObject;
import com.dstz.bus.api.constant.BusTableRelType;
import com.dstz.bus.api.model.IBusTableRel;
import com.dstz.bus.api.model.IBusinessData;
import com.dstz.bus.model.BusTableRel;
import com.dstz.bus.model.BusinessColumn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusinessData implements IBusinessData {
	/** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	private static final long serialVersionUID = -6272407461993770532L;
	
	
	private BusTableRel busTableRel;
	private Map<String, Object> data = new HashMap<String, Object>();
	private Map<String, List<BusinessData>> children = new HashMap<String, List<BusinessData>>();
	private BusinessData parent;

	public BusTableRel getBusTableRel() {
		return this.busTableRel;
	}

	public void setBusTableRel(BusTableRel busTableRel) {
		this.busTableRel = busTableRel;
	}

	public Map<String, Object> getData() {
		return this.data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public Map<String, List<BusinessData>> getChildren() {
		return this.children;
	}

	public void setChildren(Map<String, List<BusinessData>> children) {
		this.children = children;
	}

	public BusinessData getParent() {
		return this.parent;
	}

	public void setParent(BusinessData parent) {
		this.parent = parent;
	}

	public void setPk(Object id) {
		this.data.put(this.busTableRel.getTable().getPkKey(), id);
	}

	public Object getPk() {
		return this.data.get(this.busTableRel.getTable().getPkKey());
	}

	public void put(String key, Object value) {
		this.data.put(key, value);
	}

	public Object get(String key) {
		return this.data.get(key);
	}

	public String getString(String key) {
		Object obj = this.data.get(key);
		if (obj == null) {
			return null;
		}
		return obj.toString();
	}

	public Map<String, Object> getDbData() {
		HashMap<String, Object> dbData = new HashMap<String, Object>();
		for (BusinessColumn column : this.busTableRel.getTable().getColumns()) {
			if (!column.isPrimary()
					&& !this.busTableRel.getBusObj().haveColumnDbEditRights(this.busTableRel.getTableKey(), column.getKey()))
				continue;
			Object val = this.data.get(column.getKey());
			dbData.put(column.getName(), val);
		}
		return dbData;
	}

	public void setDbData(Map<String, Object> dbData) {
		for (BusinessColumn column : this.busTableRel.getTable().getColumns()) {
			if (!this.busTableRel.getBusObj().haveColumnDbReadRights(this.busTableRel.getTableKey(), column.getKey()))
				continue;
			this.data.put(column.getKey(), dbData.get(column.getName()));
		}
	}

	public void a(BusinessData businessData) {
		String tableKey = businessData.getBusTableRel().getTable().getKey();
		List list = this.children.computeIfAbsent(tableKey, k -> new ArrayList());
		businessData.setParent(this);
		list.add(businessData);
	}

	public Map<String, List<IBusinessData>> getChilds() {
		HashMap<String, List<IBusinessData>> map = new HashMap<String, List<IBusinessData>>();
		for (Map.Entry<String, List<BusinessData>> entry : this.children.entrySet()) {
			ArrayList<IBusinessData> list = new ArrayList<IBusinessData>();
			list.addAll(entry.getValue());
			map.put(entry.getKey(), list);
		}
		return map;
	}

	public JSONObject fullBusDataInitData(JSONObject initData) {
		if (initData == null) {
			initData = new JSONObject();
		}
		JSONObject initTables = new JSONObject();
		for (IBusTableRel rel : this.getBusTableRel().getChildren()) {
			initTables.put(rel.getTableKey(), (Object) this.a(rel));
		}
		initData.put(this.getBusTableRel().getBusObj().getKey(), (Object) initTables);
		return initData;
	}

	private JSONObject a(IBusTableRel busTableRel) {
		JSONObject table = new JSONObject();
		table.putAll(busTableRel.getTable().initData());
		for (IBusTableRel rel : busTableRel.getChildren()) {
			if (!BusTableRelType.ONE_TO_ONE.equalsWithKey(rel.getType()))
				continue;
			table.put(rel.getTableKey(), (Object) this.a(rel));
		}
		return table;
	}
}