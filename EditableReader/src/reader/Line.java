/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reader;

import java.util.Observable;

/**
 *
 * @author lsadusr11
 */
public class Line extends Observable {
    
    public int cursor;
    public boolean isInsertMode = false;
    StringBuffer currentLine;
    String begin, end;
    char characterToBeAdded;

    public Line() {
        this.currentLine = new StringBuffer("");
        this.cursor = 0;
    }
    
    public void addCharacter(char ch) {
  
        characterToBeAdded = ch;
        
        if (cursor == currentLine.length()|| !isInsertMode) {
            currentLine.insert(cursor, ch);
            
        } else {
            currentLine.setCharAt(cursor, ch);
        }
        
       notifyChanges(Constants.ADD_CHAR);
        
        cursor++;
        
    }
    
    public void deleteCharacter() {
        if (cursor < currentLine.length()){
            if (isInsertMode) {
                currentLine.deleteCharAt(cursor);
            }
            else{
                currentLine.deleteCharAt(cursor + 1);
            }
            notifyChanges(Constants.DEL_BUTTON);
        }
    }
    
    public void cursorRight() {
        if (cursor < currentLine.length()) {
            cursor++;
            notifyChanges(Constants.RIGHT_ARROW);
        }
    }
    
    public void cursorLeft() {
        if (cursor > 0) {
            notifyChanges(Constants.LEFT_ARROW);
            cursor--;
        }
    }
    
    public void cursorHome() {
        notifyChanges(Constants.HOME_BUTTON);
        cursor = 0;
    }
    
    public void cursorFin() {
        notifyChanges(Constants.FIN_BUTTON);        
        cursor = currentLine.length();
    }
    
    public void backspaceCharacter() {
        if (cursor > 0) {
            currentLine.deleteCharAt(cursor - 1);
            cursor--;
            notifyChanges(Constants.BKSP_BUTTON);
        }
    }
    
    public void insertMode() {
        isInsertMode = !isInsertMode;
    }
 
    void notifyChanges(int command) {
        setChanged();
        notifyObservers(command);
    }
    
}
