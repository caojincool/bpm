package com.dstz.bpm.engine.parser.flow;

import com.dstz.bpm.api.engine.plugin.def.BpmDef;
import com.dstz.bpm.api.model.def.NodeInit;
import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
import com.dstz.bpm.engine.parser.flow.AbsFlowParse;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class FlowFormInitParse extends AbsFlowParse<NodeInit> {
	public String getKey() {
		return "nodeInitList";
	}

	public void a(DefaultBpmProcessDef bpmProcessDef, Object object) {
		List list = (List) object;
		bpmProcessDef.setNodeInitList(list);
	}

	public boolean isArray() {
		return true;
	}

	public void setDefParam(BpmDef bpmDef, Object object) {
		this.a((DefaultBpmProcessDef) bpmDef, object);
	}
}