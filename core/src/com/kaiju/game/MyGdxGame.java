package com.kaiju.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.kaiju.game.screen.CustomiseScreen;
import com.kaiju.game.screen.PlayScreen;
import com.kaiju.game.screen.ResultScreen;
import com.kaiju.game.screen.SplashScreen;
import com.kaiju.game.screen.TitleScreen;

import com.kaiju.game.manager.GameManager;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;

/**
 * TODO chargement avec asssetmanager
 */
public class MyGdxGame extends Game {

	private PlayScreen playScreen;
	private SplashScreen splashScreen;
    private TitleScreen titleScreen;
	private boolean devMode;
    private ResultScreen resultScreen;
    private CustomiseScreen customiseScreen;


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
        resultScreen = gameManager.getResultScreen();
        playScreen = gameManager.getPlayScreen();
        titleScreen = gameManager.getTitleScreen();
        customiseScreen = gameManager.getCustomiseScreen();

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
