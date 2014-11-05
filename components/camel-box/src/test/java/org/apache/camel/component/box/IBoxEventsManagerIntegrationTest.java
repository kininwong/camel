/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * Camel Api Route test generated by camel-component-util-maven-plugin
 * Generated on: Tue Jun 24 22:42:08 PDT 2014
 */
package org.apache.camel.component.box;

import com.box.boxjavalibv2.dao.BoxCollection;
import com.box.boxjavalibv2.dao.BoxEventCollection;
import com.box.boxjavalibv2.requests.requestobjects.BoxEventRequestObject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.box.internal.BoxApiCollection;
import org.apache.camel.component.box.internal.IBoxEventsManagerApiMethod;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test class for com.box.boxjavalibv2.resourcemanagers.IBoxEventsManager APIs.
 */
public class IBoxEventsManagerIntegrationTest extends AbstractBoxTestSupport {

    private static final Logger LOG = LoggerFactory.getLogger(IBoxEventsManagerIntegrationTest.class);
    private static final String PATH_PREFIX = BoxApiCollection.getCollection().getApiName(IBoxEventsManagerApiMethod.class).getName();

    @Test
    public void testGetEventOptions() throws Exception {
        // using com.box.restclientv2.requestsbase.BoxDefaultRequestObject message body for single parameter "defaultRequest"
        BoxCollection result = requestBody("direct://GETEVENTOPTIONS", null);

        assertNotNull("getEventOptions result", result);
        LOG.debug("getEventOptions: " + result);
    }

    @Test
    public void testGetEvents() throws Exception {
        // using com.box.boxjavalibv2.requests.requestobjects.BoxEventRequestObject message body for single parameter "eventRequest"
        final BoxEventRequestObject requestObject =
                BoxEventRequestObject.getEventsRequestObject(BoxEventRequestObject.STREAM_POSITION_NOW);
        BoxEventCollection result = requestBody("direct://GETEVENTS", requestObject);

        assertNotNull("getEvents result", result);
        LOG.debug("getEvents: " + result);
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                // test route for getEventOptions
                from("direct://GETEVENTOPTIONS")
                        .to("box://" + PATH_PREFIX + "/getEventOptions?inBody=defaultRequest");

                // test route for getEvents
                from("direct://GETEVENTS")
                        .to("box://" + PATH_PREFIX + "/getEvents?inBody=eventRequest");

            }
        };
    }
}
