/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author LuisEduardo
 */
public class Pruebas extends Application {
    private String a;
    @Override
    public void start(Stage primaryStage) {
      /*  int filas=30;
        int columnas=50;
        int nivel=1;*/
        int filas=8;
        int columnas=8;
        int nivel=1;
        
        MatrizPrueba matriz = new MatrizPrueba();
        Matriz m=new Matriz(nivel,filas,columnas);
       // m.setMatriz(matriz.getMatrizEstructuraCircular());
        m.generarAleatoria();
        m.mostarm();
        ArrayList<Grafo> al;
        al=m.experimento();
        Grafo g=al.get(0);
        ArrayList<GNode> iniciales=g.Iniciales();
        ArrayList<GNode> finales=g.Finales();
        ArrayList<ArrayList<Vertice>> caminos=new ArrayList<>();
       // g.Imprimmirvertices();
      //  g.ImprimmirNodos();
    //    System.out.println("IMPORTANTEEEEEEEEE\n");
    //    System.out.println(iniciales.get(1).equals(iniciales.get(1)));
        ArrayList<ArrayList<Vertice>> res=new ArrayList<>();
        for(GNode in:iniciales){
            for(GNode fin:finales){
                System.out.println("PAR");
                for(ArrayList<Vertice> v:g.posiblesCaminos(in, fin)){
                    res.add(v);
                }
            }
        }
        System.out.println(res.size());
        double dist=g.CalcularDistancias(res);
        double tortuosidad=dist/m.getColumna();
        System.out.println("La tortuosidad es: "+tortuosidad);
        //g.posiblesCaminos(iniciales.get(0), finales.get(0), caminos);
       /* Circle c = new Circle (50, 100, 100);
        c.setLayoutX(30);
        c.setLayoutY(30);
        Label lb= new Label("[0000000]\n[000000000]");
        lb.setLayoutX(50);
        lb.setLayoutY(50);
        c.setFill(Color.AQUAMARINE);*/
      /*  Label lb=new Label("[0]");
        Label lb2=new Label("[0]\n");
        Label lb3=new Label("[0]\n[0]");
        lb2.setLayoutY(1*17);
        lb3.setLayoutY(2*17);*/
        
        Group root = new Group();
     //   root.getChildren().addAll(lb,lb2,lb3);
        al.get(0).DibujarGrafo(root);
        al.get(0).DibujarInterseccones(root);
       // root.getChildren().addAll(c,lb);
      //  root.getChildren().add(new Rectangle(150, 150, Color.DARKBLUE));
        Scene scene = new Scene(root, 800, 500);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    
}
