package com.marulab.elk

import org.opensearch.testcontainers.OpensearchContainer
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.utility.DockerImageName

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

	@Bean
	fun opensearchContainer(): OpensearchContainer<*> = container

	private companion object {
		val OPENSEARCH_IMAGE = DockerImageName.parse("opensearchproject/opensearch:2.11.0")

		@Container
		val container = OpensearchContainer(OPENSEARCH_IMAGE)
			.withEnv("discovery.type", "single-node")
			.withEnv("OPENSEARCH_JAVA_OPTS", "-Xms512m -Xmx512m")
	}
}
