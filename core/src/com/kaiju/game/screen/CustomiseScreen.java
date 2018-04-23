package com.kaiju.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kaiju.game.entity.ModuleElementMenu;
import com.kaiju.game.manager.GameManager;
import com.kaiju.game.manager.ModuleManager;
import com.kaiju.game.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.Gdx.files;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

/**
 * Created by Skronak on 24/03/2018.
 */

public class CustomiseScreen extends AbstractScreen {

    private Stage stage;
    private OrthographicCamera camera;
    private Viewport viewport;
    private GameManager gameManager;
    private Table parentTable;
    private Label detailGold;
    private Label detailDescription;
    private Label detailLevel;
    private Label detailTitre;
    private ModuleManager moduleManager;
    private Stack stack;
    private Label moduleLevelLabel;
    private TextButton buyButton;
    private Table moduleDetails;
    // indique le skill actuellement selectionne
    private int currentSelection;
    private List<ImageButton> moduleButtonList;
    private VerticalGroup scrollContainerVG;

    public CustomiseScreen(final GameManager gameManager) {
        this.gameManager=gameManager;

        Image backgroundImage = new Image(new Texture(files.internal("image/background.jpg")));

        backgroundImage.setSize(Constants.V_WIDTH,Constants.V_HEIGHT);

        camera = new OrthographicCamera(Constants.V_WIDTH, Constants.V_HEIGHT);
        viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);
        stage = new Stage(viewport);

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        parentTable = new Table();
    }

    @Override
    public void show() {
        // fadeIn
        stage.getRoot().getColor().a = 0;
        stage.getRoot().addAction(fadeIn(0.5f));
        Gdx.app.log("CustomiseScreen","show");

        Gdx.input.setInputProcessor(stage);

        // titre
        parentTable.add(new Label("UPGRADE MECHA", gameManager.getAssetManager().getSkin())).colspan(2).height(50);
        parentTable.row();

        // Contenu
//        stack = new Stack();
//        stack.addActor(initScrollingModuleSelection());

        parentTable.add(initScrollingModuleSelection());
        parentTable.debug();
    }

    /**
     * Methode d'initialisation des modules disponibles a
     * l'upgrade
     *
     * @return
     */
    public ScrollPane initScrollingModuleSelection() {
        scrollContainerVG = new VerticalGroup();

        scrollContainerVG.space(5f);
        ScrollPane.ScrollPaneStyle paneStyle = new ScrollPane.ScrollPaneStyle();
        paneStyle.hScroll = paneStyle.hScrollKnob = paneStyle.vScroll = paneStyle.vScrollKnob;

        ScrollPane pane = new ScrollPane(scrollContainerVG, paneStyle);
        pane.setScrollingDisabled(true, false);

        // Definition drawables possibles pour les boutons
        moduleButtonList = new ArrayList<ImageButton>();

        for (int i = 0; i < gameManager.getAssetManager().getModuleElementList().size(); i++) {
            ModuleElementMenu moduleElementMenu = new ModuleElementMenu(gameManager);
            moduleElementMenu.initModuleElementMenu(i);
            scrollContainerVG.addActor(moduleElementMenu);
        }
        Gdx.app.log("ModuleMenu", "Generation des boutons de Module terminee");

        return pane;
    }
    @Override
    public void render(float delta) {
        camera.update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
