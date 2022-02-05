import java.util.*;
/**
 * Write a description of class AI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class easyAI
{
    private FunctionsChecker functions;
    private FunctionsFlipper flipper;
    private Functions functionss;
    /**
     * Constructor for objects of class AI
     */
    public easyAI()
    {
        functions= new FunctionsChecker();
        flipper= new FunctionsFlipper();
        functionss= new Functions();
    }

    public String[][] computerMove(String [][]currentPosition, int turn)
    {
        int rowMove =0;
        int columnMove=0;
        int AICount = 0;
        int opponentCount = 0;
        int MaxPiece = 0;
        String AICoins = null;
        String opponentCoins = null;

        ArrayList<Integer> xPos = new ArrayList<>();
        ArrayList<Integer> yPos = new ArrayList<>();

        if (turn%2 == 0)
        {
            AICoins = "X";
            opponentCoins = "Y";
        }
        else
        {
            AICoins = "Y";
            opponentCoins = "X";
        }

        for (int x = 0; x < 8; x ++)
        {
            for (int y = 0; y < 8; y++)
            {
                if (functionss.legalMoveChecker(currentPosition,x, y,turn)==true)
                {
                    xPos.add(x);
                    yPos.add(y);
                }
            }
        }

        for (int x = 0; x < xPos.size(); x++)
        {
            String [][] tempBoard = new String [8][8];

            copyBoard(currentPosition, tempBoard);

            tempBoard[xPos.get(x)][yPos.get(x)] = AICoins;

            tempBoard = flipper.changePieces(tempBoard,xPos.get(x),yPos.get(x),turn);

            for (int a = 0; a < 8; a++)
            {
                for (int b = 0; b < 8; b++)
                {
                    if (tempBoard[a][b].equalsIgnoreCase(AICoins) )
                    {
                        if (a == 0 && b == 0)
                        {
                            AICount += 25;
                        }
                        else if (a == 0 && b == 7)
                        {
                            AICount += 25;
                        }
                        else if (a == 7 && b == 7)
                        {
                            AICount += 25;
                        }
                        else if (a == 7 && b == 0)
                        {
                            AICount += 25;
                        }
                        else if (a == 1 && (b == 0 || b == 1))
                        {
                            AICount -= 10;
                        }

                        else if (a == 0 && b == 1)
                        {
                            AICount -= 10;
                        }
                        else if (a == 0 && b == 7)
                        {
                            AICount -= 10;
                        }
                        else if (a == 1 && b == 7)
                        {
                            AICount -= 10;
                        }
                        else if (a == 1 && b == 6)
                        {
                            AICount -= 10;
                        }
                        else if (a == 7 && b == 0)
                        {
                            AICount -= 10;
                        }
                        else if (a == 7 && b == 1)
                        {
                            AICount -= 10;
                        }
                        else if (a == 6 && b == 1)
                        {
                            AICount -= 10;
                        }
                        else if (a == 6 && b == 6)
                        {
                            AICount -= 10;
                        }
                        else if (a == 7 && b == 6)
                        {
                            AICount -= 10;
                        }
                        else if (a == 6 && b == 7)
                        {
                            AICount -= 10;
                        }
                        else if (a == 2 && (b >=0 && b <= 2) )
                        {
                            AICount += 8;
                        }
                        else if (a == 2 && (b >=5 && b <= 7) )
                        {
                            AICount += 8;
                        }
                        else if (b == 2 && (a >=0 && a <= 2) )
                        {
                            AICount += 8;
                        }
                        else if (b == 2 && (a >=5 && a <= 7) )
                        {
                            AICount += 8;
                        }
                        else
                        {
                            AICount++;
                        }
                    }
                    else if (tempBoard[a][b].equalsIgnoreCase(opponentCoins) )
                    {
                        if (a == 0 && b == 0)
                        {
                            opponentCount += 25;
                        }
                        else if (a == 0 && b == 7)
                        {
                            opponentCount += 25;
                        }
                        else if (a == 7 && b == 7)
                        {
                            opponentCount += 25;
                        }
                        else if (a == 7 && b == 0)
                        {
                            opponentCount += 25;
                        }
                        else if (a == 1 && b == 0)
                        {
                            opponentCount -= 10;
                        }
                        else if (a == 1 && b == 1)
                        {
                            opponentCount -= 10;
                        }
                        else if (a == 0 && b == 1)
                        {
                            opponentCount -= 10;
                        }
                        else if (a == 0 && b == 7)
                        {
                            opponentCount -= 10;
                        }
                        else if (a == 1 && b == 7)
                        {
                            opponentCount -= 10;
                        }
                        else if (a == 1 && b == 6)
                        {
                            opponentCount -= 10;
                        }
                        else if (a == 7 && b == 0)
                        {
                            opponentCount -= 10;
                        }
                        else if (a == 7 && b == 1)
                        {
                            opponentCount -= 10;
                        }
                        else if (a == 6 && b == 1)
                        {
                            opponentCount -= 10;
                        }
                        else if (a == 6 && b == 6)
                        {
                            opponentCount -= 10;
                        }
                        else if (a == 7 && b == 6)
                        {
                            opponentCount -= 10;
                        }
                        else if (a == 6 && b == 7)
                        {
                            opponentCount -= 10;
                        }
                        else if (a == 2 && (b >=0 && b <= 2) )
                        {
                            AICount += 8;
                        }
                        else if (a == 2 && (b >=5 && b <= 7) )
                        {
                            AICount += 8;
                        }
                        else if (b == 2 && (a >=0 && a <= 2) )
                        {
                            AICount += 8;
                        }
                        else if (b == 2 && (a >=5 && a <= 7) )
                        {
                            AICount += 8;
                        }
                        else
                        {
                            opponentCount++;
                        }
                    }
                    else
                    {
                    }
                }
            }

            if ((AICount-opponentCount) >= MaxPiece)
            {
                MaxPiece = AICount - opponentCount;
                rowMove = xPos.get(x);
                columnMove = yPos.get(x);
            }
        }

        currentPosition[rowMove][columnMove] = AICoins;
        currentPosition = flipper.changePieces(currentPosition, rowMove, columnMove, turn);

        return currentPosition;
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
