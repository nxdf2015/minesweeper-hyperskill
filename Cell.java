package minesweeper;

public class Cell {
    private int count;
    private State state;
    private State current;

    public static Cell of(int i, State s) {
        return new Cell(0,s);
    }

    public Cell(int count, State state) {
        this.count = count;
        this.state = state;
        this.current = state;
    }

    public int getCount() {
        return count;
    }

    public State getState() {
        return state;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setState(State state) {
        this.state = state;
        current=state;
    }


    public void play() {

        if (current == State.P && state == State.S){
            current = state;
        }
        else {
            current = State.P;
        }
    }

    public boolean isMine() {
        return state == State.M;
    }

    public State getCurrent() {
        return current;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "count=" + count +
                ", state=" + state +
                ", current=" + current +
                '}';
    }
}
