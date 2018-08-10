package com.dstz.bpm.engine.parser.node;

import com.dstz.bpm.api.engine.plugin.def.BpmDef;
import com.dstz.bpm.api.model.def.NodeProperties;
import com.dstz.bpm.api.model.nodedef.impl.BaseBpmNodeDef;
import com.dstz.bpm.engine.parser.node.AbsNodeParse;
import org.springframework.stereotype.Component;

@Component
public class NodePropertiesParse extends AbsNodeParse<NodeProperties> {
	public String getKey() {
		return "propertie";
	}

	public void a(BaseBpmNodeDef userNodeDef, Object object) {
		NodeProperties prop = (NodeProperties) object;
		userNodeDef.setNodeProperties(prop);
	}

	public void setDefParam(BpmDef bpmDef, Object object) {
		this.a((BaseBpmNodeDef) bpmDef, object);
	}
}