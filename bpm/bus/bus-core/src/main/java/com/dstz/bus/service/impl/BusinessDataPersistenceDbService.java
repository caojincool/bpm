package com.dstz.bus.service.impl;

import com.dstz.base.core.util.BeanUtils;
import com.dstz.base.db.id.UniqueIdUtil;
import com.dstz.base.db.model.table.Column;
import com.dstz.base.db.tableoper.TableOperator;
import com.dstz.bus.api.constant.BusTableRelFkType;
import com.dstz.bus.api.constant.BusTableRelType;
import com.dstz.bus.api.constant.BusinessObjectPersistenceType;
import com.dstz.bus.manager.BusinessObjectManager;
import com.dstz.bus.manager.BusinessTableManager;
import com.dstz.bus.model.BusTableRel;
import com.dstz.bus.model.BusTableRelFk;
import com.dstz.bus.model.BusinessColumn;
import com.dstz.bus.model.BusinessData;
import com.dstz.bus.model.BusinessObject;
import com.dstz.bus.model.BusinessTable;
import com.dstz.bus.service.BusinessDataPersistenceService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BusinessDataPersistenceDbService implements BusinessDataPersistenceService {
	@Autowired
	BusinessTableManager h;
	@Autowired
	BusinessObjectManager k;

	public String type() {
		return BusinessObjectPersistenceType.DB.getKey();
	}

	public void saveData(BusinessData businessData) {
		Object id;
		TableOperator tableOperator = this.h.newTableOperator(businessData.getBusTableRel().getTable());
		String busTableRelType = businessData.getBusTableRel().getType();
		if (!businessData.getBusTableRel().getBusObj()
				.haveTableDbEditRights(businessData.getBusTableRel().getTableKey())) {
			return;
		}
		if (BusTableRelType.MAIN.equalsWithKey(busTableRelType)) {
			id = businessData.getPk();
			if (BeanUtils.isEmpty((Object) id)) {
				businessData.setPk((Object) UniqueIdUtil.getSuid());
				tableOperator.insertData(businessData.getDbData());
			} else {
				tableOperator.updateData(businessData.getDbData());
			}
		}
		if (BusTableRelType.ONE_TO_ONE.equalsWithKey(busTableRelType)
				|| BusTableRelType.ONE_TO_MANY.equalsWithKey(busTableRelType)) {
			id = businessData.getPk();
			if (BeanUtils.isEmpty((Object) id)) {
				businessData.setPk((Object) UniqueIdUtil.getSuid());
				BusinessData parBusinessData = businessData.getParent();
				for (BusTableRelFk fk : businessData.getBusTableRel().getFks()) {
					if (BusTableRelFkType.FIXED_VALUE.equalsWithKey(fk.getType())) {
						businessData.put(fk.getFrom(), (Object) fk.getValue());
						continue;
					}
					if (BusTableRelFkType.PARENT_FIELD.equalsWithKey(fk.getType())) {
						businessData.put(fk.getFrom(), parBusinessData.get(fk.getValue()));
						continue;
					}
					if (!BusTableRelFkType.CHILD_FIELD.equalsWithKey(fk.getType()))
						continue;
					parBusinessData.put(fk.getValue(), businessData.get(fk.getFrom()));
					this.h.newTableOperator(parBusinessData.getBusTableRel().getTable())
							.updateData(parBusinessData.getDbData());
				}
				tableOperator.insertData(businessData.getDbData());
			} else {
				tableOperator.updateData(businessData.getDbData());
			}
		}
		this.c(businessData);
	}

	private void c(BusinessData businessData) {
		for (BusTableRel rel : businessData.getBusTableRel().getChildren()) {
			if (!rel.getBusObj().haveTableDbEditRights(rel.getTableKey()))
				continue;
			TableOperator tableOperator = this.h.newTableOperator(rel.getTable());
			if (!BusTableRelType.ONE_TO_MANY.equalsWithKey(rel.getType())
					&& !BusTableRelType.ONE_TO_ONE.equalsWithKey(rel.getType()))
				continue;
			HashMap<String, Object> param = new HashMap<String, Object>();
			for (BusTableRelFk fk : rel.getFks()) {
				if (BusTableRelFkType.FIXED_VALUE.equalsWithKey(fk.getType())) {
					param.put(fk.getFrom(), fk.getValue());
					continue;
				}
				if (BusTableRelFkType.PARENT_FIELD.equalsWithKey(fk.getType())) {
					param.put(fk.getFrom(), businessData.get(fk.getValue()));
					continue;
				}
				if (!BusTableRelFkType.CHILD_FIELD.equalsWithKey(fk.getType()))
					continue;
			}
			List oldDatas = new ArrayList();
			if (!param.isEmpty()) {
				oldDatas = tableOperator.selectData(this.a(rel.getTable(), param));
			}
			List children = businessData.getChildren().computeIfAbsent(rel.getTableKey(), k -> new ArrayList());
			block2 : for (Map oldData : oldDatas) {
				Object id = oldData.get(rel.getTable().getPkName());
				for (BusinessData data : children) {
					if (!id.equals(data.getPk()))
						continue;
					continue block2;
				}
				this.a(oldData, rel);
				tableOperator.deleteData(id);
			}
			for (BusinessData data : children) {
				this.saveData(data);
			}
		}
	}

	public BusinessData loadData(BusinessObject businessObject, Object id) {
		BusinessData businessData = new BusinessData();
		BusTableRel busTableRel = businessObject.getRelation();
		businessData.setBusTableRel(busTableRel);
		BusinessTable businessTable = busTableRel.getTable();
		if (BeanUtils.isEmpty((Object) id)) {
			return businessData;
		}
		if (!businessObject.haveTableDbReadRights(busTableRel.getTableKey())) {
			return businessData;
		}
		Map data = this.h.newTableOperator(businessTable).selectData(this.b(busTableRel), id);
		businessData.setDbData(data);
		this.a(businessData, busTableRel);
		return businessData;
	}

	private void a(BusinessData businessData, BusTableRel busTableRel) {
		for (BusTableRel rel : busTableRel.getChildren()) {
			Object fk2;
			BusinessTable table = rel.getTable();
			if (!rel.getBusObj().haveTableDbReadRights(rel.getTableKey())) {
				return;
			}
			HashMap<String, Object> param = new HashMap<String, Object>();
			for (Object fk2 : rel.getFks()) {
				if (BusTableRelFkType.FIXED_VALUE.equalsWithKey(fk2.getType())) {
					param.put(fk2.getFrom(), fk2.getValue());
					continue;
				}
				if (BusTableRelFkType.PARENT_FIELD.equalsWithKey(fk2.getType())) {
					param.put(fk2.getFrom(), businessData.get(fk2.getValue()));
					continue;
				}
				if (!BusTableRelFkType.CHILD_FIELD.equalsWithKey(fk2.getType()))
					continue;
				param.put(fk2.getFrom(), businessData.get(fk2.getValue()));
			}
			List dataMapList = this.h.newTableOperator(table).selectData(this.b(rel), this.a(table, param));
			fk2 = dataMapList.iterator();
			while (fk2.hasNext()) {
				Map dataMap = (Map) fk2.next();
				BusinessData data = new BusinessData();
				data.setBusTableRel(rel);
				data.setParent(businessData);
				data.setDbData(dataMap);
				businessData.a(data);
				this.a(data, rel);
			}
		}
	}

	public void removeData(BusinessObject businessObject, Object id) {
		BusTableRel busTableRel = businessObject.getRelation();
		if (!busTableRel.getBusObj().haveTableDbEditRights(busTableRel.getTableKey())) {
			return;
		}
		Map data = this.h.newTableOperator(busTableRel.getTable()).selectData(id);
		this.h.newTableOperator(busTableRel.getTable()).deleteData(data.get(busTableRel.getTable().getPkName()));
		this.a(data, busTableRel);
	}

	private void a(Map<String, Object> dbData, BusTableRel busTableRel) {
		for (BusTableRel rel : busTableRel.getChildren()) {
			Object fk2;
			if (!rel.getBusObj().haveTableDbEditRights(rel.getTableKey()))
				continue;
			HashMap<String, Object> param = new HashMap<String, Object>();
			Map<String, Object> data = this.b(busTableRel.getTable(), dbData);
			for (Object fk2 : rel.getFks()) {
				if (BusTableRelFkType.FIXED_VALUE.equalsWithKey(fk2.getType())) {
					param.put(fk2.getFrom(), fk2.getValue());
					continue;
				}
				if (!BusTableRelFkType.PARENT_FIELD.equalsWithKey(fk2.getType()))
					continue;
				param.put(fk2.getFrom(), data.get(fk2.getValue()));
			}
			if (rel.getChildren().isEmpty()) {
				this.h.newTableOperator(rel.getTable()).deleteData(this.a(rel.getTable(), param));
				continue;
			}
			List dataMapList = this.h.newTableOperator(rel.getTable()).selectData(this.a(rel.getTable(), param));
			fk2 = dataMapList.iterator();
			while (fk2.hasNext()) {
				Map dataMap = (Map) fk2.next();
				this.a(dataMap, rel);
			}
		}
	}

	private List<String> b(BusTableRel busTableRel) {
		ArrayList<String> columnName = new ArrayList<String>();
		for (BusinessColumn column : busTableRel.getTable().getColumns()) {
			if (!busTableRel.getBusObj().haveColumnDbReadRights(busTableRel.getTableKey(), column.getKey()))
				continue;
			columnName.add(column.getName());
		}
		return columnName;
	}

	private Map<String, Object> a(BusinessTable table, Map<String, Object> map) {
		HashMap<String, Object> dbData = new HashMap<String, Object>();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String columnName = table.d(entry.getKey()).getName();
			dbData.put(columnName, entry.getValue());
		}
		return dbData;
	}

	private Map<String, Object> b(BusinessTable table, Map<String, Object> map) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String columnKey = ((BusinessColumn) table.getColumn(entry.getKey())).getKey();
			data.put(columnKey, entry.getValue());
		}
		return data;
	}
}