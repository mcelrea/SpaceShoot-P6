package com.mcelrea.spacegame;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;

public class Enemy {
    protected float x;
    protected float y;
    private static final float COLLISION_RADIUS=10f;
    protected Circle collisionCircle;
    protected float speed = 100f;
    protected static long shootDelay = 700;//1000 = 1 second
    protected long lastShotTimeStamp;

    public Enemy(float x, float y) {
        this.x = x;
        this.y = y;
        collisionCircle = new Circle(x,
                y,
                COLLISION_RADIUS);
        lastShotTimeStamp = System.currentTimeMillis();
    }

    public void act(float delta, Array<Bullet> bullets) {
        x -= delta * speed;
        y = (float) (Math.pow(x,2)/300);
        updatePosition(x,y);
        if(lastShotTimeStamp + shootDelay < System.currentTimeMillis()) {
            Bullet b = new Bullet(x,y);
            b.setVelocity(0,-800);
            bullets.add(b);
            lastShotTimeStamp = System.currentTimeMillis();
        }
    }

    public boolean isHit(Bullet b) {
        return Intersector.overlaps(collisionCircle,
                b.getCollision_circle());
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
