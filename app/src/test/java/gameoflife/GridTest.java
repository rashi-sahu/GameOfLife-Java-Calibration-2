package gameoflife;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class GridTest {

    @Test
    public void shouldReturnCompressedNextGenerationGrid() {
        ArrayList<ArrayList<Integer>> inputArray = new ArrayList<>();
        inputArray.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0)));
        inputArray.add(new ArrayList<>(Arrays.asList(0, 0, 1, 0, 0)));
        inputArray.add(new ArrayList<>(Arrays.asList(0, 0, 1, 0, 0)));
        inputArray.add(new ArrayList<>(Arrays.asList(0, 0, 1, 0, 0)));
        inputArray.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0)));

        ArrayList<ArrayList<Integer>> expectedArray = new ArrayList<>();
        expectedArray.add(new ArrayList<>(Arrays.asList(1, 1, 1)));

        Grid expectedGrid = new Grid(expectedArray);

        Grid outputGrid = new Grid(inputArray);
        outputGrid.nextGeneration();

        assertEquals(expectedGrid, outputGrid);
    }

    @Test
    public void shouldReturnEmptyNextGenerationForEmptyInputGrid() {
        ArrayList<ArrayList<Integer>> inputArray = new ArrayList<>();

        ArrayList<ArrayList<Integer>> expectedArray = new ArrayList<>();

        Grid expectedGrid = new Grid(expectedArray);

        Grid outputGrid = new Grid(inputArray);
        outputGrid.nextGeneration();

        assertEquals(expectedGrid, outputGrid);
    }

    @Test
    public void shouldReturnNextGenerationGrid() {
        ArrayList<ArrayList<Integer>> inputArray = new ArrayList<>();
        inputArray.add(new ArrayList<>(Arrays.asList(1, 0, 0, 0, 1)));
        inputArray.add(new ArrayList<>(Arrays.asList(0, 0, 1, 0, 0)));
        inputArray.add(new ArrayList<>(Arrays.asList(0, 1, 1, 0, 0)));
        inputArray.add(new ArrayList<>(Arrays.asList(0, 1, 1, 0, 0)));

        ArrayList<ArrayList<Integer>> expectedArray = new ArrayList<>();
        expectedArray.add(new ArrayList<>(Arrays.asList(0, 1, 1)));
        expectedArray.add(new ArrayList<>(Arrays.asList(0, 0, 1)));
        expectedArray.add(new ArrayList<>(Arrays.asList(1, 1, 0)));

        Grid expectedGrid = new Grid(expectedArray);

        Grid outputGrid = new Grid(inputArray);
        outputGrid.nextGeneration();

        assertEquals(expectedGrid, outputGrid);
    }
}
