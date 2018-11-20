package fall2018.csc2017.slidingtiles.slidingtiles;

import android.content.Context;
import android.widget.Toast;

import fall2018.csc2017.slidingtiles.slidingtiles.SlidingTilesManager;


public class MovementController {

    private SlidingTilesManager slidingTilesManager = null;

    public MovementController() {
    }

    public void setSlidingTilesManager(SlidingTilesManager slidingTilesManager) {
        this.slidingTilesManager = slidingTilesManager;
    }

    public void processTapMovement(Context context, int position, boolean display) {
        if (slidingTilesManager.isValidTap(position)) {
            slidingTilesManager.touchMove(position);
            if (slidingTilesManager.isGameOver()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}
