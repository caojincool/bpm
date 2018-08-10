 package com.dstz.bpm.engine.parser.flow;
 
 import com.dstz.bpm.api.model.def.BpmDataModel;
 import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Set;
 import org.springframework.stereotype.Component;
 
 
 
 @Component
 public class FlowDataModelParse
   extends AbsFlowParse<BpmDataModel>
 {
   public String getKey()
   {
     return "dataModelList";
   }
   
   public String validate(Object object)
   {
     List<BpmDataModel> list = (List)object;
     
     Set<String> keys = new HashSet();
     for (BpmDataModel def : list) {
       String key = def.getCode();
       
       if (keys.contains(key)) {
         throw new RuntimeException("解析流程数据模型出错code：" + key + "在流程中重复配置！");
       }
       keys.add(def.getCode());
     }
     return "";
   }
   
   public void a(DefaultBpmProcessDef bpmProcessDef, Object object)
   {
     List<BpmDataModel> list = (List)object;
     
     bpmProcessDef.setDataModelList(list);
   }
   
   public boolean isArray()
   {
     return true;
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\parser\flow\FlowDataModelParse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */