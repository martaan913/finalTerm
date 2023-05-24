package org.example.cviko8;

import java.util.Arrays;

public class zberaxeRosyII {
    public int hladaj(int[][] mapa){
        int max = 0;
        int r = mapa.length;
        int s = mapa[1].length;
        int[][] dp = new int[r][s];
        for(int i = dp.length-1 ; i >= 0; i--){
            for (int j = 0; j < dp[0].length ; j++) {
                if(mapa[i][j] == 1){
                    if(i < r - 1 && j > 0 && j < s - 1){
                        dp[i][j] = Math.min(Math.min(dp[i+1][j+1],dp[i+1][j-1]),dp[i+1][j])+1;
                        if(max < dp[i][j]){
                            max = dp[i][j];
                        }
                    }else{
                        dp[i][j] = 1;
                    }
                }
            }
        }
        for (int i = 0; i < r; i++) {
            System.out.println(Arrays.toString(dp[i]));

        }
        return max;
    }

    public static void main(String[] args) {
        int[][] mapa = {
                {0,0,0,0,1,0,0,0,0},
                {0,0,0,1,1,1,0,0,0},
                {0,0,1,1,1,1,1,0,0},
                {0,1,1,1,1,1,1,1,0},
                {1,1,1,1,1,1,1,1,1}
        };
        zberaxeRosyII zr = new zberaxeRosyII();
        System.out.println(zr.hladaj(mapa));
    }
}
