//> using dep org.scalameta::munit::1.2.4
//> using target.scope test

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


def drop[A](n: Int, xs: List[A]): List[A] =
  if n <= 0 then xs
  else
    xs match
      case Nil       => Nil
      case _ :: tail => drop(n - 1, tail)

class DropSuite extends munit.FunSuite:
    test("drop 0 elements returns the original list"):
        assertEquals(drop(0, List(1, 2, 3)), List(1, 2, 3))

    test("drop 2 elements from a multi-element list"):
        assertEquals(drop(2, List(1, 2, 3)), List(3))

    test("drop more elements than the list has returns Nil"):
        assertEquals(drop(5, List(1, 2, 3)), Nil)
