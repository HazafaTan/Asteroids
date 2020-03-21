/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ics4uculminating1;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author hazaf
 */
public class Asteroid extends Polygon {
    Asteroid asteroid; // initialize an asteriod
        int randX; //iniitalize random x value
        int randY; // initalize random y value
        static Point[] p = {new Point(65, 20), new Point(40, -20), new Point(30, 20), new Point(70, 40), new Point (60,50), new Point (-40,60),new Point (-50,20)}; // storing points for asteriod in array
            
    public Asteroid() {
        super(p, new Point(Math.random() * 800, Math.random() * 600), 0);//set values for super constructer with random spawn location for asteriod
        int max = 1;  //create an max
        int min = -1; //create an min
        int range = max - min + 1;  // create a range  
         this.randX = (int)(Math.random() * range) + min;// set rand x to random value 
         if (randX ==0){//if value is zero
             this.randX =1; //set to one
         }
         this.randY= (int)(Math.random() * range) + min; // set randy to random value
         if (this.randY==0){ // if randy is 0 
             this.randY =1;// set to 1
         }
    
    }
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);//set color to white
        int[] x = new int[this.getPoints().length];//set x array to the length of the asteriod points
        int[] y = new int[this.getPoints().length]; //set y array to the length of asteriod points
            for (int i = 0; i < this.getPoints().length; i++) { //for loop that runs for length of the getPoints
            x[i] = (int) this.getPoints()[i].x; //set  x value at i into x array
            y[i] = (int) this.getPoints()[i].y; //set y value at i into y array 

        }
        g.drawPolygon(x, y, p.length); //draw the polygon with the x and y along with the amount of sides (length of the points array)

    }
    public void RandomMove(){
                Point pull = new Point(randX,randY); //initalize and set pull to the randomx and randomY values
        position.x += pull.x;//set the position at X to be added to the pull at X
        position.y += pull.y;//set the position at Y to be added to the pull at Y
        if (position.x > 800){//if the position at x is greater than 800
            position.x =0; //set positon to zero
        }if (position.x<0){ // if position at x is less than zero 
            position.x= 800; //set position to 800
        }if (position.y >600){ // if position at Y is greater than 600 
            position.y =0; //set y position to 0
        }if (position.y <0){ //if position at y is less than 0
            position.y=600; // set position to 600
        }
}
}
    
