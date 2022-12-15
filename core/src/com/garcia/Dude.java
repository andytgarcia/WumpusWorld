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

    private boolean firstMove = true;

    private boolean backtrack = false;


    public Dude(Location loc, WumpusWorld myWorld) {
        this.loc = loc;
        this.myWorld = myWorld;
        texture = new Texture("guy.png");
        myWorld.makeVisible(loc);
        stack.push(new Location(loc.getRow(), loc.getCol()));
        System.out.println("Starting loc = " + loc);
    }


    public void badAISolution() {
        if (!hasGold) {
            //just to get atWall working
            if (firstMove) {
                moveUp();
                printNewPositionStack();
                firstMove = false;
            }
            else {
                if (!atWall()) {
                    if (movingUp)
                        moveUp();
                    else
                        moveDown();
                    if (steppedOnSensorTile(loc)){
                        getAway();
                    }
                    System.out.println(stack.peek());
                    return;

                }
                if (atWall())
                    turnAround();
                if (totalSteps >= 90 && loc.getCol() == 9)
                    movingRight = false;

            }
        }
        else {
            loc.setRow(stack.peek().getRow());
            loc.setCol(stack.peek().getCol());
            stack.pop();
            totalSteps++;


        }








        /*//changes his movement from right to left
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
                myWorld.

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
            //if he steps on a wind
            else if (myWorld.world[loc.getRow()][loc.getCol()] == WumpusWorld.WIND) {
                stack.pop();
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

         */
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
            if (!backtrack)
                stack.push(new Location(loc.getRow(), loc.getCol()));
            myWorld.makeVisible(loc);
            totalSteps++;
            System.out.println("Agent at " + loc);
        }
    }

    public void moveLeft() {
        if (loc.getCol() -1 >= 0) {
            loc.setCol(loc.getCol() -1);
            myWorld.makeVisible(loc);
            if (!backtrack)
                stack.push(new Location(loc.getRow(), loc.getCol()));
            totalSteps++;
            System.out.println("Agent at " + loc);
        }
    }

    public void moveUp() {
        if (loc.getRow() -1 >= 0) {
            loc.setRow(loc.getRow()-1);
            if (!backtrack)
                stack.push(new Location(loc.getRow(), loc.getCol()));
            myWorld.makeVisible(loc);
            totalSteps++;
            System.out.println("Agent at " + loc);
        }
    }

    public void moveDown() {
        if (loc.getRow()+1 < 10) {
            loc.setRow(loc.getRow()+1);
            if (!backtrack)
                stack.push(new Location(loc.getRow(), loc.getCol()));
            myWorld.makeVisible(loc);
            totalSteps++;
            System.out.println("Agent at " + loc);
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
        firstMove = true;
    }

    public void printNewPositionStack() {
        System.out.println(stack.peek());
    }

    public boolean atWall() {
        if (loc.getRow() == 0 || loc.getRow() ==9) {
            System.out.println("At wall");
            return true;
        }
        return false;
    }
    public void turnAround() {
        if (movingRight) {
            moveRight();
        }
        else {
            moveLeft();

        }

        if (movingUp) {
            moveDown();

        }
        else {
            moveUp();

        }
        movingUp = !movingUp;
    }

    public boolean steppedOnSensorTile(Location loc) {
        if (myWorld.world[loc.getRow()][loc.getCol()] == WumpusWorld.WEB || myWorld.world[loc.getRow()][loc.getCol()] == WumpusWorld.WIND || myWorld.world[loc.getRow()][loc.getCol()] == WumpusWorld.STINK) {
            System.out.println("Stepped on Enemy Tile!");
            return true;
        }
        return false;
    }
    public void getAway() {
        if (movingUp) {
            moveDown();

        } else {
            moveUp();

        }
        if (movingRight) {
            moveRight();

        } else {
            moveLeft();

        }
        if (steppedOnSensorTile(loc)) {
            getAway();
        }
    }

    public void printStack() {
        for (int i = 0; i < stack.size(); i++) {
            System.out.print(stack.get(i) + " ");
        }
    }

}
