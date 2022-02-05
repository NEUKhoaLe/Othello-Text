
public class FilledRows
{
    public FilledRows()
    {
    }

    public boolean filledVertical(String [][] board, int xPos, int yPos)
    {
        String [][] tempBoard = new String [8][8];
        copyBoard(board, tempBoard);

        int upV = xPos;
        int downV = 7-xPos;

        int z = 0;
        for (int x = 0; x <upV; x++)
        {
            z++;
            if (tempBoard[xPos-z][yPos].equalsIgnoreCase("-") )
            {
                return false;
            }
        }
        z = 0;
        for (int x = 0; x < downV; x++)
        {
            z++;
            if (tempBoard[xPos+z][yPos].equalsIgnoreCase("-") )
            {
                return false;
            }
        }
        return true;
    }

    public boolean filledHorizontal(String [][] board, int xPos, int yPos)
    {
        String [][] tempBoard = new String [8][8];
        copyBoard(board, tempBoard);

        int leftH = yPos;
        int rightH = 7-yPos;

        int z = 0;
        for (int x = 0; x <leftH; x++)
        {
            z++;
            if (tempBoard[xPos][yPos-z].equalsIgnoreCase("-") )
            {
                return false;
            }
        }
        z = 0;
        for (int x = 0; x < rightH; x++)
        {
            z++;
            if (tempBoard[xPos][yPos+z].equalsIgnoreCase("-") )
            {
                return false;
            }
        }
        return true;
    }

    public boolean filledLRDiagonal(String [][] board, int xPos, int yPos)
    {
        String [][] tempBoard = new String [8][8];
        copyBoard(board, tempBoard);

        int upV = xPos;
        int downV = 7-xPos;
        int leftH = yPos;
        int rightH = 7-yPos;

        int LRUpMax = getMin(upV,leftH);
        int LRDownMax = getMin(downV,rightH);

        int z = 0;
        for (int x = 0; x < LRUpMax; x++)
        {
            z++;
            if (tempBoard[xPos-z][yPos-z].equalsIgnoreCase("-") )
            {
                return false;
            }
        }
        z = 0;
        for (int x = 0; x < LRDownMax; x++)
        {
            z++;
            if (tempBoard[xPos+z][yPos+z].equalsIgnoreCase("-") )
            {
                return false;
            }
        }
        return true;
    }

    public boolean filledRLDiagonal(String [][] board, int xPos, int yPos)
    {
        String [][] tempBoard = new String [8][8];
        copyBoard(board, tempBoard);

        int upV = xPos;
        int downV = 7-xPos;
        int leftH = yPos;
        int rightH = 7-yPos;

        int RLUpMax = getMin(upV,rightH);
        int RLDownMax = getMin(downV,leftH);

        int z = 0;
        for (int x = 0; x < RLUpMax; x++)
        {
            z++;
            if (tempBoard[xPos-z][yPos+z].equalsIgnoreCase("-") )
            {
                return false;
            }
        }
        z = 0;
        for (int x = 0; x < RLDownMax; x++)
        {
            z++;
            if (tempBoard[xPos+z][yPos-z].equalsIgnoreCase("-") )
            {
                return false;
            }
        }
        return true;
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

    public int getMin (int x, int y)
    {
        if (x >= y)
        {
            return y;
        }
        else
        {
            return x;
        }
    }
}