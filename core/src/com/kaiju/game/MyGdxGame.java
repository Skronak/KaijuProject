package com.kaiju.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.kaiju.game.screen.PlayScreen;
import com.kaiju.game.screen.ResultScreen;
import com.kaiju.game.screen.SplashScreen;
import com.kaiju.game.screen.TitleScreen;

import com.kaiju.game.manager.GameManager;
import com.kaiju.game.screen.UpgradeScreen;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;

/**
 * Created by Skronak on 24/03/2018.
 */
public class MyGdxGame extends Game {

	private PlayScreen playScreen;
	private boolean devMode;


	public MyGdxGame(boolean devMode) {
		this.devMode = devMode;
	}

	@Override
	public void create () {
		if(devMode) {
			Gdx.app.setLogLevel(Application.LOG_DEBUG);
		} else {
			Gdx.app.setLogLevel(Application.LOG_ERROR);
		}
        GameManager gameManager = new GameManager(this);
        playScreen = gameManager.getPlayScreen();
	setScreen(gameManager.getTitleScreen());
    }

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		playScreen.dispose();
	}

    public PlayScreen getPlayScreen() {
        return playScreen;
    }
}
