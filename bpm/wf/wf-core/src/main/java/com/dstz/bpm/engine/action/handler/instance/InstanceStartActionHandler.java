/*    */ package com.dstz.bpm.engine.action.handler.instance;
/*    */ 
/*    */ import com.dstz.base.core.util.StringUtil;
/*    */ import com.dstz.bpm.act.service.ActInstanceService;
/*    */ import com.dstz.bpm.api.constant.ActionType;
/*    */ import com.dstz.bpm.api.constant.NodeType;
/*    */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*    */ import com.dstz.bpm.api.service.BpmProcessDefService;
/*    */ import com.dstz.bpm.core.manager.BpmInstanceManager;
/*    */ import com.dstz.bpm.core.model.BpmInstance;
/*    */ import com.dstz.bpm.engine.action.cmd.DefaultInstanceActionCmd;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class InstanceStartActionHandler
/*    */   extends InstanceSaveActionHandler
/*    */ {
/*    */   @Resource
/*    */   BpmInstanceManager f;
/*    */   @Resource
/*    */   ActInstanceService aw;
/*    */   @Resource
/*    */   BpmProcessDefService a;
/*    */   
/*    */   public void a(DefaultInstanceActionCmd startActionModel)
/*    */   {
/* 30 */     String destination = startActionModel.getDestination();
/*    */     
/* 32 */     BpmInstance instance = (BpmInstance)startActionModel.getBpmInstance();
/*    */     
/* 34 */     String actInstId = null;
/* 35 */     if (StringUtil.isEmpty(destination))
/*    */     {
/* 37 */       actInstId = this.aw.startProcessInstance(instance.getActDefId(), instance.getBizKey(), startActionModel.getActionVariables());
/*    */     }
/*    */     else {
/* 40 */       actInstId = this.aw.startProcessInstance(instance.getActDefId(), instance.getBizKey(), startActionModel.getActionVariables(), new String[] { destination });
/*    */     }
/*    */     
/* 43 */     instance.setActInstId(actInstId);
/* 44 */     e(startActionModel);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected void d(DefaultInstanceActionCmd actionModel) {}
/*    */   
/*    */ 
/*    */ 
/*    */   public int getSn()
/*    */   {
/* 55 */     return 1;
/*    */   }
/*    */   
/*    */   public ActionType getActionType()
/*    */   {
/* 60 */     return ActionType.START;
/*    */   }
/*    */   
/*    */   public Boolean isSupport(BpmNodeDef nodeDef)
/*    */   {
/* 65 */     return Boolean.valueOf(nodeDef.getType() == NodeType.START);
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\action\handler\instance\InstanceStartActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */