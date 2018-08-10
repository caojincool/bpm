package com.dstz.bpm.engine.plugin.runtime;

import com.dstz.bpm.api.engine.plugin.def.BpmTaskPluginDef;
import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
import com.dstz.bpm.engine.plugin.session.BpmTaskPluginSession;

public abstract interface BpmTaskPlugin<S extends BpmTaskPluginSession, M extends BpmTaskPluginDef>
  extends RunTimePlugin<S, M, Void>
{}


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\plugin\runtime\BpmTaskPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */