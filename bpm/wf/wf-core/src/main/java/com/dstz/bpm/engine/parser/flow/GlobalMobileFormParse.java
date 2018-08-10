 package com.dstz.bpm.engine.parser.flow;
 
 import com.dstz.bpm.api.model.form.DefaultForm;
 import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
 import org.springframework.stereotype.Component;
 
 
 @Component
 public class GlobalMobileFormParse
   extends AbsFlowParse<DefaultForm>
 {
   public String getKey()
   {
     return "globalMobileForm";
   }
   
 
   public void a(DefaultBpmProcessDef bpmProcessDef, Object object)
   {
     DefaultForm form = (DefaultForm)object;
     bpmProcessDef.setGlobalMobileForm(form);
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\parser\flow\GlobalMobileFormParse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */