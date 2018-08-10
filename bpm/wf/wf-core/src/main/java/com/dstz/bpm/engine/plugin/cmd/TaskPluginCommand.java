package com.dstz.bpm.engine.plugin.cmd;

import com.dstz.base.api.constant.IStatusCode;
import com.dstz.base.api.exception.BusinessException;
import com.dstz.base.core.util.BeanUtils;
import com.dstz.bpm.api.constant.EventType;
import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
import com.dstz.bpm.api.engine.plugin.cmd.TaskCommand;
import com.dstz.bpm.api.engine.plugin.context.BpmPluginContext;
import com.dstz.bpm.api.engine.plugin.def.BpmExecutionPluginDef;
import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;
import com.dstz.bpm.api.engine.plugin.def.BpmTaskPluginDef;
import com.dstz.bpm.api.exception.BpmStatusCode;
import com.dstz.bpm.api.model.def.BpmProcessDef;
import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
import com.dstz.bpm.api.model.task.IBpmTask;
import com.dstz.bpm.api.service.BpmProcessDefService;
import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
import com.dstz.bpm.engine.plugin.factory.BpmPluginFactory;
import com.dstz.bpm.engine.plugin.factory.BpmPluginSessionFactory;
import com.dstz.bpm.engine.plugin.runtime.BpmExecutionPlugin;
import com.dstz.bpm.engine.plugin.runtime.BpmTaskPlugin;
import com.dstz.bpm.engine.plugin.session.BpmTaskPluginSession;
import com.dstz.bpm.engine.plugin.session.impl.DefaultBpmExecutionPluginSession;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TaskPluginCommand implements TaskCommand {
	protected Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Resource
	BpmProcessDefService a;

	public void execute(EventType eventType, TaskActionCmd actionModel) {
		BpmExecutionPlugin bpmExecutionPlugin;
		String defId = actionModel.getBpmTask().getDefId();
		BpmNodeDef bpmNodeDef = this.a.getBpmNodeDef(defId, actionModel.getNodeId());
		BpmTaskPluginSession bpmTaskSession = BpmPluginSessionFactory
				.buildTaskPluginSession((TaskActionCmd) actionModel, (EventType) eventType);
		DefaultBpmExecutionPluginSession executionSession = BpmPluginSessionFactory
				.buildExecutionPluginSession((TaskActionCmd) actionModel, (EventType) eventType);
		for (Object bpmPluginContext : bpmNodeDef.getBpmPluginContexts()) {
			if (BeanUtils.isEmpty((Object) bpmPluginContext.getEventTypes()))
				continue;
			BpmPluginDef bpmPluginDef = bpmPluginContext.getBpmPluginDef();
			if (bpmPluginDef instanceof BpmTaskPluginDef) {
				BpmTaskPluginDef bpmTaskPluginDef = (BpmTaskPluginDef) bpmPluginDef;
				BpmTaskPlugin bpmTaskPlugin = BpmPluginFactory.buildTaskPlugin((BpmPluginContext) bpmPluginContext,
						(EventType) eventType);
				if (bpmTaskPlugin == null)
					continue;
				try {
					this.LOG.debug("==>执行任务插件【{}】", (Object) bpmPluginContext.getTitle());
					bpmTaskPlugin.execute((Object) bpmTaskSession, (Object) bpmTaskPluginDef);
				} catch (Exception e) {
					this.LOG.error("执行任务插件【{}】出现异常:{}", new Object[]{bpmPluginContext.getTitle(), e.getMessage(), e});
					throw new BusinessException(bpmPluginContext.getTitle(), (IStatusCode) BpmStatusCode.PLUGIN_ERROR,
							(Throwable) e);
				}
			}
			if (!(bpmPluginDef instanceof BpmExecutionPluginDef) || (bpmExecutionPlugin = BpmPluginFactory
					.buildExecutionPlugin((BpmPluginContext) bpmPluginContext, (EventType) eventType)) == null)
				continue;
			try {
				this.LOG.debug("==>执行节点实例插件【{}】", (Object) bpmPluginContext.getTitle());
				bpmExecutionPlugin.execute((Object) executionSession, (Object) bpmPluginContext.getBpmPluginDef());
			} catch (Exception e) {
				this.LOG.error("节点实例插件【{}】出现异常:{}", new Object[]{bpmPluginContext.getTitle(), e.getMessage(), e});
				throw new BusinessException(bpmPluginContext.getTitle(), (IStatusCode) BpmStatusCode.PLUGIN_ERROR,
						(Throwable) e);
			}
		}
		DefaultBpmProcessDef bpmProcessDef = (DefaultBpmProcessDef) this.a.getBpmProcessDef(defId);
		for (BpmPluginContext globalCtx : bpmProcessDef.getBpmPluginContexts()) {
			bpmExecutionPlugin = BpmPluginFactory.buildExecutionPlugin((BpmPluginContext) globalCtx,
					(EventType) eventType);
			if (bpmExecutionPlugin == null)
				continue;
			try {
				this.LOG.debug("==>【{}】节点执行全局插件【{}】", (Object) bpmNodeDef.getName(), (Object) globalCtx.getTitle());
				bpmExecutionPlugin.execute((Object) executionSession, (Object) globalCtx.getBpmPluginDef());
			} catch (Exception e) {
				this.LOG.error("【{}】节点执行全局插件【{}】出现异常:{}",
						new Object[]{bpmNodeDef.getName(), globalCtx.getTitle(), e.getMessage(), e});
				throw new BusinessException(globalCtx.getTitle(), (IStatusCode) BpmStatusCode.PLUGIN_ERROR,
						(Throwable) e);
			}
		}
	}
}