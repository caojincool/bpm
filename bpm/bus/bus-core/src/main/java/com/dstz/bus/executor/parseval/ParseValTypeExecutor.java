package com.dstz.bus.executor.parseval;

import com.dstz.base.core.util.StringUtil;
import com.dstz.bus.executor.parseval.ParseValExecuteChain;
import com.dstz.bus.executor.parseval.ParseValParam;
import com.dstz.bus.model.BusinessColumn;
import org.springframework.stereotype.Service;

@Service
public class ParseValTypeExecutor extends ParseValExecuteChain {
	public int getSn() {
		return 0;
	}
	@Override
	protected void run(ParseValParam param) {
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

}