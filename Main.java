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
        int countMine = scanner.nextInt();
        Field field = new Field(9 , countMine);
        System.out.println(field);

        while(!field.findAll()) {

            System.out.println("Set/delete mines marks (x and y coordinates): ");
            int col = scanner.nextInt() - 1;
            int row = scanner.nextInt() -1 ;

            if (!field.isNumber(row,col)){
                field.play(row,col);
                System.out.println(field);
            }
            else {
                System.out.println("There is a number here!");
            }
        }
        System.out.println("Congratulations! You found all mines!");
    }
}
