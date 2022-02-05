
public class PlayMove
{
    public PlayMove()
    {
    }

    public String [][] playBoard(String [][] board, int xpos, int ypos, int turn)
    {
        String [][] tempBoard = null;
        copyBoard(board, tempBoard);
        if (turn%2 == 0)
        {
            tempBoard[xpos][ypos] = "X";
        }
        else if(turn%2 == 1)
        {
            tempBoard[xpos][ypos] = "Y";
        }
        
        return tempBoard;
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
