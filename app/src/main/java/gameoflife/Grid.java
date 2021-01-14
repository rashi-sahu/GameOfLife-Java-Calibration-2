package gameoflife;

import java.util.ArrayList;

public class Grid {
    private int row;
    private int column;
    private ArrayList<ArrayList<Integer>> grid;

    public Grid(ArrayList<ArrayList<Integer>> grid) {
        this.row = grid.size();
        this.column = grid.size() != 0 ? grid.get(0).size() : 0;
        this.grid = grid;
    }

    public void printGrid() {
        if (grid.size() > 0) {
            for (int i = 0; i < grid.size(); i++) {
                for (int j = 0; j < grid.get(i).size(); j++) {
                    if (grid.get(i).get(j) == 0)
                        System.out.print(".");
                    else
                        System.out.print("*");
                }
                System.out.println();
            }
        } else {
            return;
        }
    }
}
