package com.dstz.bpm.engine.action.handler.task;

import com.dstz.bpm.api.constant.ActionType;
import com.dstz.bpm.api.constant.NodeType;
import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
import com.dstz.bpm.engine.action.handler.task.AbstractTaskActionHandler;
import org.springframework.stereotype.Component;

@Component
public class TaskOpposeActionHandler extends AbstractTaskActionHandler<DefualtTaskActionCmd> {
	public ActionType getActionType() {
		return ActionType.OPPOSE;
	}

	protected void d(DefualtTaskActionCmd actionModel) {
	}

	protected void e(DefualtTaskActionCmd actionModel) {
	}

	public int getSn() {
		return 2;
	}

	public Boolean isSupport(BpmNodeDef nodeDef) {
		NodeType nodeType = nodeDef.getType();
		if (nodeType == NodeType.USERTASK || nodeType == NodeType.SIGNTASK) {
			return true;
		}
		return false;
	}

	public String getConfigPage() {
		return "/bpm/task/taskOpinionDialog.html";
	}

	public Boolean isDefault() {
		return false;
	}

	protected void i(BaseActionCmd baseActionCmd) {
		this.d((DefualtTaskActionCmd) baseActionCmd);
	}

	protected void h(BaseActionCmd baseActionCmd) {
		this.e((DefualtTaskActionCmd) baseActionCmd);
	}
}