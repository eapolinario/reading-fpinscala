//> using dep org.scalameta::munit::1.2.4
//> using target.scope test

// enum Tree[+A]:
//   case Leaf(value: A)
//   case Branch(left: Tree[A], right: Tree[A])

extension [A](tree: Tree[A]) def map[B](f: A => B): Tree[B] = tree match
  case Tree.Leaf(value)         => Tree.Leaf(f(value))
  case Tree.Branch(left, right) => Tree.Branch(left.map(f), right.map(f))

class TreeMapSuite extends munit.FunSuite:
    test("map over a leaf applies the function to the value of the leaf"):
        assertEquals(Tree.Leaf(42).map((x: Int) => x * 2), Tree.Leaf(84))
    
    test("map over a branch applies the function to all values in the tree"):
        val tree = Tree.Branch(Tree.Leaf(1), Tree.Leaf(2))
        assertEquals(tree.map((x: Int) => x * 2), Tree.Branch(Tree.Leaf(2), Tree.Leaf(4)))
    
    test("map over a more complex tree"):
        val tree = Tree.Branch(
            Tree.Leaf(1),
            Tree.Branch(
                Tree.Leaf(2),
                Tree.Leaf(3)
            )
        )
        assertEquals(tree.map((x: Int) => x * 2), Tree.Branch(
            Tree.Leaf(2),
            Tree.Branch(
                Tree.Leaf(4),
                Tree.Leaf(6)
            )
        ))

    test("map over a complex tree with a different type"):
        val tree = Tree.Branch(
            Tree.Leaf(1),
            Tree.Branch(
                Tree.Leaf(2),
                Tree.Leaf(3)
            )
        )
        assertEquals(tree.map((x: Int) => x.toString), Tree.Branch(
            Tree.Leaf("1"),
            Tree.Branch(
                Tree.Leaf("2"),
                Tree.Leaf("3")
            )
        ))
