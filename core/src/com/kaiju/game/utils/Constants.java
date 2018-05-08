package com.kaiju.game.utils;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by Skronak on 29/01/2017.
 */
public class Constants {

    public static final String CURRENT_VERSION = "build v0.6";

    // Taille virtuelle verticale de l'application
    public static final int V_WIDTH = 320; //320

    // Taille virtuelle horizontale de l'application
    public static final int V_HEIGHT = 570; //570

    // Delay pour generation coffre rattrapage gold offline
    public static int DELAY_AUTOSAVE = 300;

    // Taille du texte du HUD
    public static int HUD_TEXT_SIZE = 40;

    // Style du font utilise
    public static String FONT_STYLE = "stocky";

    // Multiplicateur critique
    public static final int CRITICAL_MULIPLIER = 5;

    // chance d'avoir un crit (1/10)
    public static int CRITICAL_CHANCE = 20;

    public static Color CRITICAL_LABEL_COLOR = Color.ROYAL;

    public static Color NORMAL_LABEL_COLOR = Color.WHITE;

    public static Color BAD_LABEL_COLOR = Color.RED;

    public static float UPDATE_MENU_PAD_EXTERNAL_HEIGHT = 20;

    public static float UPDATE_MENU_PAD_EXTERNAL_WIDTH = 20;

    public static int UPDATE_MENU_PAD_INTERNAL = 5;

    public static int PLAYSCREEN_MENU_BUTTON_HEIGHT = 60;

    public static int PLAYSCREEN_MENU_HEIGHT = 100;

    public static float[] RESULT_LABEL_COLOR = {255,102,0};

    // Index de la lettre maximale atteignable
    public static int CURRENCY_MAX_LETTER_INDEX= 14;

}
