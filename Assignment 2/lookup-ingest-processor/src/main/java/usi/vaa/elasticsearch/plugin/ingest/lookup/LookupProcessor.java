/*
 * Copyright [2020] [your_name]
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

import org.elasticsearch.common.Strings;
import org.elasticsearch.index.engine.Engine;
import org.elasticsearch.ingest.AbstractProcessor;
import org.elasticsearch.ingest.IngestDocument;
import org.elasticsearch.ingest.Processor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.elasticsearch.ingest.ConfigurationUtils.readMap;
import static org.elasticsearch.ingest.ConfigurationUtils.readStringProperty;

public class LookupProcessor extends AbstractProcessor {

    //constant that define the type of processor used in the JSON of the pipeline as "type": "lookup"
    public static final String TYPE = "lookup";

    //field of the document on which the plugin has to apply the substitution. It's read in from the configuration pipeline
    private final String field;

    private final Map<String, String> lookupMap;

    //TODO this signature and method might be incomplete - change as needed
    /*
        initialize the processor with:
            1)tag -> optional identifier of processor in the pipeline
            2)description -> textual description of the processor
            3)field -> name of the field on which apply lookup (ex: message)
            4)lookupMap -> maps with the substitution to do (code -> value)
     */
    public LookupProcessor(String tag, String description, String field,
                           Map<String, String> lookupMap) throws IOException {
        super(tag, description);
        this.field = field;
        this.lookupMap = lookupMap;
    }

    /*
        method call for each document in input
            1)extract the value of the specified field
            2)execute the substitution using the lookupMap
            3)set the new value changed in the document
            4)return updated document
     */
    @Override
    public IngestDocument execute(IngestDocument ingestDocument) throws Exception {
        String originalContent = ingestDocument.getFieldValue(field, String.class);

        if (!originalContent.isEmpty()) {
            for (Map.Entry<String, String> entry : lookupMap.entrySet()) {
                originalContent = originalContent.replace(entry.getKey(), entry.getValue());
            }
        }
        ingestDocument.setFieldValue(field, originalContent);
        return ingestDocument;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    String getField() {
        return field;
    }

    //static class used from Elasticsearch to instantiate the processor from the configuration JSON of the pipeline
    //read field and lookup parameter from the configuration
    //create and return an instance of the lookup processor
    public static final class Factory implements Processor.Factory {

        @Override
        public LookupProcessor create(Map<String, Processor.Factory> factories, String tag,
                                      String description, Map<String, Object> config)
                throws Exception {
            // Read the 'field' string
            String field = readStringProperty(TYPE, tag, config, "field");

            // Read the 'lookup-map' properly
            Map<String, Object> lookupMapObj = readMap(TYPE, tag, config, "lookup-map");

            // Convert it manually to Map<String, String>
            Map<String, String> lookupMap = new HashMap<>();
            if (lookupMapObj != null) {
                for (Map.Entry<String, Object> entry : lookupMapObj.entrySet()) {
                    if (entry.getValue() instanceof String) {
                        lookupMap.put(entry.getKey(), (String) entry.getValue());
                    } else {
                        throw new IllegalArgumentException("Invalid type for lookup_map value: " + entry.getValue().getClass());
                    }
                }
            }

            return new LookupProcessor(tag, description, field, lookupMap);
        }
    }
}

