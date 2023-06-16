(* Testing declarations * )
sample_normal:Double (mean:Double, stddev:Double where stddev != 0);
sample:String();
normal:Int(t1:Int, t2:Double);
abs:Int where abs > 0 (val:Int);

main:Void(a:Int, i:[String]) {
	i[0] = "2";
	printf("%s", i[0]);
	
	s:String = "a";
	s = "b";
	printf("%s", s);
	printf("%s", i[2]);
	acd:[Int] = createArray("acd",2);
	ac(acd);
	doub:[Double] = createArray("doub", 2);
	doub[0] = 1;
	dwa:String = i[0];
	printf("%s", dwa);
	printf("%d", doub[0]);
	return ;
}

ac:Int(acd:[Int]) {
	printf("%i", acd[0]);
	acd[1] = 2;
	printf("%i", acd[1]);
	
	return 1;
}
