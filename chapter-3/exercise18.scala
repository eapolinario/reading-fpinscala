//> using dep org.scalameta::munit::1.2.4
//> using target.scope test

def map[A, B](xs: List[A], f: A => B): List[B] =
  xs match
    case Nil => Nil
    case x :: tail => f(x) :: map(tail, f)

class MapSuite extends munit.FunSuite:
    test("map over a multi-element list"):
        assertEquals(map(List(1, 2, 3), (x: Int) => x * 2), List(2, 4, 6))

    test("map over an empty list returns an empty list"):
        assertEquals(map(Nil: List[Int], (x: Int) => x * 2), Nil) 
