import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        SudokuGUI obj=new SudokuGUI();
    }
}

class SudokuGUI {
    JFrame baseFrame;
    int n = 9;
    JButton[][] sudokuButtons = new JButton[9][9];

    SudokuGUI() {
        baseFrame = new JFrame("Sudoku");
        baseFrame.setLayout(new GridLayout(9, 9));
        baseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                sudokuButtons[i][j] = new JButton(" ");
                baseFrame.add(sudokuButtons[i][j]).setBackground(Color.white);
                sudokuButtons[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
            }
        }
        sudokuBorder();
        baseFrame.setSize(500, 500);
        //  baseFrame.pack();
        baseFrame.setLocationRelativeTo(null);
        baseFrame.setVisible(true);
    }

    public void sudokuBorder() {
        for (int i = 0; i < sudokuButtons.length; i++) {
            for (int j = 0; j < 81; j++) {
                if ((i == 0 && j == 2) || (i == 0 && j == 5) || (i == 1 && j == 2) || (i == 1 && j == 5)
                        || (i == 7 && j == 2) || (i == 7 && j == 5) || (i == 4 && j == 2) || (i == 4 && j == 5)
                        || (i == 8 && j == 2) || (i == 8 && j == 5))
                    sudokuButtons[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 2, Color.BLACK));
                else if ((i == 0 && j == 3) || (i == 0 && j == 6) || (i == 1 && j == 3) || (i == 1 && j == 6)
                        || (i == 7 && j == 3) || (i == 7 && j == 6) || (i == 4 && j == 3) || (i == 4 && j == 6)
                        || (i == 8 && j == 3) || (i == 8 && j == 6))
                    sudokuButtons[i][j].setBorder(BorderFactory.createMatteBorder(1, 2, 1, 1, Color.BLACK));
                else if ((i == 3 && j == 0) || (i == 3 && j == 1) || (i == 3 && j == 4) || (i == 3 && j == 7)
                        || (i == 3 && j == 8) || (i == 6 && j == 0) || (i == 6 && j == 1) || (i == 6 && j == 4) || (i == 6 && j == 7)
                        || (i == 6 && j == 8))
                    sudokuButtons[i][j].setBorder(BorderFactory.createMatteBorder(2, 1, 1, 1, Color.BLACK));
                else if ((i == 2 && j == 0) || (i == 2 && j == 1) || (i == 2 && j == 4) || (i == 2 && j == 7) ||
                        (i == 2 && j == 8) || (i == 5 && j == 0) || (i == 5 && j == 1) || (i == 5 && j == 4) ||
                        (i == 5 && j == 7) || (i == 5 && j == 8))
                    sudokuButtons[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 2, 1, Color.BLACK));
                else if ((i == 2 && j == 2) || (i == 2 && j == 5) || (i == 5 && j == 2) || (i == 5 && j == 5))
                    sudokuButtons[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 2, 2, Color.BLACK));
                else if ((i == 2 && j == 3) || (i == 2 && j == 6) || (i == 5 && j == 3) || (i == 5 && j == 6))
                    sudokuButtons[i][j].setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.BLACK));
                else if ((i == 3 && j == 2) || (i == 3 && j == 5) || (i == 6 && j == 2) || (i == 6 && j == 5))
                    sudokuButtons[i][j].setBorder(BorderFactory.createMatteBorder(2, 1, 1, 2, Color.BLACK));
                else if ((i == 3 && j == 3) || (i == 3 && j == 6) || (i == 6 && j == 3) || (i == 6 && j == 6))
                    sudokuButtons[i][j].setBorder(BorderFactory.createMatteBorder(2, 2, 1, 1, Color.BLACK));
            }
        }
    }
}