package com.dstz.bus.executor.parseval;

import com.dstz.base.core.util.StringUtil;
import com.dstz.bus.executor.parseval.ParseValExecuteChain;
import com.dstz.bus.executor.parseval.ParseValParam;
import com.dstz.bus.model.BusTableRel;
import com.dstz.bus.model.BusinessColumn;
import com.dstz.bus.model.BusinessTable;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ParseValTypeExecutor extends ParseValExecuteChain {
	public int getSn() {
		return 0;
	}

	protected void a(ParseValParam param) {
		String key = param.getKey();
		Object value = param.getValue();
		if (value == null || StringUtil.isEmpty((String) value.toString())) {
			return;
		}
		BusinessColumn column = param.getBusTableRel().getTable().d(key);
		if (column == null) {
			param.getData().put(key, value);
			return;
		}
		param.getData().put(column.getKey(), column.value(value.toString()));
	}

	protected void run(Object object) {
		this.a((ParseValParam) object);
	}
}