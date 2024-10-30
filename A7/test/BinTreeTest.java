import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.tree.TreeCellRenderer;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BinTreeTest {

    @Test
    void insert() {
        BinTree<Integer> tr = BinTree.makeLeaf(1);
        assertTrue(tr.isBalanced());
        tr = tr.insert(2);
        assertTrue(tr.isBalanced());
        tr = tr.insert(3);
        assertFalse(tr.isBalanced());

        tr = BinTree.makeLeaf(50);
        int[] nums = new int[] { 25, 75, 12, 37, 62, 87, 6, 18, 31, 43, 56, 68, 81, 93, 3, 9, 15, 21, 28, 34, 40, 46, 53, 59, 65, 71, 78, 84, 90, 96, 1, 4, 7, 10, 13, 16, 19, 22, 26, 29, 32, 35, 38, 41, 44, 47, 51, 54, 57, 60, 63, 66, 69, 72, 76, 79, 82, 85, 88, 91, 94, 97, 2, 5, 8, 11, 14, 17, 20, 23, 27, 30, 33, 36, 39, 42, 45, 48, 52, 55, 58, 61, 64, 67, 70, 73, 77, 80, 83, 86, 89, 92, 95, 98 };
        for (int num : nums) {
            tr = tr.insert(num);
            assertTrue(tr.isBalanced());
        }

        tr = BinTree.makeLeaf(0);
        for (int i = 1; i < 15; i++) {
            tr = tr.insertB(i);
            TreePrinter.print(tr);
        }

        /*
        Should print

      0
      └──┐
         1

      1
   ┌──┴──┐
   0     2

            1
      ┌─────┴─────┐
      0           2
                  └──┐
                     3

            1
      ┌─────┴─────┐
      0           3
               ┌──┴──┐
               2     4

            3
      ┌─────┴─────┐
      1           4
   ┌──┴──┐        └──┐
   0     2           5

            3
      ┌─────┴─────┐
      1           5
   ┌──┴──┐     ┌──┴──┐
   0     2     4     6

                        3
            ┌───────────┴───────────┐
            1                       5
      ┌─────┴─────┐           ┌─────┴─────┐
      0           2           4           6
                                          └──┐
                                             7

                        3
            ┌───────────┴───────────┐
            1                       5
      ┌─────┴─────┐           ┌─────┴─────┐
      0           2           4           7
                                       ┌──┴──┐
                                       6     8

                        3
            ┌───────────┴───────────┐
            1                       7
      ┌─────┴─────┐           ┌─────┴─────┐
      0           2           5           8
                           ┌──┴──┐        └──┐
                           4     6           9

                        3
            ┌───────────┴───────────┐
            1                       7
      ┌─────┴─────┐           ┌─────┴─────┐
      0           2           5           9
                           ┌──┴──┐     ┌──┴──┐
                           4     6     8    10

                        7
            ┌───────────┴───────────┐
            3                       9
      ┌─────┴─────┐           ┌─────┴─────┐
      1           5           8          10
   ┌──┴──┐     ┌──┴──┐                    └──┐
   0     2     4     6                      11

                        7
            ┌───────────┴───────────┐
            3                       9
      ┌─────┴─────┐           ┌─────┴─────┐
      1           5           8          11
   ┌──┴──┐     ┌──┴──┐                 ┌──┴──┐
   0     2     4     6                10    12

                        7
            ┌───────────┴───────────┐
            3                      11
      ┌─────┴─────┐           ┌─────┴─────┐
      1           5           9          12
   ┌──┴──┐     ┌──┴──┐     ┌──┴──┐        └──┐
   0     2     4     6     8    10          13

                        7
            ┌───────────┴───────────┐
            3                      11
      ┌─────┴─────┐           ┌─────┴─────┐
      1           5           9          13
   ┌──┴──┐     ┌──┴──┐     ┌──┴──┐     ┌──┴──┐
   0     2     4     6     8    10    12    14

         */

        tr = BinTree.makeLeaf(0);
        for (int i = 1; i < 100; i++) {
            tr = tr.insertB(i);
            assertTrue(tr.isBalanced());
        }

    }


    @Test
    void delete() throws EmptyTreeE {

        BinTree<Integer> tr = BinTree.makeLeaf(0);
        for (int i = 1; i < 15; i++) {
            tr = tr.insertB(i);
        }

        TreePrinter.print(tr);
        assertTrue(tr.isBalanced());
        tr = tr.delete(7);
        TreePrinter.print(tr);
        assertTrue(tr.isBalanced());
        tr = tr.delete(8);
        TreePrinter.print(tr);
        assertTrue(tr.isBalanced());
        tr = tr.delete(9);
        TreePrinter.print(tr);
        assertFalse(tr.isBalanced());

        tr = BinTree.makeLeaf(0);
        for (int i = 1; i < 15; i++) {
            tr = tr.insertB(i);
        }
        assertTrue(tr.isBalanced());

        tr = tr.deleteB(7);
        assertTrue(tr.isBalanced());
        TreePrinter.print(tr);
        System.out.println();

        tr = tr.deleteB(6);
        assertTrue(tr.isBalanced());
        TreePrinter.print(tr);
        System.out.println();

        tr = tr.deleteB(5);
        assertTrue(tr.isBalanced());
        TreePrinter.print(tr);
        System.out.println();

        tr = tr.deleteB(4);
        assertTrue(tr.isBalanced());
        TreePrinter.print(tr);
        System.out.println();

        tr = tr.deleteB(3);
        assertTrue(tr.isBalanced());
        TreePrinter.print(tr);
        System.out.println();

        tr = tr.deleteB(2);
        assertTrue(tr.isBalanced());
        TreePrinter.print(tr);
        System.out.println();

        tr = tr.deleteB(11);
        assertTrue(tr.isBalanced());
        TreePrinter.print(tr);
        System.out.println();

        tr = tr.deleteB(10);
        assertTrue(tr.isBalanced());
        TreePrinter.print(tr);
        System.out.println();

        tr = tr.deleteB(9);
        assertTrue(tr.isBalanced());
        TreePrinter.print(tr);
        System.out.println();

        tr = tr.deleteB(8);
        assertTrue(tr.isBalanced());
        TreePrinter.print(tr);
        System.out.println();

        tr = tr.deleteB(1);
        assertTrue(tr.isBalanced());
        TreePrinter.print(tr);
        System.out.println();

        tr = tr.deleteB(13);
        assertTrue(tr.isBalanced());
        TreePrinter.print(tr);
        System.out.println();

        tr = tr.deleteB(12);
        assertTrue(tr.isBalanced());
        TreePrinter.print(tr);
        System.out.println();

        tr = tr.deleteB(0);
        assertTrue(tr.isBalanced());
        TreePrinter.print(tr);
        System.out.println();

        tr = tr.deleteB(14);
        assertTrue(tr.isBalanced());
        TreePrinter.print(tr);
        System.out.println();

/*
should print:

                        6
            ┌───────────┴───────────┐
            3                      11
      ┌─────┴─────┐           ┌─────┴─────┐
      1           5           9          13
   ┌──┴──┐     ┌──┘        ┌──┴──┐     ┌──┴──┐
   0     2     4           8    10    12    14

                        5
            ┌───────────┴───────────┐
            3                      11
      ┌─────┴─────┐           ┌─────┴─────┐
      1           4           9          13
   ┌──┴──┐                 ┌──┴──┐     ┌──┴──┐
   0     2                 8    10    12    14

                        4
            ┌───────────┴───────────┐
            1                      11
      ┌─────┴─────┐           ┌─────┴─────┐
      0           3           9          13
               ┌──┘        ┌──┴──┐     ┌──┴──┐
               2           8    10    12    14

                        3
            ┌───────────┴───────────┐
            1                      11
      ┌─────┴─────┐           ┌─────┴─────┐
      0           2           9          13
                           ┌──┴──┐     ┌──┴──┐
                           8    10    12    14

                        2
            ┌───────────┴───────────┐
            1                      11
      ┌─────┘                 ┌─────┴─────┐
      0                       9          13
                           ┌──┴──┐     ┌──┴──┐
                           8    10    12    14

                       11
            ┌───────────┴───────────┐
            1                      13
      ┌─────┴─────┐           ┌─────┴─────┐
      0           9          12          14
               ┌──┴──┐
               8    10

                       10
            ┌───────────┴───────────┐
            1                      13
      ┌─────┴─────┐           ┌─────┴─────┐
      0           9          12          14
               ┌──┘
               8

            9
      ┌─────┴─────┐
      1          13
   ┌──┴──┐     ┌──┴──┐
   0     8    12    14

            8
      ┌─────┴─────┐
      1          13
   ┌──┘        ┌──┴──┐
   0          12    14

            1
      ┌─────┴─────┐
      0          13
               ┌──┴──┐
              12    14

           13
      ┌─────┴─────┐
      0          14
      └──┐
        12

     12
   ┌──┴──┐
   0    14

      0
      └──┐
        14

  14

    
 */




    }
    private BinTree<Integer> root;

    @BeforeEach
    void setUp() {
        root = new Node<>(10, new Empty<>(), new Empty<>());
    }

    @Test
    void testInsert() {
        // Simple insertion
        BinTree<Integer> tree = root.insert(5).insert(15).insert(3).insert(7);
        assertTrue(tree.find(10));
        assertTrue(tree.find(5));
        assertTrue(tree.find(15));
        assertTrue(tree.find(3));
        assertTrue(tree.find(7));

        // Edge cases: inserting duplicates
        tree = tree.insert(5);
        assertTrue(tree.find(5));  // Check duplicate handling

        // Edge case: inserting into an empty tree
        BinTree<Integer> emptyTree = new Empty<Integer>().insert(10);
        assertTrue(emptyTree.find(10));
    }

    @Test
    void testDelete() throws EmptyTreeE {
        // Deleting from a populated tree
        BinTree<Integer> tree = root.insert(5).insert(15).insert(3).insert(7).delete(5);
        assertFalse(tree.find(5));

        // Edge case: deleting the root node
        tree = root.insert(5).insert(15).delete(10);
        assertFalse(tree.find(10));

        // Edge case: deleting from a single-node tree
        BinTree<Integer> singleNodeTree = new Node<>(20, new Empty<>(), new Empty<>()).delete(20);
        assertTrue(singleNodeTree.isEmpty());

        // Edge case: deleting a non-existent element
        assertEquals(tree, tree.delete(100));
    }

    @Test
    void testIsBalanced() {
        // Simple balanced case
        assertTrue(root.isBalanced());
        root = root.insert(5).insert(15).insert(3).insert(7);
        assertTrue(root.isBalanced());

        // Left-heavy unbalanced case
        BinTree<Integer> leftHeavyTree = root.insert(5).insert(3).insert(2);
        assertFalse(leftHeavyTree.isBalanced());

        // Right-heavy unbalanced case
        BinTree<Integer> rightHeavyTree = root.insert(15).insert(20).insert(25);
        assertFalse(rightHeavyTree.isBalanced());

        // Edge case: single-node tree is balanced
        BinTree<Integer> singleNodeTree = new Node<>(10, new Empty<>(), new Empty<>());
        assertTrue(singleNodeTree.isBalanced());
    }

    @Test
    void testUnbalancedTree() {
        // Check right-heavy tree
        BinTree<Integer> rightHeavyTree = root.insert(12).insert(14).insert(16).insert(18);
        assertFalse(rightHeavyTree.isBalanced());

        // Check left-heavy tree
        BinTree<Integer> leftHeavyTree = root.insert(8).insert(6).insert(4).insert(2);
        assertFalse(leftHeavyTree.isBalanced());
    }

    @Test
    void testInsertBWithBalancing() {
        // Insert with balancing
        BinTree<Integer> balancedTree = root.insertB(5).insertB(15).insertB(3).insertB(7).insertB(13);
        assertTrue(balancedTree.isBalanced());

        // Edge case: balancing with duplicate values
        balancedTree = balancedTree.insertB(5).insertB(10);
        assertTrue(balancedTree.isBalanced());

        // Complex balancing case (requires rotations)
        BinTree<Integer> complexTree = new Node<>(10, new Empty<>(), new Empty<>())
                .insertB(20).insertB(5).insertB(4).insertB(3).insertB(7);
        assertTrue(complexTree.isBalanced());
    }

    @Test
    void testDeleteBWithBalancing() throws EmptyTreeE {
        // Delete with balancing
        BinTree<Integer> balancedTree = root.insertB(5).insertB(15).insertB(3).insertB(7).insertB(13).deleteB(15);
        assertTrue(balancedTree.isBalanced());
        assertFalse(balancedTree.find(15));

        // Edge case: delete root with balancing
        BinTree<Integer> treeAfterRootDelete = balancedTree.deleteB(10);
        assertTrue(treeAfterRootDelete.isBalanced());
        assertFalse(treeAfterRootDelete.find(10));

        // Complex delete case (triggers rotations)
        BinTree<Integer> complexTree = balancedTree.insertB(1).insertB(2).deleteB(7);
        assertTrue(complexTree.isBalanced());
    }

    @Test
    void testLeftRotation() {
        // Test simple left rotation
        Node<Integer> unbalancedTree = new Node<>(10, new Empty<>(), new Node<>(20, new Empty<>(), new Node<>(30, new Empty<>(), new Empty<>())));
        BinTree<Integer> rotatedTree = unbalancedTree.easyLeft();
        assertTrue(rotatedTree.isBalanced());

        try {
            // Edge case: left rotation on single-node tree
            BinTree<Integer> singleNodeTree = new Node<>(10, new Empty<>(), new Empty<>()).easyLeft();
            assertEquals(singleNodeTree.getData(), 10);
        }
        catch(EmptyTreeE e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testRightRotation() {
        // Test simple right rotation
        Node<Integer> unbalancedTree = new Node<>(30, new Node<>(20, new Node<>(10, new Empty<>(), new Empty<>()), new Empty<>()), new Empty<>());
        BinTree<Integer> rotatedTree = unbalancedTree.easyRight();
        assertTrue(rotatedTree.isBalanced());

        try {
            // Edge case: right rotation on single-node tree
            BinTree<Integer> singleNodeTree = new Node<>(10, new Empty<>(), new Empty<>()).easyRight();
            assertEquals(singleNodeTree.getData(), 10);
        }
        catch(EmptyTreeE e) {
            throw new RuntimeException(e);
        }

        BinTree<Integer> tr = BinTree.makeLeaf(10);
        tr = tr.easyRight();
        assertTrue(tr.isBalanced());

        tr = tr.insert(0);
        assertTrue(tr.isBalanced());
    }

    @Test
    void testLeftRightRotation() throws EmptyTreeE {
        // Test left-right rotation
        Node<Integer> unbalancedTree = new Node<>(30, new Node<>(10, new Empty<>(), new Node<>(20, new Empty<>(), new Empty<>())), new Empty<>());
        BinTree<Integer> rotatedTree = unbalancedTree.rotateRight();
        assertTrue(rotatedTree.isBalanced());

        // Edge case: left-right rotation with duplicates
//        BinTree<Integer> duplicateTree = unbalancedTree.insertB(10).rotateRight();
//        assertTrue(duplicateTree.isBalanced());

        BinTree<Integer> tr = BinTree.makeLeaf(10);
        TreePrinter.print(tr);
        tr = tr.insertB(5);
        TreePrinter.print(tr);
        tr = tr.insertB(8);
        TreePrinter.print(tr);
        assertTrue(tr.rotateRight().isBalanced());

        tr = new Node<>(10, new Node<>(5, new Empty<>(), BinTree.makeLeaf(8)), new Empty<>());
        TreePrinter.print(tr);
        TreePrinter.print(tr.rotateRight());
        assertTrue(tr.rotateRight().isBalanced());

        tr = BinTree.makeLeaf(10);
        tr = tr.rotateRight();
        assertTrue(tr.isBalanced());

        tr = new Empty<>();
        tr = tr.insertB(10);
        assertTrue(tr.isBalanced());
    }

    @Test
    void testRightLeftRotation() {
        // Test right-left rotation
        Node<Integer> unbalancedTree = new Node<>(10, new Empty<>(), new Node<>(30, new Node<>(20, new Empty<>(), new Empty<>()), new Empty<>()));
        BinTree<Integer> rotatedTree = unbalancedTree.rotateLeft();
        assertTrue(rotatedTree.isBalanced());

        // Edge case: right-left rotation with duplicates
        BinTree<Integer> duplicateTree = unbalancedTree.insertB(30).rotateLeft();
        assertTrue(duplicateTree.isBalanced());
    }

    @Test
    void testDeleteLeftMostLeaf() throws EmptyTreeE {
        // Delete leftmost leaf in a populated tree
        BinTree<Integer> tree = root.insert(5).insert(15).insert(3);
        Pair<Integer, BinTree<Integer>> result = tree.deleteLeftMostLeaf();
        assertEquals(3, result.first());

        // Edge case: delete leftmost leaf in a single-node tree
        BinTree<Integer> singleNodeTree = new Node<>(20, new Empty<>(), new Empty<>());
        result = singleNodeTree.deleteLeftMostLeaf();
        assertTrue(result.second().isEmpty());
    }

    @Test
    void testDeleteRightMostLeafB() {
        // Delete rightmost leaf in a balanced tree
        try {
            BinTree<Integer> tree = root.insertB(5).insertB(15).insertB(3).insertB(7);
            Pair<Integer, BinTree<Integer>> result = tree.getLeftT().deleteRightMostLeafB();
            assertNotNull(result);
            assertEquals(7, result.first());

            // Edge case: delete rightmost leaf in a single-node tree
            BinTree<Integer> singleNodeTree = new Node<>(20, new Empty<>(), new Empty<>());
            result = singleNodeTree.deleteRightMostLeafB();
            assertTrue(result.second().isEmpty());
        }
        catch(EmptyTreeE e) {
            throw new RuntimeException(e);
        }
    }

}