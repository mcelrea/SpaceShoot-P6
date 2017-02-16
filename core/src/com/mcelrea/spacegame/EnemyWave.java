package com.mcelrea.spacegame;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

public class EnemyWave {
    protected Array<Enemy> enemies;
    protected static final int NUMOFENEMIES=10;
    protected static final int GAP=50;

    public EnemyWave(float x, float y) {
        enemies = new Array<Enemy>();
        for(int i=0; i < NUMOFENEMIES; i++) {
            Enemy e = new Enemy(x+(GAP*i),y);
            enemies.add(e);
        }
    }

    public void checkForHit(Bullet b) {
        for(int i=0; i < enemies.size; i++) {
            if(enemies.get(i).isHit(b)) {
                enemies.removeIndex(i);
                i--;
                b.setAlive(false);
            }
        }
    }

    public void act(float delta, Array<Bullet> bullets) {
        for(int i=0; i < enemies.size; i++) {
            enemies.get(i).act(delta,bullets);
        }
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        for(int i=0; i < enemies.size; i++) {
            enemies.get(i).drawDebug(shapeRenderer);
        }
    }
}
