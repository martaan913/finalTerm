package org.finatermKuchyna;

import java.util.*;

public class dojedanie {

    private int[] pole;
    int[] suroviny;
    int[][] recepty;
    public dojedanie(int[] surovinyVstup, int[][] receptyVstup) {
        suroviny = surovinyVstup;
        recepty = receptyVstup;
        pole = new int[recepty[0].length];
    }

    private void analyzuj(){
        int[] sucet = new int[suroviny.length];
        for (int i = 0; i < pole.length; i++) {
            if (pole[i] == 1) {
                for (int j = 0; j < recepty.length; j++) {
                    sucet[j] += recepty[j][i+1];
                }
            }
        }

        boolean mozna = true;

        for (int i = 0; i < sucet.length; i++) {
            if (suroviny[i] != sucet[i]) {
                mozna = false;
                break;
            }
        }

        if (mozna){
            System.out.println("DA SA");
        }
    }

    private void generuj(int odIndexu) {
        if (odIndexu == pole.length) {
            analyzuj();
            return;
        }
        for (int i = 0; i < 2; i++) {
            pole[odIndexu] = i;
            generuj(odIndexu + 1);
            // Eventualne mozeme vratit obsah pola do povodneho stavu - ale je
            // to zbytocne...
            // pole[odIndexu] = 0;
        }
    }


    public void generuj(){
        generuj(0);
    }

    public static void main(String[] args){
        int[] suroviny = {1, 1, 1, 1, 1};
        int[][] recepty = new int [5][4];
        for (int i = 0; i < recepty.length; i++) {
//            for (int j = 0; j < (int) (Math.random()* suroviny.length) + 1; j++) {
//                int surovina =(int) (Math.random()*4);
//                if (!recepty[i].contains(surovina)) {
//                    recepty[i].add(surovina);
//                }else
//                    j--;
//            }
            recepty[i][0] = 3;
            for (int j = 1; j < recepty[j].length; j++) {
                recepty[i][j] = 1;
            }
        }
        recepty[0][1] = 0;
        recepty[0][2] = 0;
        recepty[0][3] = 0;
        dojedanie doj = new dojedanie(suroviny, recepty);
        doj.generuj();
    }
}
