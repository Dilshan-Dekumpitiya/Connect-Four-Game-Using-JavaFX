package lk.ijse.dep.service;

public class BoardImpl implements Board{
    private Piece[][] pieces = new Piece[NUM_OF_COLS][NUM_OF_ROWS];
    private BoardUI boardUI;

    public BoardImpl(BoardUI boardUI){
        for (int i=0;i<NUM_OF_COLS;i++){
            for (int j=0;j<NUM_OF_ROWS;j++){
                this.pieces[i][j]=Piece.EMPTY;
            }
        }
        this.boardUI=boardUI;
    }

    public void setPieces(Piece[][] pieces) {
        this.pieces = pieces;
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        for(int i = 0; i < 5; i++) {
            if (this.pieces[col][i] == Piece.EMPTY) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isLegalMove(int col) {

        return (findNextAvailableSpot(col) != -1);
    }

    @Override
    public boolean existLegalMoves() {
        for(int col = 0; col < 6; col++) {
            if (isLegalMove(col)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {
        this.pieces[col][findNextAvailableSpot(col)] = move;
    }

    public void updateMove(int col, int row, Piece move) {

        this.pieces[col][row] = move;
    }

    @Override
    public Winner findWinner() {
        for (int col = 0; col < 6; col++) {
            int count = 1;
            int rows = findNextAvailableSpot(col);
            for (int i = 1; i < ((rows == -1) ? 5 : rows); i++) {
                if (this.pieces[col][i] == this.pieces[col][i - 1]) {
                    count++;
                    if (count == 4) {
                        return new Winner(this.pieces[col][i], col, i - 3, col, i);
                    }
                } else {
                    count = 1;
                    if (i >= 2) {
                        break;
                    }
                }
            }
        }

        for (int row = 0; row < 5; row++) {
            int count = 1;
            for (int i = 1; i < 6; i++) {
                if (this.pieces[i][row] == this.pieces[i - 1][row] && this.pieces[i][row] != Piece.EMPTY) {
                    count++;
                    if (count == 4) {
                        return new Winner(this.pieces[i][row], i - 3, row, i, row);
                    }
                } else {
                    count = 1;
                    if (i >= 3) {
                        break;
                    }
                }
            }
        }
        return new Winner(Piece.EMPTY);
    }

    public void setBoardUI(BoardUI boardUI) {
        this.boardUI = boardUI;
    }

    public BoardUI getBoardUI() {
        return this.boardUI;
    }
}
