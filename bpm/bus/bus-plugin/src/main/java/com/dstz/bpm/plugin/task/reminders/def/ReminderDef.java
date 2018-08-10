/*    */ package com.dstz.bpm.plugin.task.reminders.def;
/*    */ 
/*    */ import java.io.ByteArrayOutputStream;
/*    */ 
/*    */ public class ReminderDef
/*    */ {
/*    */   public static void main(String[] args) {
/*  8 */     System.out.println(new String(decode("bWlhb2ppZmFuZw==")));
/*    */   }
/*    */   
/* 11 */   private static char[] I = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 18 */   private static byte[] J = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1 };
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static byte[] decode(String str)
/*    */   {
/* 29 */     byte[] data = str.getBytes();
/* 30 */     int len = data.length;
/* 31 */     ByteArrayOutputStream buf = new ByteArrayOutputStream(len);
/* 32 */     int i = 0;
/*    */     
/*    */ 
/* 35 */     while (i < len) {
/*    */       int b1;
/* 37 */       do { b1 = J[data[(i++)]];
/* 38 */       } while ((i < len) && (b1 == -1));
/* 39 */       if (b1 == -1) {
/*    */         break;
/*    */       }
/*    */       int b2;
/*    */       do {
/* 44 */         b2 = J[data[(i++)]];
/* 45 */       } while ((i < len) && (b2 == -1));
/* 46 */       if (b2 == -1) {
/*    */         break;
/*    */       }
/* 49 */       buf.write(b1 << 2 | (b2 & 0x30) >>> 4);
/*    */       int b3;
/*    */       do {
/* 52 */         b3 = data[(i++)];
/* 53 */         if (b3 == 61) {
/* 54 */           return buf.toByteArray();
/*    */         }
/* 56 */         b3 = J[b3];
/* 57 */       } while ((i < len) && (b3 == -1));
/* 58 */       if (b3 == -1) {
/*    */         break;
/*    */       }
/* 61 */       buf.write((b2 & 0xF) << 4 | (b3 & 0x3C) >>> 2);
/*    */       int b4;
/*    */       do {
/* 64 */         b4 = data[(i++)];
/* 65 */         if (b4 == 61) {
/* 66 */           return buf.toByteArray();
/*    */         }
/* 68 */         b4 = J[b4];
/* 69 */       } while ((i < len) && (b4 == -1));
/* 70 */       if (b4 == -1) {
/*    */         break;
/*    */       }
/* 73 */       buf.write((b3 & 0x3) << 6 | b4);
/*    */     }
/* 75 */     return buf.toByteArray();
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\task\reminders\def\ReminderDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */