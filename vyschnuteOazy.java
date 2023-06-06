package org.example;

import java.util.*;

public class vyschnuteOazy {
    public static double[] dijkstra(double[][] c, int start, int maximum) {
        double ds[] = new double[c.length];
        Arrays.fill(ds, Double.POSITIVE_INFINITY);
        ds[start] = 0;

        Set<Integer> Q = new HashSet<>();
        for (int i = 0; i < c.length; i++) {
            Q.add(i);
        }

        while (!Q.isEmpty()) {
            int v = -1;
            double hodnotaKandidata = Double.MAX_VALUE;
            for (int i = 0; i < ds.length; i++) {
                if (Q.contains(i) && ds[i] < hodnotaKandidata) {
                    v = i;
                    hodnotaKandidata = ds[i];
                }
            }
            Q.remove(v);
            if (v == -1)
                break;

            for (int w = 0; w < c.length; w++) {

                if (c[v][w] != Double.POSITIVE_INFINITY && c[v][w] < maximum) {
                    relax(v, w, ds, c);
                }
            }
        }
        return ds;
    }

    private static void relax(int v, int w, double[] ds, double[][] c) {
        if (ds[w] > ds[v] + c[v][w]) {
            ds[w] = ds[v] + c[v][w];
        }
    }

    public static int[] vratCestu(int start, int ciel, double[][] c, int maximum) {
        double[] vzdialenosti = dijkstra(c, start, maximum);
        List<Integer> cesta = new LinkedList<Integer>();
        cesta.add(ciel);
        int aktualny = ciel;

        while (start != aktualny) {
            for (int i = 0; i < c.length; i++) {
                if (c[aktualny][i] != Double.POSITIVE_INFINITY) {
                    if (vzdialenosti[aktualny] - c[aktualny][i] == vzdialenosti[i]) {
                        aktualny = i;
                        cesta.add(i);
                        break;
                    }
                }
            }
        }
        Collections.reverse(cesta);
        int[] cestaPole = new int[cesta.size()];
        for (int i = 0; i < cestaPole.length; i++) {
            cestaPole[i] = cesta.get(i);
        }
        return cestaPole;

    }


    public static void main(String[] args) {
        double[][] c = new double[7][7];
        int maximum = 13;
        for (double[] doubles : c) {
            Arrays.fill(doubles, Double.POSITIVE_INFINITY);
        }
        c[0][1] = 7;
        c[0][2] = 6;
        c[0][3] = 15;
        c[1][6] = 20;
        c[2][3] = 3;
        c[2][5] = 18;
        c[3][4] = 50;
        c[4][5] = 21;
        c[6][1] = 20;
        c[1][0] = 7;
        c[2][0] = 6;
        c[3][0] = 15;
        c[3][2] = 3;
        c[5][2] = 18;
        c[4][3] = 50;
        c[5][4] = 21;
        c[4][6] = 5;
        c[6][4] = 5;
        System.out.println(Arrays.toString(vratCestu(0,6,c,25)));
    }

}
