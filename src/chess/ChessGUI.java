package chess;

import javax.swing.*;
import java.awt.*;

/**
 * Chess GUI Controls the Visual Panel of the project.
 * @author Ben Lirio - used template
 */
public class ChessGUI {
    /**
     * Main function for the whole program.
     * Creates the JFrame and adds panel to it.
     * @param args String args.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ChessPanel panel = new ChessPanel();
        frame.getContentPane().add(panel);

        frame.setResizable(true);
        frame.setPreferredSize(new Dimension(800, 637));
        frame.pack();
        frame.setVisible(true);
    }
}