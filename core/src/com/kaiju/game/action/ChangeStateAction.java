package com.kaiju.game.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.kaiju.game.utils.GameState;

import com.kaiju.game.manager.GameManager;

/**
* Action personnalisee pour changer le
 * gamestate dans une action
*/
public class ChangeStateAction extends Action {
    private GameState gameState;
    private GameManager gameManager;

    public ChangeStateAction(GameManager gameManager, GameState gameState){
        this.gameManager = gameManager;
        this.gameState = gameState;
    }

    @Override
    public boolean act(float delta) {
        gameManager.setCurrentState(gameState);
        return true;
    }
}
