package com.dstz.bpm.plugin.usercalc.user.runtime;

import com.dstz.bpm.api.engine.plugin.def.BpmTaskPluginDef;
import com.dstz.bpm.api.model.task.IBpmTask;
import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
import com.dstz.bpm.core.model.BpmTaskOpinion;
import com.dstz.bpm.engine.model.BpmIdentity;
import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractUserCalcPlugin;
import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
import com.dstz.bpm.plugin.usercalc.user.def.UserPluginDef;
import com.dstz.org.api.model.IUser;
import com.dstz.org.api.service.UserService;
import com.dstz.sys.api.model.SysIdentity;
import com.dstz.sys.util.ContextUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class UserPlugin extends AbstractUserCalcPlugin<UserPluginDef> {
	@Resource
	BpmTaskOpinionManager aa;
	@Resource
	UserService ak;

	public List<SysIdentity> a(BpmUserCalcPluginSession pluginSession, UserPluginDef def) {
		ArrayList<SysIdentity> list = new ArrayList<SysIdentity>();
		String source = def.getSource();
		if ("start".equals(source)) {
			List opinions = this.aa.getByInstAndNode(pluginSession.getBpmTask().getInstId(), "start");
			BpmTaskOpinion firstNode = (BpmTaskOpinion) opinions.get(0);
			BpmIdentity bpmIdentity = new BpmIdentity(firstNode.getApprover(), firstNode.getApproverName(), "user");
			list.add((SysIdentity) bpmIdentity);
		}
		if ("currentUser".equals(source)) {
			BpmIdentity bpmIdentity = new BpmIdentity(ContextUtil.getCurrentUser());
			list.add((SysIdentity) bpmIdentity);
		} else if ("spec".equals(source)) {
			BpmIdentity aryAccount;
			String userKeys = def.getAccount();
			for (BpmIdentity account : aryAccount = userKeys.split(",")) {
				IUser user = this.ak.getUserByAccount((String) account);
				BpmIdentity bpmIdentity = new BpmIdentity(user);
				list.add((SysIdentity) bpmIdentity);
			}
		}
		return list;
	}

	public List queryByPluginDef(BpmUserCalcPluginSession bpmUserCalcPluginSession, BpmTaskPluginDef bpmTaskPluginDef) {
		return this.a(bpmUserCalcPluginSession, (UserPluginDef) bpmTaskPluginDef);
	}
}