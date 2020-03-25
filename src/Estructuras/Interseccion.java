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
public class Interseccion {
    private Coordenada coordNodoSalida=null;
    private Coordenada coordNodoLlegada=null;
    
    public Interseccion(Coordenada coordNodoSalida,Coordenada coordNodoLlegada){
        this.coordNodoLlegada=coordNodoLlegada;
        this.coordNodoSalida=coordNodoSalida;
    }

    public Coordenada getCoordNodoSalida() {
        return coordNodoSalida;
    }

    public void setCoordNodoSalida(Coordenada coordNodoSalida) {
        this.coordNodoSalida = coordNodoSalida;
    }

    public Coordenada getCoordNodoLlegada() {
        return coordNodoLlegada;
    }

    public void setCoordNodoLlegada(Coordenada coordNodoLlegada) {
        this.coordNodoLlegada = coordNodoLlegada;
    }
    
    public String toString(){
        return "Inicio x: "+this.coordNodoSalida.getColumna()+" y: "+this.coordNodoSalida.getFila()
                +" LLegada x: "+this.coordNodoLlegada.getColumna()+" y: "+this.coordNodoLlegada.getFila();
    }
    
}
