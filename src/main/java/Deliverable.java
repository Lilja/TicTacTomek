package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Deliverable {
    public static void main(String[] args) throws FileNotFoundException {
        // Read inputs from stdin
        String[][] unparsedInputs = stdinToString();

        System.setOut(new PrintStream("stdout2.out"));
        // iterate over each captured test case and evaluate the state
        for (int i = 0; i < unparsedInputs.length; i++) {
            String[] input = unparsedInputs[i];
            Tile[][] game = parseInput(input);

            // Print friendly string back to stdout
            System.out.println("Case #"+(i+1) + ": " + codeToString(decideGameStatus(game)));
        }
    }

    public static String[][] stdinToString() {
        //Scanner sc = new Scanner(System.in);
        Scanner sc = null;
        try {
            sc = new Scanner(new File("A-small-practice.in"));
        } catch (FileNotFoundException e) {
            System.out.println("The specified input file does not exists. Please re-check spelling.");
            System.exit(1);
        }
        final int testCases = Integer.parseInt(sc.nextLine());
        String[][] unparsedInput = new String[testCases][4];

        for (int i = 0; i < testCases; i++) {
            String[] temp = new String[4];

            for (int j = 0; j < 4; j++) {
                temp[j] = sc.nextLine();
            }
            // Consume a raw newline that separates each test case
            // do it for all except the last one.
            if (i<testCases-1) {
                sc.nextLine();
            }
            unparsedInput[i] = temp;
        }

        return unparsedInput;
    }

    /**
     * Parse string to Tile[][].
     * @param args - 4x4 2d array
     * @return Tile[][]
     */
    public static Tile[][] parseInput(String[] args) {
        Tile[][] game  = initGame();
        Tile[] tempRow = null;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tempRow = parseRowToGameRow(args[i]);
                game[i] = tempRow;
            }
        }

        return game;
    }

    /**
     * Translates game status codes to human readable info
     * @param code - a code that .decideGameStatus() returns
     * @return String that is human readable
     */
    public static String codeToString(int code) {
        if(code == 0) {
            return "Game has not completed";
        } else if (code == 1) {
            return "Draw";
        } else if (code == 2) {
            return "O won";
        } else {
            return "X won";
        }
    }

    public static Tile[][] initGame() {
        Tile[][] game = new Tile[4][4];
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                game[i][j] = new Tile(".");
            }
        }
        return game;
    }

    /**
     * Transforms a string to a tile[].
     * Example: the string "XXXO" => Tile["X", "X", "X", "O"]
     * @param arg
     * @return Tile[]
     */
    public static Tile[] parseRowToGameRow(String arg) {
        Tile[] temp = new Tile[4];
        String[] row = arg.split("");
        for (int i = 0; i < row.length; i++) {
            temp[i] = new Tile(row[i]);
        }
        return temp;
    }

    /**
     * Beautifies a tile[][], debug stuff
     * @param t
     * @return
     */
    public static String stringify(Tile[][] t) {
       String k = "";
        for (Tile[] a : t) {
            for (Tile b : a) {
                k += b + "";
            }
            k += "\n";
        }
        return k;
    }

    /**
     * Validates the diagonal pattern in a tic-tac-toe game,
     * Top Left to Bottom Right
     * @param game
     * @return int[3], [0] = Amount of O tiles, [1] = Amount of X tiles, [2] = Amount of T tiles
     */
    public static int[] checkDiagonalTLBR(Tile[][] game) {
        Tile[] topLeftBotRight = new Tile[]{game[0][0], game[1][1], game[2][2], game[3][3]};
        return countTile(topLeftBotRight);
    }

    /**
     * Validates the diagonal pattern in a tic-tac-toe game,
     * Top Right to Bottom Right
     * @param game
     * @return int[3], [0] = Amount of O tiles, [1] = Amount of X tiles, [2] = Amount of T tiles
     */
    public static int[] checkDiagonalTRBL(Tile[][] game) {
        // top right, bottom left
        Tile[] topRightBotLeft = new Tile[]{game[0][3], game[1][2], game[2][1], game[3][0]};
        return countTile(topRightBotLeft);
    }

    /**
     * Validates the Top-Bottom pattern in a tic-tac-toe game
     * @param game
     * @return ResultObject
     */
    public static ResultObject checkTopBottom(Tile[][] game) {
        Tile[][] temp = new Tile[4][4];
        ResultObject cr = new ResultObject();

        // Transpose the 2d-matrix
        for ( int i = 0; i < 4; i++) {
            for ( int j = 0; j < 4; j++) {
                temp[i][j] = game[j][i];
            }
        }

        for (int i = 0; i < 4; i++) {
            int maybeWinner = decideGameWinnerOfTile(countTile(temp[i]));
            if (maybeWinner > 0) {
                cr.value = maybeWinner;
                cr.code = 0;
                cr.message = "top bottom";
                // Stop loop
                i = 4;
            }
        }

        return cr;
    }

    /**
     * Validates the Left-Right pattern in a tic-tac-toe game
     * @param game
     * @return ResultObject
     */
    public static ResultObject checkLeftRight(Tile[][] game) {
        ResultObject cr = new ResultObject();

        for (int i = 0; i < 4; i++) {
            int maybeWinner = decideGameWinnerOfTile(countTile(game[i]));
            if (maybeWinner > 0) {
                cr.value = maybeWinner;
                cr.code = 0;
                cr.message = "Left right";
                // Stop loop
                i = 4;
            }
        }

        return cr;
    }

   /**
     * Returns an array of how many X|O|T-tiles is inside of a row/column
     * @param subField - a row or a column
     * @return an array of [amount of o's in the field, amount of x's in the field, amount of t's in the field]
     */
    public static int[] countTile(Tile[] subField) {

        int amountOfOs = 0;
        int amountOfXs = 0;
        int amountOfTs = 0;
        for (Tile tile : subField) {
            if (tile.getPosition() == Tile.O)
                amountOfOs++;
            else if (tile.getPosition() == Tile.X)
                amountOfXs++;
            if (tile.getPosition() == Tile.T)
                amountOfTs++;
        }
        int[] ret = new int[3];
        ret[0] = amountOfOs;
        ret[1] = amountOfXs;
        ret[2] = amountOfTs;
        return ret;
    }

    /**
     * Validate winner based of status codes that is .decideGameResult()-friendly
     * @param tileResult - validation of a tile row/column that has been run in .countTile()
     * @return the winner, 2 = O, 3 = X, 0 = no winner/draw/in progress
     */
    public static int decideGameWinnerOfTile(int[] tileResult) {
        int amountOfOs = tileResult[0];
        int amountOfXs = tileResult[1];
        int amountOfTs = tileResult[2];

        if(amountOfOs + amountOfTs == 4) {
            return 2;
        } else if(amountOfXs + amountOfTs == 4) {
            return 3;
        } else
            return 0;
    }

    public static boolean isInProgress(Tile[][] game) {
        boolean ret = false;
        for (int i = 0; i < game.length; i++) {
            Tile[] column = game[i];
            for (Tile row : column) {
                if (row.getPosition() == Tile.FIELD) {
                    ret = true;
                    i = game.length;
                }
            }
        }
        return ret;
    }

    /**
     *
     * @param game
     * @return the result, 0 = ongoing, 1 = draw, 2 = O won, 3 = X won
     */
    public static int decideGameStatus(Tile[][] game) {
        /*   0 - ongoing
            1 - Draw
            2 - O won
            3 - X won
        */
        // check diagonals
        // top left, bottom right

        int[] diagTLBR = checkDiagonalTLBR(game);
        int amountOfOs = diagTLBR[0];
        int amountOfXs = diagTLBR[1];
        int amountOfTs = diagTLBR[2];

        if (amountOfOs + amountOfTs == 4) {
            return 2;
        }
        if (amountOfXs + amountOfTs == 4) {
            return 3;
        }

        // top right, bottom left
        int[] diagTRBL = checkDiagonalTRBL(game);
        amountOfOs = diagTRBL[0];
        amountOfXs = diagTRBL[1];
        amountOfTs = diagTRBL[2];

        if (amountOfOs + amountOfTs == 4) {
            return 2;
        }
        if (amountOfXs + amountOfTs == 4) {
            return 3;
        }

        // top-to-bottom
        ResultObject cr = checkTopBottom(game);
        if (cr.code == 0) {
            return cr.value;
        }

        // left-to-right
        cr = checkLeftRight(game);
        if (cr.code == 0) {
            return cr.value;
        }

        if (isInProgress(game)) {
            return 0;
        } else {
            return 1;
        }
    }
}
