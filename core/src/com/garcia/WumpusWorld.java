package com.garcia;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WumpusWorld {
    int world[][] = {
            {1,0,0,0,0,0,0,0,0,0},
            {2,0,0,0,0,0,0,0,0,0},
            {3,0,0,0,0,0,0,0,0,0},
            {4,0,0,0,0,0,0,0,0,0},
            {11,0,0,0,0,0,0,0,0,0},
            {12,0,0,0,0,0,0,0,0,0},
            {13,0,0,0,0,0,0,0,0,0},
            {14,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0}

    };
    boolean visible[][] = new boolean[10][10];

    private final int xoffset = 20, yoffset = 500;
    private final int tileWidth;
    private Texture groundTile, spiderTile, pitTile, wumpusTile, goldTile,
                    webTile, windTile, glitterTile, stinkTile, blackTile;

    public static final int GROUND = 0, SPIDER = 1, PIT = 2, WUMPUS = 3, GOLD = 4,
                            WEB = 11, WIND = 12, STINK = 13, GLITTER = 14;

    public WumpusWorld(){
        groundTile = new Texture("groundTile.png");
        spiderTile = new Texture("spiderTile.png");
        pitTile = new Texture("pitTile.png");
        wumpusTile = new Texture("wumpusTile.png");
        goldTile = new Texture("goldTile.png");
        webTile = new Texture("webTile.png");
        windTile = new Texture("windTile.png");
        glitterTile = new Texture("glitterTile.png");
        stinkTile = new Texture("stinkTile.png");
        blackTile = new Texture("blackTile.png");
        tileWidth = blackTile.getWidth();

    }

    public void draw(SpriteBatch spriteBatch) {
        for (int row = 0; row < world.length;row++) {
            for (int col = 0; col < world[0].length; col++) {
                if (world[row][col] == GROUND)
                    spriteBatch.draw(groundTile, xoffset+col*tileWidth, yoffset -row*tileWidth);
                else if ((world[row][col] == SPIDER))
                    spriteBatch.draw(spiderTile, xoffset+col * tileWidth, yoffset -row * tileWidth);
                else if ((world[row][col] == WUMPUS))
                    spriteBatch.draw(wumpusTile, xoffset+col * tileWidth, yoffset -row * tileWidth);
                else if ((world[row][col] == PIT))
                    spriteBatch.draw(pitTile, xoffset+col * tileWidth, yoffset -row * tileWidth);
                else if ((world[row][col] == WEB))
                    spriteBatch.draw(webTile, xoffset+col * tileWidth, yoffset -row * tileWidth);
                else if ((world[row][col] == STINK))
                    spriteBatch.draw(stinkTile, xoffset+col * tileWidth, yoffset -row * tileWidth);
                else if ((world[row][col] == WIND))
                    spriteBatch.draw(windTile, xoffset+col * tileWidth, yoffset -row * tileWidth);
                else if ((world[row][col] == GLITTER))
                    spriteBatch.draw(glitterTile, xoffset+col * tileWidth, yoffset -row * tileWidth);
                else if ((world[row][col] == GOLD))
                    spriteBatch.draw(goldTile, xoffset+col * tileWidth, yoffset -row * tileWidth);
            }//end inner for

        }//end outer for
    }//end method draw


    public Texture getGroundTile() {
        return groundTile;
    }

    public Texture getSpiderTile() {
        return spiderTile;
    }

    public Texture getPitTile() {
        return pitTile;
    }

    public Texture getWumpusTile() {
        return wumpusTile;
    }

    public Texture getGoldTile() {
        return goldTile;
    }
}//end class