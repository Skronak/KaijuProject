package com.kaiju.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kaiju.game.input.CustomResultInputProcessor;
import com.kaiju.game.manager.GameManager;
import com.kaiju.game.utils.Constants;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

/**
 * Created by Skronak on 15/07/2017.
 */

public class UpgradeScreen implements Screen {

    private GameManager gameManager;
    private Camera camera;
    private FitViewport viewport;
    private Stage stage;
    private CustomResultInputProcessor customResultInputProcessor;
    private Group layer0GraphicObject; // Background
    private Group layer1GraphicObject; // Objects
    private Group layer2GraphicObject; // Foreground
    private Table table;

    public UpgradeScreen(GameManager gameManager){
        this.gameManager = gameManager;

        camera = new OrthographicCamera(Constants.V_WIDTH, Constants.V_HEIGHT);
        viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);
        customResultInputProcessor=new CustomResultInputProcessor(gameManager);

        stage = new Stage(viewport);
        layer0GraphicObject = new Group();
        layer1GraphicObject = new Group();
        layer2GraphicObject = new Group();

        Image upgradeBGImg = gameManager.getAssetManager().getUpgradeBGImage();
        upgradeBGImg.setSize(Constants.V_WIDTH, Constants.V_HEIGHT);
        layer0GraphicObject.addActor(upgradeBGImg);
        table = new Table();

        stage.addActor(layer0GraphicObject);
        stage.addActor(layer1GraphicObject);
        stage.addActor(layer2GraphicObject);
    }

    @Override
    public void show() {

        // fadeIn
        stage.getRoot().getColor().a = 0;
        stage.getRoot().addAction(fadeIn(0.5f));

        Gdx.app.debug("UpgradeScreen","Show");

        Gdx.input.setInputProcessor(customResultInputProcessor);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
            viewport.update(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
    }

}
