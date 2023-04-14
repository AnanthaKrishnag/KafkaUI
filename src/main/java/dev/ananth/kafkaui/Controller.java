package dev.ananth.kafkaui;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class Controller {

    @GetMapping()
    public String returnName()
    {
        return "its me";
    }


}
