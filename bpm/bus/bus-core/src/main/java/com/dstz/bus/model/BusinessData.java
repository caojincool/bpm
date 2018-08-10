package com.dstz.bus.model;

import com.alibaba.fastjson.JSONObject;
import com.dstz.bus.api.constant.BusTableRelType;
import com.dstz.bus.api.model.IBusTableRel;
import com.dstz.bus.api.model.IBusinessData;
import com.dstz.bus.api.model.IBusinessTable;
import com.dstz.bus.model.BusTableRel;
import com.dstz.bus.model.BusinessColumn;
import com.dstz.bus.model.BusinessObject;
import com.dstz.bus.model.BusinessTable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class BusinessData implements IBusinessData {
	private BusTableRel d;
	private Map<String, Object> c = new HashMap<String, Object>();
	private Map<String, List<BusinessData>> A = new HashMap<String, List<BusinessData>>();
	private BusinessData B;

	public BusTableRel getBusTableRel() {
		return this.d;
	}

	public void setBusTableRel(BusTableRel busTableRel) {
		this.d = busTableRel;
	}

	public Map<String, Object> getData() {
		return this.c;
	}

	public void setData(Map<String, Object> data) {
		this.c = data;
	}

	public Map<String, List<BusinessData>> getChildren() {
		return this.A;
	}

	public void setChildren(Map<String, List<BusinessData>> children) {
		this.A = children;
	}

	public BusinessData getParent() {
		return this.B;
	}

	public void setParent(BusinessData parent) {
		this.B = parent;
	}

	public void setPk(Object id) {
		this.c.put(this.d.getTable().getPkKey(), id);
	}

	public Object getPk() {
		return this.c.get(this.d.getTable().getPkKey());
	}

	public void put(String key, Object value) {
		this.c.put(key, value);
	}

	public Object get(String key) {
		return this.c.get(key);
	}

	public String getString(String key) {
		Object obj = this.c.get(key);
		if (obj == null) {
			return null;
		}
		return obj.toString();
	}

	public Map<String, Object> getDbData() {
		HashMap<String, Object> dbData = new HashMap<String, Object>();
		for (BusinessColumn column : this.d.getTable().getColumns()) {
			if (!column.isPrimary()
					&& !this.d.getBusObj().haveColumnDbEditRights(this.d.getTableKey(), column.getKey()))
				continue;
			Object val = this.c.get(column.getKey());
			dbData.put(column.getName(), val);
		}
		return dbData;
	}

	public void setDbData(Map<String, Object> dbData) {
		for (BusinessColumn column : this.d.getTable().getColumns()) {
			if (!this.d.getBusObj().haveColumnDbReadRights(this.d.getTableKey(), column.getKey()))
				continue;
			this.c.put(column.getKey(), dbData.get(column.getName()));
		}
	}

	public void a(BusinessData businessData) {
		String tableKey = businessData.getBusTableRel().getTable().getKey();
		List list = this.A.computeIfAbsent(tableKey, k -> new ArrayList());
		businessData.setParent(this);
		list.add(businessData);
	}

	public Map<String, List<IBusinessData>> getChilds() {
		HashMap<String, List<IBusinessData>> map = new HashMap<String, List<IBusinessData>>();
		for (Map.Entry<String, List<BusinessData>> entry : this.A.entrySet()) {
			ArrayList list = new ArrayList();
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