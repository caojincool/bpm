package com.dstz.bpm.api.engine.plugin.def;

import com.dstz.bpm.api.constant.ExtractType;
import com.dstz.bpm.api.engine.constant.LogicType;

public interface BpmUserCalcPluginDef extends BpmTaskPluginDef {

    /**
     * 获取抽取类型。
     *
     * @return ExtractType
     */
    ExtractType getExtract();


    /**
     * 设置抽取类型。
     *
     * @param type void
     */
    void setExtract(ExtractType type);

    /**
     * 逻辑类型。
     *
     * @return LogicType
     */
    LogicType getLogicCal();

    /**
     * 设置逻辑类型
     *
     * @param logicType void
     */
    void setLogicCal(LogicType logicType);


}
