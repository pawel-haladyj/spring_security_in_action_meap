package pl.haladyj.SpringSecurity_configuring_authorization_CH6.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/a")
    public String postEndpointA(){
        return "Works! Post A";
    }

    @GetMapping("/a")
    public String getEndpointA(){
        return "Works! Get A";
    }

    @GetMapping("/a/b")
    public String getEndpointB(){
        return "Works! Get B";
    }

    @GetMapping("/a/b/c")
    public String getEndpointC(){
        return "Works! Get C";
    }
}
