package lk.ijse.connect4game.service;

public class HumanPlayer extends Player{
    public HumanPlayer(Board board){super(board);}

    public void movePiece(int col){
        if (this.board.isLegalMove(col)) {
            this.board.updateMove(col, Piece.BLUE);
            this.board.getBoardUI().update(col, true);
            Winner winner = this.board.findWinner();
            if (winner.getWinningPiece() != Piece.EMPTY) {
                this.board.getBoardUI().notifyWinner(winner);
            } else if (!this.board.existLegalMoves()) {
                this.board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
            }
        }
    }
}
