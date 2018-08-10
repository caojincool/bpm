/*    */ package com.dstz.bpm.service;
/*    */ 
/*    */ import com.dstz.base.api.exception.BusinessException;
/*    */ import com.dstz.base.core.util.BeanUtils;
/*    */ import com.dstz.base.core.util.StringUtil;
/*    */ import com.dstz.bpm.api.engine.data.result.BpmFlowData;
/*    */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*    */ import com.dstz.bpm.api.model.form.BpmForm;
/*    */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*    */ import com.dstz.bpm.api.service.BpmProcessDefService;
/*    */ import com.dstz.bpm.api.service.BpmRightsFormService;
/*    */ import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
/*    */ import com.dstz.bus.api.constant.BusinessPermissionObjType;
/*    */ import com.dstz.bus.api.model.IBusinessPermission;
/*    */ import com.dstz.bus.api.service.IBusinessPermissionService;
/*    */ import com.dstz.form.api.model.FormCategory;
/*    */ import com.dstz.form.api.model.FormType;
/*    */ import java.util.Set;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component("defaultBpmFormService")
/*    */ public class DefaultBpmRightsFormService
/*    */   implements BpmRightsFormService
/*    */ {
/*    */   @Resource
/*    */   BpmProcessDefService a;
/*    */   @Resource
/*    */   IBusinessPermissionService businessPermissionService;
/*    */   
/*    */   public IBusinessPermission getInstanceFormPermission(BpmFlowData flowData, String nodeId, FormType formType, boolean isReadOnly)
/*    */   {
/* 38 */     IBusinessPermission permision = null;
/* 39 */     BpmForm form = null;
/* 40 */     boolean isMobile = FormType.MOBILE == formType;
/* 41 */     DefaultBpmProcessDef processDef = (DefaultBpmProcessDef)this.a.getBpmProcessDef(flowData.getDefId());
/*    */     
/*    */ 
/* 44 */     if (StringUtil.isEmpty(nodeId)) {
/* 45 */       form = isMobile ? processDef.getInstMobileForm() : processDef.getInstForm();
/* 46 */       nodeId = "instance";
/*    */     } else {
/* 48 */       BpmNodeDef nodeDef = this.a.getBpmNodeDef(flowData.getDefId(), nodeId);
/* 49 */       form = isMobile ? nodeDef.getMobileForm() : nodeDef.getForm();
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 54 */     if ((form == null) || (form.isFormEmpty())) {
/* 55 */       form = isMobile ? processDef.getGlobalMobileForm() : processDef.getGlobalForm();
/* 56 */       nodeId = "global";
/*    */     }
/*    */     
/* 59 */     if ((form == null) || (form.isFormEmpty())) {
/* 60 */       throw new BusinessException(String.format("请配置%s[%s]的表单", new Object[] { processDef.getDefKey(), nodeId }), BpmStatusCode.FLOW_FORM_LOSE);
/*    */     }
/*    */     
/*    */ 
/* 64 */     if (FormCategory.INNER.equals(form.getType())) {
/* 65 */       permision = this.businessPermissionService.getByObjTypeAndObjVal(BusinessPermissionObjType.FLOW.getKey(), processDef.getDefKey() + "-" + nodeId, processDef.getDataModelKeys(), true);
/* 66 */       flowData.setPermission(permision.getPermission(isReadOnly));
/* 67 */       flowData.setTablePermission(permision.getTablePermission(isReadOnly));
/*    */     }
/*    */     
/* 70 */     flowData.setForm(form);
/*    */     
/* 72 */     return permision;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public IBusinessPermission getNodeSavePermission(String defKey, String nodeId, Set<String> bocodes)
/*    */   {
/* 79 */     String boCodes = null;
/* 80 */     if (BeanUtils.isNotEmpty(bocodes)) {
/* 81 */       boCodes = StringUtil.convertCollectionAsString(bocodes);
/*    */     }
/*    */     
/* 84 */     return this.businessPermissionService.getByObjTypeAndObjVal(BusinessPermissionObjType.FLOW.getKey(), defKey + "-" + nodeId, boCodes, true);
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\service\DefaultBpmRightsFormService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */