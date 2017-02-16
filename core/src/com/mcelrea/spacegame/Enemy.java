package com.mcelrea.spacegame;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.Array;

public class Enemy {
    private float x;
    private float y;
    private static final float COLLISION_RADIUS=10f;
    private Circle collisionCircle;

    public Enemy(float x, float y) {
        this.x = x;
        this.y = y;
        collisionCircle = new Circle(x,
                y,
                COLLISION_RADIUS);
    }

    public void act(float delta, Array<Bullet> bullets) {
        x -= delta;
        y = (float) (Math.pow(x,2)/300);
        updatePosition(x,y);
        System.out.println(x + ", " + y);
    }

    private void updatePosition(float x, float y) {
        collisionCircle.setPosition(x,y);
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.circle(collisionCircle.x,
                collisionCircle.y,
                collisionCircle.radius);
    }
}
