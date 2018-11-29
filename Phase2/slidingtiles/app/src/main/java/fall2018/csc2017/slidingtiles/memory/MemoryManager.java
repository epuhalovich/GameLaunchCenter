package fall2018.csc2017.slidingtiles.memory;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fall2018.csc2017.slidingtiles.GameManager;

public class MemoryManager implements GameManager, Serializable {
    private MemoryBoard board;
    private int flipCount;
    private int firstFlippedPosition;
    private int secondFlippedPosition;
    private int score;
    private int numMatches = 0;

    public MemoryManager(int rows, int cols) {
        List<Pairs> pairs = new ArrayList<>();
        int numTiles = rows * cols;
        int numPairs = numTiles / 2;
        for (int PairsId = 0; PairsId != numPairs; PairsId++) {
            pairs.add(new Pairs(PairsId));
            pairs.add(new Pairs(PairsId));
        }
        Collections.shuffle(pairs);
        this.board = new MemoryBoard(pairs, rows, cols);
        flipCount = 0;
    }

    public MemoryManager(MemoryBoard board){
        this.board = board;
        flipCount = 0;
    }

    public int getFirstFlippedPosition() {
        return firstFlippedPosition;
    }

    public int getSecondFlippedPosition() {
        return secondFlippedPosition;
    }

    public int getFlipCount() {
        return flipCount;
    }

    public static MemoryManager getLevel(String level){
        if(level.equals("Easy")){
            return new MemoryManager(2, 2);
        }
        else if(level.equals("Medium")){
            return new MemoryManager(4, 4);
        }
        else{
            return new MemoryManager(6, 6);
        }
    }

    public boolean isValidTap(int position) {
        return board.getPairs(position).getId() == 19 && flipCount < 3;
    }

    public void touchMove(int position) {
        board.flipCard(position);
        flipCount = flipCount + 1;
        if (flipCount == 1) {
            this.firstFlippedPosition = position;
        } else if(flipCount == 2) {
            this.secondFlippedPosition = position;
        }
        else{
            if (board.getPairs(firstFlippedPosition).getId() != board.getPairs(secondFlippedPosition).getId()) {
                board.unFlipCard(firstFlippedPosition);
                board.unFlipCard(secondFlippedPosition);
            }
            else { numMatches++; }
            flipCount = 0;
            score++;
            touchMove(position);
        }
    }

    public int getNumMatches() {
        return numMatches;
    }

    public boolean isGameOver(){
        Pairs[][] solution = board.getSolutionPairs();
        Pairs[][] puzzle = board.getPairsPuzzle();
        boolean over = true;
        for(int i = 0; i<board.getPairsPuzzle().length; i++){
            for(int j = 0; j<board.getPairsPuzzle()[0].length; j++)
            if(solution[i][j] != puzzle[i][j]){
                over = false;
            }
        }
        return over;
    }

    public int getScore(){
        return this.score;
    }

    public MemoryBoard getMemoryBoard(){return this.board;}

}




