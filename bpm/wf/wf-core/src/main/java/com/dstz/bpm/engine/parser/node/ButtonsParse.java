/*    */ package com.dstz.bpm.engine.parser.node;
/*    */ 
/*    */ import com.alibaba.fastjson.JSON;
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.base.core.util.BeanUtils;
/*    */ import com.dstz.bpm.api.model.nodedef.Button;
/*    */ import com.dstz.bpm.api.model.nodedef.impl.BaseBpmNodeDef;
/*    */ import java.util.List;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class ButtonsParse
/*    */   extends AbsNodeParse<Button>
/*    */ {
/*    */   public String getKey()
/*    */   {
/* 19 */     return "btnList";
/*    */   }
/*    */   
/*    */   public void a(BaseBpmNodeDef userNodeDef, Object object)
/*    */   {
/* 24 */     List<Button> btnList = (List)object;
/*    */     
/* 26 */     userNodeDef.setButtons(btnList);
/*    */   }
/*    */   
/*    */   public boolean isArray()
/*    */   {
/* 31 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void a(BaseBpmNodeDef userNodeDef, Object object, JSON thisJson)
/*    */   {
/* 39 */     JSONObject jsonConfig = (JSONObject)thisJson;
/*    */     
/* 41 */     if (BeanUtils.isEmpty(object)) {
/* 42 */       jsonConfig.put("btnList", JSON.toJSON(userNodeDef.getButtons()));
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\parser\node\ButtonsParse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */