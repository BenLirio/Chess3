package chess;
/**
 * Knight extends the current Chess piece and can move to any spot within an L shape.
 * The Knight is kind of powerful.
 * @author Ben Lirio - used template
 */
public class Knight extends ChessPiece {

    /**
     * Base constructor that takes in the owner of this piece.
     * @param player The owner of this piece.
     */
    public Knight(Player player) {
        super(player);
    }

    /**
     * Setter method that returns the type of piece this is.
     * @return Output Knight as a String.
     */
    public String type() {
        return "Knight";
    }

    /**
     * Checks to see if the move is in an L shape.
     * @param move  The move to be used.
     * @param board Board filled with IChessPieces.
     * @return True if the move is possible
     */
    public boolean isValidMove(Move move, IChessPiece[][] board){
        if(!super.isValidMove(move,board)){
            return false;
        }
        boolean valid = false;
        if(Math.abs(Math.abs(move.toColumn - move.fromColumn) - Math.abs(move.fromRow - move.toRow)) == 1 && Math.abs(Math.abs(move.toColumn - move.fromColumn) + Math.abs(move.fromRow - move.toRow)) == 3) {
            valid = true;
        }
        return valid;

    }

}