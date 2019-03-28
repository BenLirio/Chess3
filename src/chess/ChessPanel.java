package chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChessPanel extends JPanel {

    private JButton[][] board;
    private ChessModel model;
    private ArrayList<ChessModel> history;
    private JButton backBtn;

    private ImageIcon wRook;
    private ImageIcon wBishop;
    private ImageIcon wQueen;
    private ImageIcon wKing;
    private ImageIcon wPawn;
    private ImageIcon wKnight;
    private ImageIcon bRook;
    private ImageIcon bBishop;
    private ImageIcon bQueen;
    private ImageIcon bKing;
    private ImageIcon bPawn;
    private ImageIcon bKnight;

    private boolean firstTurnFlag;
    private int fromRow;
    private int toRow;
    private int fromCol;
    private int toCol;
    // declare other intance variables as needed

    private listener listener;

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

    private void setBackGroundColor(int r, int c) {
        board[r][c].setOpaque(true);
        board[r][c].setBorderPainted(false);
        if ((c % 2 == 1 && r % 2 == 0) || (c % 2 == 0 && r % 2 == 1)) {
            board[r][c].setBackground(Color.LIGHT_GRAY);
        } else if ((c % 2 == 0 && r % 2 == 0) || (c % 2 == 1 && r % 2 == 1)) {
            board[r][c].setBackground(Color.WHITE);
        }
    }

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

    // method that updates the board
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

    // inner class that represents action listener for buttons
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
