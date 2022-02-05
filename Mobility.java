
import java.util.*;
public class Mobility
{
    private FunctionsFlipper flipper = new FunctionsFlipper();
    private FunctionsChecker checker = new FunctionsChecker();
    private Functions functions = new Functions();
    private PlayMove getMove = new PlayMove();

    private int AIPossibleMoves;
    private int opponentPossibleMoves;

    private int AIPossiblePotMoves;
    private int opponentPossiblePotMoves;

    private double mobilityEval;
    private String AICoins;
    private String opponentCoins;
    private String [][] tempBoard;
    private String [][] tempBoard1;
    private int maxAIPotMove;
    private int maxOpponentPotMove;

    public Mobility()
    {
    }

    public double getMobility(String [][] board, int move)
    {
        if (move%2 == 0)
        {
            AICoins = "X";
            opponentCoins = "Y";
        }
        else
        {
            AICoins = "Y";
            opponentCoins = "X";
        }

        AIPossibleMoves = 0;
        opponentPossibleMoves = 0;
        mobilityEval = 0;
        AIPossiblePotMoves = 0;
        opponentPossiblePotMoves = 0;
        tempBoard = null;
        tempBoard1 = null;
        maxAIPotMove = 0;
        maxOpponentPotMove = 0;

        ArrayList<Integer> possibleAIXPos = new ArrayList<>();
        ArrayList<Integer> possibleAIYPos = new ArrayList<>();

        ArrayList<Integer> possibleOpponentXPos = new ArrayList<>();
        ArrayList<Integer> possibleOpponentYPos = new ArrayList<>();

        ArrayList<Integer> possibleOpponentXPos1 = new ArrayList<>();
        ArrayList<Integer> possibleOpponentYPos1 = new ArrayList<>();

        for (int x = 0; x < 8; x++)
        {
            for (int y = 0; y < 8; y++)
            {
                if (functions.legalMoveChecker(board, x, y, move) == true)
                {
                    AIPossibleMoves++;

                }
                else
                {
                }
            }
        }

        for (int x = 0; x < 8; x++)
        {
            for (int y = 0; y < 8; y++)
            {
                if (functions.legalMoveChecker(board, x, y, move+1) == true)
                {
                    opponentPossibleMoves++;
                }
                else
                {
                }
            }
        }
        if ( AIPossibleMoves + opponentPossibleMoves == 0)
        {
            return 0;
        }
        else
        {
            return 100*(AIPossibleMoves - opponentPossibleMoves)/(AIPossibleMoves+opponentPossibleMoves) ;

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
