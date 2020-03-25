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
public class Cavidad {
    private int nivel;
    private int filai;
    private int filaf;
    private int coli;
    private int colf;
    private int c0;

    public Cavidad(int nivel, int filai, int filaf, int coli, int colf, int c0) {
        this.nivel = nivel;
        this.filai = filai;
        this.filaf = filaf;
        this.coli = coli;
        this.colf = colf;
        this.c0=c0;
    }

    public int getC0() {
        return c0;
    }

    public void setC1(int c0) {
        this.c0 = c0;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getFilai() {
        return filai;
    }

    public void setFilai(int filai) {
        this.filai = filai;
    }

    public int getFilaf() {
        return filaf;
    }

    public void setFilaf(int filaf) {
        this.filaf = filaf;
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
    
    
    public boolean equals(Object o){
        if(o==null){
            return false;
        }else if(!(o instanceof Cavidad)){
            return false;
        }else{
            Cavidad cv=(Cavidad) o;
            return this.coli==cv.coli && this.colf==cv.colf && this.filaf==cv.filaf && this.filai==cv.filai && this.nivel==cv.nivel;
        }
    }
    
    public String toString(){
        
        return "Cavidad.- nivel: "+this.getNivel()+" coli: "+this.getColi()+" colf: "+this.getColf()+" fili: "+this.getFilai()+" filf: "+this.getFilaf();
    }
    
}
