package sample; /**
 * CS 1331 Section A3
 * HomeWork 9
 * I worked on this homework alone using only class materials
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;

import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Background;

/**
 * This class creates the slot game gui
 *
 * @author monwonga3 (Moraa Onwonga)
 * @version 1
 */

public class Slot extends Application {
    protected DecimalFormat df = new DecimalFormat("0.##");
    protected Text currMoneyText = new Text(50, 50,
            "Current Money Holdings: $"
                    + df.format(GameStats.currentMoney));

    protected Text moneyLostOrWonText = new Text(70, 70, "");

    protected Text betText = new Text(50, 50, "Betting Amount: $"
            + df.format(GameStats.bettingAmount));
    protected GridPane grid = new GridPane();

    /**
     * This method sets the ordering of elements in the gui
     * The images are on a gridpane
     * The regulars are on a VBox that is added to the gridPane
     *
     * @return the gridPane
     */
    protected GridPane getPane() {
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        Image slot = new Image("/images/slot.jpg", 500, 650,
                false, false);

        Image winner = new Image("/images/winner.gif", 275, 75,
                false, false);

        Image loser = new Image("/images/loser.jpg", 275, 75,
                false, false);

        Image grape = new Image("/images/grape.png", 65, 70,
                false, false);

        Image cherry = new Image("/images/cherry.png", 65, 70,
                false, false);

        Image bell = new Image("/images/bell.png", 65, 70,
                false, false);

        Image line = new Image("/images/line.jpg", 65, 70,
                false, false);

        Image spin1 = new Image("/images/spin.png", 285, 60,
                false, false);

        Image back = new Image("/images/back.gif", 550, 60,
                false, false);

        Image jackpot = new Image("/images/jackpot.gif", 500, 150,
                false, false);
        //add images to grid
        grid.add(new ImageView(slot), 0, 0, 10, 45);
        Button spin = new Button();
        spin.setGraphic(new ImageView(spin1));
        grid.add(spin, 10, 27, 50, 1); //Spin Button
        grid.add(new ImageView(grape), 16, 18); //slot3
        grid.add(new ImageView(cherry), 15, 18); //slot2
        grid.add(new ImageView(bell), 14, 18); //slot1
        grid.add(new ImageView(winner), 11, 11, 80, 1);
        //Settings Pane
        VBox paneForSettings = new VBox(20);
        paneForSettings.setPadding(new Insets(5, 5, 5, 5));
        //Radio Buttons
        RadioButton regular = new RadioButton("Regular");
        RadioButton test = new RadioButton("Test");
        //Text Box Initiation
        Label tfLabel = new Label("Enter Betting Amount from $1 to $"
                + df.format(GameStats.currentMoney)
                + ":      ");
        TextField tf = new TextField() {
            @Override
            public void replaceText(int start, int end, String text) {
                if (!text.matches("[^\\d.]")) {
                    super.replaceText(start, end, text);
                }
            }

            @Override
            public void replaceSelection(String text) {
                if (!text.matches("[^\\d.]")) {
                    super.replaceSelection(text);
                }
            }
        };
        BackgroundSize backgroundSizetf
                = new BackgroundSize(100, 100, true, true,
                true, false);
        BackgroundImage backgroundImagetf
                = new BackgroundImage(back, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, backgroundSizetf);
        Background backgroundtf = new Background(backgroundImagetf);
        tf.setPromptText("Enter Betting Amount (minimum 1.00)");
        tf.setBackground(backgroundtf);
        //Adding elements to Settings Panel
        paneForSettings.getChildren().addAll(
                new ImageView(jackpot), currMoneyText,
                betText, tfLabel, tf, regular, test, moneyLostOrWonText);
        //Radio Button Settings
        ToggleGroup group = new ToggleGroup();
        regular.setToggleGroup(group);
        test.setToggleGroup(group);
        regular.setOnAction(e -> {
            if (regular.isSelected()) {
                GameStats.regular = true;
            }
        });
        test.setOnAction(e -> {
            if (test.isSelected()) {
                GameStats.regular = false;
            }
        });
        //textfield Settings
        tf.setOnAction(e -> {
            if ((tf.getText()).equals("")) {
                GameStats.bettingAmount = 0;
                betText.setText("Betting Amount: $"
                        + df.format(GameStats.bettingAmount));
            } else if (0 > Double.parseDouble(tf.getText())
                    || GameStats.currentMoney
                    < Double.parseDouble(tf.getText())) {
                GameStats.bettingAmount = 0;
                betText.setText("Betting Amount: Enter Valid Amount");
            } else {
                GameStats.bettingAmount
                        = Double.parseDouble(tf.getText());
                betText.setText("Betting Amount: $"
                        + df.format(GameStats.bettingAmount));
            }
        });
        //Spin Button Settings
        spin.setOnAction(e -> {
            //Check for correct Input
            if ((tf.getText()).equals("")) {
                GameStats.bettingAmount = 0;
                betText.setText("Betting Amount: Enter Input"
                        + df.format(GameStats.bettingAmount));
            } else if (1 > Double.parseDouble(tf.getText())
                    || GameStats.currentMoney
                    < Double.parseDouble(tf.getText())) {
                GameStats.bettingAmount = 0;
                betText.setText("Betting Amount: Enter Valid Amount");
            } else {
                GameStats.bettingAmount
                        = Double.parseDouble(tf.getText());
                betText.setText("Betting Amount: $"
                        + df.format(GameStats.bettingAmount));
            }
            //Update images in slot and player's money
            if (GameStats.bettingAmount >= 1
                    && GameStats.bettingAmount <= GameStats.currentMoney) {
                GameStats.slotGame();
                grid.add(new ImageView(GameStats.slot[2]), 16, 18);
                grid.add(new ImageView(GameStats.slot[1]), 15, 18);
                grid.add(new ImageView(GameStats.slot[0]), 14, 18);
                currMoneyText.setText("Current Money Holdings: $"
                        + df.format(GameStats.currentMoney));
                tf.setText("");
            } else {
                tf.setText("");
            }
            if (GameStats.winner) {
                grid.add(new ImageView(winner), 11, 11, 80, 1);
                moneyLostOrWonText.setText("Congratulations you won: $"
                        + df.format(GameStats.moneyWonOrLost) + "!");
            } else {
                grid.add(new ImageView(loser), 11, 11, 80, 1);
                moneyLostOrWonText.setText("Sorry you lost: $"
                        + df.format(GameStats.moneyWonOrLost) + "!");
            }
            tfLabel.setText("Enter Betting Amount from $1 to $"
                    + df.format(GameStats.currentMoney) + ":      ");

        });
        grid.add(paneForSettings, 31, 0, 1000, 1000);
        return grid;
    }

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        Scene scene = new Scene(getPane(), 500, 250);
        primaryStage.setTitle("Slot Machine"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        primaryStage.show(); // Display the stage
    }

    /**
     * This is the launcher for the gui
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}