package dev.ananth.kafkaui.service;

import dev.ananth.kafkaui.entity.Topic;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.*;

public class TopicService
{

    String bootstrapServers = "127.0.0.1:9092";


    public static List<Topic> getTopicsList()
    {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(properties);

        Map<String, List<PartitionInfo>> topics = consumer.listTopics();
       // Set<String> topicNames = topics.keySet();

        LinkedList<Topic> listoftopics = new LinkedList<>();

        for(String topicname : topics.keySet())
        {
            Topic topic = new Topic(topicname,topics.get(topicname).size());
            listoftopics.add(topic);
        }

        return listoftopics;
    }
}
