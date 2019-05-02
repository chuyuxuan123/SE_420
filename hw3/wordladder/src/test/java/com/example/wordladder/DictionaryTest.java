package com.example.wordladder;

import com.example.wordladder.Dictionary;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DictionaryTest {
    Dictionary d1 = new Dictionary("src\\main\\resources\\dictionary\\smalldict1.txt");

    @Test
    public void exists() {
        assertEquals(d1.exists("code"), true);
        assertEquals(d1.exists("beg"), true);
        assertEquals(d1.exists("ics"), false);
    }
}
