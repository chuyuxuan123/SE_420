package com.example.wordladder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WordladderController {

//    URL url = this.getClass().getClassLoader().getResource("/Dictionary/smalldict1.txt");

    Wordladder wordladder = new Wordladder(new Dictionary("src/main/resources/Dictionary/smalldict1.txt"));


    @RequestMapping("/api/wordladder")
    @ResponseBody
    public String result(@RequestParam(value = "word1") String word1, @RequestParam(value = "word2") String word2) {
        return wordladder.solve(word1, word2).toString();
    }
}
