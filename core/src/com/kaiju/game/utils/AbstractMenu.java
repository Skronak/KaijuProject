package com.kaiju.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import com.kaiju.game.manager.GameManager;

/**
 * Created by Skronak on 04/08/2017.
 */

public abstract class AbstractMenu {
    protected GameManager gameManager;
    protected Table parentTable;
    protected Skin skin;
    private float menu_width;
    private float menu_height;
    private TextureRegionDrawable menuBackground;

    public AbstractMenu(GameManager gameManager) {
        this.gameManager = gameManager;
        initMenu();
    }

    public void initMenu() {
        menu_width = Constants.V_WIDTH - Constants.UPDATE_MENU_PAD_EXTERNAL_WIDTH;
        menu_height = Constants.V_HEIGHT - Constants.PLAYSCREEN_MENU_BUTTON_HEIGHT - (Constants.UPDATE_MENU_PAD_EXTERNAL_HEIGHT);
        menuBackground = gameManager.getAssetManager().getMenuBackgroundTexture();
        skin = gameManager.getAssetManager().getSkin();

        // Definition du menu
        parentTable = new Table();
        parentTable.setBackground(menuBackground);
        parentTable.setWidth(menu_width);
        parentTable.setHeight(menu_height);
        parentTable.setPosition(Constants.UPDATE_MENU_PAD_EXTERNAL_WIDTH/2,Constants.PLAYSCREEN_MENU_HEIGHT);
        parentTable.setVisible(false);
    }

    public void update(){

    }

    /**
     * Definition du fond du menu
     * @param fname
     * @return
     */
    private NinePatch getNinePatch(String fname) {
        // Get the image
        final Texture t = new Texture(Gdx.files.internal(fname));
        return new NinePatch( new TextureRegion(t, 1, 1 , t.getWidth() - 2, t.getHeight() - 2), Constants.UPDATE_MENU_PAD_INTERNAL, Constants.UPDATE_MENU_PAD_INTERNAL, Constants.UPDATE_MENU_PAD_INTERNAL, Constants.UPDATE_MENU_PAD_INTERNAL);
    }

//*****************************************************
//                  GETTER & SETTER
// ****************************************************

    public GameManager getGameManager() {
        return gameManager;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public Table getParentTable() {
        return parentTable;
    }
}
