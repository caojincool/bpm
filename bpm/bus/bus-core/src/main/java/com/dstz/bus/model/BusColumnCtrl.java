package com.dstz.bus.model;

import com.dstz.base.core.model.BaseModel;
import com.dstz.bus.api.model.IBusColumnCtrl;
import org.hibernate.validator.constraints.NotEmpty;

public class BusColumnCtrl extends BaseModel implements IBusColumnCtrl {
	@NotEmpty
	private String p;
	@NotEmpty
	private String type;
	private String q;
	private String r;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getColumnId() {
		return this.p;
	}

	public void setColumnId(String columnId) {
		this.p = columnId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getConfig() {
		return this.q;
	}

	public void setConfig(String config) {
		this.q = config;
	}

	public String getValidRule() {
		return this.r;
	}

	public void setValidRule(String validRule) {
		this.r = validRule;
	}
}