package com.kaiju.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kaiju.game.action.ChangeStateAction;
import com.kaiju.game.actor.AnimatedBaseActor;
import com.kaiju.game.entity.AnimatedActor;
import com.kaiju.game.entity.CombatHud;
import com.kaiju.game.input.CustomInputListener;
import com.kaiju.game.input.CustomInputProcessor;
import com.kaiju.game.manager.GameManager;
import com.kaiju.game.utils.Constants;
import com.kaiju.game.utils.GameState;

import java.util.Random;

import static com.badlogic.gdx.Gdx.files;
import static com.badlogic.gdx.Gdx.input;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

/**
 * Created by Skronak on 24/03/2018.
 */
// TODO Enemy = le playscreen, l'isoler et le mettre dans une calsse apart
public class PlayScreen extends AbstractScreen {

    private Stage stage;
    private Skin skin;
    private OrthographicCamera camera;
    private Viewport viewport;
    private float combatTimer;
    private float autoSaveTimer;
    private SpriteBatch spriteBatch;
    private AnimatedActor enemy;
    private AnimatedBaseActor playerMecha;
    private int healthMax;
    private int healthCurrent;
    private int textAnimMinX;
    private Label damageLabel;
    private int[] damageLabelPositionY = {300,320,350,290,340,300};
    private int[] damageLabelPositionX = {150,100,200,180,120,220};
    int gLPPointer;
    private Label causaltyLabel;

    private CombatHud hud;
    private GameManager gameManager;
    // Layers
    private Group layer0GraphicObject = new Group(); // Background
    private Group layer1GraphicObject = new Group(); // Objects
    private Group layer2GraphicObject = new Group(); // Foreground
    private AnimatedActor tapActor;
    private Random random;
    private InputMultiplexer inputMultiplexer;
    private Image backgroundImage;
    private Image backgroundImageHurt;

    public PlayScreen(final GameManager gameManager) {
        this.gameManager=gameManager;
        backgroundImage = new Image(gameManager.getAssetManager().getRandBackgroundTexture());
        backgroundImage.setScale(0.7f,0.7f);
        backgroundImage.setPosition(-150,0);
        backgroundImageHurt = new Image(gameManager.getAssetManager().getBackgroundCityHurt());
        backgroundImageHurt.setScale(backgroundImage.getScaleX(), backgroundImage.getScaleY());
        backgroundImageHurt.setPosition(backgroundImage.getX(), backgroundImage.getY());
        backgroundImageHurt.setVisible(false);

        BitmapFont font = gameManager.getAssetManager().getFont();
        causaltyLabel = new Label("",new Label.LabelStyle(gameManager.getAssetManager().getFont(), Color.WHITE));
        causaltyLabel.setFontScale(0.5f);
        causaltyLabel.setVisible(false);

        healthMax=200;
        spriteBatch = new SpriteBatch();
        skin = gameManager.getAssetManager().getSkin();
        hud = new CombatHud(gameManager);

        camera = new OrthographicCamera(Constants.V_WIDTH, Constants.V_HEIGHT);
        viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);
        stage = new Stage(viewport);

        enemy = new AnimatedActor(-30,200,280,250,0.5f,null,null,Animation.PlayMode.NORMAL);
        enemy.setVisible(true);

        Array<TextureRegion> framesMech = new Array<TextureRegion>();
        framesMech.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/mecha1.png"))));
        framesMech.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/mecha2.png"))));
        framesMech.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/mecha3.png"))));
        framesMech.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/mecha4.png"))));
        framesMech.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/mecha5.png"))));
        playerMecha = new AnimatedBaseActor();
        Animation mechaIdleAnimation = new Animation(0.2f,gameManager.getAssetManager().getMechaFramesIdle(), Animation.PlayMode.NORMAL);
        Animation mechaCritAnimation = new Animation(0.1f,gameManager.getAssetManager().getMechaFramesCrit(), Animation.PlayMode.LOOP_PINGPONG);
        playerMecha.setPosition(170,-10);
        playerMecha.setSize(280,430);
//        playerMecha.setPosition(430,25);
//        playerMecha.setSize(120,120);
        playerMecha.storeAnimation("idle", mechaIdleAnimation);
        playerMecha.storeAnimation("crit", mechaCritAnimation);
        playerMecha.setActiveAnimation("idle");

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        Array<TextureRegion> frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(new Texture(files.internal("sprites/tap/tap1.png"))));
        frames.add(new TextureRegion(new Texture(files.internal("sprites/tap/tap2.png"))));
        frames.add(new TextureRegion(new Texture(files.internal("sprites/tap/tap3.png"))));
        frames.add(new TextureRegion(new Texture(files.internal("sprites/tap/tap4.png"))));
        frames.add(new TextureRegion(new Texture(files.internal("sprites/tap/tap5.png"))));
        frames.add(new TextureRegion(new Texture(files.internal("sprites/tap/tap6.png"))));
        frames.add(new TextureRegion(new Texture(files.internal("sprites/tap/tap7.png"))));
        tapActor = new AnimatedActor(0,0,20,20,0.09f,frames, frames,Animation.PlayMode.REVERSED);
        tapActor.setVisible(false);

        textAnimMinX =100;
        random = new Random();

        backgroundImage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                int value = gameManager.decreasePopulation();
                hud.decreasePopulation();
                causaltyLabel.setText("- "+String.valueOf(value)+" People");

                processPointerHitAnimation(Gdx.input.getX(), Gdx.input.getY());
                processWrongTargetAnimation(Gdx.input.getX(), Gdx.input.getY());

                return true;
            }
        });

        // input Listener
        enemy.addListener(new CustomInputListener(this));
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    /**
     * Declenche le debut d'un combat
     */
    @Override
    public void show() {
        // fadeIn
        stage.getRoot().getColor().a = 0;
        stage.getRoot().addAction(fadeIn(0.5f));

        layer0GraphicObject.addActor(backgroundImage);
        layer0GraphicObject.addActor(backgroundImageHurt);
        layer1GraphicObject.addActor(enemy);
        layer1GraphicObject.addActor(tapActor);
        layer1GraphicObject.addActor(causaltyLabel);
        layer2GraphicObject.addActor(playerMecha);

        // Gestion des calques
        stage.addActor(layer0GraphicObject);
        stage.addActor(layer1GraphicObject);
        stage.addActor(layer2GraphicObject);

        startBattle();
    }

    /**
     * Reinitialise toutes les valeurs pour debuter
     * un combat
     */
    private void startBattle() {
        Gdx.app.debug("PlayScreen", "StartBattle");
        gameManager.setCurrentState(GameState.WAIT);
        gameManager.initialiseBattle();
        healthCurrent=healthMax;

        // Animation debut de combat
        // Desactive l'input pendant 4secondes
        enemy.addAction(Actions.sequence(new ChangeStateAction(gameManager, GameState.WAIT),Actions.delay(3f),new ChangeStateAction(gameManager, GameState.BATTLE)));
        backgroundImage.setDrawable(new TextureRegionDrawable(new TextureRegion(gameManager.getAssetManager().getRandBackgroundTexture())));
        backgroundImage.setPosition(backgroundImage.getX()-60, backgroundImage.getY());
        backgroundImage.addAction(Actions.moveBy(60,0,3));
        enemy.setPosition(enemy.getX()-60, enemy.getY());
        enemy.addAction(Actions.moveBy(60,0,3));
        playerMecha.setPosition(playerMecha.getX()+30, playerMecha.getY());
        playerMecha.addAction(Actions.moveBy(-30,0,3));
    }

    /**
     * Changement d'ecran lors d'une victoire
     * et animation de la mort de enemy
     */
    private void win_battle() {
        animateDeath();
        Gdx.app.log("win_battle","win_battle");
        gameManager.getBattleResultEntity().setTimerLeft(combatTimer);
        gameManager.switchToResultScreen();
        gameManager.progress();
    }

    /**
     * Changement d'ecran lors d'un defaite
     */
    private void lose_battle(){
        gameManager.switchToLoseScreen();
    }
    /**
     * Affiche image la ou lecran est touche
     * @param positionX
     * @param positionY
     */
    public void processPointerHitAnimation(int positionX, int positionY) {
        tapActor.clearActions();
        tapActor.setDeltatime(0);
        Vector3 position2World = camera.unproject(new Vector3(positionX, positionY,0));
        tapActor.setColor(Color.WHITE);
        tapActor.setPosition(position2World.x- 10-((int)tapActor.getWidth()/2),( (int) position2World.y-tapActor.getHeight()/2));
        tapActor.addAction(Actions.sequence(
                Actions.show(),
                Actions.fadeIn(0.1f),
                Actions.fadeOut(0.2f),
                Actions.hide()
        ));
    }

    public void processWrongTargetAnimation(int positionX, int positionY) {
        backgroundImageHurt.clearActions();
        backgroundImageHurt.addAction(Actions.sequence(Actions.show(),Actions.fadeIn(0.2f),Actions.fadeOut(0.2f),Actions.hide()));

        Vector3 position2World = camera.unproject(new Vector3(positionX, positionY,0));
        causaltyLabel.clearActions();
        causaltyLabel.setPosition(position2World.x- causaltyLabel.getWidth(),( (int) position2World.y+10));
        causaltyLabel.addAction(Actions.sequence(
                Actions.show(),
                Actions.fadeIn(0.3f),
                Actions.fadeOut(0.2f),
                Actions.hide()
        ));

    }    /**
     * Declenchement d'un touch sur l'ecran
     */
    public void processHit(boolean critical) {
        updateEnemyHealth();
        animateHit(critical);
   }

    private void updateEnemyHealth(){
        healthCurrent-=gameManager.getGameInformation().getGenGoldActive();
        if (healthCurrent <= 0) {
            gameManager.setCurrentState(GameState.WAIT);
            win_battle();
        } else {
            float healthPercent = 1-(float)healthCurrent / healthMax;
            if (healthPercent >=0.98f) {
                win_battle();
            } else {
                hud.decreaseHealthBar(healthPercent);
            }
        }
    }

    /**
     * Animation d'une touche
     * @param critical
     */
    private void animateHit(boolean critical){
        // Test si animation toujours en cours
        if (enemy.getActions().size==0) {
            enemy.addAction(Actions.sequence(Actions.color(Color.VIOLET,0.4f), Actions.color(Color.WHITE)));
        }
        String displayValue = gameManager.getLargeMath().getDisplayValue(gameManager.getGameInformation().getGenGoldActive(), gameManager.getGameInformation().getGenCurrencyActive());
        damageLabel = new Label(displayValue, new Label.LabelStyle(gameManager.getAssetManager().getFont(), Constants.NORMAL_LABEL_COLOR));
        damageLabel.setFontScale(0.5f);
        damageLabel.setTouchable(Touchable.disabled);
        damageLabel.setPosition(damageLabelPositionX[gLPPointer],damageLabelPositionY[gLPPointer]);
        if (critical) {
            damageLabel.setText("CRITICAL ");
            damageLabel.setColor(Constants.CRITICAL_LABEL_COLOR);
            playerMecha.setActiveAnimation("crit");
        }
        if (gLPPointer<damageLabelPositionX.length-1){
            gLPPointer++;
        } else {
            gLPPointer=0;
        }
        layer2GraphicObject.addActor(damageLabel);
        damageLabel.addAction(Actions.sequence(
                Actions.fadeIn(1f),
                Actions.fadeOut(2f),
                Actions.removeActor(damageLabel)
        ));
        damageLabel.addAction(Actions.moveTo(150+random.nextInt(100+textAnimMinX)-textAnimMinX,400,3f));
    }

    public void animateDeath(){
        enemy.playDeath();
        enemy.addAction(Actions.sequence(Actions.delay(1),Actions.sizeBy(0,-enemy.getHeight()/2,1.5f),Actions.hide()));
    }

    @Override
    public void render(float delta) {

        updateLogic();

        camera.update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
        spriteBatch.setProjectionMatrix(hud.getStage().getCamera().combined);
        hud.draw();

        //DEBUG
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.zoom = 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.zoom = 2;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.translate(-1f,0f);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.translate(1f,0f);
        }

    }

    /**
     * Modification de letat du jeu en fonction
     * du temps passe
     */
    public void updateLogic() {
        autoSaveTimer += Gdx.graphics.getDeltaTime();

        // Autosave
        if(autoSaveTimer >= Constants.DELAY_AUTOSAVE){
            Gdx.app.debug("PlayScreen","Saving");
            gameManager.getGameInformation().saveInformation();
            autoSaveTimer=0f;
        }

        switch (gameManager.getCurrentState()) {
            case BATTLE:
                combatTimer -= Gdx.graphics.getDeltaTime();
                Gdx.input.setInputProcessor(inputMultiplexer);
                if(combatTimer <= 0){
                    combatTimer=0f;
                    gameManager.setCurrentState(GameState.WAIT);
                    lose_battle();
                }
                hud.updateTimeLabel(combatTimer);
                break;
            case WAIT:
                Gdx.input.setInputProcessor(null);
                break;
            case END_BATTLE:
                Gdx.input.setInputProcessor(null);
            case UPGRADE:
                Gdx.input.setInputProcessor(hud.getStage());
                break;
            default:
                break;
        }
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
        Gdx.app.debug("PlayScreen","dispose");
        spriteBatch.dispose();
        hud.dispose();
        Gdx.app.debug("PlayScreen","saveInformation");
        gameManager.getLargeMath().formatGameInformation();
        gameManager.getGameInformation().saveInformation();
    }

    ///////////////////////////////////
    // GETTER & SETTER
    ///////////////////////////////////
    public GameManager getGameManager() {
        return gameManager;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCombatTimer(float combatTimer) {
        this.combatTimer = combatTimer;
    }

    public CombatHud getHud() {
        return hud;
    }

    public void setHealthMax(int healthMax) {

        this.healthMax = healthMax;
    }

    public AnimatedActor getEnemy() {

        return enemy;
    }
}
