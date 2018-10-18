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
    
    public void removeCurrentLine() {
        System.out.print("\033[M");
        cursorHome();
    }
    
    public void addCharacter(char ch) {
  
        if (cursor == currentLine.length()|| !isInsertMode) {
            currentLine.insert(cursor, ch);
            System.out.print("\033[@");
        } else {
            currentLine.setCharAt(cursor, ch);
        }
        cursor++;
        System.out.print(ch);
    }
    
    public void deleteCharacter() {
        if (cursor < currentLine.length() - 1){
//            currentLine = new StringBuilder(currentLine);
            
            if (isInsertMode) {
                begin = currentLine.substring(0, cursor - 1);
                end = currentLine.substring(cursor + 1);
                currentLine = new StringBuffer(begin + end);
            }
            else{
                currentLine.deleteCharAt(cursor + 1);
            }
            
//            currentLine = currentLine.toString();
            removeCurrentLine();
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
        // System.out.print("DETECTED HOME");
        System.out.print("\033[" + cursor + "D");
        cursor = 0;
    }
    
    public void cursorFin() {
        System.out.print("\033[" + (currentLine.length() - cursor) + "C");
        cursor = currentLine.length();
    }
    
    public void backspaceCharacter() {
        
        if (cursor > 0) {
//            currentLine = new StringBuilder(currentLine);
            currentLine.deleteCharAt(cursor - 1);
            cursor--;
            System.out.print(Constants.ESCAPE+"[D");
            System.out.print(Constants.ESCAPE+"[P");
        }
    }
    
    public void insertMode() {
        isInsertMode = !isInsertMode;
    }
    
}
