def tail[A](xs: List[A]): List[A] =
  xs match
    case Nil => sys.error("tail of empty list")
    case _ :: tail => tail

@main def testTail(): Unit =
  println(tail(List(1, 2, 3))) // Should print List(2, 3)
  println(tail(List("a", "b", "c"))) // Should print List("b", "c")
  try { tail(Nil) } catch { case e: RuntimeException => println(s"Error: ${e.getMessage}") }
