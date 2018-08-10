 package com.dstz.bpm.engine.parser.node;
 
 import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
 
 public abstract class AbsNodeParse<T> extends com.dstz.bpm.engine.parser.BaseBpmDefParser<T, com.dstz.bpm.api.model.nodedef.impl.BaseBpmNodeDef>
 {
   public boolean a(BpmNodeDef nodeDef)
   {
     return true;
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\parser\node\AbsNodeParse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */