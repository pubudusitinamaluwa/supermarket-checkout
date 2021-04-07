package org.supermarket.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class testController {
        @RequestMapping("/")
        public String index() {
            return "Greetings from Spring Boot!";
        }
}
