package org.mysterymuscle.randomgohomebooster.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexUserController {

    @GetMapping("/")
    public String IndexGet(Model model){
        model.addAttribute("test", "test");
        return "index";
    }

}
