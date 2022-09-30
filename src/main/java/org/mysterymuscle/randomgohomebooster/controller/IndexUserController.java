package org.mysterymuscle.randomgohomebooster.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexUserController {

    @GetMapping("/")
    @ResponseBody
    public String index(Model model
    , SecurityContext securityContext) {

        return "security context: " + securityContext.getAuthentication().getPrincipal();
    }

}
