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
public class Vertice {// Esto en verdad es arco 
    private GNode fn;//nodo salida
    private GNode ln;//nodo llegada
    private double distancia;
    private ArrayList<Interseccion> listaInt;
    private String dir;
    
    public Vertice(GNode fn, GNode ln,String dir){
        this.fn=fn;
        this.ln=ln;
        distancia=0;
        listaInt=new ArrayList<>();
        this.dir=dir;
    }
    

    public ArrayList<Interseccion> getListaInt() {
        return listaInt;
    }

    public void setListaInt(ArrayList<Interseccion> listaInt) {
        this.listaInt = listaInt;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
    

    public GNode getFn() {
        return fn;
    }

    public void setFn(GNode fn) {
        this.fn = fn;
    }

    public GNode getLn() {
        return ln;
    }

    public void setLn(GNode ln) {
        this.ln = ln;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
    
     public boolean equals(Object o){
        if(o==null){
            return false;
        }else if(!(o instanceof Vertice)){
            return false;
        }else{
            Vertice v= (Vertice) o;
            return v.fn.equals(this.fn) && v.ln.equals(this.ln);
        }
    }
    
     
    public void CalcularInterseccion(){
        if(this.fn.isHasDucto()){ //el nodo de salida es ducto
            Ducto d=this.fn.getDucto();
            if(this.ln.isHasDucto()){//el nodo de llegada es un ducto
                Ducto d2=this.ln.getDucto();
                if(this.dir.equalsIgnoreCase("Abajo")){//Los ductos estan uno arriba del otro
                    for(int i=d.getColi();i<=d.getColf();i++){
                        if(i>=d2.getColi() && i<=d2.getColf()){
              //              System.out.println(d.getFila()+"esta abajo"+d2.getFila());
                            Coordenada coordSalida=new Coordenada(i,d.getFila());
                            Coordenada coordLLegada=new Coordenada(i,d2.getFila());
              //              System.out.println(coordSalida);
           //                 System.out.println(coordLLegada);
                            Interseccion inter=new Interseccion(coordSalida,coordLLegada);
                      //      System.out.println(inter);
                            this.listaInt.add(inter);
                        }
                    }
                }else{//los ductos estan adayacentes
                   // System.out.println(d.getColf()+"estan adyacentes"+d2.getColi());
                    
                    Coordenada coordSalida=new Coordenada(d.getColf(),d.getFila());
                    Coordenada coordLlegada= new Coordenada(d2.getColi(),d.getFila());
                    Interseccion inter=new Interseccion(coordSalida,coordLlegada);
                    this.listaInt.add(inter);
                }
            }else{//el nodo de llegada es cavidad
                Cavidad c=this.ln.getCavidad();
                if(this.dir.equalsIgnoreCase("Derecha")){// la cavidad esta a la derecha del ducto
                            Coordenada coordSalida=new Coordenada(d.getColf(),d.getFila());
                            Coordenada coordLLegada=new Coordenada(c.getColi(),d.getFila());
                            Interseccion inter=new Interseccion(coordSalida,coordLLegada);
                            this.listaInt.add(inter);
                }else{// la cavidad esta abajo del ducto
                    for(int i=d.getColi();i<=d.getColf();i++){
                        if(i>=c.getColi() && i<=c.getColf()){
                            Coordenada coordSalida=new Coordenada(i,d.getFila());
                            Coordenada coordLLegada=new Coordenada(i,c.getFilai());
                            Interseccion inter=new Interseccion(coordSalida,coordLLegada);
                            this.listaInt.add(inter);
                        }
                    }
                }
            }
        }else{//el nodo de salida es cavidad
            Cavidad c=this.fn.getCavidad();
            if(this.ln.isHasDucto()){//el nodo de llegada es ducto
                Ducto duct=this.ln.getDucto();
                if(this.dir.equalsIgnoreCase("Derecha")){//el ducto esta a la derecha de la cavidad
                            Coordenada coordSalida=new Coordenada(c.getColf(),duct.getFila());
                            Coordenada coordLLegada=new Coordenada(duct.getColi(),duct.getFila());
                            Interseccion inter=new Interseccion(coordSalida,coordLLegada);
                            this.listaInt.add(inter);
                }else{//el ducto esta abajo de la cavidad
                    for(int i=duct.getColi();i<=duct.getColf();i++){
                        if(i>=c.getColi() && i<=c.getColf()){
                            Coordenada coordSalida=new Coordenada(i,c.getFilaf());
                            Coordenada coordLLegada=new Coordenada(i,duct.getFila());
                            Interseccion inter=new Interseccion(coordSalida,coordLLegada);
                            this.listaInt.add(inter);
                        }
                    }
                }
            }else{//el nodo de llegada es cavidad
                Cavidad c2=this.ln.getCavidad();
                if(this.dir.equalsIgnoreCase("Derecha")){ //la segunda cavidad esta a la derecha de la primera
                    for(int i=c.getFilai();i<=c.getFilaf();i++){
                        if(i>=c2.getFilai() && i<=c2.getFilaf()){
                            Coordenada coordSalida=new Coordenada(c.getColf(),i);
                            Coordenada coordLLegada=new Coordenada(c2.getColi(),i);
                            Interseccion inter=new Interseccion(coordSalida,coordLLegada);
                            this.listaInt.add(inter);
                        }
                    }
                }else{ //la segunda cavidad esta debajo de la primera cavidad
                    for(int i=c.getColi();i<=c.getColf();i++){
                        if(i>=c2.getColi() && i<=c2.getColf()){
                            Coordenada coordSalida=new Coordenada(i,c.getFilaf());
                            Coordenada coordLLegada=new Coordenada(i,c2.getFilai());
                            Interseccion inter=new Interseccion(coordSalida,coordLLegada);
                            this.listaInt.add(inter);
                        }
                    }
                }
            }
        }
    } 
     
    
    public String toString(){
        return "Salida: "+this.getFn()+" Llegada: "+this.getLn();
    }
}
