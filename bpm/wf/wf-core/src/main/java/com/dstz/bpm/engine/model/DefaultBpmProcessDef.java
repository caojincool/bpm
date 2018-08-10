 package com.dstz.bpm.engine.model;
 
 import com.alibaba.fastjson.JSONObject;
 import com.dstz.base.core.util.BeanUtils;
 import com.dstz.base.core.util.StringUtil;
 import com.dstz.bpm.api.constant.NodeType;
 import com.dstz.bpm.api.engine.plugin.context.BpmPluginContext;
 import com.dstz.bpm.api.model.def.BpmDataModel;
 import com.dstz.bpm.api.model.def.BpmDefProperties;
 import com.dstz.bpm.api.model.def.BpmProcessDef;
 import com.dstz.bpm.api.model.def.BpmVariableDef;
 import com.dstz.bpm.api.model.def.NodeInit;
 import com.dstz.bpm.api.model.form.BpmForm;
 import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.List;
 
 
 
 
 
 
 
 public class DefaultBpmProcessDef
   implements BpmProcessDef
 {
   private static final long serialVersionUID = 1L;
   private String A = "";
   private String name = "";
   private String processDefinitionId = "";
   private List<BpmNodeDef> aT;
   private BpmProcessDef aU = null;
   
   private List<BpmPluginContext> aV = new ArrayList();
   
   private List<BpmVariableDef> aW = new ArrayList();
   
   private List<BpmDataModel> aX = new ArrayList();
   
 
 
 
   private BpmForm aY = null;
   
 
 
 
   private BpmForm aZ = null;
   
 
 
   private BpmForm ba = null;
   
 
 
 
   private BpmForm bb = null;
   
 
 
 
 
   private BpmDefProperties bc = new BpmDefProperties();
   
   private List<NodeInit> bd = new ArrayList();
   private JSONObject be;
   
   public BpmProcessDef getParentProcessDef()
   {
     return this.aU;
   }
   
 
 
 
 
 
   public BpmDefProperties getExtProperties()
   {
     return this.bc;
   }
   
   public void setExtProperties(BpmDefProperties extPropertys) {
     this.bc = extPropertys;
   }
   
 
 
 
 
   public List<BpmPluginContext> getBpmPluginContexts()
   {
     return this.aV;
   }
   
   public BpmPluginContext a(Class<?> clazz)
   {
     List<BpmPluginContext> Plugins = getBpmPluginContexts();
     if (BeanUtils.isEmpty(Plugins)) { return null;
     }
     for (BpmPluginContext pulgin : Plugins) {
       if (pulgin.getClass().isAssignableFrom(clazz))
         return pulgin;
     }
     return null;
   }
   
 
 
 
 
   public List<BpmVariableDef> getVariableList()
   {
     return this.aW;
   }
   
 
 
 
 
   public BpmForm getInstForm()
   {
     return this.aY;
   }
   
   public BpmForm getInstMobileForm() {
     return this.aZ;
   }
   
 
 
 
 
   public BpmForm getGlobalForm()
   {
     return this.ba;
   }
   
 
 
 
   public BpmForm getGlobalMobileForm()
   {
     return this.bb;
   }
   
   public List<BpmPluginContext> getPluginContextList() {
     return this.aV;
   }
   
   public void setPluginContextList(List<BpmPluginContext> pluginContextList) {
     Collections.sort(pluginContextList);
     this.aV = pluginContextList;
   }
   
   public List<BpmVariableDef> getVarList() {
     return this.aW;
   }
   
   public void setVarList(List<BpmVariableDef> varList) {
     this.aW = varList;
   }
   
   public void setInstForm(BpmForm instForm) {
     this.aY = instForm;
   }
   
   public void setInstMobileForm(BpmForm instMobileForm) {
     this.aZ = instMobileForm;
   }
   
   public void setName(String name) {
     this.name = name;
   }
   
   public String getName()
   {
     return this.name;
   }
   
   public void setGlobalForm(BpmForm globalForm) {
     this.ba = globalForm;
   }
   
   public void setGlobalMobileForm(BpmForm globalMobileForm)
   {
     this.bb = globalMobileForm;
   }
   
 
   public String getDefKey()
   {
     return this.A;
   }
   
   public void setDefKey(String defKey) {
     this.A = defKey;
   }
   
   public void setProcessDefinitionId(String processDefinitionId) {
     this.processDefinitionId = processDefinitionId;
   }
   
   public String getProcessDefinitionId()
   {
     return this.processDefinitionId;
   }
   
   public void setBpmnNodeDefs(List<BpmNodeDef> bpmnNodeDefs) {
     this.aT = bpmnNodeDefs;
   }
   
 
 
   public List<BpmNodeDef> getBpmnNodeDefs()
   {
     return this.aT;
   }
   
 
 
   public BpmNodeDef getStartEvent()
   {
     for (BpmNodeDef nodeDef : this.aT) {
       if (nodeDef.getType().equals(NodeType.START)) {
         return nodeDef;
       }
     }
     return null;
   }
   
   public List<NodeInit> getNodeInitList() {
     return this.bd;
   }
   
   public List<NodeInit> e(String nodeId) {
     List<NodeInit> initList = new ArrayList();
     for (NodeInit init : this.bd) {
       if ((StringUtil.isNotEmpty(nodeId)) && (init.getNodeId().equals(nodeId))) {
         initList.add(init);
       }
     }
     
     return initList;
   }
   
   public void setNodeInitList(List<NodeInit> nodeInitList)
   {
     this.bd = nodeInitList;
   }
   
 
   public List<BpmNodeDef> getStartNodes()
   {
     BpmNodeDef startNode = getStartEvent();
     if (startNode == null) return null;
     return startNode.getOutcomeNodes();
   }
   
   public List<BpmNodeDef> getEndEvents()
   {
     List<BpmNodeDef> rtnList = new ArrayList();
     for (BpmNodeDef nodeDef : this.aT) {
       if (nodeDef.getType().equals(NodeType.END)) {
         rtnList.add(nodeDef);
       }
     }
     return rtnList;
   }
   
 
 
   public List<BpmDataModel> getDataModelList() { return this.aX; }
   
   public String getDataModelKeys() {
     List<String> keys = new ArrayList();
     for (BpmDataModel model : this.aX) {
       keys.add(model.getCode());
     }
     return StringUtil.convertCollectionAsString(keys);
   }
   
   public void setDataModelList(List<BpmDataModel> dataModelList) {
     this.aX = dataModelList;
   }
   
   public void setParentProcessDef(DefaultBpmProcessDef processDef)
   {
     this.aU = processDef;
   }
   
 
   public void setJson(JSONObject bpmDefSetting)
   {
     this.be = bpmDefSetting;
   }
   
   public JSONObject getJson() {
     return this.be;
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\model\DefaultBpmProcessDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */