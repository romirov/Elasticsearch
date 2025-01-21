package com.marulab.elk.configuration

import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.client.ClientConfiguration
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories

@Configuration
@EnableElasticsearchRepositories(basePackageClasses = [com.marulab.elk.repository.ElasticRepo::class])
class ElasticConfig(
	private val elasticsearchProperties: ElasticsearchProperties
) : ElasticsearchConfiguration() {
	override fun clientConfiguration(): ClientConfiguration = ClientConfiguration
		.builder()
		.connectedTo(elasticsearchProperties.uris.joinToString(","))
		.withBasicAuth(elasticsearchProperties.username, elasticsearchProperties.password)
		.withConnectTimeout(elasticsearchProperties.connectionTimeout)
		.withSocketTimeout(elasticsearchProperties.socketTimeout)
		.build()
}