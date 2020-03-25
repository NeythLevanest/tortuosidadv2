/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodologia;

import Estructuras.MatrizPrueba;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Neythan León
 */
public class Metodologia extends JFrame{
     Graphics graficos;
     Graphics grafico;
     Panel pane;
     JButton boton;
    class Panel extends JPanel{
        @Override
        public void paint(Graphics g){
            grafico=g;
            super.paintComponent(grafico);
            MatrizPrueba matriz = new MatrizPrueba(grafico);
            matriz.paint();
            
            graficos = getGraphics();
        }
         
    }
    public Metodologia(){
        super("Torutosidad");
        pane = new Panel();
        pane.repaint();
        add(pane);
        
        boton = new JButton("Calcular");
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("Calculando");
                Ruta ruta = new Ruta(graficos);
                ruta.start();
            }
        });
        add(boton,"South");
        setSize(2060,1240);
        setLocation(300,200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
   
    
    public static void main(String[] args) {
        
        Metodologia metodologia = new Metodologia();
    }

}