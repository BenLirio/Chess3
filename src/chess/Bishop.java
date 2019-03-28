package chess;

public class Bishop extends ChessPiece {

    public Bishop(Player player) {
        super(player);
    }

    public String type() {
        return "Bishop";
    }

    public boolean isValidMove(Move move, IChessPiece[][] board) {
        if(!super.isValidMove(move,board)){
            return false;
        }
        boolean valid = false;

        if(Math.abs(move.fromRow - move.toRow) == Math.abs(move.toColumn - move.fromColumn)) {
            if(move.toRow - move.fromRow > 0) {
                if(move.toColumn - move.fromColumn > 0) {
                    //ROW+ COL+
                    for(int i = 1; i < move.toRow - move.fromRow; i++) {
                        if(board[move.fromRow + i][move.fromColumn + i] != null) {
                            return false;
                        }
                    }
                } else {
                    //ROW+ COL-
                    for(int i = 1; i < move.toRow - move.fromRow; i++) {
                        if(board[move.fromRow + i][move.fromColumn - i] != null) {
                            return false;
                        }
                    }
                }
            } else {
                if(move.toColumn - move.fromColumn > 0) {
                    //ROW- COL+
                    for(int i = 1; i < move.fromRow - move.toRow; i++) {
                        if(board[move.fromRow - i][move.fromColumn + i] != null) {
                            return false;
                        }
                    }
                } else {
                    //ROW- COL-
                    for(int i = 1; i < move.fromRow - move.toRow; i++) {
                        if(board[move.fromRow - i][move.fromColumn - i] != null) {
                            return false;
                        }
                    }
                }
            }
            valid = true;
        }

        return valid;

    }
}