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

    public Line() {
        this.currentLine = new StringBuffer("");
        this.cursor = 0;
    }
    
    public void removeCurrentLine() {
        System.out.print(Constants.CURSOR_REMOVE_LINE_SEQUENCE);
    }
    
    public void addCharacter(char ch) {

        if (cursor == currentLine.length()){
            currentLine.append(ch);
        } else {
            if (!isInsertMode) {
            currentLine.insert(cursor, ch);
            
//            begin = currentLine.substring(0, cursor);
//            end = currentLine.substring(cursor);
//            currentLine = begin + ch + end;
            
            } else {
                currentLine.setCharAt(cursor, ch);
            }
        }
        cursor++;
        // removeCurrentLine();
        // System.out.print("\033[" + cursor + "C");
        System.out.print(ch);
        System.out.print(currentLine.length());
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
            System.out.print(currentLine.toString());
        }
    }
    
    public void cursorRight() {
        if (cursor < currentLine.length()) {
            cursor++;
            System.out.print(Constants.CURSOR_RIGHT_SEQUENCE);
        }
    }
    
    public void cursorLeft() {
        if (cursor > 0) {
            cursor--;
            System.out.print(Constants.CURSOR_LEFT_SEQUENCE);
        }
    }
    
    public void cursorHome() {
        System.out.print("\033[" + cursor + "C");
        cursor = 0;
    }
    
    public void cursorFin() {
        System.out.print("\033[" + (currentLine.length() - cursor) + "D");
        cursor = currentLine.length();
    }
    
    public void backspaceCharacter() {
        if (cursor > 0) {
//            currentLine = new StringBuilder(currentLine);

            currentLine.deleteCharAt(cursor - 1);
            System.out.print(currentLine.toString());
            cursor--;
//            currentLine = currentLine.toString();
        }
    }
    
    public void insertMode() {
        isInsertMode = !isInsertMode;
    }
    
}
