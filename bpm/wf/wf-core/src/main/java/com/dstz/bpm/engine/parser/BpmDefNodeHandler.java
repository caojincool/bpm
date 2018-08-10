/*     */ package com.dstz.bpm.engine.parser;
/*     */ 
/*     */ import com.dstz.bpm.api.constant.NodeType;
/*     */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*     */ import com.dstz.bpm.api.model.nodedef.impl.BaseBpmNodeDef;
/*     */ import com.dstz.bpm.api.model.nodedef.impl.CallActivityNodeDef;
/*     */ import com.dstz.bpm.api.model.nodedef.impl.GateWayBpmNodeDef;
/*     */ import com.dstz.bpm.api.model.nodedef.impl.SubProcessNodeDef;
/*     */ import com.dstz.bpm.api.model.nodedef.impl.UserTaskNodeDef;
/*     */ import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.activiti.bpmn.model.Activity;
/*     */ import org.activiti.bpmn.model.CallActivity;
/*     */ import org.activiti.bpmn.model.EndEvent;
/*     */ import org.activiti.bpmn.model.ExclusiveGateway;
/*     */ import org.activiti.bpmn.model.FlowElement;
/*     */ import org.activiti.bpmn.model.Gateway;
/*     */ import org.activiti.bpmn.model.InclusiveGateway;
/*     */ import org.activiti.bpmn.model.MultiInstanceLoopCharacteristics;
/*     */ import org.activiti.bpmn.model.ParallelGateway;
/*     */ import org.activiti.bpmn.model.SequenceFlow;
/*     */ import org.activiti.bpmn.model.ServiceTask;
/*     */ import org.activiti.bpmn.model.StartEvent;
/*     */ import org.activiti.bpmn.model.SubProcess;
/*     */ import org.activiti.bpmn.model.UserTask;
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
/*     */ @Component
/*     */ public class BpmDefNodeHandler
/*     */ {
/*     */   public void a(DefaultBpmProcessDef bpmProcessDef, Collection<FlowElement> collection)
/*     */   {
/*  49 */     a(null, collection, bpmProcessDef);
/*     */   }
/*     */   
/*     */   private void a(BpmNodeDef parentNodeDef, Collection<FlowElement> flowElementList, DefaultBpmProcessDef bpmProcessDef)
/*     */   {
/*  54 */     Map<String, FlowElement> nodeMap = a(flowElementList);
/*     */     
/*  56 */     List<SequenceFlow> seqList = b(flowElementList);
/*     */     
/*  58 */     Map<String, BpmNodeDef> nodeDefMap = a(nodeMap, parentNodeDef, bpmProcessDef);
/*     */     
/*     */ 
/*  61 */     a(nodeDefMap, seqList);
/*     */     
/*     */ 
/*  64 */     List<BpmNodeDef> nodeDefList = new ArrayList(nodeDefMap.values());
/*     */     
/*     */ 
/*  67 */     bpmProcessDef.setBpmnNodeDefs(nodeDefList);
/*     */     
/*  69 */     for (BpmNodeDef nodeDef : nodeDefList) {
/*  70 */       BaseBpmNodeDef node = (BaseBpmNodeDef)nodeDef;
/*  71 */       node.setBpmProcessDef(bpmProcessDef);
/*     */     }
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
/*     */   private Map<String, BpmNodeDef> a(Map<String, FlowElement> nodeMap, BpmNodeDef parentNodeDef, DefaultBpmProcessDef bpmProcessDef)
/*     */   {
/*  85 */     Map<String, BpmNodeDef> map = new HashMap();
/*  86 */     Set<Map.Entry<String, FlowElement>> set = nodeMap.entrySet();
/*  87 */     for (Iterator<Map.Entry<String, FlowElement>> it = set.iterator(); it.hasNext();) {
/*  88 */       Map.Entry<String, FlowElement> ent = (Map.Entry)it.next();
/*  89 */       FlowElement flowEl = (FlowElement)ent.getValue();
/*     */       
/*  91 */       BaseBpmNodeDef nodeDef = a(parentNodeDef, flowEl, bpmProcessDef);
/*     */       
/*  93 */       map.put(ent.getKey(), nodeDef);
/*     */     }
/*  95 */     return map;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void a(Map<String, BpmNodeDef> nodeDefMap, List<SequenceFlow> seqList)
/*     */   {
/* 105 */     for (SequenceFlow seq : seqList) {
/* 106 */       BpmNodeDef sourceDef = (BpmNodeDef)nodeDefMap.get(seq.getSourceRef());
/* 107 */       BpmNodeDef targetDef = (BpmNodeDef)nodeDefMap.get(seq.getTargetRef());
/* 108 */       sourceDef.addOutcomeNode(targetDef);
/* 109 */       targetDef.addIncomeNode(sourceDef);
/*     */     }
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
/*     */ 
/*     */ 
/*     */   private Map<String, FlowElement> a(Collection<FlowElement> flowElementList)
/*     */   {
/* 127 */     Map<String, FlowElement> map = new HashMap();
/* 128 */     for (FlowElement flowElement : flowElementList) {
/* 129 */       a(flowElement, map, this.bh);
/*     */     }
/* 131 */     return map;
/*     */   }
/*     */   
/*     */ 
/* 135 */   private Class<FlowElement>[] bh = { StartEvent.class, EndEvent.class, ParallelGateway.class, InclusiveGateway.class, ExclusiveGateway.class, UserTask.class, ServiceTask.class, CallActivity.class, SubProcess.class };
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
/*     */   private BaseBpmNodeDef a(BpmNodeDef parentNodeDef, FlowElement flowElement, DefaultBpmProcessDef bpmProcessDef)
/*     */   {
/* 160 */     BaseBpmNodeDef nodeDef = null;
/* 161 */     if ((flowElement instanceof StartEvent)) {
/* 162 */       nodeDef = new BaseBpmNodeDef();
/* 163 */       nodeDef.setType(NodeType.START);
/*     */     }
/* 165 */     else if ((flowElement instanceof EndEvent)) {
/* 166 */       nodeDef = new BaseBpmNodeDef();
/* 167 */       nodeDef.setType(NodeType.END);
/*     */     }
/* 169 */     else if ((flowElement instanceof Gateway)) {
/* 170 */       nodeDef = new GateWayBpmNodeDef();
/*     */       
/* 172 */       if ((flowElement instanceof ParallelGateway)) {
/* 173 */         nodeDef.setType(NodeType.PARALLELGATEWAY);
/*     */ 
/*     */       }
/* 176 */       else if ((flowElement instanceof InclusiveGateway)) {
/* 177 */         nodeDef.setType(NodeType.INCLUSIVEGATEWAY);
/*     */ 
/*     */       }
/* 180 */       else if ((flowElement instanceof ExclusiveGateway)) {
/* 181 */         nodeDef.setType(NodeType.EXCLUSIVEGATEWAY);
/*     */       }
/*     */     }
/* 184 */     else if ((flowElement instanceof Activity))
/*     */     {
/* 186 */       String multi = a((Activity)flowElement);
/*     */       
/* 188 */       if ((flowElement instanceof UserTask))
/*     */       {
/* 190 */         if (multi == null) {
/* 191 */           UserTaskNodeDef userTaskDef = new UserTaskNodeDef();
/* 192 */           nodeDef = userTaskDef;
/* 193 */           nodeDef.setType(NodeType.USERTASK);
/*     */ 
/*     */ 
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */       }
/* 201 */       else if (!(flowElement instanceof ServiceTask))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 207 */         if ((flowElement instanceof CallActivity))
/*     */         {
/* 209 */           CallActivityNodeDef callNodeDef = new CallActivityNodeDef();
/* 210 */           CallActivity call = (CallActivity)flowElement;
/* 211 */           String flowKey = call.getCalledElement();
/* 212 */           callNodeDef.setType(NodeType.CALLACTIVITY);
/* 213 */           callNodeDef.setFlowKey(flowKey);
/*     */           
/* 215 */           nodeDef = callNodeDef;
/*     */ 
/*     */ 
/*     */         }
/* 219 */         else if ((flowElement instanceof SubProcess)) {
/* 220 */           SubProcessNodeDef subProcessDef = new SubProcessNodeDef();
/*     */           
/* 222 */           nodeDef = subProcessDef;
/* 223 */           nodeDef.setNodeId(flowElement.getId());
/* 224 */           nodeDef.setName(flowElement.getName());
/* 225 */           nodeDef.setParentBpmNodeDef(parentNodeDef);
/*     */           
/* 227 */           subProcessDef.setBpmProcessDef(bpmProcessDef);
/* 228 */           SubProcess subProcess = (SubProcess)flowElement;
/*     */           
/* 230 */           a(nodeDef, subProcess, bpmProcessDef);
/*     */         }
/*     */       }
/*     */     }
/* 234 */     nodeDef.setParentBpmNodeDef(parentNodeDef);
/* 235 */     nodeDef.setNodeId(flowElement.getId());
/* 236 */     nodeDef.setName(flowElement.getName());
/* 237 */     return nodeDef;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void a(BaseBpmNodeDef nodeDef, SubProcess subProcess, DefaultBpmProcessDef parentProcessDef)
/*     */   {
/* 248 */     DefaultBpmProcessDef bpmProcessDef = new DefaultBpmProcessDef();
/* 249 */     bpmProcessDef.setProcessDefinitionId(subProcess.getId());
/* 250 */     bpmProcessDef.setName(subProcess.getName());
/* 251 */     bpmProcessDef.setDefKey(subProcess.getId());
/* 252 */     bpmProcessDef.setParentProcessDef(parentProcessDef);
/*     */     
/* 254 */     SubProcessNodeDef subNodeDef = (SubProcessNodeDef)nodeDef;
/*     */     
/* 256 */     subNodeDef.setBpmProcessDef(parentProcessDef);
/* 257 */     subNodeDef.setChildBpmProcessDef(bpmProcessDef);
/* 258 */     Collection<FlowElement> list = subProcess.getFlowElements();
/* 259 */     a(nodeDef, list, bpmProcessDef);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void a(FlowElement flowElement, Map<String, FlowElement> map, Class<? extends FlowElement>... flowTypes)
/*     */   {
/* 270 */     for (Class<? extends FlowElement> flowType : flowTypes) {
/* 271 */       if (flowType.isInstance(flowElement)) {
/* 272 */         map.put(flowElement.getId(), flowElement);
/* 273 */         break;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private String a(Activity flowElement)
/*     */   {
/* 285 */     MultiInstanceLoopCharacteristics jaxbloop = flowElement.getLoopCharacteristics();
/* 286 */     if (jaxbloop == null) return null;
/* 287 */     return jaxbloop.isSequential() ? "sequence" : "parallel";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private List<SequenceFlow> b(Collection<FlowElement> flowElementList)
/*     */   {
/* 297 */     List<SequenceFlow> nodeList = new ArrayList();
/* 298 */     for (FlowElement flowElement : flowElementList) {
/* 299 */       if ((flowElement instanceof SequenceFlow)) {
/* 300 */         nodeList.add((SequenceFlow)flowElement);
/*     */       }
/*     */     }
/* 303 */     return nodeList;
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\parser\BpmDefNodeHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */