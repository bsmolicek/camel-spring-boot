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
package org.apache.camel.itest.springboot;

import org.apache.camel.itest.springboot.util.ArquillianPackager;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(ArquillianExtension.class)
public class CamelKubernetesTest extends AbstractSpringBootTestSupport {

    @Deployment
    public static Archive<?> createSpringBootPackage() throws Exception {
        return ArquillianPackager.springBootPackage(createTestConfig());
    }

    public static ITestConfig createTestConfig() {
        return new ITestConfigBuilder()
                .module(inferModuleName(CamelKubernetesTest.class))
                //.dependency(DependencyResolver.withVersion("org.hibernate.validator:hibernate-validator"))
                .build();
    }

    @Test
    public void componentTests() throws Exception {
        this.runComponentTest(createTestConfig(), "kubernetes-config-maps");
        this.runComponentTest(createTestConfig(), "kubernetes-deployments");
        this.runComponentTest(createTestConfig(), "kubernetes-hpa");
        this.runComponentTest(createTestConfig(), "kubernetes-job");
        this.runComponentTest(createTestConfig(), "kubernetes-namespaces");
        this.runComponentTest(createTestConfig(), "kubernetes-nodes");
        this.runComponentTest(createTestConfig(), "kubernetes-persistent-volumes-claims");
        this.runComponentTest(createTestConfig(), "kubernetes-persistent-volumes");
        this.runComponentTest(createTestConfig(), "kubernetes-pods");
        this.runComponentTest(createTestConfig(), "kubernetes-replication-controllers");
        this.runComponentTest(createTestConfig(), "kubernetes-resources-quota");
        this.runComponentTest(createTestConfig(), "kubernetes-secrets");
        this.runComponentTest(createTestConfig(), "kubernetes-service-accounts");
        this.runComponentTest(createTestConfig(), "kubernetes-services");
        this.runComponentTest(createTestConfig(), "openshift-builds");
        this.runComponentTest(createTestConfig(), "openshift-build-configs");

        this.runModuleUnitTestsIfEnabled(config);
    }


}
