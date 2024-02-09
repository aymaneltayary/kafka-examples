package com.example.demo.serde;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class CustomSerializer<T> implements Serializer<T> {

  private static final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  ;

  @Override
  public byte[] serialize(String s, T t) {
    try {
      return mapper.writeValueAsBytes(t);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
