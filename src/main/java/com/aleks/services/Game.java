package com.aleks.services;

import com.aleks.model.Player;

import java.util.stream.IntStream;

public class Game {
    public String[] changeField(String step, Player player, char[] field) { // Записываем ходы и выявляем победителя
        String[] res = new String[2];
        int m = 3;
        int n = 3;
        int[][] fieldGameRes = IntStream.range(0, m)
                .mapToObj(i -> IntStream.range(0, n)
                        .map(j -> field[(j + i * n) % field.length])
                        .toArray())
                .toArray(int[][]::new);

        int x = 0;
        int y = 0;
        switch (step) {
            case "2" -> x = 1;
            case "3" -> x = 2;
            case "4" -> y = 1;
            case "5" -> {
                y = 1;
                x = 1;
            }
            case "6" -> {
                y = 1;
                x = 2;
            }
            case "7" -> y = 2;
            case "8" -> {
                y = 2;
                x = 1;
            }
            case "9" -> {
                y = 2;
                x = 2;
            }
        }
        char[][] fieldGame = new char[3][3];
        for (int i = 0; i < fieldGameRes.length; i++) {
            for (int j = 0; j < fieldGameRes.length; j++) {
                fieldGame[i][j] = (char) fieldGameRes[i][j];
            }
        }
        fieldGame[y][x] = player.getSymbol();
        int col = 0;
        int row = 0;
        int diag = 0;
        int rdiag = 0;
        for (int i = 0; i < 3; i++) {
            if (fieldGame[y][i] == player.getSymbol()) col++;
            if (fieldGame[i][x] == player.getSymbol()) row++;
            if (fieldGame[i][i] == player.getSymbol()) diag++;
            if (fieldGame[i][3 - (i + 1)] == player.getSymbol()) rdiag++;
        }
        if (row == 3 || col == 3 || diag == 3 || rdiag == 3) {
            res[1] = player.getName();
        }
        int ind = 0;
        for (char[] chars : fieldGame) {
            for (int j = 0; j < fieldGame.length; j++) {
                field[ind] = chars[j];
                ind++;
            }
        }
        if (!String.valueOf(field).contains("i")) {
            res[1] = "Draw!";
        }
        res[0] = String.valueOf(field);
        return res;
    }
}
