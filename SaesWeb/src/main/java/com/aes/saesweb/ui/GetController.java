package com.aes.saesweb.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetController {


    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/encrypt")
    public String encryptPage(Model model) {
        model.addAttribute("request", new Request());
        return "encryptPage";
    }

    @GetMapping("/multiple")
    public String triplePage(Model model) {
        model.addAttribute("request", new MultipleRequest());
        return "multiplePage";
    }

    @GetMapping("/ascii")
    public String asciiPage(Model model) {
        model.addAttribute("request", new Request());
        return "asciiPage";
    }

    @GetMapping("/double")
    public String doublePage(Model model) {
        model.addAttribute("request", new DoubleRequest());
        return "doublePage";
    }
}
