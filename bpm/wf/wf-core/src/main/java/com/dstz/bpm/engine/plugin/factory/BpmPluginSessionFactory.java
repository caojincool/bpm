package com.dstz.bpm.engine.plugin.factory;

import com.dstz.bpm.api.constant.EventType;
import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
import com.dstz.bpm.api.engine.action.cmd.InstanceActionCmd;
import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
import com.dstz.bpm.api.engine.context.BpmContext;
import com.dstz.bpm.api.model.inst.IBpmInstance;
import com.dstz.bpm.api.model.task.IBpmTask;
import com.dstz.bpm.engine.action.cmd.DefaultInstanceActionCmd;
import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
import com.dstz.bpm.engine.plugin.session.BpmExecutionPluginSession;
import com.dstz.bpm.engine.plugin.session.BpmPluginSession;
import com.dstz.bpm.engine.plugin.session.BpmTaskPluginSession;
import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
import com.dstz.bpm.engine.plugin.session.impl.DefaultBpmExecutionPluginSession;
import com.dstz.bpm.engine.plugin.session.impl.DefaultBpmTaskPluginSession;
import com.dstz.bpm.engine.plugin.session.impl.DefaultBpmUserCalcPluginSession;
import java.util.Map;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.VariableScope;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;

public class BpmPluginSessionFactory {
	public static BpmExecutionPluginSession buildExecutionPluginSession(InstanceActionCmd actionModel,
			EventType eventType) {
		DefaultBpmExecutionPluginSession executionPluginSession = new DefaultBpmExecutionPluginSession();
		executionPluginSession.setBoDatas(actionModel.getBizDataMap());
		executionPluginSession.setBpmInstance(actionModel.getBpmInstance());
		executionPluginSession.setEventType(eventType);
		executionPluginSession
				.setVariableScope((VariableScope) ((DefaultInstanceActionCmd) actionModel).getExecutionEntity());
		return executionPluginSession;
	}

	public static BpmTaskPluginSession buildTaskPluginSession(TaskActionCmd actionModel, EventType eventType) {
		DefualtTaskActionCmd taskActionModel = (DefualtTaskActionCmd) actionModel;
		DefaultBpmTaskPluginSession session = new DefaultBpmTaskPluginSession();
		session.setBoDatas(actionModel.getBizDataMap());
		session.setBpmInstance(actionModel.getBpmInstance());
		session.setEventType(eventType);
		session.setVariableScope((VariableScope) taskActionModel.getDelagateTask());
		session.setBpmTask(taskActionModel.getBpmTask());
		return session;
	}

	public static DefaultBpmExecutionPluginSession buildExecutionPluginSession(TaskActionCmd actionModel,
			EventType eventType) {
		DefualtTaskActionCmd taskActionModel = (DefualtTaskActionCmd) actionModel;
		DefaultBpmExecutionPluginSession executionPluginSession = new DefaultBpmExecutionPluginSession();
		executionPluginSession.setBoDatas(actionModel.getBizDataMap());
		executionPluginSession.setBpmInstance(actionModel.getBpmInstance());
		executionPluginSession.setVariableScope((VariableScope) taskActionModel.getDelagateTask());
		executionPluginSession.setEventType(eventType);
		return executionPluginSession;
	}

	public static BpmUserCalcPluginSession buildBpmUserCalcPluginSession(BpmPluginSession pluginSession) {
		ActionCmd action;
		DefaultBpmExecutionPluginSession plugin = (DefaultBpmExecutionPluginSession) pluginSession;
		DefaultBpmUserCalcPluginSession userCalcPluginSession = new DefaultBpmUserCalcPluginSession();
		userCalcPluginSession.setBoDatas(pluginSession.getBoDatas());
		userCalcPluginSession.setVariableScope(plugin.getVariableScope());
		if (pluginSession instanceof BpmTaskPluginSession) {
			BpmTaskPluginSession taskSession = (BpmTaskPluginSession) pluginSession;
			userCalcPluginSession.setBpmTask(taskSession.getBpmTask());
		}
		if ((action = BpmContext.getActionModel()) != null && action instanceof TaskActionCmd) {
			TaskActionCmd taskCmd = (TaskActionCmd) action;
			userCalcPluginSession.setBpmTask(taskCmd.getBpmTask());
		}
		return userCalcPluginSession;
	}
}