package reader;

import java.util.Observable;
import java.util.Observer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lsadusr11
 */
public class Console implements Observer {
    
    Line model;

    public Console(Line model) {
        this.model = model;
        model.addObserver(this);
    }
    
    

    @Override
    public void update(Observable line, Object arg1) {
        
        int command = (int) arg1;
        
        switch(command) {
            case Constants.BKSP_BUTTON:
                updateViewBackspaceCharacter();
                break;
            case Constants.DEL_BUTTON:
                updateViewDeleteCharacter();
                break;
            case Constants.FIN_BUTTON:
                updateViewCursorFin();
                break;
            case Constants.HOME_BUTTON:
                updateViewCursorHome();
                break;
            case Constants.LEFT_ARROW:
                updateViewCursorLeft();
                break;
            case Constants.RIGHT_ARROW:
                updateViewCursorRight();
                break;
            case Constants.ADD_CHAR:
                updateViewAddCharacter(model.characterToBeAdded);
                break;
                
        }
        
    }
    
    public void updateViewDeleteCharacter() {
        if (model.cursor < model.currentLine.length() && model.isInsertMode){
            System.out.print("\033[P");
        } else if (model.cursor < model.currentLine.length() && !model.isInsertMode){
            System.out.print(Constants.CURSOR_RIGHT_SEQUENCE);
            System.out.print("\033[P");
            System.out.print(Constants.CURSOR_LEFT_SEQUENCE);
        }
    }
    
    public void updateViewBackspaceCharacter() {
        if (model.cursor > 0) {
            System.out.print(Constants.ESCAPE+"[D");
            System.out.print(Constants.ESCAPE+"[P");
        }
    }
    
      public void updateViewCursorRight() {
        if (model.cursor < model.currentLine.length()) {
            System.out.print(Constants.CURSOR_RIGHT_SEQUENCE);
        }
      }
      
      public void updateViewCursorLeft() {
        if (model.cursor > 0) {
            System.out.print(Constants.CURSOR_LEFT_SEQUENCE);
        }
      }
      
      public void updateViewCursorHome() {
        System.out.print("\033[" + model.cursor + "D");
      }
      
      public void updateViewCursorFin() {
         System.out.print("\033[" + (model.currentLine.length() - model.cursor) + "C");
      }
    
      public void updateViewAddCharacter(char ch) {
         if (model.cursor == model.currentLine.length()|| !model.isInsertMode) {
             System.out.print("\033[@");
         }
         System.out.print(ch);
    }
}
