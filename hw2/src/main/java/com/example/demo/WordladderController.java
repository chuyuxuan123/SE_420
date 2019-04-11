package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WordladderController {

    Wordladder w = new Wordladder(new Dictionary("G:\\SE_420\\hw2\\src\\main\\resources\\dictionary\\smalldict1.txt"));

//    @RequestMapping("/")
//    public String index() {
//        return "index";
//    }
//
    @RequestMapping("/s")
    public String wordladder(){
        return "wordladder";
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/wordladder")
    public String result(@RequestParam(value = "word1") String word1, @RequestParam(value = "word2") String word2) {
        return w.solve(word1, word2).toString();
    }
}
