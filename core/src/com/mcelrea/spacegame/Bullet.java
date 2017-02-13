package com.mcelrea.spacegame;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

public class Bullet {
    private float x;
    private float y;
    private static final float COLLISION_RADIUS=5f;
    private Circle collision_circle;
    private float xvel; //x-velocity
    private float yvel; //y-velocity

    public Bullet(float x, float y) {
        this.x = x;
        this.y = y;
        collision_circle = new Circle(x,
                y,
                COLLISION_RADIUS);
    }

    public void setVelocity(float xvel, float yvel) {
        this.xvel = xvel;
        this.yvel = yvel;
    }

    public void update(float delta) {
        x += xvel * delta; //move graphic
        y += yvel * delta; //move graphic
        collision_circle.setPosition(x,y); //move collision circle
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.circle(collision_circle.x,
                collision_circle.y,
                collision_circle.radius);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getDiameter() {
        return COLLISION_RADIUS*2;
    }
}
