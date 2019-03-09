package com.company;

import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DictionaryTest")
class DictionaryTest {

    Dictionary d1 = new Dictionary("dictionary/smalldict1.txt");

    @org.junit.jupiter.api.Test
    @DisplayName("test exists()")
    void exists() {

        assertEquals(d1.exists("code"),true);
        assertEquals(d1.exists("beg"),true);
        assertEquals(d1.exists("ics"),false);
    }
}