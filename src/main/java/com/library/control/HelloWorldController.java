package com.library.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloWorldController {
    @RequestMapping(value = "/h")
    public String syaHello(ModelMap modle){
        modle.addAttribute("greet","Hello world");
        return "hellow";
    }
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String syaHelloAgain(ModelMap modle){
        modle.addAttribute("greet","Hello world");
        return "hellow";
    }
}
