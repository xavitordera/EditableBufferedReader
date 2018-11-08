/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reader;

/**
 *
 * @author lsadusr11
 */
public class Line {
    
    public int cursor;
    public boolean isInsertMode = false;
    StringBuffer currentLine;
    String begin, end;

    public Line() {
        this.currentLine = new StringBuffer("");
        this.cursor = 0;
    }
    
    public void addCharacter(char ch) {
  
        if (cursor == currentLine.length()|| !isInsertMode) {
            currentLine.insert(cursor, ch);
            
        } else {
            currentLine.setCharAt(cursor, ch);
        }
        cursor++;
        
    }
    
    public void deleteCharacter() {
        
        if(cursor < currentLine.length() && isInsertMode) {
            currentLine.deleteCharAt(cursor);
        }
        
        if (cursor < currentLine.length() - 1 && !isInsertMode){
                currentLine.deleteCharAt(cursor + 1);
        }
    }
    
    public void cursorRight() {
        if (cursor < currentLine.length()) {
            cursor++;
        }
    }
    
    public void cursorLeft() {
        if (cursor > 0) {
            cursor--;
        }
    }
    
    public void cursorHome() {
        cursor = 0;
    }
    
    public void cursorFin() {
        cursor = currentLine.length();
    }
    
    public void backspaceCharacter() {
        
        if (cursor > 0) {
            currentLine.deleteCharAt(cursor - 1);
            cursor--;
        }
    }
    
    public void insertMode() {
        isInsertMode = !isInsertMode;
    }
    
}
