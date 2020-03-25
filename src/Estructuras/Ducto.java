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
public class Ducto {
    private int nivel;
    private int fila;
    private int coli;
    private int colf;
    private int c0;

    public int getC0() {
        return c0;
    }

    public void setC0(int c0) {
        this.c0 = c0;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public Ducto(int nivel, int coli, int colf, int c0,int fila) {
        this.nivel = nivel;
        this.coli = coli;
        this.colf = colf;
        this.c0=c0;
        this.fila=fila;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getColi() {
        return coli;
    }

    public void setColi(int coli) {
        this.coli = coli;
    }

    public int getColf() {
        return colf;
    }

    public void setColf(int colf) {
        this.colf = colf;
    }
    
    @Override
    public String toString(){
        return " Ducto: nivel: "+this.nivel+" coli: "+this.coli+" colf: "+this.colf+ " fila: "+this.fila;
    }
    
    public boolean equals(Object o){
        if(o==null){
            return false;
        }else if(!(o instanceof Ducto)){
            return false;
        }else{
            Ducto d= (Ducto) o;
            if(this.nivel==d.getNivel() && this.fila==d.fila && this.coli==d.coli && this.colf==d.colf/* && this.c0==d.c0*/){
                return true;
            }
            return false;
        }
        
    }
    
}
