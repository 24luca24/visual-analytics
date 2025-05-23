/*
 * Copyright [2021] [your_name]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package usi.vaa.elasticsearch.plugin.ingest.lookup;

import org.elasticsearch.ElasticsearchParseException;
import org.elasticsearch.test.ESTestCase;
import org.junit.BeforeClass;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LookupProcessorFactoryTests extends ESTestCase {

    @BeforeClass
    public static void defaultSettings() {
        // TODO your job!
    }

    private Map<String, Object> getDefaultConfig() {
        Map<String, Object> config = new HashMap<>();
        config.put("field", "field1");
        Map<String, String> lookupMap = new HashMap<>();
        lookupMap.put("C001", "tyre");
        lookupMap.put("C010", "front wing");
        lookupMap.put("C100", "damper");
        config.put("lookup-map", lookupMap);
        return config;
    }

    public void testDefaultConfiguration() throws Exception{
        Map<String, Object> config = getDefaultConfig();
        LookupProcessor.Factory factory = new LookupProcessor.Factory();
        LookupProcessor processor = factory.create(null, "testTag", "testDesc", config);

        // Check that processor is created successfully
        assertThat(processor, notNullValue());

        // Check if the field and lookup-map are correctly configured
        assertThat(processor.getField(), equalTo("field1"));
        // Additional assertions can be made to check if lookup map was set correctly
    }


    public void testInvalidConfiguration() throws Exception{
        // Missing field name
        Map<String, Object> config = new HashMap<>();
        Map<String, String> lookupMap = new HashMap<>();
        lookupMap.put("C001", "tyre");
        config.put("lookup-map", lookupMap);  // Missing the field parameter

        try {
            LookupProcessor.Factory factory = new LookupProcessor.Factory();
            factory.create(null, "testTag", "testDesc", config);
            fail("Expected exception due to missing field");
        } catch (ElasticsearchParseException e) {
            // Expected exception, test passes
        }
    }


}
