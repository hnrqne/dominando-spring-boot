package hnrqne.demo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("greetings")
@Log4j2
public class HelloController {

    @GetMapping("hi")
    public String hi(){
        return "OMAE WA MO SHINDEIRU";
    }

    @PostMapping
    public Long save(@RequestBody String name){
        log.info("Saving name '{}'", name);
        return ThreadLocalRandom.current().nextLong();
    }
}
