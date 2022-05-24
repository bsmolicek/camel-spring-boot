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



import static org.apache.camel.component.mail.MailConstants.MAIL_ALTERNATIVE_BODY;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMultipart;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.attachment.AttachmentMessage;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mail.MailConstants;
import org.apache.camel.component.mail.MailEndpoint;
import org.apache.camel.component.mail.MailMessage;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.spring.boot.CamelAutoConfiguration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.jvnet.mock_javamail.Mailbox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@CamelSpringBootTest
@SpringBootTest(
    classes = {
        CamelAutoConfiguration.class,
        MimeMultipartAlternativeWithLongerFilenameTest.class,
        MimeMultipartAlternativeWithLongerFilenameTest.TestConfiguration.class
    }
)
public class MimeMultipartAlternativeWithLongerFilenameTest {

    
    @Autowired
    ProducerTemplate template;
    
    @Autowired
    CamelContext context;
    
    @EndpointInject("mock:result")
    MockEndpoint mock;
    
    private Logger log = LoggerFactory.getLogger(getClass());
    private String alternativeBody = "hello world! (plain text)";
    private String htmlBody = "<html><body><h1>Hello</h1>World<img src=\"cid:myCoolLogo.jpeg\"></body></html>";

    private void sendMultipartEmail(boolean useInlineattachments) {
        Mailbox.clearAll();

        // create an exchange with a normal body and attachment to be produced as email
        MailEndpoint endpoint = context.getEndpoint("smtp://ryan@mymailserver.com?password=secret", MailEndpoint.class);
        endpoint.getConfiguration().setUseInlineAttachments(useInlineattachments);
        endpoint.getConfiguration().setAlternativeBodyHeader(MailConstants.MAIL_ALTERNATIVE_BODY);

        // create the exchange with the mail message that is multipart with a file and a Hello World text/plain message.
        Exchange exchange = endpoint.createExchange();
        AttachmentMessage in = exchange.getIn(AttachmentMessage.class);
        in.setBody(htmlBody);
        in.setHeader(MAIL_ALTERNATIVE_BODY, alternativeBody);
        in.addAttachment("cid:myCoolLogo.jpeg", new DataHandler(new FileDataSource("src/test/data/logo.jpeg")));

        // create a producer that can produce the exchange (= send the mail)
        mock.reset();
        mock.expectedMessageCount(1);
        mock.message(0).header(MailConstants.MAIL_ALTERNATIVE_BODY).isNull();

        context.createProducerTemplate().send(endpoint, exchange);
    }

    private void verifyTheRecivedEmail(String expectString) throws Exception {
        
        mock.assertIsSatisfied();

        Exchange out = mock.assertExchangeReceived(0);
        ByteArrayOutputStream baos = new ByteArrayOutputStream(((MailMessage) out.getIn()).getMessage().getSize());
        ((MailMessage) out.getIn()).getMessage().writeTo(baos);
        String dumpedMessage = baos.toString();
        assertTrue(dumpedMessage.indexOf(expectString) > 0, "There should have the " + expectString);
        log.trace("multipart alternative: \n{}", dumpedMessage);

        // plain text
        assertEquals(alternativeBody, out.getIn().getBody(String.class));

        // attachment
        Map<String, DataHandler> attachments = out.getIn(AttachmentMessage.class).getAttachments();
        assertNotNull(attachments, "Should not have null attachments");
        assertEquals(1, attachments.size());
        assertEquals(2, out.getIn().getBody(MimeMultipart.class).getCount(), "multipart body should have 2 parts");
    }

    @Test
    public void testMultipartEmailWithInlineAttachments() throws Exception {
        sendMultipartEmail(true);
        verifyTheRecivedEmail("Content-Disposition: inline; filename=myCoolLogo.jpeg");
    }

    @Test
    public void testMultipartEmailWithRegularAttachments() throws Exception {
        sendMultipartEmail(false);
        verifyTheRecivedEmail("Content-Disposition: attachment; filename=myCoolLogo.jpeg");
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
                    from("pop3://ryan@mymailserver.com?password=secret&initialDelay=100&delay=100").to("mock:result");
                }
            };
        }
    }
    
   

}
