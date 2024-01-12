/*
 * Copyright (c) 2019. Prashant Kumar Pandey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package guru.learningjournal.kafka.examples;

import guru.learningjournal.kafka.examples.model.HeartBeat;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class HeatBeatTimestampExtractor implements TimestampExtractor {

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SS'Z'";

    @Override
    public long extract(ConsumerRecord<Object, Object> consumerRecord, long prevTime) {
        HeartBeat record = (HeartBeat) consumerRecord.value();
        LocalDateTime eventDateTime = LocalDateTime.parse(record.getCreatedTime(), DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        long eventDateTimeAsLong = eventDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
        return ((eventDateTimeAsLong > 0) ? eventDateTimeAsLong : prevTime);
    }

}