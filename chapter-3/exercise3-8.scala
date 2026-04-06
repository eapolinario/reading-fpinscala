//> using dep org.scalameta::munit::1.2.4
//> using target.scope test

def foldRight[A, B](as: List[A], acc: B, f: (A, B) => B): B = as match {
  case Nil => acc
  case x :: xs => f(x, foldRight(xs, acc, f))
}

def sumViaFoldRight(ns: List[Int]): Int =
  foldRight(ns, 0, (x, acc) => x + acc)

def productViaFoldRight(ns: List[Double]): Double =
    foldRight(ns, 1.0, (x, acc) => x * acc)


class FoldRightSuite extends munit.FunSuite:
    test("sumViaFoldRight sums a list of integers"):
        assertEquals(sumViaFoldRight(List(1, 2, 3, 4)), 10)

    test("productViaFoldRight multiplies a list of doubles"):
        assertEquals(productViaFoldRight(List(1.0, 2.0, 3.0)), 6.0)

    test("Pass Nil"):
        assertEquals(foldRight(List(1, 2, 3), Nil: List[Int], _ :: _), List(1, 2, 3))


def foldLeft[A, B](as: List[A], acc: B, f: (B, A) => B): B = as match {
  case Nil => acc
  case x :: xs => foldLeft(xs, f(acc, x), f)
}

def sumViaFoldLeft(ns: List[Int]): Int =
  foldLeft(ns, 0, (acc, x) => acc + x)

def productViaFoldLeft(ns: List[Double]): Double =
  foldLeft(ns, 1.0, (acc, x) => acc * x)

class FoldLeftSuite extends munit.FunSuite:
    test("sumViaFoldLeft sums a list of integers"):
        assertEquals(sumViaFoldLeft(List(1, 2, 3, 4)), 10)

    test("productViaFoldLeft multiplies a list of doubles"):
        assertEquals(productViaFoldLeft(List(1.0, 2.0, 3.0)), 6.0)

    test("Pass Nil"):
        assertEquals(foldLeft(List(1, 2, 3), Nil: List[Int], (acc, x) => acc :+ x), List(1, 2, 3))
