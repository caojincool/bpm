 package com.dstz.bpm.service;
 
 import com.alibaba.fastjson.JSONObject;
 import com.dstz.base.api.exception.BusinessException;
 import com.dstz.base.api.exception.SerializeException;
 import com.dstz.base.core.cache.ICache;
 import com.dstz.bpm.api.constant.NodeType;
 import com.dstz.bpm.api.engine.context.BpmContext;
 import com.dstz.bpm.api.exception.BpmStatusCode;
 import com.dstz.bpm.api.model.def.BpmProcessDef;
 import com.dstz.bpm.api.model.def.IBpmDefinition;
 import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
 import com.dstz.bpm.api.model.nodedef.impl.SubProcessNodeDef;
 import com.dstz.bpm.api.service.BpmProcessDefService;
 import com.dstz.bpm.core.manager.BpmDefinitionManager;
 import com.dstz.bpm.core.model.BpmDefinition;
 import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
 import com.dstz.bpm.engine.parser.BpmDefNodeHandler;
 import com.dstz.bpm.engine.parser.BpmProcessDefParser;
 import java.util.ArrayList;
 import java.util.List;
 import javax.annotation.Resource;
 import org.activiti.bpmn.model.BpmnModel;
 import org.activiti.bpmn.model.Process;
 import org.activiti.engine.RepositoryService;
 import org.springframework.stereotype.Component;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 @Component
 public class DefaultBpmProcessDefService
   implements BpmProcessDefService
 {
   @Resource
   ICache<DefaultBpmProcessDef> bP;
   @Resource
   private BpmDefinitionManager c;
   @Resource
   private BpmDefNodeHandler bQ;
   @Resource
   RepositoryService repositoryService;
   
   public BpmProcessDef getBpmProcessDef(String processDefId)
   {
     return i(processDefId);
   }
   
 
   public List<BpmNodeDef> getNodeDefs(String processDefinitionId)
   {
     DefaultBpmProcessDef processDef = i(processDefinitionId);
     return processDef.getBpmnNodeDefs();
   }
   
   public BpmNodeDef getBpmNodeDef(String processDefinitionId, String nodeId)
   {
     List<BpmNodeDef> list = getNodeDefs(processDefinitionId);
     List<SubProcessNodeDef> listSub = new ArrayList();
     for (BpmNodeDef nodeDef : list) {
       if (nodeDef.getNodeId().equals(nodeId)) {
         return nodeDef;
       }
       if ((nodeDef instanceof SubProcessNodeDef)) {
         listSub.add((SubProcessNodeDef)nodeDef);
       }
     }
     if (listSub.size() > 0)
       return a(listSub, nodeId);
     return null;
   }
   
   private BpmNodeDef a(List<SubProcessNodeDef> subList, String nodeId) {
     for (SubProcessNodeDef nodeDef : subList) {
       List<BpmNodeDef> nodeList = nodeDef.getChildBpmProcessDef().getBpmnNodeDefs();
       List<SubProcessNodeDef> nestSub = new ArrayList();
       for (BpmNodeDef tmpDef : nodeList) {
         if (tmpDef.getNodeId().equals(nodeId)) {
           return tmpDef;
         }
         if ((tmpDef instanceof SubProcessNodeDef)) {
           nestSub.add((SubProcessNodeDef)tmpDef);
         }
       }
       if (nestSub.size() > 0)
         return a(nestSub, nodeId);
     }
     return null;
   }
   
 
   public BpmNodeDef getStartEvent(String processDefId)
   {
     DefaultBpmProcessDef processDef = i(processDefId);
     List<BpmNodeDef> list = processDef.getBpmnNodeDefs();
     for (BpmNodeDef nodeDef : list) {
       if (nodeDef.getType().equals(NodeType.START))
         return nodeDef;
     }
     return null;
   }
   
   public List<BpmNodeDef> getEndEvents(String processDefId)
   {
     List<BpmNodeDef> nodeList = new ArrayList();
     DefaultBpmProcessDef processDef = i(processDefId);
     List<BpmNodeDef> list = processDef.getBpmnNodeDefs();
     for (BpmNodeDef nodeDef : list) {
       if (nodeDef.getType().equals(NodeType.END)) {
         nodeList.add(nodeDef);
       }
     }
     return nodeList;
   }
   
   public void clean(String processDefId)
   {
     this.bP.delByKey("procdef_" + processDefId);
     BpmContext.cleanTread();
   }
   
   public List<BpmNodeDef> getStartNodes(String processDefId)
   {
     BpmNodeDef nodeDef = getStartEvent(processDefId);
     return nodeDef.getOutcomeNodes();
   }
   
 
   public boolean isStartNode(String defId, String nodeId)
   {
     List<BpmNodeDef> nodes = getStartNodes(defId);
     for (BpmNodeDef node : nodes) {
       if (node.getNodeId().equals(nodeId)) {
         return true;
       }
     }
     return false;
   }
   
 
   public boolean validNodeDefType(String defId, String nodeId, NodeType nodeDefType)
   {
     BpmNodeDef nodeDef = getBpmNodeDef(defId, nodeId);
     return nodeDef.getType().equals(nodeDefType);
   }
   
 
   public boolean isContainCallActivity(String defId)
   {
     DefaultBpmProcessDef processDef = i(defId);
     List<BpmNodeDef> list = processDef.getBpmnNodeDefs();
     for (BpmNodeDef nodeDef : list) {
       if (nodeDef.getType().equals(NodeType.CALLACTIVITY)) {
         return true;
       }
     }
     
     return false;
   }
   
 
 
 
 
 
   private DefaultBpmProcessDef i(String processDefinitionId)
   {
     DefaultBpmProcessDef processDef = (DefaultBpmProcessDef)BpmContext.getProcessDef(processDefinitionId);
     if (processDef != null) {
       return processDef;
     }
     
     processDef = a(processDefinitionId, Boolean.valueOf(true));
     if (processDef == null) { return null;
     }
     BpmContext.addProcessDef(processDefinitionId, processDef);
     
     return processDef;
   }
   
   private synchronized DefaultBpmProcessDef a(String processDefinitionId, Boolean isCache) {
     DefaultBpmProcessDef bpmProcessDef = null;
     if (isCache.booleanValue()) {
       try {
         bpmProcessDef = (DefaultBpmProcessDef)this.bP.getByKey("procdef_" + processDefinitionId);
       } catch (SerializeException e) {
         this.bP.delByKey("procdef_" + processDefinitionId);
         bpmProcessDef = null;
       }
     }
     
     if (bpmProcessDef != null) { return bpmProcessDef;
     }
     BpmDefinition bpmDef = (BpmDefinition)this.c.get(processDefinitionId);
     bpmProcessDef = e(bpmDef);
     
     if (isCache.booleanValue()) { this.bP.add("procdef_" + processDefinitionId, bpmProcessDef);
     }
     return bpmProcessDef;
   }
   
 
 
 
 
 
 
 
 
 
 
   public DefaultBpmProcessDef e(BpmDefinition bpmDef)
   {
     if (bpmDef == null) { return null;
     }
     JSONObject bpmDefSetting = JSONObject.parseObject(bpmDef.getDefSetting());
     
     DefaultBpmProcessDef bpmProcessDef = new DefaultBpmProcessDef();
     bpmProcessDef.setProcessDefinitionId(bpmDef.getId());
     bpmProcessDef.setName(bpmDef.getName());
     bpmProcessDef.setDefKey(bpmDef.getKey());
     
 
 
     BpmnModel bpmnModel = this.repositoryService.getBpmnModel(bpmDef.getActDefId());
     Process process = (Process)bpmnModel.getProcesses().get(0);
     this.bQ.a(bpmProcessDef, process.getFlowElements());
     
     BpmProcessDefParser.a(bpmProcessDef, bpmDefSetting);
     bpmProcessDef.setJson(bpmDefSetting);
     return bpmProcessDef;
   }
   
 
   public List<BpmNodeDef> getNodesByType(String processDefinitionId, NodeType nodeType)
   {
     DefaultBpmProcessDef processDef = i(processDefinitionId);
     List<BpmNodeDef> list = processDef.getBpmnNodeDefs();
     List<BpmNodeDef> rtnList = new ArrayList();
     for (BpmNodeDef nodeDef : list) {
       if (nodeDef.getType().equals(nodeType)) {
         rtnList.add(nodeDef);
       }
     }
     return rtnList;
   }
   
 
 
   public List<BpmNodeDef> getAllNodeDef(String processDefinitionId)
   {
     List<BpmNodeDef> bpmNodeDefs = getNodeDefs(processDefinitionId);
     List<BpmNodeDef> rtnList = new ArrayList();
     a(bpmNodeDefs, rtnList);
     return rtnList;
   }
   
 
   private void a(List<BpmNodeDef> bpmNodeDefs, List<BpmNodeDef> rtnList)
   {
     for (BpmNodeDef def : bpmNodeDefs) {
       rtnList.add(def);
       if (NodeType.SUBPROCESS.equals(def.getType())) {
         SubProcessNodeDef subProcessNodeDef = (SubProcessNodeDef)def;
         BpmProcessDef processDef = subProcessNodeDef.getChildBpmProcessDef();
         if (processDef != null) {
           List<BpmNodeDef> subBpmNodeDefs = processDef.getBpmnNodeDefs();
           a(subBpmNodeDefs, rtnList);
         }
       }
     }
   }
   
 
 
   public List<BpmNodeDef> getSignUserNode(String processDefinitionId)
   {
     List<BpmNodeDef> bpmNodeDefs = getAllNodeDef(processDefinitionId);
     List<BpmNodeDef> rtnList = new ArrayList();
     for (BpmNodeDef bnd : bpmNodeDefs) {
       if ((bnd.getType().equals(NodeType.START)) || (bnd.getType().equals(NodeType.SIGNTASK)) || (bnd.getType().equals(NodeType.USERTASK))) {
         rtnList.add(bnd);
       }
     }
     return rtnList;
   }
   
   public IBpmDefinition getDefinitionById(String defId)
   {
     return (IBpmDefinition)this.c.get(defId);
   }
   
   public BpmProcessDef initBpmProcessDef(IBpmDefinition bpmDef)
   {
     try
     {
       DefaultBpmProcessDef def = e((BpmDefinition)bpmDef);
       BpmContext.cleanTread();
       this.bP.delByKey("procdef_" + bpmDef.getId());
       return def;
     } catch (Exception e) {
       throw new BusinessException(e.getMessage(), BpmStatusCode.PARSER_FLOW_ERROR, e);
     }
   }
   
 
   public IBpmDefinition getDefinitionByActDefId(String actDefId)
   {
     return this.c.getDefinitionByActDefId(actDefId);
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\service\DefaultBpmProcessDefService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */