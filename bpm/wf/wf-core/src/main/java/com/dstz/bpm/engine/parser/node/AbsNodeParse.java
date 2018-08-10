package com.dstz.bpm.engine.parser.node;

import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
import com.dstz.bpm.api.model.nodedef.impl.BaseBpmNodeDef;
import com.dstz.bpm.engine.parser.BaseBpmDefParser;

public abstract class AbsNodeParse<T> extends BaseBpmDefParser<T, BaseBpmNodeDef> {
	public boolean a(BpmNodeDef nodeDef) {
		return true;
	}
}