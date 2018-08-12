package com.dstz.bpm.engine.action.handler.task;

import com.dstz.base.api.constant.IStatusCode;
import com.dstz.base.api.exception.BusinessException;
import com.dstz.base.core.util.StringUtil;
import com.dstz.bpm.act.service.ActTaskService;
import com.dstz.bpm.api.constant.EventType;
import com.dstz.bpm.api.constant.NodeType;
import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
import com.dstz.bpm.api.engine.plugin.cmd.TaskCommand;
import com.dstz.bpm.api.exception.BpmStatusCode;
import com.dstz.bpm.api.model.inst.IBpmInstance;
import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
import com.dstz.bpm.api.model.task.IBpmTask;
import com.dstz.bpm.api.service.BpmProcessDefService;
import com.dstz.bpm.core.manager.BpmInstanceManager;
import com.dstz.bpm.core.manager.BpmTaskManager;
import com.dstz.bpm.core.model.BpmTask;
import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
import com.dstz.bpm.engine.action.handler.AbsActionHandler;
import java.io.Serializable;
import javax.annotation.Resource;

public abstract class AbstractTaskActionHandler<T extends DefualtTaskActionCmd> extends AbsActionHandler<T> {
	@Resource
	protected ActTaskService ax;
	@Resource
	protected BpmTaskManager ay;
	@Resource
	protected TaskCommand az;

	public void a(T actionModel) {
		BpmTask bpmTask = (BpmTask) actionModel.getBpmTask();
		String taskId = bpmTask.getTaskId();
		String destinationNode = bpmTask.getBackNode();
		if (StringUtil.isEmpty(destinationNode)) {
			destinationNode = actionModel.getDestination();
		}

		if (StringUtil.isEmpty(destinationNode)) {
			this.ax.completeTask(taskId);
		} else {
			this.ax.completeTask(taskId, new String[]{destinationNode});
		}

	}

	protected void b(T data) {
		if (data.getBpmTask() == null) {
			BpmTask task = (BpmTask) this.ay.get(data.getTaskId());
			if (task == null) {
				throw new BusinessException(BpmStatusCode.TASK_NOT_FOUND);
			} else {
				data.setBpmTask(task);
				data.setDefId(task.getDefId());
				data.setBpmInstance((IBpmInstance) this.f.get(task.getInstId()));
				this.l(data);
				this.a(data, this.a.getBpmNodeDef(task.getDefId(), task.getNodeId()));
			}
		}
	}

	public Boolean isSupport(BpmNodeDef nodeDef) {
		NodeType nodeType = nodeDef.getType();
		return nodeType != NodeType.USERTASK && nodeType != NodeType.SIGNTASK ? false : true;
	}

	protected void c(DefualtTaskActionCmd actionModel) {
		this.az.execute(EventType.TASK_PRE_COMPLETE_EVENT, actionModel);
	}

	public String getDefaultGroovyScript() {
		return "";
	}
}