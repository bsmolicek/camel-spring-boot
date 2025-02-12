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
package org.apache.camel.component.etcd3.springboot;

import java.time.Duration;
import java.util.Map;
import javax.annotation.Generated;
import io.netty.handler.ssl.SslContext;
import org.apache.camel.component.etcd3.Etcd3Configuration;
import org.apache.camel.spring.boot.ComponentConfigurationPropertiesCommon;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Get, set, delete or watch keys in etcd key-value store.
 * 
 * Generated by camel-package-maven-plugin - do not edit this file!
 */
@Generated("org.apache.camel.springboot.maven.SpringBootAutoConfigurationMojo")
@ConfigurationProperties(prefix = "camel.component.etcd3")
public class Etcd3ComponentConfiguration
        extends
            ComponentConfigurationPropertiesCommon {

    /**
     * Whether to enable auto configuration of the etcd3 component. This is
     * enabled by default.
     */
    private Boolean enabled;
    /**
     * Component configuration. The option is a
     * org.apache.camel.component.etcd3.Etcd3Configuration type.
     */
    private Etcd3Configuration configuration;
    /**
     * Configure etcd server endpoints using the IPNameResolver.
     */
    private String[] endpoints;
    /**
     * Configure the charset to use for the keys.
     */
    private String keyCharset = "UTF-8";
    /**
     * Configure the namespace of keys used. / will be treated as no namespace.
     */
    private String namespace;
    /**
     * To apply an action on all the key-value pairs whose key that starts with
     * the target path.
     */
    private Boolean prefix = false;
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
     * The index to watch from
     */
    private Long fromIndex = 0L;
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
     * Configure the charset to use for the values.
     */
    private String valueCharset = "UTF-8";
    /**
     * Configure the headers to be added to auth request headers.
     */
    private Map<String, String> authHeaders;
    /**
     * Configure the authority used to authenticate connections to servers.
     */
    private String authority;
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
     * Configure the connection timeout. The option is a java.time.Duration
     * type.
     */
    private Duration connectionTimeout;
    /**
     * Configure the headers to be added to http request headers.
     */
    private Map<String, String> headers;
    /**
     * Configure the interval for gRPC keepalives. The current minimum allowed
     * by gRPC is 10 seconds. The option is a java.time.Duration type.
     */
    private Duration keepAliveTime;
    /**
     * Configure the timeout for gRPC keepalives. The option is a
     * java.time.Duration type.
     */
    private Duration keepAliveTimeout;
    /**
     * Configure etcd load balancer policy.
     */
    private String loadBalancerPolicy;
    /**
     * Configure the maximum message size allowed for a single gRPC frame.
     */
    private Integer maxInboundMessageSize;
    /**
     * Configure the delay between retries in milliseconds.
     */
    private Long retryDelay = 500L;
    /**
     * Configure the max backing off delay between retries in milliseconds.
     */
    private Long retryMaxDelay = 2500L;
    /**
     * Configure the retries max duration. The option is a java.time.Duration
     * type.
     */
    private Duration retryMaxDuration;
    /**
     * The path to look for service discovery.
     */
    private String servicePath = "/services/";
    /**
     * Configure etcd auth password.
     */
    private String password;
    /**
     * Configure SSL/TLS context to use instead of the system default. The
     * option is a io.netty.handler.ssl.SslContext type.
     */
    private SslContext sslContext;
    /**
     * Configure etcd auth user.
     */
    private String userName;

    public Etcd3Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Etcd3Configuration configuration) {
        this.configuration = configuration;
    }

    public String[] getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(String[] endpoints) {
        this.endpoints = endpoints;
    }

    public String getKeyCharset() {
        return keyCharset;
    }

    public void setKeyCharset(String keyCharset) {
        this.keyCharset = keyCharset;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public Boolean getPrefix() {
        return prefix;
    }

    public void setPrefix(Boolean prefix) {
        this.prefix = prefix;
    }

    public Boolean getBridgeErrorHandler() {
        return bridgeErrorHandler;
    }

    public void setBridgeErrorHandler(Boolean bridgeErrorHandler) {
        this.bridgeErrorHandler = bridgeErrorHandler;
    }

    public Long getFromIndex() {
        return fromIndex;
    }

    public void setFromIndex(Long fromIndex) {
        this.fromIndex = fromIndex;
    }

    public Boolean getLazyStartProducer() {
        return lazyStartProducer;
    }

    public void setLazyStartProducer(Boolean lazyStartProducer) {
        this.lazyStartProducer = lazyStartProducer;
    }

    public String getValueCharset() {
        return valueCharset;
    }

    public void setValueCharset(String valueCharset) {
        this.valueCharset = valueCharset;
    }

    public Map<String, String> getAuthHeaders() {
        return authHeaders;
    }

    public void setAuthHeaders(Map<String, String> authHeaders) {
        this.authHeaders = authHeaders;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Boolean getAutowiredEnabled() {
        return autowiredEnabled;
    }

    public void setAutowiredEnabled(Boolean autowiredEnabled) {
        this.autowiredEnabled = autowiredEnabled;
    }

    public Duration getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(Duration connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Duration getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(Duration keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public Duration getKeepAliveTimeout() {
        return keepAliveTimeout;
    }

    public void setKeepAliveTimeout(Duration keepAliveTimeout) {
        this.keepAliveTimeout = keepAliveTimeout;
    }

    public String getLoadBalancerPolicy() {
        return loadBalancerPolicy;
    }

    public void setLoadBalancerPolicy(String loadBalancerPolicy) {
        this.loadBalancerPolicy = loadBalancerPolicy;
    }

    public Integer getMaxInboundMessageSize() {
        return maxInboundMessageSize;
    }

    public void setMaxInboundMessageSize(Integer maxInboundMessageSize) {
        this.maxInboundMessageSize = maxInboundMessageSize;
    }

    public Long getRetryDelay() {
        return retryDelay;
    }

    public void setRetryDelay(Long retryDelay) {
        this.retryDelay = retryDelay;
    }

    public Long getRetryMaxDelay() {
        return retryMaxDelay;
    }

    public void setRetryMaxDelay(Long retryMaxDelay) {
        this.retryMaxDelay = retryMaxDelay;
    }

    public Duration getRetryMaxDuration() {
        return retryMaxDuration;
    }

    public void setRetryMaxDuration(Duration retryMaxDuration) {
        this.retryMaxDuration = retryMaxDuration;
    }

    public String getServicePath() {
        return servicePath;
    }

    public void setServicePath(String servicePath) {
        this.servicePath = servicePath;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SslContext getSslContext() {
        return sslContext;
    }

    public void setSslContext(SslContext sslContext) {
        this.sslContext = sslContext;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}