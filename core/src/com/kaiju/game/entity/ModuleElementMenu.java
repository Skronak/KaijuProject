package com.kaiju.game.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.kaiju.game.manager.GameManager;

/**
 * Represente un module dans le menu deroulant des modules
 * Created by Skronak on 21/03/2018.
 *
 */

public class ModuleElementMenu extends Table {
    private GameManager gameManager;
    private Label moduleLevelLabel;
    private TextButton buyButton;
    private Image moduleLevelImage;
    private Label elementTitle;
    private Label goldBonusLabel;
    private Label timeBonusLabel;
    private Image goldIcon;
    private Image timeIcon;


    public ModuleElementMenu(GameManager gameManager){
        this.gameManager = gameManager;

    }

    /**
     * Methode d'initialise du module a partir de sa position
     * dans la liste de tous les modules.
     * @param i
     */
    public void initModuleElementMenu(int i) {
        ModuleElementDTO moduleElementDTOSource = gameManager.getAssetManager().getModuleElementList().get(i);
        int currentLevel = gameManager.getGameInformation().getUpgradeLevelList().get(i);
        ModuleLevelDTO moduleLevel = gameManager.getAssetManager().getModuleElementList().get(i).getLevel().get(gameManager.getGameInformation().getUpgradeLevelList().get(i));

        moduleLevelLabel = new Label("Level "+currentLevel, gameManager.getAssetManager().getSkin());
        elementTitle = new Label(moduleElementDTOSource.getTitle(), gameManager.getAssetManager().getSkin());
        goldBonusLabel = new Label("+"+gameManager.getLargeMath().getDisplayValue(moduleLevel.getGeneration().getValue(), moduleLevel.getGeneration().getCurrency()), gameManager.getAssetManager().getSkin());
        timeBonusLabel = new Label("- 1 min", gameManager.getAssetManager().getSkin());
        buyButton = new TextButton(gameManager.getLargeMath().getDisplayValue(moduleElementDTOSource.getLevel().get(currentLevel).getCost().getValue(), moduleElementDTOSource.getLevel().get(currentLevel).getCost().getCurrency()),gameManager.getAssetManager().getModuleMenuBuyTxtBtnStyle());
//        buyButton.addListener(new BuyUpgradeButtonLtistener(upgradeModuleMenu, i));
//        goldIcon = new Image(gameManager.getAssetManager().getGoldIcon());
//        timeIcon = new Image(gameManager.getAssetManager().getTimeIcon());

        // Premiere colonne
        VerticalGroup vgCol0 = new VerticalGroup();
        vgCol0.addActor(elementTitle);
  //      vgCol0.addActor(skillIcon);

        // Liste level actuel du module
        Table moduleLevelGroup = new Table();
        moduleLevelGroup.add(moduleLevelLabel).left().colspan(4);
        moduleLevelGroup.row();
//        moduleLevelGroup.add(moduleLevelImage).size(120, 40).left().colspan(4);
        moduleLevelGroup.row();
      //  moduleLevelGroup.add(goldIcon).size(20,20).left();
        moduleLevelGroup.add(goldBonusLabel).left();
//        moduleLevelGroup.add(timeIcon).size(20,20).left();
        moduleLevelGroup.add(timeBonusLabel).left();

        this.setHeight(30);
        this.add(vgCol0).width(80);
        this.add(moduleLevelGroup);
        this.add(buyButton).height(90).width(70);
    }

    /**
     * Methode de mise a jour des informations du menu
     * d'un module existant
     * @param i
     */
    // OBSOLETE
    public void updateModuleElementMenu(int i) {
        ModuleElementDTO moduleElementDTOSource = gameManager.getAssetManager().getModuleElementList().get(i);

        moduleLevelLabel.setText("Level "+gameManager.getGameInformation().getUpgradeLevelList().get(i));
//        moduleLevelImage.setDrawable(new TextureRegionDrawable(new TextureRegion(gameManager.getAssetManager().getUpgradeLvlImageList().get(gameManager.getGameInformation().getUpgradeLevelList().get(i)))));
//        skillIcon.setDrawable(new TextureRegionDrawable(new TextureRegion(gameManager.getAssetManager().getModuleDrawableUpList().get(i))));
        elementTitle.setText(moduleElementDTOSource.getTitle());
        goldBonusLabel.setText("50");
        timeBonusLabel.setText("20");
        buyButton.setText(gameManager.getLargeMath().getDisplayValue(moduleElementDTOSource.getLevel().get(i).getCost().getValue(), moduleElementDTOSource.getLevel().get(i).getCost().getCurrency()));
    }
//*****************************************************
//                  GETTER & SETTER
// ****************************************************
    public Label getModuleLevelLabel() {
        return moduleLevelLabel;
    }

    public void setModuleLevelLabel(Label moduleLevelLabel) {
        this.moduleLevelLabel = moduleLevelLabel;
    }

    public TextButton getBuyButton() {
        return buyButton;
    }

    public void setBuyButton(TextButton buyButton) {
        this.buyButton = buyButton;
    }

    public Image getModuleLevelImage() {
        return moduleLevelImage;
    }

    public void setModuleLevelImage(Image moduleLevelImage) {
        this.moduleLevelImage = moduleLevelImage;
    }

    public Label getElementTitle() {
        return elementTitle;
    }

    public void setElementTitle(Label elementTitle) {
        this.elementTitle = elementTitle;
    }

    public Label getGoldBonusLabel() {
        return goldBonusLabel;
    }

    public void setGoldBonusLabel(Label goldBonusLabel) {
        this.goldBonusLabel = goldBonusLabel;
    }

    public Label getTimeBonusLabel() {
        return timeBonusLabel;
    }

    public void setTimeBonusLabel(Label timeBonusLabel) {
        this.timeBonusLabel = timeBonusLabel;
    }


    public Image getGoldIcon() {
        return goldIcon;
    }

    public Image getTimeIcon() {
        return timeIcon;
    }
}
