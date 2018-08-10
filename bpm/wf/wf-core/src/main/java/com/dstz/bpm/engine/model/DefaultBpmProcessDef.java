/*     */ package com.dstz.bpm.engine.model;
/*     */ 
/*     */ import com.alibaba.fastjson.JSONObject;
/*     */ import com.dstz.base.core.util.BeanUtils;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.bpm.api.constant.NodeType;
/*     */ import com.dstz.bpm.api.engine.plugin.context.BpmPluginContext;
/*     */ import com.dstz.bpm.api.model.def.BpmDataModel;
/*     */ import com.dstz.bpm.api.model.def.BpmDefProperties;
/*     */ import com.dstz.bpm.api.model.def.BpmProcessDef;
/*     */ import com.dstz.bpm.api.model.def.BpmVariableDef;
/*     */ import com.dstz.bpm.api.model.def.NodeInit;
/*     */ import com.dstz.bpm.api.model.form.BpmForm;
/*     */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultBpmProcessDef
/*     */   implements BpmProcessDef
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  29 */   private String A = "";
/*  30 */   private String name = "";
/*  31 */   private String processDefinitionId = "";
/*     */   private List<BpmNodeDef> aT;
/*  33 */   private BpmProcessDef aU = null;
/*     */   
/*  35 */   private List<BpmPluginContext> aV = new ArrayList();
/*     */   
/*  37 */   private List<BpmVariableDef> aW = new ArrayList();
/*     */   
/*  39 */   private List<BpmDataModel> aX = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  44 */   private BpmForm aY = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  49 */   private BpmForm aZ = null;
/*     */   
/*     */ 
/*     */ 
/*  53 */   private BpmForm ba = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  58 */   private BpmForm bb = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  64 */   private BpmDefProperties bc = new BpmDefProperties();
/*     */   
/*  66 */   private List<NodeInit> bd = new ArrayList();
/*     */   private JSONObject be;
/*     */   
/*     */   public BpmProcessDef getParentProcessDef()
/*     */   {
/*  71 */     return this.aU;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BpmDefProperties getExtProperties()
/*     */   {
/*  81 */     return this.bc;
/*     */   }
/*     */   
/*     */   public void setExtProperties(BpmDefProperties extPropertys) {
/*  85 */     this.bc = extPropertys;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<BpmPluginContext> getBpmPluginContexts()
/*     */   {
/*  94 */     return this.aV;
/*     */   }
/*     */   
/*     */   public BpmPluginContext a(Class<?> clazz)
/*     */   {
/*  99 */     List<BpmPluginContext> Plugins = getBpmPluginContexts();
/* 100 */     if (BeanUtils.isEmpty(Plugins)) { return null;
/*     */     }
/* 102 */     for (BpmPluginContext pulgin : Plugins) {
/* 103 */       if (pulgin.getClass().isAssignableFrom(clazz))
/* 104 */         return pulgin;
/*     */     }
/* 106 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<BpmVariableDef> getVariableList()
/*     */   {
/* 115 */     return this.aW;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BpmForm getInstForm()
/*     */   {
/* 124 */     return this.aY;
/*     */   }
/*     */   
/*     */   public BpmForm getInstMobileForm() {
/* 128 */     return this.aZ;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BpmForm getGlobalForm()
/*     */   {
/* 137 */     return this.ba;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public BpmForm getGlobalMobileForm()
/*     */   {
/* 145 */     return this.bb;
/*     */   }
/*     */   
/*     */   public List<BpmPluginContext> getPluginContextList() {
/* 149 */     return this.aV;
/*     */   }
/*     */   
/*     */   public void setPluginContextList(List<BpmPluginContext> pluginContextList) {
/* 153 */     Collections.sort(pluginContextList);
/* 154 */     this.aV = pluginContextList;
/*     */   }
/*     */   
/*     */   public List<BpmVariableDef> getVarList() {
/* 158 */     return this.aW;
/*     */   }
/*     */   
/*     */   public void setVarList(List<BpmVariableDef> varList) {
/* 162 */     this.aW = varList;
/*     */   }
/*     */   
/*     */   public void setInstForm(BpmForm instForm) {
/* 166 */     this.aY = instForm;
/*     */   }
/*     */   
/*     */   public void setInstMobileForm(BpmForm instMobileForm) {
/* 170 */     this.aZ = instMobileForm;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 174 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getName()
/*     */   {
/* 179 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setGlobalForm(BpmForm globalForm) {
/* 183 */     this.ba = globalForm;
/*     */   }
/*     */   
/*     */   public void setGlobalMobileForm(BpmForm globalMobileForm)
/*     */   {
/* 188 */     this.bb = globalMobileForm;
/*     */   }
/*     */   
/*     */ 
/*     */   public String getDefKey()
/*     */   {
/* 194 */     return this.A;
/*     */   }
/*     */   
/*     */   public void setDefKey(String defKey) {
/* 198 */     this.A = defKey;
/*     */   }
/*     */   
/*     */   public void setProcessDefinitionId(String processDefinitionId) {
/* 202 */     this.processDefinitionId = processDefinitionId;
/*     */   }
/*     */   
/*     */   public String getProcessDefinitionId()
/*     */   {
/* 207 */     return this.processDefinitionId;
/*     */   }
/*     */   
/*     */   public void setBpmnNodeDefs(List<BpmNodeDef> bpmnNodeDefs) {
/* 211 */     this.aT = bpmnNodeDefs;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<BpmNodeDef> getBpmnNodeDefs()
/*     */   {
/* 218 */     return this.aT;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public BpmNodeDef getStartEvent()
/*     */   {
/* 225 */     for (BpmNodeDef nodeDef : this.aT) {
/* 226 */       if (nodeDef.getType().equals(NodeType.START)) {
/* 227 */         return nodeDef;
/*     */       }
/*     */     }
/* 230 */     return null;
/*     */   }
/*     */   
/*     */   public List<NodeInit> getNodeInitList() {
/* 234 */     return this.bd;
/*     */   }
/*     */   
/*     */   public List<NodeInit> e(String nodeId) {
/* 238 */     List<NodeInit> initList = new ArrayList();
/* 239 */     for (NodeInit init : this.bd) {
/* 240 */       if ((StringUtil.isNotEmpty(nodeId)) && (init.getNodeId().equals(nodeId))) {
/* 241 */         initList.add(init);
/*     */       }
/*     */     }
/*     */     
/* 245 */     return initList;
/*     */   }
/*     */   
/*     */   public void setNodeInitList(List<NodeInit> nodeInitList)
/*     */   {
/* 250 */     this.bd = nodeInitList;
/*     */   }
/*     */   
/*     */ 
/*     */   public List<BpmNodeDef> getStartNodes()
/*     */   {
/* 256 */     BpmNodeDef startNode = getStartEvent();
/* 257 */     if (startNode == null) return null;
/* 258 */     return startNode.getOutcomeNodes();
/*     */   }
/*     */   
/*     */   public List<BpmNodeDef> getEndEvents()
/*     */   {
/* 263 */     List<BpmNodeDef> rtnList = new ArrayList();
/* 264 */     for (BpmNodeDef nodeDef : this.aT) {
/* 265 */       if (nodeDef.getType().equals(NodeType.END)) {
/* 266 */         rtnList.add(nodeDef);
/*     */       }
/*     */     }
/* 269 */     return rtnList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 274 */   public List<BpmDataModel> getDataModelList() { return this.aX; }
/*     */   
/*     */   public String getDataModelKeys() {
/* 277 */     List<String> keys = new ArrayList();
/* 278 */     for (BpmDataModel model : this.aX) {
/* 279 */       keys.add(model.getCode());
/*     */     }
/* 281 */     return StringUtil.convertCollectionAsString(keys);
/*     */   }
/*     */   
/*     */   public void setDataModelList(List<BpmDataModel> dataModelList) {
/* 285 */     this.aX = dataModelList;
/*     */   }
/*     */   
/*     */   public void setParentProcessDef(DefaultBpmProcessDef processDef)
/*     */   {
/* 290 */     this.aU = processDef;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setJson(JSONObject bpmDefSetting)
/*     */   {
/* 296 */     this.be = bpmDefSetting;
/*     */   }
/*     */   
/*     */   public JSONObject getJson() {
/* 300 */     return this.be;
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\model\DefaultBpmProcessDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */