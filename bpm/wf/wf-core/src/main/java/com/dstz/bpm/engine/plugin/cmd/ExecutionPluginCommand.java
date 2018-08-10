package com.dstz.bpm.engine.plugin.cmd;

import com.dstz.base.api.constant.IStatusCode;
import com.dstz.base.api.exception.BusinessException;
import com.dstz.bpm.api.constant.EventType;
import com.dstz.bpm.api.engine.action.cmd.InstanceActionCmd;
import com.dstz.bpm.api.engine.plugin.cmd.ExecutionCommand;
import com.dstz.bpm.api.engine.plugin.context.BpmPluginContext;
import com.dstz.bpm.api.engine.plugin.def.BpmExecutionPluginDef;
import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;
import com.dstz.bpm.api.exception.BpmStatusCode;
import com.dstz.bpm.api.model.def.BpmProcessDef;
import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
import com.dstz.bpm.api.service.BpmProcessDefService;
import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
import com.dstz.bpm.engine.plugin.factory.BpmPluginFactory;
import com.dstz.bpm.engine.plugin.factory.BpmPluginSessionFactory;
import com.dstz.bpm.engine.plugin.runtime.BpmExecutionPlugin;
import com.dstz.bpm.engine.plugin.session.BpmExecutionPluginSession;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExecutionPluginCommand implements ExecutionCommand {
	protected Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Resource
	BpmProcessDefService a;

	public void execute(EventType eventType, InstanceActionCmd actionModel) {
		String defId = actionModel.getDefId();
		DefaultBpmProcessDef bpmProcessDef = (DefaultBpmProcessDef) this.a.getBpmProcessDef(defId);
		for (Object bpmPluginContext : bpmProcessDef.getBpmPluginContexts()) {
			this.a((BpmPluginContext) bpmPluginContext, actionModel, eventType);
		}
		BpmNodeDef nodeDef = null;
		if (eventType == EventType.START_POST_EVENT || eventType == EventType.START_EVENT) {
			nodeDef = this.a.getStartEvent(defId);
		} else if (eventType == EventType.END_EVENT || eventType == EventType.END_POST_EVENT) {
			nodeDef = (BpmNodeDef) this.a.getEndEvents(defId).get(0);
		}
		if (nodeDef != null && nodeDef.getBpmPluginContexts() != null) {
			for (BpmPluginContext bpmPluginContext : nodeDef.getBpmPluginContexts()) {
				this.a(bpmPluginContext, actionModel, eventType);
			}
		}
	}

	private void a(BpmPluginContext bpmPluginContext, InstanceActionCmd actionModel, EventType eventType) {
		BpmPluginDef bpmPluginDef = bpmPluginContext.getBpmPluginDef();
		if (bpmPluginDef instanceof BpmExecutionPluginDef) {
			BpmExecutionPluginDef bpmExecutionPluginDef = (BpmExecutionPluginDef) bpmPluginDef;
			BpmExecutionPlugin bpmExecutionPlugin = BpmPluginFactory
					.buildExecutionPlugin((BpmPluginContext) bpmPluginContext, (EventType) eventType);
			BpmExecutionPluginSession session = BpmPluginSessionFactory
					.buildExecutionPluginSession((InstanceActionCmd) actionModel, (EventType) eventType);
			if (bpmExecutionPlugin != null) {
				try {
					bpmExecutionPlugin.execute((Object) session, (Object) bpmExecutionPluginDef);
					this.LOG.debug("==>执行插件【{}】", (Object) bpmPluginContext.getTitle());
				} catch (Exception e) {
					this.LOG.error("执行插件【{}】出现异常:{}", new Object[]{bpmPluginContext.getTitle(), e.getMessage(), e});
					throw new BusinessException(bpmPluginContext.getTitle(), (IStatusCode) BpmStatusCode.PLUGIN_ERROR,
							(Throwable) e);
				}
			}
		}
	}
}