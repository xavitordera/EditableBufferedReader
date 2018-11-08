/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reader;

import java.util.Observable;
import java.util.logging.Logger;

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
    
    Columns lineColumns = new Columns();
    int auxColumn;
    int currentRow = 1;

    public Line() {
        this.currentLine = new StringBuffer("");
        this.cursor = 0;
    }
    
    public void addCharacter(char ch) {
  
        characterToBeAdded = ch;
        
        try {
            auxColumn = lineColumns.getColumns();
        }catch (Exception ex){
        }
        
            if (cursor == currentLine.length()|| !isInsertMode) {
                currentLine.insert(cursor, ch);

            } else {
                currentLine.setCharAt(cursor, ch);
            }

            notifyChanges(Constants.ADD_CHAR);

            cursor++;
           
    }
    
    public void deleteCharacter() {
        
        if(cursor < currentLine.length() && isInsertMode) {
            notifyChanges(Constants.DEL_BUTTON);
            currentLine.deleteCharAt(cursor);
        }
        
        if (cursor < currentLine.length() - 1 && !isInsertMode){
            notifyChanges(Constants.DEL_BUTTON);
            currentLine.deleteCharAt(cursor + 1);    
        }
    }
    
    public void cursorRight() {
        if (cursor < currentLine.length()) {
            notifyChanges(Constants.RIGHT_ARROW);
            cursor++;
        }
    }
    
    public void cursorLeft() {
        if (cursor > 0) {
            notifyChanges(Constants.LEFT_ARROW);
            cursor--;
        }
    }
    
    public void cursorUp() {
        notifyChanges(Constants.UP_ARROW);
    }
    
    public void cursorDown() {
        notifyChanges(Constants.DOWN_ARROW);
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
            notifyChanges(Constants.BKSP_BUTTON);
            currentLine.deleteCharAt(cursor - 1);
            cursor--;
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
