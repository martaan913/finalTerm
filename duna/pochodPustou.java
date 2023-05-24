package duna;

import java.sql.SQLOutput;
import java.util.*;

public class pochodPustou {

    /** 6 bodov len taky fukot **/
//    public static double[] dijkstra(double[][] c, int start, boolean[] voda) {
//        double ds[] = new double[c.length];
//        Arrays.fill(ds, Double.POSITIVE_INFINITY);
//        ds[start] = 0;
//
//        Set<Integer> Q = new HashSet<>();
//        for (int i = 0; i < c.length; i++) {
//            if (voda[i]) {
//                Q.add(i);
//            }
//        }
//        System.out.println(Q);
//        while (!Q.isEmpty()) {
//            int v = -1;
//            double hodnotaKandidata = Double.MAX_VALUE;
//            for (int i = 0; i < ds.length; i++) {
//                if (Q.contains(i) && ds[i] < hodnotaKandidata) {
//                    v = i;
//                    hodnotaKandidata = ds[i];
//                }
//            }
//            Q.remove(v);
//            if (v == -1)
//                continue;
//
//            for (int w = 0; w < c.length; w++) {
//                if (c[v][w] != Double.POSITIVE_INFINITY && voda[w]) {
//                    relax(v, w, ds, c);
//                }
//            }
//        }
//        return ds;
//    }

    public static double[] dijkstra(double[][] c, int start, boolean[] voda) {
        double ds[] = new double[c.length];
        Arrays.fill(ds, Double.POSITIVE_INFINITY);
        ds[start] = 0;

        Set<Integer> Q = new HashSet<>();
        for (int i = 0; i < c.length; i++) {
            Q.add(i);
        }
        while (!Q.isEmpty()) {
            int kandidat = -1;
            double hodnotaKandidata = Double.MAX_VALUE;
            for (int i = 0; i < ds.length; i++) {
                if (Q.contains(i) && ds[i] < hodnotaKandidata) {
                    kandidat = i;
                    hodnotaKandidata = ds[i];
                }
            }
            Q.remove(kandidat);
            if (kandidat == -1)
                break;

            for (int w = 0; w < c.length; w++) {
                if (c[kandidat][w] != Double.POSITIVE_INFINITY && (voda[kandidat] || voda[w]) ) {
                    relax(kandidat, w, ds, c);
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

    public static int[] vratCestu(int start, int ciel, double[][] c, boolean[] voda) {
        double[] vzdialenosti = dijkstra(c, start, voda);
        List<Integer> cesta = new LinkedList<Integer>();
        cesta.add(ciel);
        int aktualny = ciel;

        if (vzdialenosti[ciel] == Double.POSITIVE_INFINITY){
            System.out.println("Cesta neexistuje.");
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
        c[0][1] = 1;
        c[0][2] = 1;
        c[0][3] = 1;
        c[2][3] = 1;
        c[2][5] = 1;
        c[3][4] = 1;
        c[4][5] = 1;
        c[1][0] = 1;
        c[2][0] = 1;
        c[3][0] = 1;
        c[3][2] = 1;
        c[5][2] = 1;
        c[4][3] = 1;
        c[5][4] = 1;
        c[4][6] = 1;
        c[6][4] = 1;
        boolean[] maVodu = {true,true,true,true,false,false,true};
        double ds[] = dijkstra(c,0,maVodu);
        System.out.println(ds[6]);
        System.out.println(Arrays.toString(vratCestu(0,6,c,maVodu)));
    }
}
