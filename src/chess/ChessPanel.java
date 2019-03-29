package chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Chess panel holds all of the buttons and displays them to the current panel.
 * This is not the visual nor the game but the data behind the visual.
 * @author Ben Lirio - Used template
 */
public class ChessPanel extends JPanel {

    /** Holds all of the buttons */
    private JButton[][] board;

    /** Holds an instance of chess model */
    private ChessModel model;

    /** All the previous models. */
    private ArrayList<ChessModel> history;

    /** This button is used to back up in the game */
    private JButton backBtn;

    /** Icon for white rook */
    private ImageIcon wRook;

    /** Icon for white Bishop */
    private ImageIcon wBishop;

    /** Icon for white queen */
    private ImageIcon wQueen;

    /** Icon for white king */
    private ImageIcon wKing;

    /** Icon for white pawn */
    private ImageIcon wPawn;

    /** Icon for white knight */
    private ImageIcon wKnight;

    /** Icon for black rook */
    private ImageIcon bRook;

    /** Icon for black bishop */
    private ImageIcon bBishop;

    /** Icon for black queen */
    private ImageIcon bQueen;

    /** Icon for black king */
    private ImageIcon bKing;

    /** Icon for black pawn */
    private ImageIcon bPawn;

    /** Icon for black knight. */
    private ImageIcon bKnight;

    /** Who gets to go first. */
    private boolean firstTurnFlag;

    /**Used to record where the move came from */
    private int fromRow;

    /**used to see which row the move is going to */
    private int toRow;

    /** used to see which column the move came from */
    private int fromCol;

    /** Used to see to which column the row is going. */
    private int toCol;

    /**Used as an action listener */
    private listener listener;

    /**
     * Main constructor
     * Creates all of the buttons and creates all behind the scenes allingning and ordering.
     * Sets the size of the board.
     */
    public ChessPanel() {

        history = new ArrayList<>();
        model = new ChessModel();
        board = new JButton[model.numRows()][model.numColumns()];
        listener = new listener();
        backBtn = new JButton("Back");
        backBtn.addActionListener(listener);
        createIcons();

        JPanel boardpanel = new JPanel();
        JPanel buttonpanel = new JPanel();
        boardpanel.setLayout(new GridLayout(model.numRows(), model.numColumns(), 1, 1));
        buttonpanel.add(backBtn);
        for (int r = 0; r < model.numRows(); r++) {
            for (int c = 0; c < model.numColumns(); c++) {
                if (model.pieceAt(r, c) == null) {
                    board[r][c] = new JButton("", null);
                    board[r][c].addActionListener(listener);
                } else if (model.pieceAt(r, c).player() == Player.WHITE) {
                    placeWhitePieces(r, c);
                } else if (model.pieceAt(r, c).player() == Player.BLACK) {
                    placeBlackPieces(r, c);
                }

                setBackGroundColor(r, c);
                boardpanel.add(board[r][c]);
            }
        }
        add(boardpanel, BorderLayout.WEST);
        boardpanel.setPreferredSize(new Dimension(600, 600));
        add(buttonpanel);
        firstTurnFlag = true;
    }

    /**
     * Sets the background color of an individual tile
     * @param r What row is the tile in?
     * @param c What column is the tile in?
     */
    private void setBackGroundColor(int r, int c) {
        board[r][c].setOpaque(true);
        board[r][c].setBorderPainted(false);
        if ((c % 2 == 1 && r % 2 == 0) || (c % 2 == 0 && r % 2 == 1)) {
            board[r][c].setBackground(Color.LIGHT_GRAY);
        } else if ((c % 2 == 0 && r % 2 == 0) || (c % 2 == 1 && r % 2 == 1)) {
            board[r][c].setBackground(Color.WHITE);
        }
    }

    /**
     * Switch case function that places a certain piece.
     * @param r Row the piece should be placed.
     * @param c Column the piece should be placed.
     */
    private void placeWhitePieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, wPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, wRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, wKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, wBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, wQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, wKing);
            board[r][c].addActionListener(listener);
        }
    }

    /**
     * Switch case function that places a certain piece.
     * Same as above but for the black board.
     * @param r Row the piece should be placed.
     * @param c Column the piece should be placed.
     */
    private void placeBlackPieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, bPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, bRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, bKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, bBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, bQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, bKing);
            board[r][c].addActionListener(listener);
        }
    }

    /**
     * Retrieves all of the images from the folders that are responsible for holding the files.
     */
    private void createIcons() {
        // Sets the Image for white player pieces
        wRook = new ImageIcon("./src/images/wRook.png");
        wBishop = new ImageIcon("./src/images/wBishop.png");
        wQueen = new ImageIcon("./src/images/wQueen.png");
        wKing = new ImageIcon("./src/images/wKing.png");
        wPawn = new ImageIcon("./src/images/wPawn.png");
        wKnight = new ImageIcon("./src/images/wKnight.png");
        bRook = new ImageIcon("./src/images/bRook.png");
        bBishop = new ImageIcon("./src/images/bBishop.png");
        bQueen = new ImageIcon("./src/images/bQueen.png");
        bKing = new ImageIcon("./src/images/bKing.png");
        bPawn = new ImageIcon("./src/images/bPawn.png");
        bKnight = new ImageIcon("./src/images/bKnight.png");
    }

    /**
     * Displays the current pieces and where they are, Newest and up to date data we can get.
     */
    private void displayBoard() {

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++)
                if (model.pieceAt(r, c) == null)
                    board[r][c].setIcon(null);
                else
                if (model.pieceAt(r, c).player() == Player.WHITE) {
                    if (model.pieceAt(r, c).type().equals("Pawn"))
                        board[r][c].setIcon(wPawn);

                    if (model.pieceAt(r, c).type().equals("Rook"))
                        board[r][c].setIcon(wRook);

                    if (model.pieceAt(r, c).type().equals("Knight"))
                        board[r][c].setIcon(wKnight);

                    if (model.pieceAt(r, c).type().equals("Bishop"))
                        board[r][c].setIcon(wBishop);

                    if (model.pieceAt(r, c).type().equals("Queen"))
                        board[r][c].setIcon(wQueen);

                    if (model.pieceAt(r, c).type().equals("King"))
                        board[r][c].setIcon(wKing);

                }
                else
                if (model.pieceAt(r, c).player() == Player.BLACK) {
                    if (model.pieceAt(r, c).type().equals("Pawn"))
                        board[r][c].setIcon(bPawn);

                    if (model.pieceAt(r, c).type().equals("Rook"))
                        board[r][c].setIcon(bRook);

                    if (model.pieceAt(r, c).type().equals("Knight"))
                        board[r][c].setIcon(bKnight);

                    if (model.pieceAt(r, c).type().equals("Bishop"))
                        board[r][c].setIcon(bBishop);

                    if (model.pieceAt(r, c).type().equals("Queen"))
                        board[r][c].setIcon(bQueen);

                    if (model.pieceAt(r, c).type().equals("King"))
                        board[r][c].setIcon(bKing);

                }

        }
        repaint();
    }

    /**
     * This action listener can be viewd as the Code behind the GUI.
     * Which button is pressed and how does that effect everything else.
     */
    private class listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if(backBtn == event.getSource()) {
                if(history.size() > 0) {
                    model = history.get(history.size() - 1);
                    history.remove(history.size() - 1);
                    displayBoard();
                }
            }
            for (int r = 0; r < model.numRows(); r++)
                for (int c = 0; c < model.numColumns(); c++)
                    if (board[r][c] == event.getSource())
                        if (firstTurnFlag == true) {
                            if(model.pieceAt(r,c) != null) {
                                if(model.currentPlayer() == model.pieceAt(r,c).player()) {
                                    fromRow = r;
                                    fromCol = c;
                                    firstTurnFlag = false;

                                    for(int row = 0; row < 8; row++) {
                                        for(int col = 0; col < 8; col++) {
                                            if(model.isValidMove(new Move(r, c, row, col))) {
                                                System.out.println("can move to: " + row + "," + col);
                                                board[row][col].setBackground(new Color(195, 249, 187));
                                            }
                                        }
                                    }
                                }
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "It's not your turn.");
                            }
                        } else {
                            toRow = r;
                            toCol = c;
                            firstTurnFlag = true;
                            Move m = new Move(fromRow, fromCol, toRow, toCol);
                            for(int row = 0; row < 8; row++) {
                                for(int col = 0; col < 8; col++) {
                                    setBackGroundColor(row,col);
                                }
                            }
                            if ((model.isValidMove(m)) == true) {
                                history.add(new ChessModel(model));
                                model.move(m);
                                model.AI();
                                if(model.inCheck(Player.WHITE)) {
                                    if (model.isComplete()) {
                                        JOptionPane.showMessageDialog(null, "Good Job!!!!\nBlack Has WON");
                                        System.exit(0);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "White is in check");
                                    }
                                }
                                if(model.inCheck(Player.BLACK)){
                                    if(model.isComplete()) {
                                        JOptionPane.showMessageDialog(null, "Good Job!!!!\nWhite Has WON");
                                        System.exit(0);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Black is in check");
                                    }
                                }
                                displayBoard();
                            }
                        }
        }
    }
}
