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
    

    public EditableBufferedReader(Reader reader) {
        super(reader);
    }
    
    public void setRaw() {
        try {
            Process proc = Runtime.getRuntime().exec(Constants.setRawCommand);
        } catch (IOException ex) {
            Logger.getLogger(EditableBufferedReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void unsetRaw() {
        try {
            Process proc = Runtime.getRuntime().exec(Constants.unsetRawCommand);
        } catch (IOException ex) {
            Logger.getLogger(EditableBufferedReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public int read(){
        
        // Console console = System.console();
        int character;
        int valorSimbol;
        
        try {
            // ESCAPE == ^[
            if((character = super.read()) == Constants.ESCAPE) {
                if((character = super.read()) == Constants.CLAUDATOR) {
                    // CSI Sequence = ^[[
                    switch((character = super.read())) {
                        case Constants.RIGHT: 
                            //TODO: Move the cursor to the right if possible
                        case Constants.LEFT:
                            //TODO: Move the cursor to the left if possible
                        case '3':
                            //TODO: Delete the character below the cursor
                        case '2':
                            //TODO: Insert the character below the cursor
                    }
                } else if ((character = super.read()) == 'O') {
                    switch((character = super.read())) {
                        case Constants.HOME: 
                            //TODO: Move the cursor to the beginning of line
                        case Constants.FIN:
                            //TODO: Move the cursor to the end of line
                    }
                }
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(EditableBufferedReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return character;
    }
    
    
    
}


