/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dicerollerant.Classes;

import java.util.Random;
import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 *
 * @author User
 */
public class Dice {
    int sides;
    Label dieValue;
    
    public Dice(int sides){
        this.sides = sides;
        this.dieValue = new Label("");
    }
    
    public void setLabel(Label label){
        this.dieValue = label;
    }
    
    public int[] roll(int numTimes) throws InterruptedException{        
        int results[] = new int[numTimes]; 
        
        Random random = new Random();
        int rollTime = random.nextInt(20);
        int i;
        int j;
        int currentRand = 0;
        
        int min = 1;
        int max = this.sides;
        
        for (i=0; i < numTimes; i++){
            for (j=0; j < rollTime; j++){
                
                currentRand = random.nextInt(max-min) + min;

                String stringRand = Integer.toString(currentRand);
                Platform.runLater(new Runnable(){
                    @Override
                   public void run(){
                       dieValue.setText(stringRand);
                   } 
                });
                
                Thread.sleep(100);
            }
            results[i] = currentRand;
        }
        return results;
        
    }
    
    
}
