package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    private static ArrayList<String> dict;

    private static String word1;
    private static String word2;

    public static void readFile() {
        try { FileReader reader = new FileReader("dictionary/smalldict1.txt");
             BufferedReader br = new BufferedReader(reader);

            String line;

            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
//                System.out.println(line);
                dict.add(new String(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readWords() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input the first word: ");
            word1 = br.readLine();
            System.out.println("input the second word: ");
            word2 = br.readLine();
            System.out.println(word1 + word2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        // write your code here
        readFile();
        readWords();
    }
}
