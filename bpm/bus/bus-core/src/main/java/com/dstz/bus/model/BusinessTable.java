package com.dstz.bus.model;

import com.dstz.base.api.model.IBaseModel;
import com.dstz.base.db.model.table.Column;
import com.dstz.base.db.model.table.Table;
import com.dstz.bus.api.constant.BusColumnCtrlType;
import com.dstz.bus.api.model.IBusinessColumn;
import com.dstz.bus.api.model.IBusinessTable;
import com.dstz.bus.model.BusColumnCtrl;
import com.dstz.bus.model.BusinessColumn;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.validator.constraints.NotEmpty;

public class BusinessTable extends Table<BusinessColumn> implements IBaseModel, IBusinessTable {
	@NotEmpty
	private String id;
	@NotEmpty
	private String key;
	@NotEmpty
	private String M;
	@NotEmpty
	private String dsName;
	private String groupId;
	private String groupName;
	private boolean N;
	private boolean O;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDsKey() {
		return this.M;
	}

	public void setDsKey(String dsKey) {
		this.M = dsKey;
	}

	public String getDsName() {
		return this.dsName;
	}

	public void setDsName(String dsName) {
		this.dsName = dsName;
	}

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public boolean isExternal() {
		return this.N;
	}

	public void setExternal(boolean external) {
		this.N = external;
	}

	public String getPkName() {
		if (this.getPkColumn() == null) {
			return "";
		}
		return ((BusinessColumn) this.getPkColumn()).getName();
	}

	public String getPkKey() {
		if (this.getPkColumn() == null) {
			return "";
		}
		return ((BusinessColumn) this.getPkColumn()).getKey();
	}

	public void setColumns(List<BusinessColumn> columns) {
		super.setColumns(columns);
	}

	public List<BusinessColumn> getColumns() {
		return super.getColumns();
	}

	public boolean a() {
		return this.O;
	}

	public void setCreatedTable(boolean createdTable) {
		this.O = createdTable;
	}

	public List<BusinessColumn> getColumnsWithoutPk() {
		if (this.columns == null) {
			return null;
		}
		ArrayList<BusinessColumn> columnList = new ArrayList<BusinessColumn>();
		for (BusinessColumn column : this.columns) {
			if (column.isPrimary())
				continue;
			columnList.add(column);
		}
		return columnList;
	}

	public List<BusinessColumn> getColumnsWithOutHidden() {
		if (this.columns == null) {
			return null;
		}
		ArrayList<BusinessColumn> columnList = new ArrayList<BusinessColumn>();
		for (BusinessColumn column : this.columns) {
			if (column.isPrimary() || column.getCtrl() == null
					|| BusColumnCtrlType.HIDDEN.getKey().equals(column.getCtrl().getType()))
				continue;
			columnList.add(column);
		}
		return columnList;
	}

	public Map<String, Object> initDbData() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (IBusinessColumn column : this.getColumnsWithoutPk()) {
			map.put(column.getName(), column.initValue());
		}
		return map;
	}

	public Map<String, Object> initData() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (IBusinessColumn column : this.getColumnsWithoutPk()) {
			map.put(column.getKey(), column.initValue());
		}
		return map;
	}

	public BusinessColumn d(String key) {
		for (BusinessColumn column : this.columns) {
			if (!key.equals(column.getKey()))
				continue;
			return column;
		}
		return null;
	}

	public Date getCreateTime() {
		return null;
	}

	public void setCreateTime(Date createTime) {
	}

	public String getCreateBy() {
		return null;
	}

	public void setCreateBy(String createBy) {
	}

	public Date getUpdateTime() {
		return null;
	}

	public void setUpdateTime(Date updateTime) {
	}

	public String getUpdateBy() {
		return null;
	}

	public void setUpdateBy(String updateBy) {
	}

	public IBusinessColumn getColumnByKey(String string) {
		return this.d(string);
	}
}