package com.kaiju.game.manager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.Array;
import com.kaiju.game.MyGdxGame;
import com.kaiju.game.entity.BattleResultEntity;
import com.kaiju.game.entity.GameInformation;
import com.kaiju.game.entity.KaijuEntity;
import com.kaiju.game.screen.EndGameScreen;
import com.kaiju.game.screen.LostScreen;
import com.kaiju.game.screen.PlayScreen;
import com.kaiju.game.screen.ResultScreen;
import com.kaiju.game.screen.TitleScreen;
import com.kaiju.game.utils.GameState;
import com.kaiju.game.utils.LargeMath;

/**
 * Created by Skronak on 30/07/2017.
 *
 * Classe de gestion globale du jeu
 * gerant le lien entre PlayScreen et gameInformation
 */
public class GameManager {
    // Information persistentes
    private GameInformation gameInformation;

    private AssetManager assetManager;

    private PlayScreen playScreen;

    private ResultScreen resultScreen;

    private LostScreen lostScreen;

    private TitleScreen titleScreen;

    private EndGameScreen endGameScreen;

    private LargeMath largeMath;

    private GameState currentState;

    private MyGdxGame game;

    private BattleResultEntity battleResultEntity;

    // Etat du jeu
    public GameManager(MyGdxGame game) {
        this.game = game;
        gameInformation = new GameInformation();
        currentState = GameState.BATTLE;
        assetManager = new AssetManager();
        largeMath = new LargeMath(gameInformation);
        playScreen = new PlayScreen(this);
        resultScreen = new ResultScreen(this);
        titleScreen = new TitleScreen(this);
        lostScreen = new LostScreen(this);
        endGameScreen = new EndGameScreen(this);
    }

    /**
     * Initialise le playscreen
     */
    public void initialiseBattle () {
        if(gameInformation.getCurrentState()>=assetManager.getKaijuEntityList().size()) {
            gameInformation.setCurrentState(0);
        } else {
            gameInformation.setCurrentState(gameInformation.getCurrentState());
        }
        KaijuEntity kaijuEntity = assetManager.getKaijuEntityList().get(gameInformation.getCurrentState());
        if (kaijuEntity.getId().equals("4")) {
            assetManager.getMainMusic().pause();
            assetManager.getEasterMusic().play();
        } else {
            assetManager.getMainMusic().play();
            assetManager.getEasterMusic().stop();
        }

        // Previens ecran de resultat du kaiju a traiter
        resultScreen.setCurrentKaijuEntity(kaijuEntity);
        // Modifie les animations de l'enemy
        Array<TextureRegion> kaijuFrames = new Array<TextureRegion>();
        for (String frame : kaijuEntity.getFrames()) {
            kaijuFrames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/" + frame))));
        }
        Array<TextureRegion> kaijuDeathFrames = new Array<TextureRegion>();
        for (String frame : kaijuEntity.getFramesDeath()) {
            kaijuDeathFrames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/" + frame))));
        }
        playScreen.getEnemy().clearActions();
        playScreen.getEnemy().setIdleAnimation(new Animation(1,kaijuFrames));
        playScreen.getEnemy().setDeathAnimation(new Animation(1, kaijuDeathFrames));
        playScreen.getEnemy().setCurrentAnimation(playScreen.getEnemy().getIdleAnimation());
        playScreen.setHealthMax(kaijuEntity.getHp());
        playScreen.getEnemy().setPosition(kaijuEntity.getPosX(), kaijuEntity.getPosY());
        playScreen.getHud().reinitialiseHud(kaijuEntity.getTime(), kaijuEntity.getName());
        playScreen.getEnemy().setSize(kaijuEntity.getWidth(), kaijuEntity.getHeight());
        playScreen.setCombatTimer(kaijuEntity.getTime());

        battleResultEntity = new BattleResultEntity();
        battleResultEntity.setTapNumber(0);
        battleResultEntity.setTimerLeft(0);
    }

    public void progress(){
        gameInformation.setCurrentState(gameInformation.getCurrentState()+1);
        gameInformation.setGenGoldActive(gameInformation.getGenGoldActive()+1);
        gameInformation.saveInformation();
    }
    /**
     * Affiche le tableau de resultat
     */
    public void switchToResultScreen() {
        Gdx.app.log("switchToResultScreen","switchToResultScreen");
        playScreen.getStage().getRoot().getColor().a = 1;
        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(Actions.fadeOut(1.5f));
        sequenceAction.addAction(Actions.run(new Runnable() {
            @Override
            public void run() {
                game.setScreen(resultScreen);
            }
        }));
        playScreen.getStage().getRoot().addAction(sequenceAction);
    }

    /**
     * Affiche le tableau de resultat
     */
    public void switchToEndScreen() {
        Gdx.app.log("switchToResultScreen","switchToResultScreen");
        resultScreen.getStage().getRoot().getColor().a = 1;
        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(Actions.fadeOut(1.5f));
        sequenceAction.addAction(Actions.run(new Runnable() {
            @Override
            public void run() {
                game.setScreen(endGameScreen);
            }
        }));
        resultScreen.getStage().getRoot().addAction(sequenceAction);
    }

    public void decreasePopulation() {
        int value = gameInformation.getCurrentState() * 5200;
        battleResultEntity.setPopulationLose(value);//sauvegarde la valeur pour l'afficher
        if (value < gameInformation.getPopulation()){
            gameInformation.setPopulation(gameInformation.getPopulation()-value);
        } else {
            switchToLoseScreen();
        }
    }
    /**
     * Affiche le tableau de resultat
     */
    public void switchToLoseScreen() {
        Gdx.app.log("switchToResultScreen","switchToResultScreen");
        playScreen.getStage().getRoot().getColor().a = 1;
        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(Actions.fadeOut(1.5f));
        sequenceAction.addAction(Actions.run(new Runnable() {
            @Override
            public void run() {
                game.setScreen(lostScreen);
            }
        }));
        playScreen.getStage().getRoot().addAction(sequenceAction);
    }

    /**
     * Affiche le jeu
     */
    public void switchToPlayScreenFromStart() {
        Gdx.app.log("switchToPlayScreen","switchToPlayScreen");
        titleScreen.getStage().getRoot().getColor().a = 1;
        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(Actions.fadeOut(1f));
        sequenceAction.addAction(Actions.run(new Runnable() {
            @Override
            public void run() {
                game.setScreen(playScreen);
            }
        }));
        titleScreen.getStage().getRoot().addAction(sequenceAction);
    }

    /**
     * Affiche le jeu
     */
    public void switchToPlayScreen() {

        Gdx.app.log("switchToPlayScreen","switchToPlayScreen");
        resultScreen.getStage().getRoot().getColor().a = 1;
        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(Actions.fadeOut(1f));
        sequenceAction.addAction(Actions.run(new Runnable() {
            @Override
            public void run() {
                game.setScreen(playScreen);
            }
        }));
        resultScreen.getStage().getRoot().addAction(sequenceAction);
    }

    /**
     * Affiche le tableau de resultat
     */
    public void switchToTitleScreen() {
        Gdx.app.log("switchToTitleScreen","switchToTitleScreen");
        lostScreen.getStage().getRoot().getColor().a = 1;
        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(Actions.fadeOut(1f));
        sequenceAction.addAction(Actions.run(new Runnable() {
            @Override
            public void run() {
                game.setScreen(titleScreen);
            }
        }));
        lostScreen.getStage().getRoot().addAction(sequenceAction);
    }

    public void resetGame(){
        gameInformation.setCurrentState(0);
        gameInformation.setGenGoldActive(2);
        gameInformation.setCurrentGold(0);
        gameInformation.setCurrency(0);
        gameInformation.setDamage(2);
        gameInformation.setGenCurrencyActive(0);
        gameInformation.setCriticalRate(5);
        gameInformation.setTotalTapNumber(0);
        gameInformation.setTotalGameTime(0l);
        gameInformation.setFirstPlay(true);
        gameInformation.setPopulation(1000000);
    }
    /**
     * Affiche le tableau de resultat
     */
    public void switchToTitleScreenFromEnd() {
        Gdx.app.log("switchToTitleScreen","switchToTitleScreen");
        resetGame();
        endGameScreen.getStage().getRoot().getColor().a = 1;
        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(Actions.fadeOut(1f));
        sequenceAction.addAction(Actions.run(new Runnable() {
            @Override
            public void run() {
                game.setScreen(titleScreen);
            }
        }));
        endGameScreen.getStage().getRoot().addAction(sequenceAction);
    }

    public void initResultEntity() {
        float durationPercent = (battleResultEntity.getTimerLeft()/resultScreen.getCurrentKaijuEntity().getTime());
        float goldValue=0f;
        if (durationPercent > 0.9f && battleResultEntity.getPopulationLose()==0) {
            battleResultEntity.setRank("SS");
            battleResultEntity.setMultiplier(3);
        } else if (durationPercent > 0.8f && battleResultEntity.getPopulationLose() < 10000) {
            battleResultEntity.setRank("S");
            battleResultEntity.setMultiplier(2.5f);
        } else if (durationPercent > 0.7f &&battleResultEntity.getPopulationLose() < 20000) {
            battleResultEntity.setRank("AAA");
            battleResultEntity.setMultiplier(2);
        } else if (durationPercent > 0.6f && battleResultEntity.getPopulationLose() < 50000) {
            battleResultEntity.setRank("AA");
            battleResultEntity.setMultiplier(1.75f);
        } else if (durationPercent > 0.5f && battleResultEntity.getPopulationLose() < 1000000) {
            battleResultEntity.setRank("A");
            battleResultEntity.setMultiplier(1.5f);
        } else if (durationPercent > 0.5f) {
            battleResultEntity.setMultiplier(1f);
            battleResultEntity.setRank("B");
        } else {
            battleResultEntity.setMultiplier(.5f);
            battleResultEntity.setRank("C");
        }
        goldValue=(resultScreen.getCurrentKaijuEntity().getGold()*battleResultEntity.getMultiplier());
        battleResultEntity.setGold(String.valueOf((int) goldValue)); // Pas de centimes ici
        gameInformation.setCurrentGold(goldValue);
    }

//*****************************************************
//                  GETTER & SETTER
// ****************************************************


    public GameInformation getGameInformation() {
        return gameInformation;
    }

    public void setGameInformation(GameInformation gameInformation) {
        this.gameInformation = gameInformation;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public PlayScreen getPlayScreen() {
        return playScreen;
    }

    public void setPlayScreen(PlayScreen playScreen) {
        this.playScreen = playScreen;
    }

    public LargeMath getLargeMath() {
        return largeMath;
    }

    public void setLargeMath(LargeMath largeMath) {
        this.largeMath = largeMath;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }

    public MyGdxGame getGame() {
        return game;
    }

    public void setGame(MyGdxGame game) {
        this.game = game;
    }

    public ResultScreen getResultScreen() {
        return resultScreen;
    }

    public void setResultScreen(ResultScreen resultScreen) {
        this.resultScreen = resultScreen;
    }

    public TitleScreen getTitleScreen() {
        return titleScreen;
    }

    public void setTitleScreen(TitleScreen titleScreen) {
        this.titleScreen = titleScreen;
    }

    public BattleResultEntity getBattleResultEntity() {
        return battleResultEntity;
    }

    public void setBattleResultEntity(BattleResultEntity battleResultEntity) {
        this.battleResultEntity = battleResultEntity;
    }

    public LostScreen getLostScreen() {
        return lostScreen;
    }

    public EndGameScreen getEndGameScreen() {
        return endGameScreen;
    }
}
