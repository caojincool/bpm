package com.dstz.bpm.engine.parser.flow;

import com.alibaba.fastjson.JSONObject;
import com.dstz.base.core.encrypt.EncryptUtil;
import com.dstz.base.core.util.PropertyUtil;
import com.dstz.base.core.util.StringUtil;
import com.dstz.base.core.util.time.DateFormatUtil;
import com.dstz.bpm.api.engine.plugin.def.BpmDef;
import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
import com.dstz.bpm.engine.model.DefaultBpmVariableDef;
import com.dstz.bpm.engine.parser.flow.AbsFlowParse;
import java.io.PrintStream;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class FlowVersionParse extends AbsFlowParse<DefaultBpmVariableDef> {
	private static boolean bD = false;
	private static String bE = "b";

	public void b(DefaultBpmProcessDef def, JSONObject flowConf) {
		if (bD) {
			flowConf.put("v", (Object) bE);
			if (bE.equals("b")) {
				this.a(flowConf);
			}
			return;
		}
		String key = PropertyUtil.getProperty((String) String.format("%s.%s", "s", "k"));
		bE = this.f(key);
		this.g(bE);
		if (bE.equals("b")) {
			this.a(flowConf);
		}
		bD = true;
		flowConf.put("v", (Object) bE);
	}

	private String f(String key) {
		try {
			String str = EncryptUtil.decrypt((String) key);
			if (StringUtil.isEmpty((String) str)) {
				return bE;
			}
			String[] msg = str.split("_");
			if (msg.length != 3) {
				return bE;
			}
			Date date = DateFormatUtil.parse((String) msg[2]);
			if (date.before(new Date())) {
				return bE;
			}
			return msg[0];
		} catch (Exception e) {
			return bE;
		}
	}

	public static void main(String[] args) throws Exception {
		String code = "senior_agileBpm敏捷工作流_2019-5-13";
		System.out.println(EncryptUtil.encrypt((String) code));
		System.out.println(EncryptUtil.decrypt(
				(String) "5392537a203272132719aad01638fc99d0258d67e6cb7c29b76672a4cb47c18f0a16f85c569009d02da55598643b4169"));
	}

	private void a(JSONObject flowConf) {
	}

	private void g(String string) {
		new Thread(new Runnable() {

			@Override
			public void run() {
			}
		}).run();
	}

	public String getKey() {
		return null;
	}

	public void a(DefaultBpmProcessDef bpmdef, Object object) {
	}

	public void parse(BpmDef bpmDef, JSONObject jSONObject) {
		this.b((DefaultBpmProcessDef) bpmDef, jSONObject);
	}

	public void setDefParam(BpmDef bpmDef, Object object) {
		this.a((DefaultBpmProcessDef) bpmDef, object);
	}

}