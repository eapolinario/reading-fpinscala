//> using dep org.scalameta::munit::1.2.4

def tail[A](xs: List[A]): List[A] =
  xs match
    case Nil       => sys.error("tail of empty list")
    case _ :: tail => tail

class TailSuite extends munit.FunSuite:
  test("tail of a multi-element list"):
    assertEquals(tail(List(1, 2, 3)), List(2, 3))

  test("tail of a single-element list is Nil"):
    assertEquals(tail(List("a")), Nil)

  test("tail of Nil throws"):
    intercept[RuntimeException](tail(Nil))
