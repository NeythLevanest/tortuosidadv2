/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 *
 * @author LuisEduardo
 */
public class Coordenada {
    private int columna;
    private int fila;
    
    public Coordenada(int col, int fila){
        this.columna=col;
        this.fila=fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }
    
    public String toString(){
        return "Coordenada: x: "+this.columna+" y: "+this.fila;
    }
    
}
