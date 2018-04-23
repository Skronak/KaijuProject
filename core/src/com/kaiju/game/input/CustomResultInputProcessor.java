package com.kaiju.game.input;

import com.badlogic.gdx.InputProcessor;

import com.kaiju.game.manager.GameManager;

/**
 * Created by Skronak on 29/01/2017.
 * Listener des input sur le ResultScreen
 */
public class CustomResultInputProcessor implements InputProcessor {

    private GameManager gameManager;

    public CustomResultInputProcessor(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public boolean keyDown(int keycode) { return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        gameManager.switchToCustomiseScreen();
        if(gameManager.getGameInformation().getCurrentState()>=gameManager.getAssetManager().getKaijuEntityList().size()) {
            gameManager.switchToEndScreen();
        } else {
            gameManager.switchToPlayScreen();
        }
    return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
