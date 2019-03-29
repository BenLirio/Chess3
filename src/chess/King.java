package chess;

/**
 * King extends the current Chess piece and can move to any adjecent spot.
 * If the king is lost the game is over.
 * @author Ben Lirio - used template
 */
public class King extends ChessPiece {

    /**
     * Base constructor that inputs the owner of this piece.
     * @param player Input the owner of this piece.
     */
    public King(Player player) {
        super(player);
    }

    /**
     * Getter method for the type of piece this is.
     * This piece is a king piece.
     * @return Returns the String King
     */
    public String type() {
        return "King";
    }

    /**
     * Checks to see if the current position is adjacent the the position that is wished to be moved to.
     * @param move  The move to be used.
     * @param board Board filled with IChessPieces.
     * @return Returns true if the current move is possible.
     */
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        if(!super.isValidMove(move,board)){
            return false;
        }
        boolean valid = false;
        if(Math.abs(move.fromRow - move.toRow) <= 1 && Math.abs(move.fromColumn - move.toColumn) <= 1) {
            valid = true;
        }
        // More code is needed
        return valid;
    }
}