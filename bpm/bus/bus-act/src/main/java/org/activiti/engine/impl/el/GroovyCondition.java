/*    */ package org.activiti.engine.impl.el;
/*    */ 
/*    */ import com.dstz.base.api.exception.BusinessException;
/*    */ import com.dstz.base.core.util.AppUtil;
/*    */ import com.dstz.bpm.api.constant.ActionType;
/*    */ import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
/*    */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*    */ import com.dstz.bpm.api.engine.context.BpmContext;
/*    */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*    */ import com.dstz.sys.api.groovy.IGroovyScriptEngine;
/*    */ import java.util.Map;
/*    */ import org.activiti.engine.delegate.DelegateExecution;
/*    */ import org.activiti.engine.impl.Condition;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GroovyCondition
/*    */   implements Condition
/*    */ {
/*    */   private static final long serialVersionUID = -5577703954744892854L;
/* 29 */   private String script = "";
/*    */   
/*    */   public GroovyCondition(String condition) {
/* 32 */     this.script = condition;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean evaluate(String arg0, DelegateExecution execution)
/*    */   {
/* 38 */     Map<String, Object> maps = execution.getVariables();
/*    */     
/* 40 */     maps.put("variableScope", execution);
/* 41 */     ActionCmd cmd = BpmContext.getActionModel();
/* 42 */     maps.putAll(cmd.getBizDataMap());
/*    */     
/* 44 */     BaseActionCmd submitAction = (BaseActionCmd)BpmContext.submitActionModel();
/* 45 */     maps.put("submitActionName", submitAction.getActionType().getKey());
/*    */     
/* 47 */     IGroovyScriptEngine engine = (IGroovyScriptEngine)AppUtil.getBean(IGroovyScriptEngine.class);
/*    */     try {
/* 49 */       return engine.executeBoolean(this.script, maps);
/*    */     } catch (Exception e) {
/* 51 */       e.printStackTrace();
/* 52 */       StringBuffer message = new StringBuffer("条件脚本解析异常！请联系管理员。");
/* 53 */       message.append("\n节点：" + execution.getCurrentActivityName() + "——" + execution.getCurrentActivityId());
/* 54 */       message.append("\n脚本：" + this.script);
/* 55 */       message.append("\n异常：" + e.getMessage());
/* 56 */       message.append("\n\n流程变量：" + maps.toString());
/* 57 */       throw new BusinessException(message.toString(), BpmStatusCode.GATEWAY_ERROR, e);
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-act\1.1.5\wf-act-1.1.5-pg.jar!\org\activiti\engine\impl\el\GroovyCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */