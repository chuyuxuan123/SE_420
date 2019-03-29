package main.java.com.company;

import java.util.ArrayList;
import java.util.HashSet;

public class Wordladder {

    private Dictionary dict;
    private String word1;
    private String word2;

    public Wordladder(Dictionary d) {
        dict = d;
    }

    public boolean check(String w1, String w2) {
        word1 = w1.toLowerCase();
        word2 = w2.toLowerCase();
        if (!dict.exists(word1)) {
            System.out.println("Word1 not exist");
            return false;
        } else if (!dict.exists(word2)) {
            System.out.println("word2 not exist");
            return false;
        } else if (word1.length() != word2.length()) {
            System.out.println("not the same length");
            return false;
        } else if (word2.equals(word1)) {
            System.out.println("they are the same word");
            return false;
        } else {
            return true;
        }
    }

    public void solve(String w1, String w2) {

        HashSet used = new HashSet();
        ArrayList<String> ladder = new ArrayList();
        ArrayList<ArrayList<String>> solution = new ArrayList<>();

        ladder.add(w1);
        solution.add(ladder);
        used.add(w1);

        while (!solution.isEmpty()) {
            ArrayList<String> tempLadder = new ArrayList<>(solution.get(0));
            solution.remove(0);
            ArrayList<String> neighbors = new ArrayList();

            String tempStr = tempLadder.get(tempLadder.size() - 1);
            for (int i = 0; i < tempStr.length(); ++i) {
                for (char c = 'a'; c <= 'z'; ++c) {
                    String newStr = new String(tempStr.substring(0, i) + c + tempStr.substring(i + 1));
                    if (dict.exists(newStr) && !newStr.equals(tempStr)) {
                        neighbors.add(newStr);
                    }
                }
            }

            for (String s : neighbors) {
                if (!used.contains(s)) {
                    used.add(s);
                    if (s.equals(w2)) {
                        ladder = tempLadder;
                        ladder.add(s);
                        System.out.println(ladder);
                        return;
                    } else {
                        ArrayList<String> cpy = new ArrayList<>(tempLadder);
                        cpy.add(s);
                        solution.add(cpy);
                    }
                }
            }
        }
    }
}
