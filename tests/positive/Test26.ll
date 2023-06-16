
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
%pont_cd = alloca i1
store i1 1, i1* %pont_cd
br label %bExpr_0
bExpr_0:
%temp_var4 = load i1, i1* %pont_cd
%callFunction_6 = call i1 @test()
%temp_var7 = icmp eq i32 1, 1
%temp_var8 = and i1 %temp_var4, %callFunction_6
%temp_var9 = or i1 %temp_var8, %temp_var4
br i1 %temp_var9, label %if_label2, label %end_label1
if_label2:
br label %bExpr_10
bExpr_10:
%temp_var14 = icmp eq i32 1, 1
%temp_var16 = alloca double
store double 45.0, double* %temp_var16
%load_double17 = load double, double* %temp_var16
%temp_var18 = fptosi double %load_double17 to i32
%str_value19 = add i32 2 , %temp_var18
%str_value20 = mul i32 2 , %str_value19
%temp_var21 = add i32 0, 2
%temp_var22 = icmp sgt i32 %str_value20, %temp_var21
%temp_var23 = and i1 %temp_var14, %temp_var22
br i1 %temp_var23, label %if_label12, label %end_label11
if_label12:
%pont__ola = alloca i32
store i32 3, i32* %pont__ola
br label %end_label11
end_label11:
br label %end_label1
end_label1:
ret i32 0
}
define i1 @test() #5 {
ret i1 1
}
