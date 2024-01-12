package guru.learningjournal.kafka.examples.topology;

import guru.learningjournal.kafka.examples.AppConfig;
import guru.learningjournal.kafka.examples.RewardsPartitioner;
import guru.learningjournal.kafka.examples.RewardsTransformer;
import guru.learningjournal.kafka.examples.model.PosInvoice;
import guru.learningjournal.kafka.examples.serde.AppSerdes;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.processor.StreamPartitioner;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;

import java.util.Properties;

@Slf4j
public class RewardTopology {



    public static  Topology build (){
        StreamsBuilder streamsBuilder= new StreamsBuilder();
        KStream<String, PosInvoice> ks0=streamsBuilder
                .stream(AppConfig.POS_TOPIC_NAME, Consumed.with(AppSerdes.String(),AppSerdes.PosInvoice()))
                .filter((key,value)-> AppConfig.CUSTOMER_TYPE_PRIME.equals(value.getCustomerType()));

        StoreBuilder<KeyValueStore<String, Double>> kvStoreBuilder= Stores.keyValueStoreBuilder(Stores.inMemoryKeyValueStore(AppConfig.REWARDS_STORE_NAME),AppSerdes.String(),AppSerdes.Double());
        streamsBuilder.addStateStore(kvStoreBuilder);
        StreamPartitioner<String,PosInvoice> streamPartitioner= new RewardsPartitioner();
        ks0
                /* using the old deprecated method through() which should be replaced by repartition()
                .through(AppConfigs.REWARDS_TEMP_TOPIC, Produced.with(AppSerdes.String(),AppSerdes.PosInvoice(),new RewardsPartitioner()))
                */
                .repartition(Repartitioned.streamPartitioner(streamPartitioner).withName(AppConfig.REWARDS_TEMP_TOPIC).withNumberOfPartitions(5).withKeySerde(AppSerdes.String()).withValueSerde(AppSerdes.PosInvoice()))
                .transformValues(RewardsTransformer::new, AppConfig.REWARDS_STORE_NAME)
                .peek((s, notification) -> log.info("customerCardNo={} earned={}.Now the totalLoyaltyPoints={}",notification.getCustomerCardNo(),notification.getEarnedLoyaltyPoints(),notification.getTotalLoyaltyPoints()))
                //.print(Printed.<String, Notification>toSysOut().withLabel("notification message"))
                .to(AppConfig.NOTIFICATION_TOPIC_NAME, Produced.with(AppSerdes.String(),AppSerdes.Notification()));

        return  streamsBuilder.build();

    }


}
