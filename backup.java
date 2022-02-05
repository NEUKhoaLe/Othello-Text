
/*
 * 
Driver:

import java.util.*;

public class Othello
{
    public static void main (String [] args)
    {
        Scanner scan = new Scanner(System.in);
        String input = null;
        String playerOne = null;
        String playerTwo = null;
        Boolean game = true;
        Boolean legalMove = false;

        String [][] board = new String [8][8];
        String [][] tempBoard = new String[8][8];

        Functions function = new Functions();
        FunctionsFlipper flipper = new FunctionsFlipper();

        int whoFirst = 0;

        String columnMove = null;
        int rowMove = 0;

        int rowInput = 0;
        int columnInput = 0;

        int winner = 0;

        while  (game == true) //a while loop that will always play the game until the player wants to stop.
        {
            function.mainMenu();

            input = scan.nextLine();

            if (input.equalsIgnoreCase("Instructions") ) 
            {
                function.getInstructions();
            }
            else if (input.equalsIgnoreCase("Human") )
            {
                board = function.makeBoard(board);

                sopln("Enter player 1's name.");
                playerOne = scan.nextLine();

                sopln("Now enter player 2's name.");
                playerTwo = scan.nextLine();

                for (int x = 0; x < 64; x++)
                {
                    if ( x%2 == 0) //Player One will play 'X'
                    {
                        legalMove = false;

                        sopln(playerOne + " ‘s turn.");
                        sopln("This is the current state of the board.");

                        function.printBoard(board);

                        while (legalMove == false)
                        {
                            sopln("Where do you want to place your piece? Enter the 1-8 for the row, and A-H for the column.");

                            rowMove = scan.nextInt();
                            scan.nextLine();
                            columnMove = scan.nextLine();

                            columnInput = function.columnInt(columnMove);
                            rowInput = rowMove - 1;

                            if (function.legalMoveChecker(board, rowInput, columnInput, x) == false)
                            {
                                sopln("This is an illegal move. Try again.");
                            }
                            else
                            {
                                legalMove = true;
                            }
                        }

                        board[rowInput][columnInput] = "X";

                        sopln("This is the updated board: ");
                        
                        board = flipper.changePieces(board, rowInput, columnInput, x);

                        function.printBoard(board);

                        if (function.possibleMoves(board,x+1) == false)
                        {
                            x++;
                            sopln(playerTwo + " can't make a move.");
                        }
                        else
                        {
                        }
                    }
                    else if (x%2 == 1) //Player Two will play 'Y'
                    {
                        legalMove = false;

                        sopln(playerTwo + " ‘s turn.");
                        sopln("This is the current state of the board.");

                        function.printBoard(board);

                        while (legalMove == false)
                        {
                            sopln("Where do you want to place your piece? Enter the 1-8 for the row, and A-H for the column.");

                            rowMove = scan.nextInt();
                            scan.nextLine();
                            columnMove = scan.nextLine();

                            columnInput = function.columnInt(columnMove);
                            rowInput = rowMove - 1;

                            if (function.legalMoveChecker(board, rowInput, columnInput, x) == false)
                            {
                                sopln("This is an illegal move. Try again.");
                            }
                            else
                            {
                                legalMove = true;
                            }
                        }

                        board[rowInput][columnInput] = "Y";

                        sopln("This is the updated board: ");
                        //new board.

                        board = flipper.changePieces(board, rowInput, columnInput, x);

                        function.printBoard(board);

                        if (function.possibleMoves(board,x+1) == false)
                        {
                            x++;
                            sopln(playerOne + " can't make a move.");
                        }
                        else
                        {
                        }
                    }
                }

                sopln("Do you want to play again? Type in yes or no");
                input = scan.nextLine();

                if (input.equalsIgnoreCase("Yes") )
                {
                    game = true;
                }
                else if (input.equalsIgnoreCase("No") )
                {
                    game = false;
                }

            }
            else if (input.equalsIgnoreCase("Computer") )
            {
            }
            else if (input.equalsIgnoreCase("Quit") )
            {
                game = false;
            }
        }
    }

    public static void sopln(String x)
    {
        System.out.println(x);
    }

    public static void sop(String x)
    {
        System.out.print(x);
    }
}

Functions subclass

public class Functions
{   
    FunctionsChecker functions;
    FunctionsFlipper flipper;

    public Functions()
    {
        functions = new FunctionsChecker();
        flipper = new FunctionsFlipper();
    }

    public static void printBoard(String [][] x)
    {
        for (int y  = 0; y < 8; y++)
        {
            if (y == 0)
            {
                for ( int a = 0; a < 8; a++)
                {
                    if (a == 0)
                    {
                        sop("   A  ");
                    }
                    else if (a == 1)
                    {
                        sop("B  ");
                    }
                    else if (a == 2)
                    {
                        sop("C  ");
                    }
                    else if (a == 3)
                    {
                        sop("D  ");
                    }
                    else if (a == 4)
                    {
                        sop("E  ");
                    }
                    else if (a == 5)
                    {
                        sop("F  ");
                    }
                    else if (a == 6)
                    {
                        sop("G  ");
                    }
                    else if (a == 7)
                    {
                        sop("H  ");
                    }
                }
                sopln("");
            }

            if (y == 0)
            {
                sop("1 |");
            }
            else if (y == 1)
            {
                sop("2 |");
            }
            else if (y == 2)
            {
                sop("3 |");
            }
            else if (y == 3)
            {
                sop("4 |");
            }
            else if (y == 4)
            {
                sop("5 |");
            }
            else if (y == 5)
            {
                sop("6 |");
            }
            else if (y == 6)
            {
                sop("7 |");
            }
            else if (y == 7)
            {
                sop("8 |");
            }

            for (int z = 0; z < 8; z++)
            {             
                sop(x[y][z] + " |");                       
            }
            sopln("");
        }
    }

    public static int columnInt(String x)
    {
        if (x.equalsIgnoreCase("A") )
        {
            return 0;
        }
        else if (x.equalsIgnoreCase("B") )
        {
            return 1;
        }
        else if (x.equalsIgnoreCase("C") )
        {
            return 2;
        }
        else if (x.equalsIgnoreCase("D") )
        {
            return 3;
        }
        else if (x.equalsIgnoreCase("E") )
        {
            return 4;
        }
        else if (x.equalsIgnoreCase("F") )
        {
            return 5;
        }
        else if (x.equalsIgnoreCase("G") )
        {
            return 6;
        }
        else
        {
            return 7;
        }        
    }

    public static void sopln(String x)
    {
        System.out.println(x);
    }

    public static void sop(String x)
    {
        System.out.print(x);
    }

    public static void getInstructions()
    {
        sopln("\t \t \t Object:\n \t \t \t The object of the game is to get the majority of the board to be your piece (X or Y) at the end of the game");
        sopln("\t \t \t Game Play:\n \t \t \t The game will start with two X and two Y in the center of the board");
        sopln("\t \t \t Each player will take turns placing pieces in available spots until there are no more valid moves");
        sopln("\t \t \t Moves are to be made when a player places a piece on the board that will cause a string of the other player’s pieces to be flipped.");
        sopln("\t \t \t For this to happen, pieces can only be placed if it sandwiches the opponent's pieces.");
        sopln("\t \t \t This can be vertically, horizontally, and diagonally.");
        sopln("\t \t \t If there are no available move, that player will have his turn's skipped.");
        sopln("");
        sopln("");
        sopln("");
    }

    public static void mainMenu()
    {
        sopln("\t \t \t \t \t \t \t \t \t Othello");
        sopln("\t \t \t \t \t \t Gamemode: ");

        sopln("\t \t \t \t \t \t Enter ‘Instructions’ to see the instructions to play the game.");
        sopln("\t \t \t \t \t \t Enter ‘Human’ to play against another human.");
        sopln("\t \t \t \t \t \t Enter ‘Computer’ to play against a computer.");
        sopln("\t \t \t \t \t \t Enter ‘Quit’ to quit the program.");
        sopln("");
    }

    public static String [][] makeBoard(String [][] board)
    {
        for (int x = 0; x < 8; x++)
        {
            for (int y = 0; y < 8; y++)
            {
                board[x][y] = "-";

                if ( (x== 3 && y==3) || (x==4 && y == 4) )
                {
                    board[x][y] = "Y";
                }
                if ( (x== 3 && y==4) || (x==4 && y == 3) )
                {
                    board[x][y] = "X";
                }
            }
        }

        return board;
    }

    public boolean legalMoveChecker(String[][] board, int Xpos, int Ypos, int turn)
    {
        String [][] tempBoard = board;
        int xpos = Xpos;
        int ypos = Ypos;

        boolean [] checkingSorter = new boolean [8];

        boolean theirPiece = false;

        int DyMax = 0;
        int LxMax = 0;

        int UyMax = 0;
        int RxMax = 0;

        UyMax = Xpos;
        LxMax = Ypos;

        DyMax = 7 - Xpos;
        RxMax = 7 - Ypos;

        String myPiece;
        String opponent;

        if (turn %2 == 0)
        {
            myPiece = "X";
            opponent = "Y";
        }
        else
        {
            myPiece = "Y";
            opponent = "X";
        }

        if (!(tempBoard[xpos][ypos].equalsIgnoreCase("-")) )
        {
            return false;
        }
        else if (xpos > 7 || xpos < 0 || ypos > 7 || ypos < 0)
        {
            return false;
        }
        else
        {
            checkingSorter[0] = functions.verticalUpChecker(tempBoard, xpos, ypos, UyMax, turn);

            checkingSorter[1] = functions.verticalDownChecker(tempBoard, xpos, ypos, DyMax, turn);

            checkingSorter[2] = functions.horizLeftChecker (tempBoard, xpos, ypos, LxMax, turn);

            checkingSorter[3] = functions.horizRightChecker (tempBoard, xpos, ypos, RxMax, turn);

            checkingSorter[4] = functions.diagUpLeftChecker (tempBoard, xpos, ypos, getMin(LxMax, UyMax), turn);

            checkingSorter[5] = functions.diagUpRightChecker (tempBoard, xpos, ypos, getMin(RxMax, UyMax), turn);

            checkingSorter[6] = functions.diagDownLeftChecker (tempBoard, xpos, ypos, getMin(LxMax, DyMax), turn);

            checkingSorter[7] = functions.diagDownRightChecker (tempBoard, xpos, ypos, getMin(RxMax, DyMax), turn);

            return functions.getSorter(checkingSorter);
            
        }
    }

    public boolean possibleMoves(String board [][], int turn)
    {
        String tempBoard [][] = board;
        int currentTurn = turn;
        for (int x = 0; x < 8; x++)
        {
            for (int y = 0; y < 8; y++)
            {
                if (legalMoveChecker(tempBoard, x, y, currentTurn) == true)
                {
                    return true;
                }
                else
                {
                }
            }
        }        
        return false;
    }

    public int getMin(int x, int y)
    {
        if (x > y)
        {
            return y;
        }
        else
        {
            return x;
        }
    }

    public void getScore(String board [][])
    {
        int winner = 0;
        for (int x = 0; x < 8; x++)
        {
            for (int y = 0; y < 8; y++)
            {
                if (board[x][y].equalsIgnoreCase("X") )
                {
                    winner++;
                }
                else if (board[x][y].equalsIgnoreCase("y") )
                {
                    winner--;
                }
                else
                {
                }
            }
        }

        if (winner > 0)
        {
            sopln("Player 1 wins.");
        }
        else if (winner < 0)
        {
            sopln("Player 2 wins.");
        }
        else
        {
            sopln("It is a tie.");
        }
    }
    
}

Functions for checking:

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

    public boolean diagDownRightChecker(String board [][], int xpos, int ypos, int LRDiag, int turn)
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
        for (int y = 0; y < LRDiag; y++)
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
                y = LRDiag;
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

Functions for flipping:   

public class FunctionsFlipper
{
    FunctionsChecker checker = new FunctionsChecker();
    public FunctionsFlipper()
    {

    }

    public String [][] changePieces( String[][] board,int row, int column, int turn)
    {
        String myPiece;
        String opponent;

        int DyMax = 7 - row;
        int UyMax = row;
        int LxMax = column;
        int RxMax = 7 - column;
        int counter = 1;

        if (turn%2 == 0)
        {
            myPiece = "X";
            opponent = "Y";
        }
        else
        {   myPiece ="Y";
            opponent = "X";
        }
        if(checker.verticalUpChecker(board,row,column,UyMax,turn) == true)
        {
            while(board[row-counter][column].equals(opponent)&&(row-counter)>=0)
            {
                board[row-counter][column]=myPiece;
                counter++;
            }
        }
        counter = 1;
        if(checker.verticalDownChecker(board,row,column,DyMax,turn) == true)
        {
            while(board[row+counter][column].equals(opponent)&& (row+counter)< 8)
            {
                board[row+counter][column]=myPiece;
                counter++;
            }
        }
        counter = 1;
        if(checker.horizLeftChecker(board,row,column,LxMax,turn) == true)
        {
            while(board[row][column-counter].equals(opponent)&&(column - counter)>=0)
            {
                board[row][column-counter]=myPiece;
                counter++;
            }
        }
        counter = 1;
        if(checker.horizRightChecker(board,row,column,RxMax,turn) == true)
        {           
            while(board[row][column].equals(opponent)&& (column + counter) < 8)
            {
                board[row][column+counter]=myPiece;
                counter++;
            }
        }
        counter = 1;
        if(checker.diagDownRightChecker(board, row, column,getMin(RxMax,DyMax),turn) == true)
        {
            while(board[row+counter][column+counter].equals(opponent)&&column<=7&&row<=7)
            {
                board[row+counter][column+counter]=myPiece;
                counter++;
            }
        }
        counter = 1;
        if(checker.diagUpLeftChecker(board, row, column,getMin(LxMax,UyMax),turn) == true)
        {

            while(board[row-counter][column-counter].equals(opponent)&&column>=0&&row>=0)
            {
                board[row-counter][column-counter]=myPiece;
                counter++;
            }
        }
        counter = 1;
        if(checker.diagUpRightChecker(board, row, column,getMin(RxMax,UyMax),turn) == true)
        {
            while(board[row-counter][column+counter].equals(opponent)&&(row>=0&&column<8))
            {
                board[row-counter][column+counter]=myPiece;
                counter++;
            }
        }
        counter = 1;
        if(checker.diagDownLeftChecker(board, row, column,getMin(RxMax,UyMax),turn) == true)
        {
            while(board[row+counter][column-counter].equals(opponent)&&(row<8&&column>=0))
            {
                board[row+counter][column-counter]=myPiece;
                counter++;
            }
        }
        
        return board;
    }

    public int getMin(int x, int y)
    {
        if (x > y)
        {
            return y;
        }
        else
        {
            return x;
        }
    }
}
 */
