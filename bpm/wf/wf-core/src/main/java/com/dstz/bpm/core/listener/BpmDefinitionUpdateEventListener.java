 package com.dstz.bpm.core.listener;
 
 import com.dstz.bpm.act.cache.ActivitiDefCache;
 import com.dstz.bpm.api.engine.event.BpmDefinitionUpdateEvent;
 import com.dstz.bpm.api.service.BpmProcessDefService;
 import com.dstz.bpm.core.model.BpmDefinition;
 import javax.annotation.Resource;
 import org.springframework.context.ApplicationListener;
 import org.springframework.stereotype.Component;
 
 @Component
 public class BpmDefinitionUpdateEventListener implements ApplicationListener<BpmDefinitionUpdateEvent>
 {
   @Resource
   BpmProcessDefService a;
   
   public void a(BpmDefinitionUpdateEvent event)
   {
     BpmDefinition bpmDef = (BpmDefinition)event.getSource();
     
     this.a.clean(bpmDef.getId());
     ActivitiDefCache.clearByDefId(bpmDef.getActDefId());
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\listener\BpmDefinitionUpdateEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */