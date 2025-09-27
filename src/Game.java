import java.util.*;

public class Game {
    Scanner scanner = new Scanner(System.in);
    Expectiminimax expectiminimax = new Expectiminimax();
    int length = 19, hasCockedForPlayer1 = 0, getHasCockedForPlayer2 = 0, depth = 2, maxThrows = 2;
    String[][] board = new String[length][length];
    private final int totalPathSize = 84;
    private ArrayList<Cell> player1Path = new ArrayList<>(Arrays.asList(new Cell[totalPathSize]));
    private ArrayList<Cell> player2Path = new ArrayList<>(Arrays.asList(new Cell[totalPathSize]));
    private ArrayList<Cell> player1Stones = new ArrayList<>();
    private ArrayList<Cell> player2Stones = new ArrayList<>();
    private ArrayList<Integer> moves = new ArrayList<>();
    private ArrayList<Cell> xPositions = new ArrayList<>();
    public int getMaxThrows() {
        return maxThrows;
    }
    public String[][] getBoard() {
        return board;
    }
    public String getPlayer2Symbol() {
        return "2";
    }
    public String getPlayer1Symbol() {
        return "1";
    }
    public ArrayList<Cell> getPlayer1Path() {
        return player1Path;
    }
    public ArrayList<Cell> getPlayer2Path() {
        return player2Path;
    }
    public ArrayList<Cell> getPlayer1Stones() {
        return player1Stones;
    }
    public ArrayList<Cell> getPlayer2Stones() {
        return player2Stones;
    }
    public ArrayList<Integer> getMoves() {
        return moves;
    }
    public int getTotalPathSize() {
        return totalPathSize;
    }
    public ArrayList<Cell> getXPositions() {
        return xPositions;
    }
    public int getHasCockedForPlayer1() {
        return hasCockedForPlayer1;
    }
    public void setHasCockedForPlayer1(int hasCockedForPlayer1) {
        this.hasCockedForPlayer1 = hasCockedForPlayer1;
    }

    public int getHasCockedForPlayer2() {
        return getHasCockedForPlayer2;
    }

    public void setHasCockedForPlayer2(int getHasCockedForPlayer2) {
        this.getHasCockedForPlayer2 = getHasCockedForPlayer2;
    }

    public int getDepth() {
        return depth;
    }
    public void setDepth(int depth) {
        this.depth = depth;
    }
    public void setPlayer1Path(ArrayList<Cell> player1Path) {
        this.player1Path = player1Path;
    }

    public void setPlayer2Path(ArrayList<Cell> player2Path) {
        this.player2Path = player2Path;
    }

    public void setPlayer1Stones(ArrayList<Cell> player1Stones) {
        this.player1Stones = player1Stones;
    }

    public void setPlayer2Stones(ArrayList<Cell> player2Stones) {
        this.player2Stones = player2Stones;
    }

    public void setMoves(ArrayList<Integer> moves) {
        this.moves = moves;
    }

    public ArrayList<Cell> getxPositions() {
        return xPositions;
    }

    public void setxPositions(ArrayList<Cell> xPositions) {
        this.xPositions = xPositions;
    }

    public Game() {
        /* Path for player 1 */
        player1Path.set(0, new Cell(11, 9, 0));
        player1Path.set(1, new Cell(12, 9, 0));
        player1Path.set(2, new Cell(13, 9, 0));
        player1Path.set(3, new Cell(14, 9, 0));
        player1Path.set(4, new Cell(15, 9, 0));
        player1Path.set(5, new Cell(16, 9, 0));
        player1Path.set(6, new Cell(17, 9, 0));
        player1Path.set(7, new Cell(18, 9, 0));

        player1Path.set(8, new Cell(18, 10, 0));
        player1Path.set(9, new Cell(17, 10, 0));
        player1Path.set(10, new Cell(16, 10, 0));
        player1Path.set(11, new Cell(15, 10, 0));
        player1Path.set(12, new Cell(14, 10, 0));
        player1Path.set(13, new Cell(13, 10, 0));
        player1Path.set(14, new Cell(12, 10, 0));
        player1Path.set(15, new Cell(11, 10, 0));

        player1Path.set(16, new Cell(10, 11, 0));
        player1Path.set(17, new Cell(10, 12, 0));
        player1Path.set(18, new Cell(10, 13, 0));
        player1Path.set(19, new Cell(10, 14, 0));
        player1Path.set(20, new Cell(10, 15, 0));
        player1Path.set(21, new Cell(10, 16, 0));
        player1Path.set(22, new Cell(10, 17, 0));
        player1Path.set(23, new Cell(10, 18, 0));

        player1Path.set(24, new Cell(9, 18, 0));

        player1Path.set(25, new Cell(8, 18, 0));
        player1Path.set(26, new Cell(8, 17, 0));
        player1Path.set(27, new Cell(8, 16, 0));
        player1Path.set(28, new Cell(8, 15, 0));
        player1Path.set(29, new Cell(8, 14, 0));
        player1Path.set(30, new Cell(8, 13, 0));
        player1Path.set(31, new Cell(8, 12, 0));
        player1Path.set(32, new Cell(8, 11, 0));

        player1Path.set(33, new Cell(7, 10, 0));
        player1Path.set(34, new Cell(6, 10, 0));
        player1Path.set(35, new Cell(5, 10, 0));
        player1Path.set(36, new Cell(4, 10, 0));
        player1Path.set(37, new Cell(3, 10, 0));
        player1Path.set(38, new Cell(2, 10, 0));
        player1Path.set(39, new Cell(1, 10, 0));
        player1Path.set(40, new Cell(0, 10, 0));

        player1Path.set(41, new Cell(0, 9, 0));

        player1Path.set(42, new Cell(0, 8, 0));
        player1Path.set(43, new Cell(1, 8, 0));
        player1Path.set(44, new Cell(2, 8, 0));
        player1Path.set(45, new Cell(3, 8, 0));
        player1Path.set(46, new Cell(4, 8, 0));
        player1Path.set(47, new Cell(5, 8, 0));
        player1Path.set(48, new Cell(6, 8, 0));
        player1Path.set(49, new Cell(7, 8, 0));

        player1Path.set(50, new Cell(8, 7, 0));
        player1Path.set(51, new Cell(8, 6, 0));
        player1Path.set(52, new Cell(8, 5, 0));
        player1Path.set(53, new Cell(8, 4, 0));
        player1Path.set(54, new Cell(8, 3, 0));
        player1Path.set(55, new Cell(8, 2, 0));
        player1Path.set(56, new Cell(8, 1, 0));
        player1Path.set(57, new Cell(8, 0, 0));

        player1Path.set(58, new Cell(9, 0, 0));

        player1Path.set(59, new Cell(10, 0, 0));
        player1Path.set(60, new Cell(10, 1, 0));
        player1Path.set(61, new Cell(10, 2, 0));
        player1Path.set(62, new Cell(10, 3, 0));
        player1Path.set(63, new Cell(10, 4, 0));
        player1Path.set(64, new Cell(10, 5, 0));
        player1Path.set(65, new Cell(10, 6, 0));
        player1Path.set(66, new Cell(10, 7, 0));

        player1Path.set(67, new Cell(11, 8, 0));
        player1Path.set(68, new Cell(12, 8, 0));
        player1Path.set(69, new Cell(13, 8, 0));
        player1Path.set(70, new Cell(14, 8, 0));
        player1Path.set(71, new Cell(15, 8, 0));
        player1Path.set(72, new Cell(16, 8, 0));
        player1Path.set(73, new Cell(17, 8, 0));
        player1Path.set(74, new Cell(18, 8, 0));

        player1Path.set(75, new Cell(18, 9, 0));
        player1Path.set(76, new Cell(17, 9, 0));
        player1Path.set(77, new Cell(16, 9, 0));
        player1Path.set(78, new Cell(15, 9, 0));
        player1Path.set(79, new Cell(14, 9, 0));
        player1Path.set(80, new Cell(13, 9, 0));
        player1Path.set(81, new Cell(12, 9, 0));
        player1Path.set(82, new Cell(11, 9, 0));

        player1Path.set(83, new Cell(10, 9, 0));

        /* Path for player 2 */
        player2Path.set(0, new Cell(7, 9, 0));
        player2Path.set(1, new Cell(6, 9, 0));
        player2Path.set(2, new Cell(5, 9, 0));
        player2Path.set(3, new Cell(4, 9, 0));
        player2Path.set(4, new Cell(3, 9, 0));
        player2Path.set(5, new Cell(2, 9, 0));
        player2Path.set(6, new Cell(1, 9, 0));
        player2Path.set(7, new Cell(0, 9, 0));

        player2Path.set(8, new Cell(0, 8, 0));
        player2Path.set(9, new Cell(1, 8, 0));
        player2Path.set(10, new Cell(2, 8, 0));
        player2Path.set(11, new Cell(3, 8, 0));
        player2Path.set(12, new Cell(4, 8, 0));
        player2Path.set(13, new Cell(5, 8, 0));
        player2Path.set(14, new Cell(6, 8, 0));
        player2Path.set(15, new Cell(7, 8, 0));

        player2Path.set(16, new Cell(8, 7, 0));
        player2Path.set(17, new Cell(8, 6, 0));
        player2Path.set(18, new Cell(8, 5, 0));
        player2Path.set(19, new Cell(8, 4, 0));
        player2Path.set(20, new Cell(8, 3, 0));
        player2Path.set(21, new Cell(8, 2, 0));
        player2Path.set(22, new Cell(8, 1, 0));
        player2Path.set(23, new Cell(8, 0, 0));

        player2Path.set(24, new Cell(9, 0, 0));

        player2Path.set(25, new Cell(10, 0, 0));
        player2Path.set(26, new Cell(10, 1, 0));
        player2Path.set(27, new Cell(10, 2, 0));
        player2Path.set(28, new Cell(10, 3, 0));
        player2Path.set(29, new Cell(10, 4, 0));
        player2Path.set(30, new Cell(10, 5, 0));
        player2Path.set(31, new Cell(10, 6, 0));
        player2Path.set(32, new Cell(10, 7, 0));

        player2Path.set(33, new Cell(11, 8, 0));
        player2Path.set(34, new Cell(12, 8, 0));
        player2Path.set(35, new Cell(13, 8, 0));
        player2Path.set(36, new Cell(14, 8, 0));
        player2Path.set(37, new Cell(15, 8, 0));
        player2Path.set(38, new Cell(16, 8, 0));
        player2Path.set(39, new Cell(17, 8, 0));
        player2Path.set(40, new Cell(18, 8, 0));

        player2Path.set(41, new Cell(18, 9, 0));

        player2Path.set(42, new Cell(18, 10, 0));
        player2Path.set(43, new Cell(17, 10, 0));
        player2Path.set(44, new Cell(16, 10, 0));
        player2Path.set(45, new Cell(15, 10, 0));
        player2Path.set(46, new Cell(14, 10, 0));
        player2Path.set(47, new Cell(13, 10, 0));
        player2Path.set(48, new Cell(12, 10, 0));
        player2Path.set(49, new Cell(11, 10, 0));

        player2Path.set(50, new Cell(10, 11, 0));
        player2Path.set(51, new Cell(10, 12, 0));
        player2Path.set(52, new Cell(10, 13, 0));
        player2Path.set(53, new Cell(10, 14, 0));
        player2Path.set(54, new Cell(10, 15, 0));
        player2Path.set(55, new Cell(10, 16, 0));
        player2Path.set(56, new Cell(10, 17, 0));
        player2Path.set(57, new Cell(10, 18, 0));

        player2Path.set(58, new Cell(9, 18, 0));

        player2Path.set(59, new Cell(8, 18, 0));
        player2Path.set(60, new Cell(8, 17, 0));
        player2Path.set(61, new Cell(8, 16, 0));
        player2Path.set(62, new Cell(8, 15, 0));
        player2Path.set(63, new Cell(8, 14, 0));
        player2Path.set(64, new Cell(8, 13, 0));
        player2Path.set(65, new Cell(8, 12, 0));
        player2Path.set(66, new Cell(8, 11, 0));

        player2Path.set(67, new Cell(7, 10, 0));
        player2Path.set(68, new Cell(6, 10, 0));
        player2Path.set(69, new Cell(5, 10, 0));
        player2Path.set(70, new Cell(4, 10, 0));
        player2Path.set(71, new Cell(3, 10, 0));
        player2Path.set(72, new Cell(2, 10, 0));
        player2Path.set(73, new Cell(1, 10, 0));
        player2Path.set(74, new Cell(0, 10, 0));

        player2Path.set(75, new Cell(0, 9, 0));
        player2Path.set(76, new Cell(1, 9, 0));
        player2Path.set(77, new Cell(2, 9, 0));
        player2Path.set(78, new Cell(3, 9, 0));
        player2Path.set(79, new Cell(4, 9, 0));
        player2Path.set(80, new Cell(5, 9, 0));
        player2Path.set(81, new Cell(6, 9, 0));
        player2Path.set(82, new Cell(7, 9, 0));

        player2Path.set(83, new Cell(8, 9, 0));

        /* All X Positions */
        xPositions.add(new Cell(16, 8, 3));
        xPositions.add(new Cell(16, 10, 3));
        xPositions.add(new Cell(10, 15, 3));
        xPositions.add(new Cell(8, 15, 3));
        xPositions.add(new Cell(2, 8, 3));
        xPositions.add(new Cell(2, 10, 3));
        xPositions.add(new Cell(8, 2, 3));
        xPositions.add(new Cell(10, 2, 3));

        /* Initialize the board */
        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                board[i][j] = "O";
            }
        }

        /* Top section */
        for (int i = 0; i <= 7; i++) {
            for (int j = 8; j <= 10; j++) {
                board[i][j] = "_";
            }
        }

        /* Bottom Section */
        for (int i = 11; i <= 18; i++) {
            for (int j = 8; j <= 10; j++) {
                board[i][j] = "_";
            }
        }

        /* Left section */
        for (int i = 8; i <= 10; i++) {
            for (int j = 0; j <= 7; j++) {
                board[i][j] = "_";
            }
        }

        /* Right section */
        for (int i = 8; i <= 10; i++) {
            for (int j = 11; j <= 18; j++) {
                board[i][j] = "_";
            }
        }
    }

    public void printBoard() {
        for (Cell current : xPositions) {
            board[current.getI()][current.getJ()] = "X";
        }
        for (Cell current : player1Stones) {
            if(Objects.equals(board[current.getI()][current.getJ()], "_") || Objects.equals(board[current.getI()][current.getJ()], "X")) {
                board[current.getI()][current.getJ()] = String.valueOf(getPlayer1Symbol());
            }
            else if(Objects.equals(board[current.getI()][current.getJ()], "1")) {
                board[current.getI()][current.getJ()] = "1.2";
            }
            else if(Objects.equals(board[current.getI()][current.getJ()], "1.2")) {
                board[current.getI()][current.getJ()] = "1.3";
            }
            else if(Objects.equals(board[current.getI()][current.getJ()], "1.3")) {
                board[current.getI()][current.getJ()] = "1.4";
            }
        }
        for (Cell current : player2Stones) {
            if(Objects.equals(board[current.getI()][current.getJ()], "_")|| Objects.equals(board[current.getI()][current.getJ()], "X")) {
                board[current.getI()][current.getJ()] = String.valueOf(getPlayer2Symbol());
            }
            else if(Objects.equals(board[current.getI()][current.getJ()], "2")) {
                board[current.getI()][current.getJ()] = "2.2";
            }
            else if(Objects.equals(board[current.getI()][current.getJ()], "2.2")) {
                board[current.getI()][current.getJ()] = "2.3";
            }
            else if(Objects.equals(board[current.getI()][current.getJ()], "2.3")) {
                board[current.getI()][current.getJ()] = "2.4";
            }
        }

        System.out.print("\t");
        for (int i = 0; i < length; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int i = 0; i < length; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < length; j++) {
                if (!Objects.equals(board[i][j], "O")) {
                    System.out.print(board[i][j] + "\t");
                } else {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean isFinal() {
        if (hasCockedForPlayer1 == 4) {
            System.out.println("Player One is The Winner!");
            return true;
        } else if (getHasCockedForPlayer2 == 4) {
            System.out.println("Player Two is The Winner!");
            return true;
        } else {
            return false;
        }
    }

    public int throwSeashells() {
        int[] seaShell = new int[6];
        for(int i = 0; i < 6; i++) {
            seaShell[i] = (int) (Math.random() * 2);
        }
        int throwValue = 0;
        for(int i = 0; i < 6; i++) {
            if(seaShell[i] == 1) {
                throwValue++;
            }
        }
        return throwValue;
    }

    public void start() {
        expectiminimax.generateAllMoves(this, new ArrayList<>(), 0);
        expectiminimax.calculateFact();

        printBoard();
        Movement move = new Movement();
        int player = 1;

        while (!isFinal()) {
            System.out.println();
            moves.clear();
            System.out.println("Player " + player + " turn.");

            int throwValue = 0, numberOfThrows = 0;

            if (player == 1) {
                if (player1Stones.isEmpty()) {
                    while (throwValue != 5 && throwValue != 1 && numberOfThrows < 3) {
                        numberOfThrows++;

                        /* Throw seashells */
                        System.out.println("Press Enter key to throw the seashells");
                        scanner.nextLine();
                        throwValue = throwSeashells();

                        if (throwValue == 0) {
                            System.out.println("You have gat Bara(0). You can't put a stone.\n");
                        } else if (throwValue == 2) {
                            System.out.println("You have gat two(2). You can't put a stone.\n");
                        } else if (throwValue == 3) {
                            System.out.println("You have gat three(3). You can't put a stone.\n");
                        } else if (throwValue == 4) {
                            System.out.println("You have gat four(4). You can't put a stone.\n");
                        } else if (throwValue == 6) {
                            System.out.println("You have gat Shaka(6). You can't put a stone.\n");
                        }
                    }
                    if (throwValue == 5) {
                        System.out.println("You have gat Dst(5). Press Enter key to continue.\n");
                        scanner.nextLine();

                        moves.add(10);
                        moves.add(1);
                        numberOfThrows = 1;
                    } else if (throwValue == 1) {
                        System.out.println("You have gat Bng(1). Press Enter key to continue.\n");
                        scanner.nextLine();

                        moves.add(24);
                        moves.add(1);
                        numberOfThrows = 1;
                    } else {
                        if (throwValue == 0) {
                            System.out.println("You have gat Bara(0). You can't put a stone.\n");
                        } else if (throwValue == 2) {
                            System.out.println("You have gat two(2). You can't put a stone.\n");
                        } else if (throwValue == 3) {
                            System.out.println("You have gat three(3). You can't put a stone.\n");
                        } else if (throwValue == 4) {
                            System.out.println("You have gat four(4). You can't put a stone.\n");
                        } else if (throwValue == 6) {
                            System.out.println("You have gat Shaka(6). You can't put a stone.\n");
                        }
                        System.out.println("You can't make a move. Player 2 will take turn.\n");

                        throwValue = -1;
                        numberOfThrows = 100;
                    }
                }
            } else {
                if (player2Stones.isEmpty()) {
                    while (throwValue != 5 && throwValue != 1 && numberOfThrows < 3) {
                        numberOfThrows++;

                        /* Throw seashells */
                        System.out.println("Press Enter key to throw the seashells");
                        scanner.nextLine();
                        throwValue = throwSeashells();

                        if (throwValue == 0) {
                            System.out.println("You have gat Bara(0). You can't put a stone.\n");
                        } else if (throwValue == 2) {
                            System.out.println("You have gat two(2). You can't put a stone.\n");
                        } else if (throwValue == 3) {
                            System.out.println("You have gat three(3). You can't put a stone.\n");
                        } else if (throwValue == 4) {
                            System.out.println("You have gat four(4). You can't put a stone.\n");
                        } else if (throwValue == 6) {
                            System.out.println("You have gat Shaka(6). You can't put a stone.\n");
                        }
                    }
                    if (throwValue == 5) {
                        System.out.println("You have gat Dst(5). Press Enter key to continue.\n");
                        scanner.nextLine();

                        moves.add(10);
                        moves.add(1);
                        numberOfThrows = 1;
                    } else if (throwValue == 1) {
                        System.out.println("You have gat Bng(1). Press Enter key to continue.\n");
                        scanner.nextLine();

                        moves.add(24);
                        moves.add(1);
                        numberOfThrows = 1;
                    } else {
                        if (throwValue == 0) {
                            System.out.println("You have gat Bara(0). You can't put a stone.\n");
                        } else if (throwValue == 2) {
                            System.out.println("You have gat two(2). You can't put a stone.\n");
                        } else if (throwValue == 3) {
                            System.out.println("You have gat three(3). You can't put a stone.\n");
                        } else if (throwValue == 4) {
                            System.out.println("You have gat four(4). You can't put a stone.\n");
                        } else if (throwValue == 6) {
                            System.out.println("You have gat Shaka(6). You can't put a stone.\n");
                        }
                        System.out.println("You can't make a move. Player 1 will take turn.\n");

                        throwValue = -1;
                        numberOfThrows = 100;
                    }
                }
            }

            while (throwValue != -1 && throwValue != 2 && throwValue != 3 && throwValue != 4 && numberOfThrows < maxThrows) {
                numberOfThrows++;

                /* Throw seashells */
                System.out.println("Press Enter key to throw the seashells");
                scanner.nextLine();
                throwValue = throwSeashells();

                if (throwValue == 0) {
                    System.out.println("You have gat Bara(0).");
                    moves.add(12);
                } else if (throwValue == 1) {
                    System.out.println("You have gat Bng(1).");
                    moves.add(24);
                    moves.add(1);
                } else if (throwValue == 2) {
                    System.out.println("You have gat two(2).");
                    moves.add(2);
                } else if (throwValue == 3) {
                    System.out.println("You have gat three(3).");
                    moves.add(3);
                } else if (throwValue == 4) {
                    System.out.println("You have gat four(4).");
                    moves.add(4);
                } else if (throwValue == 5) {
                    System.out.println("You have gat Dst(5).");
                    moves.add(10);
                    moves.add(1);
                } else if (throwValue == 6) {
                    System.out.println("You have gat Shaka(6).");
                    moves.add(6);
                }
            }
            System.out.println("Moves:");
            for (Integer integer : moves) {
                System.out.print(integer + " ");
            }
            System.out.println();

            if (player == 1) {
                expectiminimax.getPermutation().clear();
                expectiminimax.getPermutation(moves, 0);

                Game currentGame = expectiminimax.copyGame(this);
                List<Object> bestBoard = expectiminimax.EXPECTIMAX(currentGame, 1);
                Game bestGame = (Game) bestBoard.get(0);

                initializeBoard();
                this.setHasCockedForPlayer1(bestGame.getHasCockedForPlayer1());
                this.setHasCockedForPlayer2(bestGame.getHasCockedForPlayer2());
                for (int i = 0; i < totalPathSize; i++) {
                    this.getPlayer1Path().set(i, bestGame.getPlayer1Path().get(i));
                }
                for (int i = 0; i < totalPathSize; i++) {
                    this.getPlayer2Path().set(i, bestGame.getPlayer2Path().get(i));
                }
                this.getPlayer1Stones().clear();
                for (Cell stone : bestGame.getPlayer1Stones()) {
                    this.getPlayer1Stones().add(new Cell(stone.getI(), stone.getJ(), stone.getTmp()));
                }
                this.getPlayer2Stones().clear();
                for (Cell stone : bestGame.getPlayer2Stones()) {
                    this.getPlayer2Stones().add(new Cell(stone.getI(), stone.getJ(), stone.getTmp()));
                }

                this.printBoard();
            } else {
                move.move(this, 2);
            }

            if (player == 1) {
                player = 2;
            } else {
                player = 1;
            }
        }
    }
}
