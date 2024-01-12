package guru.learningjournal.kafka.examples;


import guru.learningjournal.kafka.examples.model.CampaignPerformance;
import guru.learningjournal.kafka.examples.model.Click;
import guru.learningjournal.kafka.examples.model.Impression;
import guru.learningjournal.kafka.examples.serde.AppSerdes;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;

import java.util.Properties;

@Slf4j
public class AdvertismentDemo {

    public static void main(String[] args) {


        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.APPLICATION_ID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.BOOT_STRAP_SERVERS);
        props.put(StreamsConfig.STATE_DIR_CONFIG, AppConfigs.STATE_STORE_LOCATION_TMP);
        props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 0);

        log.info("AdvertismentDemo has started");

        StreamsBuilder streamsBuilder= new StreamsBuilder();

        KStream<String, Impression>  impressionKStream= streamsBuilder.stream(AppConfigs.TOPIC_NAME_IMPRESSION, Consumed.with(AppSerdes.String(),AppSerdes.impression()));
        KStream<String, Click>  clickKStreamKStream= streamsBuilder.stream(AppConfigs.TOPIC_NAME_CLICK, Consumed.with(AppSerdes.String(),AppSerdes.click()));

        // count impressions by campaign
        KTable<String,Long> impressionCountKtable=impressionKStream.groupBy((key, impression) -> impression.getCampaigner(), Grouped.with(AppSerdes.String(),AppSerdes.impression()) )
                .count();

        // count  clicks by campaign
        KTable<String,Long> clickCountKtable=clickKStreamKStream.groupBy((key, click) -> click.getCampaigner(), Grouped.with(AppSerdes.String(),AppSerdes.click()) )
                .count();


        // Do the join
        impressionCountKtable
                .join(clickCountKtable,(aLong, aLong2) -> new CampaignPerformance().withImpressionCount(Double.valueOf(aLong)).withClickCount(Double.valueOf(aLong2)))
                // populate the CampaignPerformance event with the campaign name
                .mapValues((key,value)-> {value.setCampaigner(key);return value;})
                .toStream().peek((key,value)->  log.info("Now printing the result key={},value={}",key,value));


        log.info("Starting Stream...");
        KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), props);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Stopping Streams...");
            streams.close();
        }));

    }
}
