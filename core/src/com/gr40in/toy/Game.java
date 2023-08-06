package com.gr40in.toy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.gr40in.toy.files.GameFilesHandler;
import com.gr40in.toy.model.Toy;

import java.util.ArrayList;
import java.util.List;

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

    private List<Toy> toysList;
    private GameFilesHandler filesHandler;


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

        toysList = new ArrayList<>();

        toysList.add(new Toy(1, "Bear", 15, "ball.png"));
        toysList.add(new Toy(2, "Ball", 5, "ball.png"));
        toysList.add(new Toy(3, "Cubes", 7, "cubes.png"));

        filesHandler = new GameFilesHandler();

        System.out.println(filesHandler.getList());

//        Map<Integer, Integer> toysMapToSave = new HashMap<>();
//        toysMapToSave.put(1, 5);
//        toysMapToSave.put(2, 7);
//        toysMapToSave.put(3, 12);
//
//        filesHandler.save(toysList, "listGSON");
//        filesHandler.save(toysMapToSave, "mapGSON");
        //filesHandler.save(toysMapToSave);
//
//        List<Toy> list = new ArrayList<>();
//        try {
//            list = filesHandler.getToysFromGSON();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }

//        System.out.println(list);
//
//        Map<Integer, Integer> toysMap = new HashMap<>();
//        try {
//            toysMap = filesHandler.getFromGSON();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(toysMap);


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

                bearPlace.x = getRandomFloatBetweenRange(1024 / 3, 1024);
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
