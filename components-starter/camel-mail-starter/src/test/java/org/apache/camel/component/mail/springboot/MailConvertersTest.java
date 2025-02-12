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
package org.apache.camel.component.mail.springboot;



import static org.apache.camel.test.junit5.TestSupport.assertIsInstanceOf;

import java.io.InputStream;

import javax.mail.Message;
import javax.mail.Multipart;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mail.MailConstants;
import org.apache.camel.component.mail.MailConverters;
import org.apache.camel.component.mail.MailMessage;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.spring.boot.CamelAutoConfiguration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.jvnet.mock_javamail.Mailbox;


@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@CamelSpringBootTest
@SpringBootTest(
    classes = {
        CamelAutoConfiguration.class,
        MailConvertersTest.class,
        MailConvertersTest.TestConfiguration.class
    }
)
public class MailConvertersTest {

    
    @Autowired
    ProducerTemplate template;
    
    @Autowired
    CamelContext context;
    
    @EndpointInject("mock:result")
    MockEndpoint mock;
    
    @BeforeEach
    public void setUp() throws Exception {
        Mailbox.clearAll();
    }

    @Test
    public void testMailMessageToString() throws Exception {
        
        mock.reset();
        
        mock.expectedMessageCount(1);

        template.sendBodyAndHeader("direct:a", "Hello World", "Subject", "Camel rocks");
        
        mock.assertIsSatisfied();
        

        Message mailMessage = mock.getReceivedExchanges().get(0).getIn().getBody(MailMessage.class).getMessage();
        assertNotNull(mailMessage);

        String s = MailConverters.toString(mailMessage);
        assertEquals("Hello World", s);
    }

    @Test
    public void testMailMessageToInputStream() throws Exception {
        mock.reset();
        
        mock.expectedMessageCount(1);

        template.sendBodyAndHeader("direct:a", "Hello World", "Subject", "Camel rocks");

        mock.assertIsSatisfied();

        Message mailMessage = mock.getReceivedExchanges().get(0).getIn().getBody(MailMessage.class).getMessage();
        assertNotNull(mailMessage);

        InputStream is = MailConverters.toInputStream(mailMessage);
        assertNotNull(is);
        assertEquals("Hello World", context.getTypeConverter().convertTo(String.class, is));
    }

    @Test
    public void testMultipartToInputStream() throws Exception {
        mock.reset();
        mock.expectedMessageCount(1);

        template.send("direct:a", new Processor() {
            public void process(Exchange exchange) {
                exchange.getIn().setBody("Hello World");
                exchange.getIn().setHeader(MailConstants.MAIL_ALTERNATIVE_BODY, "Alternative World");
            }
        });

        mock.assertIsSatisfied();

        Message mailMessage = mock.getReceivedExchanges().get(0).getIn().getBody(MailMessage.class).getMessage();
        assertNotNull(mailMessage);

        Object content = mailMessage.getContent();
        assertIsInstanceOf(Multipart.class, content);

        InputStream is = mock.getReceivedExchanges().get(0).getIn().getBody(InputStream.class);
        assertNotNull(is);
        assertEquals("Alternative World", context.getTypeConverter().convertTo(String.class, is));
    }

    @Test
    public void testMultipartToByteArray() throws Exception {
        mock.reset();
        mock.expectedMessageCount(1);

        template.send("direct:a", new Processor() {
            public void process(Exchange exchange) {
                exchange.getIn().setBody("Hello World");
                exchange.getIn().setHeader(MailConstants.MAIL_ALTERNATIVE_BODY, "Alternative World");
            }
        });

        mock.assertIsSatisfied();

        Message mailMessage = mock.getReceivedExchanges().get(0).getIn().getBody(MailMessage.class).getMessage();
        assertNotNull(mailMessage);

        Object content = mailMessage.getContent();
        assertIsInstanceOf(Multipart.class, content);

        byte[] is = mock.getReceivedExchanges().get(0).getIn().getBody(byte[].class);
        assertNotNull(is);
        assertEquals("Alternative World", context.getTypeConverter().convertTo(String.class, is));
    }

    @Test
    public void testMultipartToString() throws Exception {
        mock.reset();
        mock.expectedMessageCount(1);

        template.send("direct:a", new Processor() {
            public void process(Exchange exchange) {
                exchange.getIn().setBody("Hello World");
                exchange.getIn().setHeader(MailConstants.MAIL_ALTERNATIVE_BODY, "Alternative World");
            }
        });

        mock.assertIsSatisfied();

        Message mailMessage = mock.getReceivedExchanges().get(0).getIn().getBody(MailMessage.class).getMessage();
        assertNotNull(mailMessage);

        Object content = mailMessage.getContent();
        Multipart mp = assertIsInstanceOf(Multipart.class, content);

        String s = MailConverters.toString(mp);
        assertEquals("Alternative World", s);
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
                    from("direct:a").to("smtp://localhost?username=james@localhost");

                    from("pop3://localhost?username=james&password=secret&initialDelay=100&delay=100").to("mock:result");
                }
            };
        }
    }
    
   

}
