/*    */ package com.dstz.bpm.plugin.usercalc.script.runtime;
/*    */ 
/*    */ import com.dstz.base.core.util.BeanUtils;
/*    */ import com.dstz.base.core.util.StringUtil;
/*    */ import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractUserCalcPlugin;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
/*    */ import com.dstz.bpm.plugin.usercalc.script.def.ScriptPluginDef;
/*    */ import com.dstz.sys.api.groovy.IGroovyScriptEngine;
/*    */ import com.dstz.sys.api.model.SysIdentity;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class ScriptPlugin
/*    */   extends AbstractUserCalcPlugin<ScriptPluginDef>
/*    */ {
/*    */   @Resource
/*    */   IGroovyScriptEngine groovyScriptEngine;
/*    */   
/*    */   public List<SysIdentity> a(BpmUserCalcPluginSession pluginSession, ScriptPluginDef def)
/*    */   {
/* 33 */     String script = def.getScript();
/* 34 */     if (StringUtil.isEmpty(script)) {
/* 35 */       return Collections.EMPTY_LIST;
/*    */     }
/*    */     
/* 38 */     Set<SysIdentity> set = (Set)this.groovyScriptEngine.executeObject(script, pluginSession);
/*    */     
/* 40 */     List<SysIdentity> list = new ArrayList();
/* 41 */     if (BeanUtils.isEmpty(set)) { return list;
/*    */     }
/* 43 */     list.addAll(set);
/* 44 */     return list;
/*    */   }
/*    */   
/*    */   public boolean supportPreView()
/*    */   {
/* 49 */     return false;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\usercalc\script\runtime\ScriptPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */