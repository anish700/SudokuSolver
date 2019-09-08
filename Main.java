import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashSet;

public class Main extends JFrame {

    public static void main(String[] args) {
        System.out.println("Working");
        SudokuGUI obj=new SudokuGUI();
    }
}

class SudokuGUI implements ActionListener {

    JFrame baseFrame;
    solverClass solverObject=new solverClass();

    JMenuBar menuBar;
    int n = 9;
JMenuItem Solve,clearAll;
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

        Solve=new JMenuItem("Solve");
        clearAll=new JMenuItem("Clear the Sudoku");
        menu.add(Solve);
        menu.add(clearAll);
        menuBar.add(menu);
        Solve.addActionListener(this);clearAll.addActionListener(this);

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

        sudokuBorder(); //calling the function to make the darker borders in the GUI
        baseFrame.setSize(500, 500);

        baseFrame.setLocationRelativeTo(null);
        baseFrame.setVisible(true);
    }

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
            solverObject.solveFunction(arr);
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
        if(solverObject.solveFunction(arr))  // If solution exist print and update the board
            updateView();
        else {
            JOptionPane.showMessageDialog(null, "Solution doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
        }}
    //--------------------


    // call clearAll() method to clean the board
    if(selection.toString().equals(selectionValues[10].toString())){
        clearTheValues();
    }
}
            }
}
    }
    public void clearTheValues(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                arr[i][j]=0;
                sudokuButtons[i][j].setText("");
            }
        }
    }

    public  void updateView(){
        System.out.println(Arrays.deepToString(arr).replace("],", "],\n"));
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                sudokuButtons[i][j].setText(arr[i][j]+" ");
            }
        }
    }
    }

    class solverClass {  // Class to implement the algorithm to solve the Puzzle


    int n;
    int[][] sudokuArray;  // creating an array to manipulate later

        solverClass(int[][] array,int n) {
            this.sudokuArray = array;
            this.n=n;
        }

        solverClass() {
        } //empty constructor

        public boolean solveFunction(int[][] array) {  //Function to solve the sudoku
            this.sudokuArray = array;
            boolean flag = solveSudokuFn(0, 0);
            flag = valid();
            return flag;
        }

        public boolean solveSudokuFn(int row, int column) {  // a= row number, b= column number
            if (row == 9) {
                row = 0;
                if (++column == 9) return true;
            }
            if (sudokuArray[row][column] != 0) return solveSudokuFn(row + 1, column);
            for (int number = 1; number <= n; number++) {
                if (isLegal(row, column, number)) {
                    sudokuArray[row][column] = number;

                    if (solveSudokuFn(row + 1, column)) return true;
                }
            }
            sudokuArray[row][column]=0;
            return false;
        }

            public boolean valid () {
                for (int r = 0; r < n; r++) {
                    for (int c = 0; c < n; c++) {
                        if (sudokuArray[r][c] != 0) {
                            if (isLegal(r, c) == false) return false;
                        }
                    }
                }
                return true;
            }
            public boolean isLegal ( int r, int c, int num){
            // checking the column
                for(int i=0;i<n;i++){
                    if (sudokuArray[r][i]==num)
                        return false;
                }
                //checking the row now

                for(int i=0;i<n;i++){
                    if (sudokuArray[i][c]==num)
                        return false;
                }
                int rowNo= (r/3)*3;
                int colNo=(c/3)*3;
                for(int i=0;i<3;i++){
                    for(int j=0;j<3;j++){
                        if(sudokuArray[rowNo+i][colNo+j]==num) return false;
                    }
                }

                return true;
            }
            public boolean isLegal(int x, int y){ HashSet<Integer> hs = new HashSet<Integer>();
                for(int i=0;i<9;i++){
                    if(hs.contains(sudokuArray[x][i])){
                        return false;
                    }
                    if(sudokuArray[x][i]>0 || sudokuArray[x][i]<10){
                        hs.add(sudokuArray[x][i]);
                    }
                }
                hs.clear();
                for(int i=0;i<9;i++){
                    if(hs.contains(sudokuArray[i][y])){
                        return false;
                    }
                    if(sudokuArray[i][y]>0 || sudokuArray[i][y]<10){
                        hs.add(sudokuArray[i][y]);
                    }
                }
                hs.clear();

                int row =(x/3)*3;
                int col =(y/3)*3;
                for(int i=0;i<3;i++){
                    for(int j=0;j<3;j++){
                        if(hs.contains(sudokuArray[row+i][col+j]))
                            return false;
                        if(sudokuArray[row+i][col+j]>0 || sudokuArray[row+i][col+j]<10){
                            hs.add(sudokuArray[row+i][col+j]);
                        }
                    }
                }
                return true;
            }
    }




