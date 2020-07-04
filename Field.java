package minesweeper;


import java.util.Arrays;
import java.util.Random;

public class Field {
    private int size ;
    private Cell[][] field;
    private int find;

    public Field(int size,int numberMine) {
        this.size = size;
        getField(numberMine);
        find = numberMine;
    }

    private void getField(int number ){
        generateField();
        generateMine(number);
        computeNeigboors();
    }

    private void generateField(){
        field = new Cell[size][size];
        for(int i = 0;i <size ; i++){
            field[i] = getRow();
        }

    }

    private void generateMine(int numberMine){
        Random random = new Random();
        while(numberMine> 0){
            int row = random.nextInt(size);
            int col = random.nextInt(size);

            if (field[row][col].getState() == State.S){
                field[row][col].setState(State.M);
                numberMine--;
            }
        }
    }

    private Cell[]  getRow(){
        Cell[]  row = new Cell[size];
        for(int i = 0 ; i < size ; i++){
            row[i] = Cell.of(0, State.S);
        }
        return Arrays.copyOf(row,size);
    }

    private void  computeNeigboors(){

        for (int row = 0 ; row < size ;row++){
            for (int col = 0 ; col <size ; col++){
                if (field[row][col].getState() != State.M)
                    field[row][col].setCount(getNeighboor(row,col));
            }
        }
    }
    private    boolean isInValid(int x, int d){
        return d  + x == size || d + x <0;
    }
    private  int getNeighboor(int row, int col) {
        int[] delta = {-1,0,1};
        int count = 0;
        for(int dy :delta){
            for(int dx : delta){
                if (dy == 0 && dx == 0 || isInValid(col,dx) || isInValid(row,dy))
                    continue;

                count   += field[dy + row][dx + col ].getState() == State.M ? 1 : 0;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        String r =" |123456789|\n-|---------|\n";
        for (int row = 0 ; row < size ; row++){
            r+= (row+1)+"|";
            for(int col = 0 ; col< size; col++){
                Cell cell = field[row][col];
                r += cell.getCount() == 0 ? cell.getCurrent() : cell.getCount();
            }
            r += "|"+System.lineSeparator();
        }
        r += "-|---------|";
        return r;
    }

    public boolean isNumber(int row, int col) {
        return field[row][col].getCount() > 0;
    }


    public void play(int row, int col) {
        System.out.println(field[row][col]);
        field[row][col].play();
        if (field[row][col].isMine()){
            find--;
        }
    }


    public boolean findAll() {
        return find == 0;
    }
}
