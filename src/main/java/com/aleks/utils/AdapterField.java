package com.aleks.utils;

import java.util.stream.IntStream;

public class AdapterField {
    public static char[][] adapterField(char[] field) {
        int m = 3;
        int n = 3;
        int[][] fieldGameRes = IntStream.range(0, m)
                .mapToObj(i -> IntStream.range(0, n)
                        .map(j -> field[(j + i * n) % field.length])
                        .toArray())
                .toArray(int[][]::new);
        char[][] fieldGame = new char[3][3];
        for (int i = 0; i < fieldGameRes.length; i++) {
            for (int j = 0; j < fieldGameRes.length; j++) {
                fieldGame[i][j] = (char) fieldGameRes[i][j];
            }
        }
        return fieldGame;
    }
}
