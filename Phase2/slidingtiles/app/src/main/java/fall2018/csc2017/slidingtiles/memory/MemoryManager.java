package fall2018.csc2017.slidingtiles.memory;

import java.util.Observable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import fall2018.csc2017.slidingtiles.GameManager;

public class MemoryManager extends Observable implements GameManager {
    private MemoryBoard2 board;
    private int flipCount;
    private int lastFlippedPosition;
    private int score;


    public MemoryManager(int rows, int cols) {
        List<Pairs> pairs = new ArrayList<>();
        int numTiles = rows * cols;
        int numPairs = numTiles / 2;
        for (int PairsId = 0; PairsId != numPairs; PairsId++) {
            pairs.add(new Pairs(PairsId));
            pairs.add(new Pairs(PairsId));
        }
        Collections.shuffle(pairs);
        this.board = new MemoryBoard2(pairs, rows, cols);
    }

    public boolean isValidTap(int position) {
        return board.getPairs(position).getId() == 11 && flipCount < 2;
    }

    public void touchMove(int position) {
        board.flipCard(position);
        flipCount = flipCount + 1;
        if (flipCount == 1) {
            this.lastFlippedPosition = position;
        } else {
            if (board.getPairs(position).getId() != board.getPairs(lastFlippedPosition).getId()) {
                board.unFlipCard(position);
                board.unFlipCard(lastFlippedPosition);
            }
            score++;
            flipCount = 0;
        }
        setChanged();
        notifyObservers();
    }

    public boolean isGameOver(){
        return board.getPairsPuzzle() == board.getSolutionPairs();
    }

    public int getScore(){
        return this.score;
    }

}




