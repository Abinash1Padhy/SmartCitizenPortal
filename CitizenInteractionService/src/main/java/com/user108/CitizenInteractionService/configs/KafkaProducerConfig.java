package com.user108.CitizenInteractionService.configs;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.user108.CitizenInteractionService.dto.ComplaintDto;

@Configuration
public class KafkaProducerConfig {

	@Bean
    public ProducerFactory<String, ComplaintDto> producerFactory() {
		System.out.println("Kafka Custom producer");
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory(configProps);
    }
	
	@Bean(name = "kafkaProducerTemplate")
    public KafkaTemplate<String, ComplaintDto> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }	
	
}
