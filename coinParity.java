
public class coinParity
{
    private int opponentCoins;
    private int AICoins;

    private String opponentPiece;
    private String AIPiece;

    private double parityEval;
    public coinParity()
    {
        opponentCoins = 0;
        AICoins = 0;
        opponentPiece = null;
        AIPiece = null;
        parityEval = 0;
    }

    public double getParity(String [][] board, int move)
    {
        if (move%2 == 0)
        {
            AIPiece = "X";
            opponentPiece = "Y";
        }
        else if (move%2 == 0)
        {
            AIPiece = "Y";
            opponentPiece = "X";
        }

        for (int x = 0; x < 8; x++)
        {
            for (int y = 0; y < 8; y++)
            {
                if (board[x][y].equalsIgnoreCase(opponentPiece) )
                {
                    opponentCoins++;
                }
                else if (board[x][y].equalsIgnoreCase(AIPiece) )
                {
                    AICoins++;
                }
            }
        }
        
        if ( (AICoins + opponentCoins) != 0)
        {
            parityEval = 100 * (AICoins - opponentCoins)/(AICoins + opponentCoins);
            return parityEval;
        }
        else
        {
            return 0;
        }
    }
}
