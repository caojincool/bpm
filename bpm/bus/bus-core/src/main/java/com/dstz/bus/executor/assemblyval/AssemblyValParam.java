package com.dstz.bus.executor.assemblyval;

import com.alibaba.fastjson.JSONObject;
import com.dstz.bus.model.BusinessData;

public class AssemblyValParam {
	private JSONObject a;
	private BusinessData b;

	public AssemblyValParam(JSONObject data, BusinessData businessData) {
		this.a = data;
		this.b = businessData;
	}

	public JSONObject getData() {
		return this.a;
	}

	public BusinessData getBusinessData() {
		return this.b;
	}
}