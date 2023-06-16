
declare void @printf(i8* noundef, ...) #0
declare void @llvm.memcpy.p0i8.p0i8.i64(i8* noalias nocapture writeonly, i8* noalias nocapture readonly, i64, i1 immarg) #1
declare noalias noundef i8* @malloc(i64 noundef) local_unnamed_addr #2
define dso_local noalias i32* @createArray(i8* nocapture readnone %0, i32 noundef %1) local_unnamed_addr #3 { 
 %3 = sext i32 %1 to i64
 %4 = shl nsw i64 %3, 2 
 %5 = tail call noalias i8* @malloc(i64 noundef %4)
 %6 = bitcast i8* %5 to i32*
 ret i32* %6}
@.str.0 = private unnamed_addr constant [2 x i8] c"2\00"
@.str.1 = private unnamed_addr constant [2 x i8] c"a\00"
@.str.2 = private unnamed_addr constant [2 x i8] c"b\00"
@.str.3 = private unnamed_addr constant [4 x i8] c"acd\00"
@.str.4 = private unnamed_addr constant [5 x i8] c"doub\00"
@.str.5 = private unnamed_addr constant [3 x i8] c"%s\00"
@.str.6 = private unnamed_addr constant [3 x i8] c"%s\00"
@.str.7 = private unnamed_addr constant [3 x i8] c"%s\00"
@.str.8 = private unnamed_addr constant [3 x i8] c"%s\00"
@.str.9 = private unnamed_addr constant [3 x i8] c"%d\00"
@.str.10 = private unnamed_addr constant [3 x i8] c"%i\00"
@.str.11 = private unnamed_addr constant [3 x i8] c"%i\00"
define void @main(i32 %a, i8** %i) #4 {
%pont_a = alloca i32
store i32 %a, i32* %pont_a
%pont_i = alloca i8**
store i8** %i, i8*** %pont_i
%temp_var0 = load i8**, i8*** %pont_i
%temp_var1 = getelementptr inbounds i8*, i8** %temp_var0, i64 0
store i8* getelementptr inbounds([2 x i8], [2 x i8]* @.str.0, i64 0, i64 0), i8** %temp_var1
%pont_s = alloca i8*
store i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.str.1 , i64 0, i64 0), i8** %pont_s
store i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.str.2 , i64 0, i64 0), i8** %pont_s
%callFunction_2 = call i32* @createArray(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.3 , i64 0, i64 0),i32 2)
%pont_acd = alloca i32*
store i32* %callFunction_2, i32** %pont_acd
%callFunction_3 = call i32* @createArray(i8* getelementptr inbounds ([5 x i8], [5 x i8]* @.str.4 , i64 0, i64 0),i32 2)
%pont_doub = alloca i32*
store i32* %callFunction_3, i32** %pont_doub
%temp_var4 = load i32*, i32** %pont_doub
%temp_var5 = getelementptr inbounds i32, i32* %temp_var4, i64 0
store i32 1, i32* %temp_var5
%temp_var6 = load i8**, i8*** %pont_i
%temp_var7 = getelementptr inbounds i8*, i8** %temp_var6, i32 0
%result_var8 = load i8*, i8** %temp_var7
%pont_dwa = alloca i8*
store i8* %result_var8, i8** %pont_dwa
%new_var9 = load i8**, i8*** %pont_i
%temp_var10 = getelementptr inbounds i8*, i8** %new_var9, i64 0
%temp_var11 = load i8*, i8** %temp_var10
call void(i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.5 , i64 0, i64 0),i8* %temp_var11)
%new_var12 = load i8*, i8** %pont_s
call void(i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.6 , i64 0, i64 0),i8* %new_var12)
%new_var13 = load i8**, i8*** %pont_i
%temp_var14 = getelementptr inbounds i8*, i8** %new_var13, i64 2
%temp_var15 = load i8*, i8** %temp_var14
call void(i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.7 , i64 0, i64 0),i8* %temp_var15)
%new_var16 = load i32*, i32** %pont_acd
call i32 @ac(i32* %new_var16)
%new_var17 = load i8*, i8** %pont_dwa
call void(i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.8 , i64 0, i64 0),i8* %new_var17)
%new_var18 = load i32*, i32** %pont_doub
%temp_var19 = getelementptr inbounds i32, i32* %new_var18, i64 0
%temp_var20 = load i32, i32* %temp_var19
call void(i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.9 , i64 0, i64 0),i32 %temp_var20)
ret void
}
define i32 @ac(i32* %acd) #5 {
%pont_acd = alloca i32*
store i32* %acd, i32** %pont_acd
%temp_var21 = load i32*, i32** %pont_acd
%temp_var22 = getelementptr inbounds i32, i32* %temp_var21, i64 1
store i32 2, i32* %temp_var22
%new_var23 = load i32*, i32** %pont_acd
%temp_var24 = getelementptr inbounds i32, i32* %new_var23, i64 0
%temp_var25 = load i32, i32* %temp_var24
call void(i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.10 , i64 0, i64 0),i32 %temp_var25)
%new_var26 = load i32*, i32** %pont_acd
%temp_var27 = getelementptr inbounds i32, i32* %new_var26, i64 1
%temp_var28 = load i32, i32* %temp_var27
call void(i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.11 , i64 0, i64 0),i32 %temp_var28)
ret i32 1
}
