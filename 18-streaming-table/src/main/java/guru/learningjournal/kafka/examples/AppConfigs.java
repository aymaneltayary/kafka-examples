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

class AppConfigs {
    final static String APPLICATION_ID = "streaming-table";
    final static String BOOT_STRAP_SERVERS = "localhost:9092";
    final static String TOPIC_NAME = "stock-tick";
    final static String START_STORE_LOCATION = "tmp/state-store";
    final static String STATE_STORE_NAME = "kt01-store";
    final static String REGEX_SYMBOL = "(?i)HSBC|HSBC";
    final static String QUERY_SERVER_HOST = "localhost";
    final static int QUERY_SERVER_PORT = 7010;

}
