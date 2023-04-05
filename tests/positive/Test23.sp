(***** Testing definitions ******* )
max:Int (a:Int, b:Int) {
  if a > b {
    return a;
  }
  return b;
}