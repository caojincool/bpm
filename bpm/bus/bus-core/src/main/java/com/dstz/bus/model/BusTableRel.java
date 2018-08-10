package com.dstz.bus.model;

import com.dstz.base.core.util.BeanUtils;
import com.dstz.bus.api.model.IBusTableRel;
import com.dstz.bus.api.model.IBusinessObject;
import com.dstz.bus.api.model.IBusinessTable;
import com.dstz.bus.model.BusTableRelFk;
import com.dstz.bus.model.BusinessObject;
import com.dstz.bus.model.BusinessTable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BusTableRel implements IBusTableRel, Serializable {
	private List<BusTableRel> children;
	private String s;
	private String t;
	private String type;
	private List<BusTableRelFk> u;
	private BusinessTable v;
	private BusTableRel w;
	private BusinessObject x;

	public List<BusTableRel> getChildren() {
		if (this.children == null) {
			return Collections.emptyList();
		}
		return this.children;
	}

	public List<IBusTableRel> getChildren(String type) {
		ArrayList<IBusTableRel> list = new ArrayList<IBusTableRel>();
		if (BeanUtils.isNotEmpty(this.children)) {
			for (BusTableRel rel : this.children) {
				if (!type.equals(rel.getType()))
					continue;
				list.add(rel);
			}
		}
		return list;
	}

	public void setChildren(List<BusTableRel> children) {
		this.children = children;
	}

	public String getTableKey() {
		return this.s;
	}

	public void setTableKey(String tableKey) {
		this.s = tableKey;
	}

	public String getTableComment() {
		return this.t;
	}

	public void setTableComment(String tableComment) {
		this.t = tableComment;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<BusTableRelFk> getFks() {
		return this.u;
	}

	public void setFks(List<BusTableRelFk> fks) {
		this.u = fks;
	}

	public BusTableRel a(String tableKey) {
		if (this.s.equals(tableKey)) {
			return this;
		}
		if (this.children != null) {
			for (BusTableRel rel : this.children) {
				BusTableRel r = rel.a(tableKey);
				if (r == null)
					continue;
				return r;
			}
		}
		return null;
	}

	public List<BusTableRel> list() {
		ArrayList<BusTableRel> rels = new ArrayList<BusTableRel>();
		rels.add(this);
		if (this.children != null) {
			for (BusTableRel rel : this.children) {
				rels.addAll(rel.list());
			}
		}
		return rels;
	}

	public BusinessTable getTable() {
		return this.v;
	}

	public void setTable(BusinessTable table) {
		this.v = table;
	}

	public BusTableRel getParent() {
		return this.w;
	}

	public void setParent(BusTableRel parent) {
		this.w = parent;
	}

	public BusinessObject getBusObj() {
		return this.x;
	}

	public void setBusObj(BusinessObject busObj) {
		this.x = busObj;
	}

	public IBusTableRel find(String string) {
		return this.a(string);
	}
}