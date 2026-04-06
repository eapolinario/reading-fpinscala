//> using dep org.scalameta::munit::1.2.4
//> using target.scope test

def add_one(xs: List[Int]): List[Int] =
  xs match
    case Nil => Nil
    case x :: tail => (x + 1) :: add_one(tail)

class AddOneSuite extends munit.FunSuite:
    test("add_one to a multi-element list"):
        assertEquals(add_one(List(1, 2, 3)), List(2, 3, 4))
