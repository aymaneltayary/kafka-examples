package com.example.demo.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;

/**
 * To avoid boilerplate code for most cases, especially when you develop microservices, Spring for Apache Kafka provides the @EnableKafkaStreams annotation, which you should place on a @Configuration class.
 * All you need is to declare a KafkaStreamsConfiguration bean named defaultKafkaStreamsConfig.
 * A StreamsBuilderFactoryBean bean, named defaultKafkaStreamsBuilder, is automatically declared in the application context.
 * You can declare and use any additional StreamsBuilderFactoryBean beans as well.
 */

@Configuration
@EnableKafkaStreams
public class OrderStreamConfiguration {

  public final static String STATE_STORE_NAME_ORDER_COUNT = "order-count";

  private final static String BOOT_STRAP_SERVERS = "localhost:9092";

  public final String TOPIC_NAME_ORDER = "order";

  // @Autowired
  //This autowired Bean holds all the properties that defined in the properties  file
  // KafkaProperties kafkaProperties;


   /* @Bean
   // @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration defaultKafkaStreamsConfig() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfig.APPLICATION_ID);
        configs.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.BOOT_STRAP_SERVERS);
        configs.put(StreamsConfig.STATE_DIR_CONFIG, AppConfig.STATE_STORE_LOCATION_TMP);
        configs.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class);
        configs.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.StringSerde.class);

        // configs.put(StreamsConfig.DEFAULT_DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG, LogAndContinueExceptionHandler.class);

        return new KafkaStreamsConfiguration(configs);
    }
*/

/*
    Default StreamsBuilderFactoryBean creation
    @Bean
    public FactoryBean<StreamsBuilder> defaultKafkaStreamsBuilder(KafkaStreamsConfiguration streamsConfig) {
        return new StreamsBuilderFactoryBean(streamsConfig);
    }
*/
}
