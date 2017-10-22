package main.java;

public class Tile {
    private int position;
    public static int FIELD = 0;
    public static int O     = 1;
    public static int X     = 2;
    public static int T     = 3;

    public Tile(Tile t){
        this.position = t.position;
    }

    public Tile(String position){
        switch (position) {
            case "O":
                this.position = 1;
                break;
            case "X":
                this.position = 2;
                break;
            case "T":
                this.position = 3;
                break;
            default:
                this.position = 0;
                break;
        }
    }

    public int getPosition(){
        return this.position;
    }

    @Override
    public String toString() {
        if (this.position == 0) {
            return ".";
        } else if (this.position == 1) {
            return "O";
        } else if (this.position == 2) {
            return "X";
        } else {
            return "T";
        }
    }
}
