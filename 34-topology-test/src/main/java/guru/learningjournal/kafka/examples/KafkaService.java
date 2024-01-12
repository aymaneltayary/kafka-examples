package guru.learningjournal.kafka.examples;

import guru.learningjournal.kafka.examples.model.CampaignPerformance;
import guru.learningjournal.kafka.examples.serde.AppSerdes;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.Properties;

@Slf4j
public class KafkaService {

    /**
     *
     * @return
     */
    public Topology buildTopology(StreamsBuilder streamsBuilder) {


        // Group impressions
        KTable<String,Long> impressKtable=streamsBuilder.stream(AppConfig.TOPIC_NAME_IMPRESSION,Consumed.with(AppSerdes.String(),AppSerdes.impression()))
                .groupBy((impressionAsKey, impression) -> impression.getCampaigner(), Grouped.with(AppSerdes.String(),AppSerdes.impression()))
                .count();


        // Group user clicks
        KTable<String,Long> clickKtable=streamsBuilder.stream(AppConfig.TOPIC_NAME_CLICK,Consumed.with(AppSerdes.String(),AppSerdes.click()))
                .groupBy((impressionAsKey, impression) -> impression.getCampaigner(),Grouped.with(AppSerdes.String(),AppSerdes.click()
                ))
                .count();

        //
        impressKtable.
                leftJoin(clickKtable,(impressCount, clickCount) -> new CampaignPerformance().withImpressionCount(Double.valueOf(impressCount==null?0L:impressCount)).withClickCount(Double.valueOf(clickCount==null?0L:clickCount)))
                .mapValues((campaign, campaignPerformance) -> {
                    campaignPerformance.withCampaigner(campaign);
                    return campaignPerformance;
                }, Materialized.<String, CampaignPerformance, KeyValueStore<Bytes, byte[]>>
                                as(AppConfig.STORE_NAME_CAMPAIGN_PERFORMANCE)
                        .withKeySerde(AppSerdes.String())
                        .withValueSerde(AppSerdes.CampaignPerformance()))
                .toStream()
                .peek((campaignAsKey, campaignPerformance) -> log.info("Printing campaignPerformance result campaign={},impressions={}, clicks={}",campaignPerformance.getCampaigner(),campaignPerformance.getImpressionCount(),campaignPerformance.getClickCount()))
                .to(AppConfig.TOPIC_CAMPAIGN_PERFORMANCE,Produced.with(AppSerdes.String(),AppSerdes.CampaignPerformance()));

        return streamsBuilder.build();
    }

}
