import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BST {

    private class Node {
        int value;
        Node left, right;

        Node(int value) {
            this.value = value;
            left = right = null;
        }
    }

    private Node root;

    public void insert(int value) {
        root = insert(root, value);
    }

    private Node insert(Node node, int value) {
        if (node == null) return new Node(value);
        if (value < node.value) node.left = insert(node.left, value);
        else if (value > node.value) node.right = insert(node.right, value);
        return node;
    }

    public void balanceTree() {
        List<Integer> sortedList = inorderTraversal(root);
        root = buildBalancedTree(sortedList, 0, sortedList.size() - 1);
    }

    private List<Integer> inorderTraversal(Node node) {
        List<Integer> result = new ArrayList<>();
        if (node != null) {
            result.addAll(inorderTraversal(node.left));
            result.add(node.value);
            result.addAll(inorderTraversal(node.right));
        }
        return result;
    }

    private Node buildBalancedTree(List<Integer> sortedList, int start, int end) {
        if(start == end){
            return new Node(sortedList.get(start));
        }

        int mid = (start + end) / 2;

        Node root = new Node(sortedList.get(mid));
        root.left = buildBalancedTree(sortedList, start, mid - 1);
        root.right = buildBalancedTree(sortedList, mid + 1, end);

        return root;
    }

    public void printTree() {
        int maxLevel = getMaxLevel(root);
        printTreeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private void printTreeInternal(List<Node> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes)) return;

        int floor = maxLevel - level;
        int edgeLines = (int) Math.pow(2, Math.max(floor - 1, 0));
        int firstSpaces = (int) Math.pow(2, floor) - 1;
        int betweenSpaces = (int) Math.pow(2, floor + 1) - 1;

        printWhitespaces(firstSpaces);

        List<Node> newNodes = new ArrayList<>();
        for (Node node : nodes) {
            if (node != null) {
                System.out.print(node.value);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }
            printWhitespaces(betweenSpaces);
        }
        System.out.println();

        for (int i = 1; i <= edgeLines; i++) {
            for (Node node : nodes) {
                printWhitespaces(firstSpaces - i);
                if (node == null) {
                    printWhitespaces(edgeLines + edgeLines + i + 1);
                    continue;
                }

                if (node.left != null) System.out.print("/");
                else printWhitespaces(1);

                printWhitespaces(i + i - 1);

                if (node.right != null) System.out.print("\\");
                else printWhitespaces(1);

                printWhitespaces(edgeLines + edgeLines - i);
            }
            System.out.println();
        }

        printTreeInternal(newNodes, level + 1, maxLevel);
    }

    private void printWhitespaces(int count) {
        for (int i = 0; i < count; i++) System.out.print(" ");
    }

    private int getMaxLevel(Node node) {
        if (node == null) return 0;
        return Math.max(getMaxLevel(node.left), getMaxLevel(node.right)) + 1;
    }

    private boolean isAllElementsNull(List<Node> list) {
        for (Node node : list) {
            if (node != null) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        BST tree = new BST();

        // Insert nodes to create an unbalanced BST
        tree.insert(10);
        tree.insert(5);
        tree.insert(1);
        tree.insert(7);
        tree.insert(40);
        tree.insert(50);
        tree.insert(2);

        System.out.println("Given Tree:");
        tree.printTree();

        // Balance the tree
        tree.balanceTree();

        System.out.println("Balanced Tree:");
        tree.printTree();
    }
}
