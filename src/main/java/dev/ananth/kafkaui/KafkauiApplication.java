package dev.ananth.kafkaui;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;
import java.util.Set;

@SpringBootApplication
public class KafkauiApplication {

	public static void main(String[] args) {SpringApplication.run(KafkauiApplication.class, args);}
	
}
