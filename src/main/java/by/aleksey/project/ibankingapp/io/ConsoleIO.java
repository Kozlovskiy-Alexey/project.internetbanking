package by.aleksey.project.ibankingapp.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleIO implements IOInterface {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public String readLine() throws IOException {
        return br.readLine();
    }
}
