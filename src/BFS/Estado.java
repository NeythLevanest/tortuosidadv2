/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BFS;

/**
 *
 * @author Neythan Leon
 */
public class Estado {
    private boolean[][] matrizEstado;
    private int[][] matriz;
    private int[] movx={1,0,0,0};
    private int[] movy={0,0,1,-1};
    
    private int x;
    private int y;
    private int distancia;
    
    public Estado(int x, int y, int distancia){
        this.x=x;
        this.y=y;
        this.distancia=distancia;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }
    
}
