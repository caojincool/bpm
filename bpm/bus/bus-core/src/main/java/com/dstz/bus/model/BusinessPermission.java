package com.dstz.bus.model;

import com.alibaba.fastjson.JSONObject;
import com.dstz.base.core.model.BaseModel;
import com.dstz.base.core.util.JsonUtil;
import com.dstz.base.core.util.StringUtil;
import com.dstz.bus.api.constant.RightsType;
import com.dstz.bus.api.model.IBusinessPermission;
import com.dstz.bus.api.model.permission.IBusColumnPermission;
import com.dstz.bus.api.model.permission.IBusObjPermission;
import com.dstz.bus.api.model.permission.IBusTablePermission;
import com.dstz.bus.model.permission.BusObjPermission;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BusinessPermission extends BaseModel implements IBusinessPermission {
	private String G;
	private String H;
	private String I;
	private Map<String, BusObjPermission> J = new HashMap<String, BusObjPermission>();
	private JSONObject K;
	private JSONObject L = null;

	public String getObjType() {
		return this.G;
	}

	public void setObjType(String objType) {
		this.G = objType;
	}

	public String getObjVal() {
		return this.H;
	}

	public void setObjVal(String objVal) {
		this.H = objVal;
	}

	public String getBusObjMapJson() {
		return this.I;
	}

	public void setBusObjMapJson(String busObjMapJson) {
		this.I = busObjMapJson;
		if (StringUtil.isEmpty((String) busObjMapJson)) {
			this.J = null;
			return;
		}
		this.J = new HashMap<String, BusObjPermission>();
		Map map = (Map) JSONObject.parseObject((String) busObjMapJson, Map.class);
		for (Map.Entry entry : map.entrySet()) {
			this.J.put((String) entry.getKey(), (BusObjPermission) JSONObject
					.parseObject((String) entry.getValue().toString(), BusObjPermission.class));
		}
	}

	public Map<String, BusObjPermission> getBusObjMap() {
		return this.J;
	}

	public void setBusObjMap(Map<String, BusObjPermission> busObjMap) {
		this.J = busObjMap;
		this.I = JsonUtil.toJSONString(busObjMap);
	}

	public BusObjPermission c(String boKey) {
		return this.J.get(boKey);
	}

	public JSONObject getTablePermission(boolean isReadonly) {
		this.a(isReadonly);
		return this.K;
	}

	public JSONObject getPermission(boolean isReadonly) {
		this.a(isReadonly);
		return this.L;
	}

	private synchronized void a(Boolean isReadonly) {
		if (this.L != null) {
			return;
		}
		this.K = new JSONObject();
		this.L = new JSONObject();
		for (Map.Entry<String, BusObjPermission> entry : this.getBusObjMap().entrySet()) {
			IBusObjPermission busObjPermission = (IBusObjPermission) entry.getValue();
			this.L.put(busObjPermission.getKey(), (Object) new JSONObject());
			this.K.put(busObjPermission.getKey(), (Object) new JSONObject());
			for (Map.Entry etry : busObjPermission.getTableMap().entrySet()) {
				IBusTablePermission busTablePermission = (IBusTablePermission) etry.getValue();
				this.L.getJSONObject(busObjPermission.getKey()).put(busTablePermission.getKey(),
						(Object) new JSONObject());
				this.K.getJSONObject(busObjPermission.getKey()).put(busTablePermission.getKey(),
						(Object) this.a(busTablePermission.getResult(), isReadonly));
				for (Map.Entry ery : busTablePermission.getColumnMap().entrySet()) {
					IBusColumnPermission busColumnPermission = (IBusColumnPermission) ery.getValue();
					this.L.getJSONObject(busObjPermission.getKey()).getJSONObject(busTablePermission.getKey()).put(
							busColumnPermission.getKey(), (Object) this.a(busColumnPermission.getResult(), isReadonly));
				}
			}
		}
	}

	private String a(String result, Boolean isReadonly) {
		if (!isReadonly.booleanValue()) {
			return result;
		}
		if (RightsType.REQUIRED.getKey().equals(result) || RightsType.WRITE.getKey().equals(result)) {
			return RightsType.READ.getKey();
		}
		return result;
	}

	public IBusObjPermission getBusObj(String string) {
		return this.c(string);
	}
}