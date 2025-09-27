import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Movement {
    Scanner scanner = new Scanner(System.in);

    public void putStoneByDst(Game game, int player) {
        if (player == 1) {
            for (int i = 0; i < game.getTotalPathSize(); i++) {
                Cell stone = game.getPlayer1Path().get(i);
                if (stone.getI() == 16 && stone.getJ() == 10) {
                    stone.setTmp(1);
                    game.getPlayer1Stones().add(new Cell(16, 10, i));
                    break;
                }
            }
        } else {
            for (int i = 0; i < game.getTotalPathSize(); i++) {
                Cell stone = game.getPlayer2Path().get(i);
                if (stone.getI() == 2 && stone.getJ() == 8) {
                    stone.setTmp(1);
                    game.getPlayer2Stones().add(new Cell(2, 8, i));
                    break;
                }
            }
        }
    }

    public void putStoneByBng(Game game, int player) {
        if (player == 1) {
            for (int i = 0; i < game.getTotalPathSize(); i++) {
                Cell stone = game.getPlayer1Path().get(i);
                if (stone.getI() == 8 && stone.getJ() == 18) {
                    stone.setTmp(1);
                    game.getPlayer1Stones().add(new Cell(8, 18, i));
                    break;
                }
            }
            if (Objects.equals(game.getBoard()[8][18], game.getPlayer2Symbol()) ||
                    Objects.equals(game.getBoard()[8][18], "2.2") ||
                    Objects.equals(game.getBoard()[8][18], "2.3") ||
                    Objects.equals(game.getBoard()[8][18], "2.4")) {
                for (Cell stone : game.getPlayer2Path()) {
                    if (stone.getI() == 8 && stone.getJ() == 18) {
                        stone.setTmp(0);
                        break;
                    }
                }

                for (Cell stone : game.getPlayer2Stones()) {
                    if (stone.getI() == 8 && stone.getJ() == 18) {
                        game.getPlayer2Stones().remove(stone);
                        break;
                    }
                }
            }
        } else {
            for (int i = 0; i < game.getTotalPathSize(); i++) {
                Cell stone = game.getPlayer2Path().get(i);
                if (stone.getI() == 10 && stone.getJ() == 0) {
                    stone.setTmp(1);
                    game.getPlayer2Stones().add(new Cell(10, 0, i));
                    break;
                }
            }

            if (Objects.equals(game.getBoard()[10][0], game.getPlayer1Symbol()) ||
                    Objects.equals(game.getBoard()[10][0], "1.2") ||
                    Objects.equals(game.getBoard()[10][0], "1.3") ||
                    Objects.equals(game.getBoard()[10][0], "1.4")
            ) {
                for (Cell stone : game.getPlayer1Path()) {
                    if (stone.getI() == 10 && stone.getJ() == 0) {
                        stone.setTmp(0);
                        break;
                    }
                }

                for (Cell stone : game.getPlayer1Stones()) {
                    if (stone.getI() == 10 && stone.getJ() == 0) {
                        game.getPlayer1Stones().remove(stone);
                        break;
                    }
                }
            }
        }
    }

    public void putStoneByOne(Game game, int player) {
        if (player == 1) {
            for (int i = 0; i < game.getTotalPathSize(); i++) {
                Cell stone = game.getPlayer1Path().get(i);
                if (stone.getI() == 11 && stone.getJ() == 9) {
                    stone.setTmp(1);
                    game.getPlayer1Stones().add(new Cell(11, 9, i));
                    break;
                }
            }
        } else {
            for (int i = 0; i < game.getTotalPathSize(); i++) {
                Cell stone = game.getPlayer2Path().get(i);
                if (stone.getI() == 7 && stone.getJ() == 9) {
                    stone.setTmp(1);
                    game.getPlayer2Stones().add(new Cell(7, 9, i));
                    break;
                }
            }
        }
    }

    public void printAllMoves(Game game) {
        System.out.print("[");
        for (int i = 0; i < game.getMoves().size(); i++) {
            if (game.getMoves().get(i) == 1) {
                System.out.print("One(1)");
            } else if (game.getMoves().get(i) == 2) {
                System.out.print("Two(2)");
            } else if (game.getMoves().get(i) == 3) {
                System.out.print("Three(3)");
            } else if (game.getMoves().get(i) == 4) {
                System.out.print("Four(4)");
            } else if (game.getMoves().get(i) == 6) {
                System.out.print("Shaka(6)");
            } else if (game.getMoves().get(i) == 10) {
                System.out.print("Dst(5) = 10");
            } else if (game.getMoves().get(i) == 12) {
                System.out.print("Bara(0) = 12");
            } else if (game.getMoves().get(i) == 24) {
                System.out.print("Bng(1) = 24");
            }

            if (i != game.getMoves().size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public void printAllAvailableMovesPlayerOne(Game game, ArrayList<CustomPair> availableMoves) {
        for (int i = 0; i < availableMoves.size(); i++) {
            System.out.print(i + 1 + ". ");
            int moveIndex = availableMoves.get(i).getFirst(), stoneIndex = availableMoves.get(i).getSecond();
            int move = game.getMoves().get(moveIndex);
            if (move == 1) {
                if (stoneIndex == 10) {
                    System.out.println("You can put a stone by Dst(5).");
                } else if (stoneIndex == 24) {
                    System.out.println("You can put a stone by Bng(1).");
                } else if (stoneIndex == 100) {
                    System.out.println("You can put a stone by One(1).");
                } else {
                    int pathIndex = game.getPlayer1Stones().get(stoneIndex).getTmp();
                    Cell currentPosition = game.getPlayer1Path().get(pathIndex);
                    Cell nextPosition = game.getPlayer1Path().get(pathIndex + move);
                    System.out.println("Move Stone From (" + currentPosition.getI() + ", " + currentPosition.getJ() + ") to (" + nextPosition.getI() + ", " + nextPosition.getJ() + ")");
                }
            } else {
                int pathIndex = game.getPlayer1Stones().get(stoneIndex).getTmp();
                Cell currentPosition = game.getPlayer1Path().get(pathIndex);
                Cell nextPosition = game.getPlayer1Path().get(pathIndex + move);
                System.out.println("Move Stone From (" + currentPosition.getI() + ", " + currentPosition.getJ() + ") to (" + nextPosition.getI() + ", " + nextPosition.getJ() + ")");
            }
        }
    }

    public void printAllAvailableMovesPlayerTwo(Game game, ArrayList<CustomPair> availableMoves) {
        for (int i = 0; i < availableMoves.size(); i++) {
            System.out.print(i + 1 + ". ");
            int moveIndex = availableMoves.get(i).getFirst(), stoneIndex = availableMoves.get(i).getSecond();
            int move = game.getMoves().get(moveIndex);
            if (move == 1) {
                if (stoneIndex == 10) {
                    System.out.println("You can put a stone by Dst(5).");
                } else if (stoneIndex == 24) {
                    System.out.println("You can put a stone by Bng.");
                } else if (stoneIndex == 100) {
                    System.out.println("You can put a stone by One(1).");
                } else {
                    int pathIndex = game.getPlayer2Stones().get(stoneIndex).getTmp();
                    Cell currentPosition = game.getPlayer2Path().get(pathIndex);
                    Cell nextPosition = game.getPlayer2Path().get(pathIndex + move);
                    System.out.println("Move Stone From (" + currentPosition.getI() + ", " + currentPosition.getJ() + ") to (" + nextPosition.getI() + ", " + nextPosition.getJ() + ")");
                }
            } else {
                int pathIndex = game.getPlayer2Stones().get(stoneIndex).getTmp();
                Cell currentPosition = game.getPlayer2Path().get(pathIndex);
                Cell nextPosition = game.getPlayer2Path().get(pathIndex + move);
                System.out.println("Move Stone From (" + currentPosition.getI() + ", " + currentPosition.getJ() + ") to (" + nextPosition.getI() + ", " + nextPosition.getJ() + ")");
            }
        }
    }


    public boolean checkMoveForFirstPlayer(Game game, int newPosition) {
        if (newPosition >= game.getTotalPathSize()) {
            return false;
        }

        for (Cell currentXPosition : game.getXPositions()) {
            int newI = game.getPlayer1Path().get(newPosition).getI();
            int newJ = game.getPlayer1Path().get(newPosition).getJ();

            if (newI == currentXPosition.getI() && newJ == currentXPosition.getJ() && (Objects.equals(game.getBoard()[newI][newJ], game.getPlayer2Symbol()) ||
                    Objects.equals(game.getBoard()[newI][newJ], "2.2") ||
                    Objects.equals(game.getBoard()[newI][newJ], "2.3") ||
                    Objects.equals(game.getBoard()[newI][newJ], "2.4"))) {
                return false;
            }
        }

        return true;
    }

    public boolean checkMoveForSecondPlayer(Game game, int newPosition) {
        if (newPosition >= game.getTotalPathSize()) {
            return false;
        }

        for (Cell currentXPosition : game.getXPositions()) {
            int newI = game.getPlayer2Path().get(newPosition).getI();
            int newJ = game.getPlayer2Path().get(newPosition).getJ();

            if (newI == currentXPosition.getI() && newJ == currentXPosition.getJ() && (Objects.equals(game.getBoard()[newI][newJ], game.getPlayer1Symbol()) ||
                    Objects.equals(game.getBoard()[newI][newJ], "1.2") ||
                    Objects.equals(game.getBoard()[newI][newJ], "1.3") ||
                    Objects.equals(game.getBoard()[newI][newJ], "1.4"))) {
                return false;
            }
        }

        return true;
    }

    public void makeMoveForFirstPlayer(Game game, Cell stone, int move) {
        int newPosition = stone.getTmp() + move;
        Cell oldCell = game.getPlayer1Path().get(stone.getTmp());
        Cell newCell = game.getPlayer1Path().get(newPosition);
        oldCell.setTmp(0);
        newCell.setTmp(1);
        /* Change stone position */
        for (Cell currentStone : game.getPlayer1Stones()) {
            if (currentStone.getI() == stone.getI() && currentStone.getJ() == stone.getJ()) {
                game.getBoard()[stone.getI()][stone.getJ()] = "_";
                currentStone.setI(newCell.getI());
                currentStone.setJ(newCell.getJ());
                currentStone.setTmp(newPosition);
                break;
            }
        }
        /* Remove player two stone */
        if (Objects.equals(game.getBoard()[newCell.getI()][newCell.getJ()], game.getPlayer2Symbol()) ||
                Objects.equals(game.getBoard()[newCell.getI()][newCell.getJ()], "2.2") ||
                Objects.equals(game.getBoard()[newCell.getI()][newCell.getJ()], "2.3") ||
                Objects.equals(game.getBoard()[newCell.getI()][newCell.getJ()], "2.4")) {
            ArrayList<Cell> toRemove = new ArrayList<>();
            for (int i = 0; i < game.getPlayer2Stones().size(); i++) {
                Cell playerTwoStone = game.getPlayer2Stones().get(i);

                if (playerTwoStone.getI() == newCell.getI() && playerTwoStone.getJ() == newCell.getJ()) {
                    toRemove.add(playerTwoStone);
                }
            }
            game.getPlayer2Stones().removeAll(toRemove);
            for (Cell currentCell : game.getPlayer2Path()) {
                if (newCell.getI() == currentCell.getI() && newCell.getJ() == currentCell.getJ()) {
                    currentCell.setTmp(0);
                    break;
                }
            }
        }
        if (newPosition == 83) {
            game.setHasCockedForPlayer1(game.getHasCockedForPlayer1() + 1);
        }
    }

    public void makeMoveForSecondPlayer(Game game, Cell stone, int move) {
        int newPosition = stone.getTmp() + move;
        Cell oldCell = game.getPlayer2Path().get(stone.getTmp());
        Cell newCell = game.getPlayer2Path().get(newPosition);
        oldCell.setTmp(0);
        newCell.setTmp(1);
        /* Change stone position */
        for (Cell currentStone : game.getPlayer2Stones()) {
            if (currentStone.getI() == stone.getI() && currentStone.getJ() == stone.getJ()) {
                game.getBoard()[stone.getI()][stone.getJ()] = "_";
                currentStone.setI(newCell.getI());
                currentStone.setJ(newCell.getJ());
                currentStone.setTmp(newPosition);
                break;
            }
        }
        /* Remove player one stone */
        if (Objects.equals(game.getBoard()[newCell.getI()][newCell.getJ()], game.getPlayer1Symbol()) ||
                Objects.equals(game.getBoard()[newCell.getI()][newCell.getJ()], "1.2") ||
                Objects.equals(game.getBoard()[newCell.getI()][newCell.getJ()], "1.3") ||
                Objects.equals(game.getBoard()[newCell.getI()][newCell.getJ()], "1.4")) {
            ArrayList<Cell> toRemove = new ArrayList<>();
            for (int i = 0; i < game.getPlayer1Stones().size(); i++) {
                Cell playerOneStone = game.getPlayer1Stones().get(i);

                if (playerOneStone.getI() == newCell.getI() && playerOneStone.getJ() == newCell.getJ()) {
                    toRemove.add(playerOneStone);
                }
            }
            game.getPlayer1Stones().removeAll(toRemove);
            for (Cell current : game.getPlayer1Path()) {
                if (newCell.getI() == current.getI() && newCell.getJ() == current.getJ()) {
                    current.setTmp(0);
                    break;
                }
            }
        }

        if (newPosition == 83) {
            game.setHasCockedForPlayer2(game.getHasCockedForPlayer2() + 1);
        }
    }


    public void makeMoveForPlayer1(Game game, ArrayList<CustomPair> availableMoves, int whichMove) {
        int moveIndex = availableMoves.get(whichMove).getFirst(), stoneIndex = availableMoves.get(whichMove).getSecond();
        int move = game.getMoves().get(moveIndex);
        if (move == 1 && (stoneIndex == 10 || stoneIndex == 24 || stoneIndex == 100)) {
            if (stoneIndex == 10) { /* You can put stone by Dst */
                for (int i = 0; i < game.getTotalPathSize(); i++) {
                    Cell stone = game.getPlayer1Path().get(i);
                    if (stone.getI() == 16 && stone.getJ() == 10) {
                        stone.setTmp(1);
                        game.getPlayer1Stones().add(new Cell(16, 10, i));

                        game.getMoves().remove(moveIndex);
                        game.getMoves().remove(moveIndex - 1);
                        break;
                    }
                }
            } else if (stoneIndex == 24) { /* You can put stone by Bng */
                for (int i = 0; i < game.getTotalPathSize(); i++) {
                    Cell stone = game.getPlayer1Path().get(i);
                    if (stone.getI() == 8 && stone.getJ() == 18) {
                        stone.setTmp(1);
                        game.getPlayer1Stones().add(new Cell(8, 18, i));

                        game.getMoves().remove(moveIndex);
                        game.getMoves().remove(moveIndex - 1);
                        break;
                    }
                }
            } else { /* stoneIndex == 100, You can put stone by one */
                for (int i = 0; i < game.getTotalPathSize(); i++) {
                    Cell stone = game.getPlayer1Path().get(i);
                    if (stone.getI() == 11 && stone.getJ() == 9) {
                        stone.setTmp(1);
                        game.getPlayer1Stones().add(new Cell(11, 9, i));

                        game.getMoves().remove(moveIndex);
                        break;
                    }
                }
            }
        } else {
            Cell stone = game.getPlayer1Stones().get(stoneIndex);
            int newPosition = stone.getTmp() + move;
            Cell oldCell = game.getPlayer1Path().get(stone.getTmp());
            Cell newCell = game.getPlayer1Path().get(newPosition);
            oldCell.setTmp(0);
            newCell.setTmp(1);
            /* Change stone position */
            for (Cell current : game.getPlayer1Stones()) {
                if (current.getI() == stone.getI() && current.getJ() == stone.getJ()) {
                    game.getBoard()[stone.getI()][stone.getJ()] = "_";
                    current.setI(newCell.getI());
                    current.setJ(newCell.getJ());
                    current.setTmp(newPosition);
                    break;
                }
            }
            /* Remove player two position */
            if (Objects.equals(game.getBoard()[newCell.getI()][newCell.getJ()], game.getPlayer2Symbol()) ||
                    Objects.equals(game.getBoard()[newCell.getI()][newCell.getJ()], "2.2") ||
                    Objects.equals(game.getBoard()[newCell.getI()][newCell.getJ()], "2.3") ||
                    Objects.equals(game.getBoard()[newCell.getI()][newCell.getJ()], "2.4")) {
                for (int i = 0; i < game.getPlayer2Stones().size(); i++) {
                    Cell playerTwoStone = game.getPlayer2Stones().get(i);

                    if (playerTwoStone.getI() == newCell.getI() && playerTwoStone.getJ() == newCell.getJ()) {
                        game.getPlayer2Stones().remove(i);
                        break;
                    }
                }

                for (Cell current : game.getPlayer2Path()) {
                    if (newCell.getI() == current.getI() && newCell.getJ() == current.getJ()) {
                        current.setTmp(0);
                        break;
                    }
                }
            }
            if (newPosition == 83) {
                game.setHasCockedForPlayer1(game.getHasCockedForPlayer1() + 1);
            }
            game.getMoves().remove(moveIndex);
        }
    }

    public void makeMoveForPlayer2(Game game, ArrayList<CustomPair> availableMoves, int whichMove) {
        int moveIndex = availableMoves.get(whichMove).getFirst(), stoneIndex = availableMoves.get(whichMove).getSecond();
        int move = game.getMoves().get(moveIndex);
        if (move == 1 && (stoneIndex == 10 || stoneIndex == 24 || stoneIndex == 100)) {
            if (stoneIndex == 10) { /* You can put stone by Dst */
                for (int i = 0; i < game.getTotalPathSize(); i++) {
                    Cell stone = game.getPlayer2Path().get(i);
                    if (stone.getI() == 2 && stone.getJ() == 8) {
                        stone.setTmp(1);
                        game.getPlayer2Stones().add(new Cell(2, 8, i));

                        game.getMoves().remove(moveIndex);
                        game.getMoves().remove(moveIndex - 1);
                        break;
                    }
                }
            } else if (stoneIndex == 24) { /* You can put stone by Bng */
                for (int i = 0; i < game.getTotalPathSize(); i++) {
                    Cell stone = game.getPlayer2Path().get(i);
                    if (stone.getI() == 10 && stone.getJ() == 0) {
                        stone.setTmp(1);
                        game.getPlayer2Stones().add(new Cell(10, 0, i));

                        game.getMoves().remove(moveIndex);
                        game.getMoves().remove(moveIndex - 1);
                        break;
                    }
                }
            } else { /* stoneIndex == 100, You can put stone by one */
                for (int i = 0; i < game.getTotalPathSize(); i++) {
                    Cell stone = game.getPlayer2Path().get(i);
                    if (stone.getI() == 7 && stone.getJ() == 9) {
                        stone.setTmp(1);
                        game.getPlayer2Stones().add(new Cell(7, 9, i));

                        game.getMoves().remove(moveIndex);
                        break;
                    }
                }
            }
        } else {
            Cell stone = game.getPlayer2Stones().get(stoneIndex);
            int newPosition = stone.getTmp() + move;
            Cell oldCell = game.getPlayer2Path().get(stone.getTmp());
            Cell newCell = game.getPlayer2Path().get(newPosition);

            oldCell.setTmp(0);
            newCell.setTmp(1);

            for (Cell current : game.getPlayer2Stones()) {
                if (current.getI() == stone.getI() && current.getJ() == stone.getJ()) {
                    game.getBoard()[stone.getI()][stone.getJ()] = "_";
                    current.setI(newCell.getI());
                    current.setJ(newCell.getJ());
                    current.setTmp(newPosition);
                    break;
                }
            }

            if (Objects.equals(game.getBoard()[newCell.getI()][newCell.getJ()], game.getPlayer1Symbol()) ||
                    Objects.equals(game.getBoard()[newCell.getI()][newCell.getJ()], "1.2") ||
                    Objects.equals(game.getBoard()[newCell.getI()][newCell.getJ()], "1.3") ||
                    Objects.equals(game.getBoard()[newCell.getI()][newCell.getJ()], "1.4")
            ) {
                ArrayList<Cell> toRemove = new ArrayList<>();
                for (int i = 0; i < game.getPlayer1Stones().size(); i++) {
                    Cell playerOneStone = game.getPlayer1Stones().get(i);

                    if (playerOneStone.getI() == newCell.getI() && playerOneStone.getJ() == newCell.getJ()) {
                        toRemove.add(playerOneStone);
                    }
                }
                game.getPlayer1Stones().removeAll(toRemove);

                for (Cell current : game.getPlayer1Path()) {
                    if (newCell.getI() == current.getI() && newCell.getJ() == current.getJ()) {
                        current.setTmp(0);
                        break;
                    }
                }
            }

            if (newPosition == 83) {
                game.setHasCockedForPlayer2(game.getHasCockedForPlayer2() + 1);
            }

            game.getMoves().remove(moveIndex);
        }
    }


    public void move(Game game, int player) {
        if (player == 1) {
            while (!game.getMoves().isEmpty()) {
                System.out.println("Player 1 has these all moves:");
                printAllMoves(game);
                /* First = moveIndex, Second = stoneIndex (unless you can put a stone) */
                ArrayList<CustomPair> availableMoves = new ArrayList<>();
                for (int i = 0; i < game.getMoves().size(); i++) {
                    int currentMove = game.getMoves().get(i);
                    for (int j = 0; j < game.getPlayer1Stones().size(); j++) {
                        Cell currentStone = game.getPlayer1Stones().get(j);
                        int newPosition = currentStone.getTmp() + currentMove;
                        if (checkMoveForFirstPlayer(game, newPosition)) {
                            CustomPair tmp = new CustomPair(i, j);
                            availableMoves.add(tmp);
                        }
                    }

                    if (i != 0) {
                        int previousMove = game.getMoves().get(i - 1);
                        int numOfPlayerOneStone = game.getPlayer1Stones().size();

                        if (currentMove == 1 && previousMove == 10 && numOfPlayerOneStone < 4 && (Objects.equals(game.getBoard()[16][10], game.getPlayer2Symbol()) ||
                                Objects.equals(game.getBoard()[16][10], "2.2") ||
                                Objects.equals(game.getBoard()[16][10], "2.3") ||
                                Objects.equals(game.getBoard()[16][10], "2.4"))) {
                            /* Second = 10 => to know you can put stone by Dst */
                            CustomPair tmp = new CustomPair(i, 10);
                            availableMoves.add(tmp);
                        } else if (currentMove == 1 && previousMove == 24 && numOfPlayerOneStone < 4) {
                            /* Second = 24 => to know you can put stone by Bng */
                            CustomPair tmp = new CustomPair(i, 24);
                            availableMoves.add(tmp);
                        } else if (currentMove == 1 && numOfPlayerOneStone < 4) {
                            /* Second = 100 => to know you can put stone by One(1) */
                            CustomPair tmp = new CustomPair(i, 100);
                            availableMoves.add(tmp);
                        }
                    }
                }

                if (!availableMoves.isEmpty()) {
                    printAllAvailableMovesPlayerOne(game, availableMoves);
                    int whichMove = scanner.nextInt();
                    whichMove--;

                    makeMoveForPlayer1(game, availableMoves, whichMove);
                    game.printBoard();
                } else {
                    break;
                }
            }
        } else {
            while (!game.getMoves().isEmpty()) {
                System.out.println("Player 2 has these all moves:");
                printAllMoves(game);

                /* First = moveIndex, Second = stoneIndex (unless you can put a stone) */
                ArrayList<CustomPair> availableMoves = new ArrayList<>();
                for (int i = 0; i < game.getMoves().size(); i++) {
                    int currentMove = game.getMoves().get(i);
                    for (int j = 0; j < game.getPlayer2Stones().size(); j++) {
                        Cell currentStone = game.getPlayer2Stones().get(j);
                        int newPosition = currentStone.getTmp() + currentMove;
                        if (checkMoveForSecondPlayer(game, newPosition)) {
                            CustomPair tmp = new CustomPair(i, j);
                            availableMoves.add(tmp);
                        }
                    }

                    if (i != 0) {
                        int previousMove = game.getMoves().get(i - 1);
                        int numOfPlayerOneStone = game.getPlayer2Stones().size();

                        if (currentMove == 1 && previousMove == 10 && numOfPlayerOneStone < 4 && (Objects.equals(game.getBoard()[2][8], game.getPlayer2Symbol()) ||
                                Objects.equals(game.getBoard()[2][8], "2.2") ||
                                Objects.equals(game.getBoard()[2][8], "2.3") ||
                                Objects.equals(game.getBoard()[2][8], "2.4"))) {
                            /* Second = 10 => to know you can put stone by Dst */
                            CustomPair tmp = new CustomPair(i, 10);
                            availableMoves.add(tmp);
                        } else if (currentMove == 1 && previousMove == 24 && numOfPlayerOneStone < 4) {
                            /* Second = 24 => to know you can put stone by Bng */
                            CustomPair tmp = new CustomPair(i, 24);
                            availableMoves.add(tmp);
                        } else if (currentMove == 1 && numOfPlayerOneStone < 4) {
                            /* Second = 100 => to know you can put stone by One(1) */
                            CustomPair tmp = new CustomPair(i, 100);
                            availableMoves.add(tmp);
                        }
                    }
                }

                if (!availableMoves.isEmpty()) {
                    printAllAvailableMovesPlayerTwo(game, availableMoves);
                    int whichMove = scanner.nextInt();
                    whichMove--;

                    makeMoveForPlayer2(game, availableMoves, whichMove);

                    game.initializeBoard();
                    game.printBoard();
                } else {
                    break;
                }
            }
        }
    }
}
