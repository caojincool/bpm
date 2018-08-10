/*     */ package com.dstz.bpm.service;
/*     */ 
/*     */ import com.alibaba.fastjson.JSONObject;
/*     */ import com.dstz.base.api.exception.BusinessException;
/*     */ import com.dstz.base.api.exception.SerializeException;
/*     */ import com.dstz.base.core.cache.ICache;
/*     */ import com.dstz.bpm.api.constant.NodeType;
/*     */ import com.dstz.bpm.api.engine.context.BpmContext;
/*     */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*     */ import com.dstz.bpm.api.model.def.BpmProcessDef;
/*     */ import com.dstz.bpm.api.model.def.IBpmDefinition;
/*     */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*     */ import com.dstz.bpm.api.model.nodedef.impl.SubProcessNodeDef;
/*     */ import com.dstz.bpm.api.service.BpmProcessDefService;
/*     */ import com.dstz.bpm.core.manager.BpmDefinitionManager;
/*     */ import com.dstz.bpm.core.model.BpmDefinition;
/*     */ import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
/*     */ import com.dstz.bpm.engine.parser.BpmDefNodeHandler;
/*     */ import com.dstz.bpm.engine.parser.BpmProcessDefParser;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import org.activiti.bpmn.model.BpmnModel;
/*     */ import org.activiti.bpmn.model.Process;
/*     */ import org.activiti.engine.RepositoryService;
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
/*     */ @Component
/*     */ public class DefaultBpmProcessDefService
/*     */   implements BpmProcessDefService
/*     */ {
/*     */   @Resource
/*     */   ICache<DefaultBpmProcessDef> bP;
/*     */   @Resource
/*     */   private BpmDefinitionManager c;
/*     */   @Resource
/*     */   private BpmDefNodeHandler bQ;
/*     */   @Resource
/*     */   RepositoryService repositoryService;
/*     */   
/*     */   public BpmProcessDef getBpmProcessDef(String processDefId)
/*     */   {
/*  58 */     return i(processDefId);
/*     */   }
/*     */   
/*     */ 
/*     */   public List<BpmNodeDef> getNodeDefs(String processDefinitionId)
/*     */   {
/*  64 */     DefaultBpmProcessDef processDef = i(processDefinitionId);
/*  65 */     return processDef.getBpmnNodeDefs();
/*     */   }
/*     */   
/*     */   public BpmNodeDef getBpmNodeDef(String processDefinitionId, String nodeId)
/*     */   {
/*  70 */     List<BpmNodeDef> list = getNodeDefs(processDefinitionId);
/*  71 */     List<SubProcessNodeDef> listSub = new ArrayList();
/*  72 */     for (BpmNodeDef nodeDef : list) {
/*  73 */       if (nodeDef.getNodeId().equals(nodeId)) {
/*  74 */         return nodeDef;
/*     */       }
/*  76 */       if ((nodeDef instanceof SubProcessNodeDef)) {
/*  77 */         listSub.add((SubProcessNodeDef)nodeDef);
/*     */       }
/*     */     }
/*  80 */     if (listSub.size() > 0)
/*  81 */       return a(listSub, nodeId);
/*  82 */     return null;
/*     */   }
/*     */   
/*     */   private BpmNodeDef a(List<SubProcessNodeDef> subList, String nodeId) {
/*  86 */     for (SubProcessNodeDef nodeDef : subList) {
/*  87 */       List<BpmNodeDef> nodeList = nodeDef.getChildBpmProcessDef().getBpmnNodeDefs();
/*  88 */       List<SubProcessNodeDef> nestSub = new ArrayList();
/*  89 */       for (BpmNodeDef tmpDef : nodeList) {
/*  90 */         if (tmpDef.getNodeId().equals(nodeId)) {
/*  91 */           return tmpDef;
/*     */         }
/*  93 */         if ((tmpDef instanceof SubProcessNodeDef)) {
/*  94 */           nestSub.add((SubProcessNodeDef)tmpDef);
/*     */         }
/*     */       }
/*  97 */       if (nestSub.size() > 0)
/*  98 */         return a(nestSub, nodeId);
/*     */     }
/* 100 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public BpmNodeDef getStartEvent(String processDefId)
/*     */   {
/* 106 */     DefaultBpmProcessDef processDef = i(processDefId);
/* 107 */     List<BpmNodeDef> list = processDef.getBpmnNodeDefs();
/* 108 */     for (BpmNodeDef nodeDef : list) {
/* 109 */       if (nodeDef.getType().equals(NodeType.START))
/* 110 */         return nodeDef;
/*     */     }
/* 112 */     return null;
/*     */   }
/*     */   
/*     */   public List<BpmNodeDef> getEndEvents(String processDefId)
/*     */   {
/* 117 */     List<BpmNodeDef> nodeList = new ArrayList();
/* 118 */     DefaultBpmProcessDef processDef = i(processDefId);
/* 119 */     List<BpmNodeDef> list = processDef.getBpmnNodeDefs();
/* 120 */     for (BpmNodeDef nodeDef : list) {
/* 121 */       if (nodeDef.getType().equals(NodeType.END)) {
/* 122 */         nodeList.add(nodeDef);
/*     */       }
/*     */     }
/* 125 */     return nodeList;
/*     */   }
/*     */   
/*     */   public void clean(String processDefId)
/*     */   {
/* 130 */     this.bP.delByKey("procdef_" + processDefId);
/* 131 */     BpmContext.cleanTread();
/*     */   }
/*     */   
/*     */   public List<BpmNodeDef> getStartNodes(String processDefId)
/*     */   {
/* 136 */     BpmNodeDef nodeDef = getStartEvent(processDefId);
/* 137 */     return nodeDef.getOutcomeNodes();
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isStartNode(String defId, String nodeId)
/*     */   {
/* 143 */     List<BpmNodeDef> nodes = getStartNodes(defId);
/* 144 */     for (BpmNodeDef node : nodes) {
/* 145 */       if (node.getNodeId().equals(nodeId)) {
/* 146 */         return true;
/*     */       }
/*     */     }
/* 149 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean validNodeDefType(String defId, String nodeId, NodeType nodeDefType)
/*     */   {
/* 155 */     BpmNodeDef nodeDef = getBpmNodeDef(defId, nodeId);
/* 156 */     return nodeDef.getType().equals(nodeDefType);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isContainCallActivity(String defId)
/*     */   {
/* 162 */     DefaultBpmProcessDef processDef = i(defId);
/* 163 */     List<BpmNodeDef> list = processDef.getBpmnNodeDefs();
/* 164 */     for (BpmNodeDef nodeDef : list) {
/* 165 */       if (nodeDef.getType().equals(NodeType.CALLACTIVITY)) {
/* 166 */         return true;
/*     */       }
/*     */     }
/*     */     
/* 170 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private DefaultBpmProcessDef i(String processDefinitionId)
/*     */   {
/* 180 */     DefaultBpmProcessDef processDef = (DefaultBpmProcessDef)BpmContext.getProcessDef(processDefinitionId);
/* 181 */     if (processDef != null) {
/* 182 */       return processDef;
/*     */     }
/*     */     
/* 185 */     processDef = a(processDefinitionId, Boolean.valueOf(true));
/* 186 */     if (processDef == null) { return null;
/*     */     }
/* 188 */     BpmContext.addProcessDef(processDefinitionId, processDef);
/*     */     
/* 190 */     return processDef;
/*     */   }
/*     */   
/*     */   private synchronized DefaultBpmProcessDef a(String processDefinitionId, Boolean isCache) {
/* 194 */     DefaultBpmProcessDef bpmProcessDef = null;
/* 195 */     if (isCache.booleanValue()) {
/*     */       try {
/* 197 */         bpmProcessDef = (DefaultBpmProcessDef)this.bP.getByKey("procdef_" + processDefinitionId);
/*     */       } catch (SerializeException e) {
/* 199 */         this.bP.delByKey("procdef_" + processDefinitionId);
/* 200 */         bpmProcessDef = null;
/*     */       }
/*     */     }
/*     */     
/* 204 */     if (bpmProcessDef != null) { return bpmProcessDef;
/*     */     }
/* 206 */     BpmDefinition bpmDef = (BpmDefinition)this.c.get(processDefinitionId);
/* 207 */     bpmProcessDef = e(bpmDef);
/*     */     
/* 209 */     if (isCache.booleanValue()) { this.bP.add("procdef_" + processDefinitionId, bpmProcessDef);
/*     */     }
/* 211 */     return bpmProcessDef;
/*     */   }
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
/*     */   public DefaultBpmProcessDef e(BpmDefinition bpmDef)
/*     */   {
/* 226 */     if (bpmDef == null) { return null;
/*     */     }
/* 228 */     JSONObject bpmDefSetting = JSONObject.parseObject(bpmDef.getDefSetting());
/*     */     
/* 230 */     DefaultBpmProcessDef bpmProcessDef = new DefaultBpmProcessDef();
/* 231 */     bpmProcessDef.setProcessDefinitionId(bpmDef.getId());
/* 232 */     bpmProcessDef.setName(bpmDef.getName());
/* 233 */     bpmProcessDef.setDefKey(bpmDef.getKey());
/*     */     
/*     */ 
/*     */ 
/* 237 */     BpmnModel bpmnModel = this.repositoryService.getBpmnModel(bpmDef.getActDefId());
/* 238 */     Process process = (Process)bpmnModel.getProcesses().get(0);
/* 239 */     this.bQ.a(bpmProcessDef, process.getFlowElements());
/*     */     
/* 241 */     BpmProcessDefParser.a(bpmProcessDef, bpmDefSetting);
/* 242 */     bpmProcessDef.setJson(bpmDefSetting);
/* 243 */     return bpmProcessDef;
/*     */   }
/*     */   
/*     */ 
/*     */   public List<BpmNodeDef> getNodesByType(String processDefinitionId, NodeType nodeType)
/*     */   {
/* 249 */     DefaultBpmProcessDef processDef = i(processDefinitionId);
/* 250 */     List<BpmNodeDef> list = processDef.getBpmnNodeDefs();
/* 251 */     List<BpmNodeDef> rtnList = new ArrayList();
/* 252 */     for (BpmNodeDef nodeDef : list) {
/* 253 */       if (nodeDef.getType().equals(nodeType)) {
/* 254 */         rtnList.add(nodeDef);
/*     */       }
/*     */     }
/* 257 */     return rtnList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<BpmNodeDef> getAllNodeDef(String processDefinitionId)
/*     */   {
/* 264 */     List<BpmNodeDef> bpmNodeDefs = getNodeDefs(processDefinitionId);
/* 265 */     List<BpmNodeDef> rtnList = new ArrayList();
/* 266 */     a(bpmNodeDefs, rtnList);
/* 267 */     return rtnList;
/*     */   }
/*     */   
/*     */ 
/*     */   private void a(List<BpmNodeDef> bpmNodeDefs, List<BpmNodeDef> rtnList)
/*     */   {
/* 273 */     for (BpmNodeDef def : bpmNodeDefs) {
/* 274 */       rtnList.add(def);
/* 275 */       if (NodeType.SUBPROCESS.equals(def.getType())) {
/* 276 */         SubProcessNodeDef subProcessNodeDef = (SubProcessNodeDef)def;
/* 277 */         BpmProcessDef processDef = subProcessNodeDef.getChildBpmProcessDef();
/* 278 */         if (processDef != null) {
/* 279 */           List<BpmNodeDef> subBpmNodeDefs = processDef.getBpmnNodeDefs();
/* 280 */           a(subBpmNodeDefs, rtnList);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<BpmNodeDef> getSignUserNode(String processDefinitionId)
/*     */   {
/* 290 */     List<BpmNodeDef> bpmNodeDefs = getAllNodeDef(processDefinitionId);
/* 291 */     List<BpmNodeDef> rtnList = new ArrayList();
/* 292 */     for (BpmNodeDef bnd : bpmNodeDefs) {
/* 293 */       if ((bnd.getType().equals(NodeType.START)) || (bnd.getType().equals(NodeType.SIGNTASK)) || (bnd.getType().equals(NodeType.USERTASK))) {
/* 294 */         rtnList.add(bnd);
/*     */       }
/*     */     }
/* 297 */     return rtnList;
/*     */   }
/*     */   
/*     */   public IBpmDefinition getDefinitionById(String defId)
/*     */   {
/* 302 */     return (IBpmDefinition)this.c.get(defId);
/*     */   }
/*     */   
/*     */   public BpmProcessDef initBpmProcessDef(IBpmDefinition bpmDef)
/*     */   {
/*     */     try
/*     */     {
/* 309 */       DefaultBpmProcessDef def = e((BpmDefinition)bpmDef);
/* 310 */       BpmContext.cleanTread();
/* 311 */       this.bP.delByKey("procdef_" + bpmDef.getId());
/* 312 */       return def;
/*     */     } catch (Exception e) {
/* 314 */       throw new BusinessException(e.getMessage(), BpmStatusCode.PARSER_FLOW_ERROR, e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public IBpmDefinition getDefinitionByActDefId(String actDefId)
/*     */   {
/* 321 */     return this.c.getDefinitionByActDefId(actDefId);
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\service\DefaultBpmProcessDefService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */