package fall2018.csc2017.slidingtiles;

import android.content.Context;

public interface MovementController {
    public void processTapMovement(Context context, int position, boolean display);
    public void setGameManager(GameManager manager);
}
