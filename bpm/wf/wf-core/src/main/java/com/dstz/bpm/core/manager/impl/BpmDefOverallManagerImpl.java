 package com.dstz.bpm.core.manager.impl;
 
 import com.alibaba.fastjson.JSON;
 import com.dstz.bpm.core.manager.BpmDefOverallManager;
 import com.dstz.bpm.core.manager.BpmDefinitionManager;
 import com.dstz.bpm.core.model.BpmDefinition;
 import com.dstz.bpm.core.model.BpmOverallView;
 import java.util.List;
 import java.util.Map;
 import javax.annotation.Resource;
 import org.springframework.stereotype.Service;
 
 
 
 @Service
 public class BpmDefOverallManagerImpl
   implements BpmDefOverallManager
 {
   @Resource
   BpmDefinitionManager c;
   
   public BpmOverallView getBpmOverallView(String defId)
   {
     BpmDefinition def = (BpmDefinition)this.c.get(defId);
     
     BpmOverallView overallView = new BpmOverallView();
     overallView.setDefId(def.getId());
     overallView.setBpmDefinition(def);
     
     overallView.setDefSetting(JSON.parseObject(def.getDefSetting()));
     
     return overallView;
   }
   
 
 
 
   public void saveBpmOverallView(BpmOverallView overAllView) {}
   
 
 
   public Map<String, List<BpmOverallView>> importPreview(String flowXml)
     throws Exception
   {
     return null;
   }
   
   public void importSave(List<BpmOverallView> overAllView) {}
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\manager\impl\BpmDefOverallManagerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */