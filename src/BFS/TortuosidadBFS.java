/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BFS;

import Estructuras.Cavidad;
import Estructuras.Ducto;
import Estructuras.GNode;
import Estructuras.Grafo;
import Estructuras.Matriz;
import Estructuras.MatrizPrueba;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author Neythan Leon
 */
public class TortuosidadBFS {
    private int[][][] matriz;
    private Grafo grafo;
    private ArrayList<Estado> ruta;
    private boolean[][] matrizEstado;
    private int[] movx={0,1,-1};
    private int[] movy={1,0,0};
    private Estado inicial;
    private Estado actual;
    private Queue<Estado> colaEstados;
    private GNode nactual;
    private Queue<GNode> colaVertices;
    private int espacioLibre = 0;
    public TortuosidadBFS(){
        this.colaEstados = new LinkedList<>();
        this.ruta = new ArrayList();
        
    }
    public int rutaBFSMatriz(int[][] matriz,int filas, int columnas, int x, int y,int destino){
        matrizEstado=new boolean[filas][columnas];
        inicial = new Estado(x, y, 0);
        colaEstados.offer(inicial);
        while(!colaEstados.isEmpty()){
            actual = colaEstados.poll();
            //colaEstados.poll();
           
            if((matriz[actual.getX()][actual.getY()])==espacioLibre&&(actual.getY()==(columnas-1)&&(actual.getX()==destino))){
                return actual.getDistancia();
            }
            matrizEstado[actual.getX()][actual.getY()]=true;
            
            for(int i=0;i<3;i++){
                int nuevoX = actual.getX() + movx[i];
                int nuevoY = actual.getY() + movy[i];
                if(nuevoX >=0 && nuevoX <filas && nuevoY >=0 && nuevoY < columnas){
                    if(!matrizEstado[nuevoX][nuevoY] && matriz[nuevoX][nuevoY]!=1){
                        System.out.println(nuevoX +", "+nuevoY);
                        Estado nuevoEstado = new Estado(nuevoX, nuevoY, actual.getDistancia()+1);
                        ruta.add(nuevoEstado);
                        colaEstados.offer(nuevoEstado);
                    }
                }
            }
            
        }
        System.out.println("Distancia de interrupción: "+actual.getDistancia());
        //System.out.println("Tortuosidad: "+(actual.getDistancia()/columnas));
        return -1;
    }
    public int rutaBFSGrafo(Grafo g,int [][][] matrizRef, GNode inicial,int filas, int columnas, int x, int y){
        grafo = g;
        GNode[] vertices=grafo.getLgn();
        inicial.changeVisited(true);
        
        if(inicial.isHasCavidad()){
            
                colaVertices.offer(inicial);
                while(!colaVertices.isEmpty()){
                    nactual = colaVertices.poll();
           
                    if(this.matriz[0][actual.getX()][actual.getY()]==0&&actual.getY()==(columnas-1)){
                        return actual.getDistancia();
                    }
                    matrizEstado[actual.getX()][actual.getY()]=true;
                    for(int i=0;i<4;i++){
                        int nuevoX = actual.getX() + movx[i];
                        int nuevoY = actual.getY() + movy[i];
                        if(nuevoX >=0 && nuevoX <columnas && nuevoY >=0 && nuevoY < filas){
                            if(!matrizEstado[nuevoX][nuevoY] && this.matriz[0][nuevoX][nuevoY]!=1){
                                System.out.println(nuevoX +", "+nuevoY);
                                Estado nuevoEstado = new Estado(nuevoX, nuevoY, actual.getDistancia()+1);
                                colaEstados.offer(nuevoEstado);
                            }
                        }
                    }
            
        }

    }
        System.out.println("Distancia de interrupción: "+actual.getDistancia());
        //System.out.println("Tortuosidad: "+(actual.getDistancia()/columnas));
        return -1;
    }
    

    public ArrayList<Estado> getRuta() {
        return ruta;
    }
    
}
