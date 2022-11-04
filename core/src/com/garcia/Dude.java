package com.garcia;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class Dude {
    private Location loc;
    private WumpusWorld myWorld;
    private Texture texture;

    private boolean hasGold = false;

    private int totalSteps = 0;

    private boolean killWumpus = false;

    private Stack<Location> stack = new Stack<>();

    private boolean movingUp = true;

    private boolean movingRight = true;


    public Dude(Location loc, WumpusWorld myWorld) {
        this.loc = loc;
        this.myWorld = myWorld;
        texture = new Texture("guy.png");
        myWorld.makeVisible(loc);
        stack.push(loc);
        System.out.println("Starting loc = " + loc);
    }


    public void badAISolution() {
        //changes his movement from right to left
        if (totalSteps >= 90 && loc.getRow() == 9)
            movingRight = false;

        if(!hasGold) {
            //main movement method
            if (movingUp) {
                moveUp();
                stack.push(loc);
            } else {
                moveDown();
                stack.push(loc);

            }
            //if he steps on a web
            if (myWorld.world[loc.getRow()][loc.getCol()] == WumpusWorld.WEB) {
                stack.pop();
                loc = stack.peek();

                //back up
                if (movingUp) {
                    moveDown();

                }
                else {
                    moveUp();

                }

                if (!movingUp && !movingRight) {
                    moveUp();
                    stack.push(loc);
                    moveLeft();
                    stack.push(loc);
                }
                else if (!movingUp && movingRight){
                    moveUp();
                    stack.push(loc);
                    moveRight();
                    stack.push(loc);
                }
                else if (movingUp && !movingRight){
                    moveDown();
                    stack.push(loc);
                    moveLeft();
                    stack.push(loc);
                }
                else {
                    moveDown();
                    stack.push(loc);
                    moveRight();
                    stack.push(loc);
                }
            }
            //if he steps on a wind
            else if (myWorld.world[loc.getRow()][loc.getCol()] == WumpusWorld.WIND) {
                stack.pop();
                loc = stack.peek();
                //back up
                if (movingUp) {
                    moveDown();
                    stack.push(loc);
                }
                else {
                    moveUp();
                    stack.push(loc);
                }

                if (!movingUp && !movingRight) {
                    moveUp();
                    stack.push(loc);
                    moveLeft();
                    stack.push(loc);
                }
                else if (!movingUp && movingRight){
                    moveUp();
                    stack.push(loc);
                    moveRight();
                    stack.push(loc);
                }
                else if (movingUp && !movingRight){
                    moveDown();
                    stack.push(loc);
                    moveLeft();
                    stack.push(loc);
                }
                else {
                    moveDown();
                    stack.push(loc);
                    moveRight();
                    stack.push(loc);
                }
                //if he steps on a stink
            } else if (myWorld.world[loc.getRow()][loc.getCol()] == WumpusWorld.STINK) {
                stack.pop();
                loc = stack.peek();
                //back up
                if (movingUp) {
                    moveDown();
                    stack.push(loc);
                }
                else {
                    moveUp();
                    stack.push(loc);
                }

                if (!movingUp && !movingRight) {
                    moveUp();
                    stack.push(loc);
                    moveLeft();
                    stack.push(loc);
                }
                else if (!movingUp && movingRight){
                    moveUp();
                    stack.push(loc);
                    moveRight();
                    stack.push(loc);
                }
                else if (movingUp && !movingRight){
                    moveDown();
                    stack.push(loc);
                    moveLeft();
                    stack.push(loc);
                }
                else {
                    moveDown();
                    stack.push(loc);
                    moveRight();
                    stack.push(loc);
                }
            }

            //if he reaches wall
            if (loc.getRow() == 0 || loc.getRow() == 9) {
                if (movingUp) {
                    moveUp();
                    stack.push(loc);
                    moveUp();
                    stack.push(loc);


                } else {
                    moveDown();
                    stack.push(loc);
                    moveDown();
                    stack.push(loc);

                }




                //change his direction
                movingUp = !movingUp;
                if (movingRight) {
                    moveRight();
                    stack.push(loc);
                }
                else{
                    moveLeft();
                    stack.push(loc);
            }
            }

        }
        else {
            loc = stack.peek();
            stack.pop();
            }
        System.out.print(stack.size() + " ");
        printNewPositionStack();
    }





    //this method makes one step
    public void step()  {
        badAISolution();
    }





    public boolean killWumpus() {
        return killWumpus;
    }

    public void moveRight() {
        if (loc.getCol()+1 < 10) {
            loc.setCol(loc.getCol()+1);
            myWorld.makeVisible(loc);
            totalSteps++;
        }
    }

    public void moveLeft() {
        if (loc.getCol() -1 >= 0) {
            loc.setCol(loc.getCol() -1);
            myWorld.makeVisible(loc);
            totalSteps++;
        }
    }

    public void moveUp() {
        if (loc.getRow() -1 >= 0) {
            loc.setRow(loc.getRow()-1);
            myWorld.makeVisible(loc);
            totalSteps++;
        }
    }

    public void moveDown() {
        if (loc.getRow()+1 < 10) {
            loc.setRow(loc.getRow()+1);
            myWorld.makeVisible(loc);
            totalSteps++;
        }
    }

    public int getTotalSteps() {
        return totalSteps;
    }

    public boolean hasGold() {
        return hasGold;
    }

    public void setHasGold(boolean hasGold) {
        this.hasGold = hasGold;
    }

    public Location getLoc() {
        return loc;
    }

    public void draw(SpriteBatch spriteBatch) {
        Point myPoint = myWorld.convertRowColToCoords(loc);
        spriteBatch.draw(texture, (int)myPoint.getX(), (int)myPoint.getY());
    }

    public void reset(Location loc) {
        this.loc = loc;
        myWorld.makeVisible(loc);
        totalSteps = 0;
        killWumpus = false;
        stack.removeAllElements();
        hasGold = false;
    }

    public void printNewPositionStack() {
        System.out.println(stack.peek());
    }
}
