/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author LuisEduardo
 */
public class Grafo {
    private GNode lgn[]; //vertices
    private Vertice lv[]; //arcos en realidad
    private int elementosV;
    private int elementosGN;
    
    public Grafo(int el){
        lgn= new GNode[el];
        lv= new Vertice[el];
        elementosV=0;
        elementosGN=0;
    }

    public GNode[] getLgn() {
        return lgn;
    }

    public Vertice[] getLv() {
        return lv;
    }
    
    /*public void CrearPuntosInterseccion(){
        for(int i=0;i<this.elementosV;i++){
            this.lv[i].CalcularInterseccion();
        }
    }*/
    
    public void VerticesEspejo(){
        int limite=this.elementosV;
        for(int i=0;i<limite;i++){
            Vertice v=this.lv[i];
            if(v.getDir().equalsIgnoreCase("Derecha")){
                this.addVerticeEspejo(v.getLn(), v.getFn(), "Izquierda",v.getListaInt());
            }else{
                this.addVerticeEspejo(v.getLn(), v.getFn(), "Arriba",v.getListaInt());
            }
        }
    }
    
    public ArrayList<GNode> Iniciales(){
        ArrayList<GNode> lista=new ArrayList<>();
        for(int i=0;i<this.elementosGN;i++){
            if(this.lgn[i].getPos()==0){
                lista.add(lgn[i]);
              //  System.out.println(lgn[i]);
            }
        }
        return lista;
    }
    
    public ArrayList<GNode> Finales(){
        ArrayList<GNode> lista=new ArrayList<>();
        for(int i=0;i<this.elementosGN;i++){
            if(this.lgn[i].getPos()==1){
                lista.add(lgn[i]);
             //   System.out.println(lgn[i]);
            }
        }
        return lista;
    }
    
    public void Imprimmirvertices(){
        for(int i=0;i<this.elementosV;i++){
            System.out.println(this.lv[i].getFn());
        }
    }
    
    public void ImprimmirNodos(){
        for(int i=0;i<this.elementosGN;i++){
            System.out.println(this.lgn[i]);
        }
    }
    
    public boolean Visitado(ArrayList<GNode> ln,GNode gn){
        for(GNode gnn:ln){
            if(gnn.equals(gn)){
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<ArrayList<Vertice>> posiblesCaminos(GNode inicio, GNode fin){
        ArrayList<ArrayList<Vertice>> res=new ArrayList<>();
        Queue<ArrayList<GNode>> q=new LinkedList<>();
        ArrayList<GNode> camino=new ArrayList<>();
        Queue<ArrayList<Vertice>> qv=new LinkedList<>();
        camino.add(inicio);
        ArrayList<Vertice> vers=new ArrayList<>();
        qv.offer(vers);
        q.offer(camino);
        while(!q.isEmpty()){
            camino=q.poll();
            vers=qv.poll();
            GNode ultimo= camino.get(camino.size()-1);
            
            if(fin.equals(ultimo)){
                System.out.println("Camino: "+camino);
                res.add(vers);
            }
            
            for(int i=0;i<this.elementosV;i++){
                Vertice v=this.lv[i];
                if(v.getFn().equals(ultimo)){
                    GNode siguiente= v.getLn();
                    if(!this.Visitado(camino, siguiente)){
                            ArrayList<GNode> caminoNuevo=new ArrayList<>(camino);
                            ArrayList<Vertice> verNuevo=new ArrayList<>(vers);
                            caminoNuevo.add(siguiente);
                            q.offer(caminoNuevo);
                            verNuevo.add(v);
                            qv.offer(verNuevo);
                        
                    }
                }
            }
            
        }
        System.out.println(res);
        return res;
    }
    
    public double CalcularDistancias(ArrayList<ArrayList<Vertice>> listav){
        double dist=0;
        for(ArrayList<Vertice> lvv: listav){
            double colin=0;
            double filin=0;
            for(Vertice v: lvv){
                double colf=0;
                double filf=0;
                if(v.getDir().equalsIgnoreCase("Derecha") || v.getDir().equalsIgnoreCase("Izquierda") ){
                    for(Interseccion in:v.getListaInt()){
                        colf=in.getCoordNodoSalida().getColumna()+1;//preguntar
                        filf+=in.getCoordNodoSalida().getFila();
                    }
                    filf=filf/v.getListaInt().size() +0.5;
                }else if(v.getDir().equalsIgnoreCase("Abajo") || v.getDir().equalsIgnoreCase("Arriba")){
                    for(Interseccion in:v.getListaInt()){
                        filf=in.getCoordNodoSalida().getFila();
                        colf+=in.getCoordNodoSalida().getColumna();
                    }
                    colf=colf/v.getListaInt().size() +0.5;
                }
                
                    double coli=0;
                    double fili=0;
                    if(v.getFn().getPos()==0){
                        if(v.getFn().isHasDucto()){
                            Ducto d=v.getFn().getDucto();
                            coli=d.getColi();
                            fili=d.getFila()+0.5;
                        }else{
                            Cavidad cv=v.getFn().getCavidad();
                            fili= (cv.getFilaf()+cv.getFilai())/2  + 0.5;
                            coli= cv.getColi();
                        }
                    }else{
                        coli=colin;
                        fili=filin;
                    }
                    System.out.println(coli+"-"+colf+"="+ (coli-colf));
                    System.out.println(fili+"-"+filf+"="+(fili-filf));
                    double val=Math.hypot(coli-colf, fili-filf);
                    System.out.println("valor: "+val);
                    dist+=val;
                    colin=colf;
                    filin=filf;
                    
                if(v.getLn().getPos()==1){
                    if(v.getLn().isHasDucto()){
                            Ducto d=v.getLn().getDucto();
                            colf=d.getColf()+1;
                            filf=d.getFila()+0.5;
                    }else{
                            Cavidad cv=v.getLn().getCavidad();
                            filf= (cv.getFilaf()+cv.getFilai())/2+0.5;
                            colf= cv.getColf()+1;
                    }
                    System.out.println("le finale");
                    System.out.println(colin+"-"+colf+"="+ (colin-colf));
                    System.out.println(filin+"-"+filf+"="+(filin-filf));
                    val=Math.hypot(colin-colf, filin-filf);
                    System.out.println(val);
                    dist+=val;
                }
            }
        }
        System.out.println("distancia: "+dist);
        System.out.println("distancia prom: "+ dist/listav.size());
        return dist/listav.size();
    }
    
    public boolean addVertice(GNode fn, GNode ln,String dir){
        Vertice v=new Vertice(fn,ln,dir);
        
        
        for(int i=0;i<elementosV;i++){
            if(lv[i].equals(v)){
                return false;
            }
        }
        lv[elementosV]=v;
        elementosV+=1;
        v.CalcularInterseccion();
        return true;
    }
    
    public boolean addVerticeEspejo(GNode fn, GNode ln,String dir,ArrayList<Interseccion> li){
        Vertice v=new Vertice(fn,ln,dir);
        for(int i=0;i<elementosV;i++){
            if(lv[i].equals(v)){
                return false;
            }
        }
        lv[elementosV]=v;
        elementosV+=1;
        for(Interseccion i: li){
            Interseccion in=new Interseccion(i.getCoordNodoLlegada(),i.getCoordNodoSalida());
            v.getListaInt().add(in);
        }
        return true;
    }
    
    public boolean addGNode(GNode gn){
        for(int i=0;i<elementosGN;i++){
            if(lgn[i].equals(gn)){
                return false;
            }
        }
        lgn[elementosGN]=gn;
        elementosGN+=1;
        return true;
    }
    
    public void imprimirVertices(){
        for(int v=0;v<this.elementosV;v++){
                System.out.println("Nodo incio: "+this.lv[v].getFn()+" Nodo final: "+ this.lv[v].getLn());
            }
    }
    
    public void mostrarGrafo(){
        for(int n=0;n<this.elementosGN;n++){
            GNode gnod=this.lgn[n];
            System.out.println("GNode: "+ gnod);
            for(int v=0;v<this.elementosV;v++){
                Vertice ver=this.lv[v];
                if(ver.getFn().equals(gnod)){
                    System.out.println("Se conecta a: "+ ver.getLn());
                }
            }
            System.out.println("---------");
        }
    }
    
    public void crearVertices(){
    //    System.out.println("si entro we");
        for(int i=0;i<this.elementosGN;i++){
            GNode nactual=this.lgn[i];
     //       System.out.println("si entro we");
            for(int j=0;j<this.elementosGN;j++){
                GNode ncomparacion=this.lgn[j];
                if(nactual.isHasDucto()/* && !nactual.equals(ncomparacion)*/){
                    Ducto dactual=nactual.getDucto();
                    if(ncomparacion.isHasDucto()){//ambos nodos son ductos
                        Ducto dcomp=ncomparacion.getDucto();
                        if(dactual.getFila()==(dcomp.getFila()-1)){//el ducto dactual esta encima de dcomp
                             if((dcomp.getColi()<=dactual.getColf() && dcomp.getColi()>=dactual.getColi()) || (dcomp.getColf()<=dactual.getColf() && dcomp.getColf()>=dactual.getColi())){
                                 this.addVertice(nactual, ncomparacion,"Abajo");
                             }
                        }else if((dactual.getColf()+1)==dcomp.getColi() && dactual.getFila()==dcomp.getFila()){
                            this.addVertice(nactual, ncomparacion, "Derecha");
                        }
                    }else{//primero nodo ducto y el segundo es cavidad
                        Cavidad cav=ncomparacion.getCavidad();
                        if((dactual.getFila()+1)==cav.getFilai()){ //el ducto esta arriba de la cavidad
                            if((dactual.getColi()<=cav.getColf() && dactual.getColi()>=cav.getColi()) || (dactual.getColf()<=cav.getColf() && dactual.getColf()>=cav.getColi())){
                                 this.addVertice(nactual, ncomparacion,"Abajo");
                             }
                        }else if(dactual.getFila()>=cav.getFilai() && dactual.getFila()<=cav.getFilaf()){ //el ducto esta al mismo nivel de la cavidad
                            if((dactual.getColf()+1)==cav.getColi()){
                                this.addVertice(nactual, ncomparacion, "Derecha");
                            }
                        }
                    }
                }else /*if(!nactual.equals(ncomparacion))*/{
                    Cavidad cavact=nactual.getCavidad();
                    if(ncomparacion.isHasDucto()){ //primero nodo cavidad y el segundo ducto
                        Ducto dcomp= ncomparacion.getDucto();
                        if((cavact.getFilaf()+1)==dcomp.getFila()){  // la cavidad esta encima del ducto
                            if((cavact.getColi()<=dcomp.getColi() && cavact.getColf()>=dcomp.getColi()) || (cavact.getColi()<=dcomp.getColf() && cavact.getColf()>=dcomp.getColf())){
                                this.addVertice(nactual, ncomparacion,"Abajo");
                            }
                        }else if(cavact.getFilai()<=dcomp.getFila() && cavact.getFilaf()>=dcomp.getFila()){ // la cavidad esta al mismo nivel que el ducto
                            if((cavact.getColf()+1)==dcomp.getColi()){
                                this.addVertice(nactual, ncomparacion,"Derecha");
                            }
                        }
                    }else{//ambos nodos son cavidad
                        Cavidad cavcomp=ncomparacion.getCavidad();
                        if((cavact.getFilaf()+1)==cavcomp.getFilai()){//la cavidad actual esta arriba que la comparacion
                                if((cavact.getColi()<=cavcomp.getColi() && cavact.getColf()>=cavcomp.getColi()) || (cavact.getColi()<=cavcomp.getColf() && cavact.getColf()>=cavcomp.getColf()) ){
                                    this.addVertice(nactual, ncomparacion,"Abajo");
                                }
                        }else if((cavact.getFilai()<=cavcomp.getFilai() && cavact.getFilaf()>=cavcomp.getFilai()) || (cavact.getFilai()<=cavcomp.getFilaf() && cavact.getFilaf()>=cavcomp.getFilaf())){ //la cavidad actual esta al mismo nivel que la comparacion 
                            if((cavact.getColf()+1)==cavcomp.getColi()){
                                this.addVertice(nactual, ncomparacion,"Derecha");
                            }
                        }
                    }
                }
                    
            }
        }
    }
    
    
    
    public void DibujarGrafo(Group root){
      //  System.out.println("se empieza a dibujar");
        for(int i=0;i<this.elementosGN;i++){
            GNode gn=this.lgn[i];
            if(gn.isHasCavidad()){
                Cavidad c=gn.getCavidad();
                Label lb=new Label();
           //     System.out.println(c.getColi());
                lb.setLayoutX(c.getColi()*14);
                lb.setLayoutY(c.getFilai()*17);
                StringBuilder sb=new StringBuilder();
                this.CrearStringMatriz(c.getColi(), c.getColf(), c.getFilai(), c.getFilaf(), sb);
                lb.setText(sb.toString());
                
                Rectangle rec=new Rectangle((c.getColf()-c.getColi()+1)*14,(c.getFilaf()-c.getFilai()+1)*17);
                rec.setLayoutX(c.getColi()*14);
                rec.setLayoutY(c.getFilai()*17);
                rec.setFill(Color.LIGHTYELLOW);
                rec.setStroke(Color.BLACK);
                rec.setStrokeType(StrokeType.INSIDE);
                root.getChildren().addAll(rec,lb);
                
            }else{
                Ducto d=gn.getDucto();
                Label lb=new Label();
                StringBuilder sb=new StringBuilder();
                this.CrearStringMatriz(d.getColi(), d.getColf(), d.getFila(), d.getFila(), sb);
                lb.setText(sb.toString());
                lb.setLayoutX(d.getColi()*14);
                lb.setLayoutY(d.getFila()*17);
                Rectangle rec=new Rectangle((d.getColf()-d.getColi()+1)*14,17);
                rec.setLayoutX(d.getColi()*14);
                rec.setLayoutY(d.getFila()*17);
                rec.setFill(Color.LIGHTGREY);
                rec.setStroke(Color.BLACK);
                rec.setStrokeType(StrokeType.INSIDE);
                root.getChildren().addAll(rec,lb);
            }
        }
    }
    
    public void CrearStringMatriz(int coli,int colf,int filai,int filaf, StringBuilder sb){
        for(int f=filai;f<=filaf;f++){
           // sb.append("[");
            for(int c=coli;c<=colf;c++){
                sb.append("[0]");
            }
            sb.append("\n");
        }
    }
    
    public void DibujarInterseccones(Group root){
        for(int i=0;i<this.elementosV/2;i++){
            Vertice v=this.lv[i];
            for(Interseccion in:v.getListaInt()){
                
                Coordenada inicio=in.getCoordNodoSalida();
                Coordenada fin=in.getCoordNodoLlegada();
             //   System.out.println(in);
                Line line=new Line(inicio.getColumna()*14+7,inicio.getFila()*17+8,fin.getColumna()*14+8,fin.getFila()*17+8);
          //      line.setFill(Color.YELLOW);
                line.setStroke(Color.DARKBLUE);
                root.getChildren().add(line);
            }
        }
        
    }
    
}
