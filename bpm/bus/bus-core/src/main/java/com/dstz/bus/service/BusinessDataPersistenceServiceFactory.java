package com.dstz.bus.service;

import com.dstz.base.api.exception.BusinessException;
import com.dstz.base.core.util.AppUtil;
import com.dstz.bus.model.BusTableRel;
import com.dstz.bus.model.BusinessData;
import com.dstz.bus.model.BusinessObject;
import com.dstz.bus.service.BusinessDataPersistenceService;
import java.util.Map;
import java.util.Set;

public class BusinessDataPersistenceServiceFactory {
	private BusinessDataPersistenceServiceFactory() {
	}

	private static BusinessDataPersistenceService e(String type) {
		Map map = AppUtil.getImplInstance(BusinessDataPersistenceService.class);
		for (Map.Entry entry : map.entrySet()) {
			if (!((BusinessDataPersistenceService) entry.getValue()).type().equals(type))
				continue;
			return (BusinessDataPersistenceService) entry.getValue();
		}
		throw new BusinessException(String.format("找不到类型[%s]的业务数据持久化的实现类", type));
	}

	public static void saveData(BusinessData businessData) {
		BusinessObject businessObject = businessData.getBusTableRel().getBusObj();
		BusinessDataPersistenceService businessDataPersistenceService = BusinessDataPersistenceServiceFactory
				.e(businessObject.getPersistenceType());
		businessDataPersistenceService.saveData(businessData);
	}

	public static BusinessData loadData(BusinessObject businessObject, Object id) {
		BusinessDataPersistenceService businessDataPersistenceService = BusinessDataPersistenceServiceFactory
				.e(businessObject.getPersistenceType());
		return businessDataPersistenceService.loadData(businessObject, id);
	}

	public static void removeData(BusinessObject businessObject, Object id) {
		BusinessDataPersistenceService businessDataPersistenceService = BusinessDataPersistenceServiceFactory
				.e(businessObject.getPersistenceType());
		businessDataPersistenceService.removeData(businessObject, id);
	}
}