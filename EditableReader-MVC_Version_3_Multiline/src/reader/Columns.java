/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author lsadusr11
 */
public class Columns {

    int nColumns;

    Columns() {
        nColumns = 0;
    }

    public int getColumns() throws InterruptedException, IOException {

        Process p = Runtime.getRuntime().exec(new String[]{
            "bash", "-c", "tput cols 2> /dev/tty"});

        p.waitFor();
        BufferedReader in;
        in = new BufferedReader(new InputStreamReader(p.getInputStream()));

        int numColumns = Integer.parseInt(in.readLine());
        nColumns = numColumns;
        return nColumns;
    }
}