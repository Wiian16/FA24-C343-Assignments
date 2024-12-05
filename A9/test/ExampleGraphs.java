import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Set;

public class ExampleGraphs {

    public static @NotNull Set<DirectedGraph> allGraphs () {
        return Set.of(g0(), g1(), g2(), g3());
    }
    public static @NotNull DirectedGraph g0() {
        /*
         *
         *    A -> B
         *    |    |
         *    v    v
         *    C -> D
         *
         */
        Edge ab, ac, bd, cd;
        ab = new Edge("A", "B");
        ac = new Edge("A", "C");
        bd = new Edge("B", "D");
        cd = new Edge("C", "D");

        HashMap<String, Set<Edge>> adjacencyLists = new HashMap<>();
        adjacencyLists.put("A", Set.of(ab, ac));
        adjacencyLists.put("B", Set.of(bd));
        adjacencyLists.put("C", Set.of(cd));
        adjacencyLists.put("D", Set.of());

        return new DirectedGraph("g0", adjacencyLists);
    }

    public static @NotNull DirectedGraph g1() {
        /*
         *
         *    A -> B -> D
         *    |  / ^   /
         *    | /  |  /
         *    vv   v v
         *    C    E
         *
         */
        Edge ab, ac, bc, bd, be, de, eb;
        ab = new Edge("A", "B");
        ac = new Edge("A", "C");
        bc = new Edge("B", "C");
        bd = new Edge("B", "D");
        be = new Edge("B", "E");
        de = new Edge("D", "E");
        eb = new Edge("E", "B");

        HashMap<String, Set<Edge>> adjacencyLists = new HashMap<>();
        adjacencyLists.put("A", Set.of(ab, ac));
        adjacencyLists.put("B", Set.of(bc, bd, be));
        adjacencyLists.put("C", Set.of());
        adjacencyLists.put("D", Set.of(de));
        adjacencyLists.put("E", Set.of(eb));

        return new DirectedGraph("g1", adjacencyLists);
    }

    public static @NotNull DirectedGraph g2() {
        /*
         *
         *                 A
         *              /  |  \
         *             /   |    \
         *           A2    A7    A8
         *          /  \          |  \
         *         /    \         |    \
         *        A3      A6      A9    A12
         *         |  \            | \
         *         |    \          |   \
         *        A4     A5       A10   A11
         *
         */
        Edge a1a2, a1a7, a1a8;
        Edge a2a3, a2a6;
        Edge a3a4, a3a5;
        Edge a8a9, a8a12;
        Edge a9a10, a9a11;
        a1a2 = new Edge("A", "A2");
        a1a7 = new Edge("A", "A7");
        a1a8 = new Edge("A", "A8");
        a2a3 = new Edge("A2", "A3");
        a2a6 = new Edge("A2", "A6");
        a3a4 = new Edge("A3", "A4");
        a3a5 = new Edge("A3", "A5");
        a8a9 = new Edge("A8", "A9");
        a8a12 = new Edge("A8", "A12");
        a9a10 = new Edge("A9", "A10");
        a9a11 = new Edge("A9", "A11");

        HashMap<String, Set<Edge>> adjacencyLists = new HashMap<>();
        adjacencyLists.put("A", Set.of(a1a2, a1a7, a1a8));
        adjacencyLists.put("A2", Set.of(a2a3, a2a6));
        adjacencyLists.put("A3", Set.of(a3a4, a3a5));
        adjacencyLists.put("A4", Set.of());
        adjacencyLists.put("A5", Set.of());
        adjacencyLists.put("A6", Set.of());
        adjacencyLists.put("A7", Set.of());
        adjacencyLists.put("A8", Set.of(a8a9, a8a12));
        adjacencyLists.put("A9", Set.of(a9a10, a9a11));
        adjacencyLists.put("A10", Set.of());
        adjacencyLists.put("A11", Set.of());
        adjacencyLists.put("A12", Set.of());

        return new DirectedGraph("g2", adjacencyLists);
    }

    public static @NotNull DirectedGraph g3() {
        /*
         *
         *                  E
         *                  | \
         *                  |   \
         *                  |     \
         *                  |       v
         *         G -------+-----> I
         *         ^ ^      |       |
         *         |  \     |       v
         *         |   -----+------ H
         *         |        |      ^  \
         *         |        |     /     \
         *         |        |    /        \
         *         |        |   /           \
         *         |        v /              v
         *         B -----> A --> C --> D --> F -----> J
         *                        ^                    |
         *                        |                    |
         *                        +--------------------+
         *
         */
        Edge ac, ah, ba, bg, cd, df, ea, ei, fj, gi, hf, hg, ih, jc;
        ac = new Edge("A", "C");
        ah = new Edge("A", "H");
        ba = new Edge("B", "A");
        bg = new Edge("B", "G");
        cd = new Edge("C", "D");
        df = new Edge("D", "F");
        ea = new Edge("E", "A");
        ei = new Edge("E", "I");
        fj = new Edge("F", "J");
        gi = new Edge("G", "I");
        hf = new Edge("H", "F");
        hg = new Edge("H", "G");
        ih = new Edge("I", "H");
        jc = new Edge("J", "C");

        HashMap<String, Set<Edge>> adjacencyLists = new HashMap<>();
        adjacencyLists.put("A", Set.of(ac, ah));
        adjacencyLists.put("B", Set.of(ba, bg));
        adjacencyLists.put("C", Set.of(cd));
        adjacencyLists.put("D", Set.of(df));
        adjacencyLists.put("E", Set.of(ea, ei));
        adjacencyLists.put("F", Set.of(fj));
        adjacencyLists.put("G", Set.of(gi));
        adjacencyLists.put("H", Set.of(hf, hg));
        adjacencyLists.put("I", Set.of(ih));
        adjacencyLists.put("J", Set.of(jc));

        return new DirectedGraph("g3", adjacencyLists);
    }


}
