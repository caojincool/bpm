 package com.dstz.bpm.engine.data.handle;
 
 import com.dstz.base.api.exception.SystemException;
 import com.dstz.base.core.util.BeanUtils;
 import com.dstz.base.core.util.StringUtil;
 import com.dstz.base.db.dboper.DbOperator;
 import com.dstz.base.db.dboper.DbOperatorFactory;
 import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
 import com.dstz.bpm.api.exception.BpmStatusCode;
 import com.dstz.bpm.api.model.def.BpmDataModel;
 import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
 import com.dstz.bpm.api.service.BpmProcessDefService;
 import com.dstz.bpm.api.service.BpmRightsFormService;
 import com.dstz.bpm.core.manager.BpmBusLinkManager;
 import com.dstz.bpm.core.manager.BpmInstanceManager;
 import com.dstz.bpm.core.model.BpmBusLink;
 import com.dstz.bpm.core.model.BpmInstance;
 import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
 import com.dstz.bus.api.model.IBusTableRel;
 import com.dstz.bus.api.model.IBusinessData;
 import com.dstz.bus.api.model.IBusinessObject;
 import com.dstz.bus.api.model.IBusinessPermission;
 import com.dstz.bus.api.service.IBusinessDataService;
 import com.dstz.bus.api.service.IBusinessObjectService;
 import com.dstz.bus.api.service.IBusinessPermissionService;
 import java.util.Collections;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Map;
 import java.util.Set;
 import javax.annotation.Resource;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 @Component
 public class BpmBusDataHandle
 {
   @Resource
   private BpmInstanceManager f;
   @Resource
   private BpmProcessDefService a;
   @Resource
   private BpmBusLinkManager aG;
   @Resource
   private BpmRightsFormService aI;
   @Resource
   private IBusinessDataService au;
   @Autowired
   private IBusinessObjectService businessObjectService;
   @Autowired
   private IBusinessPermissionService businessPermissionService;
   
   public Map<String, IBusinessData> a(IBusinessPermission businessPermision, BpmInstance instance)
   {
     Map<String, IBusinessData> dataMap = new HashMap();
     
     BpmInstance topInstance = this.f.getTopInstance(instance);
     if (topInstance != null) {
       List<BpmBusLink> topInstanceBusLinks = this.aG.getByInstanceId(topInstance.getId());
       for (BpmBusLink busLink : topInstanceBusLinks) {
         IBusinessObject businessObject = this.businessObjectService.getFilledByKey(busLink.getBizCode());
         businessObject.setPermission(businessPermision.getBusObj(busLink.getBizCode()));
         
         IBusinessData busData = this.au.loadData(businessObject, busLink.getBizId());
         if (busData == null) {
           throw new SystemException(String.format("bizCode[%s] bizId[%s]", new Object[] { busLink.getBizCode(), busLink.getBizId() }), BpmStatusCode.FLOW_BUS_DATA_LOSE);
         }
         dataMap.put(busLink.getBizCode(), busData);
       }
     }
     
 
     List<BpmBusLink> busLinks = this.aG.getByInstanceId(instance.getId());
     for (BpmBusLink busLink : busLinks) {
       businessObject = this.businessObjectService.getFilledByKey(busLink.getBizCode());
       businessObject.setPermission(businessPermision.getBusObj(busLink.getBizCode()));
       IBusinessData busData = this.au.loadData(businessObject, busLink.getBizId());
       if (busData == null) {
         throw new SystemException(String.format("bizCode[%s] bizId[%s]", new Object[] { busLink.getBizCode(), busLink.getBizId() }), BpmStatusCode.FLOW_BUS_DATA_LOSE);
       }
       dataMap.put(busLink.getBizCode(), busData);
     }
     
     IBusinessObject businessObject;
     DefaultBpmProcessDef processDef = (DefaultBpmProcessDef)this.a.getBpmProcessDef(instance.getDefId());
     List<BpmDataModel> listDataModel = processDef.getDataModelList();
     for (BpmDataModel model : listDataModel) {
       String code = model.getCode();
       if (!dataMap.containsKey(code))
       {
 
         IBusinessObject businessObject = this.businessObjectService.getFilledByKey(code);
         businessObject.setPermission(businessPermision.getBusObj(code));
         
         IBusinessData busData = this.au.loadData(businessObject, null);
         dataMap.put(code, busData);
       }
     }
     return dataMap;
   }
   
 
 
 
 
 
   public Map<String, IBusinessData> a(IBusinessPermission businessPermision, String defId)
   {
     Map<String, IBusinessData> dataMap = new HashMap();
     
     DefaultBpmProcessDef processDef = (DefaultBpmProcessDef)this.a.getBpmProcessDef(defId);
     List<BpmDataModel> listDataModel = processDef.getDataModelList();
     for (BpmDataModel model : listDataModel) {
       String code = model.getCode();
       
       IBusinessObject businessObject = this.businessObjectService.getFilledByKey(code);
       businessObject.setPermission(businessPermision.getBusObj(code));
       
       IBusinessData busData = this.au.loadData(businessObject, null);
       dataMap.put(code, busData);
     }
     
     return dataMap;
   }
   
 
 
 
 
 
 
   public void n(BaseActionCmd actionCmd)
   {
     Map<String, IBusinessData> boDataMap = actionCmd.getBizDataMap();
     if (BeanUtils.isEmpty(boDataMap)) {
       return;
     }
     BpmInstance instance = (BpmInstance)actionCmd.getBpmInstance();
     BpmNodeDef startNode = this.a.getStartEvent(instance.getDefId());
     
     IBusinessPermission businessPermision = this.aI.getNodeSavePermission(instance.getDefKey(), startNode.getNodeId(), boDataMap.keySet());
     
 
     BpmInstance topInstance = this.f.getTopInstance(instance);
     Set<String> topModelCodes = new HashSet();
     List<BpmBusLink> topBusLinks; if (topInstance != null) {
       DefaultBpmProcessDef topDef = (DefaultBpmProcessDef)this.a.getBpmProcessDef(topInstance.getDefId());
       topBusLinks = this.aG.getByInstanceId(topInstance.getId());
       
       for (BpmDataModel topModel : topDef.getDataModelList()) {
         String modelCode = topModel.getCode();
         if (boDataMap.containsKey(modelCode)) {
           topModelCodes.add(modelCode);
           
           IBusinessData businessData = (IBusinessData)boDataMap.get(modelCode);
           businessData.getBusTableRel().getBusObj().setPermission(businessPermision.getBusObj(modelCode));
           
           this.au.saveData(businessData);
           
           a(businessData, modelCode, topInstance, topBusLinks);
         }
       }
     }
     
 
     List<BpmBusLink> busLinkList = this.aG.getByInstanceId(instance.getId());
     DefaultBpmProcessDef bpmProcessDef = (DefaultBpmProcessDef)this.a.getBpmProcessDef(instance.getDefId());
     
     for (BpmDataModel dataModel : bpmProcessDef.getDataModelList()) {
       String modelCode = dataModel.getCode();
       
       if ((boDataMap.containsKey(modelCode)) && (!topModelCodes.contains(modelCode))) {
         IBusinessData businessData = (IBusinessData)boDataMap.get(modelCode);
         businessData.getBusTableRel().getBusObj().setPermission(businessPermision.getBusObj(modelCode));
         
         this.au.saveData(businessData);
         
         a(businessData, modelCode, instance, busLinkList);
       }
     }
   }
   
 
 
   private void a(IBusinessData iBusinessData, String modelCode, BpmInstance instance, List<BpmBusLink> busLinks)
   {
     for (BpmBusLink link : busLinks) {
       if (link.getBizId().equals(iBusinessData.getPk())) {
         return;
       }
     }
     
     BpmBusLink busLink = new BpmBusLink();
     busLink.setBizCode(modelCode);
     busLink.setBizId(String.valueOf(iBusinessData.getPk()));
     busLink.setInstId(instance.getId());
     busLink.setDefId(instance.getDefId());
     
     b(modelCode);
     this.aG.create(busLink);
   }
   
 
 
 
   private static int aJ = -1;
   private static Set<String> aK = Collections.synchronizedSet(new HashSet());
   
 
   private static final String tableName = "BPM_BUS_LINK";
   
 
 
   private void b(String partName)
   {
     DbOperator dbOperator = DbOperatorFactory.getLocal();
     
     if (StringUtil.isEmpty(partName)) {
       return;
     }
     if (aJ == -1)
     {
       boolean isSupport = dbOperator.supportPartition("BPM_BUS_LINK");
       aJ = isSupport ? 1 : 0;
     }
     
     if (aJ == 0) {
       return;
     }
     if (aK.contains(partName)) {
       return;
     }
     boolean isPartExist = dbOperator.isExsitPartition("BPM_BUS_LINK", partName);
     if (isPartExist) {
       aK.add(partName);
       return;
     }
     
     dbOperator.createPartition("BPM_BUS_LINK", partName);
     
 
     aK.add(partName);
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\data\handle\BpmBusDataHandle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */