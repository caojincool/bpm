package com.dstz.bpm.plugin.task.ruleskip.def;

import com.dstz.bpm.engine.plugin.plugindef.AbstractBpmTaskPluginDef;
import com.dstz.bpm.plugin.task.ruleskip.def.JumpRule;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

public class RuleSkipPluginDef extends AbstractBpmTaskPluginDef {
	@Valid
	private List<JumpRule> S = new ArrayList<JumpRule>();

	public List<JumpRule> getJumpRules() {
		return this.S;
	}

	public void setJumpRules(List<JumpRule> jumpRules) {
		this.S = jumpRules;
	}
}