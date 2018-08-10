package com.dstz.bpm.engine.action.handler.task;

import com.dstz.base.api.exception.BusinessException;
import com.dstz.bpm.api.constant.ActionType;
import com.dstz.bpm.api.constant.TaskStatus;
import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
import com.dstz.bpm.api.model.task.IBpmTask;
import com.dstz.bpm.core.manager.BpmTaskManager;
import com.dstz.bpm.core.model.BpmTask;
import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
import com.dstz.bpm.engine.action.handler.task.AbstractTaskActionHandler;
import com.dstz.sys.util.ContextUtil;
import org.springframework.stereotype.Component;

@Component
public class TaskLockActionHandler extends AbstractTaskActionHandler<DefualtTaskActionCmd> {
	public ActionType getActionType() {
		return ActionType.LOCK;
	}

	public void f(DefualtTaskActionCmd model) {
		this.b(model);
		this.k((BaseActionCmd) model);
		BpmTask task = (BpmTask) model.getBpmTask();
		if (!task.getAssigneeId().equals("0")) {
			throw new BusinessException("该任务不支持锁定,或已经被锁定");
		}
		task.setAssigneeId(ContextUtil.getCurrentUserId());
		task.setAssigneeNames(ContextUtil.getCurrentUser().getFullname());
		task.setStatus(TaskStatus.LOCK.getKey());
		this.ay.update((Object) task);
	}

	protected void d(DefualtTaskActionCmd actionModel) {
	}

	protected void e(DefualtTaskActionCmd actionModel) {
	}

	public int getSn() {
		return 6;
	}

	public Boolean isDefault() {
		return false;
	}

	public String getDefaultGroovyScript() {
		return "if(task.getAssignee()==\"0\") return true; return false;";
	}

	public String getConfigPage() {
		return "";
	}

	protected void i(BaseActionCmd baseActionCmd) {
		this.d((DefualtTaskActionCmd) baseActionCmd);
	}

	protected void h(BaseActionCmd baseActionCmd) {
		this.e((DefualtTaskActionCmd) baseActionCmd);
	}

	public void a(BaseActionCmd baseActionCmd) {
		this.f((DefualtTaskActionCmd) baseActionCmd);
	}

	public void execute(ActionCmd actionCmd) {
		this.f((DefualtTaskActionCmd) actionCmd);
	}
}