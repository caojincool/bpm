/*    */ package com.dstz.bpm.plugin.task.sign.def;
/*    */ 
/*    */ import com.dstz.bpm.api.constant.ActionType;
/*    */ import com.dstz.bpm.engine.plugin.plugindef.AbstractBpmTaskPluginDef;
/*    */ import javax.validation.constraints.Min;
/*    */ import org.hibernate.validator.constraints.NotBlank;
/*    */ import org.hibernate.validator.constraints.NotEmpty;
/*    */ 
/*    */ public class SignTaskPluginDef extends AbstractBpmTaskPluginDef
/*    */ {
/*    */   private Boolean T;
/*    */   private VoteType U;
/*    */   private Boolean V;
/*    */   @NotEmpty
/*    */   @Min(1L)
/*    */   private int W;
/*    */   
/*    */   public SignTaskPluginDef()
/*    */   {
/* 20 */     this.T = Boolean.valueOf(false);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/* 25 */     this.U = VoteType.PERCENT;
/*    */     
/*    */ 
/*    */ 
/*    */ 
/* 30 */     this.V = Boolean.valueOf(false);
/*    */     
/*    */ 
/*    */ 
/* 34 */     this.W = 51;
/*    */   }
/*    */   
/*    */   @NotBlank
/* 38 */   private String X = ActionType.OPPOSE
/* 39 */     .getKey();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public VoteType getVoteType()
/*    */   {
/* 50 */     return this.U;
/*    */   }
/*    */   
/*    */   public void setVoteType(VoteType voteType) {
/* 54 */     this.U = voteType;
/*    */   }
/*    */   
/*    */   public Boolean getNeedAllSign() {
/* 58 */     return this.V;
/*    */   }
/*    */   
/*    */   public void setNeedAllSign(Boolean needAllSign) {
/* 62 */     this.V = needAllSign;
/*    */   }
/*    */   
/*    */   public int getVoteAmount() {
/* 66 */     return this.W;
/*    */   }
/*    */   
/*    */   public void setVoteAmount(int voteAmount) {
/* 70 */     this.W = voteAmount;
/*    */   }
/*    */   
/*    */   public String getOpposedAction() {
/* 74 */     return this.X;
/*    */   }
/*    */   
/*    */   public void setOpposedAction(String opposedAction) {
/* 78 */     this.X = opposedAction;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\task\sign\def\SignTaskPluginDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */