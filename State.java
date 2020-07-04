package minesweeper;

public  enum State {
    S("."),
    M("."),
    P("*");

    private String value;
    State(String value) {
        this.value= value;
    }

    @Override
    public String toString() {
        return value;
    }
}