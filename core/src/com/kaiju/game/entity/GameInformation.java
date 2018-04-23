package com.kaiju.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Skronak on 30/01/2017.
 *
 * Classe de stat & information sur le compte du jeu
 */
public class GameInformation {
    // dernier login
    private Long lastLogin;
    // Total d'or
    private float currentGold;
    // generation active d'or
    private float genGoldActive;
    // Currency (A=1, B=2, ... jusqua 9, AA=9+1
    private int currency;
    // Avancement dans le jeu
    private int currentState;
    // currency de gengoldActive
    private int genCurrencyActive;
    // multiplicateur d'or lors de critique
    private int criticalRate;
    // fichier de preference Android
    private Preferences prefs;
    // indicateur de premier lancment du jeu
    private boolean firstPlay;
    // id de station utilise
    private int stationId;
    // liste des niveau d'upgrade du joueur pour faciliter son acces
    private List<Integer> upgradeLevelList;
    // Total gameTime
    private Long totalGameTime;
    // Degat par coup
    private int damage;
    // Tap number
    private int totalTapNumber;
    // specific a une game
    private int factionId;
    private int factionLvl;
    private int factionExp;


    public GameInformation() {
        upgradeLevelList = new ArrayList<Integer>();
        prefs = Gdx.app.getPreferences("KaijuPreferences");

        if (!prefs.contains("lastLogin")) {
            Gdx.app.debug("GameInformation", "Initialisation du compte par defaut");
            currentGold = 0;
            currency = 0;
            damage = 2;
            genGoldActive = 2;
            genCurrencyActive = 0;
            criticalRate = 5;
            currentState=0;
            stationId = 1;
            upgradeLevelList.add(0);
            upgradeLevelList.add(0);
            upgradeLevelList.add(0);
            upgradeLevelList.add(0);
            upgradeLevelList.add(0);
            upgradeLevelList.add(0);
            upgradeLevelList.add(0);
            upgradeLevelList.add(0);
            lastLogin = System.currentTimeMillis();
            totalTapNumber=0;
            totalGameTime=0l;
            factionLvl=0;
            factionId=0;
            factionExp=0;
            firstPlay = true;
        } else {
            currentGold = prefs.getFloat("currentGold");
            currency = prefs.getInteger("currentCurrency");
            genGoldActive = prefs.getFloat("genGoldActive");
            damage = prefs.getInteger("damage");
            genCurrencyActive = prefs.getInteger("genCurrencyActive");
            criticalRate = prefs.getInteger("criticalRate");
            currentState = prefs.getInteger("currentState");
            stationId = prefs.getInteger("stationId");
            upgradeLevelList.add(prefs.getInteger("upgradeLevel1"));
            upgradeLevelList.add(prefs.getInteger("upgradeLevel2"));
            upgradeLevelList.add(prefs.getInteger("upgradeLevel3"));
            upgradeLevelList.add(prefs.getInteger("upgradeLevel4"));
            upgradeLevelList.add(prefs.getInteger("upgradeLevel5"));
            upgradeLevelList.add(prefs.getInteger("upgradeLevel6"));
            upgradeLevelList.add(prefs.getInteger("upgradeLevel7"));
            upgradeLevelList.add(prefs.getInteger("upgradeLevel8"));
            lastLogin = prefs.getLong("lastLogin");
            totalGameTime = prefs.getLong("totalGameTime");
            totalTapNumber = prefs.getInteger("totalTapNumber");
            factionExp = prefs.getInteger("factionExp");
            factionId = prefs.getInteger("factionId");
            factionLvl = prefs.getInteger("factionLvl");
        }
    }

    /**
     * Sauvegarde les informations courantes
     * dans le fichier de pref
     * TODO : ne pas tt sauvegarder chaque fois
     * TODO: gerer plusieurs type de sauvegarde
     */
    public void saveInformation() {
        prefs.putFloat("currentGold", currentGold);
        prefs.putInteger("currentCurrency", currency);
        prefs.putFloat("genGoldActive", genGoldActive);
        prefs.putInteger("genCurrencyActive", genCurrencyActive);
        prefs.putInteger("damage", damage);
        prefs.putInteger("criticalRate", criticalRate);
        prefs.putInteger("currentState", currentState);
        prefs.putInteger("stationId", stationId);
        prefs.putInteger("upgradeLevel1", upgradeLevelList.get(0));
        prefs.putInteger("upgradeLevel2", upgradeLevelList.get(1));
        prefs.putInteger("upgradeLevel3", upgradeLevelList.get(2));
        prefs.putInteger("upgradeLevel4", upgradeLevelList.get(3));
        prefs.putInteger("upgradeLevel5", upgradeLevelList.get(4));
        prefs.putInteger("upgradeLevel6", upgradeLevelList.get(5));
        prefs.putInteger("upgradeLevel7", upgradeLevelList.get(6));
        prefs.putInteger("upgradeLevel8", upgradeLevelList.get(7));
        prefs.putLong("lastLogin", System.currentTimeMillis());
        prefs.putLong("totalGameTime", totalGameTime + (System.currentTimeMillis() - lastLogin));
        prefs.putInteger("totalTapNumber", totalTapNumber);
        prefs.putInteger("factionExp", factionExp);
        prefs.getInteger("factionId", factionId);
        prefs.getInteger("factionLvl", factionLvl);

        prefs.flush();
    }

//*****************************************************
//                  GETTER & SETTER
// ****************************************************

    public Long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Long lastLogin) {
        this.lastLogin = lastLogin;
    }

    public float getCurrentGold() {
        return currentGold;
    }

    public void setCurrentGold(float currentGold) {
        this.currentGold = currentGold;
    }

    public float getGenGoldActive() {
        return genGoldActive;
    }

    public void setGenGoldActive(float genGoldActive) {
        this.genGoldActive = genGoldActive;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getGenCurrencyActive() {
        return genCurrencyActive;
    }

    public void setGenCurrencyActive(int genCurrencyActive) {
        this.genCurrencyActive = genCurrencyActive;
    }

    public int getCriticalRate() {
        return criticalRate;
    }

    public void setCriticalRate(int criticalRate) {
        this.criticalRate = criticalRate;
    }

    public Preferences getPrefs() {
        return prefs;
    }

    public void setPrefs(Preferences prefs) {
        this.prefs = prefs;
    }

    public boolean isFirstPlay() {
        return firstPlay;
    }

    public void setFirstPlay(boolean firstPlay) {
        this.firstPlay = firstPlay;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public List<Integer> getUpgradeLevelList() {
        return upgradeLevelList;
    }

    public void setUpgradeLevelList(List<Integer> upgradeLevelList) {
        this.upgradeLevelList = upgradeLevelList;
    }

    public Long getTotalGameTime() {
        return totalGameTime;
    }

    public void setTotalGameTime(Long totalGameTime) {
        this.totalGameTime = totalGameTime;
    }

    public int getTotalTapNumber() {
        return totalTapNumber;
    }

    public void setTotalTapNumber(int totalTapNumber) {
        this.totalTapNumber = totalTapNumber;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

}
