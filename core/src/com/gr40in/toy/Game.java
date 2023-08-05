package com.gr40in.toy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;

public class Game extends ApplicationAdapter {
    private SpriteBatch batch;
    private OrthographicCamera camera;

    private Rectangle bearPlace;
    private Rectangle ballPlace;

    private Texture bear;
    private Texture ball;
    private Texture cubes;

    private BitmapFont font;
    private String temp;
    private String temp2;


    public static float getRandomFloatBetweenRange(float min, float max) {

        return (float) (Math.random() * ((max - min) + 1)) + min;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 1024);
        bear = new Texture(Gdx.files.internal("ball.png"));
        ball = new Texture(Gdx.files.internal("ball.png"));
        cubes = new Texture(Gdx.files.internal("cubes.png"));

        temp = "";
        temp2 = "";

        bearPlace = new Rectangle();
        bearPlace.x = 1024 / 2 - (100 / 2);
        bearPlace.y = 1024 - 2 * 100;
        bearPlace.width = 100;
        bearPlace.height = 100;

        ballPlace = new Rectangle();
        ballPlace.x = 512 / 2 - (100 / 2);
        ballPlace.y = 512 - 2 * 100;
        ballPlace.width = 100;
        ballPlace.height = 100;

        font = new BitmapFont();


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
        bearPlace.x += Math.random() * (Math.random() > 0.5 ? -1 : 1) * 8;
        bearPlace.y += Math.random() * (Math.random() > 0.5 ? -1 : 1) * 8;

        ballPlace.x += Math.random() * (Math.random() > 0.5 ? -1 : 1) * 8;
        ballPlace.y += Math.random() * (Math.random() > 0.5 ? -1 : 1) * 8;

        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);




            temp = touchPos.toString();
            temp2 = bearPlace.toString();

//            bearPlace.x = Gdx.input.getX()-50;
//            bearPlace.y = 1024 - 50 - Gdx.input.getY();
//            batch.draw(cubes, Gdx.input.getX(), Gdx.input.getY());
            if (Math.abs(bearPlace.x - touchPos.x) < 100
                    && Math.abs(bearPlace.y - touchPos.y) < 100) {

                bearPlace.x  = getRandomFloatBetweenRange(1024 / 3, 1024);
                bearPlace.y = getRandomFloatBetweenRange(1024 / 3, 1024);

                if (bearPlace.x < 0 || (bearPlace.x > 1024 - 100))
                    bearPlace.x = getRandomFloatBetweenRange(1024 / 3, 1024);

                if (bearPlace.y < 0 || bearPlace.y > 1024 - 100)
                    bearPlace.y = getRandomFloatBetweenRange(1024 / 3, 1024);

//                batch.draw(cubes, Gdx.input.getX(), Gdx.input.getY());
            }
        }


        ScreenUtils.clear(0, 0.4f, 0, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
//		batch.draw(bear,  100, 750);
//		batch.draw(ball,  450, 750);
//		batch.draw(cubes,  700, 750);
        batch.draw(bear, bearPlace.x, bearPlace.y);
        font.draw(batch, temp, 500, 500);
        font.draw(batch, temp2, 500, 400);
        batch.draw(ball, ballPlace.x, ballPlace.y);


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
