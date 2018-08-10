package com.dstz.bpm.api.engine.data.result;

import com.dstz.bpm.api.model.inst.IBpmInstance;


public class BpmFlowInstanceData extends BpmFlowData {
    private IBpmInstance instance;

    public IBpmInstance getInstance() {
        return instance;
    }

    public void setInstance(IBpmInstance instance) {
        this.instance = instance;
        this.defId = instance.getDefId();
    }

}
