package com.marulab.elk.configuration

import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.testcontainers.elasticsearch.ElasticsearchContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.utility.DockerImageName

@TestConfiguration(proxyBeanMethods = false)
class TestContainersConfig(
	private val elasticsearchProperties: ElasticsearchProperties
) {

	@Container
	val container: ElasticsearchContainer = ElasticsearchContainer(ELASTIC_IMAGE)
		.withPassword(elasticsearchProperties.password)
		.withEnv("discovery.type", "single-node")
		.withEnv("ELASTICSEARCH_JAVA_OPTS", "-Xms512m -Xmx512m")
		.withEnv("xpack.security.transport.ssl.enabled", "false")
		.withEnv("xpack.security.http.ssl.enabled", "false")

	@Bean
	fun elasticsearchContainer(): ElasticsearchContainer = container

	private companion object {
		val ELASTIC_IMAGE: DockerImageName = DockerImageName.parse("elasticsearch:8.17.1")
	}
}