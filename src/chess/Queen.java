package chess;
/**
 * Queen extends the current Chess piece and can move like rook and bishop.
 * Queen is the most powerful piece.
 * @author Ben Lirio - used template
 */
public class Queen extends ChessPiece {

    /**
     * Base constructor for the Queen. Also sets the owner player.
     * @param player This parameter determines the owner of the piece.
     */
    public Queen(Player player) {
        super(player);

    }

    /**
     * This parameter returns the type of piece it is.
     * @return Returns Queen as a string.
     */
    public String type() {
        return "Queen";

    }

    /**
     * Check to see if the current move is valid using the rook and the bishop already created.
     * @param move  The move to be used.
     * @param board Board filled with IChessPieces.
     * @return Returns true if the move can be preformed by either the bishop or rook already.
     */
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        if(!super.isValidMove(move,board)){
            return false;
        }
        Bishop move1 = new Bishop(this.player());
        Rook move2 = new Rook(this.player());
        return (move1.isValidMove(move, board) || move2.isValidMove(move, board));
    }
}