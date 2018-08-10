 package com.dstz.bus.service;
 
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import com.dstz.base.core.executor.ExecutorFactory;
 import com.dstz.base.core.util.BeanUtils;
 import com.dstz.bus.api.constant.BusTableRelType;
 import com.dstz.bus.api.model.IBusinessData;
 import com.dstz.bus.api.model.IBusinessObject;
 import com.dstz.bus.api.model.IBusinessPermission;
 import com.dstz.bus.api.service.IBusinessDataService;
 import com.dstz.bus.executor.assemblyval.AssemblyValExecuteChain;
 import com.dstz.bus.executor.assemblyval.AssemblyValParam;
 import com.dstz.bus.executor.parseval.ParseValExecuteChain;
 import com.dstz.bus.executor.parseval.ParseValParam;
 import com.dstz.bus.manager.BusinessObjectManager;
 import com.dstz.bus.model.BusTableRel;
 import com.dstz.bus.model.BusinessData;
 import com.dstz.bus.model.BusinessObject;
 import com.dstz.bus.model.BusinessTable;
 import com.dstz.bus.model.permission.BusObjPermission;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.Map.Entry;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 @Service
 public class BusinessDataService implements IBusinessDataService
 {
   @Autowired
   BusinessObjectManager k;
   
   public void saveFormDefData(JSONObject data, IBusinessPermission businessPermission)
   {
     for (Map.Entry<String, Object> entry : data.entrySet()) {
       String boKey = (String)entry.getKey();
       JSONObject boData = (JSONObject)entry.getValue();
       BusinessData businessData = (BusinessData)parseBusinessData(boData, boKey);
       businessData.getBusTableRel().getBusObj().setPermission((BusObjPermission)businessPermission.getBusObj(boKey));
       BusinessDataPersistenceServiceFactory.saveData(businessData);
     }
   }
   
   public JSONObject getFormDefData(IBusinessObject businessObject, Object id)
   {
     BusinessData businessData = BusinessDataPersistenceServiceFactory.loadData((BusinessObject)businessObject, id);
     if (BeanUtils.isEmpty(id)) {
       b(businessData);
     }
     
     JSONObject data = new JSONObject();
     
     a(data, businessData);
     return data;
   }
   
 
 
 
 
 
 
 
   private void b(BusinessData businessData)
   {
     BusTableRel busTableRel = businessData.getBusTableRel();
     businessData.setDbData(busTableRel.getTable().initDbData());
     
 
     for (BusTableRel rel : busTableRel.getChildren()) {
       if (BusTableRelType.ONE_TO_ONE.equalsWithKey(rel.getType())) {
         BusinessData childData = new BusinessData();
         childData.setBusTableRel(rel);
         b(childData);
         businessData.a(childData);
       }
     }
   }
   
 
   public JSONObject assemblyFormDefData(IBusinessData businessData)
   {
     JSONObject data = new JSONObject();
     a(data, businessData);
     return data;
   }
   
 
 
 
 
 
 
 
   private void a(JSONObject data, IBusinessData ibusinessData)
   {
     BusinessData businessData = (BusinessData)ibusinessData;
     
     AssemblyValParam param = new AssemblyValParam(data, businessData);
     ExecutorFactory.execute(AssemblyValExecuteChain.class, param);
     
 
     for (Map.Entry<String, List<BusinessData>> entry : businessData.getChildren().entrySet()) {
       String tableKey = (String)entry.getKey();
       List<BusinessData> children = (List)entry.getValue();
       if (BusTableRelType.ONE_TO_ONE.equalsWithKey(((BusinessData)children.get(0)).getBusTableRel().getType())) {
         JSONObject cData = new JSONObject();
         a(cData, (IBusinessData)children.get(0));
         data.put(tableKey, cData);
       } else {
         JSONArray dataList = new JSONArray();
         for (BusinessData bd : children) {
           JSONObject cData = new JSONObject();
           a(cData, bd);
           dataList.add(cData);
         }
         data.put(tableKey + "List", dataList);
       }
     }
   }
   
 
 
 
 
 
 
 
 
 
   private BusinessData a(Object object, BusTableRel relation)
   {
     BusinessData businessData = new BusinessData();
     businessData.setBusTableRel(relation);
     
     if ((object instanceof JSONObject)) {
       JSONObject jsonObject = (JSONObject)object;
       Map<String, Object> bdData = new HashMap();
       for (Map.Entry<String, Object> enty : jsonObject.entrySet())
       {
         if ((!(enty.getValue() instanceof JSONArray)) && (!(enty.getValue() instanceof JSONObject))) {
           ParseValParam param = new ParseValParam((String)enty.getKey(), enty.getValue(), bdData, relation);
           ExecutorFactory.execute(ParseValExecuteChain.class, param);
         }
         
         BusTableRel rel;
         if ((enty.getValue() instanceof JSONArray)) {
           String tableKey = ((String)enty.getKey()).substring(0, ((String)enty.getKey()).length() - 4);
           rel = relation.a(tableKey);
           for (Object obj : (JSONArray)enty.getValue()) {
             businessData.a(a(obj, rel));
           }
         }
         
         if ((enty.getValue() instanceof JSONObject)) {
           BusTableRel rel = relation.a((String)enty.getKey());
           businessData.a(a(enty.getValue(), rel));
         }
       }
       businessData.setData(bdData);
     }
     return businessData;
   }
   
   public void removeData(IBusinessObject businessObject, Object id)
   {
     BusinessDataPersistenceServiceFactory.removeData((BusinessObject)businessObject, id);
   }
   
   public void saveData(IBusinessData businessData)
   {
     BusinessDataPersistenceServiceFactory.saveData((BusinessData)businessData);
   }
   
   public IBusinessData loadData(IBusinessObject businessObject, Object id)
   {
     return BusinessDataPersistenceServiceFactory.loadData((BusinessObject)businessObject, id);
   }
   
   public IBusinessData parseBusinessData(JSONObject jsonObject, String boKey)
   {
     BusinessObject businessObject = this.k.getFilledByKey(boKey);
     return a(jsonObject, businessObject.getRelation());
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\service\BusinessDataService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */