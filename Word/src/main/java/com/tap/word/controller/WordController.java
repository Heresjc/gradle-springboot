package com.tap.word.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordController {

    @ResponseBody
    @GetMapping("/Word")
    public String Word(){
        return "Word";
    }

}
