package com.mcelrea.spacegame;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class SpaceShip {
    private float x;
    private float y;
    private static float COLLISION_RECT_WIDTH=40;
    private static float COLLISION_RECT_HEIGHT=40;
    private Rectangle collisionRect;
    private float speed = 10;

    public SpaceShip() {
        x = 300;
        y = 300;
        collisionRect = new Rectangle(x,
                y,
                COLLISION_RECT_WIDTH,
                COLLISION_RECT_HEIGHT);
    }

    public void moveUp() {
        y += speed; //move graphic up
        collisionRect.setY(y); //move collision rect up
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(collisionRect.x,
                collisionRect.y,
                collisionRect.width,
                collisionRect.height);
    }
}
