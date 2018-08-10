package com.dstz.bpm.plugin.execution.nodemessage.context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dstz.base.core.util.AppUtil;
import com.dstz.base.core.util.StringUtil;
import com.dstz.bpm.api.constant.EventType;
import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;
import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
import com.dstz.bpm.engine.plugin.context.AbstractBpmExecutionPluginContext;
import com.dstz.bpm.plugin.execution.nodemessage.def.NodeMessage;
import com.dstz.bpm.plugin.execution.nodemessage.def.NodeMessagePluginDef;
import com.dstz.bpm.plugin.execution.nodemessage.plugin.NodeMessagePlugin;
import com.dstz.bpm.plugin.task.userassign.context.UserAssignPluginContext;
import com.dstz.bpm.plugin.task.userassign.def.UserAssignPluginDef;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class NodeMessagePluginContext extends AbstractBpmExecutionPluginContext<NodeMessagePluginDef> {
	private static final long serialVersionUID = -8171025388788811778L;

	public List<EventType> getEventTypes() {
		ArrayList<EventType> list = new ArrayList<EventType>();
		list.add(EventType.TASK_POST_CREATE_EVENT);
		list.add(EventType.START_EVENT);
		list.add(EventType.END_EVENT);
		list.add(EventType.TASK_COMPLETE_EVENT);
		return list;
	}

	public Class<? extends RunTimePlugin> getPluginClass() {
		return NodeMessagePlugin.class;
	}

	protected NodeMessagePluginDef a(JSON pluginJson) {
		JSONArray array = (JSONArray) pluginJson;
		ArrayList<NodeMessage> messageList = new ArrayList<NodeMessage>();
		for (int i = 0; i < array.size(); ++i) {
			JSONObject msgJson = array.getJSONObject(i);
			NodeMessage nodeMessage = (NodeMessage) JSON.toJavaObject((JSON) msgJson, NodeMessage.class);
			if (StringUtil.isNotEmpty((String) msgJson.getString("userRules"))) {
				UserAssignPluginContext userPluginContext = (UserAssignPluginContext) AppUtil
						.getBean(UserAssignPluginContext.class);
				userPluginContext.parse(msgJson.getString("userRules"));
				nodeMessage.setUserRules(((UserAssignPluginDef) userPluginContext.getBpmPluginDef()).getRuleList());
			}
			messageList.add(nodeMessage);
		}
		return new NodeMessagePluginDef(messageList);
	}

	public String getTitle() {
		return "节点消息";
	}

	protected BpmPluginDef parseFromJson(JSON jSON) {
		return this.a(jSON);
	}
}