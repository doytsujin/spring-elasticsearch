/*
 * Licensed to David Pilato (the "Author") under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Author licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package fr.pilato.spring.elasticsearch.it.annotation.transport.multipleclients;

import fr.pilato.spring.elasticsearch.ElasticsearchTransportClientFactoryBean;
import org.elasticsearch.client.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class AppConfig {

	@Bean
	public Client esClient(Properties esProperties) throws Exception {
		ElasticsearchTransportClientFactoryBean factory = new ElasticsearchTransportClientFactoryBean();
		factory.setProperties(esProperties);
		factory.setClasspathRoot("/models/root/multiple-clients/client1");
		factory.setMappings(new String[]{"twitter/_doc"});
		factory.setForceMapping(false);
		factory.afterPropertiesSet();
		return factory.getObject();
	}

	@Bean
	public Client esClient2(Properties esProperties) throws Exception {
		ElasticsearchTransportClientFactoryBean factory = new ElasticsearchTransportClientFactoryBean();
		factory.setProperties(esProperties);
		factory.setClasspathRoot("/models/root/multiple-clients/client2");
		factory.setMappings(new String[]{"twitter/_doc"});
		factory.setMergeSettings(true);
		factory.afterPropertiesSet();
		return factory.getObject();
	}
}
