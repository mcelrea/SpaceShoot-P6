package com.mcelrea.spacegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by mcelrea on 1/18/2017.
 */
public class GameplayScreen implements Screen {

    private static final float WORLD_WIDTH = 600;
    private static final float WORLD_HEIGHT = 800;
    private SpriteBatch batch; //draw graphics
    private ShapeRenderer shapeRenderer; //draw shapes
    private Camera camera; //the players view of the world
    private Viewport viewport; //control the view of the world
    private SpaceShip player;


    public GameplayScreen(MyGdxGame myGdxGame) {
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(); //2D camera
        camera.position.set(WORLD_WIDTH/2,WORLD_HEIGHT/2,0);
        camera.update();
        viewport = new FitViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        batch = new SpriteBatch();
        player = new SpaceShip();
    }

    @Override
    public void render(float delta) {
        clearScreen();
        getUserInput();

        batch.setProjectionMatrix(camera.projection);
        batch.setTransformMatrix(camera.view);
        //all graphics drawing goes here
        batch.begin();
        batch.end();

        shapeRenderer.setProjectionMatrix(camera.projection);
        shapeRenderer.setTransformMatrix(camera.view);
        //all graphics drawing goes here
        shapeRenderer.begin();
        player.drawDebug(shapeRenderer);
        shapeRenderer.end();
    }

    private void getUserInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.moveUp();
        }
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}