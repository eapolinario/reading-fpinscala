object MyProgram:
  def abs(n: Int): Int =
    if n < 0 then -n
    else n

  private def formatAbs(x: Int): String =
    val msg = "The absolute value of %d is %d"
    msg.format(x, abs(x))

  def isSorted(xs: Array[Int], gt: (Int, Int) => Boolean): Boolean =
    def loop(n: Int): Boolean =
      n >= xs.length - 1 || gt(xs(n), xs(n + 1)) && loop(n + 1)
    loop(0)

  def curry[A, B, C](f: (A, B) => C): A => B => C =
    a => b => f(a, b)

  @main def printAbs(): Unit =
    println(formatAbs(-42))
    val curriedIsSorted = curry(isSorted)
    val checkAscending = curriedIsSorted(Array(1, 2, 3, 4))
    println(s"Array(1,2,3,4) is sorted ascending: ${checkAscending((x, y) => x < y)}")
    println(s"Array(1,3,2,4) is sorted ascending: ${curriedIsSorted(Array(1, 3, 2, 4))((x, y) => x < y)}")
