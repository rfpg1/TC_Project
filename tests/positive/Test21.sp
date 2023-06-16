(* Testing comments * )
(* Comments work just fine *)
d:Int = 4;
x:Int = d;

main:Void(a:Int, b:[String]) {
	printf("%d", 2*2.0);
	a(2*2.0);
	printf("a %i %s %i", 1, "a", 3);
	abs:Int where abs > 0 (val:Int);
	abs(2);
	b();
	while false || false {
		c:Int = 2;
	}
	d:Int = 4;
	d = 5;
	(* this should be invalid but since it doesn't change anything in the code it doesn't matter *)
	a[d * x + b() * a(2.0)];
	return ;
}

b:Int() {
	return 2;
}

a:Double(c:Double) {
	return c;
}