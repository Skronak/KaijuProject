package com.kaiju.game.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.kaiju.game.entity.KaijuEntity;
import com.kaiju.game.entity.ModuleElementDTO;
import com.kaiju.game.utils.BitmapFontGenerator;
import com.kaiju.game.utils.Constants;

import java.util.ArrayList;

import static com.badlogic.gdx.Gdx.files;

/**
 * Created by Skronak on 02/08/2017.
 *
 * Classe de chargement des assets du jeu
 * TODO Utiliser atlas
 */
public class AssetManager {
    private Json json;
    private TextureRegionDrawable menuBackgroundTexture;
    private BitmapFont font;
    private Skin skin;
    private TextButton.TextButtonStyle textButtonStyle;
    private Image healthBarFull;
    private Image healthBarEmpty;
    private Image loseBackground;
    private Texture resultMenuBar;
    private Texture resultMenuWarningBar;
    private Image warningImage;
    private Image endGameImage;
    private Texture backgroundCity;
    private Texture backgroundCityHurt;
    private Color resultTextColor;
    private ArrayList<KaijuEntity> kaijuEntityList;
    private ArrayList<ModuleElementDTO> moduleElementList;
    private Music mainMusic;
    private Sound kaijuRoar1Sound;
    private Music easterMusic;
    private Image mainBackground;
    private Music loseMusic;
    private int loadValue;
    private TextButton.TextButtonStyle moduleMenuBuyTxtBtnStyle;

    public AssetManager() {
        this.json = new Json();

        loadValue=0;
        loadUpgradeFile();
        loadIcons();
        loadImage();
        loadTexture();
        initAsset();
    }

    private void initAsset() {
        Json json = new Json();
        kaijuEntityList = new ArrayList<KaijuEntity>();
        kaijuEntityList = json.fromJson(ArrayList.class, KaijuEntity.class, Gdx.files.internal("json/kaijuJSON.json"));

        moduleElementList = new ArrayList<ModuleElementDTO>();
        moduleElementList = json.fromJson(ArrayList.class, ModuleElementDTO.class, Gdx.files.internal("json/moduleElement.json"));

        BitmapFontGenerator generator = new BitmapFontGenerator();
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        font = generator.getFont();
        generator.dispose();
        mainBackground = new Image(new Texture(files.internal("image/mg.png")));
        resultMenuBar = new Texture(files.internal("ui/separator.png"));
        resultMenuWarningBar = new Texture(files.internal("ui/separator2.png"));
        backgroundCity = new Texture(files.internal("image/city.png"));
        backgroundCityHurt = new Texture(files.internal("image/cityHurt.png"));
        menuBackgroundTexture = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/backgroundMenu.png"))));
        loseBackground = new Image(new Texture(Gdx.files.internal("image/lost.png")));
        endGameImage = new Image(new Texture(Gdx.files.internal("image/endGame.png")));

        Texture health1 = new Texture(Gdx.files.internal("icon/healthBar1.jpg"));
        Texture health2 = new Texture(Gdx.files.internal("icon/healthBar3.jpg"));
        healthBarFull = new Image(health1);
        healthBarEmpty = new Image(health2);
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.fontColor = Color.WHITE;
        textButtonStyle.downFontColor = Color.GRAY;
        resultTextColor = new Color(Constants.RESULT_LABEL_COLOR[0],Constants.RESULT_LABEL_COLOR[1],Constants.RESULT_LABEL_COLOR[2],1f);

        TextureRegionDrawable buyDrawableUp = new TextureRegionDrawable( new TextureRegion(new Texture(Gdx.files.internal("sprites/goldButtonUp.png"))) );
        TextureRegionDrawable buyDrawableDown = new TextureRegionDrawable( new TextureRegion(new Texture(Gdx.files.internal("sprites/goldButtonDown.png"))) );
        TextureRegionDrawable buyDrawableChecked = new TextureRegionDrawable( new TextureRegion(new Texture(Gdx.files.internal("sprites/goldButtonUp.png"))) );
        moduleMenuBuyTxtBtnStyle = new TextButton.TextButtonStyle(buyDrawableUp, buyDrawableDown,buyDrawableChecked, font);

        mainMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/main.mp3"));
        loseMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/lose.mp3"));
        easterMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/easterMusic.mp3"));
        loseMusic.setLooping(true);
        mainMusic.setLooping(true);
        easterMusic.setLooping(true);
        kaijuRoar1Sound = Gdx.audio.newSound(Gdx.files.internal("audio/godRoar1.mp3"));

        loadValue+=1;

    }

    public void loadUpgradeFile(){

        loadValue+=1;
    }

    public void loadIcons(){
        loadValue+=1;
    }

    public void loadTexture() {
        loadValue+=1;
    }
    public void loadImage() {
        loadValue+=1;
    }

    //*****************************************************
//                  GETTER & SETTER
// ****************************************************


    public ArrayList<KaijuEntity> getKaijuEntityList() {
        return kaijuEntityList;
    }

    public TextButton.TextButtonStyle getTextButtonStyle() {
        return textButtonStyle;
    }

    public BitmapFont getFont() {
        return font;
    }

    public Image getHealthBarFull() {
        return healthBarFull;
    }

    public Image getHealthBarEmpty() {
        return healthBarEmpty;
    }

    public Music getMainMusic() {
        return mainMusic;
    }

    public Music getLoseMusic() {
        return loseMusic;
    }

    public Texture getBackgroundCity() {
        return backgroundCity;
    }

    public Skin getSkin() {
        return skin;
    }

    public TextureRegionDrawable getMenuBackgroundTexture() {
        return menuBackgroundTexture;
    }

    public Texture getResultMenuBar() {
        return resultMenuBar;
    }

    public Color getResultTextColor() {
        return resultTextColor;
    }

    public Image getWarningImage() {
        return warningImage;
    }

    public ArrayList<ModuleElementDTO> getModuleElementList() {
        return moduleElementList;
    }

    public TextButton.TextButtonStyle getModuleMenuBuyTxtBtnStyle() {
        return moduleMenuBuyTxtBtnStyle;
    }

    public Image getLoseBackground() {
        return loseBackground;
    }

    public void setLoseBackground(Image loseBackground) {
        this.loseBackground = loseBackground;
    }

    public Sound getKaijuRoar1Sound() {
        return kaijuRoar1Sound;
    }

    public Image getEndGameImage() {
        return endGameImage;
    }

    public Image getMainBackground() {
        return mainBackground;
    }

    public Music getEasterMusic() {
        return easterMusic;
    }

    public Texture getBackgroundCityHurt() {
        return backgroundCityHurt;
    }
}
