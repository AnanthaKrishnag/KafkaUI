package dev.ananth.kafkaui;


import dev.ananth.kafkaui.entity.Topic;
import dev.ananth.kafkaui.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class Controller {


    private TopicService topicService;

    @GetMapping(value = "/topics", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Topic> returnName()
    {
        return topicService.getTopicsList();
    }




}
