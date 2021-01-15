package gameoflife;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputReader {

    public ArrayList<ArrayList<Integer>> readInputFromFile() {
        Scanner s;
        try {
            s = new Scanner(new File("/home/rashi/Game-Of-Life/app/src/main/resources/Input.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }

        ArrayList<String> grid = new ArrayList<>();
        while (s.hasNextLine()) {
            grid.add(s.nextLine());
        }

        s.close();

        ArrayList<ArrayList<Integer>> outputGrid = convertFileInputTo2DIntegerArrayList(grid);

        return outputGrid;
    }

    private ArrayList<ArrayList<Integer>> convertFileInputTo2DIntegerArrayList(ArrayList<String> grid) {
        ArrayList<ArrayList<Integer>> outputGrid = new ArrayList<>();
        for (int i = 0; i < grid.size(); i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < grid.get(i).length(); j++) {
                row.add(Character.getNumericValue(grid.get(i).charAt(j)));
            }
            outputGrid.add(row);
        }

        return outputGrid;
    }
}
