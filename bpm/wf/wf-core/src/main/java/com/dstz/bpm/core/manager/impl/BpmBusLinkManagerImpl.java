 package com.dstz.bpm.core.manager.impl;
 
 import com.dstz.base.manager.impl.BaseManager;
 import com.dstz.bpm.core.dao.BpmBusLinkDao;
 import com.dstz.bpm.core.manager.BpmBusLinkManager;
 import com.dstz.bpm.core.model.BpmBusLink;
 import java.util.List;
 import javax.annotation.Resource;
 import org.springframework.stereotype.Service;
 
 
 
 
 
 
 @Service("bpmBusLinkManager")
 public class BpmBusLinkManagerImpl
   extends BaseManager<String, BpmBusLink>
   implements BpmBusLinkManager
 {
   @Resource
   BpmBusLinkDao b;
   
   public List<BpmBusLink> getByInstanceId(String instanceId)
   {
     return this.b.getByInstanceId(instanceId);
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\manager\impl\BpmBusLinkManagerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */