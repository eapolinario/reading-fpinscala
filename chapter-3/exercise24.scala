//> using dep org.scalameta::munit::1.2.4
//> using target.scope test

def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean =
  sup match
    case Nil => sub == Nil
    case _ if sup.startsWith(sub) => true
    case _ :: tail => hasSubsequence(tail, sub)

class HasSubsequenceSuite extends munit.FunSuite:
    test("subsequence is present at the beginning of the list"):
        assert(hasSubsequence(List(1, 2, 3, 4), List(1, 2)))

    test("subsequence is present at the end of the list"):
        assert(hasSubsequence(List(1, 2, 3, 4), List(3, 4)))

    test("subsequence is present in the middle of the list"):
        assert(hasSubsequence(List(1, 2, 3, 4), List(2, 3)))

    test("subsequence is not present in the list"):
        assert(!hasSubsequence(List(1, 2, 3, 4), List(2, 4)))
