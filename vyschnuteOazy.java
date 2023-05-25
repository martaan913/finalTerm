package org.example.cviko8;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class vyschnuteOazy {

    public static Set<Integer> cesta(double[][] c, int start, int ciel){
        Set<Integer> cesta = new HashSet<>();
        cesta.add(start);
        int zastavka = start;

        while(zastavka != ciel){
            double vzdialenost = Double.MAX_VALUE;
            int moznaZastavka = Integer.MAX_VALUE;
            for (int i = 0; i < c.length; i++) {
                if(vzdialenost > c[zastavka][i] &&! cesta.contains(i)) {
                    vzdialenost = Math.min(vzdialenost, c[zastavka][i]);
                    moznaZastavka = i;
                }
            }
            cesta.add(moznaZastavka);
            zastavka = moznaZastavka;
            System.out.println(zastavka);
        }
        return cesta;
    }

    public static void main(String[] args) {
        double[][] c = new double[7][7];
        for (double[] doubles : c) {
            Arrays.fill(doubles, Double.POSITIVE_INFINITY);
        }
        c[0][1] = 7;
        c[0][3]=5;
        c[1][0]=7;
        c[3][0]=5;
        c[3][1]=9;
        c[1][3]=9;
        c[1][2]=8;
        c[2][1]=8;
        c[1][4]=7;
        c[4][1]=7;
        c[2][4]=5;
        c[4][2]=5;
        c[3][4]=15;
        c[4][3]=15;
        c[3][5]=6;
        c[5][3]=6;
        c[4][5]=8;
        c[5][4]=8;
        c[4][6]=9;
        c[6][4]=9;
        c[5][6]=11;
        c[6][5]=11;
        System.out.println(cesta(c, 0, 6));
    }
}
