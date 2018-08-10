/*    */ package com.dstz.bus.manager.impl;
/*    */ 
/*    */ import com.dstz.base.manager.impl.BaseManager;
/*    */ import com.dstz.bus.dao.BusColumnCtrlDao;
/*    */ import com.dstz.bus.manager.BusColumnCtrlManager;
/*    */ import com.dstz.bus.model.BusColumnCtrl;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class BusColumnCtrlManagerImpl
/*    */   extends BaseManager<String, BusColumnCtrl>
/*    */   implements BusColumnCtrlManager
/*    */ {
/*    */   @Resource
/*    */   BusColumnCtrlDao e;
/*    */   
/*    */   public void removeByTableId(String tableId)
/*    */   {
/* 24 */     this.e.removeByTableId(tableId);
/*    */   }
/*    */   
/*    */   public BusColumnCtrl getByColumnId(String columnId)
/*    */   {
/* 29 */     return this.e.getByColumnId(columnId);
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\bus-core\1.1.5\bus-core-1.1.5-pg.jar!\com\dstz\bus\manager\impl\BusColumnCtrlManagerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */