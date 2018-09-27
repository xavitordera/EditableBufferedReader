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
    
    public String characters;
    public int cursor;
    public boolean isInsertMode = false;
    String begin = "";
    String end = "";
    
    
    public void addCharacter(char ch) {

        if (cursor == characters.length() - 1){

            characters += ch;

        } else if (!isInsertMode) {

            begin = characters.substring(0, cursor);
            end = characters.substring(cursor);
            characters = begin + ch + end;

        } else {
            characters.toCharArray()[cursor] = ch;
        }

        cursor++;
    }
    
    public void deleteCharacter() {
        if (cursor != characters.length() - 1){
            StringBuilder str = new StringBuilder(characters);
            if (isInsertMode) str.deleteCharAt(cursor);
            else str.deleteCharAt(cursor + 1);
        }
    }
    
    public void insertMode() {
        if (isInsertMode) {
            isInsertMode = false;
        } else {
            isInsertMode = true;
        }
    }
    
}
