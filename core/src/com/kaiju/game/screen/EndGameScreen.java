package com.kaiju.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

/**
 * Created by Skronak on 24/03/2018.
 */

public class EndGameScreen implements Screen {

    private Stage stage;
    private OrthographicCamera camera;
    private Viewport viewport;
    private GameManager gameManager;
    private Image backgroundImage;
    private Label titleLabel;
    private Label tapLabel;
    private Label timeLabel;
    private Label scoreLabel;
    private Label subTitleLabel;

    // TEST Animation text
    private float TIMEPERCHAR = 0.5f; //play with this for dif speeds
    private float ctimeperchar = 0;
    private int numchars = 0;
    private String subTextFull;
    public EndGameScreen(final GameManager gameManager) {
        this.gameManager=gameManager;

        backgroundImage = gameManager.getAssetManager().getEndGameImage();

        camera = new OrthographicCamera(Constants.V_WIDTH, Constants.V_HEIGHT);
        viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);
        stage = new Stage(viewport);

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        titleLabel = new Label("VICTORY !", new Label.LabelStyle(gameManager.getAssetManager().getFont(), Constants.NORMAL_LABEL_COLOR));

        subTitleLabel = new Label("", new Label.LabelStyle(gameManager.getAssetManager().getFont(), Constants.NORMAL_LABEL_COLOR));
        subTitleLabel.setWidth(Constants.V_WIDTH);
        subTitleLabel.setWrap(true);
        scoreLabel = new Label("", new Label.LabelStyle(gameManager.getAssetManager().getFont(), Constants.NORMAL_LABEL_COLOR));
        timeLabel = new Label("", new Label.LabelStyle(gameManager.getAssetManager().getFont(), Constants.NORMAL_LABEL_COLOR));
        tapLabel = new Label("", new Label.LabelStyle(gameManager.getAssetManager().getFont(), Constants.NORMAL_LABEL_COLOR));
        tapLabel.setFontScale(0.75f);
        scoreLabel.setFontScale(0.75f);
        timeLabel.setFontScale(0.75f);
        titleLabel.setPosition(titleLabel.getX()+10, Constants.V_HEIGHT-50);
        scoreLabel.setPosition(titleLabel.getX()+10, Constants.V_HEIGHT-150);
        timeLabel.setPosition(titleLabel.getX()+10, Constants.V_HEIGHT-200);
        tapLabel.setPosition(titleLabel.getX()+10, Constants.V_HEIGHT-250);

        subTitleLabel.setPosition(titleLabel.getX()+10, Constants.V_HEIGHT/3);

        Label creatorLabel = new Label("Made by Narvak", new Label.LabelStyle(gameManager.getAssetManager().getFont(), Color.WHITE));
        creatorLabel.setColor(1,1,1,0.5f);
        creatorLabel.setFontScale(0.5f);

        TextButton textButton = new TextButton("", gameManager.getAssetManager().getTextButtonStyle());
        textButton.setSize(Constants.V_WIDTH,Constants.V_HEIGHT);

        textButton.getLabel().addAction(Actions.alpha(0));
        textButton.getLabel().act(0);
        textButton.getLabel().addAction(Actions.forever(Actions.sequence(Actions.fadeIn(2f), Actions.fadeOut(1f))));
        // Declaration des listener
        InputListener buttonListener = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gameManager.switchToTitleScreenFromEnd();
                return false;
            }
        };
        textButton.addListener(buttonListener);

        stage.addActor(backgroundImage);
        stage.addActor(titleLabel);
        stage.addActor(subTitleLabel);
        stage.addActor(timeLabel);
        stage.addActor(scoreLabel);
        stage.addActor(tapLabel);
        stage.addActor(textButton);
        stage.addActor(creatorLabel);
    }

    @Override
    public void show() {

        // fadeIn
        stage.getRoot().getColor().a = 0;
        stage.getRoot().addAction(fadeIn(0.5f));

        gameManager.getAssetManager().getMainMusic().stop();
        backgroundImage.setPosition(-20,-50);
        camera.zoom=1.1f;
        backgroundImage.addAction(Actions.sequence(Actions.moveBy(-780,0,80),Actions.moveBy(780,0,80)));

        scoreLabel.setText("Score: " + gameManager.getGameInformation().getCurrentGold());
        timeLabel.setText("Time: " + gameManager.getGameInformation().getTotalGameTime()/1000/60 +"min");
        tapLabel.setText("Total hit: " + gameManager.getGameInformation().getTotalTapNumber());

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        camera.update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

        subTextFull = "HUMANITY IS SAVED, FOR NOW ...";
        if(numchars<subTextFull.getBytes().length){
            ctimeperchar+=delta*1.5f; //1.5f pour acceler
            if(ctimeperchar>=TIMEPERCHAR){
                ctimeperchar=0;
                numchars++;
            }
        }
        subTextFull = subTextFull.substring(0, numchars);
        subTitleLabel.setText(subTextFull);
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
