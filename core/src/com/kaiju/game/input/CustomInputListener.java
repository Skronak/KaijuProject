package com.kaiju.game.input;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.kaiju.game.manager.GameManager;
import com.kaiju.game.screen.PlayScreen;
import com.kaiju.game.utils.Constants;

import java.util.Random;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * Created by Skronak on 24/04/2018.
 */

public class CustomInputListener extends InputListener {
    private Random random;
    private PlayScreen playScreen;
    private GameManager gameManager;

    public CustomInputListener(PlayScreen playScreen){
        this.playScreen = playScreen;
        this.random = new Random();
        this.gameManager = playScreen.getGameManager();
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        this.gameManager.getBattleResultEntity().setTapNumber(gameManager.getBattleResultEntity().getTapNumber()+1);
        this.gameManager.getGameInformation().setTotalTapNumber(this.gameManager.getGameInformation().getTotalTapNumber()+1);
        this.playScreen.processHit(false);
        playScreen.processPointerHitAnimation((int)event.getStageX(), (int)event.getStageY());
        return false;
    }
}
