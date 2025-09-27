import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Expectiminimax {
    int[] fact = new int[7];
    private final Movement movement = new Movement();
    private ArrayList<ArrayList<Integer>> permutation = new ArrayList<>();
    private ArrayList<Game> allStates = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> allMoves = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> getPermutation() {
        return permutation;
    }

    public void setPermutation(ArrayList<ArrayList<Integer>> permutation) {
        this.permutation = permutation;
    }

    public ArrayList<Game> getAllStates() {
        return allStates;
    }

    public void setAllStates(ArrayList<Game> allStates) {
        this.allStates = allStates;
    }

    public void calculateFact() {
        fact[0] = 1;
        for(int i = 1; i <= 6; i++) {
            fact[i] = fact[i - 1] * i;
        }
    }

    public Game copyGame(Game game) {
        Game newGame = new Game();
        newGame.setHasCockedForPlayer1(game.getHasCockedForPlayer1());
        newGame.setHasCockedForPlayer2(game.getHasCockedForPlayer2());
        newGame.setDepth(game.getDepth());
        for (int i = 0; i < game.length; i++) {
            for (int j = 0; j < game.length; j++) {
                newGame.getBoard()[i][j] = game.getBoard()[i][j];
            }
        }
        for (int i = 0; i < game.getTotalPathSize(); i++) {
            newGame.getPlayer1Path().set(i, game.getPlayer1Path().get(i));
        }
        for (int i = 0; i < game.getTotalPathSize(); i++) {
            newGame.getPlayer2Path().set(i, game.getPlayer2Path().get(i));
        }
        for (Cell stone : game.getPlayer1Stones()) {
            newGame.getPlayer1Stones().add(new Cell(stone.getI(), stone.getJ(), stone.getTmp()));
        }
        for (Cell stone : game.getPlayer2Stones()) {
            newGame.getPlayer2Stones().add(new Cell(stone.getI(), stone.getJ(), stone.getTmp()));
        }

        return newGame;
    }

    public int reversePlayer(int player) {
        if (player == 1) {
            return 2;
        }

        return 1;
    }

    public boolean isWin(Game game, int player) {
        if (player == 1) {
            return game.getHasCockedForPlayer1() == 4;
        } else {
            return game.getHasCockedForPlayer2() == 4;
        }
    }

    public int evaluate(Game game, int player) {
        if (isWin(game, player)) {
            return 10000000;
        } else if (isWin(game, reversePlayer(player))) {
            return -10000000;
        } else {
            int playerOne = 0, playerTwo = 0;
            int stoneValue = 1000, cookedStone = 100000, OnX = 500;

            playerOne = playerOne + (stoneValue * game.getPlayer1Stones().size());
            playerOne = playerOne + (cookedStone * game.getHasCockedForPlayer1());
            /* Add how far stone from the beginning */
            for (Cell currentStone : game.getPlayer1Stones()) {
                playerOne += currentStone.getTmp();
            }
            /* Add value for every stone on X */
            for (Cell xPosition : game.getXPositions()) {
                for (Cell playerOneStone : game.getPlayer1Stones()) {
                    if (xPosition.getI() == playerOneStone.getI() && xPosition.getJ() == playerOneStone.getJ()) {
                        playerOne = playerOne + OnX;
                    }
                }
            }

            playerTwo = playerTwo + (stoneValue * game.getPlayer2Stones().size());
            playerTwo = playerTwo + (cookedStone * game.getHasCockedForPlayer2());
            /* Add how far stone from the beginning */
            for (Cell currentStone : game.getPlayer2Stones()) {
                playerTwo += currentStone.getTmp();
            }
            /* Add value for every stone on X */
            for (Cell xPosition : game.getXPositions()) {
                for (Cell playerTwoStone : game.getPlayer2Stones()) {
                    if (xPosition.getI() == playerTwoStone.getI() && xPosition.getJ() == playerTwoStone.getJ()) {
                        playerOne = playerOne + OnX;
                    }
                }
            }

            return playerOne - playerTwo;
        }
    }

    public void getPermutation(ArrayList<Integer> moves, int startIndex) {
        if (startIndex == moves.size()) {
            if (!permutation.contains(moves)) {
                permutation.add(moves);
            }
            return;
        }

        for (int i = startIndex; i < moves.size(); i++) {
            ArrayList<Integer> tmp = new ArrayList<>(moves);

            int iValue = tmp.get(i);
            int startValue = tmp.get(startIndex);
            tmp.set(startIndex, iValue);
            tmp.set(i, startValue);

            getPermutation(tmp, startIndex + 1);
        }
    }

    public void generateAllMoves(Game game, ArrayList<Integer> moves, int numberOfThrows, int i) {
        if(numberOfThrows == game.getMaxThrows()) {
            if(!allMoves.contains(moves)) {
                allMoves.add(moves);
            }
            return;
        }

        for (; i <= 6; i++) {
            if (i == 0) {
                if (numberOfThrows < game.getMaxThrows()) {
                    ArrayList<Integer> newMoves = new ArrayList<>(moves);
                    newMoves.add(0);
                    generateAllMoves(game, newMoves, numberOfThrows + 1);
                } else {
                    moves.add(0);
                    break;
                }
            } else if (i == 1) {
                if (numberOfThrows < game.getMaxThrows()) {
                    ArrayList<Integer> newMoves = new ArrayList<>(moves);
                    newMoves.add(1);
                    generateAllMoves(game, newMoves, numberOfThrows + 1);
                } else {
                    moves.add(1);
                    break;
                }
            } else if (i == 2) {
                moves.add(2);
                break;
            } else if (i == 3) {
                moves.add(3);
                break;
            } else if (i == 4) {
                moves.add(4);
                break;
            } else if (i == 5) {
                if (numberOfThrows < game.getMaxThrows()) {
                    ArrayList<Integer> newMoves = new ArrayList<>(moves);
                    newMoves.add(5);
                    generateAllMoves(game, newMoves, numberOfThrows + 1);
                } else {
                    moves.add(5);
                    break;
                }
            } else {
                if (numberOfThrows < game.getMaxThrows()) {
                    ArrayList<Integer> newMoves = new ArrayList<>(moves);
                    newMoves.add(6);
                    generateAllMoves(game, newMoves, numberOfThrows + 1);
                } else {
                    moves.add(6);
                    break;
                }
            }
        }

        if(!allMoves.contains(moves)) {
            allMoves.add(moves);
        }
    }

    public void generateAllMoves(Game game, ArrayList<Integer> moves, int numberOfThrows) {
        for(int i = 0; i <= 6; i++) {
            ArrayList<Integer> newMoves = new ArrayList<>(moves);
            generateAllMoves(game, newMoves, numberOfThrows, i);
        }
    }

    public int getProbability(ArrayList<Integer> moves) {
        /* Calculate Combinatorics for every move and multiply them all */
        int value = 1;
        for (int move : moves) {
            value = value * (fact[6] / (fact[move] * fact[6 - move]));
        }

        return value;
    }

    public void generateAllStates2(Game game, ArrayList<Integer> moves, int player, int j) {
        if (j == moves.size()) {
            allStates.add(game);
            return;
        }

        for (; j < moves.size(); j++) {
            int move = moves.get(j);

            if (player == 1 && move != -1) {
                boolean hasMove = false;

                for (Cell currentStone : game.getPlayer1Stones()) {
                    int newPosition = currentStone.getTmp() + move;

                    if (movement.checkMoveForFirstPlayer(game, newPosition)) {
                        hasMove = true;
                        Game newGame = copyGame(game);
                        Cell newStone = new Cell(currentStone.getI(), currentStone.getJ(), currentStone.getTmp());
                        ArrayList<Integer> newMoves = new ArrayList<>(moves);

                        movement.makeMoveForFirstPlayer(newGame, newStone, move);

                        newMoves.set(j, -1);

                        generateAllStates2(newGame, newMoves, player, j + 1);
                    }
                }

                if (move == 1 && game.getPlayer1Stones().size() < 4) {
                    if (!game.getPlayer1Stones().isEmpty()) {
                        hasMove = true;
                        Game newGame = copyGame(game);
                        ArrayList<Integer> newMoves = new ArrayList<>(moves);
                        movement.putStoneByOne(newGame, player);
                        newMoves.set(j, -1);
                        generateAllStates2(newGame, newMoves, player, j + 1);
                    }

                    for (int k = 0; k < moves.size(); k++) {
                        if (moves.get(k) == 24) {
                            hasMove = true;
                            Game newGame2 = copyGame(game);
                            ArrayList<Integer> newMoves2 = new ArrayList<>(moves);
                            movement.putStoneByBng(newGame2, player);
                            newMoves2.set(j, -1);
                            newMoves2.set(k, -1);
                            generateAllStates2(newGame2, newMoves2, player, j + 1);
                        } else if (moves.get(k) == 10 && !Objects.equals(game.getBoard()[16][10], game.getPlayer2Symbol())) {
                            hasMove = true;
                            Game newGame2 = copyGame(game);
                            ArrayList<Integer> newMoves2 = new ArrayList<>(moves);
                            movement.putStoneByDst(newGame2, player);
                            newMoves2.set(j, -1);
                            newMoves2.set(k, -1);
                            generateAllStates2(newGame2, newMoves2, player, j + 1);
                        }
                    }
                }

                if (!hasMove) {
                    Game newGame = copyGame(game);
                    ArrayList<Integer> newMoves = new ArrayList<>(moves);
                    generateAllStates2(newGame, newMoves, player, j + 1);
                }
            } else if (move != -1) {
                boolean hasMove = false;

                for (Cell currentStone : game.getPlayer2Stones()) {
                    int newPosition = currentStone.getTmp() + move;

                    if (movement.checkMoveForSecondPlayer(game, newPosition)) {
                        hasMove = true;
                        Game newGame = copyGame(game);
                        Cell newStone = new Cell(currentStone.getI(), currentStone.getJ(), currentStone.getTmp());
                        ArrayList<Integer> newMoves = new ArrayList<>(moves);

                        movement.makeMoveForSecondPlayer(newGame, newStone, move);

                        newMoves.set(j, -1);

                        generateAllStates2(newGame, newMoves, player, j + 1);
                    }
                }

                if (move == 1 && game.getPlayer2Stones().size() < 4) {
                    if (!game.getPlayer2Stones().isEmpty()) {
                        hasMove = true;
                        Game newGame = copyGame(game);
                        ArrayList<Integer> newMoves = new ArrayList<>(moves);
                        movement.putStoneByOne(newGame, player);
                        newMoves.set(j, -1);
                        generateAllStates2(newGame, newMoves, player, j + 1);
                    }

                    for (int k = 0; k < moves.size(); k++) {
                        if (moves.get(k) == 24) {
                            hasMove = true;
                            Game newGame2 = copyGame(game);
                            ArrayList<Integer> newMoves2 = new ArrayList<>(moves);
                            movement.putStoneByBng(newGame2, player);
                            newMoves2.set(j, -1);
                            newMoves2.set(k, -1);
                            generateAllStates2(newGame2, newMoves2, player, j + 1);
                        } else if (moves.get(k) == 10 && !Objects.equals(game.getBoard()[2][8], game.getPlayer1Symbol())) {
                            hasMove = true;
                            Game newGame2 = copyGame(game);
                            ArrayList<Integer> newMoves2 = new ArrayList<>(moves);
                            movement.putStoneByDst(newGame2, player);
                            newMoves2.set(j, -1);
                            newMoves2.set(k, -1);
                            generateAllStates2(newGame2, newMoves2, player, j + 1);
                        }
                    }
                }

                if (!hasMove) {
                    Game newGame = copyGame(game);
                    ArrayList<Integer> newMoves = new ArrayList<>(moves);
                    generateAllStates2(newGame, newMoves, player, j + 1);
                }
            }
        }
    }

    public void generateAllStates(Game game, int player) {
        for (ArrayList<Integer> integers : permutation) {
            generateAllStates2(game, integers, player, 0);
        }
    }

    public List<Object> EXPECTIMAX(Game game, int player) {
        if (player == 3 || player == 4) { /* Node is Chance node */
            if (player == 3) {
                player = 2;
            } else { // player = 4
                player = 1;
            }

            int expected = 0;
            for (ArrayList<Integer> allMove : allMoves) {
                int probability = getProbability(allMove);
                Game newGame = copyGame(game);
                newGame.getMoves().clear();
                newGame.getMoves().addAll(allMove);

                allStates.clear();
                generateAllStates2(newGame, newGame.getMoves(), player, 0);
                ArrayList<Game> allCurrentSates = new ArrayList<>(allStates);

                for (Game current : allCurrentSates) {
                    Game currentGame = copyGame(current);
                    currentGame.setDepth(currentGame.getDepth() - 1);
                    List<Object> list = EXPECTIMAX(currentGame, player);
                    int value = (Integer) list.get(1);

                    expected += value * probability;
                }
            }

            List<Object> list = new ArrayList<>();
            list.add(null);
            list.add(expected);
            return list;
        } else if (player == 1) {
            Game currentGame = copyGame(game);
            return MAX(currentGame, player);
        } else {
            Game currentGame = copyGame(game);
            return MIN(currentGame, player);
        }
    }

    public List<Object> MAX(Game game, int player) {
        if (game.isFinal() || game.getDepth() == 0) {
            int evaluate = evaluate(game, 1);
            List<Object> list = new ArrayList<>();
            list.add(null);
            list.add(evaluate);

            return list;
        }

        Game bestGame = null;
        int maxValue = Integer.MIN_VALUE;
        allStates.clear();
        generateAllStates(game, player);
        ArrayList<Game> allCurrentSates = new ArrayList<>(allStates);

        for (Game current : allCurrentSates) {
            Game currentGame = copyGame(current);
            currentGame.setDepth(currentGame.getDepth() - 1);
            List<Object> list = EXPECTIMAX(currentGame, player + 2);
            int evaluate = (Integer) list.get(1);

            if (evaluate >= maxValue) {
                bestGame = current;
                maxValue = evaluate;
            }
        }

        List<Object> list = new ArrayList<>();
        list.add(bestGame);
        list.add(maxValue);

        return list;
    }

    public List<Object> MIN(Game game, int player) {
        if (game.isFinal() || game.getDepth() == 0) {
            int evaluate = evaluate(game, 1);
            List<Object> list = new ArrayList<>();
            list.add(null);
            list.add(evaluate);

            return list;
        }

        Game bestGame = null;
        int minValue = Integer.MAX_VALUE;
        allStates.clear();
        generateAllStates(game, player);
        ArrayList<Game> allCurrentSates = new ArrayList<>(allStates);

        for (Game current : allCurrentSates) {
            Game currentGame = copyGame(current);
            currentGame.setDepth(currentGame.getDepth() - 1);
            List<Object> list = EXPECTIMAX(currentGame, player + 2);
            int evaluate = (Integer) list.get(1);

            if (evaluate <= minValue) {
                bestGame = current;
                minValue = evaluate;
            }
        }

        List<Object> list = new ArrayList<>();
        list.add(bestGame);
        list.add(minValue);

        return list;
    }
}
