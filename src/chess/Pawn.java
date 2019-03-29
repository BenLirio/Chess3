package chess;
/**
 * Pawn extends the current Chess piece and can move 1 forward, attack diagonal, or 2 of the bat forward.
 * Pawn is weak.
 * @author Ben Lirio - used template
 */
public class Pawn extends ChessPiece {

    /**
     * Base constructor for this piece. Sets the owner player.
     * @param player Player is the owner of this piece.
     */
    public Pawn(Player player) {
        super(player);
    }

    /**
     * Setter method for the type of piece this is.
     * Returns The type of piece.
     * @return Returns Pawn as a string.
     */
    public String type() {
        return "Pawn";
    }

    /**
     * Checks to see if the pawn can move in the ways specified above. Forward/diagonal/or double forward.
     * @param move  The move to be used.
     * @param board Board filled with IChessPieces.
     * @return Returns true if the current move is valid.
     */
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