/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

/**
 *
 * @author LuisEduardo
 */
public class Matriz {
    private int[][][] matriz;
    private int nivel;
    private int columna;
    private int fila;

    public int getColumna() {
        return columna;
    }
    
    public Matriz(int nivel,int fila, int columna){
        matriz= new int[nivel][fila][columna];
        this.nivel=nivel;
        this.columna=columna;
        this.fila=fila;
    }
    
    public void agregarElemento(int nivel, int columna, int fila, int el){
        matriz[nivel][columna][fila]= el;
    }

    public int[][][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int[][][] matriz) {
        this.matriz = matriz;
    }
    
    public void generarAleatoria(){
        Random r=new Random();
        for(int n=0;n<this.nivel;n++){
            for(int f=0;f<this.fila;f++){
                int cc=0;
                for(int c=0;c<this.columna;c++){
                    int el;
                    if(c==(this.columna-1) && cc==0){
                        el=0;
                    }else{
                        el=r.nextInt(2);
                        if(el==0){
                            cc+=1;
                        }
                    }
                    this.matriz[n][f][c]=el;
                    
                }
            }
        }
    }
    
   @Override
   public String toString(){
       StringBuilder s=new StringBuilder();
       
       return s.toString();
   }
    
   public void mostarm(){
       for(int n=0;n<this.nivel;n++){
           StringBuilder s=new StringBuilder();
           for(int f=0;f<this.fila;f++){
               s.append("[");
                for(int c=0;c<this.columna;c++){
                    if(c==(this.columna-1)){
                    s.append(this.matriz[n][f][c]);
                    }else{
                        s.append(this.matriz[n][f][c]);
                    s.append(",");
                    }
                }
                if(f==(this.fila-1)){
                    s.append("]");
                }else{
                    s.append("]\n");
                }
            }
           s.append("\n");
           System.out.println(s.toString());
        }
       
   }
    
   
   public PriorityQueue<Ducto> IdentificarDuctos(){
       
       PriorityQueue<Ducto> pqd =new PriorityQueue<>((d1,d2)->d1.getNivel()-d2.getNivel());
       for(int n=0;n<this.nivel;n++){
            for(int f=0;f<this.fila;f++){
                int c0=0;
                int coli=0;
                int colf=0;
                for(int c=0;c<this.columna;c++){
                    if(this.matriz[n][f][c]==0){
                        if(c0==0){
                          coli=c;
                        }
                        c0+=1;
                        if((c+1)==this.columna){
                            Ducto d=new Ducto(n,coli,c,c0,f);
                            pqd.offer(d);
                        }
                    }
                    
                    if(this.matriz[n][f][c]==1 && c0!=0){
                        colf=c-1;
                        Ducto d=new Ducto(n,coli,colf,c0,f);
                        pqd.offer(d);
                        c0=0;
                        coli=0;
                    }
                    
                }
                
                
            }
        }
       
       return pqd;
   }
   
   
   public ArrayList<Cavidad> IdentificarCavidades(PriorityQueue<Ducto> pqd,ArrayList<Ducto> ldp){
       ArrayList<Cavidad> lc=new ArrayList<>();
       PriorityQueue<Ducto> pqdm= new PriorityQueue<>((d1,d2)->d1.getFila()-d2.getFila());
       PriorityQueue<Ducto> pqdr= new PriorityQueue<>((d1,d2)->d1.getNivel()-d2.getNivel());
       for(Ducto ducto:ldp){
            pqdr.offer(ducto);
       }
       int nivela=0;
       while(!pqd.isEmpty()){
         Ducto d=pqd.poll();
         if(d.getNivel()==nivela){
             pqdm.add(d);
         }else{
             //System.out.println("momazo");
             ArrayList<Ducto> ldr=new ArrayList<>();
             while(!pqdr.isEmpty() && pqdr.peek().getNivel()==nivela){
                 Ducto ducto=pqdr.poll();
                 ldr.add(ducto);
             }
             Arbol ad= new Arbol(ldp.size());
             
             ad.agregarRaices(ldr);
             System.out.println(pqdm.size());
             while(!pqdm.isEmpty()){
                 Ducto duct=pqdm.poll();
                 ad.agregarElemento(duct);
             }
             System.out.println(ad.toString());
             nivela=d.getNivel();
             pqd.offer(d);
         }
       }
       
       
       
       return lc;
   }
   
   
   public ArrayList<Ducto> identificarPadres(PriorityQueue<Ducto> pqd){
       ArrayList<Ducto> ld=new ArrayList<>();
       ArrayList<Ducto> ldp=new ArrayList<>();
       while(!pqd.isEmpty()){
           Ducto d=pqd.poll();
           boolean padre=true;
           for(int i=d.getColi();i<(d.getColf()+1);i++){
               if(d.getFila()==0){
                   padre=true;
               }else if(this.matriz[d.getNivel()][d.getFila()-1][i]==0){
                   padre=false;
               }
           }
           ld.add(d);
           if(padre){
               ldp.add(d);
             //  System.out.println(d);
           }
       }
       
       for(Ducto duc:ld){
           pqd.offer(duc);
       }
       
       return ldp;
   }
   
   public ArrayList<Grafo> experimento(){
       ArrayList<Grafo> al=new ArrayList<>();
        for(int n=0;n<this.nivel;n++){
            Grafo gf=new Grafo(this.columna*this.fila);
            for(int f=0;f<this.fila;f++){
                for(int c=0;c<this.columna;c++){
                    if(this.matriz[n][f][c]==0){
          //              System.out.println("posicion "+n +" "+ f +" "+c);
                        gf.addGNode(this.buscarExp(n, f, c));
                       // this.mostarm();
                    }
                }
            }
            gf.crearVertices();
            gf.VerticesEspejo();
            al.add(gf);
        }
        
        return al;
    }
   
   public GNode buscarExp(int n,int f, int c){
       boolean completo=false;
       boolean limiteder=false;
       boolean limiteinf=false;
       int ci=c;
       int cpf=c;
       int fi=f;
       int fpf=f;
       while(!completo){
           
           if(!limiteinf){
               if(!((fpf+1)>=this.fila)){ //verifico que existe una celda abajo 
                   if(this.matriz[n][fpf+1][c]==0){ //verifico que la celda sea cero
                       if(!(this.fila<=(fpf+2))){ //verfico si existe una celda dos posiciones abajo
                           if(this.matriz[n][fpf+2][c]==0){ //verfico que la celda dos posiciones abajo sea cero
                               if(!(this.columna<=(c+1))){ //verifico si existe una columna una posicion mayor
                                   if(this.matriz[n][fpf+1][c+1]==0 && this.matriz[n][fpf+2][c+1]==0 && this.matriz[n][fpf][c+1]==1){
                                       limiteinf=true;
                                   }
                               }
                               
                               if(!((c-1)<0)){ // verifico si existe una columna una posicion menor
                                   if(this.matriz[n][fpf+1][c-1]==0 && this.matriz[n][fpf+2][c-1]==0 && this.matriz[n][fpf][c-1]==1){
                                       limiteinf=true;
                                   }
                               }
                               
                           }
                       }
                       if(!limiteinf){
                            fpf+=1;
                       }
                   }else{
                       limiteinf=true;
                   }
               }else{
                   limiteinf=true;
               }
           }
           
           if(!limiteder){
               if(!((cpf+1)>=this.columna)){ //verifico que existe una columna mas adeltante
                   if(this.matriz[n][fi][cpf+1]==0){ //verifico que la celda delantera sea 0
                       if(!((fpf+1>=this.fila))){ //verifico que existe una fila mas abajo
                            if(limiteinf && fi==fpf && this.matriz[n][fpf+1][cpf+1]==0){ // verifico si estoy creando un ducto
                                    limiteder=true;
                            }
                       }
                       
                       if(!limiteder){
                           cpf+=1;
                       }
                       
                   }else{
                       limiteder=true;
                   }
               }else{
                   limiteder=true;
               }
           }
           
           
           if(!limiteinf){
            for(int c2=ci;c2<=cpf;c2++){
                    if(this.matriz[n][fpf][c2]==1){
                        fpf=fpf-1;
                        limiteinf=true;
                        c2=cpf+1;
                    }

            }
           }
           
           if(!limiteder){
            for(int f2=fi;f2<=fpf;f2++){
                    if(this.matriz[n][f2][cpf]==1){
                        cpf=cpf-1;
                        limiteder=true;
                        f2=fpf+1;
                    }

            }
           }
           
           if((limiteder && limiteinf) /*|| this.fila<=fpf || this.columna<=cpf */){
               completo=true;
           }
       }
       
       if(fi!=fpf){
           int c0=(fpf-fi+1)*(cpf-ci+1);
           Cavidad cav=new Cavidad(n,fi,fpf,ci,cpf,c0);
      //     System.out.println(cav);
           GNode gn=new GNode(cav);
           this.verificarPos(gn);
           this.llenarUno(gn);
           return gn;
       }else{
           int c0=(cpf-ci+1);
           Ducto d=new Ducto(n,ci,cpf,c0,fi);
     //      System.out.println(d);
           GNode gn=new GNode(d);
           this.verificarPos(gn);
           this.llenarUno(gn);
           return gn;
       }
       //falta poner todos los cuadros que ya estan en 1 y conectar el grafo alv
   }
   
   public void llenarUno(GNode n){
       if(n.isHasCavidad()){
           Cavidad cav= n.getCavidad();
       //    System.out.println("Entro cavidad");
           for(int f=cav.getFilai();f<=cav.getFilaf();f++){
               for(int c=cav.getColi();c<=cav.getColf();c++){
                   this.matriz[cav.getNivel()][f][c]=1;
               }
           }
       }else{
           Ducto d= n.getDucto();
     //      System.out.println("Entro ducto");
           for(int c=d.getColi();c<=d.getColf();c++){
                   this.matriz[d.getNivel()][d.getFila()][c]=1;
               }
       }
       
   }
   
   public void verificarPos(GNode gn){
       if(gn.isHasCavidad()){
           Cavidad cav= gn.getCavidad();
           if(cav.getColi()==0){
               gn.setPos(0);
           }else if(cav.getColf()==(this.columna-1)){
               gn.setPos(1);
           }else{
               gn.setPos(-1);
           }
       }else{
           Ducto d= gn.getDucto();
           if(d.getColi()==0){
               gn.setPos(0);
           }else if(d.getColf()==(this.columna-1)){
               gn.setPos(1);
           }else{
               gn.setPos(-1);
           } 
           
       }
   }
   
}
