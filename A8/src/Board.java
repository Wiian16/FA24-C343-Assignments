import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.stream.Stream;

/*
 * The class Board represents a 2D grid of tiles.
 * It is a generic class that takes a type parameter E.
 * <p>
 * The interesting methods are the ones that return the neighbors of a tile.
 * There are two methods that return the neighbors of a tile:
 * getNeighbors and getFreshNeighbors. The first returns all eight neighbors of a tile
 * (unless of course the tile is on the edge of the board). The second returns only the
 * neighbors that are marked as fresh (see the Tile class).
 * <p>
 * The class also implements the Iterable interface, so that it can be used
 * in a for-each loop.
 */

public class Board<E> implements Iterable<Tile<E>> {
    private final @NotNull Tile<E>[][] tiles;
    private final int boardSize;

    public Board(@NotNull Tile<E>[] @NotNull [] tiles) {
        this.tiles = tiles;
        this.boardSize = tiles.length;
    }

    public int getBoardSize() {
        return boardSize;
    }

    /**
     * Returns the tile at the given row and column. If the row or column are out of bounds,
     * the method returns an empty Optional.
     */
    public @NotNull Optional<Tile<E>> get(int r, int c) {
        if(r >= boardSize || c >= boardSize || r < 0 || c < 0){
            return Optional.empty();
        }

        return Optional.of(tiles[r][c]);
    }

    /**
     * Returns a stream of the neighbors of the tile at the given row and column.
     * For the general case, the stream will have eight tiles.
     * If the tile is on the edge of the board, the stream will have fewer tiles.
     * <p>
     * You can use an ArrayList to collect the neighbors and then return a stream of the list.
     */
    public @NotNull Stream<Tile<E>> getNeighbors(int r, int c) {
        ArrayList<Tile<E>> neighbors = new ArrayList<>(8);

        for(int i = r - 1; i < r + 2; i++){
            for(int j = c - 1; j < c + 2; j++){
                if(i == r && j == c){
                    continue;
                }

                Optional<Tile<E>> tile = get(i, j);
                tile.ifPresent(neighbors::add);
            }
        }

        return neighbors.stream();
    }

    public @NotNull Stream<Tile<E>> getFreshNeighbors(@NotNull Tile<E> tile) {
        return getFreshNeighbors(tile.getRow(), tile.getCol());
    }

    /**
     * Returns a stream of the fresh neighbors of the tile at the given row and column.
     * An easy way to implement this method is to use the getNeighbors method and then
     * filter the stream of neighbors to keep only the fresh ones.
     * (This is the power of streams!)
     */
    public @NotNull Stream<Tile<E>> getFreshNeighbors(int r, int c) {
        return getNeighbors(r, c).filter(Tile::isFresh);
    }

    /**
     * This method returns an instance of the class Iterator that you define in this method.
     */
    public @NotNull Iterator<Tile<E>> iterator() {
        return new Iterator<>() {
            int r = 0;
            int c = 0;

            public boolean hasNext() {
                return r != boardSize && c != boardSize;
            }

            public Tile<E> next() {
                Tile<E> next = get(r, c).orElseThrow(() -> new Error("Iterator.next() called at end of board!"));
                if(c == boardSize - 1) {
                    r++;
                    c = 0;
                }
                else{
                    c++;
                }
                return next;
            }
        };
    }

    public @NotNull String toString() {
        @NotNull StringBuilder sb = new StringBuilder();
        for (@NotNull Tile<E> tile : this) {
            sb.append(tile);
            sb.append(' ');
            if (tile.getCol() == boardSize - 1) sb.append('\n');
        }
        return sb.toString();
    }

    public void paint(@NotNull Graphics2D g2, int offset, @NotNull Dimension dim) {
        Rectangle2D.Double tileBox;
        int tileSize = dim.width / boardSize;

        FontMetrics fm = g2.getFontMetrics();
        int scaleFactor = dim.width / (boardSize * 10 * fm.stringWidth("J"));

        for (@NotNull Tile<E> tile : this) {
            tileBox = new Rectangle2D.Double(
                    offset + tile.getCol() * tileSize,
                    offset + tile.getRow() * tileSize,
                    tileSize,
                    tileSize);
            tile.paint(g2, tileBox, scaleFactor);
        }
    }

}
