 package com.dstz.bpm.engine.parser;
 
 import com.dstz.bpm.api.constant.NodeType;
 import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
 import com.dstz.bpm.api.model.nodedef.impl.BaseBpmNodeDef;
 import com.dstz.bpm.api.model.nodedef.impl.CallActivityNodeDef;
 import com.dstz.bpm.api.model.nodedef.impl.GateWayBpmNodeDef;
 import com.dstz.bpm.api.model.nodedef.impl.SubProcessNodeDef;
 import com.dstz.bpm.api.model.nodedef.impl.UserTaskNodeDef;
 import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
 import java.util.ArrayList;
 import java.util.Collection;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
 import java.util.Map.Entry;
 import java.util.Set;
 import org.activiti.bpmn.model.Activity;
 import org.activiti.bpmn.model.CallActivity;
 import org.activiti.bpmn.model.EndEvent;
 import org.activiti.bpmn.model.ExclusiveGateway;
 import org.activiti.bpmn.model.FlowElement;
 import org.activiti.bpmn.model.Gateway;
 import org.activiti.bpmn.model.InclusiveGateway;
 import org.activiti.bpmn.model.MultiInstanceLoopCharacteristics;
 import org.activiti.bpmn.model.ParallelGateway;
 import org.activiti.bpmn.model.SequenceFlow;
 import org.activiti.bpmn.model.ServiceTask;
 import org.activiti.bpmn.model.StartEvent;
 import org.activiti.bpmn.model.SubProcess;
 import org.activiti.bpmn.model.UserTask;
 import org.springframework.stereotype.Component;
 
 
 
 
 
 
 
 
 
 
 @Component
 public class BpmDefNodeHandler
 {
   public void a(DefaultBpmProcessDef bpmProcessDef, Collection<FlowElement> collection)
   {
     a(null, collection, bpmProcessDef);
   }
   
   private void a(BpmNodeDef parentNodeDef, Collection<FlowElement> flowElementList, DefaultBpmProcessDef bpmProcessDef)
   {
     Map<String, FlowElement> nodeMap = a(flowElementList);
     
     List<SequenceFlow> seqList = b(flowElementList);
     
     Map<String, BpmNodeDef> nodeDefMap = a(nodeMap, parentNodeDef, bpmProcessDef);
     
 
     a(nodeDefMap, seqList);
     
 
     List<BpmNodeDef> nodeDefList = new ArrayList(nodeDefMap.values());
     
 
     bpmProcessDef.setBpmnNodeDefs(nodeDefList);
     
     for (BpmNodeDef nodeDef : nodeDefList) {
       BaseBpmNodeDef node = (BaseBpmNodeDef)nodeDef;
       node.setBpmProcessDef(bpmProcessDef);
     }
   }
   
 
 
 
 
 
 
 
 
   private Map<String, BpmNodeDef> a(Map<String, FlowElement> nodeMap, BpmNodeDef parentNodeDef, DefaultBpmProcessDef bpmProcessDef)
   {
     Map<String, BpmNodeDef> map = new HashMap();
     Set<Map.Entry<String, FlowElement>> set = nodeMap.entrySet();
     for (Iterator<Map.Entry<String, FlowElement>> it = set.iterator(); it.hasNext();) {
       Map.Entry<String, FlowElement> ent = (Map.Entry)it.next();
       FlowElement flowEl = (FlowElement)ent.getValue();
       
       BaseBpmNodeDef nodeDef = a(parentNodeDef, flowEl, bpmProcessDef);
       
       map.put(ent.getKey(), nodeDef);
     }
     return map;
   }
   
 
 
 
 
 
   private void a(Map<String, BpmNodeDef> nodeDefMap, List<SequenceFlow> seqList)
   {
     for (SequenceFlow seq : seqList) {
       BpmNodeDef sourceDef = (BpmNodeDef)nodeDefMap.get(seq.getSourceRef());
       BpmNodeDef targetDef = (BpmNodeDef)nodeDefMap.get(seq.getTargetRef());
       sourceDef.addOutcomeNode(targetDef);
       targetDef.addIncomeNode(sourceDef);
     }
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
   private Map<String, FlowElement> a(Collection<FlowElement> flowElementList)
   {
     Map<String, FlowElement> map = new HashMap();
     for (FlowElement flowElement : flowElementList) {
       a(flowElement, map, this.bh);
     }
     return map;
   }
   
 
   private Class<FlowElement>[] bh = { StartEvent.class, EndEvent.class, ParallelGateway.class, InclusiveGateway.class, ExclusiveGateway.class, UserTask.class, ServiceTask.class, CallActivity.class, SubProcess.class };
   
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
   private BaseBpmNodeDef a(BpmNodeDef parentNodeDef, FlowElement flowElement, DefaultBpmProcessDef bpmProcessDef)
   {
     BaseBpmNodeDef nodeDef = null;
     if ((flowElement instanceof StartEvent)) {
       nodeDef = new BaseBpmNodeDef();
       nodeDef.setType(NodeType.START);
     }
     else if ((flowElement instanceof EndEvent)) {
       nodeDef = new BaseBpmNodeDef();
       nodeDef.setType(NodeType.END);
     }
     else if ((flowElement instanceof Gateway)) {
       nodeDef = new GateWayBpmNodeDef();
       
       if ((flowElement instanceof ParallelGateway)) {
         nodeDef.setType(NodeType.PARALLELGATEWAY);
 
       }
       else if ((flowElement instanceof InclusiveGateway)) {
         nodeDef.setType(NodeType.INCLUSIVEGATEWAY);
 
       }
       else if ((flowElement instanceof ExclusiveGateway)) {
         nodeDef.setType(NodeType.EXCLUSIVEGATEWAY);
       }
     }
     else if ((flowElement instanceof Activity))
     {
       String multi = a((Activity)flowElement);
       
       if ((flowElement instanceof UserTask))
       {
         if (multi == null) {
           UserTaskNodeDef userTaskDef = new UserTaskNodeDef();
           nodeDef = userTaskDef;
           nodeDef.setType(NodeType.USERTASK);
 
 
         }
         
 
 
       }
       else if (!(flowElement instanceof ServiceTask))
       {
 
 
 
 
         if ((flowElement instanceof CallActivity))
         {
           CallActivityNodeDef callNodeDef = new CallActivityNodeDef();
           CallActivity call = (CallActivity)flowElement;
           String flowKey = call.getCalledElement();
           callNodeDef.setType(NodeType.CALLACTIVITY);
           callNodeDef.setFlowKey(flowKey);
           
           nodeDef = callNodeDef;
 
 
         }
         else if ((flowElement instanceof SubProcess)) {
           SubProcessNodeDef subProcessDef = new SubProcessNodeDef();
           
           nodeDef = subProcessDef;
           nodeDef.setNodeId(flowElement.getId());
           nodeDef.setName(flowElement.getName());
           nodeDef.setParentBpmNodeDef(parentNodeDef);
           
           subProcessDef.setBpmProcessDef(bpmProcessDef);
           SubProcess subProcess = (SubProcess)flowElement;
           
           a(nodeDef, subProcess, bpmProcessDef);
         }
       }
     }
     nodeDef.setParentBpmNodeDef(parentNodeDef);
     nodeDef.setNodeId(flowElement.getId());
     nodeDef.setName(flowElement.getName());
     return nodeDef;
   }
   
 
 
 
 
 
 
   private void a(BaseBpmNodeDef nodeDef, SubProcess subProcess, DefaultBpmProcessDef parentProcessDef)
   {
     DefaultBpmProcessDef bpmProcessDef = new DefaultBpmProcessDef();
     bpmProcessDef.setProcessDefinitionId(subProcess.getId());
     bpmProcessDef.setName(subProcess.getName());
     bpmProcessDef.setDefKey(subProcess.getId());
     bpmProcessDef.setParentProcessDef(parentProcessDef);
     
     SubProcessNodeDef subNodeDef = (SubProcessNodeDef)nodeDef;
     
     subNodeDef.setBpmProcessDef(parentProcessDef);
     subNodeDef.setChildBpmProcessDef(bpmProcessDef);
     Collection<FlowElement> list = subProcess.getFlowElements();
     a(nodeDef, list, bpmProcessDef);
   }
   
 
 
 
 
 
 
   private void a(FlowElement flowElement, Map<String, FlowElement> map, Class<? extends FlowElement>... flowTypes)
   {
     for (Class<? extends FlowElement> flowType : flowTypes) {
       if (flowType.isInstance(flowElement)) {
         map.put(flowElement.getId(), flowElement);
         break;
       }
     }
   }
   
 
 
 
 
 
   private String a(Activity flowElement)
   {
     MultiInstanceLoopCharacteristics jaxbloop = flowElement.getLoopCharacteristics();
     if (jaxbloop == null) return null;
     return jaxbloop.isSequential() ? "sequence" : "parallel";
   }
   
 
 
 
 
 
   private List<SequenceFlow> b(Collection<FlowElement> flowElementList)
   {
     List<SequenceFlow> nodeList = new ArrayList();
     for (FlowElement flowElement : flowElementList) {
       if ((flowElement instanceof SequenceFlow)) {
         nodeList.add((SequenceFlow)flowElement);
       }
     }
     return nodeList;
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\parser\BpmDefNodeHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */