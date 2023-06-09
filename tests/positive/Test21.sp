(* Testing comments * )
(* Coments work just fine *)

main:Void(a:Int[]) {
	a(false);
	printf("a %i %s %i", 1, "a", 3);
	abs:Int where abs > 0 (val:Int)  ;
	abs(2);
	b();
	while false || false {
		c:Int = 2;
	}
	d:Int = 4;
	d = 5;
	a[d * 2 + b()];
	return ;
}

b:Int() {
	return 2;
}

a:Boolean(c:Boolean) {
	return false;
}