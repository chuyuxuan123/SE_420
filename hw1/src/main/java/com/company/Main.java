package main.java.com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Wordladder w = new Wordladder(new Dictionary("dictionary/smalldict3.txt"));
        String word1 = new String();
        String word2 = new String();

        do {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("input the first word: ");
                word1 = br.readLine();
                System.out.println("input the second word: ");
                word2 = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!w.check(word1, word2));
        w.solve(word1, word2);
    }
}
