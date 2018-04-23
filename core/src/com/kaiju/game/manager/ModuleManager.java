package com.kaiju.game.manager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.kaiju.game.entity.ModuleElementDTO;
import com.kaiju.game.entity.ModuleElementMenu;
import com.kaiju.game.entity.ModuleLevelDTO;
import com.kaiju.game.screen.CustomiseScreen;
import com.kaiju.game.utils.ValueDTO;

import java.util.List;

/**
 * Created by Skronak on 14/08/2017.
 * Classe gerant les upgrades et l'ecran d'upgrade
 */
public class ModuleManager {

    private GameManager gameManager;
    private CustomiseScreen moduleMenu;
    private List<ModuleElementDTO> moduleEntityList;

    public ModuleManager(GameManager gameManager) {
        this.moduleMenu = moduleMenu;
        this.moduleMenu = gameManager.getCustomiseScreen();
        this.gameManager = gameManager;
        this.moduleEntityList = gameManager.getAssetManager().getModuleElementList();

       // this.calculateGoldGen();
    }

    /**
     * Verifie qu'on peut cliquer sur l'upgrade
     * @param
     * @return
     */
    public boolean isAvailableUpgrade (int idSelect) {
        int currentlevel = gameManager.getGameInformation().getUpgradeLevelList().get(idSelect);
        ModuleLevelDTO moduleLevelCurrent = moduleEntityList.get(idSelect).getLevel().get(currentlevel);

        if ( (moduleEntityList.get(idSelect).getLevel().size() > currentlevel + 1)
           && (gameManager.getGameInformation().getCurrency() > moduleLevelCurrent.getCost().getCurrency())
           || ( (gameManager.getGameInformation().getCurrency() == moduleLevelCurrent.getCost().getCurrency())
              && (gameManager.getGameInformation().getCurrentGold() >= moduleLevelCurrent.getCost().getValue())
              )){
            return true;
        } else {
            return false;
        }
    }

    public void updateUpgradeButton () {
        for (int i=0;i<gameManager.getAssetManager().getModuleElementList().size();i++) {
            if (isAvailableUpgrade(i)){
            } else {
              //  ((ModuleElementMenu) moduleMenu.getScrollContainerVG().getChildren().get(i)).getBuyButton().setColor(Color.GRAY);
            }
        }
    }
    /**
     * Recalcule le goldGen global en fonction
     * du niveau des updates
     */
    public void calculateGoldGen(){
        float goldGen = 0;
        int currGen = 0;
        float goldGenToAdd = 0;
        int currGenToAdd = 0;
        // Total a additionner
        ValueDTO newVal = new ValueDTO(0,0);

        // Parcours la liste upgrades du joueur
        for (int i=0;i<gameManager.getGameInformation().getUpgradeLevelList().size();i++) {
            if (gameManager.getGameInformation().getUpgradeLevelList().get(i) > 0) {
                int upgradeLevel = gameManager.getGameInformation().getUpgradeLevelList().get(i);
                goldGen = newVal.getValue();
                currGen = newVal.getCurrency();
                // Dans les generations possible de l'upgrade, cherche celui du lvl puis cherche valeur de l'upgrade
                goldGenToAdd = moduleEntityList.get(i).getLevel().get(upgradeLevel).getGeneration().getValue();
                currGenToAdd = moduleEntityList.get(i).getLevel().get(upgradeLevel).getGeneration().getCurrency();
                newVal = gameManager.getLargeMath().increaseValue(goldGen,currGen,goldGenToAdd,currGenToAdd);
            }
        }
        gameManager.getGameInformation().setGenGoldActive(newVal.getValue());
    }
    /**
     * Augmente le niveau d'un module
     * @param idSelect
     */
    public void increaseUpgradeLevel(int idSelect) {
        // Calcul et Affichage de la soustraction
        ValueDTO decreaseValue = moduleEntityList.get(idSelect).getLevel().get(gameManager.getGameInformation().getUpgradeLevelList().get(idSelect)).getCost();
     //   this.gameManager.getPlayScreen().getHud().animateDecreaseGold(decreaseValue);

        // Mise a jour du montant des golds du joueur
        ValueDTO gameInformationValue = gameManager.getLargeMath().decreaseValue(gameManager.getGameInformation().getCurrentGold(),gameManager.getGameInformation().getCurrency(),decreaseValue.getValue(), decreaseValue.getCurrency());
        this.gameManager.getGameInformation().setCurrentGold(gameInformationValue .getValue());
        this.gameManager.getGameInformation().setCurrency(gameInformationValue .getCurrency());

        // Met a jour les informations de l'upgrade
        gameManager.getGameInformation().getUpgradeLevelList().set(idSelect, gameManager.getGameInformation().getUpgradeLevelList().get(idSelect) + 1);
        this.calculateGoldGen();

        // Regenere les upgrades a afficher en jeux
    //    this.moduleMenu.getGameManager().getStationActor().loadUpgrade();
     }

    /**
     * Met a jour les informations d'un detail d'upgrade
     * en fonction de l'upgrade selectionne
     * @param id
     */
    public void updateModuleInformation(int id) {
//        ModuleElementMenu moduleElementMenu = (ModuleElementMenu) moduleMenu.getScrollContainerVG().getChildren().get(id);
        ModuleLevelDTO moduleLevel = gameManager.getAssetManager().getModuleElementList().get(id).getLevel().get(gameManager.getGameInformation().getUpgradeLevelList().get(id));

 //       moduleElementMenu.getModuleLevelLabel().setText("Level "+gameManager.getGameInformation().getUpgradeLevelList().get(id));
//        moduleElementMenu.getModuleLevelImage().setDrawable(new TextureRegionDrawable(new TextureRegion(gameManager.getAssetManager().getUpgradeLvlImageList().get(gameManager.getGameInformation().getUpgradeLevelList().get(id)))));
//        moduleElementMenu.getSkillIcon().setDrawable(new TextureRegionDrawable(new TextureRegion(gameManager.getAssetManager().getModuleDrawableUpList().get(id))));
//        moduleElementMenu.getGoldBonusLabel().setText("+"+gameManager.getLargeMath().getDisplayValue(moduleLevel.getGeneration().getValue(), moduleLevel.getGeneration().getCurrency()));
//        moduleElementMenu.getTimeBonusLabel().setText("20");
//        moduleElementMenu.getBuyButton().setText(gameManager.getLargeMath().getDisplayValue(moduleLevel.getCost().getValue(), moduleLevel.getCost().getCurrency()));
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
}
