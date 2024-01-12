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

import guru.learningjournal.kafka.examples.model.PaymentConfirmation;
import guru.learningjournal.kafka.examples.model.PaymentRequest;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class AppTimestampExtractor {


    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SS'Z'";

    public static PaymentRequestTimeExtractor paymentRequest() {

        return new PaymentRequestTimeExtractor();
    }

    public static PaymentConfirmationTimeExtractor paymentConfirmation() {
        return new PaymentConfirmationTimeExtractor();
    }

    private static final class PaymentRequestTimeExtractor implements TimestampExtractor {

        @Override
        public long extract(ConsumerRecord<Object, Object> consumerRecord, long prevTime) {
            PaymentRequest record = (PaymentRequest) consumerRecord.value();
            LocalDateTime createdDateTime = LocalDateTime.parse(record.getCreatedTime(), DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
            long eventTime = createdDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
            return ((eventTime > 0) ? eventTime : prevTime);
        }
    }

    private static final class PaymentConfirmationTimeExtractor implements TimestampExtractor {

        @Override
        public long extract(ConsumerRecord<Object, Object> consumerRecord, long prevTime) {
            PaymentConfirmation record = (PaymentConfirmation) consumerRecord.value();
            LocalDateTime createdDateTime = LocalDateTime.parse(record.getCreatedTime(), DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
            long eventTime = createdDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
            return ((eventTime > 0) ? eventTime : prevTime);
        }
    }


}