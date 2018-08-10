package com.dstz.bpm.api.model.nodedef.impl;

import com.dstz.bpm.api.engine.plugin.context.BpmPluginContext;
import com.dstz.bpm.api.model.def.BpmProcessDef;

import java.util.List;

/**
 * 网关节点定义。
 * <pre>
 * 描述：TODO
 * </pre>
 */
public class GateWayBpmNodeDef extends BaseBpmNodeDef {

    @Override
    public List<BpmPluginContext> getBpmPluginContexts() {
        throw new RuntimeException("GateWayBpmNodeDef not support getBpmPluginContexts method");
    }

    /**
     * 获得内部子流程的流程定义。
     *
     * @return Map<String   ,   BpmNodeDef> key：nodeId，value：BpmNodeDef
     * @throws
     * @since 1.0.0
     */
    public BpmProcessDef getChildBpmProcessDef() {
        throw new RuntimeException("GateWayBpmNodeDef not support getChildBpmProcessDef method");
    }


}
