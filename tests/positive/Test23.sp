(***** Testing definitions ******* )
d:String = "a";
max:Int (a:Int, b:Int) {
  if a > b {
    return a;
  }
  return b;
}
(* Multiple functions defined *)
min:Int (a:Int, b:Int) {
  c:Int = 3;
  d:Int = 2;
  if a > b {
    return a;
  }
  while (b>a) {
  	a = 1;
  	b = 2;
  }
  d = c;
  
  return b;
}

e:String = d;