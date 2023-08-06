package com.gr40in.toy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.gr40in.toy.model.Toy;
import com.gr40in.toy.model.ToyAssortmentImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game extends ApplicationAdapter {
    private final Integer worldHeight = 1024;
    private final Integer worldWith = 1024;
    private boolean running;

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private BitmapFont welcomeText;

    private ToyAssortmentImp assortment;
    private List<Rectangle> allToysOnGame;
    private List<Texture> allToysTextureOnGame;
    private List<Toy> toyList;
    private List<Integer> allToysLifeOnGame;

    public static float getRandomFloatBetweenRange(float min, float max) {
        return (float) (Math.random() * ((max - min) + 1)) + min;
    }

    public static float getRandomCoordinate() {
        return getRandomFloatBetweenRange(200, 850);
    }

    @Override
    public void create() {
        running = true;

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, worldWith, worldHeight);

        welcomeText = new BitmapFont();
        welcomeText.setColor(Color.RED);
        welcomeText.getData().setScale(3f);


        assortment = new ToyAssortmentImp();
        Map<Toy, Integer> toysMap = assortment.getAssortment();
        toyList = assortment.getToys();
//        System.out.println(toyList);

        allToysOnGame = new ArrayList<>();
        allToysTextureOnGame = new ArrayList<>();
        allToysLifeOnGame = new ArrayList<>();

        for (int i = 0; i < toyList.size(); i++) {
            allToysOnGame.add(new Rectangle().set(getRandomCoordinate(), getRandomCoordinate(), 0, 0));
            allToysTextureOnGame.add(new Texture(Gdx.files.internal(toyList.get(i).getImagePatch())));
            allToysLifeOnGame.add(toyList.get(i).getDifficulty());
        }
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0.4f, 0, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        if (running) {
            for (int i = 0; i < allToysOnGame.size(); i++) {
                allToysOnGame.get(i).x += Math.random() * (Math.random() > 0.5 ? -1 : 1) * 8;
                allToysOnGame.get(i).y += Math.random() * (Math.random() > 0.5 ? -1 : 1) * 8;
            }

            if (Gdx.input.isTouched()) {
                Vector3 touchPos = new Vector3();
                touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touchPos);


                for (int i = 0; i < allToysOnGame.size(); i++) {
                    Rectangle rectangle = allToysOnGame.get(i);
                    if (Math.abs(rectangle.x - touchPos.x) < 100
                            && Math.abs(rectangle.y - touchPos.y) < 100) {

                        allToysLifeOnGame.set(i, allToysLifeOnGame.get(i) - 1);

                        if (allToysLifeOnGame.get(i) == 0) {
                            running = false;

                            assortment.removeOneToy(toyList.get(i));

                        }

                        for (int j = 0; j < allToysOnGame.size(); j++) {
                            Rectangle rectangleInLoop = allToysOnGame.get(j);
                            rectangleInLoop.x = getRandomCoordinate();
                            rectangleInLoop.y = getRandomCoordinate();

                            if (rectangleInLoop.x < 0 || (rectangleInLoop.x > 1024 - 100)) {
                                rectangleInLoop.x = getRandomCoordinate();
                            }

                            if (rectangleInLoop.y < 0 || rectangleInLoop.y > 1024 - 100) {
                                rectangleInLoop.y = getRandomCoordinate();
                            }
                        }
                    }
                }
            }


            for (int i = 0; i < allToysOnGame.size(); i++) {
                batch.draw(allToysTextureOnGame.get(i), allToysOnGame.get(i).x, allToysOnGame.get(i).y);
                welcomeText.draw(batch, allToysLifeOnGame.get(i).toString(), allToysOnGame.get(i).x + 30, allToysOnGame.get(i).y);
            }

            welcomeText.draw(batch, "Click a toy for win!!", 700 / 2, 100);
        }
        if (!running) {
            for (int i = 0; i < allToysLifeOnGame.size(); i++) {
                if (allToysLifeOnGame.get(i) == 0) {
                    welcomeText.draw(batch, "you win " + toyList.get(i).getName() + "!!!  ^___^", 100, 100);
                    batch.draw(allToysTextureOnGame.get(i), allToysOnGame.get(i).x, allToysOnGame.get(i).y);
//                    System.out.println(assortment);
//                    assortment.removeOneToy(toyList.get(i));

                }
            }
        }

        batch.end();

    }

    @Override
    public void dispose() {
        batch.dispose();

    }
}
