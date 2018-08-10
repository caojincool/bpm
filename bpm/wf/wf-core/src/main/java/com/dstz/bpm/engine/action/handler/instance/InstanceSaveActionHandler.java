package com.dstz.bpm.engine.action.handler.instance;

import com.dstz.base.api.exception.BusinessException;
import com.dstz.base.core.util.StringUtil;
import com.dstz.bpm.api.constant.ActionType;
import com.dstz.bpm.api.constant.InstanceStatus;
import com.dstz.bpm.api.constant.NodeType;
import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
import com.dstz.bpm.api.model.def.IBpmDefinition;
import com.dstz.bpm.api.model.inst.IBpmInstance;
import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
import com.dstz.bpm.api.service.BpmProcessDefService;
import com.dstz.bpm.core.manager.BpmInstanceManager;
import com.dstz.bpm.core.model.BpmInstance;
import com.dstz.bpm.engine.action.cmd.DefaultInstanceActionCmd;
import com.dstz.bpm.engine.action.handler.AbsActionHandler;
import java.io.Serializable;
import org.springframework.stereotype.Component;

@Component
public class InstanceSaveActionHandler extends AbsActionHandler<DefaultInstanceActionCmd> {
	protected void a(DefaultInstanceActionCmd model) {
		BpmInstance instance = (BpmInstance) model.getBpmInstance();
		instance.setStatus(InstanceStatus.STATUS_DRAFT.getKey());
	}

	protected void b(DefaultInstanceActionCmd actionModel) {
	}

	protected void c(DefaultInstanceActionCmd data) {
		data.setBpmDefinition(this.a.getDefinitionById(data.getDefId()));
		this.f(data);
		this.l((BaseActionCmd) data);
		this.a((BaseActionCmd) data, this.a.getStartEvent(data.getDefId()));
	}

	protected void d(DefaultInstanceActionCmd actionModel) {
		this.e(actionModel);
	}

	protected void e(DefaultInstanceActionCmd actionModel) {
		BpmInstance instance = (BpmInstance) actionModel.getBpmInstance();
		if (instance.hasCreate().booleanValue()) {
			this.f.update((Object) instance);
		} else {
			this.f.create((Object) instance);
		}
	}

	protected void f(DefaultInstanceActionCmd intanceCmdData) {
		String instId = intanceCmdData.getInstanceId();
		BpmInstance instance = null;
		if (StringUtil.isNotEmpty((String) instId) && StringUtil.isNotEmpty(
				(String) (instance = (BpmInstance) this.f.get((Serializable) ((Object) instId))).getActInstId())) {
			throw new BusinessException("草稿已经启动，请勿多次启动该草稿！");
		}
		if (instance == null) {
			IBpmDefinition bpmDefinition = intanceCmdData.getBpmDefinition();
			instance = this.f.genInstanceByDefinition(bpmDefinition);
		}
		intanceCmdData.setBpmInstance(instance);
	}

	public ActionType getActionType() {
		return ActionType.DRAFT;
	}

	public int getSn() {
		return 2;
	}

	public Boolean isSupport(BpmNodeDef nodeDef) {
		if (nodeDef.getType() == NodeType.START) {
			return true;
		}
		return false;
	}

	public String getConfigPage() {
		return null;
	}

	public String getDefaultGroovyScript() {
		return "";
	}

	protected void i(BaseActionCmd baseActionCmd) {
		this.d((DefaultInstanceActionCmd) baseActionCmd);
	}

	protected void h(BaseActionCmd baseActionCmd) {
		this.b((DefaultInstanceActionCmd) baseActionCmd);
	}

	protected void b(BaseActionCmd baseActionCmd) {
		this.a((DefaultInstanceActionCmd) baseActionCmd);
	}
}