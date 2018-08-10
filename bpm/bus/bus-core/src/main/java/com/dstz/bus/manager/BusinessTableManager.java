package com.dstz.bus.manager;

import com.dstz.base.db.tableoper.TableOperator;
import com.dstz.base.manager.Manager;
import com.dstz.bus.model.BusinessTable;

public interface BusinessTableManager extends Manager<String, BusinessTable> {
	void save(BusinessTable var1);

	BusinessTable getByKey(String var1);

	BusinessTable getFilledByKey(String var1);

	TableOperator newTableOperator(BusinessTable var1);
}