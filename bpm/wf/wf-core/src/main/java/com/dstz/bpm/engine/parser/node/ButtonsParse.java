package com.dstz.bpm.engine.parser.node;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dstz.base.core.util.BeanUtils;
import com.dstz.bpm.api.engine.plugin.def.BpmDef;
import com.dstz.bpm.api.model.nodedef.Button;
import com.dstz.bpm.api.model.nodedef.impl.BaseBpmNodeDef;
import com.dstz.bpm.engine.parser.node.AbsNodeParse;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ButtonsParse extends AbsNodeParse<Button> {
	public String getKey() {
		return "btnList";
	}

	public void a(BaseBpmNodeDef userNodeDef, Object object) {
		List btnList = (List) object;
		userNodeDef.setButtons(btnList);
	}

	public boolean isArray() {
		return true;
	}

	public void a(BaseBpmNodeDef userNodeDef, Object object, JSON thisJson) {
		JSONObject jsonConfig = (JSONObject) thisJson;
		if (BeanUtils.isEmpty((Object) object)) {
			jsonConfig.put("btnList", JSON.toJSON((Object) userNodeDef.getButtons()));
		}
	}

	public void JSONAmend(BpmDef bpmDef, Object object, JSON jSON) {
		this.a((BaseBpmNodeDef) bpmDef, object, jSON);
	}

	public void setDefParam(BpmDef bpmDef, Object object) {
		this.a((BaseBpmNodeDef) bpmDef, object);
	}
}