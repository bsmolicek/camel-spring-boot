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
package org.apache.camel.component.mina.springboot;

import java.util.List;
import javax.annotation.Generated;
import org.apache.camel.LoggingLevel;
import org.apache.camel.component.mina.MinaComponent;
import org.apache.camel.component.mina.MinaConfiguration;
import org.apache.camel.component.mina.MinaTextLineDelimiter;
import org.apache.camel.spring.boot.ComponentConfigurationPropertiesCommon;
import org.apache.camel.support.jsse.SSLContextParameters;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Socket level networking using TCP or UDP with Apache Mina 2.x.
 * 
 * Generated by camel-package-maven-plugin - do not edit this file!
 */
@Generated("org.apache.camel.springboot.maven.SpringBootAutoConfigurationMojo")
@ConfigurationProperties(prefix = "camel.component.mina")
public class MinaComponentConfiguration
        extends
            ComponentConfigurationPropertiesCommon {

    /**
     * Whether to enable auto configuration of the mina component. This is
     * enabled by default.
     */
    private Boolean enabled;
    /**
     * Whether to disconnect(close) from Mina session right after use. Can be
     * used for both consumer and producer.
     */
    private Boolean disconnect = false;
    /**
     * You can enable the Apache MINA logging filter. Apache MINA uses slf4j
     * logging at INFO level to log all input and output.
     */
    private Boolean minaLogger = false;
    /**
     * Setting to set endpoint as one-way or request-response.
     */
    private Boolean sync = true;
    /**
     * You can configure the timeout that specifies how long to wait for a
     * response from a remote server. The timeout unit is in milliseconds, so
     * 60000 is 60 seconds.
     */
    private Long timeout = 30000L;
    /**
     * Maximum amount of time it should take to send data to the MINA session.
     * Default is 10000 milliseconds.
     */
    private Long writeTimeout = 10000L;
    /**
     * Allows for bridging the consumer to the Camel routing Error Handler,
     * which mean any exceptions occurred while the consumer is trying to pickup
     * incoming messages, or the likes, will now be processed as a message and
     * handled by the routing Error Handler. By default the consumer will use
     * the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that
     * will be logged at WARN or ERROR level and ignored.
     */
    private Boolean bridgeErrorHandler = false;
    /**
     * If the clientMode is true, mina consumer will connect the address as a
     * TCP client.
     */
    private Boolean clientMode = false;
    /**
     * If sync is enabled this option dictates MinaConsumer which logging level
     * to use when logging a there is no reply to send back.
     */
    private LoggingLevel noReplyLogLevel = LoggingLevel.WARN;
    /**
     * Whether the producer should be started lazy (on the first message). By
     * starting lazy you can use this to allow CamelContext and routes to
     * startup in situations where a producer may otherwise fail during starting
     * and cause the route to fail being started. By deferring this startup to
     * be lazy then the startup failure can be handled during routing messages
     * via Camel's routing error handlers. Beware that when the first message is
     * processed then creating and starting the producer may take a little time
     * and prolong the total processing time of the processing.
     */
    private Boolean lazyStartProducer = false;
    /**
     * Whether to create the InetAddress once and reuse. Setting this to false
     * allows to pickup DNS changes in the network.
     */
    private Boolean cachedAddress = true;
    /**
     * Sessions can be lazily created to avoid exceptions, if the remote server
     * is not up and running when the Camel producer is started.
     */
    private Boolean lazySessionCreation = true;
    /**
     * Whether autowiring is enabled. This is used for automatic autowiring
     * options (the option must be marked as autowired) by looking up in the
     * registry to find if there is a single instance of matching type, which
     * then gets configured on the component. This can be used for automatic
     * configuring JDBC data sources, JMS connection factories, AWS Clients,
     * etc.
     */
    private Boolean autowiredEnabled = true;
    /**
     * To use the shared mina configuration. The option is a
     * org.apache.camel.component.mina.MinaConfiguration type.
     */
    private MinaConfiguration configuration;
    /**
     * If sync is enabled then this option dictates MinaConsumer if it should
     * disconnect where there is no reply to send back.
     */
    private Boolean disconnectOnNoReply = true;
    /**
     * Number of worker threads in the worker pool for TCP and UDP
     */
    private Integer maximumPoolSize = 16;
    /**
     * Whether to use ordered thread pool, to ensure events are processed
     * orderly on the same channel.
     */
    private Boolean orderedThreadPoolExecutor = true;
    /**
     * Only used for TCP. You can transfer the exchange over the wire instead of
     * just the body. The following fields are transferred: In body, Out body,
     * fault body, In headers, Out headers, fault headers, exchange properties,
     * exchange exception. This requires that the objects are serializable.
     * Camel will exclude any non-serializable objects and log it at WARN level.
     */
    private Boolean transferExchange = false;
    /**
     * The mina component installs a default codec if both, codec is null and
     * textline is false. Setting allowDefaultCodec to false prevents the mina
     * component from installing a default codec as the first element in the
     * filter chain. This is useful in scenarios where another filter must be
     * the first in the filter chain, like the SSL filter.
     */
    private Boolean allowDefaultCodec = true;
    /**
     * To use a custom minda codec implementation. The option is a
     * org.apache.mina.filter.codec.ProtocolCodecFactory type.
     */
    private ProtocolCodecFactory codec;
    /**
     * To set the textline protocol decoder max line length. By default the
     * default value of Mina itself is used which are 1024.
     */
    private Integer decoderMaxLineLength = 1024;
    /**
     * To set the textline protocol encoder max line length. By default the
     * default value of Mina itself is used which are Integer.MAX_VALUE.
     */
    private Integer encoderMaxLineLength = -1;
    /**
     * You can configure the encoding (a charset name) to use for the TCP
     * textline codec and the UDP protocol. If not provided, Camel will use the
     * JVM default Charset
     */
    private String encoding;
    /**
     * You can set a list of Mina IoFilters to use.
     */
    private List<IoFilter> filters;
    /**
     * Only used for TCP. If no codec is specified, you can use this flag to
     * indicate a text line based codec; if not specified or the value is false,
     * then Object Serialization is assumed over TCP.
     */
    private Boolean textline = false;
    /**
     * Only used for TCP and if textline=true. Sets the text line delimiter to
     * use. If none provided, Camel will use DEFAULT. This delimiter is used to
     * mark the end of text.
     */
    private MinaTextLineDelimiter textlineDelimiter;
    /**
     * Whether to auto start SSL handshake.
     */
    private Boolean autoStartTls = true;
    /**
     * To configure SSL security. The option is a
     * org.apache.camel.support.jsse.SSLContextParameters type.
     */
    private SSLContextParameters sslContextParameters;
    /**
     * Enable usage of global SSL context parameters.
     */
    private Boolean useGlobalSslContextParameters = false;

    public Boolean getDisconnect() {
        return disconnect;
    }

    public void setDisconnect(Boolean disconnect) {
        this.disconnect = disconnect;
    }

    public Boolean getMinaLogger() {
        return minaLogger;
    }

    public void setMinaLogger(Boolean minaLogger) {
        this.minaLogger = minaLogger;
    }

    public Boolean getSync() {
        return sync;
    }

    public void setSync(Boolean sync) {
        this.sync = sync;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public Long getWriteTimeout() {
        return writeTimeout;
    }

    public void setWriteTimeout(Long writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    public Boolean getBridgeErrorHandler() {
        return bridgeErrorHandler;
    }

    public void setBridgeErrorHandler(Boolean bridgeErrorHandler) {
        this.bridgeErrorHandler = bridgeErrorHandler;
    }

    public Boolean getClientMode() {
        return clientMode;
    }

    public void setClientMode(Boolean clientMode) {
        this.clientMode = clientMode;
    }

    public LoggingLevel getNoReplyLogLevel() {
        return noReplyLogLevel;
    }

    public void setNoReplyLogLevel(LoggingLevel noReplyLogLevel) {
        this.noReplyLogLevel = noReplyLogLevel;
    }

    public Boolean getLazyStartProducer() {
        return lazyStartProducer;
    }

    public void setLazyStartProducer(Boolean lazyStartProducer) {
        this.lazyStartProducer = lazyStartProducer;
    }

    public Boolean getCachedAddress() {
        return cachedAddress;
    }

    public void setCachedAddress(Boolean cachedAddress) {
        this.cachedAddress = cachedAddress;
    }

    public Boolean getLazySessionCreation() {
        return lazySessionCreation;
    }

    public void setLazySessionCreation(Boolean lazySessionCreation) {
        this.lazySessionCreation = lazySessionCreation;
    }

    public Boolean getAutowiredEnabled() {
        return autowiredEnabled;
    }

    public void setAutowiredEnabled(Boolean autowiredEnabled) {
        this.autowiredEnabled = autowiredEnabled;
    }

    public MinaConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(MinaConfiguration configuration) {
        this.configuration = configuration;
    }

    public Boolean getDisconnectOnNoReply() {
        return disconnectOnNoReply;
    }

    public void setDisconnectOnNoReply(Boolean disconnectOnNoReply) {
        this.disconnectOnNoReply = disconnectOnNoReply;
    }

    public Integer getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(Integer maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public Boolean getOrderedThreadPoolExecutor() {
        return orderedThreadPoolExecutor;
    }

    public void setOrderedThreadPoolExecutor(Boolean orderedThreadPoolExecutor) {
        this.orderedThreadPoolExecutor = orderedThreadPoolExecutor;
    }

    public Boolean getTransferExchange() {
        return transferExchange;
    }

    public void setTransferExchange(Boolean transferExchange) {
        this.transferExchange = transferExchange;
    }

    public Boolean getAllowDefaultCodec() {
        return allowDefaultCodec;
    }

    public void setAllowDefaultCodec(Boolean allowDefaultCodec) {
        this.allowDefaultCodec = allowDefaultCodec;
    }

    public ProtocolCodecFactory getCodec() {
        return codec;
    }

    public void setCodec(ProtocolCodecFactory codec) {
        this.codec = codec;
    }

    public Integer getDecoderMaxLineLength() {
        return decoderMaxLineLength;
    }

    public void setDecoderMaxLineLength(Integer decoderMaxLineLength) {
        this.decoderMaxLineLength = decoderMaxLineLength;
    }

    public Integer getEncoderMaxLineLength() {
        return encoderMaxLineLength;
    }

    public void setEncoderMaxLineLength(Integer encoderMaxLineLength) {
        this.encoderMaxLineLength = encoderMaxLineLength;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public List<IoFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<IoFilter> filters) {
        this.filters = filters;
    }

    public Boolean getTextline() {
        return textline;
    }

    public void setTextline(Boolean textline) {
        this.textline = textline;
    }

    public MinaTextLineDelimiter getTextlineDelimiter() {
        return textlineDelimiter;
    }

    public void setTextlineDelimiter(MinaTextLineDelimiter textlineDelimiter) {
        this.textlineDelimiter = textlineDelimiter;
    }

    public Boolean getAutoStartTls() {
        return autoStartTls;
    }

    public void setAutoStartTls(Boolean autoStartTls) {
        this.autoStartTls = autoStartTls;
    }

    public SSLContextParameters getSslContextParameters() {
        return sslContextParameters;
    }

    public void setSslContextParameters(
            SSLContextParameters sslContextParameters) {
        this.sslContextParameters = sslContextParameters;
    }

    public Boolean getUseGlobalSslContextParameters() {
        return useGlobalSslContextParameters;
    }

    public void setUseGlobalSslContextParameters(
            Boolean useGlobalSslContextParameters) {
        this.useGlobalSslContextParameters = useGlobalSslContextParameters;
    }
}