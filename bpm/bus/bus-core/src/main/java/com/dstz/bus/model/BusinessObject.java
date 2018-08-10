package com.dstz.bus.model;

import com.dstz.base.core.model.BaseModel;
import com.dstz.base.core.util.JsonUtil;
import com.dstz.bus.api.constant.RightsType;
import com.dstz.bus.api.model.IBusTableRel;
import com.dstz.bus.api.model.IBusinessObject;
import com.dstz.bus.api.model.permission.IBusObjPermission;
import com.dstz.bus.model.BusTableRel;
import com.dstz.bus.model.BusinessColumn;
import com.dstz.bus.model.BusinessTable;
import com.dstz.bus.model.permission.BusColumnPermission;
import com.dstz.bus.model.permission.BusObjPermission;
import com.dstz.bus.model.permission.BusTablePermission;
import java.util.List;
import java.util.Map;
import org.hibernate.validator.constraints.NotEmpty;

public class BusinessObject extends BaseModel implements IBusinessObject {
	@NotEmpty
	private String key;
	@NotEmpty
	private String name;
	private String desc;
	@NotEmpty
	private String C;
	private String groupId;
	private String groupName;
	@NotEmpty
	private String D;
	private BusTableRel E;
	private BusObjPermission F;

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getRelationJson() {
		return this.C;
	}

	public void setRelationJson(String relationJson) {
		this.C = relationJson;
		this.E = (BusTableRel) JsonUtil.parseObject((String) relationJson, BusTableRel.class);
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

	public String getPersistenceType() {
		return this.D;
	}

	public void setPersistenceType(String persistenceType) {
		this.D = persistenceType;
	}

	public BusTableRel getRelation() {
		return this.E;
	}

	public void setRelation(BusTableRel relation) {
		this.E = relation;
		this.C = JsonUtil.toJSONString((Object) relation);
	}

	public BusObjPermission getPermission() {
		return this.F;
	}

	public void setPermission(IBusObjPermission permission) {
		this.F = (BusObjPermission) permission;
	}

	public boolean haveTableDbEditRights(String tableKey) {
		return this.a(true, tableKey);
	}

	public boolean haveTableDbReadRights(String tableKey) {
		return this.a(false, tableKey);
	}

	public boolean haveColumnDbEditRights(String tableKey, String columnKey) {
		return this.a(true, tableKey, columnKey);
	}

	public boolean haveColumnDbReadRights(String tableKey, String columnKey) {
		return this.a(false, tableKey, columnKey);
	}

	private boolean a(boolean isEdit, String tableKey, String columnKey) {
		BusColumnPermission columnPermission;
		BusTablePermission tablePermission;
		RightsType rightsType = null;
		if (this.F != null && (tablePermission = (BusTablePermission) this.F.getTableMap().get(tableKey)) != null
				&& (columnPermission = (BusColumnPermission) tablePermission.getColumnMap().get(columnKey)) != null) {
			rightsType = RightsType.getByKey((String) columnPermission.getResult());
		}
		if (rightsType == null) {
			rightsType = RightsType.getDefalut();
		}
		if (isEdit) {
			return rightsType.isDbEditable();
		}
		return rightsType.isDbReadable();
	}

	private boolean a(boolean isEdit, String tableKey) {
		BusTablePermission tablePermission;
		if (this.F != null && (tablePermission = (BusTablePermission) this.F.getTableMap().get(tableKey)) != null) {
			for (BusinessColumn column : this.E.a(tableKey).getTable().getColumnsWithoutPk()) {
				if (isEdit && this.haveColumnDbEditRights(tableKey, column.getKey())) {
					return true;
				}
				if (isEdit || !this.haveColumnDbReadRights(tableKey, column.getKey()))
					continue;
				return true;
			}
			return false;
		}
		if (isEdit) {
			return RightsType.getDefalut().isDbEditable();
		}
		return RightsType.getDefalut().isDbReadable();
	}
}