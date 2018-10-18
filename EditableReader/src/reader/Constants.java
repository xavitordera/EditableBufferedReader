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
public class Constants {
    
    // COMMANDS
    public static final String[] SET_RAW_COMMAND = {"/bin/sh", "-c","stty raw -echo </dev/tty"},
                                 UNSET_RAW_COMMAND = {"/bin/sh", "-c","stty -raw echo </dev/tty"};
    
    // PARSING
    public static final char ESCAPE = '\033',
                             CLAUDATOR = '[',
                             RIGHT = 'C',
                             LEFT = 'D',
                             HOME = 'H',
                             FIN = 'F',
                             DEL = '3',
                             INS = '2';
    
    
    public static final int BKSP_ASCII = 127;
    
    // MAPPING
    public static final int RIGHT_ARROW = 0,
                            LEFT_ARROW = 1,
                            HOME_BUTTON = 2,
                            FIN_BUTTON = 3,
                            BKSP_BUTTON = 4,
                            DEL_BUTTON = 5,
                            INS_BUTTON = 6;
    
    // ESCAPE SEQUENCES
    public static final String CURSOR_RIGHT_SEQUENCE = "\033[C",
                               CURSOR_LEFT_SEQUENCE = "\033[D",
                               CURSOR_HOME_SEQUENCE = "\033[OH",
                               CURSOR_FIN_SEQUENCE = "\033[OF",
                               CURSOR_DELETE_SEQUENCE = "\033[3",
                               CURSOR_INSERT_SEQUENCE = "\033[2",
                               CURSOR_REMOVE_LINE_SEQUENCE = "\033[X";
//                               CURSOR_REMOVE_LINE_SEQUENCE = "\033[M";
            
                               
}
