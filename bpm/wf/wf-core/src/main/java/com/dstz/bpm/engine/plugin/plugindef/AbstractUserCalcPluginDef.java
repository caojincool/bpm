package com.dstz.bpm.engine.plugin.plugindef;

import com.dstz.bpm.api.constant.ExtractType;
import com.dstz.bpm.api.engine.constant.LogicType;
import com.dstz.bpm.api.engine.plugin.def.BpmUserCalcPluginDef;

public abstract class AbstractUserCalcPluginDef implements BpmUserCalcPluginDef {
	private ExtractType bJ = ExtractType.EXACT_NOEXACT;
	private LogicType bK = LogicType.OR;

	public ExtractType getExtract() {
		return this.bJ;
	}

	public void setExtract(ExtractType type) {
		this.bJ = type;
	}

	public LogicType getLogicCal() {
		return this.bK;
	}

	public void setLogicCal(LogicType logicType) {
		this.bK = logicType;
	}
}