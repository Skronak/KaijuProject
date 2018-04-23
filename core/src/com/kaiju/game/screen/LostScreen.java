package com.kaiju.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kaiju.game.manager.GameManager;
import com.kaiju.game.utils.Constants;

import static com.badlogic.gdx.Gdx.files;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

/**
 * Created by Skronak on 24/03/2018.
 */

public class LostScreen implements Screen {

    private Stage stage;
    private OrthographicCamera camera;
    private Viewport viewport;
    private GameManager gameManager;

    public LostScreen(final GameManager gameManager) {
        this.gameManager=gameManager;

        Image backgroundImage = gameManager.getAssetManager().getLoseBackground();

        backgroundImage.setSize(Constants.V_WIDTH,Constants.V_HEIGHT);

        camera = new OrthographicCamera(Constants.V_WIDTH, Constants.V_HEIGHT);
        viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);
        stage = new Stage(viewport);

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        Label titleLabel = new Label("YOU FAILED", new Label.LabelStyle(gameManager.getAssetManager().getFont(), Constants.NORMAL_LABEL_COLOR));
        Label subTitleLabel = new Label("HUMANITY HAS ENDED..", new Label.LabelStyle(gameManager.getAssetManager().getFont(), Constants.NORMAL_LABEL_COLOR));
        subTitleLabel.setWrap(true);
        subTitleLabel.setWidth(Constants.V_WIDTH);
        titleLabel.setPosition(titleLabel.getX()+10, Constants.V_HEIGHT-100);
        subTitleLabel.setPosition(titleLabel.getX()+10, Constants.V_HEIGHT/2);

        TextButton textButton = new TextButton("", gameManager.getAssetManager().getTextButtonStyle());
        textButton.setSize(Constants.V_WIDTH,Constants.V_HEIGHT);

        textButton.getLabel().addAction(Actions.alpha(0));
        textButton.getLabel().act(0);
        textButton.getLabel().addAction(Actions.forever(Actions.sequence(Actions.fadeIn(2f), Actions.fadeOut(1f))));
        // Declaration des listener
        InputListener buttonListener = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gameManager.resetGame();
                gameManager.switchToTitleScreen();
                return false;
            }
        };
        textButton.addListener(buttonListener);

        stage.addActor(backgroundImage);
        stage.addActor(titleLabel);
        stage.addActor(subTitleLabel);
        stage.addActor(textButton);
    }

    @Override
    public void show() {

        // fadeIn
        stage.getRoot().getColor().a = 0;
        stage.getRoot().addAction(fadeIn(0.5f));

        gameManager.getAssetManager().getMainMusic().stop();
        gameManager.getAssetManager().getLoseMusic().play();
        gameManager.getAssetManager().getKaijuRoar1Sound().play();

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        camera.update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
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

    }

    @Override
    public void dispose() {

    }

    public Stage getStage() {
        return stage;
    }
}
