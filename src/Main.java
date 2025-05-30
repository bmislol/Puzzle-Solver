import puzzles.Sodoku;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter which grid size (0/1): ");
        int whichGrid = scanner.nextInt();

        Sodoku sodoku = new Sodoku(whichGrid);

        while(true){
            System.out.print("Enter block, case, number: ");
            int block = scanner.nextInt();
            int caseNb = scanner.nextInt();
            int number = scanner.nextInt();

            sodoku.insertNumber(block, caseNb, number);
        }

    }
}