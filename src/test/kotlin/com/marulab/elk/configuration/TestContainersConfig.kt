package com.marulab.elk.configuration

import com.marulab.elk.configuration.properties.ElasticProp
import com.marulab.elk.util.Constants.CONDITIONAL_PROPERTY_NAME
import com.marulab.elk.util.Constants.CONDITIONAL_PROPERTY_PREFIX
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.testcontainers.elasticsearch.ElasticsearchContainer
import org.testcontainers.utility.DockerImageName

@TestConfiguration(proxyBeanMethods = false)
@EnableConfigurationProperties(ElasticProp::class)
@ConditionalOnProperty(prefix = CONDITIONAL_PROPERTY_PREFIX, name = [CONDITIONAL_PROPERTY_NAME], havingValue = "true", matchIfMissing = false)
class TestContainersConfig(
	private val elasticsearchProperties: ElasticProp
) {
	@Bean
	fun elasticsearchContainer(): ElasticsearchContainer = container

	private companion object {
		val ELASTIC_IMAGE: DockerImageName = DockerImageName.parse("elasticsearch:8.17.1")
		val container: ElasticsearchContainer = ElasticsearchContainer(ELASTIC_IMAGE)
			.withPassword("password")
			.withEnv("discovery.type", "single-node")
			.withEnv("ELASTICSEARCH_JAVA_OPTS", "-Xms512m -Xmx512m")
			.withEnv("xpack.security.transport.ssl.enabled", "false")
			.withEnv("xpack.security.http.ssl.enabled", "false")
			.withReuse(true)
	}
}