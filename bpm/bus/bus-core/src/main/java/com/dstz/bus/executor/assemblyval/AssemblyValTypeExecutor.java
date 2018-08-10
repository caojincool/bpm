package com.dstz.bus.executor.assemblyval;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dstz.base.api.constant.ColumnType;
import com.dstz.base.core.util.time.DateFormatUtil;
import com.dstz.bus.executor.assemblyval.AssemblyValExecuteChain;
import com.dstz.bus.executor.assemblyval.AssemblyValParam;
import com.dstz.bus.model.BusColumnCtrl;
import com.dstz.bus.model.BusTableRel;
import com.dstz.bus.model.BusinessColumn;
import com.dstz.bus.model.BusinessData;
import com.dstz.bus.model.BusinessTable;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class AssemblyValTypeExecutor extends AssemblyValExecuteChain {
	public int getSn() {
		return 0;
	}

	protected void a(AssemblyValParam param) {
		BusinessData businessData = param.getBusinessData();
		JSONObject data = param.getData();
		for (Map.Entry entry : businessData.getData().entrySet()) {
			BusinessColumn column = businessData.getBusTableRel().getTable().d((String) entry.getKey());
			if (ColumnType.DATE.equalsWithKey(column.getType()) && entry.getValue() != null) {
				JSONObject config = JSON.parseObject((String) column.getCtrl().getConfig());
				data.put(column.getKey(), (Object) DateFormatUtil.format((Date) ((Date) entry.getValue()),
						(String) config.getString("format")));
				continue;
			}
			data.put((String) entry.getKey(), entry.getValue());
		}
	}

	protected void run(Object object) {
		this.a((AssemblyValParam) object);
	}
}