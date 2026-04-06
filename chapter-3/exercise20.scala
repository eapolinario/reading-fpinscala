//> using dep org.scalameta::munit::1.2.4
//> using target.scope test

def map[A, B](xs: List[A])(f: A => B): List[B] =
  xs match
    case Nil => Nil
    case head :: tail => f(head) :: map(tail)(f)

def flatMap[A, B](xs: List[A], f: A => List[B]): List[B] =
  xs match
    case Nil => Nil
    case head :: tail => f(head) ++ flatMap(tail, f)
