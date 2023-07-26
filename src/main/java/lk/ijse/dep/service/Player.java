package lk.ijse.dep.service;

import java.io.Serializable;

public abstract class Player {
    protected Board board;

    public Player(){}

    public Player(Board board){
        this.board = board;
    }

    public abstract void movePiece(int col);

}
