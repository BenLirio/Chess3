package chess;

public class ChessModel implements IChessModel {
    private IChessPiece[][] board;
    private Player player;

    public ChessModel(ChessModel c) {
        board = new IChessPiece[8][8];
        player = c.player;
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                if(c.board[x][y] != null) {
                    this.board[x][y] = c.board[x][y];
                }
            }
        }
    }
    // declare other instance variables as needed

    public ChessModel() {
        board = new IChessPiece[8][8];
        player = Player.WHITE;

        board[7][0] = new Rook(Player.WHITE);
        board[7][1] = new Knight(Player.WHITE);
        board[7][2] = new Bishop(Player.WHITE);
        board[7][3] = new Queen(Player.WHITE);
        board[7][4] = new King(Player.WHITE);
        board[7][5] = new Bishop(Player.WHITE);
        board[7][6] = new Knight (Player.WHITE);
        board[7][7] = new Rook(Player.WHITE);
        board[6][0] = new Pawn(Player.WHITE);
        board[6][1] = new Pawn(Player.WHITE);
        board[6][2] = new Pawn(Player.WHITE);
        board[6][3] = new Pawn(Player.WHITE);
        board[6][4] = new Pawn(Player.WHITE);
        board[6][5] = new Pawn(Player.WHITE);
        board[6][6] = new Pawn (Player.WHITE);
        board[6][7] = new Pawn(Player.WHITE);

        board[0][0] = new Rook(Player.BLACK);
        board[0][1] = new Knight(Player.BLACK);
        board[0][2] = new Bishop(Player.BLACK);
        board[0][3] = new Queen(Player.BLACK);
        board[0][4] = new King(Player.BLACK);
        board[0][5] = new Bishop(Player.BLACK);
        board[0][6] = new Knight (Player.BLACK);
        board[0][7] = new Rook(Player.BLACK);
        board[1][0] = new Pawn(Player.BLACK);
        board[1][1] = new Pawn(Player.BLACK);
        board[1][2] = new Pawn(Player.BLACK);
        board[1][3] = new Pawn(Player.BLACK);
        board[1][4] = new Pawn(Player.BLACK);
        board[1][5] = new Pawn(Player.BLACK);
        board[1][6] = new Pawn (Player.BLACK);
        board[1][7] = new Pawn(Player.BLACK);
    }

    public boolean isComplete() {
        Player tempPlayer;
        if(this.inCheck(Player.WHITE)) {
            tempPlayer = Player.WHITE;
        }
        else {
            tempPlayer = Player.BLACK;
        }

        for(int xFrom = 0; xFrom < 8; xFrom++) {
            for(int yFrom = 0; yFrom < 8; yFrom++) {
                if(board[xFrom][yFrom] != null) {
                    if(board[xFrom][yFrom].player() == tempPlayer) {
                        for (int xTo = 0; xTo < 8; xTo++) {
                            for (int yTo = 0; yTo < 8; yTo++) {
                                if (board[xFrom][yFrom].isValidMove(new Move(xFrom, yFrom, xTo, yTo), board)) {
                                    ChessModel tempModel = new ChessModel(this);
                                    tempModel.move(new Move(xFrom, yFrom, xTo, yTo));
                                    if(!tempModel.inCheck(tempPlayer)) {
                                        return false;
                                    }
                                 }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean isValidMove(Move move) {
        boolean valid = false;
        ChessModel tempModel = new ChessModel(this);

        if (board[move.fromRow][move.fromColumn] != null)
            if(board[move.fromRow][move.fromColumn].player() == currentPlayer())
                if (board[move.fromRow][move.fromColumn].isValidMove(move, board) == true) {
                    tempModel.move(move);
                    if(!tempModel.inCheck(currentPlayer())) {
                        valid =  true;
                    }
                }
        return valid;
    }

    public void move(Move move) {
        setNextPlayer();
        board[move.toRow][move.toColumn] =  board[move.fromRow][move.fromColumn];
        board[move.fromRow][move.fromColumn] = null;
    }

    public boolean inCheck(Player p) {
        boolean valid = false;
        Player oponenet;
        int kingRow = 0;
        int kingCol = 0;
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                if(board[row][col] != null) {
                    if(board[row][col].player() == p) {
                        if(board[row][col].type().equals("King")) {
                            kingRow = row;
                            kingCol = col;
                        }
                    }
                }
            }
        }
        if(p == Player.WHITE) {
            oponenet = Player.BLACK;
        } else {
            oponenet = Player.WHITE;
        }
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                if(board[row][col] != null) {
                    if(board[row][col].player() == oponenet) {
                        if(board[row][col].isValidMove(new Move(row,col,kingRow,kingCol), board)) {
                            valid = true;
                        }
                    }
                }
            }
        }
        return valid;
    }


    public Player currentPlayer() {
        return player;
    }

    public int numRows() {
        return 8;
    }

    public int numColumns() {
        return 8;
    }

    public IChessPiece pieceAt(int row, int column) {
        return board[row][column];
    }

    public void setNextPlayer() {
        player = player.next();
    }

    public void setPiece(int row, int column, IChessPiece piece) {
        board[row][column] = piece;
    }

    public void AI() {
        /*
         * Write a simple AI set of rules in the following order.
         * a. Check to see if you are in check.
         * 		i. If so, get out of check by moving the king or placing a piece to block the check
         *
         * b. Attempt to put opponent into check (or checkmate).
         * 		i. Attempt to put opponent into check without losing your piece
         *		ii. Perhaps you have won the game.
         *
         *c. Determine if any of your pieces are in danger,
         *		i. Move them if you can.
         *		ii. Attempt to protect that piece.
         *
         *d. Move a piece (pawns first) forward toward opponent king
         *		i. check to see if that piece is in danger of being removed, if so, move a different piece.
         */
        Player bot = Player.BLACK;
        Player human = Player.WHITE;
        Move attemptMove = new Move(0,0,0,0);
        if(this.inCheck(bot)) {
            for(int xFrom = 0; xFrom < 8; xFrom++) {
                for(int yFrom = 0; yFrom < 8; yFrom++) {
                    if(board[xFrom][yFrom] != null) {
                        if(board[xFrom][yFrom].player() == bot) {
                            for (int xTo = 0; xTo < 8; xTo++) {
                                for (int yTo = 0; yTo < 8; yTo++) {
                                    if (this.isValidMove(new Move(xFrom, yFrom, xTo, yTo))) {
                                        ChessModel tempModel = new ChessModel(this);
                                        tempModel.move(new Move(xFrom, yFrom, xTo, yTo));
                                        if(!tempModel.inCheck(bot)) {
                                            attemptMove = new Move(xFrom, yFrom, xTo, yTo);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else {
            for(int xFrom = 0; xFrom < 8; xFrom++) {
                for(int yFrom = 0; yFrom < 8; yFrom++) {
                    if(board[xFrom][yFrom] != null) {
                        if(board[xFrom][yFrom].player() == bot) {
                            for (int xTo = 0; xTo < 8; xTo++) {
                                for (int yTo = 0; yTo < 8; yTo++) {
                                    if (this.isValidMove(new Move(xFrom, yFrom, xTo, yTo))) {
                                        attemptMove = new Move(xFrom,yFrom,xTo,yTo);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for(int xFrom = 0; xFrom < 8; xFrom++) {
                for(int yFrom = 0; yFrom < 8; yFrom++) {
                    if(board[xFrom][yFrom] != null) {
                        if(board[xFrom][yFrom].player() == human) {
                            for (int xTo = 0; xTo < 8; xTo++) {
                                for (int yTo = 0; yTo < 8; yTo++) {
                                    if (this.isValidMove(new Move(xFrom, yFrom, xTo, yTo))) {
                                        if(board[xTo][yTo] != null) {
                                            if (board[xTo][yTo].player() == bot) {
                                                for (int xMove = 0; xMove < 8; xMove++) {
                                                    for (int yMove = 0; yMove < 8; yMove++) {
                                                        if (this.isValidMove(new Move(xTo, yTo, xMove, yMove))) {
                                                            attemptMove = new Move(xTo, yTo, xMove, yMove);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for(int xFrom = 0; xFrom < 8; xFrom++) {
                for(int yFrom = 0; yFrom < 8; yFrom++) {
                    if(board[xFrom][yFrom] != null) {
                        if(board[xFrom][yFrom].player() == bot) {
                            for (int xTo = 0; xTo < 8; xTo++) {
                                for (int yTo = 0; yTo < 8; yTo++) {
                                    if (this.isValidMove(new Move(xFrom, yFrom, xTo, yTo))) {
                                        ChessModel tempModel = new ChessModel(this);
                                        tempModel.move(new Move(xFrom, yFrom, xTo, yTo));
                                        if(tempModel.inCheck(human)) {
                                            attemptMove = new Move(xFrom, yFrom, xTo, yTo);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        this.move(attemptMove);

    }
}
