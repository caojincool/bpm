 package com.dstz.bpm.test;
 
 import com.alibaba.fastjson.JSON;
 import com.alibaba.fastjson.JSONObject;
 import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
 
 @org.springframework.stereotype.Component
 public class TestUrlService
 {
   public void a(ActionCmd cmd)
   {
     String data = cmd.getBusData();
     JSONObject json = JSON.parseObject(data);
     
     System.err.println(json);
     cmd.setBusinessKey("123");
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\test\TestUrlService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */