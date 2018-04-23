package com.kaiju.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kaiju.game.entity.KaijuEntity;
import com.kaiju.game.input.CustomResultInputProcessor;
import com.kaiju.game.utils.Constants;
import com.kaiju.game.utils.FightResultMenu;

import com.kaiju.game.manager.GameManager;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

/**
 * Created by Skronak on 15/07/2017.
 */

public class ResultScreen implements Screen {

    private GameManager gameManager;
    private Camera camera;
    private FitViewport viewport;
    private Stage stage;
    private KaijuEntity currentKaijuEntity;
    private FightResultMenu fightResultMenu;
    private CustomResultInputProcessor customResultInputProcessor;

    public ResultScreen(GameManager gameManager){
        this.gameManager = gameManager;

        camera = new OrthographicCamera(Constants.V_WIDTH, Constants.V_HEIGHT);
        viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);
        fightResultMenu = new FightResultMenu(gameManager);
        customResultInputProcessor=new CustomResultInputProcessor(gameManager);

        stage = new Stage(viewport);
        stage.addActor(fightResultMenu.getParentTable());
    }

    @Override
    public void show() {
        // fadeIn
        stage.getRoot().getColor().a = 0;
        stage.getRoot().addAction(fadeIn(0.5f));
        fightResultMenu.setKaijuEntity(currentKaijuEntity);
        gameManager.initResultEntity();
        fightResultMenu.initTable();

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

    public KaijuEntity getCurrentKaijuEntity() {
        return currentKaijuEntity;
    }

    public void setCurrentKaijuEntity(KaijuEntity currentKaijuEntity) {
        this.currentKaijuEntity = currentKaijuEntity;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
