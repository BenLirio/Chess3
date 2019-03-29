package chess;

/**
 * Chess model holds all of the chess pieces and
 * contains the methods used to move the pieces.
 * @author Ben Lirio - used template.
 */
public class ChessModel implements IChessModel {
    /** Variable to contain all of the chess pieces. */
    public IChessPiece[][] board;

    /** Variable that represents the current player. */
    private Player player;

    /**
     * Creates a clone of a specific chess model.
     * @param c ChessModel to be cloned
     */
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

    /**
     * Blank Board with selected starting player.
     *
     * @param player Starting player
     */
    public ChessModel(Player player) {
        board = new IChessPiece[8][8];
        this.player = player;
    }

    /**
     * Base constructor of the chess model. Sets up the board normally and sets the starting player to white.
     */
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

    /**
     * This function checks to see if the game is complete. If so who won the game.
     * @return Is there a checkmate?
     */
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

    /**
     * Checks to see if there is a valid move from one position to another.
     * @param move Hold the positions that are going to be moved.
     * @return Return true if the piece can move to a certain spot.
     */
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

    /**
     * Moves the piece from one spot to another specified in the move.
     * @param move Move param is a request for a piece to move.
     */
    public void move(Move move) {
        setNextPlayer();
        board[move.toRow][move.toColumn] =  board[move.fromRow][move.fromColumn];
        board[move.fromRow][move.fromColumn] = null;
    }

    /**
     * Takes in the current player that it is checking if that player is in check.
     * @param  p Current player to be checked if he is in check.
     * @return True if the current player is in check.
     */
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

    /**
     * Getter method for the current Player.
     * @return The current player.
     */
    public Player currentPlayer() {
        return player;
    }

    /**
     * Returns the number of rows
     * @return eight rows.
     */
    public int numRows() {
        return 8;
    }

    /**
     * Reruns the number of columns
     * @return eight columns.
     */
    public int numColumns() {
        return 8;
    }

    /**
     * Checks to see which piece is at this spot.
     * @param row Current row to check.
     * @param column Current column to check.
     * @return Returns the current piece that is to be checked.
     */
    public IChessPiece pieceAt(int row, int column) {
        return board[row][column];
    }

    /**
     * Sets the next player to the player that is not currently selected.
     */
    public void setNextPlayer() {
        player = player.next();
    }

    /**
     * Sets a piece at a specified location.
     * @param row Row to be put.
     * @param column Column to be put.
     * @param piece The piece that is to be put.
     */
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
        Move requestedMove = new Move(0,0,0,0);
        Player bot = Player.BLACK;
        if(this.inCheck(bot)) {
            int score = -9999;
            for(Move move : movesForPlayer(bot)) {
                ChessModel cloneModel = new ChessModel(this);
                cloneModel.move(move);
                if(!cloneModel.inCheck(bot) && cloneModel.boardScore(player) > score) {
                    score = boardScore(player);
                    requestedMove = move;
                }
            }
        } else {
            int score = -9999;
            for(Move move : movesForPlayer(bot)) {
                ChessModel cloneModel = new ChessModel(this);
                cloneModel.move(move);
                if(cloneModel.boardScore(player) > score) {
                    requestedMove = move;
                }

            }
            score = -5;
            for(Move move : movesForPlayer(bot)) {
                ChessModel cloneModel = new ChessModel(this);
                cloneModel.move(move);
                if(cloneModel.boardScore(player) > score) {
                    if(!moveInDanger(move,bot)) {
                        requestedMove = move;
                    }
                }

            }
        }
        move(requestedMove);
    }

    /**
     * Returns all the spaces on the board.
     * @return double array of all the spaces on the board.
     */
    public int[][] allSpaces() {
        int[][] spaces = new int[64][2];
        for(int r = 0; r < 8; r++) {
            for(int c = 0; c < 8; c++) {
                int[] position = new int[2];
                position[0] = r;
                position[1] = c;
                spaces[r*8 + c] = position;
            }
        }
        return spaces;
    }

    /**
     * Checks to see if the space is occupied
     * @param space Space being checked
     * @return Boolean if the space is occupied.
     */
    public boolean spaceOccupied(int[] space) {
        if(board[space[0]][space[1]] != null) {
            return true;
        }
        return false;
    }

    /**
     * Checks to see who owns the space
     * @param space The space being checked
     * @return Return a player
     */
    public Player playerOfSpace(int[] space) {
        if(!spaceOccupied(space)) {
            throw new IndexOutOfBoundsException();
        } else {
            return board[space[0]][space[1]].player();
        }
    }

    /**
     * Checks to find all of the pieces of the player.
     * @param player Which player is being searched
     * @return
     */
    public int[][] allPiecesOfPlayer(Player player) {
        int[][] locations;
        int numPieces = 0;
        for(int[] space : allSpaces()) {
            if(spaceOccupied(space)) {
                if(playerOfSpace(space) == player) {
                    numPieces++;
                }
            }
        }
        locations = new int[numPieces][2];
        int i = 0;
        for(int[] space : allSpaces()) {
            if(spaceOccupied(space)) {
                if(playerOfSpace(space) == player) {
                    locations[i] = space;
                    i++;
                }
            }

        }
        return locations;
    }

    /**
     * Returns all the possible moves the player can act on.
     * @param player Selected player
     * @return An array of moves
     */
    public Move[] movesForPlayer(Player player) {
        Move[] moves;
        int numMoves = 0;
        boolean changedPlayer = false;
        if(currentPlayer() != player) {
            setNextPlayer();
            changedPlayer = true;
        }
        for(int[] piece : allPiecesOfPlayer(player)) {
            for(int[] space : allSpaces()) {
                if(this.isValidMove(new Move(piece[0],piece[1],space[0],space[1]))) {
                    numMoves++;
                }
            }
        }
        moves = new Move[numMoves];
        int i = 0;
        for(int[] piece : allPiecesOfPlayer(player)) {
            for(int[] space : allSpaces()) {
                if(this.isValidMove(new Move(piece[0],piece[1],space[0],space[1]))) {
                    moves[i] = new Move(piece[0],piece[1],space[0],space[1]);
                    i++;
                }
            }
        }
        if(changedPlayer) {
            setNextPlayer();
        }
        return moves;
    }

    /**
     * Checks to see if the position can be attacked
     * @param dangerMove A move that is checked
     * @param player The player that is to be checked
     * @return Return if the position can be attacked.
     */
    public boolean moveInDanger(Move dangerMove, Player player) {
        for(Move move : movesForPlayer(player.next())) {
            if(move.toColumn == dangerMove.toColumn && move.toRow == dangerMove.toRow) {
                return true;
            }
        }
        return false;
    }

    /**
     * gets the score of a single position on the board.
     * @param position The x and y coordinates.
     * @param player The player That is being scored
     * @return
     */
    public int getPositionScore(int[] position, Player player) {
        int score = 0;
        if(this.spaceOccupied(position)) {
            switch (board[position[0]][position[1]].type()) {
                case "Pawn":
                    score = 2;
                    break;
                case "Rook":
                    score = 6;
                    break;
                case "Bishop":
                    score = 4;
                    break;
                case "Knight":
                    score = 4;
                    break;
                case "Queen":
                    score = 10;
                    break;
                case "King":
                    score = 20;
                    break;
            }
            if(board[position[0]][position[1]].player() == player.next()) {
                score *= -1;
            }
        }
        return score;
    }

    /**
     * boardScore Finds the total score of the board
     * @param player
     * @return Returns the score of the board.
     */
    public int boardScore(Player player) {
        int score = 0;
        if(inCheck(player.next())) {
            score += 5;
        }
        for(int[] space : allSpaces()) {
            score += getPositionScore(space,player);
        }
        return score;
    }
}
