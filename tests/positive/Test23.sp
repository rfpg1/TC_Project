(***** Testing definitions ******* )
max:Int (a:Int, b:Int) {
  if a > b {
    return a;
  }
  return b;
}
(* Multiple functions defined *)
max:Int (a:Int, b:Int) {
  if a > b {
    return a;
  }
  return b;
}