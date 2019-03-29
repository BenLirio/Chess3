package chess;
/**
 * Rook extends the current Chess piece and can move to any spot in a strait line
 * Rook is a powerful piece.
 * @author Ben Lirio - used template
 */
public class Rook extends ChessPiece {

    /**
     * Base constructor of the rook.
     * Sets the current owner player as well.
     * @param player This determines which player is the owner.
     */
    public Rook(Player player) {

        super(player);

    }

    /**
     * Getter method for the type of piece this is.
     * Returns the type of piece.
     * @return Returns Rook as a string.
     */
    public String type() {

        return "Rook";

    }

    /**
     * This function check to see if the current move is in a strait line.
     * @param move  The move to be used.
     * @param board Board filled with IChessPieces.
     * @return Returns if the current move is valid.
     */
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
        return valid;

    }

}