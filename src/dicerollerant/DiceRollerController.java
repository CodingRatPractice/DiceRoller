/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dicerollerant;

import dicerollerant.Classes.Dice;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 *
 * @author User
 */
public class DiceRollerController implements Initializable {
    
    @FXML
    private Label currentRoll;
    @FXML
    private TextField numberDiceField;
    @FXML
    private Label diceResults;
    @FXML
    private ImageView diceImage;
    
    private Dice currentDice;
    
    @FXML
    private void RollButtonPressed(ActionEvent event) throws InterruptedException {
        
        int numDice = Integer.valueOf(numberDiceField.getText());   
        currentDice.setLabel(currentRoll);
        
        Runnable task = new Runnable(){
            public void run(){
                try {
                    int[] result = currentDice.roll(numDice);
                    
                    Platform.runLater(new Runnable(){
                        public void run(){
                        diceResults.setText(Integer.toString(result[0]));
                    }
                });
                } catch (InterruptedException ex) {
                    Logger.getLogger(DiceRollerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        Thread backgroundThread = new Thread(task);
        backgroundThread.setDaemon(true);
        backgroundThread.start();

        
    }
    
    @FXML
    private void D6Pressed(ActionEvent event){
        setDice(6);
    }
    
    @FXML
    private void D8Pressed(ActionEvent event){
        setDice(8);
    }
    
    @FXML
    private void D10Pressed(ActionEvent event){
        setDice(10);
    }
    
    @FXML
    private void D20Pressed(ActionEvent event){
        setDice(20);
    }
    
    @FXML
    private void D100Pressed(ActionEvent event){
        setDice(100);
    }
    
    public void setDice(int numSides){
        Dice dice = new Dice(numSides);
        this.currentDice = dice;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
