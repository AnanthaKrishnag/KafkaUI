package dev.ananth.kafkaui.service;

import dev.ananth.kafkaui.entity.ClusterInfo;
import dev.ananth.kafkaui.request.CreateTopicRequest;
import dev.ananth.kafkaui.entity.Topic;
import org.apache.kafka.clients.KafkaClient;
import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.DescribeClusterResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TopicService
{
    KafkaConsumer<String,String> consumer;
    Admin admin;
    Collection<NewTopic> newTopicCollection= new LinkedList<NewTopic>();

    String bootstrapservers = "127.0.0.1:9092";

    public List<Topic> getTopicsList()
    {
        if(consumer== null){
            Properties properties = new Properties();
            properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapservers);
            properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            consumer = new KafkaConsumer<String, String>(properties);
        }

        Map<String, List<PartitionInfo>> topics = consumer.listTopics();

       // Set<String> topicNames = topics.keySet();

        LinkedList<Topic> listoftopics = new LinkedList<>();

        for(String topicname : topics.keySet())
        {
            if(!topicname.equals("__consumer_offsets")) {
                listoftopics.add(new Topic(topicname, topics.get(topicname).size()));
            }
        }

        return listoftopics;
    }

    public void createTopic(CreateTopicRequest topic) {
        if(admin==null) {
            Properties properties = new Properties();
            properties.setProperty("bootstrap.servers", bootstrapservers);
            admin = Admin.create(properties);
        }

        //Set<String> topicNames = admin.listTopics().names().get();

        if(topic.numPartitions==null)
        {
            topic.numPartitions=1;
        }
        if(topic.replicationFactor==null)
        {
            topic.replicationFactor=1;
        }

        newTopicCollection.add(new NewTopic(topic.name,topic.numPartitions,topic.replicationFactor));

        CreateTopicsResult createTopicsResult = admin.createTopics(newTopicCollection);

        newTopicCollection.clear();

        //admin.close();
    }

    public ClusterInfo getclusterinfo()
    {
        if(consumer == null){
            Properties properties = new Properties();
            properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapservers);
            properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            consumer = new KafkaConsumer<String, String>(properties);
        }

        Map<String, List<PartitionInfo>> topics = consumer.listTopics();

        LinkedList<Topic> listoftopics = new LinkedList<>();

        int totalpartitions = 0;
        for(String topicname : topics.keySet())
        {
            if(!topicname.equals("__consumer_offsets")) {
                totalpartitions += topics.get(topicname).size();
            }
        }

        return new ClusterInfo(bootstrapservers,topics.keySet().size()-1,totalpartitions);
    }


}
