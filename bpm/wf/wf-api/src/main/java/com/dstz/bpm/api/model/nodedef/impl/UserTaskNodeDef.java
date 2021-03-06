package com.dstz.bpm.api.model.nodedef.impl;

import java.util.List;

import com.dstz.bpm.api.model.def.BpmVariableDef;


/**
 * 用户任务节扩展定义。
 */
public class UserTaskNodeDef extends BaseBpmNodeDef {

    private List<BpmVariableDef> variableList;

    public List<BpmVariableDef> getVariableList() {
        return variableList;
    }

    public void setVariableList(List<BpmVariableDef> variableList) {
        this.variableList = variableList;
    }
}
