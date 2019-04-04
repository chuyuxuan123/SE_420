package com.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Dictionary {

    public ArrayList<String> dict;

    public Dictionary(String filepath) {
        dict = new ArrayList<>();
        try {
            FileReader reader = new FileReader(filepath);
            BufferedReader br = new BufferedReader(reader);

            String line;

            while ((line = br.readLine()) != null) {

                dict.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param word
     * @return
     */
    public boolean exists(String word) {
        return dict.contains(word);
    }
}
