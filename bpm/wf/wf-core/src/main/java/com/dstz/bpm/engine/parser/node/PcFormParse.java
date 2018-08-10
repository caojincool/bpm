 package com.dstz.bpm.engine.parser.node;
 
 import com.dstz.bpm.api.model.form.DefaultForm;
 import com.dstz.bpm.api.model.nodedef.impl.BaseBpmNodeDef;
 import org.springframework.stereotype.Component;
 
 
 @Component
 public class PcFormParse
   extends AbsNodeParse<DefaultForm>
 {
   public String getKey()
   {
     return "form";
   }
   
   public void a(BaseBpmNodeDef userNodeDef, Object object)
   {
     DefaultForm form = (DefaultForm)object;
     userNodeDef.setForm(form);
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\parser\node\PcFormParse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */