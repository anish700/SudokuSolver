/*
Anish R
1710110053
CSD311 ASSIGNMENT 2
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main extends JFrame {

    public static void main(String[] args) {
int n=9;
        SudokuGUI obj=new SudokuGUI();
        solverClass solveObj=new solverClass();


    }

}

class SudokuGUI implements ActionListener {
//creating the required frame, menuitems,buttons
    JFrame baseFrame;
    solverClass solverObject=new solverClass();
    JMenuBar menuBar;
    int n = 9;
    JMenuItem chooseFileForInput,Solve,clearAll;
    JMenu menu;
    JButton[][] sudokuButtons = new JButton[9][9];
    Object[] selectionValues = {" ","1","2","3","4","5","6",
            "7","8","9","Solve", "Clear all"};
    int [][]arr = new int[n][n];
//    UIManager ui=new UIManager();

    SudokuGUI() {
        baseFrame = new JFrame("Sudoku");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        menuBar=new JMenuBar();
        menu=new JMenu("Options");
        chooseFileForInput=new JMenuItem("chooseFileForInput");
        Solve=new JMenuItem("Solve");
        clearAll=new JMenuItem("Clear the Sudoku");
        menu.add(chooseFileForInput);
        menu.add(Solve);
        menu.add(clearAll);
        menuBar.add(menu);
        Solve.addActionListener(this);
        clearAll.addActionListener(this);
        chooseFileForInput.addActionListener(this);

        baseFrame.setJMenuBar(menuBar);

        baseFrame.setLayout(new GridLayout(9, 9));
        baseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Loop for adding the buttons and their respective ActionListener to the baseFrame is given below
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sudokuButtons[i][j] = new JButton(" ");
                sudokuButtons[i][j].addActionListener(this);
                baseFrame.add(sudokuButtons[i][j]).setBackground(Color.white);
                //      sudokuButtons[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
            }
        }
        chooseFileForInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser("c:");
                int r = fileChooser.showOpenDialog(null);
//invoke the Jfunction
                if (r == JFileChooser.APPROVE_OPTION) {
                    //if the user selects a file , we set the path of the selected to the label
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                    try {

                        String s = "", sudokuFile = "";
//creating the file reader object
                       FileReader fileReader = new FileReader(file);
//creating the buffer reader object
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        sudokuFile = bufferedReader.readLine();
                        // Taking the input from the file by reading it and adding a , when required
                        while ((s = bufferedReader.readLine()) != null) {
                            sudokuFile = sudokuFile + "," + s;
                        }
                        enterFromFile(sudokuFile);
                    }
                    catch (Exception evt) {
                        JOptionPane.showMessageDialog(null, "Improper format, please try again.");
                    }
                }
            }
        });
        sudokuBorder(); //calling the function to make the darker borders in the GUI
        baseFrame.setSize(500, 500);

        baseFrame.setLocationRelativeTo(null);
        baseFrame.setVisible(true);
    }

    //function to get the input from the file and set it into the GUI
public void enterFromFile(String file){
        String[][] sudInput2=new String[200][200];
    int[][] sudInput3=new int[200][200];

    int m=0;
String[] sudInput=new String[200];
    sudInput=file.split(",");
for(int i=0;i<n;i++){
   if (m==sudInput.length -1) break;;
   for (int j=0;j<n;j++){
       sudInput2[i][j]=sudInput[m];
       arr[i][j]=Integer.parseInt(sudInput[m]);
       m++;
   }
}
//Setting the values into the grid tiles in the GUI
    System.out.println(Arrays.deepToString(arr).replace("],", "],\n"));
    for (int i=0;i<n;i++){
        for (int j=0;j<n;j++){

            sudokuButtons[i][j].setText(sudInput2[i][j]+" ");
        }
    }





}
// a function to highlight and make borders around to identify 3 by 3 boxes
    public void sudokuBorder() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sudokuButtons[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

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

    @Override
    // getting the action performed , ie, the input for each tile in the sudoku GUI
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Solve) { // if the solution exists then print and update the GUI tile of the SudokuPuzzle
            if(solverObject.solveSudoku(arr,n)==false){
                JOptionPane.showMessageDialog(null, "  NO SOLUTION ");
            }



            for (int i=0;i<n;i++) {
                for (int k = 0; k < n; k++) {
                    if (!solverObject.isSafe(arr, i, k, arr[i][k])) {
                        JOptionPane.showMessageDialog(null, " REDUNDANCY");
                        return;
                    }
                }
            }
            solverObject.solveSudoku(arr,n);
            updateView();
        }
        //
        // else  JOptionPane.showMessageDialog(null,"NO SOLUTION","ERROR",JOptionPane.ERROR_MESSAGE);

        if (e.getSource()==clearAll) clearTheValues(); // clearing all values if chosen

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(e.getSource()==sudokuButtons[i][j])
                {
                    JDialog.setDefaultLookAndFeelDecorated(true);
                    String initialSelection=" ";
                    Object selection=JOptionPane.showInputDialog(null," Select a number to be inputed",
                            "SELECT OPTIONS",JOptionPane.QUESTION_MESSAGE,null,selectionValues,initialSelection);
                    if(!selection.toString().equals(selectionValues[0].toString()) &&
                            !selection.toString().equals(selectionValues[10].toString())
                    )
                    //if user has not selected Blank space or Clearing the values , we fix the value the user has chosen from the menu
                    {sudokuButtons[i][j].setText(selection.toString());
                        try{arr[i][j] =Integer.parseInt(selection.toString());}
                        catch(Exception e1){
                            e1.printStackTrace();
                        }}
                    // if user has chosen a blank space, we show it on the tile
                    if(selection.toString().equals(selectionValues[0].toString())){
                        arr[i][j]=0;
                        sudokuButtons[i][j].setText(" ");}

                    //------------------
                    if(selection.toString().equals(selectionValues[10].toString())) {
                        if(solverObject.solveSudoku(arr,n))  // If solution exist print and update the sudokuGridValues
                            updateView();
                        else {
                            JOptionPane.showMessageDialog(null, "Solution doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
                        }}
                    //--------------------


                    // call clearAll() method to clean the sudokuGridValues
                    if(selection.toString().equals(selectionValues[10].toString())){
                        clearTheValues();
                    }
                }
            }
        }
    }
    // a function to clear the values in GUI
    public void clearTheValues(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                arr[i][j]=0;
                sudokuButtons[i][j].setText("");
            }
        }
    }
// this function sets the updated values into the GUI
    public  void updateView(){
        System.out.println(Arrays.deepToString(arr).replace("],", "],\n"));
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){

                sudokuButtons[i][j].setText(arr[i][j]+" ");
            }
        }
    }
}

class solverClass {
    // Class to implement the algorithm to solve the Puzzle


    int n;
    int[][] sudokuArray;
    // creating an array to manipulate later

    solverClass(int[][] array,int n) {
        this.sudokuArray = array;
        this.n=n;
    }

    solverClass() {
    } //empty constructor

    public static boolean isSafe(int[][] sudokuGridValues,
                                 int row, int c,
                                 int numberForGrid)
    {
int[][] arrGridValues=new int[9][9];
        //checking for redundancy in the rows
        for (int d = 0; d < sudokuGridValues.length; d++)
        {
            //if redundancy in row, reuturn false
            if (sudokuGridValues[row][d] == numberForGrid)
            {
                if(d!=c)                return false;
            }
        }


        for (int r = 0; r < sudokuGridValues.length; r++)
        //checking for clash in column
        {
            //if redundant in column, return false
            if (sudokuGridValues[r][c] == numberForGrid)
            {
                if (r!=row)
                return false;
            }
        }


        int sqrt = (int) Math.sqrt(sudokuGridValues.length);
        //cheking for clash in 3 by 3 boxes
        int boxRowStart = row - row % sqrt;
        int boxColStart = c - c % sqrt;

        for (int r = boxRowStart;
             r < boxRowStart + sqrt; r++)
        {
            for (int d = boxColStart;
                 d < boxColStart + sqrt; d++)
            {
                if (sudokuGridValues[r][d] == numberForGrid)
                {if (d!=c&&r!=row)
                    return false;
                }
            }
        }

        return true;
    }

    public  boolean solveSudoku(int[][] sudokuGridValues, int n)
    {int[][] arr=new int[n][n];
    arr=sudokuGridValues;
        int row = -1;
        int col = -1;
        boolean isEmpty = true;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (arr[i][j] == 0)
                {
                    // still missing nos in sudoku
                    row = i;
                    col = j;

                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty)
            {
                break;
            }
        }


        if (isEmpty)
        {
            return true;
        }


        for (int num = 1; num <= n; num++)
        // loop the algorithm for each
        {
            if (isSafe(arr, row, col, num))
            {
                arr[row][col] = num;
                if (solveSudoku(arr, n))
                {
                    return true;
                }
                else
                {

                    arr[row][col] = 0;

                }
            }

               // JOptionPane.showMessageDialog(null," REDUNDANCY");

        }
        return false;
    }

    public static void printTheOutput(int[][] sudokuGridValues, int N)
    {

        for (int r = 0; r < N; r++)
        {
            for (int d = 0; d < N; d++)
            {
                System.out.print(sudokuGridValues[r][d]);
                System.out.print(" ");
            }
            System.out.print("\n");

            if ((r + 1) % (int) Math.sqrt(N) == 0)
            {
                System.out.print("");
            }
        }
    }
}



