//> using dep org.scalameta::munit::1.2.4
//> using target.scope test

enum Tree[+A]:
  case Leaf(value: A)
  case Branch(left: Tree[A], right: Tree[A])

  def size: Int = this match
    case Leaf(_) => 1
    case Branch(left, right) => 1 + left.size + right.size

extension (tree: Tree[Int]) def firstPositive: Int = tree match
  case Tree.Leaf(value) => value
  case Tree.Branch(left, right) =>
    val leftResult = left.firstPositive
    if leftResult > 0 then leftResult else right.firstPositive

class TreeSizeSuite extends munit.FunSuite:
    test("size of a leaf is 1"):
        assertEquals(Tree.Leaf(42).size, 1)

    test("size of a branch is 1 plus the size of its left and right subtrees"):
        val tree = Tree.Branch(Tree.Leaf(1), Tree.Leaf(2))
        assertEquals(tree.size, 3)

    test("size of a more complex tree"):
        val tree = Tree.Branch(
            Tree.Leaf(1),
            Tree.Branch(
                Tree.Leaf(2),
                Tree.Leaf(3)
            )
        )
        assertEquals(tree.size, 5)


class FirstPositiveSuite extends munit.FunSuite:
    test("first positive in a leaf is the value of the leaf"):
        assertEquals(Tree.Leaf(42).firstPositive, 42)

    test("first positive in a branch is the first positive in the left subtree if it exists"):
        val tree = Tree.Branch(Tree.Leaf(1), Tree.Leaf(-1))
        assertEquals(tree.firstPositive, 1)

    test("first positive in a branch is the first positive in the right subtree if it does not exist in the left subtree"):
        val tree = Tree.Branch(Tree.Leaf(-1), Tree.Leaf(2))
        assertEquals(tree.firstPositive, 2)
        
    test("first positive in a more complex tree"):
        val tree = Tree.Branch(
            Tree.Leaf(-1),
            Tree.Branch(
                Tree.Leaf(-2),
                Tree.Leaf(3)
            )
        )
        assertEquals(tree.firstPositive, 3)

    test("Tree of all negatives returns the last negative leaf value"):
        val tree = Tree.Branch(
            Tree.Leaf(-1),
            Tree.Branch(
                Tree.Leaf(-2),
                Tree.Leaf(-3)
            )
        )
        assertEquals(tree.firstPositive, -3)


extension (tree: Tree[Int]) def maximum: Int = tree match
  case Tree.Leaf(value) => value
  case Tree.Branch(left, right) =>
    val leftMax = left.maximum
    val rightMax = right.maximum
    leftMax.max(rightMax)


class MaximumSuite extends munit.FunSuite:
    test("maximum of a leaf is the value of the leaf"):
        assertEquals(Tree.Leaf(42).maximum, 42)

    test("maximum of a branch is the maximum of its left and right subtrees"):
        val tree = Tree.Branch(Tree.Leaf(1), Tree.Leaf(2))
        assertEquals(tree.maximum, 2)

    test("maximum of a more complex tree"):
        val tree = Tree.Branch(
            Tree.Leaf(1),
            Tree.Branch(
                Tree.Leaf(3),
                Tree.Leaf(2)
            )
        )
        assertEquals(tree.maximum, 3)
        
