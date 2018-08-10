package com.dstz.bus.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dstz.base.api.constant.ColumnType;
import com.dstz.base.api.model.IBaseModel;
import com.dstz.base.core.util.StringUtil;
import com.dstz.base.core.util.time.DateFormatUtil;
import com.dstz.base.db.model.table.Column;
import com.dstz.bus.api.model.IBusColumnCtrl;
import com.dstz.bus.api.model.IBusinessColumn;
import com.dstz.bus.api.model.IBusinessTable;
import com.dstz.bus.model.BusColumnCtrl;
import com.dstz.bus.model.BusinessTable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import javax.validation.Valid;
import org.hibernate.validator.constraints.NotEmpty;

public class BusinessColumn extends Column implements IBaseModel, IBusinessColumn {
	@NotEmpty
	private String id;
	@NotEmpty
	private String key;
	@NotEmpty
	private String y;
	@Valid
	private BusColumnCtrl z;
	private BusinessTable v;

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

	public BusColumnCtrl getCtrl() {
		return this.z;
	}

	public void setCtrl(BusColumnCtrl ctrl) {
		this.z = ctrl;
	}

	public String getTableId() {
		return this.y;
	}

	public void setTableId(String tableId) {
		this.y = tableId;
	}

	public BusinessTable getTable() {
		return this.v;
	}

	public void setTable(BusinessTable table) {
		this.v = table;
	}

	public Object initValue() {
		return this.value(this.defaultValue);
	}

	public Object value(String str) {
		if (StringUtil.isEmpty((String) str)) {
			return null;
		}
		Object value = null;
		if (ColumnType.VARCHAR.equalsWithKey(this.type) || ColumnType.CLOB.equalsWithKey(this.type)) {
			value = str;
		} else if (ColumnType.NUMBER.equalsWithKey(this.type)) {
			BigDecimal bigDecimal = new BigDecimal(str);
			value = bigDecimal.setScale(this.getDecimal(), RoundingMode.HALF_UP);
		} else if (ColumnType.DATE.equalsWithKey(this.type)) {
			JSONObject config = JSON.parseObject((String) this.getCtrl().getConfig());
			value = DateFormatUtil.parse((String) str, (String) config.getString("format"));
		}
		return value;
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
}