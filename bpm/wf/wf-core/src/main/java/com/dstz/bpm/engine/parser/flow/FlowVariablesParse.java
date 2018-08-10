 package com.dstz.bpm.engine.parser.flow;
 
 import com.dstz.bpm.api.model.def.BpmVariableDef;
 import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
 import com.dstz.bpm.engine.model.DefaultBpmVariableDef;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Set;
 import org.springframework.stereotype.Component;
 
 
 
 @Component
 public class FlowVariablesParse
   extends AbsFlowParse<DefaultBpmVariableDef>
 {
   public String getKey()
   {
     return "variableList";
   }
   
   public String validate(Object object)
   {
     List<BpmVariableDef> varList = (List)object;
     
     Set<String> keys = new HashSet();
     for (BpmVariableDef def : varList) {
       String key = def.getKey();
       
       if (keys.contains(key)) {
         throw new RuntimeException("解析流程变量出错：" + key + "在流程中重复！");
       }
       keys.add(def.getKey());
     }
     return "";
   }
   
   public void a(DefaultBpmProcessDef bpmProcessDef, Object object)
   {
     List<BpmVariableDef> varList = (List)object;
     
     bpmProcessDef.setVarList(varList);
   }
   
   public boolean isArray()
   {
     return true;
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\parser\flow\FlowVariablesParse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */