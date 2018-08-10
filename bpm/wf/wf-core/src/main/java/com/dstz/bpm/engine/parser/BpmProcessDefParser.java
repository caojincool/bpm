 package com.dstz.bpm.engine.parser;
 
 import com.alibaba.fastjson.JSONObject;
 import com.dstz.base.core.util.BeanUtils;
 import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
 import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
 import com.dstz.bpm.engine.parser.flow.AbsFlowParse;
 import com.dstz.bpm.engine.parser.node.AbsNodeParse;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
 
 public class BpmProcessDefParser
 {
   private static List<AbsFlowParse> bi;
   private static List<AbsNodeParse> bj;
   
   public static void a(DefaultBpmProcessDef bpmProcessDef, JSONObject bpmDefSetting)
   {
     JSONObject flowConf = bpmDefSetting.getJSONObject("flow");
     for (Iterator localIterator1 = getFlowParsers().iterator(); localIterator1.hasNext();) { flowParser = (AbsFlowParse)localIterator1.next();
       flowParser.parse(bpmProcessDef, flowConf);
     }
     
     JSONObject nodeMap = bpmDefSetting.getJSONObject("nodeMap");
     for (AbsFlowParse flowParser = bpmProcessDef.getBpmnNodeDefs().iterator(); flowParser.hasNext();) { nodeDef = (BpmNodeDef)flowParser.next();
       nodeConfig = nodeMap.getJSONObject(nodeDef.getNodeId());
       
       for (AbsNodeParse nodeParser : getNodeParsers()) {
         if (nodeParser.a(nodeDef)) {
           nodeParser.parse(nodeDef, nodeConfig);
         }
       }
     }
     BpmNodeDef nodeDef;
     JSONObject nodeConfig;
   }
   
   private static List<AbsFlowParse> getFlowParsers()
   {
     if (BeanUtils.isNotEmpty(bi)) { return bi;
     }
     Map<String, AbsFlowParse> map = com.dstz.base.core.util.AppUtil.getImplInstance(AbsFlowParse.class);
     bi = new java.util.ArrayList(map.values());
     
     return bi;
   }
   
   private static List<AbsNodeParse> getNodeParsers()
   {
     if (BeanUtils.isNotEmpty(bj)) return bj;
     Map<String, AbsNodeParse> map = com.dstz.base.core.util.AppUtil.getImplInstance(AbsNodeParse.class);
     bj = new java.util.ArrayList(map.values());
     
     return bj;
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\parser\BpmProcessDefParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */