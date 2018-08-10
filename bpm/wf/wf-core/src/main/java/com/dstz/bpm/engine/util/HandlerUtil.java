/*    */ package com.dstz.bpm.engine.util;
/*    */ 
/*    */ import com.dstz.base.core.util.AppUtil;
/*    */ import com.dstz.base.core.util.StringUtil;
/*    */ import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
/*    */ import java.lang.reflect.Method;
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
/*    */ public class HandlerUtil
/*    */ {
/*    */   public static int h(String handler)
/*    */   {
/* 22 */     if (handler.indexOf(".") == -1) return -1;
/* 23 */     String[] aryHandler = handler.split("[.]");
/* 24 */     if (aryHandler.length != 2) return -1;
/* 25 */     String beanId = aryHandler[0];
/* 26 */     String method = aryHandler[1];
/* 27 */     Object serviceBean = null;
/*    */     try {
/* 29 */       serviceBean = AppUtil.getBean(beanId);
/*    */     } catch (Exception ex) {
/* 31 */       return -2;
/*    */     }
/* 33 */     if (serviceBean == null) return -2;
/*    */     try
/*    */     {
/* 36 */       Method invokeMethod = serviceBean.getClass().getDeclaredMethod(method, new Class[] { ActionCmd.class });
/*    */       
/* 38 */       return 0;
/*    */     } catch (NoSuchMethodException e) {
/* 40 */       return -3;
/*    */     } catch (Exception e) {}
/* 42 */     return -4;
/*    */   }
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
/*    */   public static void a(ActionCmd actionModel, String handler)
/*    */     throws Exception
/*    */   {
/* 57 */     if (StringUtil.isEmpty(handler)) return;
/* 58 */     String[] aryHandler = handler.split("[.]");
/* 59 */     if ((aryHandler == null) || (aryHandler.length != 2)) return;
/* 60 */     String beanId = aryHandler[0];
/* 61 */     String method = aryHandler[1];
/*    */     
/* 63 */     Object serviceBean = AppUtil.getBean(beanId);
/*    */     
/* 65 */     if (serviceBean == null) { return;
/*    */     }
/* 67 */     Method invokeMethod = serviceBean.getClass().getDeclaredMethod(method, new Class[] { ActionCmd.class });
/* 68 */     invokeMethod.invoke(serviceBean, new Object[] { actionModel });
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\util\HandlerUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */