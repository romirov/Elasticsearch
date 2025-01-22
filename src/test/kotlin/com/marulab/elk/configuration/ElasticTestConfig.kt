package com.marulab.elk.configuration

import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.data.elasticsearch.client.ClientConfiguration
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration
import org.testcontainers.elasticsearch.ElasticsearchContainer

@TestConfiguration
class ElasticTestConfig(
	private val elasticsearchProperties: ElasticsearchProperties,
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