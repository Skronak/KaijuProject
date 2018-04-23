package com.kaiju.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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

public class TitleScreen implements Screen {

    private Stage stage;
    private OrthographicCamera camera;
    private Viewport viewport;
    private GameManager gameManager;

    public TitleScreen(final GameManager gameManager) {
        this.gameManager=gameManager;

        Image backgroundImage = gameManager.getAssetManager().getMainBackground();
        backgroundImage.setScale(1.7f);
        backgroundImage.setPosition(-150,0);

        camera = new OrthographicCamera(Constants.V_WIDTH, Constants.V_HEIGHT);
        viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);
        stage = new Stage(viewport);

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        Label versionLabel = new Label(Constants.CURRENT_VERSION, new Label.LabelStyle(gameManager.getAssetManager().getFont(), Color.WHITE));
        versionLabel.setColor(1,1,1,0.5f);
        versionLabel.setFontScale(0.5f);

        Label titleLabel = new Label("Kaiju Kakusei", new Label.LabelStyle(gameManager.getAssetManager().getFont(), Constants.NORMAL_LABEL_COLOR));
        titleLabel.setPosition(titleLabel.getX()+10, Constants.V_HEIGHT-100);
        TextButton textButton = new TextButton("START", gameManager.getAssetManager().getTextButtonStyle());
        textButton.setSize(200,200);
        textButton.setPosition((Constants.V_WIDTH/2)-textButton.getWidth()/2, Constants.V_HEIGHT/5);

        textButton.getLabel().addAction(Actions.alpha(0));
        textButton.getLabel().act(0);
        textButton.getLabel().addAction(Actions.forever(Actions.sequence(Actions.fadeIn(2f), Actions.fadeOut(1f))));
        // Declaration des listener
        InputListener buttonListener = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gameManager.switchToPlayScreenFromStart();
                return false;
            }
        };
        textButton.addListener(buttonListener);

        stage.addActor(backgroundImage);
        stage.addActor(titleLabel);
        stage.addActor(textButton);
        stage.addActor(versionLabel);
    }

    @Override
    public void show() {

        // fadeIn
        stage.getRoot().getColor().a = 0;
        stage.getRoot().addAction(fadeIn(0.5f));

        gameManager.getAssetManager().getLoseMusic().stop();
        gameManager.getAssetManager().getKaijuRoar1Sound().stop();
//        gameManager.getAssetManager().getMainMusic().play();

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

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
