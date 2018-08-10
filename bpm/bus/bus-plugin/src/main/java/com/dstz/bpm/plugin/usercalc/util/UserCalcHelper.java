/*    */ package com.dstz.bpm.plugin.usercalc.util;
/*    */ 
/*    */ import com.dstz.base.core.util.BeanUtils;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
/*    */ import com.dstz.bpm.plugin.usercalc.user.def.ExecutorVar;
/*    */ import com.dstz.bus.api.model.IBusinessData;
/*    */ import com.dstz.org.api.model.IGroup;
/*    */ import com.dstz.org.api.model.IUser;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Map;
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
/*    */ 
/*    */ 
/*    */ public class UserCalcHelper
/*    */ {
/*    */   public static List<String> a(ExecutorVar executorVar, BpmUserCalcPluginSession pluginSession, boolean turnKeys2Ids)
/*    */   {
/* 31 */     Map<String, Object> vars = null;
/*    */     
/*    */ 
/* 34 */     List<String> ids = new ArrayList();
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 44 */     String PK = "";
/*    */     Map<String, IBusinessData> boMap;
/*    */     IBusinessData boData;
/* 47 */     if ("BO".equals(executorVar.getSource())) {
/* 48 */       String[] BOData = executorVar.getName().split("\\.");
/*    */       
/*    */ 
/* 51 */       boMap = null;
/* 52 */       boData = (IBusinessData)boMap.get(BOData[0]);
/* 53 */       PK = boData.getString(BOData[1]);
/*    */ 
/*    */ 
/*    */     }
/* 57 */     else if ("flowVar".equals(executorVar.getSource())) {
/* 58 */       PK = (String)vars.get(executorVar.getName());
/*    */     }
/*    */     
/* 61 */     String[] PKs = PK.split(",");
/* 62 */     if (BeanUtils.isEmpty(PK)) { return Collections.emptyList();
/*    */     }
/*    */     
/* 65 */     if (executorVar.getExecutorType().equals("fixed")) {
/* 66 */       ids.addAll(Arrays.asList(PKs));
/* 67 */       return ids;
/*    */     }
/*    */     
/*    */     String account;
/* 71 */     if ("user".equals(executorVar.getExecutorType())) {
/* 72 */       if (("userId".equals(executorVar.getUserValType())) || (!turnKeys2Ids)) {
/* 73 */         ids.addAll(Arrays.asList(PKs));
/*    */       }
/*    */       else {
/* 76 */         boMap = PKs;boData = boMap.length; for (IBusinessData localIBusinessData1 = 0; localIBusinessData1 < boData; localIBusinessData1++) { account = boMap[localIBusinessData1];
/* 77 */           IUser u = null;
/* 78 */           if (u != null) { ids.add(u.getUserId());
/*    */           }
/*    */           
/*    */         }
/*    */       }
/*    */     }
/* 84 */     else if (("groupId".equals(executorVar.getGroupValType())) || (!turnKeys2Ids)) {
/* 85 */       ids.addAll(Arrays.asList(PKs));
/*    */     }
/*    */     else {
/* 88 */       String dimension = executorVar.getDimension();
/* 89 */       boData = PKs;String str1 = boData.length; for (account = 0; account < str1; account++) { String groupKey = boData[account];
/* 90 */         IGroup group = null;
/* 91 */         if (group != null) { ids.add(group.getGroupId());
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 96 */     return ids;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\usercalc\util\UserCalcHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */