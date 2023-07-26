package lk.ijse.connect4game.service;

public interface BoardUI {
    public void update(int col,boolean isHuman);
    public void notifyWinner(Winner winner);
}
