package com.example.demo;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class DictionaryTest {

    Dictionary d1 = new Dictionary("G:\\SE_420\\hw2\\src\\main\\resources\\dictionary\\smalldict1.txt");

    @Test
    public void exists() {
        assertEquals(d1.exists("code"), true);
        assertEquals(d1.exists("beg"), true);
        assertEquals(d1.exists("ics"), false);
    }
}