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
public class NodeD {
    private NodeD hijos[];
    private int canti;
    private Ducto d;
    private int proxh;
    private int size;
    
    public NodeD(int size){
        this.hijos=new NodeD[size];
        this.d=null;
        this.proxh=0;
        this.canti=0;
        this.size=size;
    }
    
    public void agregarHijo(NodeD n){
        if(this.canti<this.size){
            this.hijos[this.proxh]=n;
            this.canti+=1;
            this.proxh+=1;
        }
    }
    
    public void agregarDucto(Ducto duc){
        this.d=duc;
    }

    public NodeD[] getHijos() {
        return hijos;
    }

    public void setHijos(NodeD[] hijos) {
        this.hijos = hijos;
    }

    public int getCanti() {
        return canti;
    }

    public void setCanti(int canti) {
        this.canti = canti;
    }

    public Ducto getD() {
        return d;
    }

    public void setD(Ducto d) {
        this.d = d;
    }

    public int getProxh() {
        return proxh;
    }

    public void setProxh(int proxh) {
        this.proxh = proxh;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
   public String toString(){
       StringBuilder sb=new StringBuilder();
       String s="Ducto :"+d+" con cantidad de hijos: "+this.proxh+" con hijos:";
       sb.append(s);
       for(int i=0;i<this.proxh;i++){
           sb.append(this.hijos[i]);
       }
               
       return sb.toString();
   }
   
   public boolean verificarHijo(Ducto d){
       for(int i=0;i<this.proxh;i++){
           if(this.hijos[i].getD().equals(d)){
               return true;
           }
       }
       return false;
   }
   
}
