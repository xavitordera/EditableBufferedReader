/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reader;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author lsadusr11
 */
public class EditableBufferedReader extends BufferedReader {
    
    ///////////////////////////////
    //         TO COMPILE        //
    ///////////////////////////////
    
    // javac -d build/classes/ -cp src src/reader/EditableBufferedReader.java
    // src/reader/Constants.java src/reader/TestReadLine.java
    
    
    
    ///////////////////////////////
    //         TO EXECUTE        //
    ///////////////////////////////
    
    // java -cp build/classes/ reader.TestReadLine
    
    
    boolean symbolFlag = false;
    

    public EditableBufferedReader(Reader reader) {
        super(reader);
    }
    
    public void setRaw() {
        try {
            Process proc = Runtime.getRuntime().exec(Constants.SET_RAW_COMMAND);
        } catch (IOException ex) {
            Logger.getLogger(EditableBufferedReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void unsetRaw() {
        try {
            Process proc = Runtime.getRuntime().exec(Constants.UNSET_RAW_COMMAND);
        } catch (IOException ex) {
            Logger.getLogger(EditableBufferedReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public int read(){
        
        // Console console = System.console();
        int character;
        int valorSimbol = -1;
        symbolFlag = false;
        
        try {
            character = super.read();
            // ESCAPE == ^[
            if(character == Constants.ESCAPE) {
                
                symbolFlag = true;
                
                if((character = super.read()) == Constants.CLAUDATOR) {
                    // CSI Sequence = ^[[
                    switch((character = super.read())) {
                        case Constants.RIGHT: 
                            // Move the cursor to the right if possible
                            valorSimbol = Constants.RIGHT_ARROW;
                            break;
                        case Constants.LEFT:
                            // Move the cursor to the left if possible
                            valorSimbol = Constants.LEFT_ARROW;
                            break;
                        case Constants.DEL:
                            // Delete the character below the cursor
//                            System.out.print("SUPR DETECTED");
                            if (super.read() == '~') {
                                valorSimbol = Constants.DEL_BUTTON;
                            }     
                            break;
                        case Constants.INS:
                            // Insert the character below the cursor
                            if (super.read() == '~') {
                                valorSimbol = Constants.INS_BUTTON;
                            }
                            break;
                    }
                } else if ((character = super.read()) == 'O') {
                    switch((character = super.read())) {
                        case Constants.HOME: 
                            // Move the cursor to the beginning of line
                            System.out.print("H detected");
                            valorSimbol = Constants.HOME_BUTTON;
                            break;
                        case Constants.FIN:
                            // Move the cursor to the end of line
                            valorSimbol = Constants.FIN_BUTTON;
                            break;
                    }
                }
            } else if (character == Constants.BKSP_ASCII){
                valorSimbol = Constants.BKSP_BUTTON;
            } else {
                valorSimbol = character;
            }
            
        } catch (IOException ex) {
            Logger.getLogger(EditableBufferedReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return valorSimbol;
    }
    
    @Override
    public String readLine() {
        int ch;
        Line line = new Line();
        
        setRaw();
        
        while((ch = read()) != '\r') {
            if(symbolFlag) {
                switch(ch) {
                    case Constants.BKSP_BUTTON:
                        line.backspaceCharacter();
                        break;
                    case Constants.DEL_BUTTON:
                        //System.out.print("Supr");
                        line.deleteCharacter();
                        break;
                    case Constants.FIN_BUTTON: 
                        line.cursorFin();
                        break;
                    case Constants.HOME_BUTTON:
                        line.cursorHome();
                        break;
                    case Constants.RIGHT_ARROW:
                        line.cursorRight();
                        break;
                    case Constants.LEFT_ARROW:
                        line.cursorLeft();
                        break;
                    case Constants.INS_BUTTON:
                        line.insertMode();
                        break;
                    default: break;
                }
            } else {
                line.addCharacter((char) ch);
            }
            
        }
        
        unsetRaw();
        
        return line.currentLine.toString();
    }
}


