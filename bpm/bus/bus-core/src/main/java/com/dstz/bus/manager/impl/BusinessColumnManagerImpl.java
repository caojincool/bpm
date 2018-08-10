/*    */ package com.dstz.bus.manager.impl;
/*    */ 
/*    */ import com.dstz.base.manager.impl.BaseManager;
/*    */ import com.dstz.bus.dao.BusinessColumnDao;
/*    */ import com.dstz.bus.manager.BusinessColumnManager;
/*    */ import com.dstz.bus.model.BusinessColumn;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class BusinessColumnManagerImpl
/*    */   extends BaseManager<String, BusinessColumn>
/*    */   implements BusinessColumnManager
/*    */ {
/*    */   @Resource
/*    */   BusinessColumnDao f;
/*    */   
/*    */   public void removeByTableId(String tableId)
/*    */   {
/* 26 */     this.f.removeByTableId(tableId);
/*    */   }
/*    */   
/*    */   public List<BusinessColumn> getByTableId(String tableId)
/*    */   {
/* 31 */     return this.f.getByTableId(tableId);
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\manager\impl\BusinessColumnManagerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */