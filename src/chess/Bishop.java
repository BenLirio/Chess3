package chess;

/**
 * Bishop extends a chess piece.
 * Moves Diagonally.
 * Starts in the back row.
 * @author Ben Lirio - used template
 */
public class Bishop extends ChessPiece {
    /**
     * Base constructor for the class takes in a player variable.
     * @param player Which player owns this piece.
     */
    public Bishop(Player player) {
        super(player);
    }

    /**
     * Getter method for the type of the piece this is.
     * @return Return the name of the type of piece.
     */
    public String type() {
        return "Bishop";
    }

    /**
     * Check to see if the move is diagonal.
     * @param move Requested move.
     * @param board Current position of pieces
     * @return Is this a valid move?
     */
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