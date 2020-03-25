/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import java.util.ArrayList;

/**
 *
 * @author LuisEduardo
 */
public class GNode {
    private ArrayList<Vertice> verticesAdyacentes;
    private boolean hasCavidad;
    private boolean hasDucto;
    private boolean wasVisited;
    private Cavidad cavidad;
    private Ducto ducto;
    private int pos;
    
    public GNode(Cavidad c){
        hasCavidad=true;
        hasDucto=false;
        wasVisited=false;
        cavidad=c;
        ducto=null;
        pos=0;
    }

    public boolean getWasVisited() {
        return wasVisited;
    }

    public void changeVisited(boolean wasVisited) {
        this.wasVisited = wasVisited;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
    
    
    
    public GNode(Ducto d){
        hasCavidad=false;
        hasDucto=true;
        cavidad=null;
        ducto=d;
    }

    public boolean isHasCavidad() {
        return hasCavidad;
    }

    public void setHasCavidad(boolean hasCavidad) {
        this.hasCavidad = hasCavidad;
    }

    public boolean isHasDucto() {
        return hasDucto;
    }

    public void setHasDucto(boolean hasDucto) {
        this.hasDucto = hasDucto;
    }

    public Cavidad getCavidad() {
        return cavidad;
    }

    public void setCavidad(Cavidad cavidad) {
        this.cavidad = cavidad;
    }

    public Ducto getDucto() {
        return ducto;
    }

    public void setDucto(Ducto ducto) {
        this.ducto = ducto;
    }
    
    public boolean equals(Object o){
        if(o==null){
            return false;
        }else if(!(o instanceof GNode)){
            return false;
        }else{
            GNode gn=(GNode) o;
            if(gn.hasCavidad && this.hasCavidad){
                return gn.getCavidad().equals(this.getCavidad());
            }else if(gn.hasDucto && this.hasDucto){
                return gn.getDucto().equals(this.getDucto());
            }else{
                
                return false;
            }
        }
    }
    
    public int getC0(){
        if(this.hasCavidad){
            return this.cavidad.getC0();
        }else{
            return this.ducto.getC0();
        }
    }
    
    public String toString(){
        if(this.hasCavidad){
            return this.cavidad.toString();
        }else{
            return this.ducto.toString();
        }
    }

    public void setWasVisited(boolean wasVisited) {
        this.wasVisited = wasVisited;
    }
    
    
}
