package com.dstz.bpm.engine.parser.node;

import com.dstz.bpm.api.engine.plugin.def.BpmDef;
import com.dstz.bpm.api.model.form.BpmForm;
import com.dstz.bpm.api.model.form.DefaultForm;
import com.dstz.bpm.api.model.nodedef.impl.BaseBpmNodeDef;
import com.dstz.bpm.engine.parser.node.AbsNodeParse;
import org.springframework.stereotype.Component;

@Component
public class MobileFormParse extends AbsNodeParse<DefaultForm> {
	public String getKey() {
		return "mobileForm";
	}

	public void setDefParam(BaseBpmNodeDef userNodeDef, Object object) {
		DefaultForm form = (DefaultForm) object;
		userNodeDef.setMobileForm((BpmForm) form);
	}

//	public void setDefParam(BpmDef bpmDef, Object object) {
//		this.a((BaseBpmNodeDef) bpmDef, object);
//	}
}