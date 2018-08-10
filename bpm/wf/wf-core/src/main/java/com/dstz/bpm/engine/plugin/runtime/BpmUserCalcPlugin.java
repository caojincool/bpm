package com.dstz.bpm.engine.plugin.runtime;

import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;
import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
import com.dstz.sys.api.model.SysIdentity;
import java.util.List;

public interface BpmUserCalcPlugin<M extends BpmPluginDef>
		extends
			RunTimePlugin<BpmUserCalcPluginSession, M, List<SysIdentity>> {
}