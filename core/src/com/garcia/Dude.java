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


    public Dude(Location loc, WumpusWorld myWorld) {
        this.loc = loc;
        this.myWorld = myWorld;
        texture = new Texture("guy.png");
        myWorld.makeVisible(loc);
        stack.push(loc);
    }


    public void badAISolution() {
        if(!hasGold) {
            if (myWorld.world[loc.getRow()][loc.getCol()] == WumpusWorld.WEB) {
                stack.pop();
                loc = stack.peek();
                moveDown();
                if (movingUp) {
                    moveDown();
                    stack.push(loc);
                    moveRight();
                    stack.push(loc);
                }
                else {
                    moveUp();
                    stack.push(loc);
                    moveRight();
                    stack.push(loc);
                }
            } else if (myWorld.world[loc.getRow()][loc.getCol()] == WumpusWorld.WIND) {
                stack.pop();
                loc = stack.peek();
                moveDown();
                if (movingUp) {
                    moveDown();
                    stack.push(loc);
                    moveRight();
                    stack.push(loc);
                }
                else {
                    moveUp();
                    stack.push(loc);
                    moveRight();
                    stack.push(loc);
                }
            } else if (myWorld.world[loc.getRow()][loc.getCol()] == WumpusWorld.STINK) {
                stack.pop();
                loc = stack.peek();
                moveDown();
                if (movingUp) {
                    moveDown();
                    stack.push(loc);
                    moveRight();
                    stack.push(loc);
                }
                else {
                    moveUp();
                    stack.push(loc);
                    moveRight();
                    stack.push(loc);
                }
            }
            if (movingUp) {
                moveUp();
                stack.push(loc);
            } else {
                moveDown();
                stack.push(loc);

            }

            if (loc.getRow() == 0 || loc.getRow() == 9) {
                if (movingUp) {
                    moveUp();
                    stack.push(loc);
                } else {
                    moveDown();
                    stack.push(loc);

                }
                movingUp = !movingUp;
                moveRight();
                stack.push(loc);
            }
        }

        else {
            loc = stack.peek();
            stack.pop();
            }
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
        if (loc.getRow()+1 < myWorld.getNumRows()) {
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
    }
}
