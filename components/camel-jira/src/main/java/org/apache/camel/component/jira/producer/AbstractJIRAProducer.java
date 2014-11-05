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
package org.apache.camel.component.jira.producer;

import java.net.URI;

import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClientFactory;
import org.apache.camel.Exchange;
import org.apache.camel.component.jira.JIRAEndpoint;
import org.apache.camel.impl.DefaultProducer;

public abstract class AbstractJIRAProducer extends DefaultProducer {
    
    private final JiraRestClient client;
    
    public AbstractJIRAProducer(JIRAEndpoint endpoint) throws Exception {
        super(endpoint);
        
        final JerseyJiraRestClientFactory factory = new JerseyJiraRestClientFactory();
        final URI jiraServerUri = URI.create(endpoint.getServerUrl());
        client = factory.createWithBasicHttpAuthentication(jiraServerUri, endpoint.getUsername(),
                                                           endpoint.getPassword());
    }
    
    protected JiraRestClient client() {
        return client;
    }

    public abstract void process(Exchange exchange) throws Exception;
}
