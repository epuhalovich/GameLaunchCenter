package fall2018.csc2017.slidingtiles.slidingtiles;

import android.content.Context;
import android.widget.Toast;

import fall2018.csc2017.slidingtiles.GameManager;


public class MovementController {

    private GameManager gameManager = null;

    public MovementController() {
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void processTapMovement(Context context, int position, boolean display) {
        if (gameManager.isValidTap(position)) {
            gameManager.touchMove(position);
            if (gameManager.isGameOver()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}
