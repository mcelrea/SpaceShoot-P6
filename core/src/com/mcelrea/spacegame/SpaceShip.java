package com.mcelrea.spacegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class SpaceShip {
    private float x;
    private float y;
    private static float COLLISION_RECT_WIDTH=30;
    private static float COLLISION_RECT_HEIGHT=30;
    private Rectangle collisionRect;
    private float speed = 6;
    private static final int SINGLE=1, DOUBLE=2;
    private int currentWeapon = DOUBLE;
    private long shootDelay = 600; //1000 = 1 second
    private long lastShot;
    private TextureRegion middleImage;
    private TextureRegion leftImage;
    private TextureRegion rightImage;
    public static final int LEFT = 1, MIDDLE = 2, RIGHT = 3;
    private int dir = MIDDLE;
    ParticleEffect engine1;
    ParticleEffect engine2;

    public SpaceShip() {
        lastShot = System.currentTimeMillis();
        x = 300;
        y = 300;
        collisionRect = new Rectangle(x,
                y,
                COLLISION_RECT_WIDTH,
                COLLISION_RECT_HEIGHT);
        Texture spriteSheet = new Texture("spaceSpriteSheet.png");
        TextureRegion[] regions = TextureRegion.split(spriteSheet,39,37)[0];
        leftImage = regions[0];
        middleImage = regions[1];
        rightImage = regions[2];
        engine1 = new ParticleEffect();
        engine1.load(Gdx.files.internal("engine.effect"),
                Gdx.files.internal(""));
        engine1.getEmitters().first().setPosition(x,y);
        engine1.start();

        engine2 = new ParticleEffect();
        engine2.load(Gdx.files.internal("engine.effect"),
                Gdx.files.internal(""));
        engine2.getEmitters().first().setPosition(x,y);
        engine2.start();
    }

    public void update(float delta) {
        engine1.update(delta);
        engine2.update(delta);
    }

    public void moveUp() {
        y += speed; //move graphic up
        collisionRect.setY(y); //move collision rect up
    }

    public void moveDown() {
        y -= speed; //move graphic up
        collisionRect.setY(y); //move collision rect up
    }

    public void moveLeft() {
        x -= speed; //move graphic up
        collisionRect.setX(x); //move collision rect up
    }

    public void moveRight() {
        x += speed; //move graphic up
        collisionRect.setX(x); //move collision rect up
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(collisionRect.x,
                collisionRect.y,
                collisionRect.width,
                collisionRect.height);
    }

    public void draw(SpriteBatch batch) {

        engine1.draw(batch);
        engine2.draw(batch);

        if(dir == MIDDLE) {
            batch.draw(middleImage, x - 5, y - 3);
        }
        else if(dir == RIGHT) {
            batch.draw(rightImage, x - 5, y - 3);
        }
        else if(dir == LEFT) {
            batch.draw(leftImage, x - 5, y - 3);
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void shoot(Array<Bullet> bullets) {
        if (lastShot + shootDelay < System.currentTimeMillis()) {
            lastShot = System.currentTimeMillis(); //restart timer
            if (currentWeapon == SINGLE) {
                Bullet b = new Bullet(x + COLLISION_RECT_WIDTH / 2,
                        y + COLLISION_RECT_HEIGHT);
                b.setVelocity(0, 800);//straight up (now tell me)
                bullets.add(b);
            } else if (currentWeapon == DOUBLE) {
                Bullet b = new Bullet(x,
                        y + COLLISION_RECT_HEIGHT);
                b.setVelocity(0, 800);//straight up (now tell me)
                bullets.add(b);
                b = new Bullet(x + COLLISION_RECT_WIDTH,
                        y + COLLISION_RECT_HEIGHT);
                b.setVelocity(0, 800);//straight up (now tell me)
                bullets.add(b);
            }
        }
    }

    public void setDir(int dir) {
        this.dir = dir;

        if(dir == MIDDLE) {
            engine1.setPosition(x + 4, y + 3);
            engine2.setPosition(x + COLLISION_RECT_WIDTH - 5, y + 3);
        }
        else if(dir == LEFT) {
            engine1.setPosition(x + 4, y + 3);
            engine2.setPosition(x + COLLISION_RECT_WIDTH - 12, y + 3);
        }
        else if(dir == RIGHT) {
            engine1.setPosition(x + 11, y + 3);
            engine2.setPosition(x + COLLISION_RECT_WIDTH - 5, y + 3);
        }
    }
}
