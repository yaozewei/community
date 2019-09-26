package life.majiang.community.community.controller;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.model.IModel;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String greeting(@RequestParam(name = "name") String name ,Model model){
        model.addAttribute("name",name);
        return "greeting";
    }
}
