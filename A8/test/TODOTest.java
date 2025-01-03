import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class TODOTest {
    public TODOTest() throws IOException {
    }

    Board<Integer> makeBoard(int size){
        Tile<Integer>[][] tiles = makeTiles(size);

        return new Board<>(tiles);
    }

    @SuppressWarnings("unchecked")
    private static Tile<Integer>[] @NotNull [] makeTiles(int size) {
        Tile<Integer>[][] tiles = new Tile[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                tiles[i][j] = new Tile<>(0, i, j);
            }
        }
        return tiles;
    }

    @Test
    public void test1() {
        // write test cases that thoroughly check the 'getNeighbors' method in
        // the 'Board' class. You should have cases for the corners, edges, and
        // middle of the board. You should also have cases for boards of size 1,
        Board<Integer> board1 = makeBoard(1);
        Board<Integer> board3 = makeBoard(3);

        assertEquals(8, board3.getNeighbors(1, 1).count());
        assertEquals(3, board3.getNeighbors(0, 0).count());
        assertEquals(3, board3.getNeighbors(0, 2).count());
        assertEquals(3, board3.getNeighbors(2, 0).count());
        assertEquals(3, board3.getNeighbors(2, 2).count());
        assertEquals(5, board3.getNeighbors(0, 1).count());
        assertEquals(5, board3.getNeighbors(1, 0).count());
        assertEquals(5, board3.getNeighbors(2, 1).count());
        assertEquals(5, board3.getNeighbors(1, 2).count());

        assertEquals(0, board1.getNeighbors(0, 0).count());

        assertEquals(1, board3.getNeighbors(-1, -1).count());
        assertEquals(1, board3.getNeighbors(3, 3).count());

        assertEquals(1, board1.getNeighbors(-1, -1).count());
        assertEquals(1, board1.getNeighbors(1, 1).count());

        assertEquals(0, board3.getNeighbors(-2, -2).count());
        assertEquals(0, board1.getNeighbors(-2, -2).count());
    }

    @Test
    public void test2() {
        // write test cases that thoroughly check the 'getFreshNeighbors' method in
        // the 'Board' class. You should have cases for when none of the neighbors
        // are fresh, all the neighbors are fresh, and some of the neighbors are fresh.
        //
        Tile<Integer>[][] tiles = makeTiles(3);
        Board<Integer> board1 = new Board<>(tiles);

        assertEquals(8, board1.getFreshNeighbors(1, 1).count());
        assertEquals(3, board1.getFreshNeighbors(0, 0).count());
        assertEquals(3, board1.getFreshNeighbors(0, 2).count());
        assertEquals(3, board1.getFreshNeighbors(2, 0).count());
        assertEquals(3, board1.getFreshNeighbors(2, 2).count());
        assertEquals(5, board1.getFreshNeighbors(0, 1).count());
        assertEquals(5, board1.getFreshNeighbors(1, 0).count());
        assertEquals(5, board1.getFreshNeighbors(2, 1).count());
        assertEquals(5, board1.getFreshNeighbors(1, 2).count());

        assertEquals(1, board1.getFreshNeighbors(-1, -1).count());
        assertEquals(1, board1.getFreshNeighbors(3, 3).count());

        assertEquals(0, board1.getFreshNeighbors(-2, -2).count());

        tiles[0][0].setVisited();

        Board<Integer> board2 = new Board<>(tiles);

        assertEquals(7, board2.getFreshNeighbors(1, 1).count());
        assertEquals(3, board2.getFreshNeighbors(0, 0).count());
        assertEquals(3, board2.getFreshNeighbors(0, 2).count());
        assertEquals(3, board2.getFreshNeighbors(2, 0).count());
        assertEquals(3, board2.getFreshNeighbors(2, 2).count());
        assertEquals(4, board2.getFreshNeighbors(0, 1).count());
        assertEquals(4, board2.getFreshNeighbors(1, 0).count());
        assertEquals(5, board2.getFreshNeighbors(2, 1).count());
        assertEquals(5, board2.getFreshNeighbors(1, 2).count());

        assertEquals(0, board2.getFreshNeighbors(-1, -1).count());
        assertEquals(1, board2.getFreshNeighbors(3, 3).count());

        assertEquals(0, board2.getFreshNeighbors(-2, -2).count());

        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[i].length; j++){
                tiles[i][j].setVisited();
            }
        }

        Board<Integer> board3 = new Board<>(tiles);


        assertEquals(0, board3.getFreshNeighbors(1, 1).count());
        assertEquals(0, board3.getFreshNeighbors(0, 0).count());
        assertEquals(0, board3.getFreshNeighbors(0, 2).count());
        assertEquals(0, board3.getFreshNeighbors(2, 0).count());
        assertEquals(0, board3.getFreshNeighbors(2, 2).count());
        assertEquals(0, board3.getFreshNeighbors(0, 1).count());
        assertEquals(0, board3.getFreshNeighbors(1, 0).count());
        assertEquals(0, board3.getFreshNeighbors(2, 1).count());
        assertEquals(0, board3.getFreshNeighbors(1, 2).count());

        assertEquals(0, board3.getFreshNeighbors(-1, -1).count());
        assertEquals(0, board3.getFreshNeighbors(3, 3).count());

        assertEquals(0, board3.getFreshNeighbors(-2, -2).count());


        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[i].length; j++){
                tiles[i][j].reset();
            }
        }

        Board<Integer> board4 = new Board<>(tiles);

        assertEquals(8, board4.getFreshNeighbors(1, 1).count());
        assertEquals(3, board4.getFreshNeighbors(0, 0).count());
        assertEquals(3, board4.getFreshNeighbors(0, 2).count());
        assertEquals(3, board4.getFreshNeighbors(2, 0).count());
        assertEquals(3, board4.getFreshNeighbors(2, 2).count());
        assertEquals(5, board4.getFreshNeighbors(0, 1).count());
        assertEquals(5, board4.getFreshNeighbors(1, 0).count());
        assertEquals(5, board4.getFreshNeighbors(2, 1).count());
        assertEquals(5, board4.getFreshNeighbors(1, 2).count());

        assertEquals(1, board4.getFreshNeighbors(-1, -1).count());
        assertEquals(1, board4.getFreshNeighbors(3, 3).count());

        assertEquals(0, board4.getFreshNeighbors(-2, -2).count());
    }

    @Test
    public void test3() {
        // write test cases that for 'getNeighbors' and 'getFreshNeighbors' that
        // collect the neighbors 'k' steps away from a given tile. You should have
        // cases for when 'k' is 0, 1, 2, and 3. You should also have cases for when
        // 'k' is greater than the size of the board.
    }

    @Test
    public void test4() {
        // write test cases for the methods 'contains' and 'possiblePrefix' in the
        // 'WordList' class. You should have cases for when the word is in the list,
        // when the word is not in the list, when the word is a prefix of a word in
        // the list, and when the word is not a prefix of any word in the list. You
        // should empirically observe that the performance is O(N) where N is the size
        // of the list.
        WordList list = new WordList(new String[]{"table", "chair", "door", "hinge"});

        assertTrue(list.contains("table"));
        assertTrue(list.contains("chair"));
        assertTrue(list.contains("door"));
        assertTrue(list.contains("hinge"));
        assertFalse(list.contains("rug"));
        assertFalse(list.contains("shoe"));

        assertTrue(list.possiblePrefix("tab"));
        assertTrue(list.possiblePrefix("tabl"));
        assertTrue(list.possiblePrefix("table"));
        assertTrue(list.possiblePrefix("c"));
        assertTrue(list.possiblePrefix("chai"));
        assertTrue(list.possiblePrefix("do"));
        assertTrue(list.possiblePrefix("hin"));
        assertTrue(list.possiblePrefix(""));
        assertFalse(list.possiblePrefix("able"));
        assertFalse(list.possiblePrefix("oor"));
        assertFalse(list.possiblePrefix("nope"));
    }

    @Test
    public void test5() {
        // write tests for the method 'insert' in the 'Trie' class. You should have
        // cases for when the word is already in the trie, when the word is not in the
        // trie, when the word is a prefix of a word in the trie, and when the word is
        // not a prefix of any word in the trie. You should empirically observe that
        // the performance is O(L) where L is the length of the word (the corresponding
        // height of the trie).
        Trie trie = new Trie();

        trie.insert("table");
        assertTrue(trie.contains("table"));
        trie.insert("chair");
        assertTrue(trie.contains("table"));
        assertTrue(trie.contains("chair"));
        trie.insert("door");
        assertTrue(trie.contains("table"));
        assertTrue(trie.contains("chair"));
        assertTrue(trie.contains("door"));
        trie.insert("hinge");
        assertTrue(trie.contains("table"));
        assertTrue(trie.contains("chair"));
        assertTrue(trie.contains("door"));
        assertTrue(trie.contains("hinge"));

    }


    @Test
    public void test6() {
        // write test cases for the methods 'contains' and
        // 'possiblePrefix' in the 'Trie' class. You should have cases for when
        // the word is in the trie, when the word is not in the trie, when the
        // word is a prefix of a word in the trie, and when the word is not a
        // prefix of any word in the trie. You should empirically observe that
        // the performance is O(L) where L is the length of the word (the
        // corresponding height of the trie).
        Trie trie = new Trie(new String[]{"table", "chair", "door", "hinge"});

        assertTrue(trie.contains("table"));
        assertTrue(trie.contains("chair"));
        assertTrue(trie.contains("door"));
        assertTrue(trie.contains("hinge"));
        assertFalse(trie.contains("rug"));
        assertFalse(trie.contains("shoe"));

        assertTrue(trie.possiblePrefix("tab"));
        assertTrue(trie.possiblePrefix("tabl"));
        assertTrue(trie.possiblePrefix("table"));
        assertTrue(trie.possiblePrefix("c"));
        assertTrue(trie.possiblePrefix("chai"));
        assertTrue(trie.possiblePrefix("do"));
        assertTrue(trie.possiblePrefix("hin"));
        assertTrue(trie.possiblePrefix(""));
        assertFalse(trie.possiblePrefix("able"));
        assertFalse(trie.possiblePrefix("oor"));
        assertFalse(trie.possiblePrefix("nope"));

    }

    @Test
    public void test7() throws IOException {
        // write test cases for the 'findWordsFromPos' method in the 'Boggle' class.
        // You should construct boards of different sizes and different configurations
        // of letters and special dictionaries to test the following scenarios.
        // The string 's' + the letter at current position form a word in
        // the dictionary, the string 's' + the letter at current position
        // do not form a word in the dictionary, the string 's'
        // + the letter at current position form a prefix of a word in the dictionary,
        // and the string 's' + the letter at current position do not form a prefix of
        // any word in the dictionary.
        Trie dict = new Trie(new File("commonwords.txt"));

        char[][] board = {{'a', 't', 'r', 's'},
                          {'n', 'i', 'o', 't'},
                          {'b', 'e', 'l', 's'},
                          {'u', 'd', 'g', 'y'}};

        Boggle game = new Boggle(board, dict);

        game.findWordsFromPos(new Tile<>('a', 0, 0), "");

        assertEquals(9, game.getFoundWords().size());

        board = new char[][]{{'e', 'i', 'f', 't'},
                             {'l', 'm', 'a', 'r'},
                             {'d', 'n', 'o', 's'},
                             {'c', 'k', 'p', 'q'}};

        game = new Boggle(board, dict);

        game.findWordsFromPos(new Tile<>('a', 1, 2), "");

        assertEquals(17, game.getFoundWords().size());

        board = new char[][]{{'k'}};

        game = new Boggle(board, dict);
        game.findWordsFromPos(new Tile<>('k', 0, 0), "aardvar");

        assertEquals(1, game.getFoundWords().size());

        board = new char[][]{{'r'}};

        game = new Boggle(board, dict);
        game.findWordsFromPos(new Tile<>('r', 0, 0), "aardva");

        assertEquals(0, game.getFoundWords().size());

        board = new char[][]{{'z'}};

        game = new Boggle(board, dict);
        game.findWordsFromPos(new Tile<>('z', 0, 0), "aardvar");

        assertEquals(0, game.getFoundWords().size());

        dict = new Trie(new String[]{"lid", "last", "lip", "lint", "lame", "lug"});

        board = new char[][]{{'r', 'o', 's', 't'},
                             {'e', 'a', 'p', 'n'},
                             {'m', 'l', 'i', 'd'},
                             {'h', 'q', 'u', 'g'}};

        game = new Boggle(board, dict);
        game.findWordsFromPos(new Tile<>('l', 2, 1), "");

        assertEquals(6, game.getFoundWords().size());
    }
}
