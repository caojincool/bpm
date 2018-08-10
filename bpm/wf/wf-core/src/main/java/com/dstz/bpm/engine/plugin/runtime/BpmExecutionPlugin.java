package com.dstz.bpm.engine.plugin.runtime;

import com.dstz.bpm.api.engine.plugin.def.BpmExecutionPluginDef;
import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
import com.dstz.bpm.engine.plugin.session.BpmExecutionPluginSession;

public abstract interface BpmExecutionPlugin<S extends BpmExecutionPluginSession, M extends BpmExecutionPluginDef>
  extends RunTimePlugin<S, M, Void>
{}


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\plugin\runtime\BpmExecutionPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */