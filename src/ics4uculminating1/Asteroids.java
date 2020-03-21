/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ics4uculminating1;

/**
 *
 * @author 202432
 */
/*
CLASS: Asteroids
DESCRIPTION: Extending Game, Asteroids is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.
 */
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.*;

class Asteroids extends Game implements KeyListener {

    Bullets bullet;//initialize Bullets Class
    Ship ship;//initialize of ship clkass
    Asteroid asteroid; //initialize asteriod class
    boolean pressUp = false; // initialize the variable for up button
    boolean pressDown = false; // initialize the variable for down button
    boolean pressLeft = false; // initialize the variable for left button
    boolean pressRight = false; // initialize the variable for right button
    boolean pressShoot = false; // initialize the variable for shoot button
    ArrayList<Asteroid> asteroidList = new ArrayList<Asteroid>(); //ArrayList that will hold asteriods that are created 
    ArrayList<Bullets> BulletList = new ArrayList<Bullets>(); // arrayList that will hold bullets
    int intCounter = 0; //Counter that will be used for the spawning of asteriods
    int score = 0; //int variable that will store the score

    public Asteroids() {
        super("Asteroids!", 800, 600);//set in super class constructor the name, followed by dimensions
        ship = new Ship();//create an  instance of ship class
        asteroid = new Asteroid(); // create an instance of asteriod class
        addKeyListener(this);//add the keylistener method
    }

    public void paint(Graphics brush) {
        brush.setColor(Color.black);//set color to black
        brush.fillRect(0, 0, width, height);//set the dimensions
        brush.setColor(Color.WHITE);//set the color to white
        brush.setFont(new Font("Calibri", Font.PLAIN, 30));//set the font and size
        brush.setColor(Color.RED);// set the color for text
        brush.drawString("Click to Start", 300, 250);//draw the string onto screen
        brush.drawString("MOVEMENT: ARROW KEYS", 300, 275);// draw the string to screen
        brush.drawString("SHOOT: Q", 300, 300);// draw the string to screen

        if (KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner().toString().equals("ics4uculminating1.Asteroids[canvas0,3,32,794x565]")) {    // check if the screen has been clicked
            int intValue = 150; // int variable that will be used to spawn asteriods
            int intBulletCounter = 0;// initialize int variable to count bullets
            brush.setColor(Color.black); //set color to black 
            brush.fillRect(0, 0, width, height); //fill rectangle with dimensions
            brush.setColor(Color.WHITE);//setcolor to white
            ship.paint(brush);//paint the ship
            if (pressUp) { //if pressup is true
                ship.accelerate(1);//accelerate at the value by 1
            }
            if (pressDown) { //if the pressdown is true
                ship.accelerate(-1); //accelerate by -1
            }
            if (pressLeft) { //if pressleft is true
                ship.rotate(-2);//rotate by -2
            }
            if (pressRight) {//pressright is true
                ship.rotate(2); //rotate by 2
            }
            if (pressShoot) {//if pressshoot is true
                BulletList.add(new Bullets(ship.position, ship.rotation));//add a new bullet to BulletArratList with the position of the ship and its rotation
            }
            if (intCounter % 100 == 0) {//if the counter % 100 is zero
                BulletList.clear(); //clear array List so bullets would dissapear on screen
            }
            for (int i = 0; i < BulletList.size(); i++) {  //for loop that will run the length of the BulletList ArrayList
                BulletList.get(i).paint(brush);//paint the bullet at i in the BulletList ArrayList
                intBulletCounter++;//increase the Bullet Counter by 1
                BulletList.get(i).move(); // use the move method in the Bullet class at the value of i in the BulletList ArrayList
                if (intBulletCounter == 3) {//if the counter is 3
                    break;//break out of the statement
                }
            }
            ship.move();//move the ship using the move method in the ship class
            if (intCounter % intValue == 0) {//if intcounter %intvalue is zero
                asteroidList.add(new Asteroid());//add another asteriod into arrayList
            }

            for (int i = 0; i < asteroidList.size() && asteroidList.size() > 0; i++) {//for loop that runs for the length of the Asteriod ArrayList
                for (int j = 0; j < BulletList.size() && BulletList.size() > 0; j++) {//for loop that runs for the length of the Bullet ArrayList
                    if (BulletList.size() > j && asteroidList.size() > i && asteroidList.get(i) != null && BulletList.get(j) != null && asteroidList.get(i).contains(BulletList.get(j).position)) { //using the contains method check if bullet hits the asteriod
                        asteroidList.remove(i);//remove the asteriod at i
                        score += 10;//increase score by 10
                    }
                }
                if (asteroidList.size() > i && asteroidList.get(i) != null) {//check if asteriod is present
                    Point[] pArrayShip = ship.getPoints();//gets all the values of the ship and puts into point class
                    for (int j = 0; j < pArrayShip.length; j++) {//for loop that runs for the length of array
                        if (asteroidList.get(i).contains(pArrayShip[j])) {//if asteriod contains ship point
                            ship.isDead = true; //set boolean in ship class (isDead) to true
                        }
                    }
                    asteroidList.get(i).paint(brush);//paint asteriod at i
                    asteroidList.get(i).RandomMove();//call randommove method in asteroid class to move asteriod around
                }
            }
            brush.drawString(score + "", 700, 50);//draw score in corner
            intValue -= 150;//decrease int valueby 150 
            intCounter++; //increase intCounter
            repaint(); //repaint
        }
        repaint(); //repaint
    }

    public static void main(String[] args) {
        new Asteroids(); //ceate instance of Asteriods class
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent e) { //Using keyListener check if each key has been pressed
        int id = e.getKeyCode();// store keycode in int variable
        if (id == KeyEvent.VK_RIGHT) { // if value is right 
            pressRight = true; //set to true
        } else if (id == KeyEvent.VK_LEFT) { // if value is Left 
            pressLeft = true; //set to true
        } else if (id == KeyEvent.VK_UP) { // if value is Up
            pressUp = true; //set to true
        } else if (id == KeyEvent.VK_DOWN) { // if value is Down 
            pressDown = true; //set to true
        } else if (id == KeyEvent.VK_Q) { // if value is Q 
            pressShoot = true; //set to true
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { //Using keyListener check if each key has been released
        int id = e.getKeyCode();// store keycode in int variable
        if (id == KeyEvent.VK_RIGHT) { // if value is right 
            pressRight = false; //set to false
        } else if (id == KeyEvent.VK_LEFT) { // if value is Left 
            pressLeft = false;  //set to false
        } else if (id == KeyEvent.VK_UP) {  // if value is Up
            pressUp = false;//set to false
        } else if (id == KeyEvent.VK_DOWN) { // if value is Down 
            pressDown = false;//set to false
        } else if (id == KeyEvent.VK_Q) {// if value is Q 
            pressShoot = false;//set to false
        }
    }

}
