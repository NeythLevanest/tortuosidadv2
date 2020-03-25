/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import javafx.scene.layout.HBox;

/**
 *
 * @author LuisEduardo
 */
public class Arbol {
    private NodeD raiz[];
    private int traiz;
    private int elementos;
    private int sizeM;
    
    public Arbol(int size){
        raiz=new NodeD[size];
        traiz=0;
        elementos=0;
        sizeM=size;
    }
    
    private boolean isEmpty(){
        return elementos==0;
    }
    
    
    public void agregarRaices(ArrayList<Ducto> ld){
        
        ArrayList<Ducto> ldt=new ArrayList<>();
        PriorityQueue<Ducto> pqdm= new PriorityQueue<>((d1,d2)->d1.getFila()-d2.getFila());
        for(Ducto dt:ld){
            pqdm.offer(dt);
        }
        
        while(!pqdm.isEmpty()){
            ldt.add(pqdm.poll());
        }
        
        int i=0;
        for(Ducto d: ldt){
            NodeD nd=new NodeD(this.sizeM);
            nd.setD(d);
            this.raiz[i]=nd;
            this.traiz+=1;
            this.elementos+=1;
            i+=1;
        }
    }
    
    public boolean verificarRaiz(NodeD nd){
        for(int i=0;i<this.traiz;i++){
            if(this.raiz[i].getD().equals(nd.getD())){
                return true;
            }
        }
        
        return false;
    }
    
    public void agregarElemento(Ducto d){
        NodeD nn=new NodeD(this.sizeM);
        nn.setD(d);
        if(!this.verificarRaiz(nn)){
            for(int i=0;i<traiz;i++){
                this.agregarElemento(this.raiz[i],nn);
            }
        
        }
        
    }
    
    private void agregarElemento(NodeD padre,NodeD n){
        Ducto dp= padre.getD();
        Ducto dn= n.getD();
        boolean con1=dp.getColi()<=dn.getColi() && dp.getColf()>=dn.getColf();
        boolean con2=dp.getColi()>=dn.getColi() && dp.getColf()<=dn.getColf();
        boolean con3=dp.getColi()>=dn.getColi() && dp.getColi()<=dn.getColf();
        boolean con4=dp.getColf()>=dn.getColi() && dp.getColf()<=dn.getColf();
        
        if(padre.verificarHijo(dn) ){
          //  System.out.println("momazo p");
        }else if((dp.getFila()+1)==dn.getFila() && ( con1 || con2 || con3 ||con4)){
            padre.agregarHijo(n);
            this.elementos+=1;
        }else{
           for(int i=0;i<padre.getCanti();i++){
               agregarElemento(padre.getHijos()[i],n);
           }
        }
    }
    
    
    public String toString(){
        StringBuilder sb=new StringBuilder();
        String s= "cantidad de nodos en la raiz: "+this.traiz+"\n";
        sb.append(s);
        for(int i=0;i<this.traiz;i++){
            StringBuilder(this.raiz[i],sb);
        }
        return sb.toString();
    }
    
    private void StringBuilder(NodeD n,StringBuilder sb){
        sb.append(n.toString());
        sb.append("\n");
        if(n.getHijos().length!=0){
            for(int i=0;i<n.getProxh();i++){
                StringBuilder(n.getHijos()[i],sb);
            }
        }
        
    }
    
    
    /*
    
    */
    
    public List<Cavidad> identificarCavidad(){
        int ci=0;
        int cf=0;
        int fi=0;
        int ff=0;
        int c0=0;
        Grafo gf= new Grafo(this.elementos);
        for(int i=0;i<this.traiz;i++){
            c0=this.raiz[i].getD().getC0();
            fi=this.raiz[i].getD().getFila();
            this.identificarCavidad(this.raiz[i], gf, ci, cf, fi, ff, c0);
        }
        return null;
    }
    
    private GNode identificarCavidad(NodeD n,Grafo gf ,int ci,int cf, int fi, int ff, int c0){
        Ducto dp=n.getD();
        int cai=ci;
        int caf=cf;
        int fai=fi;
        int faf=ff;
        int ca0=c0;
        GNode gr=null;
        if(n.getHijos().length!=0){
            for(int i=0;i<n.getProxh();i++){
                NodeD nh=n.getHijos()[i];
                Ducto dh=nh.getD();
                ci=cai;
                cf=caf;
                fi=fai;
                ff=faf;
                c0=ca0; 
                boolean con1=(dp.getColi()<=dh.getColi()) && dp.getColf()>=dh.getColf();

                boolean con2=dp.getColi()>=dh.getColi() && dp.getColf()<=dh.getColf();

                boolean con3=dp.getColi()>=dh.getColi() && dp.getColi()<=dh.getColf();

                boolean con4=dp.getColf()>=dh.getColi() && dp.getColf()<=dh.getColf();

                if(con1){
                    int nvalorpos=(dh.getColf()-dh.getColi()+1)*(dh.getFila()-fi+1);
                    GNode gporsiacaso=null;
                    if(dh.getColi()<ci ||dh.getColf()>cf){
                        gporsiacaso=this.identificarCavidad(n, gf, dh.getColi(), dh.getColf(), dh.getFila(), 0, 0);
                    }
                    
                    if(c0<nvalorpos){
                        ci=dh.getColi();
                        cf=dh.getColf();
                        ff=dh.getFila();
                        c0=nvalorpos;
                        gr=this.identificarCavidad(nh, gf, ci, cf, fi, ff, c0);
                    }else{
                        Cavidad cv=new Cavidad(dh.getNivel(),fi,ff,ci,cf,c0);
                        GNode gn=this.identificarCavidad(nh, gf, 0, 0, dh.getFila(), 0, 0);
                        GNode gnp= new GNode(cv);
                        gf.addGNode(gn);
                        gf.addGNode(gnp);
                        return gnp;
                    }
                    
                    
                }else if(con2){
                    int nvalorpos=0;
                        nvalorpos=(dh.getColf()-dh.getColi()+1)*2;
                        
                        
                }else if(con3){
                    
                }else if(con4){
                    
                }
                
            }
        }else{
            if(c0!=0){
                Cavidad c=new Cavidad(dp.getNivel(),fi,ff,ci,cf,c0);
                GNode ngn=new GNode(c);
                return ngn;
            }else{
                GNode ngn=new GNode(dp);
                return ngn;
            }
        }
        return gr;
    }
    
}
