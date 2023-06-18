@.str.0 = private unnamed_addr constant [2x i8] c"a\00"
@global_d= global i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.str.0, i32 0, i32 0)
@.str.1 = private unnamed_addr constant [2x i8] c"a\00"
@global_e= global i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.str.1, i32 0, i32 0)

declare void @printf(i8* noundef, ...) #0
declare void @llvm.memcpy.p0i8.p0i8.i64(i8* noalias nocapture writeonly, i8* noalias nocapture readonly, i64, i1 immarg) #1
declare noalias noundef i8* @malloc(i64 noundef) local_unnamed_addr #2
define dso_local noalias i32* @createArray(i8* nocapture readnone %0, i32 noundef %1) local_unnamed_addr #3 { 
 %3 = sext i32 %1 to i64
 %4 = shl nsw i64 %3, 2 
 %5 = tail call noalias i8* @malloc(i64 noundef %4)
 %6 = bitcast i8* %5 to i32*
 ret i32* %6}
define i32 @max(i32 %a, i32 %b) #4 {
%pont_a = alloca i32
store i32 %a, i32* %pont_a
%pont_b = alloca i32
store i32 %b, i32* %pont_b
br label %bExpr_0
bExpr_0:
%temp_var4 = load i32, i32* %pont_a
%temp_var5 = load i32, i32* %pont_b
%temp_var6 = icmp sgt i32 %temp_var4, %temp_var5
br i1 %temp_var6, label %end_label1, label %end_label1
end_label1:
%ret_7 = load i32, i32* %pont_b
ret i32 %ret_7
}
define i32 @min(i32 %a, i32 %b) #5 {
%pont_a = alloca i32
store i32 %a, i32* %pont_a
%pont_b = alloca i32
store i32 %b, i32* %pont_b
%pont_c = alloca i32
store i32 3, i32* %pont_c
%pont_d = alloca i32
store i32 2, i32* %pont_d
store i32 c, i32* @global_d
br label %bExpr_8
bExpr_8:
%temp_var12 = load i32, i32* %pont_a
%temp_var13 = load i32, i32* %pont_b
%temp_var14 = icmp sgt i32 %temp_var12, %temp_var13
br i1 %temp_var14, label %end_label9, label %end_label9
end_label9:
br label %bExpr_15
bExpr_15:
%temp_var19 = load i32, i32* %pont_b
%temp_var20 = load i32, i32* %pont_a
%temp_var21 = icmp sgt i32 %temp_var19, %temp_var20
br i1 %temp_var21, label %if_label17, label %end_label16
if_label17:
store i32 1, i32* %pont_a
store i32 2, i32* %pont_b
br label %bExpr_15
end_label16:
%ret_22 = load i32, i32* %pont_b
ret i32 %ret_22
}
