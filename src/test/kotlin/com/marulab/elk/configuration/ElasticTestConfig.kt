package com.marulab.elk.configuration

import org.opensearch.testcontainers.OpensearchContainer
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.data.elasticsearch.client.ClientConfiguration
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration

@TestConfiguration
class ElasticTestConfig(
	private val elasticsearchProperties: ElasticsearchProperties,
	private val container: OpensearchContainer<*>
): ElasticsearchConfiguration() {

	override fun clientConfiguration(): ClientConfiguration = ClientConfiguration
		.builder()
		.connectedTo(container.host + ":" + container.firstMappedPort)
		.withBasicAuth(container.username, container.password)
		.withConnectTimeout(elasticsearchProperties.connectionTimeout)
		.withSocketTimeout(elasticsearchProperties.socketTimeout)
		.build()
}