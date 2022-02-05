
public class Corners
{
    private int AICorners;
    private int opponentCorners;

    private String AICoins;
    private String opponentCoins;

    private double cornerEval;

    public Corners()
    {
        AICorners = 0;
        opponentCorners = 0;
        AICoins = null;
        opponentCoins = null;
        cornerEval = 0;
    }

    public double getCorners(String [][] board, int move)
    {
        if (move%2 == 0)
        {
            AICoins = "X";
            opponentCoins = "Y";
        }
        else if (move%2 == 1)
        {
            AICoins = "Y";
            opponentCoins = "X";
        }

        if (board[0][0].equalsIgnoreCase(AICoins) )
        {
            AICorners++;
        }
        else if (board[0][0].equalsIgnoreCase(opponentCoins) )
        {
            opponentCorners++;
        }

        if (board[0][7].equalsIgnoreCase(AICoins) )
        {
            AICorners++;
        }
        else if (board[0][7].equalsIgnoreCase(opponentCoins) )
        {
            opponentCorners++;
        }

        if (board[7][0].equalsIgnoreCase(AICoins) )
        {
            AICorners++;
        }
        else if (board[7][0].equalsIgnoreCase(opponentCoins) )
        {
            opponentCorners++;
        }

        if (board[7][7].equalsIgnoreCase(AICoins) )
        {
            AICorners++;
        }
        else if (board[7][7].equalsIgnoreCase(opponentCoins) )
        {
            opponentCorners++;
        }

        if ((AICorners + opponentCorners) != 0)
        {
            cornerEval = 100* (AICorners-opponentCorners)/(AICorners + opponentCorners);
            return cornerEval;
        }
        else 
        {
            cornerEval = 0;
            return cornerEval;
        }
               
    }
}
