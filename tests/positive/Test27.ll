
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
%temp_var4 = icmp eq i32 1, 1
%temp_var5 = icmp eq i32 0, 1
%temp_var6 = icmp eq i32 1, 1
%temp_var7 = and i1 %temp_var4, %temp_var5
%temp_var8 = or i1 %temp_var7, %temp_var4
br i1 %temp_var8, label %end_label1, label %end_label1
end_label1:
%ret_9 = load i32, i32* %pont_a
ret i32 %ret_9
}
