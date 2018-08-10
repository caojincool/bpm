/*    */ package com.dstz.bpm.engine.plugin.runtime.abstact;
/*    */ 
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmTaskPluginDef;
/*    */ import com.dstz.bpm.engine.plugin.runtime.BpmTaskPlugin;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmTaskPluginSession;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ public abstract class AbstractBpmTaskPlugin<S extends BpmTaskPluginSession, M extends BpmTaskPluginDef>
/*    */   implements BpmTaskPlugin<S, M>
/*    */ {
/* 13 */   protected static final Logger LOG = LoggerFactory.getLogger(AbstractBpmTaskPlugin.class);
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\plugin\runtime\abstact\AbstractBpmTaskPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */