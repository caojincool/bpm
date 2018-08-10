/*     */ package com.dstz.bpm.engine.data.handle;
/*     */ 
/*     */ import com.dstz.base.api.exception.SystemException;
/*     */ import com.dstz.base.core.util.BeanUtils;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.base.db.dboper.DbOperator;
/*     */ import com.dstz.base.db.dboper.DbOperatorFactory;
/*     */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*     */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*     */ import com.dstz.bpm.api.model.def.BpmDataModel;
/*     */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*     */ import com.dstz.bpm.api.service.BpmProcessDefService;
/*     */ import com.dstz.bpm.api.service.BpmRightsFormService;
/*     */ import com.dstz.bpm.core.manager.BpmBusLinkManager;
/*     */ import com.dstz.bpm.core.manager.BpmInstanceManager;
/*     */ import com.dstz.bpm.core.model.BpmBusLink;
/*     */ import com.dstz.bpm.core.model.BpmInstance;
/*     */ import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
/*     */ import com.dstz.bus.api.model.IBusTableRel;
/*     */ import com.dstz.bus.api.model.IBusinessData;
/*     */ import com.dstz.bus.api.model.IBusinessObject;
/*     */ import com.dstz.bus.api.model.IBusinessPermission;
/*     */ import com.dstz.bus.api.service.IBusinessDataService;
/*     */ import com.dstz.bus.api.service.IBusinessObjectService;
/*     */ import com.dstz.bus.api.service.IBusinessPermissionService;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Resource;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ public class BpmBusDataHandle
/*     */ {
/*     */   @Resource
/*     */   private BpmInstanceManager f;
/*     */   @Resource
/*     */   private BpmProcessDefService a;
/*     */   @Resource
/*     */   private BpmBusLinkManager aG;
/*     */   @Resource
/*     */   private BpmRightsFormService aI;
/*     */   @Resource
/*     */   private IBusinessDataService au;
/*     */   @Autowired
/*     */   private IBusinessObjectService businessObjectService;
/*     */   @Autowired
/*     */   private IBusinessPermissionService businessPermissionService;
/*     */   
/*     */   public Map<String, IBusinessData> a(IBusinessPermission businessPermision, BpmInstance instance)
/*     */   {
/*  83 */     Map<String, IBusinessData> dataMap = new HashMap();
/*     */     
/*  85 */     BpmInstance topInstance = this.f.getTopInstance(instance);
/*  86 */     if (topInstance != null) {
/*  87 */       List<BpmBusLink> topInstanceBusLinks = this.aG.getByInstanceId(topInstance.getId());
/*  88 */       for (BpmBusLink busLink : topInstanceBusLinks) {
/*  89 */         IBusinessObject businessObject = this.businessObjectService.getFilledByKey(busLink.getBizCode());
/*  90 */         businessObject.setPermission(businessPermision.getBusObj(busLink.getBizCode()));
/*     */         
/*  92 */         IBusinessData busData = this.au.loadData(businessObject, busLink.getBizId());
/*  93 */         if (busData == null) {
/*  94 */           throw new SystemException(String.format("bizCode[%s] bizId[%s]", new Object[] { busLink.getBizCode(), busLink.getBizId() }), BpmStatusCode.FLOW_BUS_DATA_LOSE);
/*     */         }
/*  96 */         dataMap.put(busLink.getBizCode(), busData);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 101 */     List<BpmBusLink> busLinks = this.aG.getByInstanceId(instance.getId());
/* 102 */     for (BpmBusLink busLink : busLinks) {
/* 103 */       businessObject = this.businessObjectService.getFilledByKey(busLink.getBizCode());
/* 104 */       businessObject.setPermission(businessPermision.getBusObj(busLink.getBizCode()));
/* 105 */       IBusinessData busData = this.au.loadData(businessObject, busLink.getBizId());
/* 106 */       if (busData == null) {
/* 107 */         throw new SystemException(String.format("bizCode[%s] bizId[%s]", new Object[] { busLink.getBizCode(), busLink.getBizId() }), BpmStatusCode.FLOW_BUS_DATA_LOSE);
/*     */       }
/* 109 */       dataMap.put(busLink.getBizCode(), busData);
/*     */     }
/*     */     
/*     */     IBusinessObject businessObject;
/* 113 */     DefaultBpmProcessDef processDef = (DefaultBpmProcessDef)this.a.getBpmProcessDef(instance.getDefId());
/* 114 */     List<BpmDataModel> listDataModel = processDef.getDataModelList();
/* 115 */     for (BpmDataModel model : listDataModel) {
/* 116 */       String code = model.getCode();
/* 117 */       if (!dataMap.containsKey(code))
/*     */       {
/*     */ 
/* 120 */         IBusinessObject businessObject = this.businessObjectService.getFilledByKey(code);
/* 121 */         businessObject.setPermission(businessPermision.getBusObj(code));
/*     */         
/* 123 */         IBusinessData busData = this.au.loadData(businessObject, null);
/* 124 */         dataMap.put(code, busData);
/*     */       }
/*     */     }
/* 127 */     return dataMap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Map<String, IBusinessData> a(IBusinessPermission businessPermision, String defId)
/*     */   {
/* 137 */     Map<String, IBusinessData> dataMap = new HashMap();
/*     */     
/* 139 */     DefaultBpmProcessDef processDef = (DefaultBpmProcessDef)this.a.getBpmProcessDef(defId);
/* 140 */     List<BpmDataModel> listDataModel = processDef.getDataModelList();
/* 141 */     for (BpmDataModel model : listDataModel) {
/* 142 */       String code = model.getCode();
/*     */       
/* 144 */       IBusinessObject businessObject = this.businessObjectService.getFilledByKey(code);
/* 145 */       businessObject.setPermission(businessPermision.getBusObj(code));
/*     */       
/* 147 */       IBusinessData busData = this.au.loadData(businessObject, null);
/* 148 */       dataMap.put(code, busData);
/*     */     }
/*     */     
/* 151 */     return dataMap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void n(BaseActionCmd actionCmd)
/*     */   {
/* 162 */     Map<String, IBusinessData> boDataMap = actionCmd.getBizDataMap();
/* 163 */     if (BeanUtils.isEmpty(boDataMap)) {
/* 164 */       return;
/*     */     }
/* 166 */     BpmInstance instance = (BpmInstance)actionCmd.getBpmInstance();
/* 167 */     BpmNodeDef startNode = this.a.getStartEvent(instance.getDefId());
/*     */     
/* 169 */     IBusinessPermission businessPermision = this.aI.getNodeSavePermission(instance.getDefKey(), startNode.getNodeId(), boDataMap.keySet());
/*     */     
/*     */ 
/* 172 */     BpmInstance topInstance = this.f.getTopInstance(instance);
/* 173 */     Set<String> topModelCodes = new HashSet();
/* 174 */     List<BpmBusLink> topBusLinks; if (topInstance != null) {
/* 175 */       DefaultBpmProcessDef topDef = (DefaultBpmProcessDef)this.a.getBpmProcessDef(topInstance.getDefId());
/* 176 */       topBusLinks = this.aG.getByInstanceId(topInstance.getId());
/*     */       
/* 178 */       for (BpmDataModel topModel : topDef.getDataModelList()) {
/* 179 */         String modelCode = topModel.getCode();
/* 180 */         if (boDataMap.containsKey(modelCode)) {
/* 181 */           topModelCodes.add(modelCode);
/*     */           
/* 183 */           IBusinessData businessData = (IBusinessData)boDataMap.get(modelCode);
/* 184 */           businessData.getBusTableRel().getBusObj().setPermission(businessPermision.getBusObj(modelCode));
/*     */           
/* 186 */           this.au.saveData(businessData);
/*     */           
/* 188 */           a(businessData, modelCode, topInstance, topBusLinks);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 194 */     List<BpmBusLink> busLinkList = this.aG.getByInstanceId(instance.getId());
/* 195 */     DefaultBpmProcessDef bpmProcessDef = (DefaultBpmProcessDef)this.a.getBpmProcessDef(instance.getDefId());
/*     */     
/* 197 */     for (BpmDataModel dataModel : bpmProcessDef.getDataModelList()) {
/* 198 */       String modelCode = dataModel.getCode();
/*     */       
/* 200 */       if ((boDataMap.containsKey(modelCode)) && (!topModelCodes.contains(modelCode))) {
/* 201 */         IBusinessData businessData = (IBusinessData)boDataMap.get(modelCode);
/* 202 */         businessData.getBusTableRel().getBusObj().setPermission(businessPermision.getBusObj(modelCode));
/*     */         
/* 204 */         this.au.saveData(businessData);
/*     */         
/* 206 */         a(businessData, modelCode, instance, busLinkList);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void a(IBusinessData iBusinessData, String modelCode, BpmInstance instance, List<BpmBusLink> busLinks)
/*     */   {
/* 215 */     for (BpmBusLink link : busLinks) {
/* 216 */       if (link.getBizId().equals(iBusinessData.getPk())) {
/* 217 */         return;
/*     */       }
/*     */     }
/*     */     
/* 221 */     BpmBusLink busLink = new BpmBusLink();
/* 222 */     busLink.setBizCode(modelCode);
/* 223 */     busLink.setBizId(String.valueOf(iBusinessData.getPk()));
/* 224 */     busLink.setInstId(instance.getId());
/* 225 */     busLink.setDefId(instance.getDefId());
/*     */     
/* 227 */     b(modelCode);
/* 228 */     this.aG.create(busLink);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 234 */   private static int aJ = -1;
/* 235 */   private static Set<String> aK = Collections.synchronizedSet(new HashSet());
/*     */   
/*     */ 
/*     */   private static final String tableName = "BPM_BUS_LINK";
/*     */   
/*     */ 
/*     */ 
/*     */   private void b(String partName)
/*     */   {
/* 244 */     DbOperator dbOperator = DbOperatorFactory.getLocal();
/*     */     
/* 246 */     if (StringUtil.isEmpty(partName)) {
/* 247 */       return;
/*     */     }
/* 249 */     if (aJ == -1)
/*     */     {
/* 251 */       boolean isSupport = dbOperator.supportPartition("BPM_BUS_LINK");
/* 252 */       aJ = isSupport ? 1 : 0;
/*     */     }
/*     */     
/* 255 */     if (aJ == 0) {
/* 256 */       return;
/*     */     }
/* 258 */     if (aK.contains(partName)) {
/* 259 */       return;
/*     */     }
/* 261 */     boolean isPartExist = dbOperator.isExsitPartition("BPM_BUS_LINK", partName);
/* 262 */     if (isPartExist) {
/* 263 */       aK.add(partName);
/* 264 */       return;
/*     */     }
/*     */     
/* 267 */     dbOperator.createPartition("BPM_BUS_LINK", partName);
/*     */     
/*     */ 
/* 270 */     aK.add(partName);
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\data\handle\BpmBusDataHandle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */