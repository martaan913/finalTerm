package org.example;

import sk.upjs.paz.graph.*;

import java.util.*;

public class mario {

    private static final int NEKONECNO = 20000;

    public static Map<Vertex, Double> dijkstra(Vertex start, Graph g) {
        Map<Vertex, Double> ds = new HashMap<>();
        for (Vertex v : g.getVertices()) {
            ds.put(v, Double.POSITIVE_INFINITY);
        }
        ds.put(start, 0.0);
        Set<Vertex> q = new HashSet<>(g.getVertices());
        while (!q.isEmpty()) {
            Vertex v = null;
            double minimum = Double.POSITIVE_INFINITY;
            for (Vertex u : q) {
                if (ds.get(u) <= minimum) {
                    minimum = ds.get(u);
                    v = u;
                }
            }
            q.remove(v);
            for (Edge e : v.getOutEdges()) {
                //relaxacia
                Vertex uu = e.getSource();
                Vertex vv = e.getTarget();
                if (uu == v) {
                    ds.put(vv, Math.min(ds.get(vv), ds.get(uu) + (int) (e.getWeight())));
                }else{
                    ds.put(uu, Math.min(ds.get(uu), ds.get(vv) + (int) (e.getWeight())));
                }
            }
        }
        return ds;
    }



    private static List<Edge> kruskal(Graph g) {
        List<Edge> kostra = new ArrayList<>();
        List<Edge> hrany = new ArrayList<>(g.getEdges());
        Collections.sort(hrany, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Double.compare(o1.getWeight(), o2.getWeight());
            }
        });
        Map<Vertex, Integer> komp = new HashMap<>();
        int i = 1;
        for (Vertex vertex : g.getVertices()) {
            komp.put(vertex, i);
            i++;
        }

        for (Edge edge : hrany) {
            int k1 = komp.get(edge.getSource());
            int k2 = komp.get(edge.getTarget());
            if (k1 != k2) {
                kostra.add(edge);
                for (Vertex vertex : komp.keySet()) {
                    if (komp.get(vertex) == k1) {
                        komp.put(vertex, k2);
                    }
                }
            }
        }
        int result = 0;
        for (Edge edge : kostra) {
            result += edge.getWeight();
        }
        //System.out.println(result);
        return kostra;
    }

    private static List<Edge> kruskalSKanalmi(Graph g, List<Integer> kanal) {

        for (Edge hrana :
                g.getEdges()) {
            if (kanal.contains(Integer.valueOf(hrana.getSource().getLabel())) && kanal.contains(Integer.valueOf(hrana.getTarget().getLabel()))) {
                hrana.setWeight(0);
            }
        }

        List<Edge> kostra = new ArrayList<>();
        List<Edge> hrany = new ArrayList<>(g.getEdges());
        Collections.sort(hrany, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Double.compare(o1.getWeight(), o2.getWeight());
            }
        });
        Map<Vertex, Integer> komp = new HashMap<>();
        int i = 1;
        for (Vertex vertex : g.getVertices()) {
            komp.put(vertex, i);
            i++;
        }

        for (Edge edge : hrany) {
            int k1 = komp.get(edge.getSource());
            int k2 = komp.get(edge.getTarget());
            if (k1 != k2) {
                kostra.add(edge);
                for (Vertex vertex : komp.keySet()) {
                    if (komp.get(vertex) == k1) {
                        komp.put(vertex, k2);
                    }
                }
            }
        }
        int result = 0;
        for (Edge edge : kostra) {
            result += edge.getWeight();
        }
        System.out.println(result);
        return kostra;
    }

    public static List<Vertex> vratCestu(Vertex start, Vertex ciel, Graph g) {

        Map<Vertex, Double> vzdialenosti = dijkstra(start, g);
        List<Vertex> cesta = new LinkedList<Vertex>();
        cesta.add(ciel);
        Vertex aktualny = ciel;
        while (start != aktualny) {
            for (Vertex vrchol : aktualny.getInNeighbours()) {
                if (g.getEdge(aktualny, vrchol).getWeight() != Double.POSITIVE_INFINITY) {
                    if (vzdialenosti.get(aktualny) - g.getEdge(aktualny, vrchol).getWeight() == vzdialenosti.get(vrchol)) {
                        aktualny = vrchol;
                        cesta.add(vrchol);
                    }
                }
            }
        }
        Collections.reverse(cesta);
        int[] cestaPole = new int[cesta.size()];
        for (int i = 0; i < cestaPole.length; i++) {
            cestaPole[i] = 0;
        }
        return cesta;
    }

    public static double cenaCesty(Graph g, double DN, int VIP1, int VIP2) {
        List<Edge> kostra = kruskal(g);
        for (Edge hrana :
                g.getEdges()) {
            if (!kostra.contains(hrana)) {
                hrana.setWeight(Double.POSITIVE_INFINITY);
            }
        }
        double result = 0;
        List<Vertex> cesta = vratCestu(g.getVertex(String.valueOf(VIP1)), g.getVertex(String.valueOf(VIP2)), g);
        for (Edge hrana :
                kostra) {
            if (cesta.contains(hrana.getSource()) && cesta.contains(hrana.getTarget())) {
                result += hrana.getWeight() * (DN / 100);
            }else {
                result+= hrana.getWeight();
            }
        }

        return result;
    }

        public static void main (String[]args){

//        double[][] c = new double[7][7];
//        for (int i = 0; i < c.length; i++) {
//            Arrays.fill(c[i], Double.POSITIVE_INFINITY);
//        }
//
//        c[0][1] = 5;
//        c[1][0] = 5;
//        c[0][2] = 6;
//        c[2][0] = 6;
//        c[0][3] = 10;
//        c[3][0] = 10;
//        c[2][3] = 0;
//        c[3][2] = 0;
//        c[2][4] = 12;
//        c[4][2] = 12;
//        c[3][5] = 8;
//        c[5][3] = 8;
//        c[4][5] = 3;
//        c[5][4] = 3;
//        c[4][6] = 6;
//        c[6][4] = 6;

            Graph g = Graph.createFromFile("C:\\Users\\pc12\\paz1b LM\\finalTerm\\src\\main\\java\\org\\example\\graf", false);
            List<Integer> kanal = new ArrayList<>();
            kanal.add(0);
            kanal.add(1);
//        System.out.println(cenaCesty(g,300,vip));
//        System.out.println(kruskalSKanalmi(g, kanal));
            System.out.println(cenaCesty(g, 300, 2, 0));

        }

    }
