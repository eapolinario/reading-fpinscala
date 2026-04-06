//> using dep org.scalameta::munit::1.2.4
//> using target.scope test

def filter[A](xs: List[A])(f: A => Boolean): List[A] =
  // Use map to implement filter
  flatMap(xs, x => if f(x) then List(x) else Nil)

class FilterSuite extends munit.FunSuite:
    test("filter out odd numbers"):
        assertEquals(filter(List(1, 2, 3, 4, 5))(x => x % 2 == 0), List(2, 4))

    test("filter out all elements returns an empty list"):
        assertEquals(filter(List(1, 2, 3))(x => x > 3), Nil)

    test("filter over an empty list returns an empty list"):
        assertEquals(filter(Nil: List[Int])(x => x % 2 == 0), Nil)
