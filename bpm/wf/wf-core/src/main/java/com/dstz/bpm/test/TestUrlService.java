package com.dstz.bpm.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
import java.io.PrintStream;
import org.springframework.stereotype.Component;

@Component
public class TestUrlService {
	public void a(ActionCmd cmd) {
		String data = cmd.getBusData();
		JSONObject json = JSON.parseObject((String) data);
		System.err.println((Object) json);
		cmd.setBusinessKey("123");
	}
}