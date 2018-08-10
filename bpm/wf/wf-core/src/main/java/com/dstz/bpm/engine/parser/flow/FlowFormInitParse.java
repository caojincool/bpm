 package com.dstz.bpm.engine.parser.flow;
 
 import com.dstz.bpm.api.model.def.NodeInit;
 import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
 import java.util.List;
 import org.springframework.stereotype.Component;
 
 
 
 @Component
 public class FlowFormInitParse
   extends AbsFlowParse<NodeInit>
 {
   public String getKey()
   {
     return "nodeInitList";
   }
   
 
   public void a(DefaultBpmProcessDef bpmProcessDef, Object object)
   {
     List<NodeInit> list = (List)object;
     bpmProcessDef.setNodeInitList(list);
   }
   
   public boolean isArray()
   {
     return true;
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\parser\flow\FlowFormInitParse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */