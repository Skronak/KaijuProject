package com.kaiju.game.entity;

import com.kaiju.game.manager.GameManager;

/**
 * Created by gcouturier on 28/03/2018.
 */

public class BattleResultEntity {
    private int tapNumber;
    private float timerLeft;
    private String rank;
    private String gold;
    private String repair;
    private float multiplier;
    private int populationLose;

    public int getPopulationLose() {
        return populationLose;
    }

    public void setPopulationLose(int populationLose) {
        this.populationLose = populationLose;
    }

    public float getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(float multiplier) {
        this.multiplier = multiplier;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getGold() {
        return gold;
    }

    public void setGold(String gold) {
        this.gold = gold;
    }

    public String getRepair() {
        return repair;
    }

    public void setRepair(String repair) {
        this.repair = repair;
    }

    public int getTapNumber() {
        return tapNumber;
    }

    public void setTapNumber(int tapNumber) {
        this.tapNumber = tapNumber;
    }

    public float getTimerLeft() {
        return timerLeft;
    }

    public void setTimerLeft(float timerLeft) {
        this.timerLeft = timerLeft;
    }
}
