/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ics4uculminating1;

import java.awt.Graphics;

/**
 *
 * @author hazafa
 */
public class Bullets {
    Point position; //initalize position
    double rotation;//initalize rotation

    public Bullets(Point position, double rotation) {
        this.position = new Point(position.x, position.y); // set position to instance variable
        this.rotation = rotation; // set rotateion to instance variable
    }

    public void paint(Graphics g) {
        g.drawOval((int) position.x, (int) position.y, 4, 4); //draw oval wth position at x and y 
    }

    public void move() {
        position.x += 5*Math.cos(Math.toRadians(rotation)); //multiply 5 wo the cos of rotation
        position.y += 5*Math.sin(Math.toRadians(rotation)); //multiply 5 wo the sin of rotation
        if (position.x > 800) {//if the position at x is greater than 800
            position.x = 0; //set positon to zero
        }
        if (position.x < 0) { // if position at x is less than zero 
            position.x = 800; //set position to 800
        }
        if (position.y > 600) { // if position at Y is greater than 600 
            position.y = 0; //set y position to 0
        }
        if (position.y < 0) { //if position at y is less than 0
            position.y = 600;  // set position to 600
        }

    }
}
