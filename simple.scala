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

  @main def printAbs(): Unit =
    println(formatAbs(-42))
