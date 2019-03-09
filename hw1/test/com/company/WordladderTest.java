package com.company;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.invoke.WrongMethodTypeException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("WordladderTest")
class WordladderTest {

    Wordladder wl = new Wordladder(new Dictionary("dictionary/smalldict1.txt"));

    @Test
    @DisplayName("test check()")
    void check() {
        assertEquals(wl.check("code","data"),true);
        assertEquals(wl.check("code","code"),false);
        assertEquals(wl.check("happy","code"),false);
        assertEquals(wl.check("ics","beg"),false);

    }

    @Test
    void solve() {
    }
}