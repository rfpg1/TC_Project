
declare void @printf(i8* noundef, ...) #0
declare void @llvm.memcpy.p0i8.p0i8.i64(i8* noalias nocapture writeonly, i8* noalias nocapture readonly, i64, i1 immarg) #1
declare noalias noundef i8* @malloc(i64 noundef) local_unnamed_addr #2
define dso_local noalias i32* @createArray(i8* nocapture readnone %0, i32 noundef %1) local_unnamed_addr #3 { 
 %3 = sext i32 %1 to i64
 %4 = shl nsw i64 %3, 2 
 %5 = tail call noalias i8* @malloc(i64 noundef %4)
 %6 = bitcast i8* %5 to i32*
 ret i32* %6}
define i32 @main(i32 %a, i8** %b) #4 {
%pont_a = alloca i32
store i32 %a, i32* %pont_a
%pont_b = alloca i8**
store i8** %b, i8*** %pont_b
%pont_c = alloca i32
store i32 2, i32* %pont_c
br label %bExpr_0
bExpr_0:
%temp_var5 = alloca double
store double 4.5, double* %temp_var5
%load_double6 = load double, double* %temp_var5
%temp_var7 = fptosi double %load_double6 to i32
%str_value8 = add i32 %temp_var7 , 6
%str_value9 = mul i32 45 , %str_value8
%temp_var10 = load i32, i32* %pont_c
%temp_var11 = icmp eq i32 %str_value9, %temp_var10
br i1 %temp_var11, label %end_label1, label %end_label1
end_label1:
%temp_var12 = alloca double
store double 5.0, double* %temp_var12
%load_double13 = load double, double* %temp_var12
%temp_var14 = fptosi double %load_double13 to i32
%str_value15 = mul i32 4 , %temp_var14
%temp16 = alloca i32
store i32 %str_value15, i32* %temp16
%ret_17 = load i32, i32* %temp16
ret i32 %ret_17
}
