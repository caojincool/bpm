package com.dstz.bpm.api.engine.plugin.cmd;

import com.dstz.bpm.api.constant.EventType;
import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;

public interface TaskCommand {
    public void execute(EventType eventType, TaskActionCmd model);
}
