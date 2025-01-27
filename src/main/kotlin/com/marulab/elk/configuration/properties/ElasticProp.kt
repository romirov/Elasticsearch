package com.marulab.elk.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding
import java.time.Duration

@ConfigurationProperties(prefix = "spring.elasticsearch")
data class ElasticProp @ConstructorBinding constructor(
	val enabled: Boolean,
	val url: String,
	val username: String,
	val password: String,
	val connectionTimeout: Duration,
	val socketTimeout: Duration,
	val indexNamePattern: String
)