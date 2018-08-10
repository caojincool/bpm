/*    */ package com.dstz.bpm.engine.parser.flow;
/*    */ 
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.base.core.encrypt.EncryptUtil;
/*    */ import com.dstz.base.core.util.StringUtil;
/*    */ import com.dstz.base.core.util.time.DateFormatUtil;
/*    */ import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
/*    */ import com.dstz.bpm.engine.model.DefaultBpmVariableDef;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Date;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ public class FlowVersionParse extends AbsFlowParse<DefaultBpmVariableDef>
/*    */ {
/* 16 */   private static boolean bD = false;
/* 17 */   private static String bE = "b";
/*    */   
/*    */   public void b(DefaultBpmProcessDef def, JSONObject flowConf)
/*    */   {
/* 21 */     if (bD) {
/* 22 */       flowConf.put("v", bE);
/* 23 */       if (bE.equals("b")) {
/* 24 */         a(flowConf);
/*    */       }
/* 26 */       return;
/*    */     }
/*    */     
/* 29 */     String key = com.dstz.base.core.util.PropertyUtil.getProperty(String.format("%s.%s", new Object[] { "s", "k" }));
/* 30 */     bE = f(key);
/* 31 */     g(bE);
/*    */     
/* 33 */     if (bE.equals("b")) {
/* 34 */       a(flowConf);
/*    */     }
/*    */     
/* 37 */     bD = true;
/* 38 */     flowConf.put("v", bE);
/*    */   }
/*    */   
/*    */ 
/*    */   private String f(String key)
/*    */   {
/*    */     try
/*    */     {
/* 46 */       String str = EncryptUtil.decrypt(key);
/* 47 */       if (StringUtil.isEmpty(str)) {
/* 48 */         return bE;
/*    */       }
/* 50 */       String[] msg = str.split("_");
/* 51 */       if (msg.length != 3) {
/* 52 */         return bE;
/*    */       }
/* 54 */       Date date = DateFormatUtil.parse(msg[2]);
/* 55 */       if (date.before(new Date())) {
/* 56 */         return bE;
/*    */       }
/*    */       
/* 59 */       return msg[0];
/*    */     }
/*    */     catch (Exception e) {}
/*    */     
/* 63 */     return bE;
/*    */   }
/*    */   
/*    */   public static void main(String[] args) throws Exception
/*    */   {
/* 68 */     String code = "senior_agileBpm敏捷工作流_2019-5-13";
/* 69 */     System.out.println(EncryptUtil.encrypt(code));
/* 70 */     System.out.println(EncryptUtil.decrypt("5392537a203272132719aad01638fc99d0258d67e6cb7c29b76672a4cb47c18f0a16f85c569009d02da55598643b4169"));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private void a(JSONObject flowConf) {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private void g(String string)
/*    */   {
/* 88 */     new Thread(new Runnable() {
/*    */       public void run() {}
/*    */     })
/*    */     
/*    */ 
/*    */ 
/*    */ 
/* 88 */       .run();
/*    */   }
/*    */   
/*    */   public String getKey() {
/* 94 */     return null;
/*    */   }
/*    */   
/*    */   public void a(DefaultBpmProcessDef bpmdef, Object object) {}
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\parser\flow\FlowVersionParse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */