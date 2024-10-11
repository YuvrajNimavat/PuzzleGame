package com.example.puzzleapp;

import java.util.ArrayList;
import java.util.Collections;

public class PuzzleGame {
    private ArrayList<Integer> numbers;

    public PuzzleGame() {

        numbers = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            numbers.add(i);
        }
        numbers.add(0); // Add the empty space
        shuffle();
    }

    public ArrayList<Integer> getNumbers() {
        return numbers;
    }

    public void shuffle() {
        Collections.shuffle(numbers);
    }

    public void move(int position) {
        int emptyPosition = numbers.indexOf(0);
        if (isValidMove(position, emptyPosition)) {
            Collections.swap(numbers, position, emptyPosition);
        }
    }

    private boolean isValidMove(int position, int emptyPosition) {
        int row = position / 3;
        int col = position % 3;
        int emptyRow = emptyPosition / 3;
        int emptyCol = emptyPosition % 3;

        return (row == emptyRow && Math.abs(col - emptyCol) == 1) || (col == emptyCol && Math.abs(row - emptyRow) == 1);
    }

    public boolean isSolved() {
        for (int i = 0; i < 8; i++) {
            if (numbers.get(i) != i + 1) {
                return false;
            }
        }
        return true;
    }
}
