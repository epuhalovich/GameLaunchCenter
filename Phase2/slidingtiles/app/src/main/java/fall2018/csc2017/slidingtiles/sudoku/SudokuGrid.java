package fall2018.csc2017.slidingtiles.sudoku;

import java.io.Serializable;

import fall2018.csc2017.slidingtiles.R;

public class SudokuGrid implements Serializable {
    private int background;
    private int id;
    private String number;

    public SudokuGrid(int id, int background, String number){
        this.id = id;
        this.background = background;
        this.number = number;
    }

    public int getBackground() {
        return background;
    }

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setBackground(int background){
        this.background = background;
    }

    public void setNumber(String number){
        this.number = number;
    }

}
