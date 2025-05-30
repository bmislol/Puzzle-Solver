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
    }

    // check for the sodoku rule to make sure you didn't lose
    public boolean checkSodokuRule(){
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
                    System.out.println("current i: " + i + " j: " + j + " k: " + k + ", current number: " + currentNb + ", check number: " + checkNb);
                    if(currentNb == checkNb){
                        return true;
                    }
                }
            }
        }

        return false;
    }

}
