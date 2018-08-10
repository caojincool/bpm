package com.dstz.bpm.api.engine.data.result;

import com.alibaba.fastjson.JSONObject;
import com.dstz.bpm.api.model.form.BpmForm;
import com.dstz.bpm.api.model.nodedef.Button;

import java.util.List;

public interface FlowData {

    public abstract String getDefId();

    public abstract BpmForm getForm();

    public abstract JSONObject getData();

    public abstract JSONObject getPermission();
    
    public abstract JSONObject getTablePermission();
    
    public abstract JSONObject getInitData();

//	public abstract IBpmDefinition getBpmDefinition();

    public List<Button> getButtonList();

}