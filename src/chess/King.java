package chess;

public class King extends ChessPiece {

    public King(Player player) {
        super(player);
    }

    public String type() {
        return "King";
    }

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