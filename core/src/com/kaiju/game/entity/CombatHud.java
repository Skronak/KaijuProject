package com.kaiju.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kaiju.game.utils.AbstractMenu;
import com.kaiju.game.utils.Constants;
import com.kaiju.game.utils.FightResultMenu;

import com.kaiju.game.manager.GameManager;

/**
 * Created by Skronak on 11/12/2016.
 *
 * Classe HUD contenant tous les boutons et menu
 * du jeu
 */
public class CombatHud implements Disposable {
    public Stage stage;
    private Viewport viewport;
    private Label versionLabel;
    private Label monsterName;
    private Label timeLabel ;
    Table hudTable;
    private Texture upgradeButtonTextureUp;
    private Texture skillButtonTextureUp;
    private Texture achievButtonTextureUp;
    private Texture mapButtonTextureUp;
    private Texture upgradeButtonTextureDown;
    private Texture skillButtonTextureDown;
    private Texture achievButtonTextureDown;
    private Texture mapButtonTextureDown;
    private GameManager gameManager;
    private BitmapFont font;
    private Image healthBarFull;
    private Image healthBarEmpty;
    private Table healthTable;
    private Label populationLabel;

    public CombatHud(GameManager gameManager) {
        OrthographicCamera camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);
        stage = new Stage(viewport);

        this.gameManager = gameManager;
        this.font = gameManager.getAssetManager().getFont();
        this.healthBarFull= gameManager.getAssetManager().getHealthBarFull();
        this.healthBarEmpty= gameManager.getAssetManager().getHealthBarEmpty();

        initHud();
    }

    /**
     * Initialise les informations du HUD et
     * ajoute les elemnts dans le stage
     */
    private void initHud(){
        monsterName = new Label("Odogaron", new Label.LabelStyle(font, Color.WHITE));
        monsterName.setFontScale(0.7f);

        timeLabel = new Label("00.00s", new Label.LabelStyle(font, Color.WHITE));
        timeLabel.setAlignment(Align.right);
        timeLabel.setFontScale(0.7f);

        hudTable = new Table();
        hudTable.top();
        hudTable.setFillParent(true);
        monsterName.setWrap(true);
        hudTable.add(monsterName).align(Align.left).top();
        hudTable.add(timeLabel).align(Align.right).colspan(3);
        hudTable.row();

        healthTable= new Table();
        healthTable.add(healthBarFull).fill();
        healthTable.add(healthBarEmpty).width(Value.percentWidth(.0F, healthTable));
        hudTable.add(healthTable).colspan(4);
        healthTable.pad(5);
        hudTable.row();

        populationLabel = new Label("Population: "+String.valueOf(gameManager.getGameInformation().getPopulation()),new Label.LabelStyle(gameManager.getAssetManager().getFont(), Constants.NORMAL_LABEL_COLOR));
        populationLabel.setFontScale(0.4f);
        hudTable.add(populationLabel);

        stage.addActor(hudTable);

        // Ajout des menu a l'interface
    }

    /**
     * Met a jour la population restante
      * @param
     */
    public void decreasePopulation(){
        populationLabel.setText("Population: "+gameManager.getGameInformation().getPopulation());
    }

    /**
     * Reduit la barre de vie
     * @param val
     */
    public void decreaseHealthBar(float val) {
        if (val>=0.96f ){
            val=0.96f; // Fix empeche la bar de s'etendre
        }
        healthTable.getCells().get(1).width(Value.percentWidth(val, healthTable));
        Gdx.app.log("width",String.valueOf(healthTable.getCells().get(1).getMinWidth()));

        healthTable.invalidate();
    }

    public void reinitialiseHud(float timer, String name) {
        healthTable.getCells().get(1).width(Value.percentWidth(0, healthTable));
        updateTimeLabel(timer);
        healthTable.invalidate();
        monsterName.setText(name);
    }

    public void updateTimeLabel(float timer){
        timeLabel.setText(String.valueOf(gameManager.getLargeMath().getDecimalFormat().format(timer)+"s"));
    }

    /**
     * Methode draw specifique
     */
    public void draw () {
        stage.draw();
        stage.act();
    }

    public void showCombatResult(){

    }
    public Stage getStage() {
        return stage;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public Label getTimeLabel() {
        return timeLabel;
    }

    public void setTimeLabel(Label timeLabel) {
        this.timeLabel = timeLabel;
    }
}
