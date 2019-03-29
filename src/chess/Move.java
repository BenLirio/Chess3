package chess;

/**
 * Data for the current move being processed.
 * @author Ben Lirio - used Template
 */
public class Move {

    /** All of the instance variables that determine the start and finish of the piece. */
    public int fromRow, fromColumn, toRow, toColumn;

    /**
     * Empty function for move, base constructor.
     */
    public Move() {
        
    }

    /** Constructor that sets all of the instance variables to current parameters */
    public Move(int fromRow, int fromColumn, int toRow, int toColumn) {
        this.fromRow = fromRow;
        this.fromColumn = fromColumn;
        this.toRow = toRow;
        this.toColumn = toColumn;
    }

    /**
     * String method that outputs the move so it is easy to read.
     * @return Returns a very nice looking string.
     */
    @Override
    public String toString() {
        return "Move [fromRow=" + fromRow + ", fromColumn=" + fromColumn + ", toRow=" + toRow + ", toColumn=" + toColumn
                + "]";
    }


}