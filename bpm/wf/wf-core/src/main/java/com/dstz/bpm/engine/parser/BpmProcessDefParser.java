/*    */ package com.dstz.bpm.engine.parser;
/*    */ 
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.base.core.util.BeanUtils;
/*    */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*    */ import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
/*    */ import com.dstz.bpm.engine.parser.flow.AbsFlowParse;
/*    */ import com.dstz.bpm.engine.parser.node.AbsNodeParse;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class BpmProcessDefParser
/*    */ {
/*    */   private static List<AbsFlowParse> bi;
/*    */   private static List<AbsNodeParse> bj;
/*    */   
/*    */   public static void a(DefaultBpmProcessDef bpmProcessDef, JSONObject bpmDefSetting)
/*    */   {
/* 20 */     JSONObject flowConf = bpmDefSetting.getJSONObject("flow");
/* 21 */     for (Iterator localIterator1 = getFlowParsers().iterator(); localIterator1.hasNext();) { flowParser = (AbsFlowParse)localIterator1.next();
/* 22 */       flowParser.parse(bpmProcessDef, flowConf);
/*    */     }
/*    */     
/* 25 */     JSONObject nodeMap = bpmDefSetting.getJSONObject("nodeMap");
/* 26 */     for (AbsFlowParse flowParser = bpmProcessDef.getBpmnNodeDefs().iterator(); flowParser.hasNext();) { nodeDef = (BpmNodeDef)flowParser.next();
/* 27 */       nodeConfig = nodeMap.getJSONObject(nodeDef.getNodeId());
/*    */       
/* 29 */       for (AbsNodeParse nodeParser : getNodeParsers()) {
/* 30 */         if (nodeParser.a(nodeDef)) {
/* 31 */           nodeParser.parse(nodeDef, nodeConfig);
/*    */         }
/*    */       }
/*    */     }
/*    */     BpmNodeDef nodeDef;
/*    */     JSONObject nodeConfig;
/*    */   }
/*    */   
/*    */   private static List<AbsFlowParse> getFlowParsers()
/*    */   {
/* 41 */     if (BeanUtils.isNotEmpty(bi)) { return bi;
/*    */     }
/* 43 */     Map<String, AbsFlowParse> map = com.dstz.base.core.util.AppUtil.getImplInstance(AbsFlowParse.class);
/* 44 */     bi = new java.util.ArrayList(map.values());
/*    */     
/* 46 */     return bi;
/*    */   }
/*    */   
/*    */   private static List<AbsNodeParse> getNodeParsers()
/*    */   {
/* 51 */     if (BeanUtils.isNotEmpty(bj)) return bj;
/* 52 */     Map<String, AbsNodeParse> map = com.dstz.base.core.util.AppUtil.getImplInstance(AbsNodeParse.class);
/* 53 */     bj = new java.util.ArrayList(map.values());
/*    */     
/* 55 */     return bj;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\parser\BpmProcessDefParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */