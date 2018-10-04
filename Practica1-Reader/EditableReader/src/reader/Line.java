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
    String begin = "";
    String end = "";
    StringBuffer currentLine;

    public Line() {
        this.currentLine = new StringBuffer("");
        this.cursor = 0;
       
    }
    
    
    
    public void addCharacter(char ch) {

        if (cursor < currentLine.length()){

            currentLine.append(ch);

        } else if (!isInsertMode) {
            currentLine.insert(cursor, ch);
//            begin = currentLine.substring(0, cursor);
//            end = currentLine.substring(cursor);
//            currentLine = begin + ch + end;

        } else {
            currentLine.setCharAt(cursor, ch);
        }

        cursor++;
    }
    
    public void deleteCharacter() {
        if (cursor < currentLine.length() - 1){
//            currentLine = new StringBuilder(currentLine);
            
            if (isInsertMode) {
                currentLine.deleteCharAt(cursor);
            }
            else{
                currentLine.deleteCharAt(cursor + 1);
            }
            
//            currentLine = currentLine.toString();
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
//            currentLine = new StringBuilder(currentLine);

            currentLine.deleteCharAt(cursor - 1);
            
//            currentLine = currentLine.toString();
        }
    }
    
    public void insertMode() {
        isInsertMode = !isInsertMode;
    }
    
}
