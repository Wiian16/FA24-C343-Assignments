import com.sun.source.tree.Tree;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkedBinaryTreeTest {
    private Node<Integer> node1, node2, node3, node4, node12, node34, node1234, node;
    private LinkedBinaryTree<Integer> tr1, tr2, tr3, tr;

    void makeNode() {
        node1 = Node.makeLeaf(1);
        node2 = Node.makeLeaf(2);
        node3 = Node.makeLeaf(3);
        node4 = Node.makeLeaf(4);
        node12 = new NonEmptyNode<>(10, node1, node2);
        node34 = new NonEmptyNode<>(20, node3, node4);
        node1234 = new NonEmptyNode<>(30, node12, node34);
        node = new NonEmptyNode<>(40, node1234, Node.makeLeaf(50));
    }

    void makeTree() {
        tr1 = new LinkedBinaryTree<>(1);
        tr2 = new LinkedBinaryTree<>(2);
        tr3 = new LinkedBinaryTree<>(3, tr1, tr2);
        tr = new LinkedBinaryTree<>(4, tr3, new LinkedBinaryTree<>(5));
    }

    @Test
    void testEmpty() {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();

        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());

        Node<Integer> root = tree.root();
        assertTrue(root.isEmpty());
        assertEquals(0, root.depth());
    }

    @Test
    void testNode () throws NodeNotFoundE {
        makeNode();
        TreePrinter.print(node);

        assertEquals(node12, node1.parent());
        assertEquals(node12, node2.parent());
        assertEquals(node34, node3.parent());
        assertEquals(node34, node4.parent());
        assertEquals(node1234, node12.parent());
        assertEquals(node1234, node34.parent());
        assertEquals(node, node1234.parent());

        assertEquals(0, node.depth());
        assertEquals(1, node1234.depth());
        assertEquals(2, node12.depth());
        assertEquals(2, node34.depth());
        assertEquals(3, node1.depth());
        assertEquals(3, node2.depth());
        assertEquals(3, node3.depth());
        assertEquals(3, node4.depth());

        assertEquals(9, node.size());

        assertEquals(4, node.height());
        assertEquals(3, node1234.height());
        assertEquals(2, node12.height());
        assertEquals(2, node34.height());
        assertEquals(1, node1.height());
        assertEquals(1, node2.height());
        assertEquals(1, node3.height());
        assertEquals(1, node4.height());

        assertEquals(node1234, node.leftNode());
        assertNotEquals(node.rightNode(), Node.makeLeaf(50));
    }

    @Test
    void testLinkedBinaryTree() throws NodeNotFoundE {
        makeTree();
        TreePrinter.print(tr);

        assertEquals(5, tr.size());
    }

    @Test
    void testTraversals () {
        makeNode();
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>(node);
        TreePrinter.print(tree);

        List<Integer> preOrder = tree.preOrder();
        List<Integer> inOrder = tree.inOrder();
        List<Integer> postOrder = tree.postOrder();
        List<Integer> levelOrder = tree.levelOrder();

        assertEquals(List.of(40, 30, 10, 1, 2, 20, 3, 4, 50), preOrder);
        assertEquals(List.of(1, 10, 2, 30, 3, 20, 4, 40, 50), inOrder);
        assertEquals(List.of(1, 2, 10, 3, 4, 20, 30, 50, 40), postOrder);
        assertEquals(List.of(40, 30, 50, 10, 20, 1, 2, 3, 4), levelOrder);

    }

}