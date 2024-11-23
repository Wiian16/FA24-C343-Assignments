import graph.noweight.DirectedGraph;
import graph.withweight.WeightedDirectedGraph;

public class ExampleGraphs {

    public static DirectedGraph g0() {
        DirectedGraph g = new DirectedGraph();
        g.insertEdge("A", "B");
        g.insertEdge("A", "C");
        g.insertEdge("B", "D");
        g.insertEdge("C", "D");
        return g;
    }

    public static DirectedGraph g1() {
        DirectedGraph g = new DirectedGraph();
        g.insertEdge("A", "B");
        g.insertEdge("A", "C");
        g.insertEdge("B", "C");
        g.insertEdge("B", "D");
        g.insertEdge("B", "E");
        g.insertEdge("D", "E");
        g.insertEdge("E", "B");
        return g;
    }

    public static DirectedGraph g2() {
        DirectedGraph g = new DirectedGraph();
        g.insertEdge("A1", "A2");
        g.insertEdge("A1", "A7");
        g.insertEdge("A1", "A8");
        g.insertEdge("A2", "A3");
        g.insertEdge("A2", "A6");
        g.insertEdge("A3", "A4");
        g.insertEdge("A3", "A5");
        g.insertEdge("A8", "A9");
        g.insertEdge("A8", "A12");
        g.insertEdge("A9", "A10");
        g.insertEdge("A9", "A11");
        return g;
    }

    public static DirectedGraph g3() {
        DirectedGraph g = new DirectedGraph();
        g.insertEdge("A", "C");
        g.insertEdge("A", "H");
        g.insertEdge("B", "A");
        g.insertEdge("B", "G");
        g.insertEdge("C", "D");
        g.insertEdge("D", "F");
        g.insertEdge("E", "A");
        g.insertEdge("E", "I");
        g.insertEdge("F", "J");
        g.insertEdge("G", "I");
        g.insertEdge("H", "F");
        g.insertEdge("H", "G");
        g.insertEdge("I", "H");
        g.insertEdge("J", "C");
        return g;
    }

    public static WeightedDirectedGraph g4() {
        WeightedDirectedGraph g = new WeightedDirectedGraph();
        g.insertEdge("A", "B", 1);
        g.insertEdge("A", "C", 2);
        g.insertEdge("B", "C", 3);
        g.insertEdge("B", "D", 4);
        g.insertEdge("B", "E", 5);
        g.insertEdge("D", "E", 6);
        g.insertEdge("E", "B", 7);
        return g;
    }

    public static WeightedDirectedGraph g5() {
        WeightedDirectedGraph g = new WeightedDirectedGraph();
        g.insertEdge("A", "B", 4);
        g.insertEdge("A", "C", 2);
        g.insertEdge("B", "C", 5);
        g.insertEdge("B", "D", 10);
        g.insertEdge("C", "E", 3);
        g.insertEdge("D", "F", 11);
        g.insertEdge("E", "D", 4);
        return g;
    }

    public static WeightedDirectedGraph g6() {
        /*
                        A
                      /   \
                    B  ---  C
                    | \   / |
                    |   D   |
                    | / | \ |
                    E   |   F
                     \  |  /
                        G

        AC, BD, CD, DE, CF, EG

        */

        WeightedDirectedGraph g = new WeightedDirectedGraph();
        g.insertEdge("A", "B", 8);
        g.insertEdge("A", "C", 5);
        g.insertEdge("B", "C", 10);
        g.insertEdge("B", "D", 2);
        g.insertEdge("C", "D", 3);
        g.insertEdge("B", "E", 18);
        g.insertEdge("D", "E", 12);
        g.insertEdge("C", "F", 16);
        g.insertEdge("D", "F", 30);
        g.insertEdge("E", "G", 4);
        g.insertEdge("D", "G", 14);
        g.insertEdge("F", "G", 26);
        return g.bidirectional();
    }

    public static WeightedDirectedGraph g7() {
        /*
                  -- A --------- B --
             10 /    |     5     |    \ 7
              S    2 |         8 |      T
              8 \    |    10     |    / 10
                  -- C --------- D --

            Max flow from S to T is 15
         */

        WeightedDirectedGraph g = new WeightedDirectedGraph();
        g.insertEdge("S", "A", 10);
        g.insertEdge("S", "C", 8);
        g.insertEdge("A", "C", 2);
        g.insertEdge("A", "B", 5);
        g.insertEdge("C", "D", 10);
        g.insertEdge("D", "B", 8);
        g.insertEdge("B", "T", 7);
        g.insertEdge("D", "T", 10);
        return g;
    }

    public static WeightedDirectedGraph g8() {
        /*

                 ------------- A
                /              | 8
             7 /          3    |
              S -------------- B
              \                |
              3 \              | 5
                  ------------ C -------- D --------- T
                                     2          1

            Max flow from S to T is 1
         */

        WeightedDirectedGraph g = new WeightedDirectedGraph();
        g.insertEdge("S", "A", 7);
        g.insertEdge("S", "B", 3);
        g.insertEdge("S", "C", 3);
        g.insertEdge("A", "B", 8);
        g.insertEdge("B", "C", 5);
        g.insertEdge("C", "D", 2);
        g.insertEdge("D", "T", 1);
        return g;
    }
}
