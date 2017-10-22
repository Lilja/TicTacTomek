package test.java;

import main.java.Deliverable;
import main.java.Tile;

import static org.junit.Assert.*;

public class Test {

    @org.junit.Test
    public void test() {
        String[] input = new String[]{"X..O","X.O.","X.O.","X.O."};
        Tile[][] tiles = Deliverable.parseInput(input);
        Deliverable.decideGameStatus(tiles);
        assertEquals(3, Deliverable.decideGameStatus(tiles));
    }

    @org.junit.Test
    public void testTiles1() {
        String[] input = new String[]{"O..X","O.X.","O.X.","O.X."};
        Tile[][] tiles = Deliverable.parseInput(input);
        Deliverable.decideGameStatus(tiles);
        assertEquals(2, Deliverable.decideGameStatus(tiles));
    }

    @org.junit.Test
    public void testTiles2() {
        String[] input = new String[]{"XXXX", "....", "....", "OOO."};
        Tile[][] tiles = Deliverable.parseInput(input);
        Deliverable.decideGameStatus(tiles);
        assertEquals(3, Deliverable.decideGameStatus(tiles));
    }

    @org.junit.Test
    public void testTiles3() {
        String[] input = new String[]{"OOOO", "....", "....", "XXX."};
        Tile[][] tiles = Deliverable.parseInput(input);
        Deliverable.decideGameStatus(tiles);
        assertEquals(2, Deliverable.decideGameStatus(tiles));
    }

    @org.junit.Test
    public void testTiles4() {
        String[] input = new String[]{"OOOO", "....", "....", "XXX."};
        Tile[][] tiles = Deliverable.parseInput(input);
        Deliverable.decideGameStatus(tiles);
        assertEquals(2, Deliverable.decideGameStatus(tiles));
    }

    @org.junit.Test
    public void testTiles5() {
        String[] input = new String[]{"...O", "..O.", ".O..", "OXX."};
        Tile[][] tiles = Deliverable.parseInput(input);
        Deliverable.decideGameStatus(tiles);
        assertEquals(2, Deliverable.decideGameStatus(tiles));
    }

    @org.junit.Test
    public void testTiles6() {
        String[] input = new String[]{"...X", "..X.", ".X..", "XOO."};
        Tile[][] tiles = Deliverable.parseInput(input);
        Deliverable.decideGameStatus(tiles);
        assertEquals(3, Deliverable.decideGameStatus(tiles));
    }

    @org.junit.Test
    public void testInProgress() {
        String[] input = new String[]{"...X", "....", "....", "...."};
        Tile[][] tiles = Deliverable.parseInput(input);
        Deliverable.decideGameStatus(tiles);
        assertEquals(0, Deliverable.decideGameStatus(tiles));
    }

    @org.junit.Test
    public void testDraw() {
        String[] input = new String[]{  "XOTX",
                                        "OXXO",
                                        "XOOX",
                                        "XXOX"};
        Tile[][] tiles = Deliverable.parseInput(input);
        Deliverable.decideGameStatus(tiles);
        assertEquals(1, Deliverable.decideGameStatus(tiles));
    }

    @org.junit.Test
    public void testWinWithWildCardLeftToRight() {
        String[] input = new String[]{  "TXXX",
                                        "..O.",
                                        ".O..",
                                        "...."};
        Tile[][] tiles = Deliverable.parseInput(input);
        Deliverable.decideGameStatus(tiles);
        assertEquals(3, Deliverable.decideGameStatus(tiles));
    }

    @org.junit.Test
    public void testWinWithWildCardLeftToRight2() {
        String[] input = new String[]{  "TOOO",
                                        "..X.",
                                        ".X..",
                                        "...."};
        Tile[][] tiles = Deliverable.parseInput(input);
        Deliverable.decideGameStatus(tiles);
        assertEquals(2, Deliverable.decideGameStatus(tiles));
    }

    @org.junit.Test
    public void testWinWithWildCardDiag() {
        String[] input = new String[]{  "T...",
                                        ".X..",
                                        "OOX.",
                                        "...X"};
        Tile[][] tiles = Deliverable.parseInput(input);
        Deliverable.decideGameStatus(tiles);
        assertEquals(3, Deliverable.decideGameStatus(tiles));
    }

    @org.junit.Test
    public void testWinWithWildCardDiag2() {
        String[] input = new String[]{  "T...",
                                        ".O..",
                                        "XXO.",
                                        "...O"};
        Tile[][] tiles = Deliverable.parseInput(input);
        Deliverable.decideGameStatus(tiles);
        assertEquals(2, Deliverable.decideGameStatus(tiles));
    }

    @org.junit.Test
    public void testWinWithWildCardDiag3() {
        String[] input = new String[]{  "...X",
                                        "..X.",
                                        ".T..",
                                        "XOO."};
        Tile[][] tiles = Deliverable.parseInput(input);
        Deliverable.decideGameStatus(tiles);
        assertEquals(3, Deliverable.decideGameStatus(tiles));
    }

    @org.junit.Test
    public void testWinWithWildCardDiag4() {
        String[] input = new String[]{  "...O",
                                        "..O.",
                                        ".T..",
                                        "OXX."};
        Tile[][] tiles = Deliverable.parseInput(input);
        Deliverable.decideGameStatus(tiles);
        assertEquals(2, Deliverable.decideGameStatus(tiles));
    }

    @org.junit.Test
    public void testWinWithWildCardTopToBottom() {
        String[] input = new String[]{  "X...",
                                        "T...",
                                        "XOO.",
                                        "X..."};
        Tile[][] tiles = Deliverable.parseInput(input);
        Deliverable.decideGameStatus(tiles);
        assertEquals(3, Deliverable.decideGameStatus(tiles));
    }

    @org.junit.Test
    public void testWinWithWildCardTopToBottom2() {
        String[] input = new String[]{  "O...",
                                        "T...",
                                        "OXX.",
                                        "O..."};
        Tile[][] tiles = Deliverable.parseInput(input);
        Deliverable.decideGameStatus(tiles);
        assertEquals(2, Deliverable.decideGameStatus(tiles));
    }

    @org.junit.Test
    public void testWinWithWildCardTopToBottom3() {
        String[] input = new String[]{  "X.T.",
                                        "..O.",
                                        "X.O.",
                                        "..O."};
        Tile[][] tiles = Deliverable.parseInput(input);
        Deliverable.decideGameStatus(tiles);
        assertEquals(2, Deliverable.decideGameStatus(tiles));
    }
}
