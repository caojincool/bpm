package com.dstz.bpm.engine.plugin.runtime;

import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;
import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
import com.dstz.sys.api.model.SysIdentity;
import java.util.List;

public abstract interface BpmUserCalcPlugin<M extends BpmPluginDef>
  extends RunTimePlugin<BpmUserCalcPluginSession, M, List<SysIdentity>>
{}


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\plugin\runtime\BpmUserCalcPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */