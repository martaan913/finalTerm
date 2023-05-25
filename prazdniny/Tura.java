package org.example;

import java.util.*;

public class Tura {

    public static double[] dijkstra(double[][] c, int start) {
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
                if (c[v][w] != Double.POSITIVE_INFINITY) {
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

    public static int[] vratCestu(int start, int ciel, double[][] c) {
        double[] vzdialenosti = dijkstra(c, start);
        List<Integer> cesta = new LinkedList<Integer>();
        cesta.add(ciel);
        int aktualny = ciel;
        if (vzdialenosti[ciel] == Double.POSITIVE_INFINITY){
            System.out.println("Neda sa");
            return null;
        }
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
        for (int i = 0; i < c.length; i++) {
            Arrays.fill(c[i], Double.POSITIVE_INFINITY);
        }
        c[0][1] = 7;
        c[0][2] = 6;
        c[0][3] = 15;
        c[1][6] = 20;
        c[2][3] = 3;
        c[2][5] = 18;
        c[3][4] = 10;
        c[4][5] = 12;
        c[6][1] = 20;
        c[1][0] = 7;
        c[2][0] = 6;
        c[3][0] = 15;
        c[3][2] = 3;
        c[5][2] = 18;
        c[4][3] = 10;
        c[5][4] = 12;
        c[4][6] = 5;
        c[6][4] = 5;

        int[] cesta = vratCestu(0,6,c);
        System.out.println(Arrays.toString(cesta));
        for (int i = 1; i < cesta.length-1; i++) {
            for (int j = 0; j < c[i].length; j++) {
                c[cesta[i]][j] = Double.POSITIVE_INFINITY;
           }
        }
        int[] cesta2 = vratCestu(0, 6, c);
        System.out.println(Arrays.toString(cesta2));
    }
}
