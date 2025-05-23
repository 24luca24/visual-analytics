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

import org.elasticsearch.ingest.IngestDocument;
import org.elasticsearch.ingest.RandomDocumentPicks;
import org.elasticsearch.test.ESTestCase;
import org.junit.Before;
import org.junit.BeforeClass;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class LookupProcessorTests extends ESTestCase {

    private Map<String, String> lookupMap;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        // Initialize the lookup map before each test
        lookupMap = new HashMap<>();
        lookupMap.put("C001", "tyre");
        lookupMap.put("C010", "front wing");
        lookupMap.put("C100", "damper");
    }

    @BeforeClass
    public static void defaultSettings() {
        // TODO your job!
    }

    public void testSimple() throws Exception {
        // Create processor
        LookupProcessor processor = new LookupProcessor("testTag", "testDesc", "field1", lookupMap);

        // Create document
        Map<String, Object> doc = new HashMap<>();
        doc.put("field1", "Need to optimize the C001 temperature. C010 needs to be changed as it is damaged. C100 seems ok.");
        IngestDocument ingestDocument = RandomDocumentPicks.randomIngestDocument(random(), doc);

        // Execute processor
        processor.execute(ingestDocument);

        // Check result
        String expected = "Need to optimize the tyre temperature. front wing needs to be changed as it is damaged. damper seems ok.";
        assertThat(ingestDocument.getFieldValue("field1", String.class), equalTo(expected));
    }

    public void testEmptyField() throws Exception {
        // Create processor
        LookupProcessor processor = new LookupProcessor("testTag", "testDesc", "field1", lookupMap);

        // Create document with empty field
        Map<String, Object> doc = new HashMap<>();
        doc.put("field1", "");
        IngestDocument ingestDocument = RandomDocumentPicks.randomIngestDocument(random(), doc);

        // Execute processor
        processor.execute(ingestDocument);

        // Check that the field is still empty
        assertThat(ingestDocument.getFieldValue("field1", String.class), equalTo(""));
    }

}
