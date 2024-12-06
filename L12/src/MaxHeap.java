import java.util.*;

public class MaxHeap {
    private List<String> nodes; // Heap nodes
    private Map<String, Integer> indices; // Node indices
    private Map<String, Integer> weights; // Node weights
    private int size; // Heap size

    public MaxHeap() {
        nodes = new ArrayList<>();
        indices = new HashMap<>();
        weights = new HashMap<>();
        size = 0;
    }

    // Swap two nodes
    public void swap(String n1, String n2) {
        int index1 = indices.get(n1);
        int index2 = indices.get(n2);

        nodes.set(index1, n2);
        nodes.set(index2, n1);

        indices.put(n2, index1);
        indices.put(n1, index2);
    }

    // Heapify up
    public void moveUp(String n) {
        // TODO: Implement heapifyUp logic
        // 1. Retrieve the parent of the current element.
        // 2. Compare the element with its parent; if its weight is greater, swap them.
        // 3. Continue moving up until the heap property is restored or the root is reached.
        Optional<String> parentOptional = getParent(n);

        if(parentOptional.isEmpty()){
            return;
        }

        String parent = parentOptional.get();

        if(weights.get(n) > weights.get(parent)){
            swap(n, parent);
            moveUp(parent);
        }
    }

    // Heapify down
    public void moveDown(String n) {
        // TODO: Implement heapifyDown logic
        // 1. Start with the current element and assume it is the largest.
        // 2. Retrieve the left, middle, and right children of the current element.
        // 3. Compare the weights of the children with the current element to find the largest.
        // 4. If a child has a greater weight, swap it with the current element.
        // 5. Update the current element to the largest child and repeat the process.
        // 6. Stop when the current element is larger than all its children or no children exist.
        // 7. This ensures the heap property is restored by moving the element down.
        String largest = n;

        for(Optional<String> child : List.of(getLeftChild(n), getMiddleChild(n), getRightChild(n))){
            if(child.isPresent() && weights.get(child.get()) > weights.get(largest)){
                largest = child.get();
            }
        }

        if(largest.equals(n)){
            return;
        }

        swap(n, largest);
        moveDown(n);
    }

    // Get parent
    public Optional<String> getParent(String n) {
        int parentIndex = (indices.get(n) - 1) / 3;
        if (parentIndex < 0) return Optional.empty();
        return Optional.of(nodes.get(parentIndex));
    }

    // Get left child
    public Optional<String> getLeftChild(String n) {
        int leftIndex = 3 * indices.get(n) + 1;
        if (leftIndex >= size) return Optional.empty();
        return Optional.of(nodes.get(leftIndex));
    }

    // Get middle child
    public Optional<String> getMiddleChild(String n) {
        int middleIndex = 3 * indices.get(n) + 2;
        if (middleIndex >= size) return Optional.empty();
        return Optional.of(nodes.get(middleIndex));
    }

    // Get right child
    public Optional<String> getRightChild(String n) {
        int rightIndex = 3 * indices.get(n) + 3;
        if (rightIndex >= size) return Optional.empty();
        return Optional.of(nodes.get(rightIndex));
    }

    // Insert a new node
    public void insert(String n, int weight) {
        // TODO: Add the node to the heap and adjust its position
        // 1. Add the new element to the heap.
        // 2. Store the weight and its index for tracking in the heap structure.
        // 3. Update the size of the heap to reflect the insertion.
        // 4. Reheapify by moving the newly added element up to restore the heap property.
        nodes.addLast(n);
        indices.put(n, nodes.size() - 1);
        weights.put(n, weight);

        size++;

        moveUp(n);
    }

    // Extract the maximum node
    public String extractMax() {
        // TODO: Remove and return the max element, then reheapify
        // 1. Check if the heap is empty; return null if true.
        // 2. Extract the maximum element (root of the heap).
        // 3. Replace the root with the last element, remove the last element, and adjust the size.
        // 4. Reheapify the heap by moving the new root down to restore the heap property.
        if(nodes.isEmpty()){
            return null;
        }

        swap(nodes.getFirst(), nodes.getLast());

        String max = nodes.removeLast();
        indices.remove(max);
        weights.remove(max);

        size--;

        if(size != 0) {
            moveDown(nodes.getFirst());
        }

        return max;
    }
    // Get a list of all nodes in the heap
    public List<String> getNodes() {
        return new ArrayList<>(nodes);
    }
}
