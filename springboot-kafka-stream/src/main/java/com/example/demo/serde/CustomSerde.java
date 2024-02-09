package com.example.demo.serde;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;

import com.example.demo.domain.PosInvoice;
import com.example.demo.domain.Store;
import com.example.demo.domain.TotalRevenue;
import com.example.demo.domain.TotalRevenueWithAddress;

public class CustomSerde extends Serdes {

  public static Serde<PosInvoice> Order() {
    return Serdes.serdeFrom(new CustomSerializer<PosInvoice>(), new CustomDeserializer<PosInvoice>(PosInvoice.class));
    // or
    // return new OrderSerde(new CustomSerializer<Order>(), new CustomDeserializer<Order>(Order.class));

  }

  public static Serde<Store> Store() {
    return Serdes.serdeFrom(new CustomSerializer<Store>(), new CustomDeserializer<Store>(Store.class));
    // or
    // return new OrderSerde(new CustomSerializer<Store>(), new CustomDeserializer<Store>(Store.class));

  }

  public static Serde<TotalRevenue> TotalRevenuetSerde() {
    return Serdes.serdeFrom(new CustomSerializer<TotalRevenue>(), new CustomDeserializer<TotalRevenue>(TotalRevenue.class));
    // or
    // return new TotalRevenueSerde(new CustomSerializer<TotalRevenue>(), new CustomDeserializer<TotalRevenue>(TotalRevenue.class));
  }

  public static Serde<TotalRevenueWithAddress> TotalRevenueWithAddressSerde() {
    return Serdes.serdeFrom(new CustomSerializer<TotalRevenueWithAddress>(), new CustomDeserializer<TotalRevenueWithAddress>(TotalRevenueWithAddress.class));
    // or
    // return new TotalRevenueWithAddressSerde(new CustomSerializer<TotalRevenueWithAddress>(), new CustomDeserializer<TotalRevenueWithAddress>(TotalRevenueWithAddress.class));
  }

  public static class OrderSerde extends WrapperSerde<PosInvoice> {

    public OrderSerde(Serializer<PosInvoice> serializer, Deserializer<PosInvoice> deserializer) {
      super(serializer, deserializer);
    }
  }

  public static class StoreSerde extends WrapperSerde<Store> {

    public StoreSerde(Serializer<Store> serializer, Deserializer<Store> deserializer) {
      super(serializer, deserializer);
    }
  }

  public static class TotalRevenueSerde extends WrapperSerde<TotalRevenue> {

    public TotalRevenueSerde(Serializer<TotalRevenue> serializer, Deserializer<TotalRevenue> deserializer) {
      super(serializer, deserializer);
    }
  }

  public static class TotalRevenueWithAddressSerde extends WrapperSerde<TotalRevenueWithAddress> {

    public TotalRevenueWithAddressSerde(Serializer<TotalRevenueWithAddress> serializer,
                                        Deserializer<TotalRevenueWithAddress> deserializer) {
      super(serializer, deserializer);
    }
  }
}
