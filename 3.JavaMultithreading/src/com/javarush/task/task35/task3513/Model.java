package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    int score;
    int maxTile;
    private Stack<Tile[][]> previousStates = new Stack<>();
    private Stack<Integer> previousScores = new Stack<>();
    private boolean isSaveNeeded = true;

    public Model() {
        resetGameTiles();
    }

    public void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();

        gameTiles = new Tile[][]{{new Tile(2), new Tile(4), new Tile(8), new Tile(0)},
                {new Tile(0), new Tile(8), new Tile(0), new Tile(4)},
                {new Tile(4), new Tile(8), new Tile(8), new Tile(0)},
                {new Tile(2), new Tile(4), new Tile(0), new Tile(2)}};
        score = 0;
        maxTile = 0;
    }

    private void saveState(Tile[][] tiles) {
        Tile[][] a = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                a[j][i] = new Tile(tiles[j][i].value);
            }
        }
        Integer sc = score;
        previousStates.push(a);
        previousScores.push(sc);
        isSaveNeeded = false;
    }

    public void rollback() {
        if (!previousStates.empty() && !previousScores.empty()) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }

    private void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();
        if (emptyTiles.size() > 0) {
            Tile tile = emptyTiles.get((int) (emptyTiles.size() * Math.random()));
            tile.value = Math.random() < 0.9 ? 2 : 4;
        }

    }

    private List<Tile> getEmptyTiles() {
        List<Tile> emptyTiles = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].isEmpty()) {
                    emptyTiles.add(gameTiles[i][j]);
                }
            }
        }
        return emptyTiles;
    }

    private boolean compressTiles(Tile[] tiles) {
        Comparator<Tile> comparator = new Comparator<Tile>() {
            @Override
            public int compare(Tile o1, Tile o2) {
                return !o1.isEmpty() && o2.isEmpty() ? -1 : (o1.isEmpty() && !o2.isEmpty() ? 1 : 0);
            }
        };
        List<Tile> list = Arrays.asList(tiles);
        List<Tile> unsortedList = new ArrayList<>(Arrays.asList(tiles));
        Collections.sort(list, comparator);
        list.toArray(tiles);
        return !unsortedList.equals(list);
    }

    private void copy(Tile[] tiles, Tile[] copy) {
        for (int i = 0; i < FIELD_WIDTH; i++) {
            copy[i] = new Tile(tiles[i].value);
        }
    }
    private boolean arraysAreEqual(Tile[] tile, Tile[] copy) {
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (tile[i].value != copy[i].value) {
                return false;
            }
        }
        return true;
    }
    private boolean mergeTiles(Tile[] tiles) {
        Tile[] a = new Tile[FIELD_WIDTH];
        copy(tiles, a);
        for (int j = 0; j < FIELD_WIDTH - 1; j++) {
            if (tiles[j].value == tiles[j + 1].value) {
                tiles[j].value *= 2;
                tiles[j + 1].value = 0;
                score += tiles[j].value;
                if (tiles[j].value > maxTile) {
                    maxTile = tiles[j].value;
                }
            }
        }


        compressTiles(tiles);
        return !arraysAreEqual(tiles, a);
    }
    public void left() {
        boolean res1 = false;
        boolean res2 = false;
        if (isSaveNeeded) {
            saveState(gameTiles);
        }
        for (int i = 0; i < FIELD_WIDTH; i++) {
             res1 = compressTiles(gameTiles[i]);
             res2 = mergeTiles(gameTiles[i]);
        }
        isSaveNeeded = true;
        if (res1 || res2) {
            addTile();
        }
    }

    public void turn(Tile[][] tiles) {
        Tile[][] a = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
               a[j][i] = tiles[FIELD_WIDTH - 1 - i][j];
            }
        }
        gameTiles = a;
    }

    public void down() {
        saveState(gameTiles);
        turn(gameTiles);
        left();
        turn(gameTiles);
        turn(gameTiles);
        turn(gameTiles);
    }

    public void right() {
        saveState(gameTiles);
        turn(gameTiles);
        turn(gameTiles);
        left();
        turn(gameTiles);
        turn(gameTiles);
    }

    public void up() {
        saveState(gameTiles);
        turn(gameTiles);
        turn(gameTiles);
        turn(gameTiles);
        left();
        turn(gameTiles);
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public boolean canMove() {
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[j][i].value == 0) {
                    return true;
                }
                if (j < FIELD_WIDTH - 1) {
                    if (gameTiles[j][i].value == gameTiles[j + 1][i].value) {
                        return true;
                    }
                }
                if (i < FIELD_WIDTH - 1) {
                    if (gameTiles[j][i].value == gameTiles[j][i + 1].value) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void randomMove() {
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n) {
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
        }
    }

    private int getTilesWeight(Tile[][] tiles) {
        int res = 0;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                res = res + tiles[i][j].value;
            }
        }
        return res;
    }

    boolean hasBoardChanged () {
        if (getTilesWeight(gameTiles) != getTilesWeight(previousStates.peek()))
            return true;
        else return false;
    }

    public MoveEfficiency getMoveEfficiency(Move move) {
        move.move();
        MoveEfficiency moveEfficiency = null;
        if (!hasBoardChanged()) {
            moveEfficiency = new MoveEfficiency(-1, score, move);
        } else {
            moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
        }
        rollback();
        return moveEfficiency;
    }

    public void autoMove() {
        PriorityQueue<MoveEfficiency> queue = new PriorityQueue<>(4, Collections.reverseOrder());
        queue.add(getMoveEfficiency(this::left));
        queue.add(getMoveEfficiency(this::right));
        queue.add(getMoveEfficiency(this::up));
        queue.add(getMoveEfficiency(this::down));
        queue.poll().getMove().move();
    }
}
