package com.kaiju.game.utils;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.kaiju.game.entity.KaijuEntity;

import com.kaiju.game.manager.GameManager;

/**
 * Created by Skronak on 21/08/2017.
* TODO a recharger en live
 */
public class FightResultMenu extends AbstractMenu {

    private Label titleLabel;
    private Label kaijuClassLabel;
    private Label kaijuNameLabel;
    private Label timerLabel;
    private Label hitLabel;
    private KaijuEntity kaijuEntity;
    private Label noteLabel;
    private Label goldLabel;

    public FightResultMenu(GameManager gameManager) {
        super(gameManager);
        customizeMenuTable();
    }

    public void customizeMenuTable() {
        titleLabel = new Label("BATTLE REPORT",skin);
        titleLabel.setFontScale(1.5f);
        kaijuClassLabel = new Label("", skin);
        kaijuNameLabel = new Label("", skin);
        hitLabel = new Label("", skin);
        timerLabel = new Label("", skin);
        goldLabel = new Label("",skin);
        goldLabel.setFontScale(2);
        goldLabel.setColor(gameManager.getAssetManager().getResultTextColor());
        noteLabel = new Label("",skin);
        noteLabel.setFontScale(2);

        parentTable.setVisible(true);
        parentTable.add(new Image(gameManager.getAssetManager().getResultMenuBar())).colspan(2).top();
        parentTable.row();
        parentTable.add(titleLabel).colspan(2);
        parentTable.row();
        parentTable.add(new Image(gameManager.getAssetManager().getResultMenuBar())).colspan(2).padBottom(20);
        parentTable.row();
        parentTable.add(kaijuNameLabel).left().pad(5);
        parentTable.row();
        parentTable.add(kaijuClassLabel).left().pad(5);
        parentTable.row();
        parentTable.add(timerLabel).left().pad(5);
        parentTable.row();
        parentTable.add(hitLabel).left().pad(5);
        parentTable.row();
        parentTable.add(noteLabel).pad(20);
        parentTable.row();
        parentTable.add(new Image(gameManager.getAssetManager().getResultMenuBar())).padTop(20).colspan(2);
        parentTable.row();
        parentTable.add(goldLabel).right();
        parentTable.row();
        parentTable.add(new Image(gameManager.getAssetManager().getResultMenuBar())).colspan(2);
    }

    /**
     * Initialise le tableau avec les valeurs du combat
     * // TODO rank => enum
     * si val enum = x, alors affiche label avec couleur specifique
     */
    public void initTable() {

        kaijuClassLabel.setText("Category: "+kaijuEntity.getCategory());
        kaijuNameLabel.setText("Name: "+kaijuEntity.getName());
        hitLabel.setText(String.valueOf("Hit number: "+gameManager.getBattleResultEntity().getTapNumber()));
        timerLabel.setText("Duration: "+ gameManager.getLargeMath().getDecimalFormat().format(kaijuEntity.getTime() - gameManager.getBattleResultEntity().getTimerLeft())+"s");
        noteLabel.setText("RANK "+gameManager.getBattleResultEntity().getRank());
        goldLabel.setText("+ "+String.valueOf(gameManager.getBattleResultEntity().getGold()+ " Gold"));
        if (gameManager.getBattleResultEntity().getMultiplier()>0) {
            goldLabel.setText(goldLabel.getText().append(" (X"+gameManager.getBattleResultEntity().getMultiplier()+")"));
        }

    }

    public void update() {
    }

//*****************************************************
//                  GETTER & SETTER
// ****************************************************


    public Label getTitleLabel() {
        return titleLabel;
    }

    public void setTitleLabel(Label titleLabel) {
        this.titleLabel = titleLabel;
    }

    public Label getKaijuClassLabel() {
        return kaijuClassLabel;
    }

    public void setKaijuClassLabel(Label kaijuClassLabel) {
        this.kaijuClassLabel = kaijuClassLabel;
    }

    public Label getKaijuNameLabel() {
        return kaijuNameLabel;
    }

    public void setKaijuNameLabel(Label kaijuNameLabel) {
        this.kaijuNameLabel = kaijuNameLabel;
    }

    public Label getTimerLabel() {
        return timerLabel;
    }

    public void setTimerLabel(Label timerLabel) {
        this.timerLabel = timerLabel;
    }

    public Label getHitLabel() {
        return hitLabel;
    }

    public void setHitLabel(Label hitLabel) {
        this.hitLabel = hitLabel;
    }

    public KaijuEntity getKaijuEntity() {
        return kaijuEntity;
    }

    public void setKaijuEntity(KaijuEntity kaijuEntity) {
        this.kaijuEntity = kaijuEntity;
    }

}
