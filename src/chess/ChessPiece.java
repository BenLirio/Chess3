package chess;

public abstract class ChessPiece implements IChessPiece {

    private Player owner;

    protected ChessPiece(Player player) {
        this.owner = player;
    }

    public abstract String type();

    public Player player() {
        return owner;
    }
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = true;
        if(this != board[move.fromRow][move.fromColumn]) {
            valid = false;
        }
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