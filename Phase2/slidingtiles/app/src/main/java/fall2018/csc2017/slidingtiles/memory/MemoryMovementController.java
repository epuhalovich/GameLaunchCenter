package fall2018.csc2017.slidingtiles.memory;

import android.content.Context;
import android.widget.Toast;

import fall2018.csc2017.slidingtiles.GameManager;
import fall2018.csc2017.slidingtiles.MovementController;


public class MemoryMovementController implements MovementController {

    private MemoryManager memoryManager = null;

    public MemoryMovementController() {
    }

    public void setGameManager(GameManager memoryManager) {
        this.memoryManager = (MemoryManager) memoryManager;
    }

    public void processTapMovement(Context context, int position, boolean display) {
        if (memoryManager.isValidTap(position)) {
            int numMatches = memoryManager.getNumMatches();
            memoryManager.touchMove(position);
            if (memoryManager.getNumMatches() > numMatches) {
                Toast.makeText(context, "You got a match!", Toast.LENGTH_SHORT).show();
            }

            if (memoryManager.isGameOver()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}
