package chess;

public class Rook extends ChessPiece {

    public Rook(Player player) {

        super(player);

    }

    public String type() {

        return "Rook";

    }
    // determines if the move is valid for a rook piece
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        if(!super.isValidMove(move,board)){
            return false;
        }
        boolean valid = false;
        if(move.fromRow == move.toRow || move.toColumn == move.fromColumn) {
            if(move.fromRow  > move.toRow) {
                for(int i = 1; i < move.fromRow - move.toRow; i++) {
                    if(board[move.fromRow - i][move.fromColumn] != null) {
                        return false;
                    }
                }
            } else if(move.fromRow  < move.toRow) {
                for(int i = 1; i < move.toRow - move.fromRow; i++) {
                    if(board[move.fromRow + i][move.fromColumn] != null) {
                        return false;
                    }
                }
            } else if(move.fromColumn  > move.toColumn) {
                for(int i = 1; i < move.fromColumn - move.toColumn; i++) {
                    if(board[move.fromRow][move.fromColumn - i] != null) {
                        return false;
                    }
                }
            } else if(move.fromColumn  < move.toColumn) {
                for(int i = 1; i < move.toColumn - move.fromColumn; i++) {
                    if(board[move.fromRow][move.fromColumn + i] != null) {
                        return false;
                    }
                }
            }
            valid = true;
        }
        // More code is needed
        return valid;

    }

}