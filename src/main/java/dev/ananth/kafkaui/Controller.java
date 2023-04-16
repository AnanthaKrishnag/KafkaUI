package dev.ananth.kafkaui;


import dev.ananth.kafkaui.entity.ClusterInfo;
import dev.ananth.kafkaui.request.CreateTopicRequest;
import dev.ananth.kafkaui.entity.Topic;
import dev.ananth.kafkaui.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class Controller {


    @Autowired
    private TopicService topicService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ClusterInfo getclusterinfo()
    {
        return topicService.getclusterinfo();
    }

    @GetMapping(value = "/topics", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Topic> returnName()
    {
        return topicService.getTopicsList();
    }

    @PostMapping(value = "/createtopic",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTopic(@RequestBody CreateTopicRequest topic)
    {
        topicService.createTopic(topic);
    }

}
