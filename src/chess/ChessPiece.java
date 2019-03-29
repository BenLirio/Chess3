package chess;

/**
 * This class is an abstract class that implements IChessPiece
 * This is the base functionality of the IChessPiece class.
 * @author Ben Lirio - template
 */
public abstract class ChessPiece implements IChessPiece {

    /** Defines which player owns this chess piece */
    private Player owner;

    /**
     * The constructor that takes in a player and sets it as the owner
     * @param player The owners player color.
     */
    protected ChessPiece(Player player) {
        this.owner = player;
    }

    /**
     * Abstract method that returns a string.
     * @return Type string of the type of piece it is.
     */
    public abstract String type();

    /**
     * Getter method for the owner
     * @return returns the owners color.
     */
    public Player player() {
        return owner;
    }

    /**
     * Checks to see if the selected move may be preformed by this current piece.
     * @param move  The move to be used.
     * @param board Board filled with IChessPieces.
     * @return Is this a valid move?
     */
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = true;
        if (((move.fromRow == move.toRow) && (move.fromColumn == move.toColumn)) == true) {
            valid = false;
        }
        if(board[move.toRow][move.toColumn] != null) {
            if (board[move.fromRow][move.fromColumn].player() == board[move.toRow][move.toColumn].player()) {
                valid = false;
            }
        }
        return valid;
    }
}