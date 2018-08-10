package com.dstz.bpm.api.engine.data.result;

import com.dstz.bpm.api.model.task.IBpmTask;

public class BpmFlowTaskData extends BpmFlowData {
    private IBpmTask task;

    public IBpmTask getTask() {
        return task;
    }

    public void setTask(IBpmTask task) {
        this.task = task;
        this.defId = task.getDefId();
    }

}
