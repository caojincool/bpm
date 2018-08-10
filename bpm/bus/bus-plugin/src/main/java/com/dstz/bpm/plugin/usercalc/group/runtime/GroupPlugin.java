/*    */ package com.dstz.bpm.plugin.usercalc.group.runtime;
/*    */ 
/*    */ import com.dstz.base.core.util.StringUtil;
/*    */ import com.dstz.bpm.engine.model.BpmIdentity;
/*    */ import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractUserCalcPlugin;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
/*    */ import com.dstz.bpm.plugin.usercalc.group.def.GroupPluginDef;
/*    */ import com.dstz.org.api.model.IGroup;
/*    */ import com.dstz.org.api.service.GroupService;
/*    */ import com.dstz.sys.api.model.SysIdentity;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class GroupPlugin
/*    */   extends AbstractUserCalcPlugin<GroupPluginDef>
/*    */ {
/*    */   @Resource
/*    */   GroupService ac;
/*    */   
/*    */   public List<SysIdentity> a(BpmUserCalcPluginSession pluginSession, GroupPluginDef def)
/*    */   {
/* 26 */     if (StringUtil.isEmpty(def.getGroupKey())) return null;
/* 27 */     String groupType = def.getType();
/*    */     
/* 29 */     List<SysIdentity> identityList = new ArrayList();
/* 30 */     for (String key : def.getGroupKey().split(",")) {
/* 31 */       if (!StringUtil.isEmpty(key)) {
/* 32 */         IGroup group = this.ac.getByCode(groupType, key);
/* 33 */         if (group != null)
/*    */         {
/* 35 */           identityList.add(new BpmIdentity(group.getGroupId(), group.getName(), group.getGroupType())); }
/*    */       }
/*    */     }
/* 38 */     return identityList;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\usercalc\group\runtime\GroupPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */