package minesweeper;


import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.FileHandler;

public class Main {
    public static  State[][] board;
    public static  int size = 9;

    public static void main(String[] args) {
        System.out.print("How many mines do you want on the field? ");
        Scanner scanner = new Scanner(System.in);
        int countMine = Integer.parseInt(scanner.nextLine());

        Game game = new Game(countMine);
        System.out.println(game.showField());
        boolean win = true;
        boolean start= true;

        do{
            System.out.println("Set/unset mines marks or claim a cell as free: ");
           String[] line = scanner.nextLine().split(" ");
           int  col = Integer.parseInt(line[0])-1;
            int  row = Integer.parseInt(line[1])-1;

             if (start) {
                 game.setField(Point.of(row, col));
                 start = false;
             }

            String type = line[2];
            game.play(col,row,type);

            System.out.println(game.showField());
            if (game.isGameOver()){
                System.out.println("You stepped on a mine and failed!");
                win = false;
                break;
            }
        }while(game.active());
        if(win)
            System.out.println("Congratulations! You found all mines!");
    }
}
