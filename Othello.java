
import java.util.*;
public class Othello
{
    public static void main (String [] args)
    {
        Scanner scan = new Scanner(System.in);
        String input = null;
        String playerOne = null;
        String playerTwo = null;
        String playerThree = null;
        String temp = null;
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

        int winCounterP1 = 0;
        int winCounterP2 = 0;

        int loseCounterP1 = 0;
        int loseCounterP2 = 0;

        int winCounterAI = 0;
        int winCounterM1 = 0;

        int loseCounterAI = 0;
        int loseCounterM1 = 0;

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

                sopln("Randomly choosing who to start first:");

                if ( ((int)(Math.random()*2+1)%2 == 0) )
                {
                    temp = playerOne;
                    playerOne = playerTwo;
                    playerTwo = temp;
                }
                else
                {
                }

                for (int x = 0; x < 60; x++)
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

                        function.getSpace();

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
                        function.getSpace();

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

                if (function.getScore(board).equalsIgnoreCase("Player 1") )
                {
                    sopln(playerOne + " win!");
                    winCounterP1++;
                    loseCounterP2--;
                }
                else if (function.getScore(board).equalsIgnoreCase("Player 2") )
                {
                    sopln(playerTwo + " win!");
                    winCounterP2++;
                    loseCounterP1--;
                }

                sopln(function.getP1Score(board) + " - " + function.getP2Score(board) );

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
                board = function.makeBoard(board);

                sopln("Do you want to play the easy AI or the hard AI?");
                input = scan.nextLine();

                sopln("Enter your name.");
                playerThree = scan.nextLine();

                if (input.equalsIgnoreCase("Easy") )
                {
                    easyAI easyMode = new easyAI();

                    int turn = (int)(Math.random()*2+1);

                    if (turn %2 == 0)
                    {
                        sopln("AI will start first.");

                        for (int x = 0; x < 60; x++)
                        {
                            if ( x%2 == 0) //AI will play 'X'
                            {
                                board = easyMode.computerMove(board, x);

                                function.printBoard(board);

                                function.getSpace();

                                if (function.possibleMoves(board,x+1) == false)
                                {
                                    x++;
                                    sopln(playerThree + " can't make a move.");
                                }
                                else
                                {
                                }
                            }
                            else if (x%2 == 1) //Player Two will play 'Y'
                            {
                                legalMove = false;

                                sopln(playerThree + " ‘s turn.");
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
                                function.getSpace();

                                if (function.possibleMoves(board,x+1) == false)
                                {
                                    x++;
                                    sopln("AI can't make a move.");
                                }
                                else
                                {
                                }
                            }
                        }

                        if (function.getScore(board).equalsIgnoreCase("Player 1") )
                        {
                            sopln("The AI win! You Lose!");
                            winCounterAI++;
                            loseCounterM1--;
                        }
                        else if (function.getScore(board).equalsIgnoreCase("Player 2") )
                        {
                            sopln(playerThree + " win!");
                            winCounterAI++;
                            loseCounterM1--;
                        }

                        sopln(function.getP1Score(board) + " - " + function.getP2Score(board) );

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
                    else
                    {
                        sopln(playerThree + " will start first.");

                        for (int x = 0; x < 60; x++)
                        {
                            if ( x%2 == 0) //AI will play 'X'
                            {

                                legalMove = false;

                                sopln(playerThree + " ‘s turn.");
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
                                //new board.

                                board = flipper.changePieces(board, rowInput, columnInput, x);

                                function.printBoard(board);
                                function.getSpace();

                                if (function.possibleMoves(board,x+1) == false)
                                {
                                    x++;
                                    sopln("AI can't make a move.");
                                }
                                else
                                {
                                }
                            }

                            else if (x%2 == 1) //Player Two will play 'Y'
                            {
                                board = easyMode.computerMove(board, x);

                                function.printBoard(board);

                                function.getSpace();

                                if (function.possibleMoves(board,x+1) == false)
                                {
                                    x++;
                                    sopln(playerThree + " can't make a move.");
                                }
                                else
                                {
                                }
                            }                         
                        }

                        if (function.getScore(board).equalsIgnoreCase("Player 1") )
                        {
                            sopln("The AI win! You Lose!");
                            winCounterAI++;
                            loseCounterM1--;
                        }
                        else if (function.getScore(board).equalsIgnoreCase("Player 2") )
                        {
                            sopln(playerThree + " win!");
                            winCounterAI++;
                            loseCounterM1--;
                        }

                        sopln(function.getP1Score(board) + " - " + function.getP2Score(board) );

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
                }
                else if (input.equalsIgnoreCase("Hard") )
                {
                    int turn = (int)(Math.random()*2+1);

                    HardAI hardMode = new HardAI();

                    if (turn %2 == 0)
                    {
                        sopln("AI will start first.");

                        for (int x = 0; x < 60; x++)
                        {
                            if ( x%2 == 0) //AI will play 'X'
                            {
                                board = hardMode.makeMove(board, x);

                                function.printBoard(board);

                                function.getSpace();

                                if (function.possibleMoves(board,x+1) == false)
                                {
                                    x++;
                                    sopln(playerThree + " can't make a move.");
                                }
                                else
                                {
                                }
                            }
                            else if (x%2 == 1) //Player Two will play 'Y'
                            {
                                legalMove = false;

                                sopln(playerThree + " ‘s turn.");
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
                                function.getSpace();

                                if (function.possibleMoves(board,x+1) == false)
                                {
                                    x++;
                                    sopln("AI can't make a move.");
                                }
                                else
                                {
                                }
                            }
                        }

                        if (function.getScore(board).equalsIgnoreCase("Player 1") )
                        {
                            sopln("The AI win! You Lose!");
                            winCounterAI++;
                            loseCounterM1--;
                        }
                        else if (function.getScore(board).equalsIgnoreCase("Player 2") )
                        {
                            sopln(playerThree + " win!");
                            winCounterAI++;
                            loseCounterM1--;
                        }

                        sopln(function.getP1Score(board) + " - " + function.getP2Score(board) );

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
                    else
                    {
                        sopln(playerThree + " will start first.");

                        for (int x = 0; x < 60; x++)
                        {
                            if ( x%2 == 0) //AI will play 'Y'
                            {

                                legalMove = false;

                                sopln(playerThree + " ‘s turn.");
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
                                //new board.

                                board = flipper.changePieces(board, rowInput, columnInput, x);

                                function.printBoard(board);
                                function.getSpace();

                                if (function.possibleMoves(board,x+1) == false)
                                {
                                    x++;
                                    sopln("AI can't make a move.");
                                }
                                else
                                {
                                }
                            }

                            else if (x%2 == 1) //Player Two will play 'Y'
                            {
                                board = hardMode.makeMove(board, x);

                                function.printBoard(board);

                                function.getSpace();

                                if (function.possibleMoves(board,x+1) == false)
                                {
                                    x++;
                                    sopln(playerThree + " can't make a move.");
                                }
                                else
                                {
                                }
                            }                         
                        }

                        if (function.getScore(board).equalsIgnoreCase("Player 1") )
                        {
                            sopln(playerThree +"Win! You Lose!");
                            winCounterAI++;
                            loseCounterM1--;
                        }
                        else if (function.getScore(board).equalsIgnoreCase("Player 2") )
                        {
                            sopln("AI win! You lose.");
                            winCounterAI++;
                            loseCounterM1--;
                        }

                        sopln(function.getP1Score(board) + " - " + function.getP2Score(board) );

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
                }
            }
            else if (input.equalsIgnoreCase("Quit") )
            {
                game = false;
            }
        }

        sopln(playerOne + "'s game record is: " + winCounterP1 + " - " + loseCounterP1);
        sopln(playerTwo + "'s game record is: " + winCounterP2 + " - " + loseCounterP2);
        sopln(playerThree + "'s game record against AI is: " + winCounterM1 + " - " + loseCounterM1);
    }

    public static void sopln(String x)
    {
        System.out.println(x);
    }

    public static void sop(String x)
    {
        System.out.print(x);
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

