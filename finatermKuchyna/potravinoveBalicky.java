package org.finatermKuchyna;

import sk.upjs.paz.graph.Graph;
import sk.upjs.paz.graph.Vertex;

import java.util.*;

public class potravinoveBalicky {

    Graph graf;

    public potravinoveBalicky(String[] recepty) {
        this.graf = new Graph();
        for (String recept :
                recepty) {
            for (int i = 0; i < recept.length(); i++) {
                String pismeno1 = String.valueOf(recept.charAt(i));
                if (graf.getVertex(pismeno1) == null){
                    graf.addVertex(pismeno1);
                }
                for (int j = i + 1; j < recept.length(); j++) {
                    String pismeno2 = String.valueOf(recept.charAt(j));
                    if (graf.getVertex(pismeno2) == null){
                        graf.addVertex(pismeno2);
                        graf.addEdge(graf.getVertex(pismeno1), graf.getVertex(pismeno2));
                    }else {
                        if (graf.hasEdge(graf.getVertex(pismeno1), graf.getVertex(pismeno2))){
                            graf.getEdge(graf.getVertex(pismeno1), graf.getVertex(pismeno2)).setWeight(
                                    graf.getEdge(graf.getVertex(pismeno1), graf.getVertex(pismeno2)).getWeight()+1);
                        }else {
                            graf.addEdge(graf.getVertex(pismeno1), graf.getVertex(pismeno2));
                        }
                        System.out.println(graf.getEdge(graf.getVertex(pismeno1), graf.getVertex(pismeno2)) +" "+
                                graf.getEdge(graf.getVertex(pismeno1), graf.getVertex(pismeno2)).getWeight());
                    }
                }
            }
        }
    }

    public Map<Vertex, Boolean> bfs(Vertex start) {
        // Vytvorime mapu vrcholov a vsetky vrcholy nastavime ako nenavstivene
        Map<Vertex, Boolean> navstiveny = graf.createVertexMap(false);
        // Vytvorime rad pre navstivene vrcholy, ktorych susedov sme zatial
        // neskusali navstivit
        Queue<Vertex> rad = new LinkedList<Vertex>();
        // Na zaciatku je navstiveny iba startovaci vrchol
        navstiveny.put(start, true);
        rad.offer(start);
        // Kym rad nie je prazdny
        while (!rad.isEmpty()) {
            // Vyberieme prvy vrchol v rade
            Vertex v = rad.poll();
            // Postupne vsetkych nenavstivenych susedov vrcholu v oznacime ako
            // navstivenych a pridame ich do radu
            for (Vertex sused : v.getOutNeighbours())
                if (!navstiveny.get(sused)) {
                    navstiveny.put(sused, true);
                    rad.offer(sused);
                }
        }
        return navstiveny;
    }

    public int analyzuj(int pocetReceptov, int pocetSurovin){
        for (Vertex vrchol :
                graf.getVertices()) {
            for (Vertex sused :
                    vrchol.getOutNeighbours()) {
                if (graf.getEdge(vrchol,sused).getWeight() < pocetReceptov){
                    graf.removeEdge(graf.getEdge(vrchol,sused));
                }
            }
        }

        int pocetBalickov = 0;
        List<Vertex> navstiveny = new ArrayList<>();
        int velkostBalicka = 0;

        for (Vertex vrchol :
                graf.getVertices()) {
            boolean balik = false;
            Map<Vertex,Boolean> mapa = bfs(vrchol);
            navstiveny.add(vrchol);
            for (Vertex sused :
                    mapa.keySet()) {
                if(sused != vrchol && mapa.get(sused)) {
                    if (!navstiveny.contains(sused)){
                        navstiveny.add(sused);
                        balik = true;
                    }
                    velkostBalicka++;
                }
            }

            if (balik && velkostBalicka >= pocetSurovin){
                pocetBalickov++;
            }
        }
        return pocetBalickov;
    }

    public static void main(String[] args){

        String[] recepty = {"ABC","BCD","AB","EF","EFG","ABG"};
        potravinoveBalicky pb = new potravinoveBalicky(recepty);
        System.out.println( pb.analyzuj(2,2));
    }
}
