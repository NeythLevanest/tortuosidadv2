/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import metodologia.Colores;

/**
 *
 * @author Neythan León
 */
public class MatrizPrueba {
    private int[][] matriz;
    private Graphics grafico;
    private int filas;
    private int columnas;
    //private Rectangle [][] matrizPaint;
    public MatrizPrueba(){}
    
    public MatrizPrueba(Graphics grafico){
        this.grafico=grafico;
    }
    
    public MatrizPrueba(int filas, int columnas){
        this.filas=filas;
        this.columnas=columnas;
    }
    
    public void paint(){
        //Aqui se debe cambiar el nombre del archivo para graficar la matriz
        this.matriz = getReadMatrizFile("7",185,171);
        
        for(int f=0;f<filas;f++){
            for(int c=0;c<columnas;c++){
                //matrizPaint[i][j]=(new Rectangle(512/columnas*j,512/filas*i,512/columnas,512/filas)); 
                if(this.matriz[f][c]==1){
                    grafico.setColor(Color.blue);
                    grafico.fillRect(c*20, f*20, 20, 20);
                    grafico.setColor(Color.black);
                    grafico.drawRect(c*20, f*20, 20,20);
                }
            }
        }
    }
    
    public int[][] getReadMatrizFile(String direccion, int filas, int columnas){
        this.filas=filas;
        this.columnas=columnas;
        int [][] lmatriz  = new int[filas][columnas];
        try {
            BufferedReader br = getBufferedArchivo("./src/assets/"+direccion+".txt");
            //leemos la primera linea
            String linea =  br.readLine();
            int contador = 0;
            while(linea != null){
                String[] values = linea.split("");
                //recorremos el array de string
                for (int i = 0; i<values.length; i++) {
                    //se obtiene el primer caracter de el arreglo de strings
                    int coordenadaValor = Integer.parseInt(values[i]);
                    if(coordenadaValor==1){
                        lmatriz[contador][i] = 0;
                    }else{
                        lmatriz[contador][i] = 1;
                    }
                    
                }
                contador++;
                linea = br.readLine();
            }
        } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
        }
        return lmatriz;
    }
    
    public BufferedReader getBufferedArchivo(String link){

        FileReader lector  = null;
        BufferedReader br = null;
        try {
             File Arch=new File(link);
            if(!Arch.exists()){
               System.out.println("No existe el archivo");
            }else{
               lector = new FileReader(link);
               br = new BufferedReader(lector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return br;
}

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
    public void mostarmMatriz(){
           StringBuilder s=new StringBuilder();
           for(int f=0;f<this.filas;f++){
               s.append("[");
                for(int c=0;c<this.columnas;c++){
                    if(c==(this.columnas-1)){
                    s.append(this.matriz[f][c]);
                    }else{
                        s.append(this.matriz[f][c]);
                        s.append(",");
                    }
                }
                if(f==(this.filas-1)){
                    s.append("]");
                }else{
                    s.append("]\n");
                }
            }
           s.append("\n");
           System.out.println(s.toString());
        }

  
   
}
