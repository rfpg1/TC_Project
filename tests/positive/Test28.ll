
declare void @printf(i8* noundef, ...) #0
declare void @llvm.memcpy.p0i8.p0i8.i64(i8* noalias nocapture writeonly, i8* noalias nocapture readonly, i64, i1 immarg) #1
declare noalias noundef i8* @malloc(i64 noundef) local_unnamed_addr #2
define dso_local noalias i32* @createArray(i8* nocapture readnone %0, i32 noundef %1) local_unnamed_addr #3 { 
 %3 = sext i32 %1 to i64
 %4 = shl nsw i64 %3, 2 
 %5 = tail call noalias i8* @malloc(i64 noundef %4)
 %6 = bitcast i8* %5 to i32*
 ret i32* %6}
define void @main(i32 %a, i8** %b) #4 {
%pont_a = alloca i32
store i32 %a, i32* %pont_a
%pont_b = alloca i8**
store i8** %b, i8*** %pont_b
br label %bExpr_0
bExpr_0:
%temp_var4 = icmp eq i32 1, 1
%temp_var5 = icmp eq i32 0, 1
%temp_var6 = and i1 %temp_var4, %temp_var5
br i1 %temp_var6, label %if_label2, label %end_label1
if_label2:
br label %bExpr_7
bExpr_7:
%temp_var11 = icmp eq i32 0, 1
br i1 %temp_var11, label %if_label9, label %end_label8
if_label9:
%pont__ola = alloca i32
store i32 3, i32* %pont__ola
br label %end_label8
end_label8:
br label %bExpr_0
end_label1:
ret void
}
