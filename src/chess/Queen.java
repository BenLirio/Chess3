package chess;

public class Queen extends ChessPiece {

    public Queen(Player player) {
        super(player);

    }

    public String type() {
        return "Queen";

    }

    public boolean isValidMove(Move move, IChessPiece[][] board) {
        if(!super.isValidMove(move,board)){
            return false;
        }
        Bishop move1 = new Bishop(this.player());
        Rook move2 = new Rook(this.player());
        return (move1.isValidMove(move, board) || move2.isValidMove(move, board));
    }
}