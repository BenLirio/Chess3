package chess;

public class Pawn extends ChessPiece {

    public Pawn(Player player) {
        super(player);
    }

    public String type() {
        return "Pawn";
    }

    // determines if the move is valid for a pawn piece
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        if(!super.isValidMove(move,board)){
            return false;
        }
        boolean valid = false;
        if(board[move.fromRow][move.fromColumn].player() == Player.WHITE) {
            if(move.fromRow == 6) {
                if(move.toRow == move.fromRow - 2 && board[move.toRow][move.toColumn] == null) {
                    if(board[move.toRow + 1][move.toColumn] == null) {
                        if (move.toColumn == move.fromColumn) {
                            valid = true;
                        }
                    }
                }
            }
            if(move.toRow == move.fromRow - 1 && board[move.toRow][move.toColumn] == null) {
                if(move.toColumn == move.fromColumn) {
                    valid = true;
                }
            } else if(move.toRow == move.fromRow - 1 && Math.abs(move.fromColumn - move.toColumn) == 1 && board[move.toRow][move.toColumn] != null) {
                valid = true;
            }
        } else {
            if(move.fromRow == 1) {
                if(move.toRow == move.fromRow + 2 && board[move.toRow][move.toColumn] == null) {
                    if(board[move.toRow - 1][move.toColumn] == null) {
                        if (move.toColumn == move.fromColumn) {
                            valid = true;
                        }
                    }
                }
            }
            if(move.toRow == move.fromRow + 1 && board[move.toRow][move.toColumn] == null) {
                if(move.toColumn == move.fromColumn) {
                    valid = true;
                }
            } else if(move.toRow == move.fromRow + 1 && Math.abs(move.fromColumn - move.toColumn) == 1 && board[move.toRow][move.toColumn] != null) {
                valid = true;
            }
        }
        return valid;
    }
}