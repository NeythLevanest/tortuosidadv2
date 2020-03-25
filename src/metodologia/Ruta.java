/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodologia;

import BFS.Estado;
import BFS.TortuosidadBFS;
import Estructuras.MatrizPrueba;
import com.sun.webkit.ThemeClient;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import static metodologia.Colores.generarColor;

/**
 *
 * @author Neythan León
 */
public class Ruta extends Thread{
    Graphics grafico;
    Pane pane;
    TortuosidadBFS BFSMatriz;
    MatrizPrueba matrizPrueba;
    Rectangle [][] rutaPaint;
    int espacioLibre = 1;
    int tamanoCelda = 20;
   
    int [][] matrizAOperar; //CREARLAS MATRICES A ANALIZAR AQUI
    float sumaTortuosidades =0;
    int filasAnalizadas = 0;
    Color  color;
    public Ruta(Graphics grafico){
        this.grafico=grafico;
        this.matrizPrueba = new MatrizPrueba(185,171);
        //Al instanciar la matriz de prueba el primer parametro de la funcion getReadMatriz es el nombre (string) del archivo en la carpeta assets con extencion .txt
        matrizAOperar = matrizPrueba.getReadMatrizFile("7",matrizPrueba.getFilas(),matrizPrueba.getColumnas());

    }
    public void paint() {
        //EXAMINA CADA UNA DE LAS ENTRADAS DE LA MATRIZ
        for(int fila=0;fila<matrizPrueba.getFilas();fila++){
            color = generarColor();
            if(matrizAOperar[fila][0]!=espacioLibre){
                for(int destino=0;destino<matrizPrueba.getFilas();destino++){
                    color = generarColor();
                    if(matrizAOperar[0][matrizPrueba.getColumnas()-1]!=espacioLibre){
                        System.out.println("Entrada en Fila: "+(fila+1));
                        System.out.println("Salidad esperada: "+(destino));
                        BFSMatriz = new TortuosidadBFS();
                        int pasos = BFSMatriz.rutaBFSMatriz(matrizAOperar, matrizPrueba.getFilas(), matrizPrueba.getColumnas(), fila, 0,destino); //ASUMIMOS MATRICES DE 30X50
                        for(Estado estado:BFSMatriz.getRuta()){
                            grafico.setColor(color);
                            grafico.fillRect(estado.getY()*tamanoCelda,estado.getX()*tamanoCelda, tamanoCelda, tamanoCelda);
                            grafico.setColor(color);
                            grafico.fillRect(estado.getY()*tamanoCelda,estado.getX()*tamanoCelda, tamanoCelda, tamanoCelda);
                        }
                        if (pasos == -1){
                            System.out.println("Error 404: Destino not found\n");
                        } else {
                            System.out.print("Minima cantidad de pasos: "+pasos+"\n");
                            System.out.println("Tortuosidad: "+(float)(pasos+1)/matrizPrueba.getColumnas());
                            sumaTortuosidades+=(float)(pasos+1)/matrizPrueba.getColumnas();
                            filasAnalizadas+=1;
                        }
                    }
                }
        }}
        System.out.println("Promedio de tortuosidad: " +(float)sumaTortuosidades/filasAnalizadas);
    }
    @Override
    public void run(){
        System.out.println("Hilo se ejecuta correctamente");
        paint();
    }
    //public void obtenerRutas(){
        //for(int fila=0;fila<filas;fila++){
            //if(mcuadros.getMatrizEstructuraRectangular()[fila][0]!=1){
                //System.out.println("Entrada en Fila: "+(fila+1));
                //int pasos = BFSMatriz.rutaBFSMatrizDefaultCuadros(matrizIdeal, 30, 50, fila, 0);
               //if (pasos == -1){
               //    System.out.println("Error 404: Destino not found\n");
               //  } else {
               //     System.out.print("Minima cantidad de pasos: "+pasos+"\n");
             //   }
        //}
   // }
    
}
      
