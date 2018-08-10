/*    */ package com.dstz.bpm.plugin.execution.script.plugin;
/*    */ 
/*    */ import com.dstz.base.core.util.StringUtil;
/*    */ import com.dstz.bpm.api.constant.EventType;
/*    */ import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractBpmExecutionPlugin;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmExecutionPluginSession;
/*    */ import com.dstz.bpm.plugin.execution.script.def.NodeScriptPluginDef;
/*    */ import com.dstz.sys.api.groovy.IGroovyScriptEngine;
/*    */ import javax.annotation.Resource;
/*    */ import org.slf4j.Logger;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class NodeScriptPlugin
/*    */   extends AbstractBpmExecutionPlugin<BpmExecutionPluginSession, NodeScriptPluginDef>
/*    */ {
/*    */   @Resource
/*    */   IGroovyScriptEngine groovyScriptEngine;
/*    */   
/*    */   public Void a(BpmExecutionPluginSession pluginSession, NodeScriptPluginDef pluginDef)
/*    */   {
/* 27 */     String script = pluginDef.a(pluginSession.getEventType());
/* 28 */     if (StringUtil.isEmpty(script)) { return null;
/*    */     }
/* 30 */     this.groovyScriptEngine.execute(script, pluginSession);
/*    */     
/* 32 */     this.LOG.info("节点{}执行了{}事件脚本", pluginDef.getNodeId(), pluginSession.getEventType().getValue());
/* 33 */     return null;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\execution\script\plugin\NodeScriptPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */