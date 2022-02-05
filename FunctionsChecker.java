public class FunctionsChecker
{
    public FunctionsChecker()
    {
    }

    public boolean verticalUpChecker(String board [][], int xpos, int ypos, int UyMax, int turn)
    {
        boolean theirPiece = false;
        String myPiece;
        String opponent;
        int z;

        if (turn%2 == 0)
        {
            myPiece = "X";
            opponent = "Y";
        }
        else 
        {
            myPiece = "Y";
            opponent = "X";
        }

        z = 0;
        theirPiece = false;
        for (int y = 0; y < UyMax; y++)
        {
            z++;
            if (board[xpos-z][ypos].equalsIgnoreCase(myPiece) && theirPiece == true)
            {
                return true;
            }
            else if (board[xpos-z][ypos].equalsIgnoreCase(opponent) && theirPiece == false)
            {
                theirPiece = true;
            }
            else if (board[xpos-z][ypos].equalsIgnoreCase(opponent) )
            {
                theirPiece = true;
            }
            else
            {
                y = UyMax;
            }   
        }
        return false;
    }

    public boolean verticalDownChecker(String board [][], int xpos, int ypos, int DyMax, int turn)
    {
        boolean theirPiece = false;
        String myPiece;
        String opponent;
        int z;

        if (turn%2 == 0)
        {
            myPiece = "X";
            opponent = "Y";
        }
        else 
        {
            myPiece = "Y";
            opponent = "X";
        }

        z = 0;
        theirPiece = false;
        for (int y = 0; y < DyMax; y++)
        {
            z++;
            if (board[xpos+z][ypos].equalsIgnoreCase(myPiece) && theirPiece == true)
            {
                return true;
            }
            else if (board[xpos+z][ypos].equalsIgnoreCase(opponent) && theirPiece == false)
            {
                theirPiece = true;
            }
            else if (board[xpos+z][ypos].equalsIgnoreCase(opponent) )
            {
                theirPiece = true;
            }
            else
            {
                y = DyMax;
            }   
        }
        return false;
    }

    public boolean horizLeftChecker(String board [][], int xpos, int ypos, int LxMax, int turn)
    {
        boolean theirPiece = false;
        String myPiece;
        String opponent;
        int z;

        if (turn%2 == 0)
        {
            myPiece = "X";
            opponent = "Y";
        }
        else 
        {
            myPiece = "Y";
            opponent = "X";
        }

        z = 0;
        theirPiece = false;
        for (int y = 0; y < LxMax; y++)
        {
            z++;
            if (board[xpos][ypos-z].equalsIgnoreCase(myPiece) && theirPiece == true)
            {
                return true;
            }
            else if (board[xpos][ypos-z].equalsIgnoreCase(opponent) && theirPiece == false)
            {
                theirPiece = true;
            }
            else if (board[xpos][ypos-z].equalsIgnoreCase(opponent) )
            {
                theirPiece = true;
            }
            else
            {
                y = LxMax;
            }   
        }
        return false;
    }

    public boolean horizRightChecker(String board [][], int xpos, int ypos, int RxMax, int turn)
    {
        boolean theirPiece = false;
        String myPiece;
        String opponent;
        int z;

        if (turn%2 == 0)
        {
            myPiece = "X";
            opponent = "Y";
        }
        else 
        {
            myPiece = "Y";
            opponent = "X";
        }

        z = 0;
        theirPiece = false;
        for (int y = 0; y < RxMax; y++)
        {
            z++;
            if (board[xpos][ypos+z].equalsIgnoreCase(myPiece) && theirPiece == true)
            {
                return true;
            }
            else if (board[xpos][ypos+z].equalsIgnoreCase(opponent) && theirPiece == false)
            {
                theirPiece = true;
            }
            else if (board[xpos][ypos+z].equalsIgnoreCase(opponent) )
            {
                theirPiece = true;
            }
            else
            {
                y = RxMax;
            }   
        }
        return false;
    }

    public boolean diagUpLeftChecker(String board [][], int xpos, int ypos, int LUDiag, int turn)
    {
        boolean theirPiece = false;
        String myPiece;
        String opponent;
        int z;

        if (turn%2 == 0)
        {
            myPiece = "X";
            opponent = "Y";
        }
        else 
        {
            myPiece = "Y";
            opponent = "X";
        }

        z = 0;
        theirPiece = false;
        for (int y = 0; y < LUDiag; y++)
        {
            z++;
            if (board[xpos-z][ypos-z].equalsIgnoreCase(myPiece) && theirPiece == true)
            {
                return true;
            }
            else if (board[xpos-z][ypos-z].equalsIgnoreCase(opponent) && theirPiece == false)
            {
                theirPiece = true;
            }
            else if (board[xpos-z][ypos-z].equalsIgnoreCase(opponent) )
            {
                theirPiece = true;
            }
            else
            {
                y = LUDiag;
            }   
        }
        return false;
    }

    public boolean diagUpRightChecker(String board [][], int xpos, int ypos, int RUDiag, int turn)
    {
        boolean theirPiece = false;
        String myPiece;
        String opponent;
        int z;

        if (turn%2 == 0)
        {
            myPiece = "X";
            opponent = "Y";
        }
        else 
        {
            myPiece = "Y";
            opponent = "X";
        }

        z = 0;
        theirPiece = false;
        for (int y = 0; y < RUDiag; y++)
        {
            z++;
            if (board[xpos-z][ypos+z].equalsIgnoreCase(myPiece) && theirPiece == true)
            {
                return true;
            }
            else if (board[xpos-z][ypos+z].equalsIgnoreCase(opponent) && theirPiece == false)
            {
                theirPiece = true;
            }
            else if (board[xpos-z][ypos+z].equalsIgnoreCase(opponent) )
            {
                theirPiece = true;
            }
            else
            {
                y = RUDiag;
            }   
        }
        return false;
    }

    public boolean diagDownLeftChecker(String board [][], int xpos, int ypos, int LDDiag, int turn)
    {
        boolean theirPiece = false;
        String myPiece;
        String opponent;
        int z;

        if (turn%2 == 0)
        {
            myPiece = "X";
            opponent = "Y";
        }
        else 
        {
            myPiece = "Y";
            opponent = "X";
        }

        z = 0;
        theirPiece = false;
        for (int y = 0; y < LDDiag; y++)
        {
            z++;
            if (board[xpos+z][ypos-z].equalsIgnoreCase(myPiece) && theirPiece == true)
            {
                return true;
            }
            else if (board[xpos+z][ypos-z].equalsIgnoreCase(opponent) && theirPiece == false)
            {
                theirPiece = true;
            }
            else if (board[xpos+z][ypos-z].equalsIgnoreCase(opponent) )
            {
                theirPiece = true;
            }
            else
            {
                y = LDDiag;
            }   
        }
        return false;
    }

    public boolean diagDownRightChecker(String board [][], int xpos, int ypos, int RDDiag, int turn)
    {
        boolean theirPiece = false;
        String myPiece;
        String opponent;
        int z;

        if (turn%2 == 0)
        {
            myPiece = "X";
            opponent = "Y";
        }
        else 
        {
            myPiece = "Y";
            opponent = "X";
        }

        z = 0;
        theirPiece = false;
        for (int y = 0; y < RDDiag; y++)
        {
            z++;
            if (board[xpos+z][ypos+z].equalsIgnoreCase(myPiece) && theirPiece == true)
            {
                return true;
            }
            else if (board[xpos+z][ypos+z].equalsIgnoreCase(opponent) && theirPiece == false)
            {
                theirPiece = true;
            }
            else if (board[xpos+z][ypos+z].equalsIgnoreCase(opponent) )
            {
                theirPiece = true;
            }
            else
            {
                y = RDDiag;
            }   
        }
        return false;
    }

    public boolean getSorter(boolean board[])
    {
        int counter = 0;
        for (int x = 0; x < 8; x++)
        {
            if (board[x] == true)
            {
                return true;
            }
            else
            {
            }
        }
        return false;
    }
}