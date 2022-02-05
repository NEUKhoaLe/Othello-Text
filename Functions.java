
public class Functions
{   
    private FunctionsChecker functions;
    private FunctionsFlipper flipper;

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
        String [][] tempBoard = new String [8][8];
        copyBoard(board, tempBoard);

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
        String tempBoard [][] = new String [8][8];
        copyBoard(board, tempBoard);

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

    public int getP1Score(String board [][])
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
                else
                {
                }
            }
        }

        return winner;
    }

    public int getP2Score(String board [][])
    {
        int winner = 0;
        for (int x = 0; x < 8; x++)
        {
            for (int y = 0; y < 8; y++)
            {
                if (board[x][y].equalsIgnoreCase("y") )
                {
                    winner++;
                }
                else
                {
                }
            }
        }

        return winner;
    }

    public String getScore(String board [][])
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
            return "Player 1";
        }
        else if (winner < 0)
        {
            return "Player 2";
        }
        else
        {
            return "tie";
        }
    }

    public void getSpace()
    {
        sopln("\n");
        sopln("\n");
        sopln("\n");
        sopln("\n");
        sopln("\n");
        sopln("\n");
        sopln("\n");
        sopln("\n");
        sopln("\n");
        sopln("\n");
        sopln("\n");
        sopln("\n");
        sopln("\n");
        sopln("\n");
        sopln("\n");
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

