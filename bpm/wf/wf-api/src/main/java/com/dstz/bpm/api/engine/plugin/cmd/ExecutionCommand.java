package com.dstz.bpm.api.engine.plugin.cmd;

import com.dstz.bpm.api.constant.EventType;
import com.dstz.bpm.api.engine.action.cmd.InstanceActionCmd;

public interface ExecutionCommand {

    public void execute(EventType eventType, InstanceActionCmd actionModel);
}
