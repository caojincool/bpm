package com.dstz.bpm.api.engine.action.cmd;

import com.dstz.bpm.api.constant.ActionType;
import com.dstz.bpm.api.model.task.IBpmTask;


/**
 * 任务处理命令接口
 */
public interface TaskActionCmd extends ActionCmd {


    /**
     * 动作类型。
     *
     * @return ActionType
     */
    ActionType getActionType();


    /**
     * 获取任务ID
     *
     * @return
     */
    String getTaskId();

    /**
     * 获取节点ID
     *
     * @return
     */
    String getNodeId();


    IBpmTask getBpmTask();


    void setBpmTask(IBpmTask task);

    /**
     * 目标节点，在一般情况下不需要指定，流程会按照流程图进行运行。
     * 在需要指定节点跳转的情况下，才会起作用。
     */
    String getDestination();

    void setDestination(String destination);


    String getOpinion();
}
