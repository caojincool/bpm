 package com.dstz.bpm.engine.plugin.runtime.abstact;
 
 import com.dstz.base.core.util.BeanUtils;
 import com.dstz.bpm.api.constant.ExtractType;
 import com.dstz.bpm.api.engine.plugin.def.BpmTaskPluginDef;
 import com.dstz.bpm.engine.model.BpmIdentity;
 import com.dstz.bpm.engine.plugin.plugindef.AbstractUserCalcPluginDef;
 import com.dstz.bpm.engine.plugin.runtime.BpmUserCalcPlugin;
 import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
 import com.dstz.org.api.model.IUser;
 import com.dstz.org.api.service.UserService;
 import com.dstz.sys.api.model.SysIdentity;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.LinkedHashSet;
 import java.util.List;
 import java.util.Set;
 import javax.annotation.Resource;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public abstract class AbstractUserCalcPlugin<M extends BpmTaskPluginDef>
   implements BpmUserCalcPlugin<M>
 {
   @Resource
   protected UserService bL;
   
   protected abstract List<SysIdentity> queryByPluginDef(BpmUserCalcPluginSession paramBpmUserCalcPluginSession, M paramM);
   
   public List<SysIdentity> execute(BpmUserCalcPluginSession pluginSession, M pluginDef)
   {
     if ((pluginSession.isPreVrewModel().booleanValue()) && 
       (!supportPreView())) { return Collections.emptyList();
     }
     
     List<SysIdentity> list = queryByPluginDef(pluginSession, pluginDef);
     if (BeanUtils.isEmpty(list)) { return list;
     }
     ExtractType extractType = ((AbstractUserCalcPluginDef)pluginDef).getExtract();
     
     Set<SysIdentity> set = new LinkedHashSet();
     List<SysIdentity> rtnList = new ArrayList();
     
 
     list = extract(list, extractType);
     
     set.addAll(list);
     
     rtnList.addAll(set);
     
     return rtnList;
   }
   
 
 
 
 
 
 
 
 
   protected List<SysIdentity> extract(List<SysIdentity> bpmIdentities, ExtractType extractType)
   {
     if (BeanUtils.isEmpty(bpmIdentities)) { return Collections.EMPTY_LIST;
     }
     if (extractType == ExtractType.EXACT_NOEXACT) {
       return bpmIdentities;
     }
     
     return extractBpmIdentity(bpmIdentities);
   }
   
   protected List<SysIdentity> extractBpmIdentity(List<SysIdentity> bpmIdentities) {
     List<SysIdentity> results = new ArrayList();
     for (SysIdentity bpmIdentity : bpmIdentities)
     {
       if ("user".equals(bpmIdentity.getType())) {
         results.add(bpmIdentity);
       }
       else
       {
         List<IUser> users = this.bL.getUserListByGroup(bpmIdentity.getType(), bpmIdentity.getId());
         for (IUser user : users) {
           results.add(new BpmIdentity(user));
         }
       }
     }
     return results;
   }
   
 
 
 
   public boolean supportPreView()
   {
     return true;
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\plugin\runtime\abstact\AbstractUserCalcPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */