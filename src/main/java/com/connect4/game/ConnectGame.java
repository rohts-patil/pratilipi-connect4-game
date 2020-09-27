package com.connect4.game;

import com.connect4.exception.GameException;
import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public @Data class ConnectGame implements Serializable {

  private static final long SerialVersionUID = 1;

  private static final char[] PLAYERS = {'R', 'Y'};

  private int width, height;

  private char[][] grid;

  private int lastCol = -1, lastTop = -1;

  public ConnectGame(int w, int h) {
    width = w;
    height = h;
    grid = new char[h][];

    for (int i = 0; i < h; i++) {
      Arrays.fill(grid[i] = new char[w], '.');
    }
  }

  public String chooseAndDrop(char symbol, int col) {
    for (int h = height - 1; h >= 0; h--) {
      if (grid[h][col] == '.') {
        grid[lastTop = h][lastCol = col] = symbol;
        return "Successfully inserted the coin at column " + col + " for" + symbol;
      }
    }
    throw new GameException("Column " + col + " is full.");
  }

  public String horizontal() {
    return new String(grid[lastTop]);
  }

  public String vertical() {
    StringBuilder sb = new StringBuilder(height);

    for (int h = 0; h < height; h++) {
      sb.append(grid[h][lastCol]);
    }

    return sb.toString();
  }

  public String slashDiagonal() {
    StringBuilder sb = new StringBuilder(height);

    for (int h = 0; h < height; h++) {
      int w = lastCol + lastTop - h;

      if (0 <= w && w < width) {
        sb.append(grid[h][w]);
      }
    }

    return sb.toString();
  }

  public String backslashDiagonal() {
    StringBuilder sb = new StringBuilder(height);

    for (int h = 0; h < height; h++) {
      int w = lastCol - lastTop + h;

      if (0 <= w && w < width) {
        sb.append(grid[h][w]);
      }
    }

    return sb.toString();
  }

  public static boolean contains(String str, String substring) {
    return str.indexOf(substring) >= 0;
  }

  public boolean isWinningPlay() {
    if (lastCol == -1) {
      System.err.println("No move has been made yet");
      return false;
    }

    char sym = grid[lastTop][lastCol];
    // winning streak with the last play symbol
    String streak = String.format("%c%c%c%c", sym, sym, sym, sym);

    // check if streak is in row, col,
    // diagonal or backslash diagonal
    return contains(horizontal(), streak)
        || contains(vertical(), streak)
        || contains(slashDiagonal(), streak)
        || contains(backslashDiagonal(), streak);
  }

  public String toString() {
    return IntStream.range(0, width).mapToObj(Integer::toString).collect(Collectors.joining())
        + "\n"
        + Arrays.stream(grid).map(String::new).collect(Collectors.joining("\n"));
  }
}
