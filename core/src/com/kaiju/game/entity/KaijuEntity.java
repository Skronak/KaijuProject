package com.kaiju.game.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Skronak on 25/03/2018.
 */
public class KaijuEntity implements Serializable {
    private String id;
    private String name;
    private String category;
    private int hp;
    private int time;
    private int posX;
    private int posY;
    private int width;
    private int height;
    private int gold;
    private int masse;
    private List<String> frames;
    private List<String> framesDeath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<String> getFrames() {
        return frames;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getMasse() {
        return masse;
    }

    public void setMasse(int masse) {
        this.masse = masse;
    }

    public void setFrames(List<String> frames) {
        this.frames = frames;
    }

    public List<String> getFramesDeath() {
        return framesDeath;
    }

    public void setFramesDeath(List<String> framesDeath) {
        this.framesDeath = framesDeath;
    }
}


