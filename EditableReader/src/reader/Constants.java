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
    public static final String setRawCommand = "stty raw echo";
    public static final String unsetRawCommand = "stty cooked echo";
    public static final char ESCAPE = '\033';
    public static final char CLAUDATOR = '[';
    public static final char RIGHT = 'C';
    public static final char LEFT = 'D';
    public static final char HOME = 'H';
    public static final char FIN = 'F';
}
