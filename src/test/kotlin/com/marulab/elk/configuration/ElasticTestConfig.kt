package com.marulab.elk.configuration

import com.marulab.elk.configuration.properties.ElasticProp
import com.marulab.elk.util.Constants.CONDITIONAL_PROPERTY_NAME
import com.marulab.elk.util.Constants.CONDITIONAL_PROPERTY_PREFIX
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.data.elasticsearch.client.ClientConfiguration
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration
import org.testcontainers.elasticsearch.ElasticsearchContainer

@TestConfiguration
@EnableConfigurationProperties(ElasticProp::class)
@ConditionalOnProperty(prefix = CONDITIONAL_PROPERTY_PREFIX, name = [CONDITIONAL_PROPERTY_NAME], havingValue = "true", matchIfMissing = false)
class ElasticTestConfig(
	private val elasticsearchProperties: ElasticProp,
	private val container: ElasticsearchContainer
) : ElasticsearchConfiguration() {

	override fun clientConfiguration(): ClientConfiguration = ClientConfiguration
		.builder()
		.connectedTo(container.host + ":" + container.firstMappedPort)
		.withBasicAuth(elasticsearchProperties.username, elasticsearchProperties.password)
		.withConnectTimeout(elasticsearchProperties.connectionTimeout)
		.withSocketTimeout(elasticsearchProperties.socketTimeout)
		.build()
}