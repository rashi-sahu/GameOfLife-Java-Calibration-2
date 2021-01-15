package gameoflife;

import java.util.ArrayList;
import java.util.Objects;

public class Grid {
    public int row;
    public int column;
    public ArrayList<ArrayList<Integer>> grid;

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

    public Grid nextGeneration() {
        expandGrid();
        ArrayList<ArrayList<Integer>> gridCopy = generateDeepCopyOfArrayList();
        calculateNextGenerationCells(gridCopy);
        compressGrid();
        //System.out.print(grid);
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Grid) {
            Grid that = (Grid) obj;
            return Objects.equals(this.row, that.row) && Objects.equals(this.column, that.column) &&
                    checkEqualityOfContent(that);
        }
        return false;
    }

    private boolean checkEqualityOfContent(Grid obj) {
        for (int i = 0; i < obj.row; i++) {
            for (int j = 0; j < obj.column; j++) {
                if (this.grid.get(i).get(j) != obj.grid.get(i).get(j)) {
                    return false;
                }
            }
        }

        return true;
    }

    private Grid expandGrid() {
        ArrayList<ArrayList<Integer>> expandedGrid = new ArrayList<>();
        if (grid.size() > 0) {
            for (int i = 0; i < grid.size() + 2; i++) {
                ArrayList<Integer> row = new ArrayList<>();
                for (int j = 0; j < grid.get(0).size() + 2; j++) {
                    row.add(0);
                }
                expandedGrid.add(row);
            }

            for (int i = 1; i < expandedGrid.size() - 1; i++) {
                for (int j = 1; j < expandedGrid.get(0).size() - 1; j++) {
                    expandedGrid.get(i).set(j, grid.get(i - 1).get(j - 1));
                }
            }

            grid = expandedGrid;
            row = row + 2;
            column = column + 2;
        }

        return this;
    }

    private ArrayList<ArrayList<Integer>> generateDeepCopyOfArrayList() {
        ArrayList<ArrayList<Integer>> gridCopy = new ArrayList<>();
        for (int index = 0; index < grid.size(); index++) {
            gridCopy.add(new ArrayList<>(grid.get(index)));
        }

        return gridCopy;
    }

    private Grid calculateNextGenerationCells(ArrayList<ArrayList<Integer>> gridCopy) {
        for (int l = 0; l < gridCopy.size(); l++) {
            for (int m = 0; m < gridCopy.get(0).size(); m++) {
                int aliveNeighbours = countAliveNeighbours(gridCopy, l, m);
                if ((gridCopy.get(l).get(m) == 1) && (aliveNeighbours < 2))
                    grid.get(l).set(m, 0);

                else if ((gridCopy.get(l).get(m) == 1) && (aliveNeighbours > 3))
                    grid.get(l).set(m, 0);

                else if ((gridCopy.get(l).get(m) == 0) && (aliveNeighbours == 3))
                    grid.get(l).set(m, 1);

                else
                    grid.get(l).set(m, gridCopy.get(l).get(m));
            }
        }

        return this;
    }

    private int countAliveNeighbours(ArrayList<ArrayList<Integer>> gridCopy, int x, int y) {
        int aliveNeighbours = 0;

        if (row > 0) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (checkValidCell(x + i, y + j)) {
                        aliveNeighbours += gridCopy.get(x + i).get(y + j);
                    }
                }
            }

            aliveNeighbours -= gridCopy.get(x).get(y);
        }

        return aliveNeighbours;
    }

    private boolean checkValidCell(int x, int y) {
        if (x >= 0 && x < row && y >= 0 && y < column) {
            return true;
        } else {
            return false;
        }
    }

    private Grid compressGrid() {
        deleteFromTop();
        deleteFromBottom();
        deleteFromLeft();
        deleteFromRight();
        return this;
    }

    private Grid deleteFromTop() {
        ArrayList<ArrayList<Integer>> gridCopy = generateDeepCopyOfArrayList();
        outerloop:
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                if (grid.get(i).get(j) == 1) {
                    break outerloop;
                }
            }
            gridCopy.remove(0);
        }
        grid = gridCopy;
        row = gridCopy.size();
        column = gridCopy.size() != 0 ? gridCopy.get(0).size() : 0;
        return this;
    }

    private Grid deleteFromBottom() {
        ArrayList<ArrayList<Integer>> gridCopy = generateDeepCopyOfArrayList();
        outerloop:
        for (int i = grid.size() - 1; i >= 0; i--) {
            for (int j = grid.get(i).size() - 1; j >= 0; j--) {
                if (grid.get(i).get(j) == 1)
                    break outerloop;
            }
            gridCopy.remove(gridCopy.size() - 1);
        }
        grid = gridCopy;
        row = gridCopy.size();
        column = gridCopy.size() != 0 ? gridCopy.get(0).size() : 0;
        return this;
    }

    private Grid deleteFromLeft() {
        ArrayList<ArrayList<Integer>> gridCopy = generateDeepCopyOfArrayList();

        if (grid.size() > 0) {
            int col = 0;
            outerloop:
            for (int j = 0; j < grid.get(0).size(); j++) {
                for (int i = 0; i < grid.size(); i++) {
                    if (grid.get(i).get(j) == 1) {
                        col = j;
                        break outerloop;
                    }
                }
            }

            for (int j = 0; j < col; j++) {
                for (int i = 0; i < grid.size(); i++) {
                    gridCopy.get(i).remove(0);
                }
            }

            grid = gridCopy;
            row = gridCopy.size();
            column = gridCopy.size() != 0 ? gridCopy.get(0).size() : 0;
        }
        return this;
    }

    private Grid deleteFromRight() {
        ArrayList<ArrayList<Integer>> gridCopy = generateDeepCopyOfArrayList();

        if (grid.size() > 0) {
            int col = 0;
            outerloop:
            for (int j = grid.get(0).size() - 1; j >= 0; j--) {
                for (int i = grid.size() - 1; i >= 0; i--) {
                    if (grid.get(i).get(j) == 1) {
                        col = j;
                        break outerloop;
                    }
                }
            }
            for (int j = grid.get(0).size() - 1; j > col; j--) {
                for (int i = grid.size() - 1; i >= 0; i--) {
                    gridCopy.get(i).remove(gridCopy.get(0).size() - 1);
                }
            }
            grid = gridCopy;
            row = gridCopy.size();
            column = gridCopy.size() != 0 ? gridCopy.get(0).size() : 0;
        }

        return this;
    }
}
