package com.marulab.elk.configuration

import com.marulab.elk.configuration.properties.ElasticProp
import com.marulab.elk.util.Constants.CONDITIONAL_PROPERTY_NAME
import com.marulab.elk.util.Constants.CONDITIONAL_PROPERTY_PREFIX
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.client.ClientConfiguration
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories

@Configuration
@EnableConfigurationProperties(ElasticProp::class)
@EnableElasticsearchRepositories(basePackages = ["com.marulab.elk.repository"])
@ConditionalOnProperty(prefix = CONDITIONAL_PROPERTY_PREFIX, name = [CONDITIONAL_PROPERTY_NAME], havingValue = "true", matchIfMissing = false)
class ElasticConfig(
	private val elasticsearchProperties: ElasticProp
) : ElasticsearchConfiguration() {

	override fun clientConfiguration(): ClientConfiguration = ClientConfiguration
		.builder()
		.connectedTo(elasticsearchProperties.url)
		.withBasicAuth(elasticsearchProperties.username, elasticsearchProperties.password)
		.withConnectTimeout(elasticsearchProperties.connectionTimeout)
		.withSocketTimeout(elasticsearchProperties.socketTimeout)
		.build()
}