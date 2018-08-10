 package com.dstz.bpm.engine.parser.node;
 
 import com.alibaba.fastjson.JSON;
 import com.alibaba.fastjson.JSONObject;
 import com.dstz.base.core.util.BeanUtils;
 import com.dstz.bpm.api.model.nodedef.Button;
 import com.dstz.bpm.api.model.nodedef.impl.BaseBpmNodeDef;
 import java.util.List;
 import org.springframework.stereotype.Component;
 
 
 
 @Component
 public class ButtonsParse
   extends AbsNodeParse<Button>
 {
   public String getKey()
   {
     return "btnList";
   }
   
   public void a(BaseBpmNodeDef userNodeDef, Object object)
   {
     List<Button> btnList = (List)object;
     
     userNodeDef.setButtons(btnList);
   }
   
   public boolean isArray()
   {
     return true;
   }
   
 
 
 
   public void a(BaseBpmNodeDef userNodeDef, Object object, JSON thisJson)
   {
     JSONObject jsonConfig = (JSONObject)thisJson;
     
     if (BeanUtils.isEmpty(object)) {
       jsonConfig.put("btnList", JSON.toJSON(userNodeDef.getButtons()));
     }
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\parser\node\ButtonsParse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */