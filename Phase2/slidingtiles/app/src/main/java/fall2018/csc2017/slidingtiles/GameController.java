package fall2018.csc2017.slidingtiles;

public interface GameController {
    public GameManager getGameManager();
    public void setGameManager(GameManager manager);
    public boolean checkToAddScore(Scoreboard scoreboard, String user);
    public void setUpBoard(String level);
}
