package com.kaiju.game.utils;

import com.badlogic.gdx.Gdx;
import com.kaiju.game.entity.GameInformation;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Skronak on 17/08/2017.
 * Classe utilitaire pour gerer
 * les tres larges montants du jeu
 */
public class LargeMath {

    private DecimalFormat decimalFormat;

    private GameInformation gameInformation;

    public LargeMath(GameInformation gameInformation) {
        this.gameInformation = gameInformation;
        decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.FRENCH);
        decimalFormat.applyPattern("##.#");
    }

    // Transforme index lettre en lettre
    // TODO voir perf sinon liste
    // String[] list = {"","A","B","C","D","E","F","G","H","I","J","K","L","M","N","AA","AB","AC","AD","AE","AF","AG","AH","AI","AJ","AK","AL","AM","B","BA","BB","BC","BD","BE","BF","BG","BH","BI","BJ","BK","BL","BM","C","CA","CB","CC","CD","CE","CF","CG","CH","CI","CJ","CK","CL","CM","D","DA","DB","DC","DD","DE","DF","DG","DH","DI","DJ","DK","DL","DM","E","EA","EB","EC","ED","EE","EF","EG","EH","EI","EJ","EK","EL","EM","F","FA","FB","FC","FD","FE","FF","FG","FH","FI","FJ","FK","FL","FM","G","GA","GB","GC","GD","GE","GF","GG","GH","GI","GJ","GK","GL","GM","H","HA","HB","HC","HD","HE","HF","HG","HH","HI","HJ","HK","HL","HM","I","IA","IB","IC","ID","IE","IF","IG","IH","II","IJ","IK","IL","IM","J","JA","JB","JC","JD","JE","JF","JG","JH","JI","JJ","JK","JL","JM","K","KA","KB","KC","KD","KE","KF","KG","KH","KI","KJ","KK","KL","KM","L","LA","LB","LC","LD","LE","LF","LG","LH","LI","LJ","LK","LL","LM","M","MA","MB","MC","MD","ME","MF","MG","MH","MI","MJ","MK","ML","MM","N","NA","NB","NC","ND","NE","NF","NG","NH","NI","NJ","NK","NL","NM","NA","NAA","NAB","NAC","NAD","NAE","NAF","NAG","NAH","NAI","NAJ","NAK","NAL","NAM","NA","NAA","NAB","NAC","NAD","NAE","NAF","NAG","NAH","NAI","NAJ","NAK","NAL","NAM","NA","NAA","NAB","NAC","NAD","NAE","NAF","NAG","NAH","NAI","NAJ","NAK","NAL","NAM","NA","NAA","NAB","NAC","NAD","NAE","NAF","NAG","NAH","NAI","NAJ","NAK","NAL","NAM","NA","NAA","NAB","NAC","NAD","NAE","NAF","NAG","NAH","NAI","NAJ","NAK","NAL","NAM","NA","NAA","NAB","NAC","NAD","NAE","NAF","NAG","NAH","NAI","NAJ","NAK","NAL","NAM","NA","NAA","NAB","NAC","NAD","NAE","NAF","NAG","NAH","NAI","NAJ","NAK","NAL","NAM","NA","NAA","NAB","NAC","NAD","NAE","NAF","NAG","NAH","NAI","NAJ","NAK","NAL","NAM","NA","NAA","NAB","NAC","NAD","NAE","NAF","NAG","NAH","NAI","NAJ","NAK","NAL","NAM","NA","NAA","NAB","NAC","NAD","NAE","NAF","NAG","NAH","NAI","NAJ","NAK","NAL","NAM","NA","NAA","NAB","NAC","NAD","NAE","NAF","NAG","NAH","NAI","NAJ","NAK","NAL","NAM","NA","NAA","NAB","NAC","NAD","NAE","NAF","NAG","NAH","NAI","NAJ","NAK","NAL","NAM","NA","NAA","NAB","NAC","NAD","NAE","NAF","NAG","NAH","NAI","NAJ","NAK","NAL","NAM","NB","NBA","NBB","NBC","NBD","NBE","NBF","NBG","NBH","NBI","NBJ","NBK","NBL","NBM","NB","NBA","NBB","NBC","NBD","NBE","NBF","NBG","NBH","NBI","NBJ","NBK","NBL","NBM","NB","NBA","NBB","NBC","NBD","NBE","NBF","NBG","NBH","NBI","NBJ","NBK","NBL","NBM","NB","NBA","NBB","NBC","NBD","NBE","NBF","NBG","NBH","NBI","NBJ","NBK","NBL","NBM","NB","NBA","NBB","NBC","NBD","NBE","NBF","NBG","NBH","NBI","NBJ","NBK","NBL","NBM","NB","NBA","NBB","NBC","NBD","NBE","NBF","NBG","NBH","NBI","NBJ","NBK","NBL","NBM","NB","NBA","NBB","NBC","NBD","NBE","NBF","NBG","NBH","NBI","NBJ","NBK","NBL","NBM","NB","NBA","NBB","NBC","NBD","NBE","NBF","NBG","NBH","NBI","NBJ","NBK","NBL","NBM","NB","NBA","NBB","NBC","NBD","NBE","NBF","NBG","NBH","NBI","NBJ","NBK","NBL","NBM","NB","NBA","NBB","NBC","NBD","NBE","NBF","NBG","NBH","NBI","NBJ","NBK","NBL","NBM","NB","NBA","NBB","NBC","NBD","NBE","NBF","NBG","NBH","NBI","NBJ","NBK","NBL","NBM","NB","NBA","NBB","NBC","NBD","NBE","NBF","NBG","NBH","NBI","NBJ","NBK","NBL","NBM","NB","NBA","NBB","NBC","NBD","NBE","NBF","NBG","NBH","NBI","NBJ","NBK","NBL","NBM","NB","NBA","NBB","NBC","NBD","NBE","NBF","NBG","NBH","NBI","NBJ","NBK","NBL","NBM","NC","NCA","NCB","NCC","NCD","NCE","NCF","NCG","NCH","NCI","NCJ","NCK","NCL","NCM","NC","NCA","NCB","NCC","NCD","NCE","NCF","NCG","NCH","NCI","NCJ","NCK","NCL","NCM","NC","NCA","NCB","NCC","NCD","NCE","NCF","NCG","NCH","NCI","NCJ","NCK","NCL","NCM","NC","NCA","NCB","NCC","NCD","NCE","NCF","NCG","NCH","NCI","NCJ","NCK","NCL","NCM","NC","NCA","NCB","NCC","NCD","NCE","NCF","NCG","NCH","NCI","NCJ","NCK","NCL","NCM","NC","NCA","NCB","NCC","NCD","NCE","NCF","NCG","NCH","NCI","NCJ","NCK","NCL","NCM","NC","NCA","NCB","NCC","NCD","NCE","NCF","NCG","NCH","NCI","NCJ","NCK","NCL","NCM","NC","NCA","NCB","NCC","NCD","NCE","NCF","NCG","NCH","NCI","NCJ","NCK","NCL","NCM","NC","NCA","NCB","NCC","NCD","NCE","NCF","NCG","NCH","NCI","NCJ","NCK","NCL","NCM","NC","NCA","NCB","NCC","NCD","NCE","NCF","NCG","NCH","NCI","NCJ","NCK","NCL"};
    public String printLetter(int currency) {
        String currencyString = "";
        int currencyLast = 0;

        if (currency ==0) {
            return currencyString;
        }

        // cas multi lettres
        if (currency > Constants.CURRENCY_MAX_LETTER_INDEX) {
            currencyLast = currency%Constants.CURRENCY_MAX_LETTER_INDEX;

            while (currency > Constants.CURRENCY_MAX_LETTER_INDEX) {
                currency = (int) currency/Constants.CURRENCY_MAX_LETTER_INDEX;
                if (currency >= Constants.CURRENCY_MAX_LETTER_INDEX) {
                    currencyString += String.valueOf(getLetter(Constants.CURRENCY_MAX_LETTER_INDEX));
                } else {
                    currencyString += String.valueOf(getLetter(currency));
                }
            }
//    	Calcule derniere lettre
            if (currencyLast >0) {
                currencyString += String.valueOf(getLetter(currencyLast));
            }
        } else {
            currencyString += getLetter(currency);
        }
        return currencyString;
    }

    /**
     * Format les valeurs de gameInformation pour
     * la sauvegarde
     * Similaire a adjustCurrency
     */
    public void formatGameInformation(){
        float value=gameInformation.getCurrentGold();
        int currency=gameInformation.getCurrency();
        while(value>=1000) {
            value=value/1000;
            currency+=1;
        }
        gameInformation.setCurrentGold(value);
        gameInformation.setCurrency(currency);
        //TODO formater pour que virgule ne passe pas la limite du systeme
    }

    /**
     * Divise la valeur et augmente la currency pour rester dans les limites du systeme
     * @param value
     * @param currency
     * @return
     */
    public ValueDTO adjustCurrency(Float value, int currency) {
        while(value>=1000) {
            value=value/1000;
            currency+=1;
        }
        return new ValueDTO(value, currency);
    }

    // Retourne la valeur afficher
    public String getDisplayValue(Float value, int currency) {
        ValueDTO valueDto = adjustCurrency(value, currency);
        return (decimalFormat.format(valueDto.getValue()) + printLetter(valueDto.getCurrency()));
    }

    public String getDisplayValue(int value) {
        return (decimalFormat.format(value));
    }

    //    formatGameInformation();

    /**
     * Renvoie la lettre Majuscule associee a l'index passe en parametre
     * @param i
     * @return
     */
    private char getLetter(int i)
    {
        return (char) (i + 64);
    }

    public DecimalFormat getDecimalFormat() {
        return decimalFormat;
    }
}

