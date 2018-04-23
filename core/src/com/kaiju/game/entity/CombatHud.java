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
    private Button skillButton;
    private Button upgradeButton;
    private Button mapButton;
    private Button achievButton;
    private GameManager gameManager;
    private BitmapFont font;
    private Image healthBarFull;
    private Image healthBarEmpty;
    private Table healthTable;
    private AbstractMenu resultMenu;

    public CombatHud(GameManager gameManager) {
        OrthographicCamera camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);
        stage = new Stage(viewport);

        this.gameManager = gameManager;
        this.font = gameManager.getAssetManager().getFont();
        this.healthBarFull= gameManager.getAssetManager().getHealthBarFull();
        this.healthBarEmpty= gameManager.getAssetManager().getHealthBarEmpty();

        initMenu();
        initButton();
        initHud();
    }

    /**
     * Initialise les menu
     */
    private void initMenu() {
        resultMenu = new FightResultMenu(gameManager);
//        stage.addActor(resultMenu.getParentTable());
//        resultMenu.getParentTable().setVisible(true);
    }
    /**
     * Initialisation des bouton du hud
     */
    private void initButton() {
        upgradeButtonTextureUp = new Texture(Gdx.files.internal("icon/hud_b2.png"));
        skillButtonTextureUp = new Texture(Gdx.files.internal("icon/hud_b1.png"));
        achievButtonTextureUp = new Texture(Gdx.files.internal("icon/hud_b3.png"));
        mapButtonTextureUp = new Texture(Gdx.files.internal("icon/hud_b4.png"));
        upgradeButtonTextureDown = new Texture(Gdx.files.internal("icon/hud_b2_r.png"));
        skillButtonTextureDown = new Texture(Gdx.files.internal("icon/hud_b1_r.png"));
        achievButtonTextureDown = new Texture(Gdx.files.internal("icon/hud_b3_r.png"));
        mapButtonTextureDown = new Texture(Gdx.files.internal("icon/hud_b4_r.png"));
        Drawable upgradeDrawableUp = new TextureRegionDrawable(new TextureRegion(upgradeButtonTextureUp));
        Drawable skillDrawableUp = new TextureRegionDrawable(new TextureRegion(skillButtonTextureUp));
        Drawable mapDrawableUp = new TextureRegionDrawable(new TextureRegion(achievButtonTextureUp));
        Drawable achievDrawableUp = new TextureRegionDrawable(new TextureRegion(mapButtonTextureUp));
        Drawable upgradeDrawableDown = new TextureRegionDrawable(new TextureRegion(upgradeButtonTextureDown));
        Drawable skillDrawableDown = new TextureRegionDrawable(new TextureRegion(skillButtonTextureDown));
        Drawable mapDrawableDown = new TextureRegionDrawable(new TextureRegion(achievButtonTextureDown));
        Drawable achievDrawableDown = new TextureRegionDrawable(new TextureRegion(mapButtonTextureDown));
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.up = skillDrawableUp;
        style.down = skillDrawableDown;
        upgradeButton = new ImageButton(style);
        ImageButton.ImageButtonStyle style2 = new ImageButton.ImageButtonStyle();
        style2.up = upgradeDrawableUp;
        style2.down = upgradeDrawableDown;
        skillButton = new ImageButton(style2);
        ImageButton.ImageButtonStyle style3 = new ImageButton.ImageButtonStyle();
        style3.up = mapDrawableUp;
        style3.down = mapDrawableDown;
        mapButton = new ImageButton(style3);
        ImageButton.ImageButtonStyle style4 = new ImageButton.ImageButtonStyle();
        style4.up = achievDrawableUp;
        style4.down = achievDrawableDown;
        achievButton = new ImageButton(style4);

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
//        hudTable.add(upgradeButton).expandY().bottom().height(Constants.PLAYSCREEN_MENU_BUTTON_HEIGHT).width(Constants.V_WIDTH/4);
//        hudTable.add(skillButton).expandY().bottom().height(Constants.PLAYSCREEN_MENU_BUTTON_HEIGHT).width(Constants.V_WIDTH/4);
//        hudTable.add(mapButton).expandY().bottom().height(Constants.PLAYSCREEN_MENU_BUTTON_HEIGHT).width(Constants.V_WIDTH/4);
//        hudTable.add(achievButton).expandY().bottom().height(Constants.PLAYSCREEN_MENU_BUTTON_HEIGHT).width(Constants.V_WIDTH/4);
        stage.addActor(hudTable);

        // Ajout des menu a l'interface
    }

    /**
     * Reduit la barre de vie
     * @param val
     */
    public void decreaseHealthBar(float val) {
        healthTable.getCells().get(1).width(Value.percentWidth(val, healthTable));
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
