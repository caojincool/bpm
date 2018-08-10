package com.dstz.bpm.engine.action.handler.instance;

import com.dstz.bpm.api.constant.ActionType;
import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
import com.dstz.bpm.api.engine.action.handler.ActionHandler;
import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
import org.springframework.stereotype.Component;

@Component
public class InstanceImageActionHandler implements ActionHandler {
	public void execute(ActionCmd model) {
	}

	public ActionType getActionType() {
		return ActionType.FLOWIMAGE;
	}

	public int getSn() {
		return 6;
	}

	public Boolean isSupport(BpmNodeDef nodeDef) {
		return true;
	}

	public Boolean isDefault() {
		return true;
	}

	public String getConfigPage() {
		return "/bpm/instance/instanceImageDialog.html";
	}

	public String getDefaultGroovyScript() {
		return "";
	}

	public String getDefaultBeforeScript() {
		return "";
	}
}