
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

        ArrayList<Integer> constXPos;
        ArrayList<Integer> constYPos;

        for (int x  = 0; x < 8; x++)
        {
            for (int y = 0; y < 8; y++)
            {
                if (tempBoard[x][y].equalsIgnoreCase(AICoins) )
                {
                    constXPos = new ArrayList<>();
                    constYPos = new ArrayList<>();
                    constXPos.add(x);
                    constYPos.add(y);

                    if (getStability(tempBoard, x, y,constXPos,constYPos, turn) == true)
                    {
                        AIStabilityPoint++;
                    }
                    else if (getPotStability(tempBoard, x, y,constXPos,constYPos, turn) == true)
                    {
                    }
                    else
                    {
                        AIStabilityPoint--;
                    }
                }
                else if (tempBoard[x][y].equalsIgnoreCase(opponentCoins) )
                {
                    constXPos = new ArrayList<>();
                    constYPos = new ArrayList<>();
                    constXPos.add(x);
                    constYPos.add(y);
                    if (getStability(tempBoard, x, y,constXPos,constYPos, turn+1) == true)
                    {
                        opponentStabilityPoint++;
                    }
                    else if (getPotStability(tempBoard, x, y,constXPos,constYPos, turn+1) == true)
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

    public boolean getStability(String [][] board, int xPos, int yPos,ArrayList<Integer> constXPos,ArrayList<Integer> constYPos, int turn)
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

        int CurrentXPos = xPos;
        int CurrentYPos = yPos;

        int countVert1 = 0;
        int countVert2 = 0;

        int countHoriz1 = 0;
        int countHoriz2 = 0;

        int countLRD1 = 0;
        int countLRD2 = 0;

        int countRLD1 = 0;
        int countRLD2 = 0;

        int countHorizEdge1 = 0;
        int countHorizEdge2 = 0;

        int countVertEdge1 = 0;
        int countVertEdge2 = 0;

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

        if (!tempBoard[CurrentXPos][CurrentYPos].equalsIgnoreCase(coinsChecked) )
        {
            return false;
        }

        if (checkFilled.filledVertical(tempBoard, CurrentXPos,CurrentYPos) == true )
        {
            verticalStabilityChecker[0] = true;
        }
        else if (checkFilled.filledVertical(tempBoard,CurrentXPos,CurrentYPos) == false )
        {
            verticalStabilityChecker[0] = false;
        }

        if (checkFilled.filledHorizontal(tempBoard,CurrentXPos,CurrentYPos) == true )
        {
            horizontalStabilityChecker[0] = true;
        }
        else if (checkFilled.filledVertical(tempBoard,CurrentXPos,CurrentYPos) == false )
        {
            horizontalStabilityChecker[0] = false;
        }

        if (checkFilled.filledLRDiagonal(tempBoard,CurrentXPos,CurrentYPos) == true )
        {
            leftRightDiagStabilityChecker[0] = true;
        }
        else if (checkFilled.filledLRDiagonal(tempBoard,CurrentXPos,CurrentYPos) == false )
        {
            leftRightDiagStabilityChecker[0] = false;
        }

        if (checkFilled.filledRLDiagonal(tempBoard,CurrentXPos,CurrentYPos) == true )
        {
            rightLeftDiagStabilityChecker[0] = true;
        }
        else if (checkFilled.filledRLDiagonal(tempBoard,CurrentXPos,CurrentYPos) == false )
        {
            leftRightDiagStabilityChecker[0] = false;
        }

        if (CurrentXPos == 0 && CurrentYPos == 0)
        {
            verticalStabilityChecker[1] = true;
            horizontalStabilityChecker[1] = true;
            leftRightDiagStabilityChecker[1] = true;
            rightLeftDiagStabilityChecker[1] = true;
        }

        else if (CurrentXPos == 0 && CurrentYPos == 7)
        {
            verticalStabilityChecker[1] = true;
            horizontalStabilityChecker[2] = true;
            leftRightDiagStabilityChecker[1] = true;
            rightLeftDiagStabilityChecker[2] = true;
        }

        else if (CurrentXPos == 7 && CurrentYPos == 7)
        {
            verticalStabilityChecker[2] = true;
            horizontalStabilityChecker[2] = true;
            leftRightDiagStabilityChecker[2] = true;
            rightLeftDiagStabilityChecker[2] = true;
        }

        else if (CurrentXPos == 7 && CurrentYPos == 0)
        {
            verticalStabilityChecker[2] = true;
            horizontalStabilityChecker[1] = true;
            leftRightDiagStabilityChecker[2] = true;
            rightLeftDiagStabilityChecker[1] = true;
        }

        if (CurrentXPos == 0 || CurrentXPos == 7)
        {
            if (CurrentXPos == 0)
            {
                verticalStabilityChecker[1] = true;
                leftRightDiagStabilityChecker[1] = true;
                rightLeftDiagStabilityChecker[2] = true;
            }
            else if (CurrentXPos == 7)
            {
                verticalStabilityChecker[2] = true;
                leftRightDiagStabilityChecker[2] = true;
                rightLeftDiagStabilityChecker[1] = true;
            }
            if  (CurrentYPos < 7 && CurrentYPos >0)
            {
                for (int x = 0; x < constXPos.size(); x++)
                {
                    if (CurrentXPos != constXPos.get(x) && CurrentYPos-1 != constYPos.get(x) )
                    {

                    }
                    else
                    {
                        countHorizEdge1++;
                    }
                    if (CurrentXPos != constXPos.get(x) && CurrentYPos+1 != constYPos.get(x) )
                    {

                    }
                    else
                    {
                        countHorizEdge2++;
                    }
                }
                if (!tempBoard[CurrentXPos][CurrentYPos-1].equalsIgnoreCase(coinsChecked) )
                {
                    horizontalStabilityChecker[1] = false;
                }
                else
                {
                    if(countHorizEdge1 == 0)
                    {
                        constXPos.add(CurrentXPos);
                        constYPos.add(CurrentYPos-1);
                        horizontalStabilityChecker[1] = getStability(tempBoard, CurrentXPos,CurrentYPos-1,constXPos,constYPos, turn);
                    }
                }
                if (!tempBoard[CurrentXPos][CurrentYPos+1].equalsIgnoreCase(coinsChecked) )
                {
                    horizontalStabilityChecker[2] = false;
                }
                else
                {
                    if(countHorizEdge2 == 0)
                    {
                        constXPos.add(CurrentXPos);
                        constYPos.add(CurrentYPos+1);
                        horizontalStabilityChecker[2] = getStability(tempBoard,CurrentXPos,CurrentYPos+1,constXPos,constYPos, turn);
                    }
                }
            }
        }

        else if (CurrentYPos == 0 || CurrentYPos == 7)
        {
            if (CurrentYPos == 0)
            {
                horizontalStabilityChecker[1] = true;
                leftRightDiagStabilityChecker[1] = true;
                rightLeftDiagStabilityChecker[1] = true;
            }
            else if (CurrentYPos == 7)
            {
                horizontalStabilityChecker[2] = true;
                leftRightDiagStabilityChecker[2] = true;
                rightLeftDiagStabilityChecker[2] = true;
            }
            for (int x = 0; x < constXPos.size(); x++)
            {
                if (CurrentXPos-1 != constXPos.get(x) && CurrentYPos != constYPos.get(x) )
                {

                }
                else
                {
                    countVertEdge1++;
                }
                if (CurrentXPos+1 != constXPos.get(x) && CurrentYPos != constYPos.get(x) )
                {

                }
                else
                {
                    countVertEdge2++;
                }
            }
            if (!tempBoard[CurrentXPos-1][CurrentYPos].equalsIgnoreCase(coinsChecked) )
            {
                verticalStabilityChecker[1] = false;
            }
            else
            {
                if (countVertEdge1 == 0)
                {
                    constXPos.add(CurrentXPos-1);
                    constYPos.add(CurrentYPos);
                    verticalStabilityChecker[1] = getStability(tempBoard, CurrentXPos-1,CurrentYPos,constXPos,constYPos, turn);
                }
            }

            if (!tempBoard[CurrentXPos+1][CurrentYPos].equalsIgnoreCase(coinsChecked) )
            {
                verticalStabilityChecker[2] = false;
            }
            else
            {
                if (countVertEdge2 == 0)
                {
                    constXPos.add(CurrentXPos+1);
                    constYPos.add(CurrentYPos);
                    verticalStabilityChecker[2] = getStability(tempBoard, CurrentXPos+1,CurrentYPos,constXPos,constYPos, turn);
                }
            }
        }

        if ( (CurrentXPos >= 1 && CurrentXPos <= 6) && (CurrentYPos>= 1 && CurrentYPos <= 6) )
        {
            for (int x = 0; x < constXPos.size(); x++)
            {
                if (CurrentXPos - 1 != constXPos.get(x) && CurrentYPos != constYPos.get(x) )
                {
                }
                else
                {
                    countVert1++;
                }
                if (CurrentXPos != constXPos.get(x) && CurrentYPos-1 != constYPos.get(x) )
                {
                }
                else
                {
                    countHoriz1++;
                }
                if (CurrentXPos-1 != constXPos.get(x) && CurrentYPos-1 != constYPos.get(x) )
                {
                }
                else
                {
                    countLRD1++;
                }
                if (CurrentXPos+1 != constXPos.get(x) && CurrentYPos+1 != constYPos.get(x) )
                {
                }
                else
                {
                    countRLD1++;
                }
                if (CurrentXPos + 1 != constXPos.get(x) && CurrentYPos != constYPos.get(x) )
                {
                }
                else
                {
                    countVert2++;
                }
                if (CurrentXPos != constXPos.get(x) && CurrentYPos+ 1 != constYPos.get(x) )
                {
                }
                else
                {
                    countHoriz2++;
                }
                if (CurrentXPos+1 != constXPos.get(x) && CurrentYPos+1 != constYPos.get(x) )
                {
                }
                else
                {
                    countLRD2++;
                }
                if (CurrentXPos-1 != constXPos.get(x) && CurrentYPos +1!= constYPos.get(x) )
                {
                }
                else
                {
                    countRLD2++;
                }
            }

            if (countVert1 == 0)
            {
                constXPos.add(CurrentXPos-1);
                constYPos.add(CurrentYPos);
                verticalStabilityChecker[1] = getStability(tempBoard, CurrentXPos-1,CurrentYPos,constXPos,constYPos, turn);
            }
            if (countHoriz1 == 0)
            {
                constXPos.add(CurrentXPos);
                constYPos.add(CurrentYPos-1);
                horizontalStabilityChecker[1] = getStability(tempBoard, CurrentXPos,CurrentYPos-1,constXPos,constYPos, turn);
            }
            if (countLRD1 == 0)
            {
                constXPos.add(CurrentXPos-1);
                constYPos.add(CurrentYPos-1);
                leftRightDiagStabilityChecker[1] = getStability(tempBoard,CurrentXPos-1,CurrentYPos-1,constXPos,constYPos, turn);
            }
            if (countRLD1 == 0)
            {
                constXPos.add(CurrentXPos+1);
                constYPos.add(CurrentYPos-1);
                rightLeftDiagStabilityChecker[1] = getStability(tempBoard,CurrentXPos+1,CurrentYPos-1,constXPos,constYPos, turn);
            }
            if (countVert2 == 0)
            {
                constXPos.add(CurrentXPos+1);
                constYPos.add(CurrentYPos);
                verticalStabilityChecker[2] = getStability(tempBoard,CurrentXPos+1,CurrentYPos,constXPos,constYPos, turn);
            }
            if (countHoriz2 == 0)
            {constXPos.add(CurrentXPos);
                constYPos.add(CurrentYPos+1);
                horizontalStabilityChecker[2] = getStability(tempBoard,CurrentXPos,CurrentYPos+1,constXPos,constYPos, turn);
            }
            if (countLRD2 == 0)
            {
                constXPos.add(CurrentXPos+1);
                constYPos.add(CurrentYPos+1);
                leftRightDiagStabilityChecker[2] = getStability(tempBoard,CurrentXPos+1,CurrentYPos+1,constXPos,constYPos, turn);
            }
            if (countRLD2 == 0)
            {
                constXPos.add(CurrentXPos-1);
                constYPos.add(CurrentYPos+1);
                rightLeftDiagStabilityChecker[2] = getStability(tempBoard,CurrentXPos-1,CurrentYPos+1,constXPos,constYPos, turn);
            }
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

    public boolean getPotStability(String [][] board, int xPos, int yPos,ArrayList<Integer> constXPos,ArrayList<Integer> constYPos, int turn)
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

        int CurrentXPos = xPos;
        int CurrentYPos = yPos;

        int countVert1 = 0;
        int countVert2 = 0;

        int countHoriz1 = 0;
        int countHoriz2 = 0;

        int countLRD1 = 0;
        int countLRD2 = 0;

        int countRLD1 = 0;
        int countRLD2 = 0;

        int countVertEdge1 = 0;
        int countVertEdge2 = 0;

        int countHorizEdge1 = 0;
        int countHorizEdge2 = 0;

        int stableCount = 0;

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

        if (!tempBoard[CurrentXPos][CurrentYPos].equalsIgnoreCase(coinsChecked) )
        {
            return false;
        }

        if (checkFilled.filledVertical(tempBoard, CurrentXPos,CurrentYPos) == true )
        {
            verticalStabilityChecker[0] = true;
        }
        else if (checkFilled.filledVertical(tempBoard,CurrentXPos,CurrentYPos) == false )
        {
            verticalStabilityChecker[0] = false;
        }

        if (checkFilled.filledHorizontal(tempBoard,CurrentXPos,CurrentYPos) == true )
        {
            horizontalStabilityChecker[0] = true;
        }
        else if (checkFilled.filledVertical(tempBoard,CurrentXPos,CurrentYPos) == false )
        {
            horizontalStabilityChecker[0] = false;
        }

        if (checkFilled.filledLRDiagonal(tempBoard,CurrentXPos,CurrentYPos) == true )
        {
            leftRightDiagStabilityChecker[0] = true;
        }
        else if (checkFilled.filledLRDiagonal(tempBoard,CurrentXPos,CurrentYPos) == false )
        {
            leftRightDiagStabilityChecker[0] = false;
        }

        if (checkFilled.filledRLDiagonal(tempBoard,CurrentXPos,CurrentYPos) == true )
        {
            rightLeftDiagStabilityChecker[0] = true;
        }
        else if (checkFilled.filledRLDiagonal(tempBoard,CurrentXPos,CurrentYPos) == false )
        {
            leftRightDiagStabilityChecker[0] = false;
        }

        if (CurrentXPos == 0 && CurrentYPos == 0)
        {
            verticalStabilityChecker[1] = true;
            horizontalStabilityChecker[1] = true;
            leftRightDiagStabilityChecker[1] = true;
            rightLeftDiagStabilityChecker[1] = true;
        }

        else if (CurrentXPos == 0 && CurrentYPos == 7)
        {
            verticalStabilityChecker[1] = true;
            horizontalStabilityChecker[2] = true;
            leftRightDiagStabilityChecker[1] = true;
            rightLeftDiagStabilityChecker[2] = true;
        }

        else if (CurrentXPos == 7 && CurrentYPos == 7)
        {
            verticalStabilityChecker[2] = true;
            horizontalStabilityChecker[2] = true;
            leftRightDiagStabilityChecker[2] = true;
            rightLeftDiagStabilityChecker[2] = true;
        }

        else if (CurrentXPos == 7 && CurrentYPos == 0)
        {
            verticalStabilityChecker[2] = true;
            horizontalStabilityChecker[1] = true;
            leftRightDiagStabilityChecker[2] = true;
            rightLeftDiagStabilityChecker[1] = true;
        }

        if (CurrentXPos == 0 || CurrentXPos == 7)
        {
            if (CurrentXPos == 0)
            {
                verticalStabilityChecker[1] = true;
                leftRightDiagStabilityChecker[1] = true;
                rightLeftDiagStabilityChecker[2] = true;
            }
            else if (CurrentXPos == 7)
            {
                verticalStabilityChecker[2] = true;
                leftRightDiagStabilityChecker[2] = true;
                rightLeftDiagStabilityChecker[1] = true;
            }
            if  (CurrentYPos < 7 && CurrentYPos >0)
            {
                for (int x = 0; x < constXPos.size(); x++)
                {
                    if (CurrentXPos != constXPos.get(x) && CurrentYPos-1 != constYPos.get(x) )
                    {

                    }
                    else
                    {
                        countHorizEdge1++;
                    }
                    if (CurrentXPos != constXPos.get(x) && CurrentYPos+1 != constYPos.get(x) )
                    {

                    }
                    else
                    {
                        countHorizEdge2++;
                    }
                }
                if (!tempBoard[CurrentXPos][CurrentYPos-1].equalsIgnoreCase(coinsChecked) )
                {
                    horizontalStabilityChecker[1] = false;
                }
                else
                {
                    if(countHorizEdge1 == 0)
                    {
                        constXPos.add(CurrentXPos);
                        constYPos.add(CurrentYPos-1);
                        horizontalStabilityChecker[1] = getStability(tempBoard, CurrentXPos,CurrentYPos-1,constXPos,constYPos, turn);
                    }
                }
                if (!tempBoard[CurrentXPos][CurrentYPos+1].equalsIgnoreCase(coinsChecked) )
                {
                    horizontalStabilityChecker[2] = false;
                }
                else
                {
                    if(countHorizEdge2 == 0)
                    {
                        constXPos.add(CurrentXPos);
                        constYPos.add(CurrentYPos+1);
                        horizontalStabilityChecker[2] = getStability(tempBoard,CurrentXPos,CurrentYPos+1,constXPos,constYPos, turn);
                    }
                }
            }
        }

        else if (CurrentYPos == 0 || CurrentYPos == 7)
        {
            if (CurrentYPos == 0)
            {
                horizontalStabilityChecker[1] = true;
                leftRightDiagStabilityChecker[1] = true;
                rightLeftDiagStabilityChecker[1] = true;
            }
            else if (CurrentYPos == 7)
            {
                horizontalStabilityChecker[2] = true;
                leftRightDiagStabilityChecker[2] = true;
                rightLeftDiagStabilityChecker[2] = true;
            }
            for (int x = 0; x < constXPos.size(); x++)
            {
                if (CurrentXPos-1 != constXPos.get(x) && CurrentYPos != constYPos.get(x) )
                {

                }
                else
                {
                    countVertEdge1++;
                }
                if (CurrentXPos+1 != constXPos.get(x) && CurrentYPos != constYPos.get(x) )
                {

                }
                else
                {
                    countVertEdge2++;
                }
            }
            if (!tempBoard[CurrentXPos-1][CurrentYPos].equalsIgnoreCase(coinsChecked) )
            {
                verticalStabilityChecker[1] = false;
            }
            else
            {
                if (countVertEdge1 == 0)
                {
                    constXPos.add(CurrentXPos-1);
                    constYPos.add(CurrentYPos);
                    verticalStabilityChecker[1] = getStability(tempBoard, CurrentXPos-1,CurrentYPos,constXPos,constYPos, turn);
                }
            }

            if (!tempBoard[CurrentXPos+1][CurrentYPos].equalsIgnoreCase(coinsChecked) )
            {
                verticalStabilityChecker[2] = false;
            }
            else
            {
                if (countVertEdge2 == 0)
                {
                    constXPos.add(CurrentXPos+1);
                    constYPos.add(CurrentYPos);
                    verticalStabilityChecker[2] = getStability(tempBoard, CurrentXPos+1,CurrentYPos,constXPos,constYPos, turn);
                }
            }
        }

        if ( (CurrentXPos >= 1 && CurrentXPos <= 6) && (CurrentYPos>= 1 && CurrentYPos <= 6) )
        {
            for (int x = 0; x < constXPos.size(); x++)
            {
                if (CurrentXPos - 1 != constXPos.get(x) && CurrentYPos != constYPos.get(x) )
                {
                }
                else
                {
                    countVert1++;
                }
                if (CurrentXPos != constXPos.get(x) && CurrentYPos-1 != constYPos.get(x) )
                {
                }
                else
                {
                    countHoriz1++;
                }
                if (CurrentXPos-1 != constXPos.get(x) && CurrentYPos-1 != constYPos.get(x) )
                {
                }
                else
                {
                    countLRD1++;
                }
                if (CurrentXPos+1 != constXPos.get(x) && CurrentYPos+1 != constYPos.get(x) )
                {
                }
                else
                {
                    countRLD1++;
                }
                if (CurrentXPos + 1 != constXPos.get(x) && CurrentYPos != constYPos.get(x) )
                {
                }
                else
                {
                    countVert2++;
                }
                if (CurrentXPos != constXPos.get(x) && CurrentYPos+ 1 != constYPos.get(x) )
                {
                }
                else
                {
                    countHoriz2++;
                }
                if (CurrentXPos+1 != constXPos.get(x) && CurrentYPos+1 != constYPos.get(x) )
                {
                }
                else
                {
                    countLRD2++;
                }
                if (CurrentXPos-1 != constXPos.get(x) && CurrentYPos +1!= constYPos.get(x) )
                {
                }
                else
                {
                    countRLD2++;
                }
            }

            if (countVert1 == 0)
            {
                constXPos.add(CurrentXPos-1);
                constYPos.add(CurrentYPos);
                verticalStabilityChecker[1] = getStability(tempBoard, CurrentXPos-1,CurrentYPos,constXPos,constYPos, turn);
            }
            if (countHoriz1 == 0)
            {
                constXPos.add(CurrentXPos);
                constYPos.add(CurrentYPos-1);
                horizontalStabilityChecker[1] = getStability(tempBoard, CurrentXPos,CurrentYPos-1,constXPos,constYPos, turn);
            }
            if (countLRD1 == 0)
            {
                constXPos.add(CurrentXPos-1);
                constYPos.add(CurrentYPos-1);
                leftRightDiagStabilityChecker[1] = getStability(tempBoard,CurrentXPos-1,CurrentYPos-1,constXPos,constYPos, turn);
            }
            if (countRLD1 == 0)
            {
                constXPos.add(CurrentXPos+1);
                constYPos.add(CurrentYPos-1);
                rightLeftDiagStabilityChecker[1] = getStability(tempBoard,CurrentXPos+1,CurrentYPos-1,constXPos,constYPos, turn);
            }
            if (countVert2 == 0)
            {
                constXPos.add(CurrentXPos+1);
                constYPos.add(CurrentYPos);
                verticalStabilityChecker[2] = getStability(tempBoard,CurrentXPos+1,CurrentYPos,constXPos,constYPos, turn);
            }
            if (countHoriz2 == 0)
            {constXPos.add(CurrentXPos);
                constYPos.add(CurrentYPos+1);
                horizontalStabilityChecker[2] = getStability(tempBoard,CurrentXPos,CurrentYPos+1,constXPos,constYPos, turn);
            }
            if (countLRD2 == 0)
            {
                constXPos.add(CurrentXPos+1);
                constYPos.add(CurrentYPos+1);
                leftRightDiagStabilityChecker[2] = getStability(tempBoard,CurrentXPos+1,CurrentYPos+1,constXPos,constYPos, turn);
            }
            if (countRLD2 == 0)
            {
                constXPos.add(CurrentXPos-1);
                constYPos.add(CurrentYPos+1);
                rightLeftDiagStabilityChecker[2] = getStability(tempBoard,CurrentXPos-1,CurrentYPos+1,constXPos,constYPos, turn);
            }
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

