package minesweeper;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Game {

    private final Field player;
    private final int mineCount;
    private Field field;
    private int detectMine;
    private boolean gameOver;
    private int countExplore;
    private int  step;

    public Game(int mineCount) {
        //field = new Field(9,mineCount);
        player = new Field(9);
        detectMine = 0;
        this.mineCount = mineCount;
        countExplore= 0;
        step = 0;
    }

    public void setField( Point positionUser){
        field = new Field(9,mineCount,positionUser);
    }

    public String showField() {
        String r =" |123456789|\n-|---------|\n";
        for (int row = 0 ; row < 9 ; row++){
            r+= (row+1)+"|";
            for(int col = 0 ; col< 9; col++){
                Cell cell = player.getCell(row,col) ;

                r += cell.isExplored()  && field.getCell(row,col).getCount()>0 ?   field.getCell(row,col).getCount() : cell.getCurrent() ;
            }
            r += "|"+System.lineSeparator();
        }
        r += "-|---------|";
       return r;
        }




    public boolean active() {
        return !exploreAll() && !field.findAll() && detectMine != mineCount;
    }

    private  boolean exploreAll(){
        return countExplore == 81 - mineCount;
    }

    public void play(int col, int row, String type) {
        switch(type){
            case "free":
                if (field.isMine(row,col)){
                    gameOver=true;
                }
                else {
                    //System.out.println(row+"+++++++++++++++"+col);

                    player.play(row,col);
                    field.play(row,col);
                    explore(row,col);

                }

                break;
            case "mine":
                field.play(row,col);
                player.play(row,col);
                break;
        }
    }


    private void explore(int row, int col) {
        Point  current = Point.of(row,col);
        Deque<Point> deque = new LinkedList<>();
        deque.addLast(current);

        while(deque.size() >0)
        {

            current = deque.pollFirst();

            if(field.getCell(current.row,current.col).getState() != State.S){
                continue;
            }

            player.getCell(current.row, current.col).explored();

            countExplore++;

            if (field.getCell(current.row,current.col).getCount() > 0)
                continue;

            List<Point> points = field.neighboor(current);
            for(Point p : points){

                 if(player.getCell(p.row,p.col).isNotExplored()) {

                    deque.addLast(Point.of(p.row, p.col));
                 }

            }
        }

    }

    public boolean isGameOver() {
        return gameOver;
    }



}
