/*
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
package org.apache.camel.converter.jaxb.springboot;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.Builder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.example.PurchaseOrder;
import org.apache.camel.spi.DataFormat;
import org.apache.camel.spring.boot.CamelAutoConfiguration;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;


@DirtiesContext
@CamelSpringBootTest
@SpringBootTest(
    classes = {
        CamelAutoConfiguration.class,
        JaxbConcurrentDataFormatTest.class,
        JaxbConcurrentDataFormatTest.TestConfiguration.class
    }
)
public class JaxbConcurrentDataFormatTest {
    
            
    @Autowired
    ProducerTemplate template;

    
    
    @EndpointInject("mock:result")
    private MockEndpoint result;

    
    @Test
    public void testNoConcurrentProducers() throws Exception {
        doSendMessages(1, 1);
    }

    @Test
    public void testConcurrentProducers() throws Exception {
        doSendMessages(10, 5);
    }

    private void doSendMessages(int files, int poolSize) throws Exception {
        result.reset();
        result.expectedMessageCount(files);
        result.assertNoDuplicates(Builder.body());

        ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        for (int i = 0; i < files; i++) {
            final int index = i;
            executor.submit(new Callable<Object>() {
                public Object call() throws Exception {
                    PurchaseOrder bean = new PurchaseOrder();
                    bean.setName("Beer");
                    bean.setAmount(Double.valueOf("" + index));
                    bean.setPrice(Double.valueOf("" + index) * 2);

                    template.sendBody("direct:start", bean);
                    return null;
                }
            });
        }

        result.assertIsSatisfied();
        executor.shutdownNow();
    }


    // *************************************
    // Config
    // *************************************

    @Configuration
    public class TestConfiguration {

        @Bean
        public RouteBuilder routeBuilder() {
            return new RouteBuilder() {
                @Override
                public void configure() {
                    DataFormat jaxb = new JaxbDataFormat("org.apache.camel.example");

                    from("direct:start").marshal(jaxb).to("direct:marshalled");

                    from("direct:marshalled").unmarshal(jaxb).to("mock:result");
                }
            };
        }
    }
}
