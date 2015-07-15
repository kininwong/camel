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
package org.apache.camel.component.undertow;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Ignore;
import org.junit.Test;

public class UndertowProducerTest extends CamelTestSupport {

    @Ignore
    @Test
    public void testHttpSimple() throws Exception {
        getMockEndpoint("mock:input").expectedHeaderReceived(Exchange.HTTP_METHOD, "GET");

        String out = template.requestBody("undertow:http://localhost:8888/foo", null, String.class);
        assertEquals("Bye World", out);

        assertMockEndpointsSatisfied();
    }

    @Ignore
    @Test
    public void testHttpSimpleHeader() throws Exception {
        getMockEndpoint("mock:input").expectedHeaderReceived(Exchange.HTTP_METHOD, "POST");

        String out = template.requestBodyAndHeader("undertow:http://localhost:8888/foo", null, Exchange.HTTP_METHOD, "POST", String.class);
        assertEquals("Bye World", out);

        assertMockEndpointsSatisfied();
    }

    @Ignore
    @Test
    public void testHttpSimpleHeaderAndBody() throws Exception {

        getMockEndpoint("mock:input").expectedBodiesReceived("Hello World");
        getMockEndpoint("mock:input").expectedHeaderReceived(Exchange.HTTP_METHOD, "POST");

        String out = template.requestBodyAndHeader("undertow:http://localhost:8888/foo", "Hello World", Exchange.HTTP_METHOD, "POST", String.class);
        assertEquals("Bye World", out);

        assertMockEndpointsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("undertow:http://localhost:8888/foo")
                    .to("mock:input")
                    .transform().constant("Bye World");
            }
        };
    }
}