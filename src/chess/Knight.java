package chess;

public class Knight extends ChessPiece {

    public Knight(Player player) {
        super(player);
    }

    public String type() {
        return "Knight";
    }

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