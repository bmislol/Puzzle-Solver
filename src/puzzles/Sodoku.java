package puzzles;

import generate.SodokuGenerator;
import solve.SodokuSolver;

import java.util.Arrays;

public class Sodoku {
    // grid that could be 2x2 or 3x3
    int[][] playGrid;

    // 0 for 2x2 grids, 1 for 3x3 grids
    int whichGrid;

    SodokuSolver solver;
    SodokuGenerator generator;

    public Sodoku(int whichGrid) {
        solver = new SodokuSolver();
        generator = new SodokuGenerator();

        this.whichGrid = whichGrid;

        if(whichGrid == 0){
            playGrid = new int[4][4];
            for (int[] ints : playGrid) {
                Arrays.fill(ints, 0);
            }
        }

        else{
            playGrid = new int[9][9];
            for (int[] ints : playGrid) {
                Arrays.fill(ints, 0);
            }
        }

        printGrid();
    }

    // check for the sodoku rule to make sure you didn't lose
    private boolean checkSodokuRule(){
        // check for each case (1 array block) has no repeating number
        int currentNb;
        int checkNb;

        int allGrids = whichGrid == 0 ? 4 : 9;
        int caseSize = whichGrid == 0 ? 4 : 9;

        for(int i = 0; i < allGrids; i++){
            for(int j = 0; j < caseSize; j++){
                currentNb = playGrid[i][j];
                for(int k = 0; k < caseSize; k++){
                    if(k == j && k != caseSize - 1){
                        k++;
                    }
                    checkNb = playGrid[i][k];
                    System.out.println("current i: " + i + ", j: " + j + ", k: " + k + ", current number: " + currentNb + ", check number: " + checkNb);
                    if(currentNb == checkNb && currentNb != 0 && k != j){
                        return true;
                    }
                }
            }
        }

        // check each row for no repeating numbers
        int rowNb = whichGrid == 0 ? 2 : 3; // Number of rows in a block
        int rowSize = whichGrid == 0 ? 2 : 3; // Number of elements in a row
        int gridSize = whichGrid == 0 ? 4 : 9; // Total grid size

        for (int r = 0; r < gridSize; r += rowNb) { // Iterate over row groups
            for (int c = 0; c < gridSize; c += rowSize) { // Iterate over column groups
                for (int i = 0; i < rowNb; i++) { // Iterate within each row
                    boolean[] seen = new boolean[gridSize + 1]; // Track numbers seen in the row
                    for (int j = 0; j < rowSize; j++) { // Iterate within the row's columns
                        int num = playGrid[r + i][c + j];
                        if (num != 0) {
                            if (seen[num]) {
                                return true; // Duplicate found in the row
                            }
                            seen[num] = true;
                        }
                    }
                }
            }
        }

        // Check each column for no repeating numbers
        for (int c = 0; c < gridSize; c++) { // Iterate over columns
            for (int r = 0; r < gridSize; r += rowNb) { // Iterate over row groups
                boolean[] seen = new boolean[gridSize + 1]; // Track numbers seen in the column
                for (int i = 0; i < rowNb; i++) { // Iterate within the group rows
                    int num = playGrid[r + i][c];
                    if (num != 0) {
                        if (seen[num]) {
                            return true; // Duplicate found in the column
                        }
                        seen[num] = true;
                    }
                }
            }
        }

        // Check each 3x3 box for no repeating numbers (for 9x9 grids)
        if (whichGrid == 1) { // Only applies to 9x9 boards
            for (int boxRow = 0; boxRow < gridSize; boxRow += rowNb) { // Iterate by box row
                for (int boxCol = 0; boxCol < gridSize; boxCol += rowSize) { // Iterate by box column
                    boolean[] seen = new boolean[gridSize + 1]; // Track numbers seen in the box
                    for (int i = 0; i < rowNb; i++) { // Iterate within the box rows
                        for (int j = 0; j < rowSize; j++) { // Iterate within the box columns
                            int num = playGrid[boxRow + i][boxCol + j];
                            if (num != 0) {
                                if (seen[num]) {
                                    return true; // Duplicate found in the box
                                }
                                seen[num] = true;
                            }
                        }
                    }
                }
            }
        }


        // return false if no repeating numbers found
        return false;
    }

    private void printGrid(){
        for(int[] ints : playGrid){
            for(int anInt : ints){
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    public void insertNumber(int row, int col, int number){
        playGrid[row][col] = number;

        if(!checkSodokuRule()){
            System.out.println("Solved!");
        }
        else{
            System.out.println("You Lose!");
        }

        printGrid();
    }

}
