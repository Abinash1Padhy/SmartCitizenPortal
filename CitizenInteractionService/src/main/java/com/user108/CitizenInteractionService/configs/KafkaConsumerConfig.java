package com.user108.CitizenInteractionService.configs;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.user108.CitizenInteractionService.dto.ComplaintDto;

@Configuration
public class KafkaConsumerConfig {

	public static final String complaint_GROUP_ID = "COMPLAINT_GROUP";
	
	@Bean
    public ConsumerFactory<String, ComplaintDto> consumerFactory() {
        JsonDeserializer<ComplaintDto> deserializer = new JsonDeserializer<>(ComplaintDto.class);
        deserializer.addTrustedPackages("com.user108.CitizenInteractionService.dto");

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9091,localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, complaint_GROUP_ID);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ComplaintDto> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ComplaintDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }



}
