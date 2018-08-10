package com.dstz.bpm.plugin.usercalc.util;

import com.dstz.base.core.util.BeanUtils;
import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
import com.dstz.bpm.plugin.usercalc.user.def.ExecutorVar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UserCalcHelper {
	public static List<String> a(ExecutorVar executorVar, BpmUserCalcPluginSession pluginSession,
			boolean turnKeys2Ids) {
		ArrayList<String> ids;
		Map vars = null;
		ids = new ArrayList<String>();
		String PK = "";
		if ("BO".equals(executorVar.getSource())) {
			String[] BOData = executorVar.getName().split("\\.");
			String[] boMap = null;
			String[] boData = (String[]) boMap.get(BOData[0]);
			PK = boData.getString(BOData[1]);
		} else if ("flowVar".equals(executorVar.getSource())) {
			PK = (String) vars.get(executorVar.getName());
		}
		String[] PKs = PK.split(",");
		if (BeanUtils.isEmpty((Object) PK)) {
			return Collections.emptyList();
		}
		if (executorVar.getExecutorType().equals("fixed")) {
			ids.addAll(Arrays.asList(PKs));
			return ids;
		}
		if ("user".equals(executorVar.getExecutorType())) {
			if ("userId".equals(executorVar.getUserValType()) || !turnKeys2Ids) {
				ids.addAll(Arrays.asList(PKs));
			} else {
				for (String account : PKs) {
					Object u = null;
					if (u == null)
						continue;
					ids.add(u.getUserId());
				}
			}
		} else if ("groupId".equals(executorVar.getGroupValType()) || !turnKeys2Ids) {
			ids.addAll(Arrays.asList(PKs));
		} else {
			String dimension = executorVar.getDimension();
			for (String groupKey : PKs) {
				Object group = null;
				if (group == null)
					continue;
				ids.add(group.getGroupId());
			}
		}
		return ids;
	}
}