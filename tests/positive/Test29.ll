
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
%pont_i = alloca i32
store i32 1, i32* %pont_i
ret void
}
