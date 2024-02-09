package com.example.demo.serde;

import java.io.IOException;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class CustomDeserializer<T> implements Deserializer<T> {

  private static final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  ;

  private Class<T> type;

  public CustomDeserializer(Class<T> type) {
    this.type = type;
  }

  @Override
  public T deserialize(String s, byte[] bytes) {
    try {

      return mapper.readValue(bytes, type);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
