/*     */ package com.dstz.bpm.core.manager.impl;
/*     */ 
/*     */ import com.dstz.base.api.exception.BusinessException;
/*     */ import com.dstz.base.api.query.QueryFilter;
/*     */ import com.dstz.base.api.query.QueryOP;
/*     */ import com.dstz.base.core.util.AppUtil;
/*     */ import com.dstz.base.core.util.BeanUtils;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.base.db.id.UniqueIdUtil;
/*     */ import com.dstz.base.db.model.query.DefaultQueryFilter;
/*     */ import com.dstz.base.manager.impl.BaseManager;
/*     */ import com.dstz.bpm.api.engine.event.BpmDefinitionUpdateEvent;
/*     */ import com.dstz.bpm.api.model.def.BpmDefProperties;
/*     */ import com.dstz.bpm.api.service.BpmProcessDefService;
/*     */ import com.dstz.bpm.core.dao.BpmDefinitionDao;
/*     */ import com.dstz.bpm.core.manager.BpmDefinitionManager;
/*     */ import com.dstz.bpm.core.manager.BpmInstanceManager;
/*     */ import com.dstz.bpm.core.model.BpmDefinition;
/*     */ import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
/*     */ import com.dstz.org.api.model.IUser;
/*     */ import com.dstz.sys.api.constant.EnvironmentConstant;
/*     */ import com.dstz.sys.api.constant.RightsObjectConstants;
/*     */ import com.dstz.sys.api.service.SysAuthorizationService;
/*     */ import com.dstz.sys.util.ContextUtil;
/*     */ import com.fasterxml.jackson.core.JsonProcessingException;
/*     */ import com.fasterxml.jackson.databind.ObjectMapper;
/*     */ import com.fasterxml.jackson.databind.node.ObjectNode;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Resource;
/*     */ import org.activiti.bpmn.converter.BpmnXMLConverter;
/*     */ import org.activiti.bpmn.model.BpmnModel;
/*     */ import org.activiti.editor.language.json.converter.BpmnJsonConverter;
/*     */ import org.activiti.engine.ProcessEngineConfiguration;
/*     */ import org.activiti.engine.RepositoryService;
/*     */ import org.activiti.engine.impl.bpmn.deployer.BpmnDeployer;
/*     */ import org.activiti.engine.impl.bpmn.parser.BpmnParse;
/*     */ import org.activiti.engine.impl.bpmn.parser.BpmnParser;
/*     */ import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
/*     */ import org.activiti.engine.impl.context.Context;
/*     */ import org.activiti.engine.impl.persistence.deploy.DeploymentCache;
/*     */ import org.activiti.engine.impl.persistence.deploy.DeploymentManager;
/*     */ import org.activiti.engine.impl.persistence.entity.DeploymentEntity;
/*     */ import org.activiti.engine.impl.util.IoUtil;
/*     */ import org.activiti.engine.repository.Deployment;
/*     */ import org.activiti.engine.repository.DeploymentBuilder;
/*     */ import org.activiti.engine.repository.DeploymentQuery;
/*     */ import org.activiti.engine.repository.Model;
/*     */ import org.activiti.engine.repository.ProcessDefinition;
/*     */ import org.activiti.engine.repository.ProcessDefinitionQuery;
/*     */ import org.activiti.image.ProcessDiagramGenerator;
/*     */ import org.apache.batik.transcoder.TranscoderInput;
/*     */ import org.apache.batik.transcoder.TranscoderOutput;
/*     */ import org.apache.batik.transcoder.image.PNGTranscoder;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.util.MultiValueMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Service("bpmDefinitionManager")
/*     */ public class BpmDefinitionManagerImpl
/*     */   extends BaseManager<String, BpmDefinition>
/*     */   implements BpmDefinitionManager
/*     */ {
/*     */   @Resource
/*     */   BpmDefinitionDao d;
/*     */   @Resource
/*     */   BpmProcessDefService a;
/*     */   @Resource
/*     */   RepositoryService repositoryService;
/*     */   @Resource
/*     */   ProcessEngineConfiguration processEngineConfiguration;
/*     */   @Resource
/*     */   SysAuthorizationService e;
/*     */   @Resource
/*     */   BpmInstanceManager f;
/*     */   
/*     */   public void a(BpmDefinition bpmDefinition)
/*     */   {
/*  91 */     if (StringUtil.isNotEmpty(bpmDefinition.getId())) {
/*  92 */       d(bpmDefinition);
/*  93 */       return;
/*     */     }
/*     */     
/*  96 */     List<BpmDefinition> defList = this.d.getByKey(bpmDefinition.getKey());
/*  97 */     if (BeanUtils.isNotEmpty(defList)) {
/*  98 */       throw new BusinessException("流程定义Key重复：" + bpmDefinition.getKey());
/*     */     }
/*     */     
/* 101 */     bpmDefinition.setIsMain("Y");
/* 102 */     bpmDefinition.setStatus("draft");
/* 103 */     bpmDefinition.setVersion(Integer.valueOf(1));
/*     */     
/* 105 */     String defId = UniqueIdUtil.getSuid();
/* 106 */     bpmDefinition.setId(defId);
/* 107 */     bpmDefinition.setMainDefId(defId);
/*     */     
/* 109 */     String modelId = b(bpmDefinition);
/*     */     
/* 111 */     bpmDefinition.setActModelId(modelId);
/* 112 */     this.d.create(bpmDefinition);
/*     */   }
/*     */   
/*     */   private String b(BpmDefinition bpmDefinition)
/*     */   {
/*     */     try {
/* 118 */       ObjectMapper objectMapper = new ObjectMapper();
/* 119 */       ObjectNode editorNode = objectMapper.createObjectNode();
/* 120 */       editorNode.put("id", "canvas");
/* 121 */       editorNode.put("resourceId", "canvas");
/* 122 */       ObjectNode stencilSetNode = objectMapper.createObjectNode();
/* 123 */       stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
/* 124 */       editorNode.set("stencilset", stencilSetNode);
/* 125 */       Model modelData = this.repositoryService.newModel();
/*     */       
/* 127 */       ObjectNode modelObjectNode = objectMapper.createObjectNode();
/* 128 */       modelObjectNode.put("name", bpmDefinition.getName());
/* 129 */       modelObjectNode.put("revision", 1);
/* 130 */       modelObjectNode.put("key", bpmDefinition.getKey());
/*     */       
/* 132 */       modelObjectNode.put("description", bpmDefinition.getDesc());
/* 133 */       modelData.setMetaInfo(modelObjectNode.toString());
/* 134 */       modelData.setName(bpmDefinition.getName());
/* 135 */       modelData.setKey(bpmDefinition.getKey());
/*     */       
/* 137 */       this.repositoryService.saveModel(modelData);
/*     */       
/* 139 */       this.repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
/* 140 */       return modelData.getId();
/*     */     } catch (UnsupportedEncodingException e) {
/* 142 */       throw new RuntimeException("创建activiti流程定义失败！", e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void updateBpmnModel(Model model, MultiValueMap<String, String> values)
/*     */     throws Exception
/*     */   {
/* 153 */     InputStream svgStream = new ByteArrayInputStream(((String)values.getFirst("svg_xml")).getBytes("utf-8"));
/* 154 */     TranscoderInput input = new TranscoderInput(svgStream);
/*     */     
/* 156 */     String bpmDefSettingJSON = (String)values.getFirst("bpmDefSetting");
/*     */     
/* 158 */     BpmDefinition bpmDef = this.d.getMainByDefKey(model.getKey());
/* 159 */     bpmDef.setName((String)values.getFirst("name"));
/* 160 */     bpmDef.setDesc((String)values.getFirst("description"));
/* 161 */     bpmDef.setDefSetting(bpmDefSettingJSON);
/*     */     
/* 163 */     PNGTranscoder transcoder = new PNGTranscoder();
/*     */     
/* 165 */     ByteArrayOutputStream outStream = new ByteArrayOutputStream();
/* 166 */     TranscoderOutput output = new TranscoderOutput(outStream);
/*     */     
/*     */ 
/* 169 */     transcoder.transcode(input, output);
/* 170 */     byte[] result = outStream.toByteArray();
/* 171 */     byte[] b = ((String)values.getFirst("json_xml")).getBytes("utf-8");
/*     */     
/*     */ 
/* 174 */     this.repositoryService.addModelEditorSource(model.getId(), b);
/*     */     
/* 176 */     boolean publish = Boolean.parseBoolean((String)values.getFirst("publish"));
/*     */     
/* 178 */     ObjectNode modelNode = (ObjectNode)new ObjectMapper().readTree(b);
/* 179 */     BpmnModel bpmnModel = new BpmnJsonConverter().convertToBpmnModel(modelNode);
/* 180 */     byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(bpmnModel);
/*     */     
/* 182 */     if ((StringUtil.isEmpty(bpmDef.getActDefId())) || (publish)) {
/* 183 */       b(bpmDef, model, bpmnBytes);
/*     */     } else {
/* 185 */       a(bpmDef, model, bpmnBytes);
/*     */     }
/*     */     
/* 188 */     this.repositoryService.saveModel(model);
/* 189 */     this.repositoryService.addModelEditorSourceExtra(model.getId(), result);
/*     */     
/* 191 */     DefaultBpmProcessDef def = (DefaultBpmProcessDef)this.a.initBpmProcessDef(bpmDef);
/*     */     
/*     */ 
/* 194 */     if (("deploy".equals(bpmDef.getStatus())) && ("deploy".equals(def.getExtProperties().getStatus())) && 
/* 195 */       (!AppUtil.getCtxEnvironment().contains(EnvironmentConstant.PROD.key()))) {
/* 196 */       throw new BusinessException("除了生产环境外，已发布状态的流程禁止修改！");
/*     */     }
/*     */     
/*     */ 
/* 200 */     if ((StringUtil.isEmpty(bpmDef.getStatus())) || (!bpmDef.getStatus().equals(def.getExtProperties().getStatus())) || 
/* 201 */       (!bpmDef.getSupportMobile().equals(def.getExtProperties().getSupportMobile()))) {
/* 202 */       bpmDef.setStatus(def.getExtProperties().getStatus());
/* 203 */       bpmDef.setSupportMobile(def.getExtProperties().getSupportMobile());
/* 204 */       this.d.update(bpmDef);
/*     */     }
/*     */     
/*     */ 
/* 208 */     c(bpmDef);
/*     */     
/* 210 */     outStream.close();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void a(BpmDefinition definition, Model model, byte[] bpmnBytes)
/*     */     throws JsonProcessingException, IOException
/*     */   {
/* 222 */     ProcessDefinition bpmnProcessDef = this.repositoryService.getProcessDefinition(definition.getActDefId());
/*     */     
/* 224 */     ProcessEngineConfigurationImpl conf = (ProcessEngineConfigurationImpl)this.processEngineConfiguration;
/* 225 */     Context.setProcessEngineConfiguration(conf);
/*     */     
/*     */ 
/* 228 */     DeploymentManager deploymentManager = conf.getDeploymentManager();
/* 229 */     BpmnDeployer deployer = (BpmnDeployer)deploymentManager.getDeployers().get(0);
/* 230 */     DeploymentEntity deploy = (DeploymentEntity)this.repositoryService.createDeploymentQuery().deploymentId(definition.getActDeployId()).list().get(0);
/* 231 */     ByteArrayInputStream inputStream = new ByteArrayInputStream(bpmnBytes);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 238 */     BpmnParse bpmnParse = deployer.getBpmnParser().createParse().sourceInputStream(inputStream).setSourceSystemId(model.getKey() + ".bpmn20.xml").deployment(deploy).name(model.getKey() + ".bpmn20.xml");
/*     */     
/* 240 */     bpmnParse.execute();
/* 241 */     BpmnModel bpmnModel = bpmnParse.getBpmnModel();
/*     */     
/* 243 */     deploymentManager.getBpmnModelCache().add(bpmnProcessDef.getId(), bpmnModel);
/*     */     
/* 245 */     byte[] diagramBytes = IoUtil.readInputStream(this.processEngineConfiguration.getProcessDiagramGenerator()
/* 246 */       .generateDiagram(bpmnModel, "png", this.processEngineConfiguration.getActivityFontName(), this.processEngineConfiguration.getLabelFontName(), this.processEngineConfiguration
/* 247 */       .getAnnotationFontName(), this.processEngineConfiguration.getClassLoader()), null);
/*     */     
/* 249 */     this.d.updateActResourceEntity(bpmnProcessDef.getDeploymentId(), model.getKey() + ".bpmn20.xml", bpmnBytes);
/* 250 */     this.d.updateActResourceEntity(bpmnProcessDef.getDeploymentId(), model.getKey() + "." + bpmnProcessDef.getKey() + ".png", diagramBytes);
/* 251 */     d(definition);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void b(BpmDefinition definition, Model model, byte[] bpmnBytes)
/*     */   {
/*     */     try
/*     */     {
/* 261 */       String processName = model.getKey() + ".bpmn20.xml";
/*     */       
/*     */ 
/*     */ 
/* 265 */       Deployment deployment = this.repositoryService.createDeployment().name(model.getKey()).addString(processName, new String(bpmnBytes)).deploy();
/*     */       
/* 267 */       ProcessDefinition proDefinition = (ProcessDefinition)this.repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
/* 268 */       if (proDefinition == null) throw new RuntimeException("error   ");
/* 269 */       model.setDeploymentId(deployment.getId());
/*     */       
/* 271 */       if (StringUtil.isEmpty(definition.getActDefId())) {
/* 272 */         definition.setActDefId(proDefinition.getId());
/* 273 */         definition.setActDeployId(deployment.getId());
/* 274 */         d(definition);
/* 275 */         return;
/*     */       }
/*     */       
/*     */ 
/* 279 */       String newDefId = UniqueIdUtil.getSuid();
/* 280 */       definition.setIsMain("N");
/* 281 */       definition.setMainDefId(newDefId);
/* 282 */       d(definition);
/*     */       
/*     */ 
/* 285 */       definition.setId(newDefId);
/* 286 */       definition.setIsMain("Y");
/* 287 */       definition.setRev(Integer.valueOf(0));
/* 288 */       definition.setVersion(Integer.valueOf(definition.getVersion().intValue() + 1));
/* 289 */       definition.setCreateBy(ContextUtil.getCurrentUser().getUserId());
/* 290 */       definition.setCreateTime(new Date());
/*     */       
/* 292 */       definition.setActDefId(proDefinition.getId());
/* 293 */       definition.setActDeployId(deployment.getId());
/* 294 */       definition.setActModelId(model.getId());
/* 295 */       this.d.create(definition);
/*     */     } catch (Exception e) {
/* 297 */       throw new RuntimeException("Invoke natProDefinitionService.deploy method error = " + e.getMessage());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public BpmDefinition getMainDefByActModelId(String actModelId)
/*     */   {
/* 304 */     return this.d.getMainDefByActModelId(actModelId);
/*     */   }
/*     */   
/*     */ 
/*     */   private void c(BpmDefinition def)
/*     */   {
/* 310 */     List<BpmDefinition> defList = this.d.getByKey(def.getKey());
/* 311 */     for (BpmDefinition defEntity : defList) {
/* 312 */       AppUtil.publishEvent(new BpmDefinitionUpdateEvent(defEntity));
/*     */     }
/* 314 */     AppUtil.publishEvent(new BpmDefinitionUpdateEvent(def));
/*     */   }
/*     */   
/*     */ 
/*     */   public BpmDefinition getDefinitionByActDefId(String actDefId)
/*     */   {
/* 320 */     return this.d.getByActDefId(actDefId);
/*     */   }
/*     */   
/*     */ 
/*     */   public BpmDefinition getByKey(String flowKey)
/*     */   {
/* 326 */     return this.d.getMainByDefKey(flowKey);
/*     */   }
/*     */   
/*     */   public List<BpmDefinition> getMyDefinitionList(String userId, QueryFilter queryFilter)
/*     */   {
/* 331 */     Map map = this.e.getUserRightsSql(RightsObjectConstants.FLOW, userId, null);
/* 332 */     queryFilter.addParams(map);
/*     */     
/* 334 */     return this.d.getMyDefinitionList(queryFilter);
/*     */   }
/*     */   
/*     */   public void remove(String entityId)
/*     */   {
/* 339 */     BpmDefinition definition = (BpmDefinition)this.d.get(entityId);
/*     */     
/* 341 */     if (a(definition.getId())) {
/* 342 */       throw new BusinessException("该流程定义下存在流程实例，请勿删除！<br> 请清除数据后再来删除");
/*     */     }
/*     */     
/* 345 */     List<BpmDefinition> definitionList = this.d.getByKey(definition.getKey());
/* 346 */     for (BpmDefinition def : definitionList) {
/* 347 */       AppUtil.publishEvent(new BpmDefinitionUpdateEvent(def));
/* 348 */       this.d.remove(def.getId());
/* 349 */       if (StringUtil.isNotEmpty(def.getActDeployId())) {
/* 350 */         this.repositoryService.deleteDeployment(def.getActDeployId());
/*     */       }
/*     */     }
/* 353 */     if (StringUtil.isNotEmpty(definition.getActModelId())) {
/* 354 */       this.repositoryService.deleteModel(definition.getActModelId());
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean a(String defId) {
/* 359 */     DefaultQueryFilter query = new DefaultQueryFilter();
/* 360 */     query.addFilter("def_id_", defId, QueryOP.EQUAL);
/* 361 */     List list = this.f.query(query);
/* 362 */     return BeanUtils.isNotEmpty(list);
/*     */   }
/*     */   
/*     */   public void d(BpmDefinition entity)
/*     */   {
/* 367 */     entity.setUpdateTime(new Date());
/* 368 */     int updateRows = this.d.update(entity).intValue();
/* 369 */     if (updateRows == 0) {
/* 370 */       AppUtil.publishEvent(new BpmDefinitionUpdateEvent(entity));
/* 371 */       throw new RuntimeException("流程定义更新失败，当前版本并非最新版本！已经刷新当前服务器缓存，请刷新页面重新修改提交。id:" + entity.getId() + "reversion:" + entity.getRev());
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\manager\impl\BpmDefinitionManagerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */