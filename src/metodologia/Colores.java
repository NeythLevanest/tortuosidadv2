/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodologia;

import java.util.Random;
import java.awt.Color;
public class Colores{


public static Color generarColor(){
Random rand = new Random();
int r = rand.nextInt(256);
int g = rand.nextInt(256);
int b = rand.nextInt(256);
Color color = new Color(r, g, b);
return color ;
}

}
