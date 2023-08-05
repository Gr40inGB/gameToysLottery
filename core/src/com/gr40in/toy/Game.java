package com.gr40in.toy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;

public class Game extends ApplicationAdapter {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Rectangle bucket;
    private Rectangle bearPlace;

    private Texture bear;
    private Texture ball;
    private Texture cubes;


    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 1024);
        bear = new Texture(Gdx.files.internal("bear.png"));
        ball = new Texture(Gdx.files.internal("ball.png"));
        cubes = new Texture(Gdx.files.internal("cubes.png"));

        bearPlace = new Rectangle();
        bearPlace.x = 1024 / 2 - (192 / 2);
        bearPlace.y = 800;
        bearPlace.width = 192;
        bearPlace.height = 256;


//		bucket = new Rectangle();
//		bucket.x = 1024 / 2 - 64 / 2;
//		bucket.y = 20;
//		bucket.width = 192;
//		bucket.height = 64;
//

//
//		if(Gdx.input.isTouched()) {
//			Vector3 touchPos = new Vector3();
//			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//			camera.unproject(touchPos);
//			bucket.x = touchPos.x - 64 / 2;
//		}
//		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= 200 * Gdx.graphics.getDeltaTime();
//		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += 200 * Gdx.graphics.getDeltaTime();
//		if(bucket.x < 0) bucket.x = 0;
//		if(bucket.x > 800 - 64) bucket.x = 800 - 64;
    }

    @Override
    public void render() {
        bearPlace.x += 1;
        bearPlace.y += 1;
        ScreenUtils.clear(0, 0.4f, 0, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
//		batch.draw(bear,  100, 750);
//		batch.draw(ball,  450, 750);
//		batch.draw(cubes,  700, 750);
        batch.draw(bear, bearPlace.x, bearPlace.y);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        bear.dispose();
        ball.dispose();
        cubes.dispose();
    }
}
