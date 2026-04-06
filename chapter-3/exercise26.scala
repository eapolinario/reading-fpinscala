//> using dep org.scalameta::munit::1.2.4
//> using target.scope test

extension (tree: Tree[Int]) def depth: Int = tree match
  case Tree.Leaf(_) => 1
  case Tree.Branch(left, right) => 1 + math.max(left.depth, right.depth)

class TreeDepthSuite extends munit.FunSuite:
    test("depth of a leaf is 1"):
        assertEquals(Tree.Leaf(42).depth, 1)
    
    test("depth of a branch is 1 plus the maximum depth of its left and right subtrees"):
        val tree = Tree.Branch(Tree.Leaf(1), Tree.Leaf(2))
        assertEquals(tree.depth, 2)
    
    test("depth of a more complex tree"):
        val tree = Tree.Branch(
            Tree.Leaf(1),
            Tree.Branch(
                Tree.Leaf(2),
                Tree.Leaf(3)
            )
        )
        assertEquals(tree.depth, 3)
