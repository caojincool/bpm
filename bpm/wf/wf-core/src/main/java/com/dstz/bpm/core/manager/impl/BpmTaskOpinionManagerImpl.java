package com.dstz.bpm.core.manager.impl;

import com.dstz.base.core.util.BeanUtils;
import com.dstz.base.manager.impl.BaseManager;
import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
import com.dstz.bpm.api.model.inst.IBpmInstance;
import com.dstz.bpm.api.model.task.IBpmTask;
import com.dstz.bpm.core.dao.BpmTaskOpinionDao;
import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
import com.dstz.bpm.core.model.BpmTaskOpinion;
import com.dstz.org.api.model.IUser;
import com.dstz.sys.api.model.SysIdentity;
import com.dstz.sys.util.ContextUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service(value = "bpmTaskOpinionManager")
public class BpmTaskOpinionManagerImpl extends BaseManager<String, BpmTaskOpinion> implements BpmTaskOpinionManager {
	@Resource
	BpmTaskOpinionDao k;

	public BpmTaskOpinion getByTaskId(String taskId) {
		return this.k.getByTaskId(taskId);
	}

	public void createOpinionByInstance(IBpmInstance bpmInstance, String formIdentity, boolean isCreateEvent) {
		BpmTaskOpinion bpmTaskOpinion = new BpmTaskOpinion();
		bpmTaskOpinion.setApproveTime(new Date());
		bpmTaskOpinion.setUpdateTime(new Date());
		bpmTaskOpinion.setDurMs(Long.valueOf(0L));
		bpmTaskOpinion.setInstId(bpmInstance.getId());
		bpmTaskOpinion.setSupInstId(bpmInstance.getParentInstId());
		bpmTaskOpinion.setOpinion(isCreateEvent ? "发起流程" : "流程结束");
		bpmTaskOpinion.setStatus(isCreateEvent ? "start" : "end");
		bpmTaskOpinion.setTaskId("0");
		bpmTaskOpinion.setTaskKey(isCreateEvent ? "start" : "end");
		bpmTaskOpinion.setTaskName(isCreateEvent ? "发起节点" : "终止节点");
		bpmTaskOpinion.setFormId(formIdentity);
		IUser user = ContextUtil.getCurrentUser();
		if (user != null) {
			bpmTaskOpinion.setApprover(user.getUserId());
			bpmTaskOpinion.setApproverName(user.getFullname());
			bpmTaskOpinion.setCreateBy(user.getUserId());
			bpmTaskOpinion.setUpdateBy(user.getUserId());
		}
		this.create(bpmTaskOpinion);
	}

	public void createOpinionByTask(TaskActionCmd taskActionModel) {
		IBpmTask task = taskActionModel.getBpmTask();
		IBpmInstance bpmInstance = taskActionModel.getBpmInstance();
		List<SysIdentity> taskIdentitys = taskActionModel.getBpmIdentity(taskActionModel.getNodeId());
		BpmTaskOpinion bpmTaskOpinion = new BpmTaskOpinion();
		bpmTaskOpinion.setUpdateTime(new Date());
		bpmTaskOpinion.setDurMs(Long.valueOf(0L));
		bpmTaskOpinion.setInstId(bpmInstance.getId());
		bpmTaskOpinion.setSupInstId(bpmInstance.getParentInstId());
		bpmTaskOpinion.setOpinion(taskActionModel.getOpinion());
		bpmTaskOpinion.setStatus(taskActionModel.getActionName());
		bpmTaskOpinion.setTaskId(task.getId());
		bpmTaskOpinion.setTaskKey(task.getNodeId());
		bpmTaskOpinion.setTaskName(task.getName());
		bpmTaskOpinion.setFormId(taskActionModel.getFormId());
		IUser user = ContextUtil.getCurrentUser();
		if (user != null) {
			bpmTaskOpinion.setCreateBy(user.getUserId());
			bpmTaskOpinion.setUpdateBy(user.getUserId());
		}
		StringBuffer assignInfo = new StringBuffer();
		if (BeanUtils.isNotEmpty((Object) taskIdentitys)) {
			for (SysIdentity identity : taskIdentitys) {
				assignInfo.append(identity.getType()).append("-").append(identity.getName()).append("-")
						.append(identity.getId()).append(",");
			}
		}
		bpmTaskOpinion.setAssignInfo(assignInfo.toString());
		this.create(bpmTaskOpinion);
	}

	public List<BpmTaskOpinion> getByInstAndNode(String instId, String nodeId) {
		return this.k.getByInstAndNode(instId, nodeId);
	}

	public List<BpmTaskOpinion> getByInstId(String instId) {
		return this.k.getByInstAndNode(instId, null);
	}
}