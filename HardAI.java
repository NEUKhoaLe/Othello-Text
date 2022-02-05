
import java.util.*;

public class HardAI
{
    FunctionsFlipper flipper;
    FunctionsChecker checker;
    Functions functions;
    coinParity coinParity;
    Stability stability;
    Corners corners;
    Mobility mobility;
    CornerOpeness cornerOpeness;
    PlayMove playMove;

    public HardAI()
    {
        cornerOpeness = new CornerOpeness();
        mobility = new Mobility();
        corners = new Corners();
        stability = new Stability();
        coinParity = new coinParity();
        functions = new Functions();
        flipper = new FunctionsFlipper();
        checker = new FunctionsChecker();
        playMove = new PlayMove();
    }

    public String [][] makeMove(String [][] board, int turn)
    {
        String Piece = null;
        int currentTurn;
        currentTurn = turn;
        double staticBoardEval = 0;
        double maxBoardEval = 99999999;

        String [][] tempBoard = new String[8][8];
        copyBoard(board, tempBoard);

        ArrayList <Integer> possibleXPos = new ArrayList<>();
        ArrayList <Integer> possibleYPos = new ArrayList<>();

        int xMove = 0;
        int yMove = 0;

        if (currentTurn%2 == 0)
        {
            Piece = "X";
        }
        else
        {
            Piece = "Y";
        }

        for (int x = 0 ; x < 8; x++)
        {
            for (int y = 0; y < 8; y++)
            {
                if (functions.legalMoveChecker(tempBoard,x, y, currentTurn) == true)
                {
                    possibleXPos.add(x);
                    possibleYPos.add(y);
                }
            }
        }

        for (int x = 0; x < possibleXPos.size(); x++)
        {
            tempBoard = new String[8][8];
            copyBoard(board, tempBoard);

            tempBoard[possibleXPos.get(x)][possibleYPos.get(x)] = Piece;
            tempBoard = flipper.changePieces(tempBoard, possibleXPos.get(x), possibleYPos.get(x),currentTurn);
            staticBoardEval = miniMaxAlgorithm(tempBoard, false,13, Double.MAX_VALUE,
                Double.MIN_VALUE, currentTurn+1);

            if (staticBoardEval <= maxBoardEval)
            {
                maxBoardEval = staticBoardEval;
                xMove = possibleXPos.get(x);
                yMove = possibleYPos.get(x);
            }
        }

        tempBoard = new String [8][8];
        copyBoard(board, tempBoard);

        tempBoard[xMove][yMove] = Piece;
        tempBoard = flipper.changePieces(tempBoard, xMove, yMove,currentTurn);
        return tempBoard;
    }

    public double miniMaxAlgorithm(String [][] board,boolean maxPlayer, 
    int depth, double alpha, double beta, int turn)
    {
        String Piece = null;
        int currentTurn;
        currentTurn = turn;

        if (currentTurn%2 == 0)
        {
            Piece = "X";
        }
        else
        {
            Piece = "Y";
        }

        if (depth == 1 || gameOver(board, turn) == true )
        {
            System.out.println(getHeuristics(board,turn));
            return getHeuristics(board, turn);
        }

        String [][] tempBoard;
        String [][] playBoard;
        tempBoard = new String [8][8];
        copyBoard(board, tempBoard);

        ArrayList <Integer> possibleXPos = new ArrayList<>();
        ArrayList <Integer> possibleYPos = new ArrayList<>();

        for (int x = 0 ; x < 8; x++)
        {
            for (int y = 0; y < 8; y++)
            {
                if (functions.legalMoveChecker(tempBoard,x, y, turn) == true)
                {
                    possibleXPos.add(x);
                    possibleYPos.add(y);
                }
            }
        }

        if (maxPlayer == true)
        {
            double maxValue = -999999999;
            double minValue = 999999999;

            for (int x = 0; x < possibleXPos.size(); x++)
            {
                playBoard = new String [8][8];
                copyBoard(tempBoard,playBoard);
                playBoard[possibleXPos.get(x)][possibleYPos.get(x)] = Piece;
                playBoard = flipper.changePieces(playBoard, possibleXPos.get(x), possibleYPos.get(x),turn);
                double eval = miniMaxAlgorithm(playBoard, false, depth-1,
                        alpha, beta,currentTurn+1);

                maxValue = getMax(maxValue,eval);

                alpha = getMax(alpha, eval);

                if (beta <= alpha)
                {
                    break;
                }
            }

            return maxValue;

        }
        else
        {
            double maxValue = -999999999;
            double minValue = 999999999;

            for (int x = 0; x < possibleXPos.size(); x++)
            {
                playBoard = new String [8][8];
                copyBoard(tempBoard,playBoard);

                playBoard[possibleXPos.get(x)][possibleYPos.get(x)] = Piece;
                playBoard = flipper.changePieces(playBoard, possibleXPos.get(x), possibleYPos.get(x),turn);
                double eval = miniMaxAlgorithm(playBoard, true, depth-1,
                        alpha, beta,currentTurn+1);

                minValue = getMin(minValue,eval);

                beta = getMin(beta, eval);

                if (beta <= alpha)
                {
                    break;
                }
            }                
            return minValue;                
        }
    }

    public double getHeuristics(String [][] board, int turn)
    {
        String [][] tempBoard = new String [8][8];
        copyBoard(board, tempBoard);
        double totalHeuristics = 0;
        if (turn <=8)
        {
            totalHeuristics = 100*stability.initializeStability(tempBoard,turn) + 70*mobility.getMobility(tempBoard,turn)
            +150*corners.getCorners(tempBoard, turn)+10*coinParity.getParity(tempBoard,turn)+200*cornerOpeness.getCornerOpenessEval(tempBoard,turn);
        }

        else if (turn <=30)
        {
            totalHeuristics = 200*stability.initializeStability(tempBoard,turn) + 30 *mobility.getMobility(tempBoard,turn)
            +600*corners.getCorners(tempBoard, turn)+15*coinParity.getParity(tempBoard,turn)+200*cornerOpeness.getCornerOpenessEval(tempBoard,turn);
        }

        else if (turn <=52)
        {
            totalHeuristics = 200*stability.initializeStability(tempBoard,turn) + 50*mobility.getMobility(tempBoard,turn)
            +800*corners.getCorners(tempBoard, turn)+20*coinParity.getParity(tempBoard,turn)+200*cornerOpeness.getCornerOpenessEval(tempBoard,turn);;
        }
        else if (turn <= 60)
        {
            totalHeuristics = 1000*coinParity.getParity(tempBoard,turn);
        }

        return totalHeuristics;
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

    public double getMax(double x, double y)
    {
        if (x >= y)
        {
            return x;
        }
        else
        {
            return y; 
        }
    }

    public double getMin(double x, double y)
    {
        if (x <= y)
        {
            return x;
        }
        else
        {
            return y; 
        }
    }

    public boolean gameOver(String [][] board, int turn)
    {
        for (int x = 0; x <60-turn; x++)
        {

            for (int y = 0; y < 8; y++)
            {
                for (int z = 0; z < 8; z++)
                {
                    if (functions.legalMoveChecker(board, y, z, turn+x) == true)
                    {
                        return false;
                    }
                    else
                    {
                    }
                }
            }
            x++;
        }
        return true;
    }
}
