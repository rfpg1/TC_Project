@global_d= global i32 4
@global_x= global i32 4

declare void @printf(i8* noundef, ...) #0
declare void @llvm.memcpy.p0i8.p0i8.i64(i8* noalias nocapture writeonly, i8* noalias nocapture readonly, i64, i1 immarg) #1
declare noalias noundef i8* @malloc(i64 noundef) local_unnamed_addr #2
define dso_local noalias i32* @createArray(i8* nocapture readnone %0, i32 noundef %1) local_unnamed_addr #3 { 
 %3 = sext i32 %1 to i64
 %4 = shl nsw i64 %3, 2 
 %5 = tail call noalias i8* @malloc(i64 noundef %4)
 %6 = bitcast i8* %5 to i32*
 ret i32* %6}
declare i32 @abs(i32) #5
@.str.0 = private unnamed_addr constant [3 x i8] c"%d\00"
@.str.1 = private unnamed_addr constant [8 x i8] c"a%i%s%i\00"
@.str.2 = private unnamed_addr constant [2 x i8] c"a\00"
define void @main(i32 %a, i8** %b) #4 {
%pont_a = alloca i32
store i32 %a, i32* %pont_a
%pont_b = alloca i8**
store i8** %b, i8*** %pont_b
%pont_d = alloca i32
store i32 4, i32* %pont_d
store i32 5, i32* @global_d
%temp_var0 = alloca double
store double 2.0, double* %temp_var0
%load_double1 = load double, double* %temp_var0
%temp_var2 = fptosi double %load_double1 to i32
%str_value3 = mul i32 2 , %temp_var2
call void(i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.0 , i64 0, i64 0),i32 %str_value3)
%temp_var4 = alloca double
store double 2.0, double* %temp_var4
%load_double5 = load double, double* %temp_var4
%temp_var6 = fptosi double %load_double5 to i32
%str_value7 = mul i32 2 , %temp_var6
call i32 @a(i32 %str_value7)
call void(i8*, ...) @printf(i8* getelementptr inbounds ([8 x i8], [8 x i8]* @.str.1 , i64 0, i64 0),i32 1,i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.str.2 , i64 0, i64 0),i32 3)
call i32 @abs(i32 2)
call i32 @b()
br label %bExpr_8
bExpr_8:
%temp_var12 = icmp eq i32 0, 1
%temp_var13 = icmp eq i32 0, 1
%temp_var14 = or i1 %temp_var12, %temp_var13
br i1 %temp_var14, label %if_label10, label %end_label9
if_label10:
%pont_c = alloca i32
store i32 2, i32* %pont_c
br label %bExpr_8
end_label9:
ret void
}
define i32 @b() #6 {
ret i32 2
}
define i32 @a(i32 %c) #7 {
%pont_c = alloca i32
store i32 %c, i32* %pont_c
%ret_15 = load i32, i32* %pont_c
ret i32 %ret_15
}
