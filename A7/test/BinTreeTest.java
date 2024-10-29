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
        System.out.println("Balanced Tree");
        for (int i = 1; i < 100; i++) {
            tr = tr.insertB(i);
            TreePrinter.print(tr);
            assertTrue(tr.isBalanced());
        }

    }


    @Test
    void delete() throws EmptyTreeE {

        BinTree<Integer> tr = BinTree.makeLeaf(0);
        for (int i = 1; i < 15; i++) {
            tr = tr.insertB(i);
        }

        assertTrue(tr.isBalanced());
        tr = tr.delete(7);
        assertTrue(tr.isBalanced());
        tr = tr.delete(8);
        assertTrue(tr.isBalanced());
        tr = tr.delete(9);
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
        BinTree<Integer> tree = root.insert(5).insert(15).insert(3).insert(7);
        assertTrue(tree.find(10));
        assertTrue(tree.find(5));
        assertTrue(tree.find(15));
        assertTrue(tree.find(3));
        assertTrue(tree.find(7));
    }

    @Test
    void testDelete() throws EmptyTreeE {
        BinTree<Integer> tree = root.insert(5).insert(15).insert(3).insert(7).delete(5);
        assertFalse(tree.find(5));
    }

    @Test
    void testIsBalanced() {
        assertTrue(root.isBalanced());
        root = root.insert(5).insert(15).insert(3).insert(7);
        assertTrue(root.isBalanced());
    }

    @Test
    void testUnbalancedTree() {
        BinTree<Integer> tree = root.insert(5).insert(15).insert(20).insert(25);
        assertFalse(tree.isBalanced());
    }

    @Test
    void testInsertBWithBalancing() {
        BinTree<Integer> balancedTree = root.insertB(5).insertB(15).insertB(3).insertB(7).insertB(13);
        assertTrue(balancedTree.isBalanced());
    }

    @Test
    void testDeleteBWithBalancing() throws EmptyTreeE {
        BinTree<Integer> balancedTree = root.insertB(5).insertB(15).insertB(3).insertB(7).insertB(13).deleteB(15);
        assertTrue(balancedTree.isBalanced());
        assertFalse(balancedTree.find(15));
    }

    @Test
    void testLeftRotation() {
        Node<Integer> unbalancedTree = new Node<>(10, new Empty<>(), new Node<>(20, new Empty<>(), new Node<>(30, new Empty<>(), new Empty<>())));
        BinTree<Integer> rotatedTree = unbalancedTree.easyLeft();
        assertTrue(rotatedTree.isBalanced());
    }

    @Test
    void testRightRotation() {
        Node<Integer> unbalancedTree = new Node<>(30, new Node<>(20, new Node<>(10, new Empty<>(), new Empty<>()), new Empty<>()), new Empty<>());
        BinTree<Integer> rotatedTree = unbalancedTree.easyRight();
        assertTrue(rotatedTree.isBalanced());
    }

    @Test
    void testLeftRightRotation() {
        Node<Integer> unbalancedTree = new Node<>(30, new Node<>(10, new Empty<>(), new Node<>(20, new Empty<>(), new Empty<>())), new Empty<>());
        BinTree<Integer> rotatedTree = unbalancedTree.rotateRight();
        assertTrue(rotatedTree.isBalanced());
    }

    @Test
    void testRightLeftRotation() {
        Node<Integer> unbalancedTree = new Node<>(10, new Empty<>(), new Node<>(30, new Node<>(20, new Empty<>(), new Empty<>()), new Empty<>()));
        BinTree<Integer> rotatedTree = unbalancedTree.rotateLeft();
        assertTrue(rotatedTree.isBalanced());
    }

    @Test
    void testDeleteLeftMostLeaf() throws EmptyTreeE {
        BinTree<Integer> tree = root.insert(5).insert(15).insert(3);
        Pair<Integer, BinTree<Integer>> result = tree.deleteLeftMostLeaf();
        assertEquals(3, result.first());
    }

    @Test
    void testDeleteRightMostLeafB() throws EmptyTreeE {
        BinTree<Integer> tree = root.insertB(5).insertB(15).insertB(3).insertB(7);
        Pair<Integer, BinTree<Integer>> result = tree.deleteRightMostLeafB();
        assertNotNull(result);
    }
}