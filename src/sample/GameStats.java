package sample; /**
 * CS 1331 Section A3
 * HomeWork 9
 * I worked on this homework alone using only class materials
 */

import java.util.Random;

import javafx.scene.image.Image;


/**
 * This class controls the game process of the slot machine
 * The results and money and calculated in this class
 *
 * @author monwonga3 (Moraa Onwonga)
 * @version 1
 */
public class GameStats {

    /**
     * Gets the grape image from file
     *
     * @return the image of the grape
     */
    private static Image getGrape() {
        Image grapeImage = new Image("images/grape.png", 65,
                70, false, false);
        return grapeImage;
    }

    /**
     * Gets the cherry image from file
     *
     * @return the image of the cherry
     */
    private static Image getCherry() {
        Image cherryImage = new Image("images/cherry.png", 65,
                70, false, false);
        return cherryImage;
    }

    /**
     * Gets the bell image from file
     *
     * @return the image of the bell
     */
    private static Image getBell() {
        Image bellImage = new Image("/images/bell.png", 65,
                70, false, false);
        return bellImage;
    }

    /**
     * Gets the line image from file
     *
     * @return the image of the line
     */
    private static Image getLine() {
        Image lineImage = new Image("/images/line.jpg", 65,
                70, false, false);
        return lineImage;
    }


    //current money of the player
    protected static double currentMoney = 100.00;
    //amount the player is betting
    protected static double bettingAmount = 1.00;
    //whether setting of the game is in regular mode or test mode
    protected static boolean regular = true;
    //the amount of money won
    protected static double moneyWonOrLost;

    private static Image grape = getGrape();
    private static Image cherry = getCherry();
    private static Image bell = getBell();
    private static Image line = getLine();

    protected static boolean winner;


    protected static Image[] slot = new Image[]{cherry, cherry, cherry};


    /**
     * starts the current game
     */
    public static void slotGame() {


        for (int i = 0; i < 30; i++) {
            slot[0] = gameProb();
            slot[1] = gameProb();
            slot[2] = gameProb();
        }

        gameMoney();
    }

    /**
     * The method controls the probability of each value appearing for the slot
     * The probability depends on the setting
     *
     * @return the image for that slot
     */
    private static Image gameProb() {
        Random random = new Random();
        double prob = random.nextDouble();
        if (regular) {


            if (0 < prob && prob <= .125) {
                return cherry;
            } else if (.125 < prob && prob <= .25) {
                return bell;
            } else if (.25 < prob && prob <= .375) {
                return grape;
            } else if (.375 < prob && prob <= 1) {
                return line;
            }


        } else {

            if (0 < prob && prob <= .33) {
                return cherry;
            } else if (.33 < prob && prob <= .66) {
                return bell;
            } else if (.66 < prob && prob <= 1) {
                return grape;
            }

        }

        return line;
    }


    /**
     * This method calculates the amount won/lost
     */
    private static void gameMoney() {

        if (slot[0].equals(bell)
                && slot[1].equals(bell) && slot[2].equals(bell)) {
            currentMoney += bettingAmount * 10;
            moneyWonOrLost = bettingAmount * 10;
            winner = true;
        } else if (slot[0].equals(grape)
                && slot[1].equals(grape) && slot[2].equals(grape)) {
            currentMoney += bettingAmount * 7;
            moneyWonOrLost = bettingAmount * 7;
            winner = true;
        } else if (slot[0].equals(cherry)
                && slot[1].equals(cherry) && slot[2].equals(cherry)) {
            currentMoney += bettingAmount * 5;
            moneyWonOrLost = bettingAmount * 5;
            winner = true;
        } else if (slot[0].equals(cherry) && slot[1].equals(cherry)
                || slot[0].equals(cherry) && slot[2].equals(cherry)
                || slot[1].equals(cherry) && slot[2].equals(cherry)) {
            currentMoney += bettingAmount * 3;
            moneyWonOrLost = bettingAmount * 3;
            winner = true;
        } else if (slot[0].equals(cherry)
                || slot[1].equals(cherry)
                || slot[2].equals(cherry)) {
            currentMoney += bettingAmount * 1;
            moneyWonOrLost = bettingAmount * 1;
            winner = true;
        } else {
            winner = false;
            currentMoney -= bettingAmount;
            moneyWonOrLost = bettingAmount;
        }
    }
}