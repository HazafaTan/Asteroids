/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ics4uculminating1;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author hazaf
 */
public class Ship extends Polygon {
    Ship ship; //create instance of ship class
    Point pull = new Point(0, 0);//intialize the pull at 0,0
    boolean isDead = false; //initlaize isDead boolean to determine if player is still alive
    static Point[] p = {new Point(0, 0), new Point(20, 10), new Point(0, 20) , new Point(10,10)};// storing points for ship in array
    public Ship() {
        super(p, new Point(250, 250), 0);//initalize the ship at point 250,250

    }

    public void paint(Graphics g) {
        if(!isDead){//if isDead is false
        g.setColor(Color.RED);//set colot to red
        int[] x = new int[this.getPoints().length];//set x array to the length of the Ship points
        int[] y = new int[this.getPoints().length];//set y array to the length of ship points
        for (int i = 0; i < this.getPoints().length; i++) { //for loop that runs for length of the getPoints
            x[i] = (int) this.getPoints()[i].x; //set  x value at i into x array
            y[i] = (int) this.getPoints()[i].y;//set y value at i into y array

        }
        g.fillPolygon(x, y, p.length); //draw the polygon with the x and y along with the amount of sides (length of the points array)
        }
        else {//else if the player is dead
            g.setFont(new Font("Calibri", Font.PLAIN,30)); //set font and size
            g.setColor(Color.RED); //set color to red
            g.drawString("GOOD GAME <3!!!", 300, 250); //draw string that is for the end game
            g.drawString("Final SCORE: ", 490,50); //draw the final score 
        }
    }

    public void accelerate(double acceleration) {
        pull.x += (acceleration * Math.cos(Math.toRadians(rotation))); // multiplay rotatation with acceleration and add to pull at x
        pull.y += (acceleration * Math.sin(Math.toRadians(rotation)));// multiplay rotatation with acceleration and add to pull at Y
        if (pull.x > 3) {//if pull at x is greater than 3
            pull.x = 3; //set to three
        }
        if (pull.x < -3) { //if pull at x is less than -3
            pull.x = -3; //set to negative 3
        }
        if (pull.y > 3) { //if pull at Y is greater than 3
            pull.y = 3; //set to three
        }
        if (pull.y < -3) { //if pull at Y is less than -3
            pull.y = -3;//set to negative 3
        }

    }

    public void move() {
        position.x += pull.x; //add position with pull at x
        position.y += pull.y; //add position with pull at Y
        if (pull.x > 0.01) { //set
            pull.x -= 0.1;
        }
        if (pull.x < -0.01) {
            pull.x  += 0.1;
        }
        if (pull.y > 0.01) {
            pull.y -= 0.1;
        }if (pull.y < -0.01) {
            pull.y += 0.1;
        }
        if (position.x > 800){//if the position at x is greater than 800
            position.x =0; //set positon to zero
        }if (position.x<0){ // if position at x is less than zero 
            position.x= 800; //set position to 800
        }if (position.y >600){ // if position at Y is greater than 600 
            position.y =0; //set y position to 0
        }if (position.y <0){ //if position at y is less than 0
            position.y=600;  // set position to 600
        }
        

    }
}
