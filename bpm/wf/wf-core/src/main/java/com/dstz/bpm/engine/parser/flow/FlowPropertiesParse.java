package com.dstz.bpm.engine.parser.flow;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dstz.bpm.api.engine.plugin.def.BpmDef;
import com.dstz.bpm.api.model.def.BpmDefProperties;
import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
import com.dstz.bpm.engine.parser.flow.AbsFlowParse;
import org.springframework.stereotype.Component;

@Component
public class FlowPropertiesParse extends AbsFlowParse<BpmDefProperties> {
	public void b(DefaultBpmProcessDef def, JSONObject flowConf) {
		JSONObject properties = (JSONObject) JSONObject.toJSON((Object) def.getExtProperties());
		if (flowConf.containsKey((Object) this.getKey())) {
			properties = flowConf.getJSONObject(this.getKey());
		}
		BpmDefProperties bpmDefproperties = (BpmDefProperties) JSONObject.toJavaObject((JSON) properties,
				BpmDefProperties.class);
		def.setExtProperties(bpmDefproperties);
		flowConf.put(this.getKey(), (Object) properties);
	}

	public String getKey() {
		return "properties";
	}

	public void a(DefaultBpmProcessDef bpmProcessDef, Object object) {
		BpmDefProperties properties = (BpmDefProperties) object;
		bpmProcessDef.setExtProperties(properties);
	}

	public void parse(BpmDef bpmDef, JSONObject jSONObject) {
		this.b((DefaultBpmProcessDef) bpmDef, jSONObject);
	}

	public void setDefParam(BpmDef bpmDef, Object object) {
		this.a((DefaultBpmProcessDef) bpmDef, object);
	}
}