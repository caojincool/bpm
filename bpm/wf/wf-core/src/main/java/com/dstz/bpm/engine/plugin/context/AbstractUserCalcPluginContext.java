package com.dstz.bpm.engine.plugin.context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dstz.base.core.util.StringUtil;
import com.dstz.bpm.api.constant.ExtractType;
import com.dstz.bpm.api.engine.constant.LogicType;
import com.dstz.bpm.api.engine.plugin.context.PluginParse;
import com.dstz.bpm.api.engine.plugin.context.UserCalcPluginContext;
import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;
import com.dstz.bpm.api.engine.plugin.def.BpmUserCalcPluginDef;

public abstract class AbstractUserCalcPluginContext<T extends BpmUserCalcPluginDef>
		implements
			PluginParse<T>,
			UserCalcPluginContext {
	private static final long serialVersionUID = -4447195857368619545L;
	private T bI;

	public T getBpmPluginDef() {
		return this.bI;
	}

	public void setBpmPluginDef(T bpmPluginDef) {
		this.bI = bpmPluginDef;
	}

	protected abstract T parseJson(JSONObject var1);

	public T parse(JSON pluginDefJson) {
		JSONObject jsonObject = (JSONObject) pluginDefJson;
		T bpmPluginDef = this.parseJson(jsonObject);
		String extract = jsonObject.getString("extract");
		String logicCal = jsonObject.getString("logicCal");
		bpmPluginDef.setExtract(ExtractType.fromKey((String) extract));
		bpmPluginDef.setLogicCal(LogicType.fromKey((String) logicCal));
		this.setBpmPluginDef(bpmPluginDef);
		return bpmPluginDef;
	}

	public T parse(String jsonStr) {
		JSON json = (JSON) JSON.parse((String) jsonStr);
		return this.parse(json);
	}

	public JSON getJson() {
		return (JSON) JSON.toJSON(this.bI);
	}

	public String getType() {
		return StringUtil.lowerFirst((String) this.getClass().getSimpleName().replaceAll("PluginContext", ""));
	}
}