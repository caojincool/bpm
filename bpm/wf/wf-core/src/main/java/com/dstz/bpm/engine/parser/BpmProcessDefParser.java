package com.dstz.bpm.engine.parser;

import com.alibaba.fastjson.JSONObject;
import com.dstz.base.core.util.AppUtil;
import com.dstz.base.core.util.BeanUtils;
import com.dstz.bpm.api.engine.plugin.def.BpmDef;
import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
import com.dstz.bpm.engine.parser.flow.AbsFlowParse;
import com.dstz.bpm.engine.parser.node.AbsNodeParse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class BpmProcessDefParser {
	private static List<AbsFlowParse> bi;
	private static List<AbsNodeParse> bj;

	public static void a(DefaultBpmProcessDef bpmProcessDef, JSONObject bpmDefSetting) {
		JSONObject flowConf = bpmDefSetting.getJSONObject("flow");
		for (AbsFlowParse flowParser : BpmProcessDefParser.getFlowParsers()) {
			flowParser.parse((BpmDef) bpmProcessDef, flowConf);
		}
		JSONObject nodeMap = bpmDefSetting.getJSONObject("nodeMap");
		for (BpmNodeDef nodeDef : bpmProcessDef.getBpmnNodeDefs()) {
			JSONObject nodeConfig = nodeMap.getJSONObject(nodeDef.getNodeId());
			for (AbsNodeParse nodeParser : BpmProcessDefParser.getNodeParsers()) {
				if (!nodeParser.a(nodeDef))
					continue;
				nodeParser.parse((BpmDef) nodeDef, nodeConfig);
			}
		}
	}

	private static List<AbsFlowParse> getFlowParsers() {
		if (BeanUtils.isNotEmpty(bi)) {
			return bi;
		}
		Map map = AppUtil.getImplInstance(AbsFlowParse.class);
		bi = new ArrayList(map.values());
		return bi;
	}

	private static List<AbsNodeParse> getNodeParsers() {
		if (BeanUtils.isNotEmpty(bj)) {
			return bj;
		}
		Map map = AppUtil.getImplInstance(AbsNodeParse.class);
		bj = new ArrayList(map.values());
		return bj;
	}
}