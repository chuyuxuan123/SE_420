package com.example.demo;

import org.apache.tomcat.util.digester.ArrayStack;
import org.assertj.core.util.Arrays;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WordladderTest {

    Wordladder wl = new Wordladder(new Dictionary("G:\\SE_420\\hw2\\src\\main\\resources\\dictionary\\smalldict1.txt"));

    @Test
    public void check() {
        assertEquals(wl.check("code","data"),true);
        assertEquals(wl.check("code","code"),false);
        assertEquals(wl.check("happy","code"),false);
        assertEquals(wl.check("ics","beg"),false);
    }

    @Test
    public void solve() {

        String[] strs = {"code","cade","cate","date","data"};
        ArrayList a = new ArrayList(Arrays.asList(strs));

        assertEquals(a, wl.solve("code","data"));
    }
}