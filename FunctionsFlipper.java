
/**
 * Write a description of class FunctionsFippler here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FunctionsFlipper
{
    private FunctionsChecker checker = new FunctionsChecker();
    public FunctionsFlipper()
    {

    }

    public String [][] changePieces( String[][] board,int row, int column, int turn)
    {
        String myPiece;
        String opponent;

        String [][] tempBoard = new String [8][8];
        copyBoard(board, tempBoard);

        int DyMax = 7 - row;
        int UyMax = row;
        int LxMax = column;
        int RxMax = 7 - column;
        int counter = 1;

        if (turn%2 == 0)
        {
            myPiece = "X";
            opponent = "Y";
        }
        else
        {   myPiece ="Y";
            opponent = "X";
        }
        if(checker.verticalUpChecker(tempBoard,row,column,UyMax,turn) == true)
        {
            while(tempBoard[row-counter][column].equals(opponent)&&(row-counter)>=0)
            {
                tempBoard[row-counter][column]=myPiece;
                counter++;
            }
        }
        counter = 1;
        if(checker.verticalDownChecker(tempBoard,row,column,DyMax,turn) == true)
        {
            while(tempBoard[row+counter][column].equals(opponent)&& (row+counter)< 8)
            {
                tempBoard[row+counter][column]=myPiece;
                counter++;
            }
        }
        counter = 1;
        if(checker.horizLeftChecker(tempBoard,row,column,LxMax,turn) == true)
        {
            while(tempBoard[row][column-counter].equals(opponent)&&(column - counter)>=0)
            {
                tempBoard[row][column-counter]=myPiece;
                counter++;
            }
        }
        counter = 1;
        if(checker.horizRightChecker(tempBoard,row,column,RxMax,turn) == true)
        {           
            while(tempBoard[row][column+counter].equals(opponent)&& (column + counter) < 8)
            {
                tempBoard[row][column+counter]=myPiece;
                counter++;
            }
        }
        counter = 1;
        if(checker.diagDownRightChecker(tempBoard, row, column,getMin(RxMax,DyMax),turn) == true)
        {
            while(tempBoard[row+counter][column+counter].equals(opponent)&&column<=7&&row<=7)
            {
                tempBoard[row+counter][column+counter]=myPiece;
                counter++;
            }
        }
        counter = 1;
        if(checker.diagUpLeftChecker(tempBoard, row, column,getMin(LxMax,UyMax),turn) == true)
        {

            while(tempBoard[row-counter][column-counter].equals(opponent)&&column>=0&&row>=0)
            {
                tempBoard[row-counter][column-counter]=myPiece;
                counter++;
            }
        }
        counter = 1;
        if(checker.diagUpRightChecker(tempBoard, row, column,getMin(RxMax,UyMax),turn) == true)
        {
            while(tempBoard[row-counter][column+counter].equals(opponent)&&(row>=0&&column<8))
            {
                tempBoard[row-counter][column+counter]=myPiece;
                counter++;
            }
        }
        counter = 1;
        if(checker.diagDownLeftChecker(tempBoard, row, column,getMin(LxMax,DyMax),turn) == true)
        {
            while(tempBoard[row+counter][column-counter].equals(opponent)&&(row<8&&column>=0))
            {
                tempBoard[row+counter][column-counter]=myPiece;
                counter++;
            }
        }

        return tempBoard;
    }

    public int getMin(int x, int y)
    {
        if (x > y)
        {
            return y;
        }
        else
        {
            return x;
        }
    }
    public void copyBoard(String originalBoard [][], String copyBoard [][])
    {
        for (int a = 0; a < 8; a++)
            {
                for (int b = 0; b < 8; b++)
                {
                    copyBoard[a][b] = originalBoard[a][b];
                }
            }
    }
}
