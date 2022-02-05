
/*
 * 

import java.util.*;
public class Stability
{
    private int AIStabilityPoint;
    private int opponentStabilityPoint;
    private String AICoins;
    private String opponentCoins;
    Functions functions;
    FilledRows checkFilled;

    public Stability() 
    {
        functions = new Functions();
        checkFilled = new FilledRows();
    }

    public double initializeStability(String [][] board, int turn)
    {
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
        String [][] tempBoard = new String [8][8];
        copyBoard(board,tempBoard);
        AIStabilityPoint = 0;
        opponentStabilityPoint = 0;

        for (int x  = 0; x < 8; x++)
        {
            for (int y = 0; y < 8; y++)
            {
                if (tempBoard[x][y].equalsIgnoreCase(AICoins) )
                {
                    System.out.println("test");
                    if (getStability(tempBoard, x, y, turn) == true)
                    {
                        AIStabilityPoint++;
                    }
                    else if (getPotStability(tempBoard, x, y, turn) == true)
                    {
                    }
                    else
                    {
                        AIStabilityPoint--;
                    }
                }
                else if (tempBoard[x][y].equalsIgnoreCase(opponentCoins) )
                {
                    if (getStability(tempBoard, x, y, turn+1) == true)
                    {
                        opponentStabilityPoint++;
                    }
                    else if (getPotStability(tempBoard, x, y, turn) == true)
                    {
                    }
                    else
                    {
                        opponentStabilityPoint--;
                    } 
                }
            }
        }

        if (opponentStabilityPoint + AIStabilityPoint != 0)
        {
            return ( 100*(AIStabilityPoint - opponentStabilityPoint)/(AIStabilityPoint + opponentStabilityPoint));
        }
        else
        {
            return 0;
        }
    }

    public boolean getStability(String [][] board, int xPos, int yPos, int turn)
    {

        //The first index is the filled row condition. The second is the adjacent stable piece condition. We can mix and match the two conditions.
        //We can do a sorter, where it will check whether the array have at least one true. If it does, return true for that direction.
        //For a piece to be stable, the 4 directions have to return true.

        //The filled rows are done, and it is a lot easier than the adjacent Stability checker.
        //The stability checker will recursively check for the stability of the adjacent piece. It will go until it meets the above condition of being stable,
        //That piece is will be the corner. It will then return all the values etc...
        boolean [] verticalStabilityChecker = new boolean [3];  //0 is filled rows. 1 is up stable. 2 is down stable.
        boolean [] horizontalStabilityChecker = new boolean [3]; //0 is filled rows. 1 is left stable. 2 is right stabe.
        boolean [] leftRightDiagStabilityChecker = new boolean [3]; //0 is filled rows. 1 is left up stable. 2 is down right stable.
        boolean [] rightLeftDiagStabilityChecker = new boolean [3]; //0 is filled rows. 1 is left down stable. 2 is up right stable.

        String [][] tempBoard = new String [8][8];
        copyBoard(board, tempBoard);

        String coinsChecked = null;

        if (turn %2 == 0)
        {
            coinsChecked = "X";
        }
        else
        {
            coinsChecked ="Y";
        }

        if (checkFilled.filledVertical(tempBoard, xPos, yPos) == true )
        {
            verticalStabilityChecker[0] = true;
        }
        else if (checkFilled.filledVertical(tempBoard, xPos, yPos) == false )
        {
            verticalStabilityChecker[0] = false;
        }

        if (checkFilled.filledHorizontal(tempBoard, xPos, yPos) == true )
        {
            horizontalStabilityChecker[0] = true;
        }
        else if (checkFilled.filledVertical(tempBoard, xPos, yPos) == false )
        {
            horizontalStabilityChecker[0] = false;
        }

        if (checkFilled.filledLRDiagonal(tempBoard, xPos, yPos) == true )
        {
            leftRightDiagStabilityChecker[0] = true;
        }
        else if (checkFilled.filledLRDiagonal(tempBoard, xPos, yPos) == false )
        {
            leftRightDiagStabilityChecker[0] = false;
        }

        if (checkFilled.filledRLDiagonal(tempBoard, xPos, yPos) == true )
        {
            rightLeftDiagStabilityChecker[0] = true;
        }
        else if (checkFilled.filledRLDiagonal(tempBoard, xPos, yPos) == false )
        {
            leftRightDiagStabilityChecker[0] = false;
        }

        if (xPos == 0 &&yPos == 0)
        {
            verticalStabilityChecker[1] = true;
            horizontalStabilityChecker[1] = true;
            leftRightDiagStabilityChecker[1] = true;
            rightLeftDiagStabilityChecker[1] = true;
        }

        else if (xPos == 0 &&yPos == 7)
        {
            verticalStabilityChecker[1] = true;
            horizontalStabilityChecker[2] = true;
            leftRightDiagStabilityChecker[1] = true;
            rightLeftDiagStabilityChecker[2] = true;
        }

        else if (xPos == 7 &&yPos == 7)
        {
            verticalStabilityChecker[2] = true;
            horizontalStabilityChecker[2] = true;
            leftRightDiagStabilityChecker[2] = true;
            rightLeftDiagStabilityChecker[2] = true;
        }

        else if (xPos == 7 &&yPos == 0)
        {
            verticalStabilityChecker[2] = true;
            horizontalStabilityChecker[1] = true;
            leftRightDiagStabilityChecker[2] = true;
            rightLeftDiagStabilityChecker[1] = true;
        }

        if (xPos == 0 || xPos == 7)
        {
            if (xPos == 0)
            {
                verticalStabilityChecker[1] = true;
                leftRightDiagStabilityChecker[1] = true;
                rightLeftDiagStabilityChecker[2] = true;
            }
            else if (xPos == 7)
            {
                verticalStabilityChecker[2] = true;
                leftRightDiagStabilityChecker[2] = true;
                rightLeftDiagStabilityChecker[1] = true;
            }
            if  (yPos < 7 && yPos >0)
            {
                if (!tempBoard[xPos][yPos-1].equalsIgnoreCase(coinsChecked) )
                {
                    horizontalStabilityChecker[1] = false;
                }
                else
                {
                    horizontalStabilityChecker[1] = getStability(tempBoard, xPos, yPos-1, turn);
                }
                if (!tempBoard[xPos][yPos+1].equalsIgnoreCase(coinsChecked) )
                {
                    horizontalStabilityChecker[2] = false;
                }
                else
                {
                    horizontalStabilityChecker[2] = getStability(tempBoard, xPos, yPos+1, turn);
                }
            }
        }

        else if (yPos == 0 || yPos == 7)
        {
            if (yPos == 0)
            {
                horizontalStabilityChecker[1] = true;
                leftRightDiagStabilityChecker[1] = true;
                rightLeftDiagStabilityChecker[1] = true;
            }
            else if (yPos == 7)
            {
                horizontalStabilityChecker[2] = true;
                leftRightDiagStabilityChecker[2] = true;
                rightLeftDiagStabilityChecker[2] = true;
            }

            if (!tempBoard[xPos-1][yPos].equalsIgnoreCase(coinsChecked) )
            {
                verticalStabilityChecker[1] = false;
            }
            else
            {
                verticalStabilityChecker[1] = getStability(tempBoard, xPos-1, yPos, turn);
            }

            if (!tempBoard[xPos+1][yPos].equalsIgnoreCase(coinsChecked) )
            {
                verticalStabilityChecker[2] = false;
            }
            else
            {
                verticalStabilityChecker[2] = getStability(tempBoard, xPos-1, yPos, turn);
            }
        }

        if ( (xPos >= 1 && xPos <= 6) && (yPos>= 1 && yPos <= 6) )
        {
            verticalStabilityChecker[1] = getStability(tempBoard, xPos-1, yPos, turn);
            horizontalStabilityChecker[1] = getStability(tempBoard, xPos, yPos-1, turn);
            leftRightDiagStabilityChecker[1] = getStability(tempBoard, xPos-1, yPos-1, turn);
            rightLeftDiagStabilityChecker[1] = getStability(tempBoard, xPos+1, yPos-1, turn);

            verticalStabilityChecker[2] = getStability(tempBoard, xPos+1, yPos, turn);
            horizontalStabilityChecker[2] = getStability(tempBoard, xPos, yPos+1, turn);
            leftRightDiagStabilityChecker[2] = getStability(tempBoard, xPos+1, yPos+1, turn);
            rightLeftDiagStabilityChecker[2] = getStability(tempBoard, xPos-1, yPos+1, turn);
        }

        if (trueSort(verticalStabilityChecker) == true && trueSort(horizontalStabilityChecker) == true && 
        trueSort(leftRightDiagStabilityChecker) == true && trueSort(rightLeftDiagStabilityChecker) == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean getPotStability(String [][] board, int xPos, int yPos, int turn)
    {

        //The first index is the filled row condition. The second is the adjacent stable piece condition. We can mix and match the two conditions.
        //We can do a sorter, where it will check whether the array have at least one true. If it does, return true for that direction.
        //For a piece to be stable, the 4 directions have to return true.

        //The filled rows are done, and it is a lot easier than the adjacent Stability checker.
        //The stability checker will recursively check for the stability of the adjacent piece. It will go until it meets the above condition of being stable,
        //That piece is will be the corner. It will then return all the values etc...
        boolean [] verticalStabilityChecker = new boolean [3];  //0 is filled rows. 1 is up stable. 2 is down stable.
        boolean [] horizontalStabilityChecker = new boolean [3]; //0 is filled rows. 1 is left stable. 2 is right stabe.
        boolean [] leftRightDiagStabilityChecker = new boolean [3]; //0 is filled rows. 1 is left up stable. 2 is down right stable.
        boolean [] rightLeftDiagStabilityChecker = new boolean [3]; //0 is filled rows. 1 is left down stable. 2 is up right stable.

        String [][] tempBoard = new String [8][8];
        copyBoard(board, tempBoard);
        int stableCount = 0;

        String coinsChecked = null;

        if (turn %2 == 0)
        {
            coinsChecked = "X";
        }
        else
        {
            coinsChecked ="Y";
        }

        if (checkFilled.filledVertical(tempBoard, xPos, yPos) == true )
        {
            verticalStabilityChecker[0] = true;
        }
        else if (checkFilled.filledVertical(tempBoard, xPos, yPos) == false )
        {
            verticalStabilityChecker[0] = false;
        }

        if (checkFilled.filledHorizontal(tempBoard, xPos, yPos) == true )
        {
            horizontalStabilityChecker[0] = true;
        }
        else if (checkFilled.filledVertical(tempBoard, xPos, yPos) == false )
        {
            horizontalStabilityChecker[0] = false;
        }

        if (checkFilled.filledLRDiagonal(tempBoard, xPos, yPos) == true )
        {
            leftRightDiagStabilityChecker[0] = true;
        }
        else if (checkFilled.filledLRDiagonal(tempBoard, xPos, yPos) == false )
        {
            leftRightDiagStabilityChecker[0] = false;
        }

        if (checkFilled.filledRLDiagonal(tempBoard, xPos, yPos) == true )
        {
            rightLeftDiagStabilityChecker[0] = true;
        }
        else if (checkFilled.filledRLDiagonal(tempBoard, xPos, yPos) == false )
        {
            leftRightDiagStabilityChecker[0] = false;
        }

        if (xPos == 0 &&yPos == 0)
        {
            verticalStabilityChecker[1] = true;
            horizontalStabilityChecker[1] = true;
            leftRightDiagStabilityChecker[1] = true;
            rightLeftDiagStabilityChecker[1] = true;
        }

        else if (xPos == 0 &&yPos == 7)
        {
            verticalStabilityChecker[1] = true;
            horizontalStabilityChecker[2] = true;
            leftRightDiagStabilityChecker[1] = true;
            rightLeftDiagStabilityChecker[2] = true;
        }

        else if (xPos == 7 &&yPos == 7)
        {
            verticalStabilityChecker[2] = true;
            horizontalStabilityChecker[2] = true;
            leftRightDiagStabilityChecker[2] = true;
            rightLeftDiagStabilityChecker[2] = true;
        }

        else if (xPos == 7 &&yPos == 0)
        {
            verticalStabilityChecker[2] = true;
            horizontalStabilityChecker[1] = true;
            leftRightDiagStabilityChecker[2] = true;
            rightLeftDiagStabilityChecker[1] = true;
        }

        if (xPos == 0 || xPos == 7)
        {
            if (xPos == 0)
            {
                verticalStabilityChecker[1] = true;
                leftRightDiagStabilityChecker[1] = true;
                rightLeftDiagStabilityChecker[2] = true;
            }
            else if (xPos == 7)
            {
                verticalStabilityChecker[2] = true;
                leftRightDiagStabilityChecker[2] = true;
                rightLeftDiagStabilityChecker[1] = true;
            }

            if (!tempBoard[xPos][yPos-1].equalsIgnoreCase(coinsChecked) )
            {
                horizontalStabilityChecker[1] = false;
            }
            else
            {
                horizontalStabilityChecker[1] = getStability(tempBoard, xPos, yPos-1, turn);
            }

            if (!tempBoard[xPos][yPos+1].equalsIgnoreCase(coinsChecked) )
            {
                horizontalStabilityChecker[2] = false;
            }
            else
            {
                horizontalStabilityChecker[2] = getStability(tempBoard, xPos, yPos+1, turn);
            }
        }

        else if (yPos == 0 || yPos == 7)
        {
            if (yPos == 0)
            {
                horizontalStabilityChecker[1] = true;
                leftRightDiagStabilityChecker[1] = true;
                rightLeftDiagStabilityChecker[1] = true;
            }
            else if (yPos == 7)
            {
                horizontalStabilityChecker[2] = true;
                leftRightDiagStabilityChecker[2] = true;
                rightLeftDiagStabilityChecker[2] = true;
            }

            if (!tempBoard[xPos-1][yPos].equalsIgnoreCase(coinsChecked) )
            {
                verticalStabilityChecker[1] = false;
            }
            else
            {
                verticalStabilityChecker[1] = getStability(tempBoard, xPos-1, yPos, turn);
            }

            if (!tempBoard[xPos+1][yPos].equalsIgnoreCase(coinsChecked) )
            {
                verticalStabilityChecker[2] = false;
            }
            else
            {
                verticalStabilityChecker[2] = getStability(tempBoard, xPos-1, yPos, turn);
            }
        }

        if ( (xPos >= 1 && xPos <= 6) && (yPos>= 1 && yPos <= 6) )
        {
            verticalStabilityChecker[1] = getStability(tempBoard, xPos-1, yPos, turn);
            horizontalStabilityChecker[1] = getStability(tempBoard, xPos, yPos-1, turn);
            leftRightDiagStabilityChecker[1] = getStability(tempBoard, xPos-1, yPos-1, turn);
            rightLeftDiagStabilityChecker[1] = getStability(tempBoard, xPos+1, yPos-1, turn);

            verticalStabilityChecker[2] = getStability(tempBoard, xPos+1, yPos, turn);
            horizontalStabilityChecker[2] = getStability(tempBoard, xPos, yPos+1, turn);
            leftRightDiagStabilityChecker[2] = getStability(tempBoard, xPos+1, yPos+1, turn);
            rightLeftDiagStabilityChecker[2] = getStability(tempBoard, xPos-1, yPos+1, turn);
        }

        if (trueSort(verticalStabilityChecker) == true)
        {
            stableCount++;
        }

        if (trueSort(horizontalStabilityChecker) == true)
        {
            stableCount++;
        }

        if (trueSort(leftRightDiagStabilityChecker) == true)
        {
            stableCount++;
        }

        if (trueSort(rightLeftDiagStabilityChecker) == true)
        {
            stableCount++;
        }

        if (stableCount >1 && stableCount<4)
        {
            return true;
        }
        else
        {
            return false;
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

    public boolean trueSort(boolean [] array)
    {
        for (int x = 0; x < array.length; x++)
        {
            if (array[x] == true)
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

 */
