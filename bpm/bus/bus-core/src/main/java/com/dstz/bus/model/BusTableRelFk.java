package com.dstz.bus.model;

import com.dstz.bus.api.model.IBusTableRelFk;
import java.io.Serializable;

public class BusTableRelFk implements IBusTableRelFk, Serializable {
	private String from;
	private String type;
	private String value;

	public String getFrom() {
		return this.from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}