
public class CornerOpeness
{
    private int AIAdjacentCorners;
    private int opponentAdjacentCorners;

    private String AICoins;
    private String opponentCoins;

    private double adjacentEval;

    public CornerOpeness()
    {
        AIAdjacentCorners = 0;
        opponentAdjacentCorners = 0;
        AICoins = null;
        opponentCoins = null;
        adjacentEval = 0;
    }

    public double getCornerOpenessEval(String [][] board, int turn)
    {
        if (turn%2 == 0)
        {
            AICoins = "X";
            opponentCoins = "Y";
        }
        else if (turn%2 == 1)
        {
            AICoins = "Y";
            opponentCoins = "X";
        }

        if (board[0][0].equalsIgnoreCase("-") )
        {
            if (board[1][0].equalsIgnoreCase(AICoins) )
            {
                AIAdjacentCorners++;
            }
            else if (board[1][0].equalsIgnoreCase(opponentCoins) )
            {
                opponentAdjacentCorners++;
            }

            if (board[1][1].equalsIgnoreCase(AICoins) )
            {
                AIAdjacentCorners++;
            }
            else if (board[1][1].equalsIgnoreCase(opponentCoins) )
            {
                opponentAdjacentCorners++;
            }

            if (board[0][1].equalsIgnoreCase(AICoins) )
            {
                AIAdjacentCorners++;
            }
            else if (board[0][1].equalsIgnoreCase(opponentCoins) )
            {
                opponentAdjacentCorners++;
            }
        }

        if (board[0][7].equalsIgnoreCase("-") )
        {
            if (board[0][6].equalsIgnoreCase(AICoins) )
            {
                AIAdjacentCorners++;
            }
            else if (board[0][6].equalsIgnoreCase(opponentCoins) )
            {
                opponentAdjacentCorners++;
            }

            if (board[1][6].equalsIgnoreCase(AICoins) )
            {
                AIAdjacentCorners++;
            }
            else if (board[1][6].equalsIgnoreCase(opponentCoins) )
            {
                opponentAdjacentCorners++;
            }

            if (board[1][7].equalsIgnoreCase(AICoins) )
            {
                AIAdjacentCorners++;
            }
            else if (board[1][7].equalsIgnoreCase(opponentCoins) )
            {
                opponentAdjacentCorners++;
            }
        }

        if (board[7][0].equalsIgnoreCase("-") )
        {
            if (board[6][0].equalsIgnoreCase(AICoins) )
            {
                AIAdjacentCorners++;
            }
            else if (board[6][0].equalsIgnoreCase(opponentCoins) )
            {
                opponentAdjacentCorners++;
            }

            if (board[6][1].equalsIgnoreCase(AICoins) )
            {
                AIAdjacentCorners++;
            }
            else if (board[6][1].equalsIgnoreCase(opponentCoins) )
            {
                opponentAdjacentCorners++;
            }

            if (board[7][1].equalsIgnoreCase(AICoins) )
            {
                AIAdjacentCorners++;
            }
            else if (board[7][1].equalsIgnoreCase(opponentCoins) )
            {
                opponentAdjacentCorners++;
            }
        }

        if (board[7][7].equalsIgnoreCase("-") )
        {
            if (board[6][7].equalsIgnoreCase(AICoins) )
            {
                AIAdjacentCorners++;
            }
            else if (board[6][7].equalsIgnoreCase(opponentCoins) )
            {
                opponentAdjacentCorners++;
            }

            if (board[6][6].equalsIgnoreCase(AICoins) )
            {
                AIAdjacentCorners++;
            }
            else if(board[6][6].equalsIgnoreCase(opponentCoins) )
            {
                opponentAdjacentCorners++;
            }

            if (board[7][6].equalsIgnoreCase(AICoins) )
            {
                AIAdjacentCorners++;
            }
            else if (board[7][6].equalsIgnoreCase(opponentCoins) )
            {
                opponentAdjacentCorners++;
            }
        }

        if (AIAdjacentCorners + opponentAdjacentCorners != 0)
        { 
            adjacentEval = -100*(AIAdjacentCorners - opponentAdjacentCorners)/(AIAdjacentCorners + opponentAdjacentCorners);
            return adjacentEval;
        }
        else 
            return 0;
    }
}
