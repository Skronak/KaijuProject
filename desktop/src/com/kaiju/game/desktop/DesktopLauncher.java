package com.kaiju.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kaiju.game.MyGdxGame;
import com.kaiju.game.utils.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width= Constants.V_WIDTH;
		config.height= Constants.V_HEIGHT;
		new LwjglApplication(new MyGdxGame(true), config);
	}
}
