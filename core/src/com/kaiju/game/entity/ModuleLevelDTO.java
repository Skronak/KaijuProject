package com.kaiju.game.entity;

import com.kaiju.game.utils.ValueDTO;

import java.io.Serializable;

/**
 * Created by Skronak on 17/07/2017.
 * Classe representant l'ensemble des modules du jeu
 *
 */
public class ModuleLevelDTO implements Serializable {

    // Cout du niveau suivant
    private ValueDTO cost;
    // Generation du niveau en cours
    private ValueDTO generation;
    // Sprite
    private String sprite;

//*****************************************************
//                  GETTER & SETTER
// ****************************************************

    public ValueDTO getCost() {
        return cost;
    }

    public void setCost(ValueDTO cost) {
        this.cost = cost;
    }

    public ValueDTO getGeneration() {
        return generation;
    }

    public void setGeneration(ValueDTO generation) {
        this.generation = generation;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }
}
