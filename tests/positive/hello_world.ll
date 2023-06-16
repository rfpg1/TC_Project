@.str.0 = private unnamed_addr constant [2x i8] c"2\00"
@global_k= global i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.str.0, i32 0, i32 0)
@.str.1 = private unnamed_addr constant [2x i8] c"2\00"
@global_d= global i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.str.1, i32 0, i32 0)
@.str.2 = private unnamed_addr constant [2x i8] c"2\00"
@global_s= global i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.str.2, i32 0, i32 0)
@global_f= global i32 1
@global_b= global i32 3
@global_z= global i32 2
@global_kkk= global i32 4

declare void @printf(i8* noundef, ...) #0
declare void @llvm.memcpy.p0i8.p0i8.i64(i8* noalias nocapture writeonly, i8* noalias nocapture readonly, i64, i1 immarg) #1
declare noalias noundef i8* @malloc(i64 noundef) local_unnamed_addr #2
define dso_local noalias i32* @createArray(i8* nocapture readnone %0, i32 noundef %1) local_unnamed_addr #3 { 
 %3 = sext i32 %1 to i64
 %4 = shl nsw i64 %3, 2 
 %5 = tail call noalias i8* @malloc(i64 noundef %4)
 %6 = bitcast i8* %5 to i32*
 ret i32* %6}
@.str.3 = private unnamed_addr constant [7 x i8] c"true\t\00"
declare i32 @sample_normal(i32,i32) #5
declare i32 @abs(i32) #6
@.str.4 = private unnamed_addr constant [2 x i8] c"a\00"
define i32 @main(i32 %a, i8** %b) #4 {
%pont_a = alloca i32
store i32 %a, i32* %pont_a
%pont_b = alloca i8**
store i8** %b, i8*** %pont_b
%tempV0 = load i32, i32* @global_z
%new_var2 = load i32, i32* @global_z
%callFunction_1 = call i32 @min(i32 %new_var2)
%str_value3 = mul i32 4 , %callFunction_1
%str_value4 = mul i32 %tempV0 , %str_value3
%pont_pi3 = alloca i32
store i32 %str_value4, i32* %pont_pi3
%pont_pi = alloca i1
store i1 0, i1* %pont_pi
%pont_pi1 = alloca i8*
store i8* getelementptr inbounds ([7 x i8], [7 x i8]* @.str.3 , i64 0, i64 0), i8** %pont_pi1
%pont_pi2 = alloca i32
store i32 3, i32* %pont_pi2
%pont_pi4 = alloca i32
store i32 2, i32* %pont_pi4
store i1 1, i1* %pont_pi
%new_var6 = load i32, i32* %pont_pi2
%new_var7 = load i32, i32* %pont_pi4
%callFunction_5 = call i32 @max(i32 %new_var6,i32 %new_var7)
%pont_adwadad = alloca i32
store i32 %callFunction_5, i32* %pont_adwadad
call i32 @abs(i32 2)
ret i32 2
}
define i32 @max(i32 %adawda, i32 %zczsb) #7 {
%pont_adawda = alloca i32
store i32 %adawda, i32* %pont_adawda
%pont_zczsb = alloca i32
store i32 %zczsb, i32* %pont_zczsb
br label %bExpr_8
bExpr_8:
%temp_var12 = add i32 0, 5
%temp_var13 = add i32 0, 4
%temp_var14 = icmp sgt i32 %temp_var12, %temp_var13
br i1 null, label %if_label10, label %else_label11
if_label10:
%pont_a = alloca i32
store i32 2, i32* %pont_a
br label %end_label9
else_label11:
br label %bExpr_15
bExpr_15:
%temp_var19 = icmp eq i32 1, 1
br i1 null, label %if_label17, label %end_label16
if_label17:
br label %bExpr_20
bExpr_20:
%temp_var24 = add i32 0, 1
%temp_var25 = add i32 0, 2
%temp_var26 = icmp sgt i32 %temp_var24, %temp_var25
br i1 null, label %if_label22, label %end_label21
if_label22:
%str_value27 = mul i32 3 , 4
%pont__ola = alloca i32
store i32 %str_value27, i32* %pont__ola
br label %end_label21
end_label21:
br label %bExpr_15
end_label16:
br label %end_label9
end_label9:
ret i32 2
}
define i32 @min(i32 %i) #8 {
%pont_i = alloca i32
store i32 %i, i32* %pont_i
%ret_28 = load i32, i32* %pont_i
ret i32 %ret_28
}
define i32 @mini2(i32 %i) #9 {
%pont_i = alloca i32
store i32 %i, i32* %pont_i
%ret_29 = load i32, i32* %pont_i
ret i32 %ret_29
}
define i32 @maxi(i32 %a, i32 %b) #10 {
%pont_a = alloca i32
store i32 %a, i32* %pont_a
%pont_b = alloca i32
store i32 %b, i32* %pont_b
%new_var31 = load i32, i32* %pont_a
%callFunction_30 = call i32 @min(i32 %new_var31)
%pont_z = alloca i32
store i32 %callFunction_30, i32* %pont_z
ret i32 2
}
define i8* @toString() #11 {
ret i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.str.4 , i64 0, i64 0)

}
define i1 @b() #12 {
ret i1 0
}
